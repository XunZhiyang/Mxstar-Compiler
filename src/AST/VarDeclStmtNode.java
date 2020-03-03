package AST;

import Utils.Position;

public class VarDeclStmtNode extends Stmt {
    VarDeclNode variable;

    VarDeclStmtNode(VarDeclNode variable, Position position) {
        super(position);
        this.variable = variable;
    }
}
