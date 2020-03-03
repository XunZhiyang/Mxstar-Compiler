package AST;

import Utils.Position;

import java.util.List;

public class NewExprNode extends Expr {
    TypeNode type;
    List<Integer> shape;

    NewExprNode(TypeNode type, List<Integer> shape, Position position) {
        super(position);
        this.type = type;
        this.shape = shape;
    }

}
