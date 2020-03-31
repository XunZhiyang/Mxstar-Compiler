package AST;

import Symbol.FunctionSymbol;
import Utils.Position;

import java.util.List;

public class FuncDeclNode extends ProgramFragment {
    private boolean isConstructor;
    private TypeNode type;
    private String identifier;
    private List<ParamDeclNode> params;
    private CompoundStmtNode stmt;

    private FunctionSymbol symbol;

    public FuncDeclNode(Boolean isConstructor, TypeNode type, String identifier, List<ParamDeclNode> params,
                        CompoundStmtNode stmt, Position position) {
        super(position);
        this.isConstructor = isConstructor;
        this.type = type;
        this.identifier = identifier;
        this.params = params;
        this.stmt = stmt;
    }

    public void setSymbol(FunctionSymbol symbol) {
        this.symbol = symbol;
    }

    public TypeNode getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Boolean getIsConstructor() {
        return isConstructor;
    }

    public FunctionSymbol getSymbol() {
        return symbol;
    }

    public List<ParamDeclNode> getParams() {
        return params;
    }

    public CompoundStmtNode getStmt() {
        return stmt;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
