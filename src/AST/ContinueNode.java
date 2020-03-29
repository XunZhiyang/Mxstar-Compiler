package AST;

import Utils.Position;

public class ContinueNode extends Stmt {
    public ContinueNode(Position position) {
        super(position);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
