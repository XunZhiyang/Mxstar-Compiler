package AST;

import Utils.Position;

import java.util.List;

public class IdentifierListNode extends ASTNode {
    private List<String> identifiers;

    public IdentifierListNode(List<String> identifiers, Position position) {
        super(position);
        this.identifiers = identifiers;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
