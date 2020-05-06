package Backend;

import AST.*;
import IR.*;
import IR.Constant.*;
import IR.Instruction.*;
import IR.Module;
import Symbol.ClassType;
import Symbol.GlobalScope;
import Symbol.PointerType;
import Symbol.Type;

import java.awt.*;
import java.util.*;
import java.util.List;

public class IRBuilder implements ASTVisitor {
    Module module;
    GlobalScope globalScope;
    ClassType curClass;
    Function curFunction;
    BasicBlock curBlock, breakBlock, continueBlock, retBlock;
    Value returnValue, thisValue;
    boolean scanGlobalVariable;

    Map<String, GlobalVariable> stringLiteralMap = new LinkedHashMap<>();

    public IRBuilder(GlobalScope globalScope) {
        this.globalScope = globalScope;
    }

    public Module getModule() {
        return module;
    }

//    private String convertFuncName(String identifier) {
//        return curClass == null ? identifier : curClass.getIdentifier() + "." + identifier;
//    }

    private Value assignConvert(Value value, Type demandedType) {
        if (value.getType() == demandedType) {
            return value;
        } if (value.getType().isNull()) {
            value.setType(demandedType);
            return value;
        }
        else {
            return new LoadInst(value, curBlock);
        }
    }

    @Override
    public void visit(ProgramNode node) {
        scanGlobalVariable = true;
        curFunction = new Function("_init", GlobalScope.getVoidType());
        module.addFunction(curFunction);
        for (ProgramFragment i : node.getList()) {
            if (i instanceof VarDeclNode) {
                i.accept(this);
            }
        }

        scanGlobalVariable = false;
        for (ProgramFragment i : node.getList()) {
            if (!(i instanceof VarDeclNode)) {
                i.accept(this);
            }
        }
    }

    @Override
    public void visit(BinaryExprNode node) {
        node.getSrc1().accept(this);
        node.getSrc2().accept(this);
        Value v1 = node.getSrc1().getValue();
        Value v2 = node.getSrc2().getValue();
        Type vType = node.getSrc1().getType();
        Value res = null;
        switch(node.getOp()){
            case MUL:
                v1 = assignConvert(v1, GlobalScope.getIntType());
                v2 = assignConvert(v2, GlobalScope.getIntType());
                res = new BinaryOpInst("mul", v1, v2, curBlock);
                break;
            case DIV:
                v1 = assignConvert(v1, GlobalScope.getIntType());
                v2 = assignConvert(v2, GlobalScope.getIntType());
                res = new BinaryOpInst("sdiv", v1, v2, curBlock);
                break;
            case MOD:
                v1 = assignConvert(v1, GlobalScope.getIntType());
                v2 = assignConvert(v2, GlobalScope.getIntType());
                res = new BinaryOpInst("srem", v1, v2, curBlock);
                break;
            case SUB:
                v1 = assignConvert(v1, GlobalScope.getIntType());
                v2 = assignConvert(v2, GlobalScope.getIntType());
                res = new BinaryOpInst("sub", v1, v2, curBlock);
                break;
            case SAL:
                v1 = assignConvert(v1, GlobalScope.getIntType());
                v2 = assignConvert(v2, GlobalScope.getIntType());
                res = new BinaryOpInst("shl", v1, v2, curBlock);
                break;
            case SAR:
                v1 = assignConvert(v1, GlobalScope.getIntType());
                v2 = assignConvert(v2, GlobalScope.getIntType());
                res = new BinaryOpInst("ashr", v1, v2, curBlock);
                break;
            case AND:
                v1 = assignConvert(v1, GlobalScope.getIntType());
                v2 = assignConvert(v2, GlobalScope.getIntType());
                res = new BinaryOpInst("and", v1, v2, curBlock);
                break;
            case OR: {
                v1 = assignConvert(v1, GlobalScope.getIntType());
                v2 = assignConvert(v2, GlobalScope.getIntType());
                res = new BinaryOpInst("or", v1, v2, curBlock);
                break;
            }
            case ADD: {
                if (vType.isInt()) {
                    v1 = assignConvert(v1, GlobalScope.getIntType());
                    v2 = assignConvert(v2, GlobalScope.getIntType());
                    res = new BinaryOpInst("add", v1, v2, curBlock);
                } else if (vType.isString()) {
                    v1 = assignConvert(v1, GlobalScope.getCharType().getPointer());
                    v2 = assignConvert(v2, GlobalScope.getCharType().getPointer());
                    res = new CallInst("_string_concatenate", GlobalScope.getCharType().getPointer(),
                            Arrays.asList(v1, v2), curBlock);
                }
                break;
            }
            case LT:
                if (vType.isInt()) {
                    v1 = assignConvert(v1, GlobalScope.getIntType());
                    v2 = assignConvert(v2, GlobalScope.getIntType());
                    res = new BinaryOpInst("slt", v1, v2, curBlock);
                } else if (vType.isString()) {
                    v1 = assignConvert(v1, GlobalScope.getCharType().getPointer());
                    v2 = assignConvert(v2, GlobalScope.getCharType().getPointer());
                    res = new CallInst("_string_lt", GlobalScope.getCharType().getPointer(),
                            Arrays.asList(v1, v2), curBlock);
                }
                break;
            case GT:
                if (vType.isInt()) {
                    v1 = assignConvert(v1, GlobalScope.getIntType());
                    v2 = assignConvert(v2, GlobalScope.getIntType());
                    res = new BinaryOpInst("sgt", v1, v2, curBlock);
                } else if (vType.isString()) {
                    v1 = assignConvert(v1, GlobalScope.getCharType().getPointer());
                    v2 = assignConvert(v2, GlobalScope.getCharType().getPointer());
                    res = new CallInst("_string_gt", GlobalScope.getCharType().getPointer(),
                            Arrays.asList(v1, v2), curBlock);
                }
                break;
            case LE:
                if (vType.isInt()) {
                    v1 = assignConvert(v1, GlobalScope.getIntType());
                    v2 = assignConvert(v2, GlobalScope.getIntType());
                    res = new BinaryOpInst("sle", v1, v2, curBlock);
                } else if (vType.isString()) {
                    v1 = assignConvert(v1, GlobalScope.getCharType().getPointer());
                    v2 = assignConvert(v2, GlobalScope.getCharType().getPointer());
                    res = new CallInst("_string_le", GlobalScope.getCharType().getPointer(),
                            Arrays.asList(v1, v2), curBlock);
                }
                break;
            case GE: {
                if (vType.isInt()) {
                    v1 = assignConvert(v1, GlobalScope.getIntType());
                    v2 = assignConvert(v2, GlobalScope.getIntType());
                    res = new BinaryOpInst("sge", v1, v2, curBlock);
                } else if (vType.isString()) {
                    v1 = assignConvert(v1, GlobalScope.getCharType().getPointer());
                    v2 = assignConvert(v2, GlobalScope.getCharType().getPointer());
                    res = new CallInst("_string_ge", GlobalScope.getCharType().getPointer(),
                            Arrays.asList(v1, v2), curBlock);
                }
                break;
            }
            case EQ:
                if (node.getSrc1().getType().isString() && node.getSrc2().getType().isString()) {
                    v1 = assignConvert(v1, GlobalScope.getCharType().getPointer());
                    v2 = assignConvert(v2, GlobalScope.getCharType().getPointer());
                    res = new CallInst("_string_eq", GlobalScope.getCharType().getPointer(),
                            Arrays.asList(v1, v2), curBlock);
                } else {
                    if (!v1.getType().isNull()) {
                        v1 = new LoadInst(v1, curBlock);
                    }
                    if (!v2.getType().isNull()) {
                        v2 = new LoadInst(v2, curBlock);
                    }
                    res = new BinaryOpInst("eq", v1, v2, curBlock);
                }
                break;
            case NE:
                if (node.getSrc1().getType().isString() && node.getSrc2().getType().isString()) {
                    v1 = assignConvert(v1, GlobalScope.getCharType().getPointer());
                    v2 = assignConvert(v2, GlobalScope.getCharType().getPointer());
                    res = new CallInst("_string_ne", GlobalScope.getCharType().getPointer(),
                            Arrays.asList(v1, v2), curBlock);
                } else {
                    if (!v1.getType().isNull()) {
                        v1 = new LoadInst(v1, curBlock);
                    }
                    if (!v2.getType().isNull()) {
                        v2 = new LoadInst(v2, curBlock);
                    }
                    res = new BinaryOpInst("ne", v1, v2, curBlock);
                }
                break;
            case XOR:
                if (vType.isBoolean()) {
                    v1 = assignConvert(v1, GlobalScope.getBoolType());
                    v2 = assignConvert(v2, GlobalScope.getBoolType());
                } else {
                    v1 = assignConvert(v1, GlobalScope.getIntType());
                    v2 = assignConvert(v2, GlobalScope.getIntType());
                }
                res = new BinaryOpInst("xor", v1, v2, curBlock);
                break;
            case ANL:
                v1 = assignConvert(v1, GlobalScope.getBoolType());
                v2 = assignConvert(v2, GlobalScope.getBoolType());
                res = new BinaryOpInst("and", v1, v2, curBlock);
                break;
            case ORL:
                v1 = assignConvert(v1, GlobalScope.getBoolType());
                v2 = assignConvert(v2, GlobalScope.getBoolType());
                res = new BinaryOpInst("or", v1, v2, curBlock);
                break;
            case ASSIGN:
                v2 = assignConvert(v2, ((PointerType) v1.getType()).getMember());
                new StoreInst(v2, v1, curBlock);
                res = v1;
        }
        node.setValue(res);
    }

    @Override
    public void visit(BoolExprNode node) {
        node.setValue(new BoolConst(node.getBoolValue()));
    }

    @Override
    public void visit(BreakNode node) {
        new JumpInst(breakBlock, curBlock);
    }

    @Override
    public void visit(ClassDeclNode node) {
        module.addClass(node.getClassType());
    }

    @Override
    public void visit(CompoundStmtNode node) {
        node.getStmtList().forEach(x -> x.accept(this));
    }

    @Override
    public void visit(ConditionalExprNode node) {
//        Suddenly found that there is no such Expr
//        Cry Cry
    }

    @Override
    public void visit(ContinueNode node) {
        new JumpInst(continueBlock, curBlock);
    }

    @Override
    public void visit(EmptyStmtNode node) {}

    @Override
    public void visit(ExprStmtNode node) {
        node.getExpression().accept(this);
    }

    @Override
    public void visit(FieldExprNode node) {
        node.getObject().accept(this);
        ClassType thisClass = (ClassType) ((PointerType) node.getObject().getValue().getType()).getMember();
        Type fieldType = thisClass.getFieldType(node.getField());
        if (!thisClass.getIsMethod(node.getField())) {
            GEPInst inst = new GEPInst(node.getObject().getValue(), fieldType, curBlock);
            inst.addOperand(new IntConst(0), new IntConst(thisClass.getFieldIndex(node.getField())));
        } else {
            node.setValue(node.getObject().getValue());
        }
    }

    @Override
    public void visit(ForStmtNode node) {
        BasicBlock forCond = curFunction.add("forCond");
        BasicBlock forStep = curFunction.add("forStep");
        BasicBlock forBody = curFunction.add("forBody");
        BasicBlock forAfter = curFunction.add("forAfter");

        if (node.getInit() != null)
            node.getInit().accept(this);
        new JumpInst(forCond, curBlock);

        curBlock = forCond;
        Value condValue;
        if (node.getCondition() != null) {
            node.getCondition().accept(this);
            condValue = assignConvert(node.getCondition().getValue(), GlobalScope.getBoolType());
        }
        else {
            condValue = new BoolConst(true);
        }
        new BranchInst(condValue, forBody, forAfter, curBlock);

        curBlock = forBody;
        BasicBlock tempBreak = breakBlock;
        BasicBlock tempContinue = continueBlock;
        breakBlock = forAfter;
        continueBlock = forStep;
        if (node.getStatement() != null)
            node.getStatement().accept(this);
        breakBlock = tempBreak;
        continueBlock = tempContinue;
        new JumpInst(forStep, curBlock);

        curBlock = forStep;
        if (node.getStep() != null)
            node.getStep().accept(this);
        new JumpInst(forCond, curBlock);

        curBlock = forAfter;
    }


    @Override
    public void visit(FuncDeclNode node) {
        String identifier = node.getSymbol().IRName();
        curFunction = new Function(identifier, node.getSymbol().getType());
        module.addFunction(curFunction);

        if (curClass != null) {
            thisValue = new Value(".this", curClass.getPointer());
            curFunction.addOperand(thisValue);
        }

        for (int i = 0; i < node.getParams().size(); ++i) {
            curFunction.addOperand(new Value(".p", node.getSymbol().getParam().get(i)));
        }

        retBlock = curFunction.add("retBlock");
        curBlock = curFunction.add("initBlock");


        if (!curFunction.getType().isVoid()) {
            returnValue = new AllocaInst(curFunction.getType(), curBlock);
            Value loadedReturnValue = new LoadInst(returnValue, retBlock);
            new ReturnInst(loadedReturnValue, retBlock);
        } else {
            new ReturnInst(retBlock);
        }

        if (node.getIdentifier().equals("main")) {
            new StoreInst(new IntConst(0), returnValue, curBlock);
            new CallInst("_init", GlobalScope.getVoidType(), new ArrayList<>(),curBlock);
        }

        node.getStmt().accept(this);

        List<BasicBlock> blocks = curFunction.getBasicBlockList();
        for (BasicBlock block : blocks) {
            if (!block.isTerminated()) {
                new JumpInst(retBlock, block);
            }
        }

    }

    @Override
    public void visit(FuncCallExprNode node) {
        node.getFunction().accept(this);
        List<Value> arguments = new ArrayList<>();
        List<Type> paramList = node.getFunction().getFunctionSymbol().getParam();

        if (node.getFunction().getValue() != null) {
            arguments.add(node.getFunction().getValue());
        }

        for (int i = 0; i < node.getArguments().size(); ++i) {
            Expr t = node.getArguments().get(i);
            arguments.add(assignConvert(t.getValue(), paramList.get(i)));
        }
        new CallInst(node.getFunction().getFunctionSymbol(), arguments, curBlock);
    }

    @Override
    public void visit(IdentifierListNode node) {}

    @Override
    public void visit(IntLiteralNode node) {
        node.setValue(new IntConst(node.getLiteral()));
    }

//    private

    @Override
    public void visit(NewExprNode node) {
        Type type = globalScope.getType(node.getNewType());
//        if (type.is)
    }

    @Override
    public void visit(NullNode node) {
        node.setValue(new NullConst());
    }

    @Override
    public void visit(ParamDeclList node) {
        //never accessed
    }

    @Override
    public void visit(ParamDeclNode node) {
        //never accessed
    }

    @Override
    public void visit(ParamList node) {
        //never accessed
    }

    @Override
    public void visit(ReturnNode node) {
        node.getExpression().accept(this);
        new StoreInst(assignConvert(node.getExpression().getValue(), returnValue.getType()), returnValue, curBlock);
        new JumpInst(retBlock, curBlock);
    }

    @Override
    public void visit(SelectionStmtNode node) {
        BasicBlock ifTaken = curFunction.add("ifTaken");
        BasicBlock ifNotTaken = node.getNotTaken() == null? null : curFunction.add("ifNotTaken");
        BasicBlock ifAfter = curFunction.add("ifAfter");

        node.getCond().accept(this);
        Value cond = assignConvert(node.getCond().getValue(), GlobalScope.getBoolType());
        node.getTaken().accept(this);

        if (node.getNotTaken() != null) {
            new BranchInst(cond, ifTaken, ifNotTaken, curBlock);
            curBlock = ifNotTaken;
            node.getNotTaken().accept(this);
            new JumpInst(ifAfter, curBlock);
        }
        else {
            new BranchInst(cond, ifTaken, ifAfter, curBlock);
        }

        curBlock = ifTaken;
        node.getTaken().accept(this);
        new JumpInst(ifAfter, curBlock);
    }

    @Override
    public void visit(StringLiteralNode node) {
        String s = node.getStringLiteral();

        s = s.replace("\\\\", "\\5C");
        s = s.replace("\\n", "\\0A");
        s = s.replace("\\\"", "\\22");

        GlobalVariable res = stringLiteralMap.get(s);
        if (res == null) {
            res = new StringConst(s);
            stringLiteralMap.put(s, res);
            module.addGlobalVariable(res);
        }
        node.setValue(res);
    }

    @Override
    public void visit(SubscriptExprNode node) {
        node.getArray().accept(this);
        node.getSubscript().accept(this);
        Value num = assignConvert(node.getSubscript().getValue(), GlobalScope.getIntType());
        GEPInst inst = new GEPInst(node.getArray().getValue(), ((PointerType) node.getArray().getType()).getMember(), curBlock);
        inst.addOperand(num);
    }

    @Override
    public void visit(ThisNode node) {
        node.setValue(thisValue);
    }

    @Override
    public void visit(TypeNode node) {}

    @Override
    public void visit(UnaryExprNode node) {
        node.getSrc().accept(this);
        Value src = node.getSrc().getValue();
        Value v, res = null;
        switch (node.getOp()) {
            case PRE_INC:
                v = new BinaryOpInst("add", assignConvert(src, GlobalScope.getIntType()),
                        new IntConst(1), curBlock);
                new StoreInst(v, src, curBlock);
                res = src;
                break;
            case PRE_DEC:
                v = new BinaryOpInst("sub", assignConvert(src, GlobalScope.getIntType()),
                        new IntConst(1), curBlock);
                new StoreInst(v, src, curBlock);
                res = src;
                break;
            case POST_INC:
                v = assignConvert(src, GlobalScope.getIntType());
                new StoreInst(new BinaryOpInst("add", v, new IntConst(1), curBlock), src, curBlock);
                res = v;
                break;
            case POST_DEC:
                v = assignConvert(src, GlobalScope.getIntType());
                new StoreInst(new BinaryOpInst("sub", v, new IntConst(1), curBlock), src, curBlock);
                res = v;
                break;
            case POS:
                res = src;
                break;
            case NEG:
                res = new BinaryOpInst("sub", new IntConst(0),
                        assignConvert(src, GlobalScope.getIntType()), curBlock);
                break;
            case NOT:
                res = new BinaryOpInst("xor", assignConvert(src, GlobalScope.getIntType()),
                        new IntConst(-1), curBlock);
                break;
            case NOTL:
                res = new BinaryOpInst("xor", assignConvert(src, GlobalScope.getBoolType()),
                        new BoolConst(true), curBlock);
                break;
        }
        node.setValue(res);
    }

    @Override
    public void visit(VarDeclNode node) {
        Value np;
        for (int i = 0; i < node.getVarSymbol().size(); ++i) {
            if (scanGlobalVariable) {
                np = new GlobalVariable(node.getVariables().get(i), globalScope.getType(node.getType()).getPointer());
            }
            else {
                np = new AllocaInst(globalScope.getType(node.getType()), curBlock);
            }
            node.getVarSymbol().get(i).setValue(np);
            if (node.ifInitValue()) {
                node.getInitValue().accept(this);
                Value v = assignConvert(node.getInitValue().getValue(), globalScope.getType(node.getType()));
                new StoreInst(v, np, curBlock);
            }
        }
    }

    @Override
    public void visit(VarDeclStmtNode node) {
        node.getVariable().accept(this);
    }

    @Override
    public void visit(VarExprNode node) {
        node.setValue(node.getVarSymbol().getValue());
    }

    @Override
    public void visit(WhileStmtNode node) {
        BasicBlock whileCond = curFunction.add("forCond");
        BasicBlock whileBody = curFunction.add("forBody");
        BasicBlock whileAfter = curFunction.add("forAfter");

        new JumpInst(whileCond, curBlock);

        curBlock = whileCond;
        node.getCondition().accept(this);
        Value condValue = assignConvert(node.getCondition().getValue(), GlobalScope.getBoolType());
        new BranchInst(condValue, whileBody, whileAfter, curBlock);

        curBlock = whileBody;
        BasicBlock tempBreak = breakBlock;
        BasicBlock tempContinue = continueBlock;
        breakBlock = whileAfter;
        continueBlock = whileCond;
        if (node.getStatement() != null)
            node.getStatement().accept(this);
        breakBlock = tempBreak;
        continueBlock = tempContinue;
        new JumpInst(whileCond, curBlock);

        curBlock = whileAfter;
    }
}
