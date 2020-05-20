package Optimizer;

import IR.BasicBlock;
import IR.Constant.Function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomTree {
    static class Node {
        BasicBlock block;
        Node IDom;
        int order;
        List<Node> children = new ArrayList<>();
        List<BasicBlock> DF = new ArrayList<>();

        Node(BasicBlock block, int order) {
            this.block = block;
            this.order = order;
        }
    }

    private CFG cfg;
    private Node root;
    private Map<BasicBlock, Node> nodes = new HashMap<>();


    public DomTree(Function function, BasicBlock rootBlock, boolean reverse) {
        cfg = new CFG(function, rootBlock, reverse);

        for (int i = 0; i < cfg.getRPO().size(); ++i) {
            BasicBlock block = cfg.getRPO().get(i);
            nodes.put(block, new Node(block, i));
        }
        root = nodes.get(rootBlock);

        buildTree();
        calcDF();
        //Print IDom
//        for (BasicBlock block : cfg.getRPO()) {
//            if (nodes.get(block).IDom != null)
//            System.out.println(block.getIdentifier() + " IDom: " + nodes.get(block).IDom.block.getIdentifier());
//            System.out.println(block.getIdentifier());
//            nodes.get(block).DF.forEach(x -> System.out.println("     " + x.getIdentifier()));
//        }
    }

    private Node calcLCA(Node u, Node v) {
        while (u != v) {
            if (u.order > v.order)
                u = u.IDom;
            else
                v = v.IDom;
        }
        return u;
    }

    private void buildTree() {
        root.IDom = root;

        boolean changed = true;
        while (changed) {
            changed = false;
            for (BasicBlock block : cfg.getRPO()) {
                Node now = nodes.get(block);
                if (now == root) continue;
                Node LCA = null;
                for (BasicBlock predecessor : cfg.getPredecessors(block)) {
                    Node preNode = nodes.get(predecessor);
                    if (preNode.IDom == null) continue;
                    if (LCA == null) {
                        LCA = preNode;
                    } else {
                        LCA = calcLCA(LCA, preNode);
                    }
                }
                if (LCA != now.IDom) {
                    now.IDom = LCA;
                    changed = true;
                }
            }
        }


        for (Node node : nodes.values()) {
            if (node != root) {
                node.IDom.children.add(node);
            }
        }
    }

    private void calcDF() {
        for (BasicBlock block : cfg.getRPO()) {
            List<BasicBlock> predecessors = cfg.getPredecessors(block);
            if (predecessors.size() > 1) {
                for (BasicBlock predecessor : predecessors) {
                    Node now = nodes.get(predecessor);
                    while (now != nodes.get(block).IDom) {
                        now.DF.add(block);
                        now = now.IDom;
                    }
                }
            }
        }
    }

    public List<BasicBlock> getDF(BasicBlock block) {
        return nodes.get(block).DF;
    }

    public Node getRoot() {
        return root;
    }
}
