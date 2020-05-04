package AST;

import Utils.Position;

public class SelectionStmtNode extends Stmt {
    private Expr cond;
    private Stmt taken, notTaken;

    public SelectionStmtNode(Expr cond, Stmt taken, Stmt notTaken, Position position) {
        super(position);
        this.cond = cond;
        this.taken = taken;
        this.notTaken = notTaken;
    }

    public Expr getCond() {
        return cond;
    }

    public Stmt getTaken() {
        return taken;
    }

    public Stmt getNotTaken() {
        return notTaken;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
