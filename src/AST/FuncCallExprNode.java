package AST;

import Utils.Position;

import java.util.List;

public class FuncCallExprNode extends Expr {
    private Expr function;
    private List<Expr> parameters;

    public FuncCallExprNode(Expr function, List<Expr> parameters, Position position) {
        super(position);
        this.function = function;
        this.parameters = parameters;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
