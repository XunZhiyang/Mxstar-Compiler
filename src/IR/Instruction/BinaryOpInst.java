package IR.Instruction;

import IR.BasicBlock;
import IR.Value;
import Utils.BinaryOp;

import java.util.Map;

public class BinaryOpInst extends Instruction {
    public BinaryOpInst(String op, Value src1, Value src2, BasicBlock curBlock) {
        super()
    }

//    public enum IRBinaryOp{
//        mul, sdiv, add, sub, srem, shl, ashr, and, or, xor, slt, sle, sgt, sge, eq, ne
     Map.of("<", "slt", "<=", "sle", ">", "sgt", ">=", "sge",
             "==", "eq", "!=", "ne");
//    }

}
