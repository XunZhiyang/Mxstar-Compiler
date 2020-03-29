package AST;

import Utils.Position;

public class ThisNode extends PrimaryExpr {
    public ThisNode(Position position) {
        super(position);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
