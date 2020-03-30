package AST;

import Utils.Position;

public class ParamDeclNode extends ASTNode {
    private TypeNode type;
    private String identifier;

    public ParamDeclNode(TypeNode type, String identifier, Position position) {
        super(position);
        this.type = type;
        this.identifier = identifier;
    }

    public TypeNode getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
