package AST;

import Utils.Position;

public class IntLiteralNode extends ConstExpr {
    private Integer value;

    public IntLiteralNode(Integer value, Position position) {
        super(position);
        this.value = value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
