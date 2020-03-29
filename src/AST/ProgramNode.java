package AST;

import Utils.Position;

import java.util.List;

public class ProgramNode extends ASTNode {
    private List<ProgramFragment> programFragments;

    public ProgramNode(List<ProgramFragment> programFragments, Position position) {
        super(position);
        this.programFragments = programFragments;
    }

    public List<ProgramFragment> getList() {
        return programFragments;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
