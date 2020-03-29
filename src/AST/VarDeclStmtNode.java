package AST;

import Utils.Position;

public class VarDeclStmtNode extends Stmt {
    private VarDeclNode variable;

    public VarDeclStmtNode(VarDeclNode variable, Position position) {
        super(position);
        this.variable = variable;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
