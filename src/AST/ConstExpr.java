package AST;

import Utils.Position;

public abstract class ConstExpr extends PrimaryExpr{
    ConstExpr(Position position) {
        super(position);
    }
}
