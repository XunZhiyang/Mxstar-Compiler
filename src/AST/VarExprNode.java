package AST;

import Utils.Position;

public class VarExprNode extends PrimaryExpr {
    String identifier;

    VarExprNode(String identifier, Position position) {
        super(position);
        this.identifier = identifier;
    }
}
