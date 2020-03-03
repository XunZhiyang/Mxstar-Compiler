package AST;

import Utils.Position;

public class WhileStmtNode extends Stmt {
    Expr condition;
    Stmt statement;

    WhileStmtNode(Expr condition, Stmt statement, Position position) {
        super(position);
        this.condition = condition;
        this.statement = statement;
    }
}
