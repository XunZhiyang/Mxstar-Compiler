package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Value;
import Symbol.GlobalScope;
import Symbol.Type;


public class BinaryOpInst extends Instruction {
    private static Type calcType(String op, Value v) {
        switch(op) {
            case "mul":
            case "sdiv":
            case "add":
            case "sub":
            case "srem":
            case "shl":
            case "ashr":
                return GlobalScope.getIntType();
            case "and":
            case "or":
            case "xor":
                return v.getType();
            case "slt":
            case "sle":
            case "sgt":
            case"sge":
            case "eq":
            case "ne":
                return GlobalScope.getBoolType();
            default:
                throw new RuntimeException();
        }
    }

    public BinaryOpInst(String op, Value src1, Value src2, BasicBlock curBlock) {
        super(op, calcType(op, src1), curBlock);
        addOperand(src1, src2);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
//    public enum IRBinaryOp{
//        mul, sdiv, add, sub, srem, shl, ashr, and, or, xor, slt, sle, sgt, sge, eq, ne

}
