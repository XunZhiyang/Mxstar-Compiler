package AST;

import Utils.Position;

import java.util.List;

public class VarDeclNode extends ProgramFragment{
    private TypeNode type;
    private List<String> variables;
    private boolean hasInitValue;
    private Expr initValue;

    public VarDeclNode(TypeNode type, List<String> variables, boolean hasInitValue, Expr initValue, Position position) {
        super(position);
        this.type = type;
        this.variables = variables;
        this.hasInitValue = hasInitValue;
        if (!hasInitValue) {
            throw new RuntimeException("VarDeclNode Error" + position.toString());
        }
        this.initValue = initValue;
    }

    public VarDeclNode(TypeNode type, List<String> variables, boolean hasInitValue, Position position) {
        super(position);
        this.type = type;
        this.variables = variables;
        this.hasInitValue = hasInitValue;
        if (hasInitValue) {
            throw new RuntimeException("VarDeclNode Error" + position.toString());
        }
    }

    public TypeNode getType() {
        return type;
    }

    public List<String> getVariables() {
        return variables;
    }

    public boolean ifInitValue() {
        return hasInitValue;
    }

    public Expr getInitValue() {
        return initValue;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
