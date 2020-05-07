package IR.Instruction;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.Value;
import Symbol.GlobalScope;
import Symbol.Type;


public class BinaryOpInst extends Instruction {
    private String op;
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
            default:
                throw new RuntimeException();
        }
    }

    public BinaryOpInst(String op, Value src1, Value src2, BasicBlock curBlock) {
        super(op, calcType(op, src1), curBlock);
        this.op = op;
        addOperand(src1, src2);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    public String getOp() {
        return op;
    }

    //    public enum IRBinaryOp{
//        mul, sdiv, add, sub, srem, shl, ashr, and, or, xor, slt, sle, sgt, sge, eq, ne

}
