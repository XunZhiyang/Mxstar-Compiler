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

    public Expr getArray() {
        return array;
    }

    public Expr getSubscript() {
        return subscript;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
