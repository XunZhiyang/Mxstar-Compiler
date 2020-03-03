package AST;

import Utils.Position;

import java.util.List;

public class FuncCallExprNode extends Expr {
    Expr function;
    List<Expr> parameters;

    FuncCallExprNode(Expr function, List<Expr> parameters, Position position) {
        super(position);
        this.function = function;
        this.parameters = parameters;
    }
}
