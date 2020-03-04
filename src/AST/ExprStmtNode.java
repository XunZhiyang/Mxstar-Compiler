package AST;

import Utils.Position;

public class ExprStmtNode extends Stmt {
    private Expr expression;

    public ExprStmtNode(Expr expression, Position position) {
        super(position);
        this.expression = expression;
    }
}
