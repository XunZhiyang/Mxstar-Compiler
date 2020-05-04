package AST;

import Utils.Position;

public class IntLiteralNode extends ConstExpr {
    private int value;

    public IntLiteralNode(Integer value, Position position) {
        super(position);
        this.value = value;
    }

    public int getLiteral() { return value; }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
