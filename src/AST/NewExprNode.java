package AST;

import Utils.Position;

import java.util.List;

public class NewExprNode extends Expr {
    private TypeNode type;
    private List<Expr> shape;

    public NewExprNode(TypeNode type, List<Expr> shape, Position position) {
        super(position);
        this.type = type;
        this.shape = shape;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
