package AST;

import Utils.Position;

public class FieldExprNode extends Expr {
    Expr object;
    String field;

    FieldExprNode(Expr object, String field, Position position) {
        super(position);
        this.object = object;
        this.field = field;
    }
}
