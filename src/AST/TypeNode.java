package AST;

import Utils.Position;

public class TypeNode extends ASTNode{
    private String type;
    private int arrayDimension;

    public TypeNode(String type, int arrayDimension, Position position) {
        super(position);
        this.type = type;
        this.arrayDimension = arrayDimension;
    }

    public void setDim(int dim) {
        arrayDimension = dim;
    }

    public int getDim() {
        return arrayDimension;
    }
}
