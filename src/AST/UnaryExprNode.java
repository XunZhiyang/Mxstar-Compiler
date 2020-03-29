package AST;

import Utils.UnaryOp;
import Utils.Position;

public class UnaryExprNode extends Expr {
    Expr src;
    private UnaryOp op;

    public UnaryExprNode(Expr src, UnaryOp op, Position position) {
        super(position);
        this.src = src;
        this.op = op;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
