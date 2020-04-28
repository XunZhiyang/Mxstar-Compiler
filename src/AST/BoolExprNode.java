package AST;

import Utils.Position;

public class BoolExprNode extends ConstExpr {
    private boolean value;

    public BoolExprNode(boolean value, Position position) {
        super(position);
        this.value = value;
    }

    public boolean getBoolValue() {
        return value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
