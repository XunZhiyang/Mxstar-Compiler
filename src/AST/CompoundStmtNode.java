package AST;

import Utils.Position;

import java.util.List;

public class CompoundStmtNode extends Stmt {
    private List<Stmt> stmtList;

    public CompoundStmtNode(List<Stmt> stmtList, Position position) {
        super(position);
        this.stmtList = stmtList;
    }

    public List<Stmt> getStmtList() {
        return stmtList;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
