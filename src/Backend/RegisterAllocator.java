package Backend;

import OperandRV.*;
import OperandRV.InstRV.*;

import java.util.*;

import static java.lang.Integer.min;
import static java.lang.Math.pow;

public class RegisterAllocator {
    static final int K = RV32.normal.size();
    ModuleRV module;
    FunctionRV curFunction;

    Set<Register> preColored = new LinkedHashSet<>();
    Set<Register> initial = new LinkedHashSet<>();
    Set<Register> simplifyWorkList = new LinkedHashSet<>();
    Set<Register> freezeWorkList = new LinkedHashSet<>();
    Set<Register> spillWorkList = new LinkedHashSet<>();
    Set<Register> spilledNodes = new LinkedHashSet<>();
    Set<Register> coalescedNodes = new LinkedHashSet<>();
    Set<Register> coloredNodes = new LinkedHashSet<>();
    Stack<Register> selectStack = new Stack<>();

    Set<MoveInst> coalescedMoves = new LinkedHashSet<>();
    Set<MoveInst> constrainedMoves = new LinkedHashSet<>();
    Set<MoveInst> frozenMoves = new LinkedHashSet<>();
    Set<MoveInst> workListMoves = new LinkedHashSet<>();
    Set<MoveInst> activeMoves = new LinkedHashSet<>();

    Set<Map.Entry<Register, Register>> adjSet = new LinkedHashSet<>();
    Map<Register, Set<Register>> adjList = new LinkedHashMap<>();
    Map<Register, Integer> degree = new LinkedHashMap<>();
    Map<Register, Set<MoveInst>> moveList = new LinkedHashMap<>();
    Map<Register, Register> alias = new LinkedHashMap<>();
    Map<Register, Register> colour = new LinkedHashMap<>();

    Map<Register, Immediate> framePos = new LinkedHashMap<>();
    Map<Register, Double> priority = new LinkedHashMap<>();
    Set<Register> oldTemps = new LinkedHashSet<>();
    int stackSize;

    public RegisterAllocator(ModuleRV module) {
        this.module = module;
        preColored.addAll(RV32.registers.values());
        Set<Register> gotcha = new LinkedHashSet<>();
        for (FunctionRV function : module.getFunctions()) {
            Set<Register> tmpGotcha = new LinkedHashSet<>();
            function.getBlocks().forEach(block -> block.getInstructions().forEach(i ->{
                for (Register reg : i.getDef()) {
                    if (gotcha.contains(reg) && !reg.isGlobal() && reg instanceof VRegister) {
                        throw new RuntimeException();
                    } else {
                        tmpGotcha.add(reg);
                    }
                }
                for (Register reg : i.getUses()) {
                    if (gotcha.contains(reg) && !reg.isGlobal() && reg instanceof VRegister) {
                        throw new RuntimeException();
                    } else {
                        tmpGotcha.add(reg);
                    }
                }
            }));
            gotcha.addAll(tmpGotcha);
            run(function);
            stackSize += function.getMaxParamBytes();
            stackSize = (stackSize + 15) / 16 * 16;
            function.getBlocks().forEach(b -> b.getInstructions().forEach(i -> i.adjustImmediate(stackSize)));

            colour.forEach((reg, colour) -> {
//                System.err.println("_" + reg.getIdentifier());
//                System.err.println("__" + colour.getIdentifier());
                reg.setIdentifier(colour.getIdentifier());
            });
//            if (function.getIdentifier().equals("check")) {
//                break;
//            }
        }
        removeMove();
    }

    void init() {
        initial = new LinkedHashSet<>();
        simplifyWorkList = new LinkedHashSet<>();
        freezeWorkList = new LinkedHashSet<>();
        spillWorkList = new LinkedHashSet<>();
        spilledNodes = new LinkedHashSet<>();
        coalescedNodes = new LinkedHashSet<>();
        coloredNodes = new LinkedHashSet<>();
        selectStack = new Stack<>();

        coalescedMoves = new LinkedHashSet<>();
        constrainedMoves = new LinkedHashSet<>();
        frozenMoves = new LinkedHashSet<>();
        workListMoves = new LinkedHashSet<>();
        activeMoves = new LinkedHashSet<>();

        adjSet = new LinkedHashSet<>();
        adjList = new LinkedHashMap<>();
        degree = new LinkedHashMap<>();
        moveList = new LinkedHashMap<>();
        alias = new LinkedHashMap<>();
        colour = new LinkedHashMap<>();
        RV32.registers.values().forEach(reg -> colour.put(reg, reg));
        RV32.registers.values().forEach(reg -> degree.put(reg, Integer.MAX_VALUE / 2));

        priority = new LinkedHashMap<>();

        for (BlockRV block : curFunction.getBlocks()) {
            for (InstRV inst : block.getInstructions()) {
                initial.addAll(inst.getDef());
                initial.addAll(inst.getUses());
            }
        }
        initial.removeAll(preColored);
    }

    private void run(FunctionRV function) {
        curFunction = function;
        stackSize = 0;
        oldTemps.clear();
        for (;;) {
            init();
            calcPriority();
            new LivenessAnalyzer(function);
            build();
            makeWorkList();
//            if (function.getIdentifier().equals("check")) {
//                for (var q : adjList.entrySet()) {
//                    if (q.getKey().getIdentifier().equals("v0")) {
//                        System.err.println(colour.get(q.getKey()));
//                        System.err.println(getAlias(q.getKey()));
//                    System.err.println(q.getValue().getIdentifier());
//                        for (var v : q.getValue())
//                            System.err.println("***" + v.getIdentifier() + " " + colour.get(v));
//                    }
//                }
//            }
            do {
                if (!simplifyWorkList.isEmpty()) {
                    simplify();
                } else if (!workListMoves.isEmpty()) {
                    coalesce();
                } else if (!freezeWorkList.isEmpty()) {
                    freeze();
                } else if (!spillWorkList.isEmpty()) {
                    selectSpill();
                }
            } while (!simplifyWorkList.isEmpty() || !workListMoves.isEmpty()
                    || !freezeWorkList.isEmpty() || !spillWorkList.isEmpty());

            assignColours();
            if (!spilledNodes.isEmpty()) {
                rewriteProgram();
            } else {
                break;
            }
        }
//        if (function.getIdentifier().equals("check")) {
//            System.err.println("\n");
//            for (var q : adjList.entrySet()) {
//                if (q.getKey().getIdentifier().equals("v0")) {
//                    System.err.println(colour.get(q.getKey()));
//                    System.err.println(getAlias(q.getKey()));
//                    for (var v : q.getValue())
//                        System.err.println("***" + v.getIdentifier() + " " + colour.get(v));
//                }
//            }
//            System.err.println("\nfinish\n");
//        }
    }

    private void calcPriority() {
        initial.forEach(reg -> priority.put(reg, 0.0));
        oldTemps.forEach(reg -> priority.put(reg, pow(10, 50)));

        for (BlockRV block : curFunction.getBlocks()) {
            Double weight = pow(10, min(block.getPredecessors().size(), block.getSuccessors().size()));
            for (InstRV inst : block.getInstructions()) {
                inst.getDef().forEach(reg ->
                        priority.put(reg, priority.getOrDefault(reg, 666666.666) + weight));
                inst.getUses().forEach(reg ->
                        priority.put(reg, priority.getOrDefault(reg, 666666.666) + weight));
            }
        }
    }

    private void build() {
        for (BlockRV block : curFunction.getBlocks()) {
            Set<Register> live = new LinkedHashSet<>(block.getLiveOut());
            live.add(RV32.get("zero"));
            List<InstRV> instructions = block.getInstructions();
            for (int i = instructions.size() - 1; i >= 0; --i) {
                InstRV inst = instructions.get(i);
                if (inst instanceof MoveInst) {
                    live.removeAll(inst.getUses());
                    for (Register r : inst.getUses()) {
                        moveList.putIfAbsent(r, new LinkedHashSet<>());
                        moveList.get(r).add((MoveInst) inst);
                    }
                    for (Register def : inst.getDef()) {
                        moveList.putIfAbsent(def, new LinkedHashSet<>());
                        moveList.get(def).add((MoveInst) inst);
                    }
                    workListMoves.add((MoveInst) inst);
                }
                live.addAll(inst.getDef());
                for (Register d : inst.getDef()) {
                    for (Register l : live) {
                            addEdge(l, d);
                    }
                }
                live.removeAll(inst.getDef());
                live.addAll(inst.getUses());
            }
        }
    }

    private void addEdge(Register u, Register v) {
        if (u != v && !adjSet.contains(Map.entry(u, v))) {
            adjSet.add(Map.entry(u, v));
            adjSet.add(Map.entry(v, u));
            if (!preColored.contains(u)) {
                adjList.putIfAbsent(u, new LinkedHashSet<>());
                adjList.get(u).add(v);
                degree.put(u, degree.getOrDefault(u, 0) + 1);
            }
            if (!preColored.contains(v)) {
                adjList.putIfAbsent(v, new LinkedHashSet<>());
                adjList.get(v).add(u);
                degree.put(v, degree.getOrDefault(v, 0) + 1);
            }
        }
    }



    private void makeWorkList() {
        var iterator = initial.iterator();
        while (iterator.hasNext()) {
            Register n = iterator.next();
            iterator.remove();
            if (degree.getOrDefault(n, 0) >= K) {
                spillWorkList.add(n);
            } else if (moveRelated(n)) {
                freezeWorkList.add(n);
            } else {
                simplifyWorkList.add(n);
            }
        }
    }

    private Set<Register> adjacent(Register n) {
        adjList.putIfAbsent(n, new LinkedHashSet<>());
        Set<Register> ret = adjList.get(n);
        ret.removeAll(selectStack);
        ret.removeAll(coalescedNodes);
        return ret;
    }

    private Set<MoveInst> nodeMoves(Register n) {
        Set<MoveInst> ret = new LinkedHashSet<>(activeMoves);
        ret.addAll(workListMoves);
        moveList.putIfAbsent(n, new LinkedHashSet<>());
        ret.retainAll(moveList.get(n));
        return ret;
    }

    private boolean moveRelated(Register n) {
        return !nodeMoves(n).isEmpty();
    }

    private void simplify() {
        var iterator = simplifyWorkList.iterator();
        Register n = iterator.next();
        iterator.remove();
        selectStack.push(n);
        for (Register m : adjacent(n)) {
            decrementDegree(m);
        }
    }

    private void decrementDegree(Register m) {
        Integer d = degree.getOrDefault(m, 0);
        degree.put(m, d - 1);
        if (d == K) {
            enableMoves(new LinkedHashSet<>(adjacent(m)) {{add(m);}});
            spillWorkList.remove(m);
            if (moveRelated(m)) {
                freezeWorkList.add(m);
            } else {
                simplifyWorkList.add(m);
            }
        }
    }

    private void enableMoves(Set<Register> nodes) {
        for (Register n : nodes) {
            for (MoveInst m : nodeMoves(n)) {
                if (activeMoves.contains(m)) {
                    activeMoves.remove(m);
                    workListMoves.add(m);
                }
            }
        }
    }

    private void addWorkList(Register u) {
        if (!preColored.contains(u) && !moveRelated(u) && degree.getOrDefault(u, 0) < K) {
            freezeWorkList.remove(u);
            simplifyWorkList.add(u);
        }
    }

    private boolean OK(Set<Register> ts, Register r) {
        for (Register t : ts) {
            if (!(degree.getOrDefault(t, 0) < K
                    || preColored.contains(t) || adjSet.contains(Map.entry(t, r)))) {
                return false;
            }
        }
        return true;
    }

    private boolean conservative(Set<Register> nodes) {
        int k = 0;
        for (Register n : nodes) {
            if (degree.getOrDefault(n, 0) >= K) {
                k++;
            }
        }
        return k < K;
    }

    private void coalesce() {
        var iterator = workListMoves.iterator();
        MoveInst m = iterator.next();
        iterator.remove();
        Register x = getAlias(m.getRd());
        Register y = getAlias(m.getRs());
        Register u, v;
        if (preColored.contains(y)) {
            u = y;
            v = x;
        } else {
            u = x;
            v = y;
        }
        if (u == v) {
            coalescedMoves.add(m);
            addWorkList(u);
        } else if (preColored.contains(v) || adjSet.contains(Map.entry(u, v))) {
            constrainedMoves.add(m);
            addWorkList(u);
            addWorkList(v);
        } else {
            Set<Register> tmp = new LinkedHashSet<>(adjacent(u));
            tmp.addAll(adjacent(v));
            if ((preColored.contains(u) && OK(adjacent(v), u))
                    || (!preColored.contains(u) && conservative(tmp))) {
//                System.err.println(u)
                coalescedMoves.add(m);
                combine(u, v);
                addWorkList(u);
            } else {
                activeMoves.add(m);
            }
        }
    }

    private void combine(Register u, Register v) {
        if (freezeWorkList.contains(v)) {
            freezeWorkList.remove(v);
        } else {
            spillWorkList.remove(v);
        }
        coalescedNodes.add(v);
//        System.err.println("##" + v.getIdentifier());
//        System.err.println("####" + u.getIdentifier());
        alias.put(v, u);
        moveList.get(u).addAll(moveList.get(v));
        enableMoves(Collections.singleton(v));
        for (Register t : adjacent(v)) {
            addEdge(t, u);
            decrementDegree(t);
        }
        if (degree.getOrDefault(u, 0) >= K && freezeWorkList.contains(u)) {
            freezeWorkList.remove(u);
            spillWorkList.add(u);
        }
    }

    private Register getAlias(Register n) {
        if (coalescedNodes.contains(n)) {
            var tmp = getAlias(alias.get(n));
            alias.put(n, tmp);
            return tmp;
        } else {
            return n;
        }
    }

    private void freeze() {
        var iterator = freezeWorkList.iterator();
        Register u = iterator.next();
        iterator.remove();
        simplifyWorkList.add(u);
        freezeMoves(u);
    }

    private void freezeMoves(Register u) {
        for (MoveInst m : nodeMoves(u)) {
            Register x = getAlias(m.getRd());
            Register y = getAlias(m.getRs());
            Register v;
            if (getAlias(y) == getAlias(u)) {
                v = getAlias(x);
            } else {
                v = getAlias(y);
            }
            activeMoves.remove(m);
            frozenMoves.add(m);
            if (freezeWorkList.contains(v) && nodeMoves(v).isEmpty()) {
                freezeWorkList.remove(v);
                simplifyWorkList.add(v);
            }
        }
    }

    private void selectSpill() {
        Register m = null;
        double min = Double.POSITIVE_INFINITY;
        for (Register reg : spillWorkList) {
            double p = priority.get(reg) / degree.get(reg);
            if (p < min) {
                min = p;
                m = reg;
            }
        }
//        System.err.println(min);
//        System.err.println(m.getIdentifier());
        spillWorkList.remove(m);
        simplifyWorkList.add(m);
        freezeMoves(m);
    }

    private void assignColours() {
        while(!selectStack.isEmpty()) {
            Register n = selectStack.pop();
            Set<Register> okColours = new LinkedHashSet<>();
            RV32.normal.forEach(reg -> okColours.add(RV32.get(reg)));
            for (Register w : adjList.get(n)) {
                Register alias = getAlias(w);
                if (coloredNodes.contains(alias) || preColored.contains(alias)) {
                    okColours.remove(colour.get(alias));
                }
            }
            if (okColours.isEmpty()) {
                spilledNodes.add(n);
//                System.err.println("&&&" + n.getIdentifier());
//                System.err.println("&&&" + degree.get(n));
//                System.err.println("&&&" + adjList.get(n).size());
            } else {
                coloredNodes.add(n);
                colour.put(n, okColours.iterator().next());
//                System.err.println("**" + n.getIdentifier());
//                System.err.println("****" + colour.get(n).getIdentifier());
            }
        }
        for (Register n : coalescedNodes) {
            colour.put(n, colour.get(getAlias(n)));
//            System.err.println("**" + n.getIdentifier());
//            System.err.println("**" + getAlias(n).getIdentifier());
//            System.err.println("****" + colour.get(n).getIdentifier());
        }
    }

    private void rewriteProgram() {
//        System.err.println("\n\nRewriting...\n\n");

        Set<Register> newTemps = new LinkedHashSet<>();

        for (Register reg : spilledNodes) {
            stackSize += 4;
            framePos.put(reg, new Immediate(-stackSize, false));
        }

//        for (BlockRV block : curFunction.getBlocks()) {
//            for (InstRV inst : block.getInstructions()) {
//                if (inst.getDef().size() == 1) {
//                    if () {
//
//                    }
//                }
//            }
//        }

        for (BlockRV block : curFunction.getBlocks()) {
            var iterator = block.getInstructions().listIterator();
            while (iterator.hasNext()) {
                InstRV inst = iterator.next();
                for (Register reg : inst.getUses()) {
                    if (framePos.get(reg) != null) {
                        if (!inst.getDef().contains(reg)) {
                            if (inst instanceof MoveInst && framePos.get(((MoveInst) inst).getRd()) == null) {
                                iterator.set(new LoadInst(((MoveInst) inst).getRd(), RV32.get("sp"),
                                        framePos.get(reg), ((VRegister) reg).getByteNum()));
                            } else {
                                VRegister vRegister = new VRegister(-1, ((VRegister) reg).getByteNum());
                                if (!inst.replaceUsesWith(reg, vRegister)) {
                                    continue;
                                }
                                iterator.previous();
                                iterator.add(new LoadInst(vRegister, RV32.get("sp"),
                                        framePos.get(reg), vRegister.getByteNum()));
                                iterator.next();
                                newTemps.add(vRegister);
                            }
                        } else {
                            VRegister vRegister = new VRegister(-1, ((VRegister) reg).getByteNum());
                            inst.replaceDefWith(reg, vRegister);
                            inst.replaceUsesWith(reg, vRegister);
                            iterator.previous();
                            iterator.add(new LoadInst(vRegister, RV32.get("sp"),
                                    framePos.get(reg), vRegister.getByteNum()));
                            iterator.next();
                            iterator.add(new StoreInst(RV32.get("sp"), vRegister,
                                    framePos.get(reg), vRegister.getByteNum()));
                            newTemps.add(vRegister);
                        }
                    }
                }
                for (Register reg : inst.getDef()) {
                    if (framePos.get(reg) != null && !inst.getUses().contains(reg)) {
                        if (inst instanceof MoveInst && framePos.get(((MoveInst) inst).getRs()) == null) {
                            iterator.set(new StoreInst(RV32.get("sp"), ((MoveInst) inst).getRs(),
                                    framePos.get(reg), ((VRegister) reg).getByteNum()));
                        } else {
                            VRegister vRegister = new VRegister(-1, ((VRegister) reg).getByteNum());
                            inst.replaceDefWith(reg, vRegister);
                            iterator.add(new StoreInst(RV32.get("sp"), vRegister,
                                    framePos.get(reg), vRegister.getByteNum()));
                            newTemps.add(vRegister);
                        }
                    }
                }
            }
        }

        spilledNodes.clear();
        initial.clear();
        initial.addAll(coloredNodes);
        initial.addAll(coalescedNodes);
        initial.addAll(newTemps);
        oldTemps.addAll(newTemps);
        coloredNodes.clear();
        coalescedNodes.clear();
    }

    private void removeMove() {
        for (FunctionRV function : module.getFunctions()) {
            for (BlockRV block : function.getBlocks()) {
                var iterator = block.getInstructions().listIterator();
                while (iterator.hasNext()) {
                    InstRV inst = iterator.next();
                    if (inst instanceof MoveInst || (inst instanceof ITypeInst && inst.getImmediateNumber() == 0
                            && ((ITypeInst) inst).getOp().equals("addi"))) {
                        if (inst.getRdStr().equals(inst.getRs1()))
                            iterator.remove();
                    }
                }
            }
        }
    }
}
