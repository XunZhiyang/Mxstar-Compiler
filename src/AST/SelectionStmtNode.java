package AST;

import Utils.Position;

public class SelectionStmtNode extends Stmt {
    private Expr cond;
    private Stmt[] branch;

    public SelectionStmtNode(Expr cond, Stmt[] branch, Position position) {
        super(position);
        this.cond = cond;
        this.branch = branch;
    }
}
