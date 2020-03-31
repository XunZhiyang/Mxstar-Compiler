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

    public Expr getCond() {
        return cond;
    }

    public Stmt[] getBranch() {
        return branch;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
