package AST;

import Utils.Position;

import java.util.List;

public class NewExprNode extends Expr {
    private TypeNode newType;
    private List<Expr> shape;

    public NewExprNode(TypeNode type, List<Expr> shape, Position position) {
        super(position);
        this.newType = type;
        this.shape = shape;
    }

    public TypeNode getNewType() {
        return newType;
    }

    public List<Expr> getShape() {
        return shape;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
