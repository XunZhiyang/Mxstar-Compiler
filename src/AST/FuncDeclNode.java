package AST;

import Utils.Position;

import java.util.List;

public class FuncDeclNode extends ProgramFragment {
    private Boolean isConstructor;
    private TypeNode type;
    private String identifier;
    private List<ParamDeclNode> params;
    private CompoundStmtNode stmt;

    public FuncDeclNode(Boolean isConstructor, TypeNode type, String identifier, List<ParamDeclNode> params,
                        CompoundStmtNode stmt, Position position) {
        super(position);
        this.isConstructor = isConstructor;
        this.type = type;
        this.identifier = identifier;
        this.params = params;
        this.stmt = stmt;
    }
}
