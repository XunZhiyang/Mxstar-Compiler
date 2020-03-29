package Frontend;

import AST.*;
import Symbol.ClassType;
import Symbol.FunctionSymbol;
import Symbol.GlobalScope;

public class FunctionScanner implements ASTVisitor {
    private GlobalScope globalScope;

    public FunctionScanner(GlobalScope globalScope) {
        this.globalScope = globalScope;
    }

    @Override
    public void visit(BinaryExprNode node){}

    @Override
    public void visit(BoolExprNode node){}

    @Override
    public void visit(BreakNode node){}

    @Override
    public void visit(ClassDeclNode node){
        ClassType classType = new ClassType(node.getIdentifier(), node, globalScope);
        globalScope.defineClass(classType);
        node.setClassType(classType);
    }

    @Override
    public void visit(CompoundStmtNode node){}

    @Override
    public void visit(ConditionalExprNode node){}

    @Override
    public void visit(ContinueNode node){}

    @Override
    public void visit(EmptyStmtNode node){}

    @Override
    public void visit(ExprStmtNode node){}

    @Override
    public void visit(FieldExprNode node){}

    @Override
    public void visit(ForStmtNode node){}

    @Override
    public void visit(FuncCallExprNode node){}

    @Override
    public void visit(FuncDeclNode node){
        FunctionSymbol functionSymbol = new FunctionSymbol();
    }

    @Override
    public void visit(IdentifierListNode node){}

    @Override
    public void visit(IntLiteralNode node){}

    @Override
    public void visit(NewExprNode node){}

    @Override
    public void visit(NullNode node){}

    @Override
    public void visit(ParamDeclList node){}

    @Override
    public void visit(ParamDeclNode node){}

    @Override
    public void visit(ParamList node){}

    @Override
    public void visit(ProgramNode node){
        for (ProgramFragment i : node.getList()) {
            i.accept(this);
        }
    }

    @Override
    public void visit(ReturnNode node){}

    @Override
    public void visit(SelectionStmtNode node){}

    @Override
    public void visit(StringLiteralNode node){}

    @Override
    public void visit(SubscriptExprNode node){}

    @Override
    public void visit(ThisNode node){}

    @Override
    public void visit(TypeNode node){}

    @Override
    public void visit(UnaryExprNode node){}

    @Override
    public void visit(VarDeclNode node){}

    @Override
    public void visit(VarDeclStmtNode node){}

    @Override
    public void visit(VarExprNode node){}

    @Override
    public void visit(WhileStmtNode node){}
}
