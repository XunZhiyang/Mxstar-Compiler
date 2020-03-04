package AST;

import Utils.Position;

public abstract class ConstExpr extends PrimaryExpr{
    public ConstExpr(Position position) {
        super(position);
    }
}
