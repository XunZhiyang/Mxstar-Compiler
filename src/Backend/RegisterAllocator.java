package Backend;

import OperandRV.*;
import OperandRV.InstRV.*;

import java.util.*;

import static java.lang.Integer.min;

public class RegisterAllocator {
    static final int K = RV32.normal.size();
    ModuleRV module;
    FunctionRV curFunction;

    Set<Register> preColored = new HashSet<>();
    Set<Register> initial = new HashSet<>();
    Set<Register> simplifyWorkList = new HashSet<>();
    Set<Register> freezeWorkList = new HashSet<>();
    Set<Register> spillWorkList = new HashSet<>();
    Set<Register> spilledNodes = new HashSet<>();
    Set<Register> coalescedNodes = new HashSet<>();
    Set<Register> coloredNodes = new HashSet<>();
    Stack<Register> selectStack = new Stack<>();

    Set<MoveInst> coalescedMoves = new HashSet<>();
    Set<MoveInst> constrainedMoves = new HashSet<>();
    Set<MoveInst> frozenMoves = new HashSet<>();
    Set<MoveInst> workListMoves = new HashSet<>();
    Set<MoveInst> activeMoves = new HashSet<>();

    Set<Map.Entry<Register, Register>> adjSet = new HashSet<>();
    Map<Register, Set<Register>> adjList = new HashMap<>();
    Map<Register, Integer> degree = new HashMap<>();
    Map<Register, Set<MoveInst>> moveList = new HashMap<>();
    Map<Register, Register> alias = new HashMap<>();
    Map<Register, Register> colour = new HashMap<>();

    Map<Register, Double> priority = new HashMap<>();
    int stackOffset;

    public RegisterAllocator(ModuleRV module) {
        this.module = module;
        preColored.addAll(module.getGlobalVariables());
        for (FunctionRV function : module.getFunctions()) {
            run(function);
        }
    }

    private void run(FunctionRV function) {
        stackOffset = 0;
        curFunction = function;
        for (BlockRV block : curFunction.getBlocks()) {
            for (InstRV inst : block.getInstructions()) {
                if (inst.getDef() != null) {
                    initial.add(inst.getDef());
                }
                initial.addAll(inst.getUses());
            }
        }
        initial.removeAll(preColored);

        for (;;) {
            init();
            new LivenessAnalyzer(function);
            build();
            makeWorkList();

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
    }

    private void init() {
        initial.forEach(reg -> priority.put(reg, 0));

        for (BlockRV block : curFunction.getBlocks()) {
            Double weight = Math.pow(10, min(block.getPredecessors().size(), block.getSuccessors().size());
            for (InstRV inst : block.getInstructions()) {
                Register def = inst.getDef();
                if (def != null) {
                    priority.put(def, priority.get(def) + weight);
                }
                inst.getUses().forEach(reg -> priority.put(reg, priority.get(reg) + weight));
            }
        }
    }

    private void build() {
        for (BlockRV block : curFunction.getBlocks()) {
            Set<Register> live = new HashSet<>(block.getLiveOut());
            List<InstRV> instructions = block.getInstructions();
            for (int i = instructions.size() - 1; i >= 0; --i) {
                InstRV inst = instructions.get(i);
                if (inst instanceof MoveInst) {
                    live.removeAll(inst.getUses());
                    for (Register r : inst.getUses()) {
                        moveList.putIfAbsent(r, new HashSet<>());
                        moveList.get(r).add((MoveInst) inst);
                    }
                    Register def = inst.getDef();
                    if (def != null) {
                        moveList.putIfAbsent(def, new HashSet<>());
                        moveList.get(def).add((MoveInst) inst);
                    }
                    workListMoves.add((MoveInst) inst);
                }
                Register d = inst.getDef();
                if (d != null) {
                    live.add(inst.getDef());
                    for (Register l : live) {
                        addEdge(l, d);
                    }
                }
                live.remove(inst.getDef());
                live.addAll(inst.getUses());
            }
        }
    }

    private void addEdge(Register u, Register v) {
        if (u != v && !adjSet.contains(Map.entry(u, v))) {
            if (!preColored.contains(u)) {
                adjList.putIfAbsent(u, new HashSet<>());
                adjList.get(u).add(v);
                degree.put(u, degree.getOrDefault(u, 0) + 1);
            }
            if (!preColored.contains(v)) {
                adjList.putIfAbsent(v, new HashSet<>());
                adjList.get(v).add(u);
                degree.put(v, degree.getOrDefault(v, 0) + 1);
            }
        }
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }



    private void makeWorkList() {
        var iterator = initial.iterator();
        while (iterator.hasNext()) {
            Register n = iterator.next();
            iterator.remove();
            if (degree.get(n) >= K) {
                spillWorkList.add(n);
            } else if (moveRelated(n)) {
                freezeWorkList.add(n);
            } else {
                simplifyWorkList.add(n);
            }
        }
    }

    private Set<Register> adjacent(Register n) {
        adjList.putIfAbsent(n, new HashSet<>());
        Set<Register> ret = adjList.get(n);
        ret.removeAll(selectStack);
        ret.removeAll(coalescedNodes);
    }

    private Set<MoveInst> nodeMoves(Register n) {
        Set<MoveInst> ret = new HashSet<>(activeMoves);
        activeMoves.addAll(workListMoves);
        moveList.putIfAbsent(n, new HashSet<>());
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
        Integer d = degree.get(m);
        degree.put(m, d - 1);
        if (d == K) {
            enableMoves(new HashSet<Register>(adjacent(m)) {{add(m);}});

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
        Register u = getAlias(m.getRd());
        Register v = getAlias(m.getRs());
        if (preColored.contains(v)) {
            var tmp = u;
            u = v;
            v = tmp;
        }
        if (u == v) {
            coalescedMoves.add(m);
            addWorkList(u);
        } else if (preColored.contains(v) || adjSet.contains(Map.entry(u, v))) {
            constrainedMoves.add(m);
            addWorkList(u);
            addWorkList(v);
        } else if ((preColored.contains(u) && OK(adjacent(v), u))
                || (!preColored.contains(u)
                        && conservative(new HashSet<Register>(adjacent(u)){{addAll(adjacent(v));}} ))) {
            coalescedMoves.add(m);
            combine(u, v);
            addWorkList(u);
        }
    }

    private void combine(Register u, Register v) {
        if (freezeWorkList.contains(v)) {
            freezeWorkList.remove(v);
        } else {
            spillWorkList.remove(v);
        }
        coalescedNodes.add(v);
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
            if (getAlias(x) == getAlias(y)) {
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

    }

    private void assignColours() {
        while(!selectStack.isEmpty()) {
            Register n = selectStack.pop();
            Set<Register> okColours = new HashSet<>();
            RV32.normal.forEach(reg -> okColours.add(RV32.get(reg)));
            for (Register w : adjList.get(n)) {
                Register alias = getAlias(w);
                if (coloredNodes.contains(alias) || preColored.contains(alias)) {
                    okColours.remove(colour.get(alias));
                }
            }
            if (okColours.isEmpty()) {
                spilledNodes.add(n);
            } else {
                coloredNodes.add(n);
                colour.put(n, okColours.iterator().next());
            }
        }
        for (Register n : coalescedNodes) {
            colour.put(n, colour.get(getAlias(n)));
        }
    }

    private void rewriteProgram() {
        Set<Register> newTemps = new HashSet<>();

        for (Register reg : spilledNodes) {

        }

//        for (BlockRV block : curFunction.getBlocks()) {
//            for (InstRV inst : block.getInstructions()) {
//                if (inst.getDef() != null) {
//                    Register dest = inst.getDef();
//                }
//            }
//        }

        for (BlockRV block : curFunction.getBlocks()) {
            for (InstRV inst : block.getInstructions()) {
                for (Register reg : inst.getUses()) {

                }
            }
        }


        spilledNodes.clear();
        initial.clear();
        initial.addAll(coloredNodes);
        initial.addAll(coalescedNodes);
        initial.addAll(newTemps);
        coloredNodes.clear();
        coalescedNodes.clear();
    }


}
