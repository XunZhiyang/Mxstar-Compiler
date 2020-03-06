package AST;

import Utils.Position;

import java.util.List;

public class ClassDeclNode extends ProgramFragment {
    private String identifier;
    private List<VarDeclNode> fields;
    private List<FuncDeclNode> methods;

    public ClassDeclNode(String identifier, List<VarDeclNode> fields, List<FuncDeclNode> methods, Position position) {
        super(position);
        this.identifier = identifier;
        this.fields = fields;
        this.methods = methods;
    }
}
