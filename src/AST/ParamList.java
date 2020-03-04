package AST;

import Utils.Position;

import java.util.List;

public class ParamList extends ASTNode {
    private List<Expr> paramList;

    public ParamList(List<Expr> paramList, Position position) {
        super(position);
        this.paramList = paramList;
    }

    public List<Expr> getList() {
        return paramList;
    }
}
