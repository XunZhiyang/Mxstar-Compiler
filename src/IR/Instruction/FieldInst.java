package IR.Instruction;

import IR.BasicBlock;
import IR.Value;
import Symbol.ClassType;
import Symbol.Type;

public class FieldInst extends Instruction {
    Value classObject;
    int index;
    public FieldInst(Value classObject, Type fieldType, int index, BasicBlock curBlock) {
        super(".GEP", fieldType, curBlock);
        this.classObject = classObject;
        this.index = index;
    }
}
