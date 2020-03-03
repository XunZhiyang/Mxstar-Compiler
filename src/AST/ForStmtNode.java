package AST;

import Utils.Position;

public class ForStmtNode extends Stmt {
    Expr init, condition, step;
    Stmt statement;

    ForStmtNode(Expr init, Expr condition, Expr step, Stmt statement, Position position) {
        super(position);
        this.init = init;
        this.condition = condition;
        this.step = step;
        this.statement = statement;
    }
}
