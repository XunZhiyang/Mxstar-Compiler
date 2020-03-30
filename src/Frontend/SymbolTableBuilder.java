package Frontend;

import AST.*;
import Symbol.*;
import Utils.SemanticError;
import Utils.TypeError;

public class SymbolTableBuilder implements ASTVisitor {
    private GlobalScope globalScope;
    private Scope currentScope;

    public SymbolTableBuilder(GlobalScope globalScope) {
        this.globalScope = globalScope;
        this.currentScope = globalScope;
    }

    @Override
    public void visit(BinaryExprNode node){
        Expr src1 = node.getSrc1();
        Expr src2 = node.getSrc2();
        src1.accept(this);
        src2.accept(this);
        switch (node.getOp()) {
            case MUL:
            case DIV:
            case MOD:
            case SUB:
            case SAL:
            case SAR:
            case AND:
            case OR: {
                if (src1.getType().isInt() && src2.getType().isInt()) {
                    node.setType(globalScope.getIntType());
                }
                else throw new TypeError(node.getPosition());
                break;
            }
            case ADD: {
                if (src1.getType().isInt() && src2.getType().isInt()) {
                    node.setType(globalScope.getIntType());
                } else if (src1.getType().isInt() && src2.getType().isInt()) {
                    node.setType(globalScope.getStringType());
                } else {
                    throw new TypeError(node.getPosition());
                }
                break;
            }
            case LT:
            case GT:
            case LE:
            case GE: {
                if (src1.getType().isInt() && src2.getType().isInt()) {
                    node.setType(globalScope.getBoolType());
                } else if (src1.getType().isInt() && src2.getType().isInt()) {
                    node.setType(globalScope.getBoolType());
                } else {
                    throw new TypeError(node.getPosition());
                }
                break;
            }
            case EQ:
            case NE:
                src1.getType().compatible(src2.getType(), node.getPosition());
                node.setType(globalScope.getBoolType());
                break;
            case XOR:
                if (src1.getType().isBoolean() && src2.getType().isBoolean()) {
                    node.setType(globalScope.getBoolType());
                } else if (src1.getType().isInt() && src2.getType().isInt()) {
                    node.setType(globalScope.getIntType());
                } else{
                    throw new TypeError(node.getPosition());
                }
                break;
            case ANL:
            case ORL:
                if (src1.getType().isBoolean() && src2.getType().isBoolean()) {
                    node.setType(globalScope.getBoolType());
                } else{
                    throw new TypeError(node.getPosition());
                }
                break;
            case ASSIGN:
        }
    }

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
    public void visit(VarDeclNode node) {
        Type type = globalScope.getType(node.getType());
        if (node.ifInitValue()) {
            Expr rhs = node.getInitValue();
            rhs.accept(this);
            if (!type.equals(rhs.getType()) && !(type.isNullable() && rhs.getType().isNull()))
                throw new TypeError(node.getPosition());
        }
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
