package IR;

import IR.Instruction.Instruction;

import java.util.ArrayList;
import java.util.List;

public class BasicBlock {
    private List<Instruction> instructionList = new ArrayList<>();

    void addInst(Instruction instruction) {
        instructionList.add(instruction);
    }
}
