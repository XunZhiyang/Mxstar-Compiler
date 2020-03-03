package AST;

import Utils.Position;

public class BoolExprNode extends ConstExpr {
    private Boolean value;

    public BoolExprNode(Boolean value, Position position) {
        super(position);
        this.value = value;
    }
}
