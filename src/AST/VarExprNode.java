package AST;

import Utils.Position;

public class VarExprNode extends PrimaryExpr {
    private String identifier;

    public VarExprNode(String identifier, Position position) {
        super(position);
        this.identifier = identifier;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
