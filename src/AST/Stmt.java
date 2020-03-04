package AST;

import Utils.Position;

public abstract class Stmt extends ASTNode {
    Stmt(Position position) {
        super(position);
    }
}

