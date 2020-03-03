package AST;

import Utils.Position;

public class StringLiteralNode extends ConstExpr {
    String value;

    StringLiteralNode(String value, Position position) {
        super(position);
        this.value = value;
    }
}
