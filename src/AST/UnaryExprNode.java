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

    public Expr getSrc() {
        return src;
    }

    public UnaryOp getOp() {
        return op;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
