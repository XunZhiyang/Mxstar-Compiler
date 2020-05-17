package Optimizer;

import IR.BasicBlock;
import IR.Constant.Function;
import IR.Instruction.BranchInst;
import IR.Instruction.Instruction;
import IR.Instruction.JumpInst;

import java.util.*;

class CFGNode {
    BasicBlock block;
    List<CFGNode> in = new ArrayList<>();
    List<CFGNode> out = new ArrayList<>();
    boolean visit = false;

    CFGNode(BasicBlock block) {
        this.block = block;
    }
}


public class CFG {
    private Map<BasicBlock, CFGNode> nodes = new HashMap<>();
    List<BasicBlock> RPO = new ArrayList<>();

    void addEdge(CFGNode from, CFGNode to) {
        from.out.add(to);
        to.in.add(from);
    }

    CFGNode dfs(BasicBlock block) {
        CFGNode now = new CFGNode(block);
        nodes.put(block, now);
        for (BasicBlock successor : block.getSuccessors()) {
            CFGNode to = nodes.get(successor);
            if (to != null) {
                 addEdge(now, to);
            } else {
                addEdge(now, dfs(successor));
            }
        }
        return now;
    }

    void buildRPO(CFGNode node) {
        node.visit = true;
        for (CFGNode successor : node.out) {
            if (!successor.visit) {
                buildRPO(successor);
            }
        }
        RPO.add(node.block);
    }

    void removeUnreachable(Function function) {
        List<BasicBlock> toDelete = new ArrayList<>();
        for (BasicBlock block : function.getBasicBlockList()) {
            if (nodes.get(block) == null) {
                toDelete.add(block);
            }
        }
        toDelete.forEach(block -> function.getBasicBlockList().remove(block));
    }

    public CFG(Function function, BasicBlock root, boolean reverse) {
        dfs(function.getBasicBlockList().get(0));
//        removeUnreachable(function);
        if (reverse) {
            for (CFGNode node : nodes.values()) {
                var tmp = node.out;
                node.out = node.in;
                node.in = tmp;
            }
        }
        buildRPO(nodes.get(root));
        Collections.reverse(RPO);
    }

    public List<BasicBlock> getRPO() {
        return RPO;
    }

    public List<BasicBlock> getPredecessors(BasicBlock block) {
        List<BasicBlock> predecessors = new ArrayList<>();
        nodes.get(block).in.forEach(x -> predecessors.add(x.block));
        return predecessors;
    }
}
