package AST;

import Utils.Position;

public class WhileStmtNode extends Stmt {
    private Expr condition;
    private Stmt statement;

    public WhileStmtNode(Expr condition, Stmt statement, Position position) {
        super(position);
        this.condition = condition;
        this.statement = statement;
    }
}
