package AST;

import Utils.Position;

public class VarDeclStmtNode extends Stmt {
    private VarDeclNode variable;

    public VarDeclStmtNode(VarDeclNode variable, Position position) {
        super(position);
        this.variable = variable;
    }

    public VarDeclNode getVariable() {
        return variable;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
