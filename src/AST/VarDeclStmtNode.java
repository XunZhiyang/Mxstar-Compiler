package AST;

import Utils.Position;

public class VarDeclStmtNode extends Stmt {
    private VarDeclNode variable;

    public VarDeclStmtNode(VarDeclNode variable, Position position) {
        super(position);
        this.variable = variable;
    }
}
