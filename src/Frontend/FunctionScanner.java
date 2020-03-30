package Frontend;

import AST.*;
import Symbol.*;
import Utils.SemanticError;

public class FunctionScanner implements ASTVisitor {
    private GlobalScope globalScope;
    private Scope currentScope;

    public FunctionScanner(GlobalScope globalScope) {
        this.globalScope = globalScope;
        this.currentScope = globalScope;
    }

    private void checkMain() {
        FunctionSymbol mainFunction =  globalScope.getMain();
        if (mainFunction.getType() != globalScope.getIntType()) {
            throw new SemanticError("'main' must return int.", mainFunction.getPosition());
        }
        if (mainFunction.mapSize() > 0) {
            throw new SemanticError("'main' must not have arguments.", mainFunction.getPosition());
        }
    }

    @Override
    public void visit(BinaryExprNode node){}

    @Override
    public void visit(BoolExprNode node){}

    @Override
    public void visit(BreakNode node){}

    @Override
    public void visit(ClassDeclNode node){
        currentScope = node.getClassType();
        for (FuncDeclNode i : node.getMethods()) {
            i.accept(this);
            currentScope = node.getClassType();
        }
        node.getFields().forEach(x -> x.accept(this));
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
        FunctionSymbol functionSymbol;
        if (node.getIsConstructor()) {
            functionSymbol = new FunctionSymbol(null, node.getIdentifier(), node, currentScope);
            if (!functionSymbol.getIdentifier().equals(((ClassType) currentScope).getIdentifier())) {
                throw new SemanticError("Not a legal constructor.", functionSymbol.getPosition());
            }
            currentScope.defineSymbol(functionSymbol);
        }
        else {
            Type type = globalScope.getType(node.getType());
            functionSymbol = new FunctionSymbol(type, node.getIdentifier(), node, currentScope);
            if (currentScope instanceof ClassType) {
                if (functionSymbol.getIdentifier().equals(((ClassType) currentScope).getIdentifier())) {
                    throw new SemanticError("Not a legal constructor.", functionSymbol.getPosition());
                }
            }
        }
       functionSymbol.setConstructor(node.getIsConstructor());
        currentScope.defineSymbol(functionSymbol);
        node.setSymbol(functionSymbol);

        currentScope = functionSymbol;
        for (ParamDeclNode i : node.getParams()) {
            i.accept(this);
            currentScope = functionSymbol;
        }
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
    public void visit(ParamDeclNode node){
        VarSymbol varSymbol = new VarSymbol(globalScope.getType(node.getType()), node.getIdentifier(), node);
        currentScope.defineSymbol(varSymbol);
    }

    @Override
    public void visit(ParamList node){}

    @Override
    public void visit(ProgramNode node){
        for (ProgramFragment i : node.getList()) {
            i.accept(this);
            currentScope = globalScope;
        }
        checkMain();
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
    public void visit(VarDeclNode node){
        Type type = globalScope.getType(node.getType());
        for (String i : node.getVariables()) {
            VarSymbol varSymbol = new VarSymbol(type, i, node);
            currentScope.defineSymbol(varSymbol);
        }
    }

    @Override
    public void visit(VarDeclStmtNode node){}

    @Override
    public void visit(VarExprNode node){}

    @Override
    public void visit(WhileStmtNode node){}
}
