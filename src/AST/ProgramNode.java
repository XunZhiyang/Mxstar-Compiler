package AST;

import Utils.Position;

import java.util.List;

public class ProgramNode extends ASTNode {
    private List<ProgramFragment> programFragments;

    public ProgramNode(Position position) {
        super(position);
    }
}
