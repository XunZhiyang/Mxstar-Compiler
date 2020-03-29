package AST;

import Utils.Position;

public class StringLiteralNode extends ConstExpr {
    private String value;

    public StringLiteralNode(String value, Position position) {
        super(position);
        this.value = value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
