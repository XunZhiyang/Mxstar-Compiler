package AST;

import Utils.Position;

public class BreakNode extends Stmt {
    public BreakNode(Position position) {
        super(position);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
