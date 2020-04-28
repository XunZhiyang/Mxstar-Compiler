package IR;

import IR.Instruction.Instruction;
import Symbol.GlobalScope;

import java.util.ArrayList;
import java.util.List;

public class BasicBlock extends User {
    private String label;
    private List<Instruction> instructionList = new ArrayList<>();

    public BasicBlock(String label) {
        super(label, GlobalScope.voidType);
    }

    public void addInst(Instruction instruction) {
        instructionList.add(instruction);
    }

    public String getLabel() {
        return label;
    }
}
