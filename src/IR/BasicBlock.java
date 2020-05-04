package IR;

import IR.Instruction.Instruction;
import Symbol.GlobalScope;

import java.util.ArrayList;
import java.util.List;

public class BasicBlock extends User {
    private String label;
    private List<Instruction> instructionList = new ArrayList<>();
    private boolean terminated = false;

    public BasicBlock(String label) {
        super(label, GlobalScope.getVoidType());
    }

    public void addInst(Instruction instruction) {
        if(!terminated) {
            instructionList.add(instruction);
            this.terminated = instruction.isTerminator();
        }
    }

    public boolean isTerminated() {
        return terminated;
    }

    public String getLabel() {
        return label;
    }
}
