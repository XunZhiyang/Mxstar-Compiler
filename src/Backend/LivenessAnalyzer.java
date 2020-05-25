package Backend;

import OperandRV.BlockRV;
import OperandRV.FunctionRV;
import OperandRV.InstRV.InstRV;
import OperandRV.ModuleRV;
import OperandRV.Register;

import java.util.*;

public class LivenessAnalyzer {
    private Map<BlockRV, Set<Register>> use;
    private Map<BlockRV, Set<Register>> def;

    public LivenessAnalyzer(ModuleRV module) {
        module.getFunctions().forEach(this::analyzeFunction);
    }

    public LivenessAnalyzer(FunctionRV function) {
        analyzeFunction(function);
    }

    public void analyzeFunction(FunctionRV function) {
        function.getBlocks().forEach(this::analyzeBlock);
        iterateInOut(function.exitBlock());
    }

    private void analyzeBlock(BlockRV block) {
        Set<Register> blockUse = new HashSet<>();
        Set<Register> blockDef = new HashSet<>();
        for (InstRV inst : block.getInstructions()) {
            List<Register> instUses = inst.getUses();
            instUses.removeAll(blockDef);
            blockUse.addAll(instUses);
            blockDef.add(inst.getDef());
        }
        blockDef.remove(null);
        use.put(block, blockUse);
        def.put(block, blockDef);
    }

    Set<BlockRV> visit = new HashSet<>();

    private void iterateInOut(BlockRV block) {
        if (visit.contains(block)) return;
        visit.add(block);

        Set<Register> nLiveOut = new HashSet<>();
        block.getSuccessors().forEach(b -> nLiveOut.addAll(b.getLiveIn()));
        Set<Register> nLiveIn = new HashSet<>(nLiveOut);
        nLiveIn.removeAll(def.get(block));
        nLiveIn.addAll(use.get(block));
        if (!block.getLiveIn().equals(nLiveIn) || !block.getLiveOut().equals(nLiveOut)) {
            block.getLiveIn().addAll(nLiveIn);
            block.getLiveIn().addAll(nLiveOut);
            visit.removeAll(block.getPredecessors());
        }
        block.getPredecessors().forEach(this::iterateInOut);
    }

}
