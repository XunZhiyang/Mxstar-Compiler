package AST;

import Utils.Position;

import java.util.List;

public class FuncCallExprNode extends Expr {
    private Expr function;
    private List<Expr> arguments;

    public FuncCallExprNode(Expr function, List<Expr> arguments, Position position) {
        super(position);
        this.function = function;
        this.arguments = arguments;
    }

    public Expr getFunction() {
        return function;
    }

    public List<Expr> getArguments() {
        return arguments;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
