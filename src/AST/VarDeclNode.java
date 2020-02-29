package AST;

import Utils.Position;

public class VarDeclNode extends ProgramFragment{
    private TypeNode type;
    private IdentifierListNode variables;
    private Boolean hasInitValue;
    private Expr initValue;

    public VarDeclNode(TypeNode type, IdentifierListNode variables, Boolean hasInitValue, ExprNode initValue, Position position) {
        super(position);
        this.type = type;
        this.variables = variables;
        this.hasInitValue = hasInitValue;
        this.initValue = initValue;
    }

}
