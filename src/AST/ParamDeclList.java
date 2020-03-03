package AST;

import Utils.Position;

import java.util.List;

public class ParamDeclList extends ASTNode {
    private List<ParamDeclNode> paramList;

    public ParamDeclList(List<ParamDeclNode> paramList, Position position) {
        super(position);
        this.paramList = paramList;
    }

    public List<ParamDeclNode> getList() {
        return paramList;
    }
}
