package AST;

import Utils.Position;

public class NullNode extends ConstExpr {
    public NullNode(Position position) {
        super(position);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
