package IR;

import IR.Instruction.Instruction;

import java.util.ArrayList;
import java.util.List;

public class BasicBlock {
    private String label;
    private List<Instruction> instructionList = new ArrayList<>();

    public BasicBlock(String label) {
        this.label = label;
    }

    public void addInst(Instruction instruction) {
        instructionList.add(instruction);
    }

    public String getLabel() {
        return label;
    }
}
