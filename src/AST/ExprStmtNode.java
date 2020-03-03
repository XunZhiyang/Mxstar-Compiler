package AST;

import Utils.Position;

public class ExprStmtNode extends Stmt {
    Expr expression;

    ExprStmtNode(Expr expression, Position position) {
        super(position);
        this.expression = expression;
    }
}
