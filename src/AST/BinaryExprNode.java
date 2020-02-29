package AST;

import Utils.BinaryOp;
import Utils.Position;

public class BinaryExprNode extends Expr {
    private Expr src1, src2;
    private BinaryOp op;

    public BinaryExprNode(Expr src1, Expr src2, BinaryOp op, Position position) {
        super(position);
        this.src1 = src1;
        this.src2 = src2;
        this.op = op;
    }
}
