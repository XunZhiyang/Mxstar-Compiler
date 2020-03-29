package AST;

import Symbol.ClassType;
import Utils.Position;

import java.util.List;

public class ClassDeclNode extends ProgramFragment {
    private String identifier;
    private List<VarDeclNode> fields;
    private List<FuncDeclNode> methods;

    private ClassType classType;

    public ClassDeclNode(String identifier, List<VarDeclNode> fields, List<FuncDeclNode> methods, Position position) {
        super(position);
        this.identifier = identifier;
        this.fields = fields;
        this.methods = methods;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
