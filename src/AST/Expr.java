package AST;

import Utils.Position;

public abstract class Expr extends ASTNode {
    Expr(Position position) {
        super(position);
    }
}
