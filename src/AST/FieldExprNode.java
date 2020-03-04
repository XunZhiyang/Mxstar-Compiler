package AST;

import Utils.Position;

public class FieldExprNode extends Expr {
    private Expr object;
    private String field;

    public FieldExprNode(Expr object, String field, Position position) {
        super(position);
        this.object = object;
        this.field = field;
    }
}
