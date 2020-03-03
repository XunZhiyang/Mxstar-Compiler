package AST;

import Utils.Position;

public abstract class PrimaryExpr extends Expr {
    PrimaryExpr(Position position) {
        super(position);
    }
}
