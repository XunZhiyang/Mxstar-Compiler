package AST;

import Utils.Position;

public class ReturnNode extends Stmt {
    Expr expression;

    ReturnNode(Expr expression, Position position) {
        super(position);
        this.expression = expression;
    }
}
