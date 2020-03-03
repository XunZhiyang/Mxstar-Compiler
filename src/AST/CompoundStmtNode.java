package AST;

import Utils.Position;

import java.util.List;

public class CompoundStmtNode extends Stmt {
    List<Stmt> stmt;

    CompoundStmtNode(List<Stmt> stmt, Position position) {
        super(position);
        this.stmt = stmt;
    }
}
