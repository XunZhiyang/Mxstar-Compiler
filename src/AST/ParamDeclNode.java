package AST;

import IR.Value;
import Utils.Position;

public class ParamDeclNode extends ASTNode {
    private TypeNode type;
    private String identifier;

    Value value;

    public ParamDeclNode(TypeNode type, String identifier, Position position) {
        super(position);
        this.type = type;
        this.identifier = identifier;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
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
