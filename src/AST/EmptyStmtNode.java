package AST;

import Utils.Position;

public class EmptyStmtNode extends Stmt {
    public EmptyStmtNode(Position position) {
        super(position);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
