package Backend;

import IR.BasicBlock;
import IR.Constant.*;
import IR.IRVisitor;
import IR.Instruction.CmpInst;
import IR.Module;
import IR.Value;
import OperandRV.*;
import OperandRV.InstRV.*;
import OperandRV.InstRV.LoadInst;
import OperandRV.InstRV.MoveInst;
import OperandRV.InstRV.ReturnInst;
import Symbol.ClassType;
import Symbol.PointerType;
import Symbol.Type;

import java.util.*;

import static java.lang.Integer.min;

public class InstSelector implements IRVisitor {
    private Module module;
    private ModuleRV moduleRV;
    private FunctionRV curFunction;
    private BlockRV curBlock;
    private int regCnt;
    private static final Set<String> commutative = new HashSet<>(Arrays.asList("mul", "add", "and", "or", "xor"));
    private static final Set<String> hasI = new HashSet<>(Arrays.asList("add", "sub", "shl", "ashr", "and", "or", "xor"));

    public ModuleRV getModule() {
        return moduleRV;
    }

    private static boolean isZero(Value value) {
        return value instanceof NullConst
                || (value instanceof IntConst && ((IntConst) value).getValue() == 0)
                || (value instanceof BoolConst && !((BoolConst) value).getValue());
    }

    private static boolean inImmRange(int value) {
        return value >= -(1 << 11) && value <= (1 << 11) - 1;
    }

    private static boolean isPowerOfTwo(int number) {
        return number > 0 && ((number & (number - 1)) == 0);
    }

    private Immediate immFor(Value value, boolean positive) {
        if (isZero(value)) {
            return new Immediate(0);
        } else if (value instanceof IntConst) {
            int literal = positive ? ((IntConst) value).getValue() : -((IntConst) value).getValue();
            if (inImmRange(literal)) {
                return new Immediate(literal);
            } else {
                return null;
            }
        } else if (value instanceof BoolConst) {
            assert positive;
            return new Immediate(((BoolConst) value).getValue() ? 1 : 0);
        } else {
            return null;
        }
    }

    private Register regFor(Value value) {
        if (value.getCorRV() != null) {
            return (Register) value.getCorRV();
        }

        if (isZero(value)) {
            return RV32.get("zero");
        }

        if (value instanceof BoolConst) {
            VRegister ret = new VRegister(regCnt++, 1);
            new LoadImmInst(ret, 1, curBlock);
            return ret;
        } else if (value instanceof IntConst) {
            VRegister ret = new VRegister(regCnt++);
            new LoadImmInst(ret, ((IntConst) value).getValue(), curBlock);    //may be problematic
        } else if (value instanceof GlobalVariable) {
            int byteNum = ((PointerType) value.getType()).getMember().getByteNum(); //may be problematic
            VRegister ret = (VRegister) value.setCorRV(new VRegister(value.getIdentifier(), byteNum));
            if (value instanceof StringConst) {
                moduleRV.addStringConst(ret, ((StringConst) value).getString());
            } else {
                moduleRV.addGlobalVariable(ret);
            }
            return ret;
        }

        return (VRegister) value.setCorRV(new VRegister(regCnt++, value.getType().getByteNum()));
    }

    private Integer intFor(Value value) {
        if (isZero(value)) {
            return 0;
        } else if (value instanceof IntConst) {
            return ((IntConst) value).getValue();
        } else {
            return null;
        }
    }

    private boolean onlyForBranch(IR.Instruction.CmpInst inst) {
        return inst.getUses().size() == 1 && (inst.getUses().get(0) instanceof IR.Instruction.BranchInst);
    }

    private void genBinaryOpInst(String op, Register rd, Value rs1, Value rs2) {
        if (isZero(rs1)) {
            switch (op) {
                case "add":
                case "sub":
                case "or":
                case "xor":
                    new RTypeInst(op, rd, RV32.get("zero"), regFor(rs2), curBlock);
                    break;
                default:
                    new MoveInst(rd, RV32.get("zero"), curBlock);
            }
        } else if (isZero(rs2)) {
            switch (op) {
                case "mul":
                case "and":
                    new MoveInst(rd, RV32.get("zero"), curBlock);
                    break;
                default:
                    new MoveInst(rd, regFor(rs1), curBlock);
            }
        } else if (hasI.contains(op)) {
            if (op.equals("sub")) {
                Immediate immediate = immFor(rs2, false);
                if (immediate != null) {
                    new ITypeInst("sub", rd, regFor(rs1), immediate, curBlock);
                    return;
                }
            } else {
                Immediate immediate = immFor(rs2, true);
                if (immediate != null) {
                    new ITypeInst(op, rd, regFor(rs1), immediate, curBlock);
                    return;
                }
            }
            if (commutative.contains(op)) {
                Immediate immediate = immFor(rs1, true);
                if (immediate != null) {
                    new ITypeInst(op, rd, regFor(rs2), immediate, curBlock);
                    return;
                }
            }
        } else {
            if (op.equals("mul")) {
                Integer literal = intFor(rs1);
                if (literal != null && isPowerOfTwo(literal)) {
                    new ITypeInst("shl", rd, regFor(rs2),
                            new Immediate(Integer.numberOfTrailingZeros(literal)), curBlock);
                    return;
                }
                literal = intFor(rs2);
                if (literal != null && isPowerOfTwo(literal)) {
                    new ITypeInst("shl", rd, regFor(rs1),
                            new Immediate(Integer.numberOfTrailingZeros(literal)), curBlock);
                    return;
                }
            }
        }

        new RTypeInst(op, rd, regFor(rs1), regFor(rs2), curBlock);
    }

    @Override
    public void visit(Module module) {
        this.module = module;
        moduleRV = new ModuleRV();
        for (Function function : module.getFunctionList()) {
            FunctionRV functionRV = new FunctionRV(function);
            moduleRV.getFunctions().add(functionRV);

            for (BasicBlock block : function.getBasicBlockList()) {
                functionRV.addBlock(new BlockRV(block));
            }

            for (BasicBlock block : function.getBasicBlockList()) {
                BlockRV blockRV = (BlockRV) block.getCorRV();
                function.predecessorsOf(block).forEach(b -> blockRV.addPredecessor((BlockRV) b.getCorRV()));
                block.getSuccessors().forEach(b -> blockRV.addSuccessor((BlockRV) b.getCorRV()));
            }

            function.getOperands().forEach(v -> functionRV.getParams().add(regFor(v)));
            function.accept(this);
        }
    }

    @Override
    public void visit(Function function) {
        regCnt = 0;
        FunctionRV functionRV = (FunctionRV) function.getCorRV();
        curFunction = functionRV;
        new ITypeInst("add", RV32.get("sp"), RV32.get("sp"),
                new Immediate(0, true), functionRV.entryBlock());

        List<Register> calleeSave = new ArrayList<>();
        for (String str : RV32.calleeSave) {
            VRegister reg = new VRegister(regCnt++);
            calleeSave.add(reg);
            new MoveInst(reg, RV32.get(str), functionRV.entryBlock());
        }

        VRegister ra = new VRegister(regCnt++);
        new MoveInst(ra, RV32.get("ra"), functionRV.entryBlock());

        List<Value> parameters = function.getOperands();
        for (int i = 0; i < min(RV32.argNum, parameters.size()); i++) {
            new MoveInst(functionRV.getParams().get(i), RV32.get("a" + i), functionRV.entryBlock());
        }
        for (int i = RV32.argNum, offset = 0; i < parameters.size(); ++i) {
            int byteNum = parameters.get(i).getType().getByteNum();
            new LoadInst(functionRV.getParams().get(i), RV32.get("sp"), new Immediate(offset, false),
                    byteNum , functionRV.entryBlock());
            offset += 4;
        }

        function.getBasicBlockList().forEach(block -> block.accept(this));

        for (int i = 0; i < calleeSave.size(); i++) {
            new MoveInst(RV32.get(RV32.calleeSave.get(i)), calleeSave.get(i), functionRV.entryBlock());
        }
        new MoveInst(RV32.get("ra"), ra, functionRV.exitBlock());
        new ITypeInst("add", RV32.get("sp"), RV32.get("sp"),
                new Immediate(0, false), functionRV.exitBlock());
        new ReturnInst(functionRV.exitBlock());
        functionRV.setRegNum(regCnt);
    }

    @Override
    public void visit(BasicBlock block) {
        curBlock = (BlockRV) block.getCorRV();
        block.getInstructionList().forEach(inst -> inst.accept(this));
    }

    @Override
    public void visit(IR.Instruction.BinaryOpInst node) {
        genBinaryOpInst(node.getOp(), regFor(node), node.getOperand(0), node.getOperand(1));
    }

    @Override
    public void visit(IR.Instruction.BitCastInst node) {
        node.setCorRV(regFor(node.getOperand(0)));
    }

    @Override
    public void visit(IR.Instruction.BranchInst node) {
        Value cond = node.getOperand(0);
        BlockRV taken = (BlockRV) node.getOperand(1).getCorRV();
        BlockRV notTaken = (BlockRV) node.getOperand(2).getCorRV();
        if (cond instanceof IR.Instruction.CmpInst && onlyForBranch((IR.Instruction.CmpInst) cond)) {
            String op = ((CmpInst) cond).getOp();
            Value cmp1 = node.getOperand(1);
            Value cmp2 = node.getOperand(2);
            String opRV;
            Value tmp;
            switch (op) {
                case "eq":
                case "ne":
                    opRV = op;
                    break;
                case "slt":
                case "sge":
                    opRV = op.substring(1);
                    break;
                case "sgt":
                    opRV = "lt";
                    tmp = cmp1;
                    cmp1 = cmp2;
                    cmp2 = tmp;
                    break;
                case "sle":
                    opRV = "ge";
                    tmp = cmp1;
                    cmp1 = cmp2;
                    cmp2 = tmp;
                    break;
                default:
                    throw new RuntimeException();
            }
            new BranchInst(regFor(cmp1), regFor(cmp2), "b" + opRV, taken, curBlock);
        } else {
            new BranchInst(regFor(cond), RV32.get("zero"), "bne", taken, curBlock);
        }
        new JumpInst(notTaken, curBlock);
    }

    @Override
    public void visit(IR.Instruction.CallInst node) {
        List<Value> parameters = node.getOperands();
        for (int i = 0; i < min(RV32.argNum, parameters.size()); i++) {
            Register reg = regFor(parameters.get(i));
            if (reg.isGlobal()) {
                new LaInst(RV32.get("a" + i), reg, curBlock);
            }
            new MoveInst(RV32.get("a" + i), reg, curBlock);
        }
        int offset = 0;
        for (int i = RV32.argNum; i < parameters.size(); ++i) {
            int byteNum = parameters.get(i).getType().getByteNum();
            new StoreInst(RV32.get("sp"), regFor(parameters.get(i)), new Immediate(offset), byteNum, curBlock);
            offset += 4;
        }
        curFunction.updateParamBytes(offset);
        new CallInst((FunctionRV) module.getFunction(node.getFunctionIdentifier()).getCorRV(), curBlock);
        if (!node.getType().isVoid()) {
            new MoveInst(regFor(node), RV32.get("a0"), curBlock);
        }
    }

    private void slt(Register rd, Value cmp1, Value cmp2) {
        Immediate immediate = immFor(cmp2, true);
        if (immediate != null) {
            new ITypeInst("slt", rd, regFor(cmp1), immediate, curBlock);
        } else {
            new RTypeInst("slt", rd, regFor(cmp1), regFor(cmp2), curBlock);
        }
    }

    @Override
    public void visit(IR.Instruction.CmpInst node) {
        if (onlyForBranch(node)) return;
        Value cmp1 = node.getOperand(0);
        Value cmp2 = node.getOperand(1);
        Register rd = regFor(node);
        VRegister tmp;
        switch (node.getOp()) {
            case "slt":
                slt(rd, cmp1, cmp2);
                break;
            case "sgt":
                slt(rd, cmp2, cmp1);
                break;
            case "sle":
                tmp = new VRegister(regCnt++);
                slt(tmp, cmp2, cmp1);
                new ITypeInst("xor", rd, tmp, new Immediate(1), curBlock);
                break;
            case "sge":
                tmp = new VRegister(regCnt++);
                slt(tmp, cmp1, cmp2);
                new ITypeInst("xor", rd, tmp, new Immediate(1), curBlock);
                break;
            case "eq":
                if (isZero(cmp1)) {
                    new CmpZeroInst("seqz", rd, regFor(cmp2), curBlock);
                } else if (isZero(cmp2)) {
                    new CmpZeroInst("seqz", rd, regFor(cmp1), curBlock);
                } else {
                    tmp = new VRegister(regCnt++);
                    genBinaryOpInst("xor", tmp, cmp1, cmp2);
                    new CmpZeroInst("seqz", rd, tmp, curBlock);
                }
                break;
            case "ne":
                if (isZero(cmp1)) {
                    new CmpZeroInst("snez", rd, regFor(cmp2), curBlock);
                } else if (isZero(cmp2)) {
                    new CmpZeroInst("snez", rd, regFor(cmp1), curBlock);
                } else {
                    tmp = new VRegister(regCnt++);
                    genBinaryOpInst("xor", tmp, cmp1, cmp2);
                    new CmpZeroInst("snez", rd, tmp, curBlock);
                }
                break;
            default:
                throw new RuntimeException();
        }

    }

    @Override
    public void visit(IR.Instruction.GEPInst node) {
        Register dest;
        Value pointer = node.getOperand(0);
        Value offset = node.getOperand(node.getOperands().size() - 1);
        Type pointTo = ((PointerType) pointer.getType()).getMember();
        int step = pointTo.isClass() ? 4 : pointTo.getByteNum();
        if (offset instanceof IntConst) {
            int index = ((IntConst) offset).getValue();
            if (index != 0) {
                dest = new VRegister(regCnt++);
                genBinaryOpInst("add", dest, pointer, new IntConst(step * index));
            } else {
                Register ptr = regFor(pointer);
                if (ptr.isGlobal()) {
                    dest = new VRegister(regCnt++);
                    new LaInst(dest, ptr, curBlock);
                } else {
                    dest = ptr;
                }
            }
        } else {
            Register offsetByte = new VRegister(regCnt++);
            genBinaryOpInst("mul", offsetByte, offset, new IntConst(step));
            dest = new VRegister(regCnt++);
            new RTypeInst("add", dest, regFor(pointer), offsetByte, curBlock);
        }
        new MoveInst(regFor(node), dest, curBlock);
    }

    @Override
    public void visit(IR.Instruction.JumpInst node) {
        new JumpInst((BlockRV) node.getOperand(0).getCorRV(), curBlock);
    }

    @Override
    public void visit(IR.Instruction.LoadInst node) {
        new LoadInst(regFor(node), regFor(node.getOperand(0)),
                new Immediate(0), node.getType().getByteNum(), curBlock);
    }

    @Override
    public void visit(IR.Instruction.ReturnInst node) {
        Value retValue = node.getOperand(0);
        if (isZero(retValue)) {
            new MoveInst(RV32.get("a0"), RV32.get("zero"), curBlock);
        } else if (retValue instanceof IntConst) {
            new LoadImmInst(RV32.get("a0"), ((IntConst) retValue).getValue(), curBlock);
        } else if (retValue instanceof BoolConst) {
            new LoadImmInst(RV32.get("a0"), ((BoolConst) retValue).getValue() ? 1 : 0, curBlock);
        } else {
            new MoveInst(RV32.get("a0"), regFor(retValue), curBlock);
        }
    }

    @Override
    public void visit(IR.Instruction.SextInst node) {
        new MoveInst(regFor(node), regFor(node.getOperand(0)), curBlock);
    }

    @Override
    public void visit(IR.Instruction.StoreInst node) {
        Value value = node.getOperand(0);
        Register dest = regFor(node.getOperand(1));
        if (dest.isGlobal()) {
            VRegister lui = new VRegister(regCnt++);
            new LuiInst(lui, new HiLo(dest, true), curBlock);
            new StoreInst(lui, regFor(value), new HiLo(dest, false), value.getType().getByteNum(), curBlock);
        } else {
            new StoreInst(dest, regFor(value), new Immediate(0), value.getType().getByteNum(), curBlock);
        }
    }

    @Override
    public void visit(IR.Instruction.MoveInst node) {
        Value rs = node.getOperand(0);
        Register rd = regFor(node.getOperand(1));
        if (isZero(rs)) {
            new MoveInst(rd, RV32.get("zero"), curBlock);
        } else if (rs instanceof IntConst) {
            new LoadImmInst(rd, ((IntConst) rs).getValue(), curBlock);
        } else if (rs instanceof BoolConst) {
            new LoadImmInst(rd, ((BoolConst) rs).getValue() ? 1 : 0, curBlock);
        } else {
            new MoveInst(rd, regFor(rs), curBlock);
        }
    }

    @Override
    public void visit(IR.Instruction.AllocaInst node) {
        throw new RuntimeException();
    }

    @Override
    public void visit(IR.Instruction.PhiInst node) {
        throw new RuntimeException();
    }

    @Override
    public void visit(ClassType node){}

    @Override
    public void visit(GlobalVariable node){}

    @Override
    public void visit(Value value){
        throw new RuntimeException();
    }




}
