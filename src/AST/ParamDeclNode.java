package AST;

import Utils.Position;

public class ParamDeclNode extends ASTNode {
    TypeNode type;
    String name;

    ParamDeclNode(TypeNode type, String name, Position position) {
        super(position);
        this.type = type;
        this.name = name;
    }
}
