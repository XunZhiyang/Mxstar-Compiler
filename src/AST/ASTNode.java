package AST;

import Utils.Position;

public abstract class ASTNode {
    private Position position;

    public ASTNode(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public abstract void accept(ASTVisitor visitor);
}
