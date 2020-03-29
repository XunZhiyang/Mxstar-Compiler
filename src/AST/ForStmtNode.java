package AST;

import Utils.Position;

public class ForStmtNode extends Stmt {
    private Expr init, condition, step;
    private Stmt statement;

    public ForStmtNode(Expr init, Expr condition, Expr step, Stmt statement, Position position) {
        super(position);
        this.init = init;
        this.condition = condition;
        this.step = step;
        this.statement = statement;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
