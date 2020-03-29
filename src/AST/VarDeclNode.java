package AST;

import Utils.Position;

import java.util.List;

public class VarDeclNode extends ProgramFragment{
    private TypeNode type;
    private List<String> variables;
    private Boolean hasInitValue;
    private Expr initValue;

    public VarDeclNode(TypeNode type, List<String> variables, Boolean hasInitValue, Expr initValue, Position position) {
        super(position);
        this.type = type;
        this.variables = variables;
        this.hasInitValue = hasInitValue;
        if (hasInitValue == false) {
            throw new RuntimeException("VarDeclNode Error" + position.toString());
        }
        this.initValue = initValue;
    }

    public VarDeclNode(TypeNode type, List<String> variables, Boolean hasInitValue, Position position) {
        super(position);
        this.type = type;
        this.variables = variables;
        this.hasInitValue = hasInitValue;
        if (hasInitValue == true) {
            throw new RuntimeException("VarDeclNode Error" + position.toString());
        }
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
