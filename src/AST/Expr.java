package AST;

import Utils.Position;

public abstract class Expr extends ASTNode {
    public Expr(Position position) {
        super(position);
    }
}
