package AST;

import Utils.Position;

public class ReturnNode extends Stmt {
    private Expr expression;

    public ReturnNode(Expr expression, Position position) {
        super(position);
        this.expression = expression;
    }

    public Expr getExpression() {
        return expression;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
