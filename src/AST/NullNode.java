package AST;

import Utils.Position;

public class NullNode extends ConstExpr {
    NullNode(Position position) {
        super(position);
    }
}
