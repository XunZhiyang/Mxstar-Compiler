package Frontend;

import AST.*;
import Symbol.*;
import Utils.SemanticError;
import Utils.TypeError;

import java.util.List;

public class SymbolTableBuilder implements ASTVisitor {
    private GlobalScope globalScope;
    private Scope currentScope;
    private FunctionSymbol currentFunction;
    private ClassType currentClass;
    private boolean inLoop = false;
    private boolean inFunction = false;

    public SymbolTableBuilder(GlobalScope globalScope) {
        this.globalScope = globalScope;
        this.currentScope = globalScope;
    }

    @Override
    public void visit(BinaryExprNode node) {
        Expr src1 = node.getSrc1();
        Expr src2 = node.getSrc2();
        src1.accept(this);
        src2.accept(this);
        Type lhs = src1.getType();
        Type rhs = src2.getType();
        switch (node.getOp()) {
            case MUL:
            case DIV:
            case MOD:
            case SUB:
            case SAL:
            case SAR:
            case AND:
            case OR: {
                if (lhs.isInt() && rhs.isInt()) {
                    node.setType(globalScope.getIntType());
                }
                else throw new TypeError(node.getPosition());
                break;
            }
            case ADD: {
                if (lhs.isInt() && rhs.isInt()) {
                    node.setType(globalScope.getIntType());
                } else if (lhs.isString() && rhs.isString()) {
                    node.setType(globalScope.getStringType());
                } else {
                    System.out.println(lhs.getIdentifier());
                    System.out.println(rhs.getIdentifier());
                    throw new TypeError(node.getPosition());
                }
                break;
            }
            case LT:
            case GT:
            case LE:
            case GE: {
                if (lhs.isInt() && rhs.isInt()) {
                    node.setType(globalScope.getBoolType());
                } else if (lhs.isString() && rhs.isString()) {
                    node.setType(globalScope.getBoolType());
                } else {
                    throw new TypeError(node.getPosition());
                }
                break;
            }
            case EQ:
            case NE:
                if (lhs.equals(rhs) || (lhs.isNullable() && rhs.isNull()) || (lhs.isNull() && rhs.isNullable()))
                    node.setType(globalScope.getBoolType());
                else {
                    throw new TypeError(node.getPosition());
                }
                break;
            case XOR:
                if (lhs.isBoolean() && rhs.isBoolean()) {
                    node.setType(globalScope.getBoolType());
                } else if (lhs.isInt() && rhs.isInt()) {
                    node.setType(globalScope.getIntType());
                } else {
                    throw new TypeError(node.getPosition());
                }
                break;
            case ANL:
            case ORL:
                if (lhs.isBoolean() && rhs.isBoolean()) {
                    node.setType(globalScope.getBoolType());
                } else {
                    throw new TypeError(node.getPosition());
                }
                break;
            case ASSIGN:
                if (src1.getLvalue() && (lhs.assignable(rhs))) {
                    node.setType(globalScope.getVoidType());
                } else {
                    throw new TypeError(node.getPosition());
                }
        }
    }

    @Override
    public void visit(BoolExprNode node) {
        node.setType(globalScope.getBoolType());
    }

    @Override
    public void visit(BreakNode node) {
        if (!inLoop) {
            throw new SemanticError("break statement not within a loop", node.getPosition());
        }
    }

    @Override
    public void visit(ClassDeclNode node) {
        Scope thisScope = currentScope;
        currentScope = currentClass = node.getClassType();
        node.getFields().forEach(x -> x.accept(this));
        for (FuncDeclNode i : node.getMethods()) {
            i.accept(this);
        }
        currentClass = null;
        currentScope = thisScope;
    }

    @Override
    public void visit(CompoundStmtNode node) {
        Scope thisScope = currentScope;
        currentScope = new LocalScope(currentScope);
        for (Stmt i : node.getStmtList()) {
            i.accept(this);
        }
        currentScope = thisScope;
    }

    @Override
    public void visit(ConditionalExprNode node) {
        node.getCondition().accept(this);
        Expr opt1 = node.getOpt1();
        Expr opt2 = node.getOpt2();

        if (!node.getCondition().getType().isBoolean()) {
            throw new TypeError(node.getPosition());
        }
        opt1.accept(this);
        opt2.accept(this);

        if (opt1.getType().equals(opt2.getType())) {
            node.setType(opt1.getType());
        }
        else {
            throw new TypeError(node.getPosition());
        }
    }

    @Override
    public void visit(ContinueNode node) {
        if (!inLoop) {
            throw new SemanticError("continue statement not within a loop", node.getPosition());
        }
    }

    @Override
    public void visit(EmptyStmtNode node) {}

    @Override
    public void visit(ExprStmtNode node) {
        node.getExpression().accept(this);
    }

    @Override
    public void visit(FieldExprNode node) {
        Expr object = node.getObject();
        object.accept(this);
        if (object.getType().isClass()) {
            Symbol symbol = ((ClassType) object.getType()).getSymbol(node.getField(), node.getPosition());
            node.setType(symbol.getType());
            if (symbol.isFunction()) {
                node.setFunctionSymbol((FunctionSymbol) symbol);
            }
            node.setLvalue(true);
        } else if (object.getType().isArray()) {
            if (node.getField().equals("size")) {
                node.setType(globalScope.getIntType());
                node.setFunctionSymbol(globalScope.getSize());
            } else {
                throw new TypeError(node.getPosition());
            }
        }
        else{
            System.out.println(object.getType().getIdentifier());
            throw new TypeError(node.getPosition());
        }
    }

    @Override
    public void visit(ForStmtNode node) {
        Expr init = node.getInit();
        Expr condition = node.getCondition();
        Expr step = node.getStep();
        Stmt statement = node.getStatement();

        if (init != null)
            init.accept(this);
        if (condition != null) {
            condition.accept(this);
            if (!condition.getType().isBoolean())
                throw new TypeError(node.getPosition());
        }
        if (step != null)
            step.accept(this);
        if (statement != null) {
            boolean nowInLoop = inLoop;
            inLoop = true;
            Scope thisScope = currentScope;
            currentScope = new LocalScope(currentScope);
            statement.accept(this);
            currentScope = thisScope;
            inLoop = nowInLoop;
        }
    }

    @Override
    public void visit(FuncCallExprNode node) {
        Expr function = node.getFunction();
        List<Expr> args = node.getArguments();
        function.accept(this);
        if (function.getCallable()) {
            List<Type> param = function.getFunctionSymbol().getParam();
            if (args.size() != param.size())
                throw new SemanticError("Wrong argument number.", node.getPosition());
            int n = args.size();
            for (int i = 0; i < args.size(); ++i) {
                args.get(i).accept(this);
//                System.out.println(args.get(i).getType().getIdentifier());
//                System.out.println(param.get(i).getIdentifier());
                if (!args.get(i).getType().equals(param.get(n - i - 1)) && !(param.get(n - i - 1).isNullable() && args.get(i).getType().isNull())) {
                    throw new SemanticError("Wrong argument type.", node.getPosition());
                }
            }
            node.setType(function.getType());
        } else{
            throw new TypeError(node.getPosition());
        }
    }

    @Override
    public void visit(FuncDeclNode node) {
        inFunction = true;
        Scope nowScope = currentScope;
        currentScope = currentFunction = node.getSymbol();
        node.getStmt().accept(this);
        currentFunction = null;
        currentScope = nowScope;
        inFunction = false;
    }

    @Override
    public void visit(IdentifierListNode node) {}

    @Override
    public void visit(IntLiteralNode node) {
        node.setType(globalScope.getIntType());
    }

    @Override
    public void visit(NewExprNode node) {
        List<Expr> shape = node.getShape();
        for (Expr i : shape) {
            i.accept(this);
            if (!i.getType().isInt())
                throw new TypeError(node.getPosition());
        }
        node.setType(globalScope.getType(node.getNewType()));
    }

    @Override
    public void visit(NullNode node) {
        node.setType(globalScope.getNullType());
    }

    @Override
    public void visit(ParamDeclList node) {}

    @Override
    public void visit(ParamDeclNode node) {}

    @Override
    public void visit(ParamList node) {}

    @Override
    public void visit(ProgramNode node) {
        for (ProgramFragment i : node.getList()) {
            i.accept(this);
        }
    }

    @Override
    public void visit(ReturnNode node) {
        if (!inFunction) {
            throw new SemanticError("return statement not within a function", node.getPosition());
        }
        if (node.getExpression() != null) {
            node.getExpression().accept(this);
            if (!currentFunction.getType().assignable(node.getExpression().getType())) {
                throw new TypeError(node.getPosition());
            }
        }
    }

    @Override
    public void visit(SelectionStmtNode node) {
        node.getCond().accept(this);
        if (!node.getCond().getType().isBoolean()) {
            throw new TypeError(node.getPosition());
        }
        Scope thisScope = currentScope;
        for (Stmt i : node.getBranch()) {
            currentScope = new LocalScope(currentScope);
            i.accept(this);
        }
        currentScope = thisScope;
    }

    @Override
    public void visit(StringLiteralNode node) {
        node.setType(globalScope.getStringType());
    }

    @Override
    public void visit(SubscriptExprNode node) {
        node.getArray().accept(this);
        node.getSubscript().accept(this);
        if (!node.getArray().getType().isArray() || !node.getSubscript().getType().isInt()) {
            throw new TypeError(node.getPosition());
        }
        node.setLvalue(true);
        node.setType(((ArrayType) node.getArray().getType()).getMember());
    }

    @Override
    public void visit(ThisNode node) {
        node.setType(currentClass);
    }

    @Override
    public void visit(TypeNode node) {}

    @Override
    public void visit(UnaryExprNode node) {
        Expr src = node.getSrc();
        src.accept(this);
        switch (node.getOp()) {
            case PRE_INC:
            case PRE_DEC:
                if (!src.getType().isInt() || !src.getLvalue()) {
                    throw new TypeError(node.getPosition());
                }
                node.setLvalue(true);
                node.setType(globalScope.getIntType());
                break;
            case POST_INC:
            case POST_DEC:
                if (!src.getType().isInt() || !src.getLvalue()) {
                    throw new TypeError(node.getPosition());
                }
                node.setType(globalScope.getIntType());
                break;
            case POS:
            case NEG:
            case NOT:
                if (!src.getType().isInt()) {
                    throw new TypeError(node.getPosition());
                }
                node.setType(globalScope.getIntType());
                break;
            case NOTL:
                if (!src.getType().isBoolean()) {
                    throw new TypeError(node.getPosition());
                }
                node.setType(globalScope.getBoolType());
        }
    }

    @Override
    public void visit(VarDeclNode node) {
        Type type = globalScope.getType(node.getType());
        if (node.ifInitValue()) {
            Expr rhs = node.getInitValue();
            rhs.accept(this);
            if (!type.equals(rhs.getType()) && !(type.isNullable() && rhs.getType().isNull())) {
                throw new TypeError(node.getPosition());
            }
        }
        if (!(currentScope instanceof ClassType)) {
            for (String i : node.getVariables()) {
                VarSymbol varSymbol = new VarSymbol(type, i, node);
                currentScope.defineSymbol(varSymbol);
            }
        }
    }

    @Override
    public void visit(VarDeclStmtNode node) {
        node.getVariable().accept(this);
    }

    @Override
    public void visit(VarExprNode node) {
        Symbol symbol = currentScope.getSymbol(node.getIdentifier(), node.getPosition());
        node.setType(symbol.getType());
        node.setLvalue(true);
        if (symbol.isFunction())
            node.setFunctionSymbol((FunctionSymbol) symbol);
    }

    @Override
    public void visit(WhileStmtNode node) {
        Expr condition = node.getCondition();
        Stmt statement = node.getStatement();
        if (condition != null) {
            condition.accept(this);
            if (!condition.getType().isBoolean())
                throw new TypeError(node.getPosition());
        }
        if (statement != null) {
            boolean nowInLoop = inLoop;
            inLoop = true;
            Scope thisScope = currentScope;
            currentScope = new LocalScope(currentScope);
            statement.accept(this);
            currentScope = thisScope;
            inLoop = nowInLoop;
        }
    }
}
