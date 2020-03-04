package AST;

import Utils.Position;

public class SubscriptExprNode extends Expr {
    private Expr array;
    private Expr subscript;

    public SubscriptExprNode(Expr array, Expr subscript, Position position) {
        super(position);
        this.array = array;
        this.subscript = subscript;
    }
}
