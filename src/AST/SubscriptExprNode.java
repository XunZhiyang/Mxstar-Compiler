package AST;

import Utils.Position;

public class SubscriptExprNode extends Expr {
    Expr array;
    Expr subscript;

    SubscriptExprNode(Expr array, Expr subscript, Position position) {
        super(position);
        this.array = array;
        this.subscript = subscript;
    }
}
