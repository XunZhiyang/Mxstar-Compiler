package AST;

import Utils.Position;

public class IntLiteralNode extends ConstExpr {
    int value;

    IntLiteralNode(int value, Position position) {
        super(position);
        this.value = value;
    }
}
