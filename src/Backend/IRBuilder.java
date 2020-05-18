package Backend;

import AST.*;
import IR.*;
import IR.Constant.*;
import IR.Instruction.*;
import IR.Module;
import Symbol.*;

import java.util.*;
import java.util.List;

import static Symbol.GlobalScope.getIntType;
import static Utils.BinaryOp.ANL;

public class IRBuilder implements ASTVisitor {
    Module module = new Module();
    GlobalScope globalScope;
    ClassType curClass;
    Function curFunction;
    BasicBlock curBlock, breakBlock, continueBlock, retBlock;
    Value returnValue;
    Value thisValue;
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
        if (value instanceof StringConst) {
            return new GEPInst(value, GlobalScope.getCharType().getPointer(), curBlock){{
                addOperand(new IntConst(0), new IntConst(0));
            }};
        } if (value.getType().equals(demandedType)) {
            return value;
        } if (value.getType().isNull()) {
            value.setType(demandedType);
            return value;
        } if (value.getType().isPointer() && ((PointerType) value.getType()).getMember().equals(demandedType)) {
//            new SextInst(new IntConst(-1), GlobalScope.getIntType(), curBlock);
            return new LoadInst(value, curBlock);
        } else {
            return value;
        }
    }

    @Override
    public void visit(ProgramNode node) {
        scanGlobalVariable = true;
        curFunction = new Function("@_init", GlobalScope.getVoidType());
        module.addFunction(curFunction);
        curBlock = curFunction.add("initBlock");

        for (ProgramFragment i : node.getList()) {
            if (i instanceof VarDeclNode) {
                i.accept(this);
            }
        }

        new ReturnInst(curBlock);

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
        Value v1 = node.getSrc1().getValue();
        Value v2;
        Type vType = node.getSrc1().getType();
        Value res = null;
        switch(node.getOp()){
            case MUL:
                v1 = assignConvert(v1, getIntType());
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                v2 = assignConvert(v2, getIntType());
                res = new BinaryOpInst("mul", v1, v2, curBlock);
                break;
            case DIV:
                v1 = assignConvert(v1, getIntType());
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                v2 = assignConvert(v2, getIntType());
                res = new BinaryOpInst("sdiv", v1, v2, curBlock);
                break;
            case MOD:
                v1 = assignConvert(v1, getIntType());
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                v2 = assignConvert(v2, getIntType());
                res = new BinaryOpInst("srem", v1, v2, curBlock);
                break;
            case SUB:
                v1 = assignConvert(v1, getIntType());
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                v2 = assignConvert(v2, getIntType());
                res = new BinaryOpInst("sub", v1, v2, curBlock);
                break;
            case SAL:
                v1 = assignConvert(v1, getIntType());
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                v2 = assignConvert(v2, getIntType());
                res = new BinaryOpInst("shl", v1, v2, curBlock);
                break;
            case SAR:
                v1 = assignConvert(v1, getIntType());
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                v2 = assignConvert(v2, getIntType());
                res = new BinaryOpInst("ashr", v1, v2, curBlock);
                break;
            case AND:
                v1 = assignConvert(v1, getIntType());
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                v2 = assignConvert(v2, getIntType());
                res = new BinaryOpInst("and", v1, v2, curBlock);
                break;
            case OR:
                v1 = assignConvert(v1, getIntType());
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                v2 = assignConvert(v2, getIntType());
                res = new BinaryOpInst("or", v1, v2, curBlock);
                break;
            case ADD:
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                if (vType.isInt()) {
                    v1 = assignConvert(v1, getIntType());
                    v2 = assignConvert(v2, getIntType());
                    res = new BinaryOpInst("add", v1, v2, curBlock);
                } else if (vType.isString()) {
                    v1 = assignConvert(v1, GlobalScope.getCharType().getPointer());
                    v2 = assignConvert(v2, GlobalScope.getCharType().getPointer());
                    res = new CallInst("_string_concatenate", GlobalScope.getCharType().getPointer(),
                            Arrays.asList(v1, v2), curBlock);
                }
                break;
            case LT:
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                if (vType.isInt()) {
                    v1 = assignConvert(v1, getIntType());
                    v2 = assignConvert(v2, getIntType());
                    res = new CmpInst("slt", v1, v2, curBlock);
                } else if (vType.isString()) {
                    v1 = assignConvert(v1, GlobalScope.getCharType().getPointer());
                    v2 = assignConvert(v2, GlobalScope.getCharType().getPointer());
                    res = new CallInst("_string_lt", GlobalScope.getCharType().getPointer(),
                            Arrays.asList(v1, v2), curBlock);
                }
                break;
            case GT:
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                if (vType.isInt()) {
                    v1 = assignConvert(v1, getIntType());
                    v2 = assignConvert(v2, getIntType());
                    res = new CmpInst("sgt", v1, v2, curBlock);
                } else if (vType.isString()) {
                    v1 = assignConvert(v1, GlobalScope.getCharType().getPointer());
                    v2 = assignConvert(v2, GlobalScope.getCharType().getPointer());
                    res = new CallInst("_string_gt", GlobalScope.getCharType().getPointer(),
                            Arrays.asList(v1, v2), curBlock);
                }
                break;
            case LE:
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                if (vType.isInt()) {
                    v1 = assignConvert(v1, getIntType());
                    v2 = assignConvert(v2, getIntType());
                    res = new CmpInst("sle", v1, v2, curBlock);
                } else if (vType.isString()) {
                    v1 = assignConvert(v1, GlobalScope.getCharType().getPointer());
                    v2 = assignConvert(v2, GlobalScope.getCharType().getPointer());
                    res = new CallInst("_string_le", GlobalScope.getCharType().getPointer(),
                            Arrays.asList(v1, v2), curBlock);
                }
                break;
            case GE: {
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                if (vType.isInt()) {
                    v1 = assignConvert(v1, getIntType());
                    v2 = assignConvert(v2, getIntType());
                    res = new CmpInst("sge", v1, v2, curBlock);
                } else if (vType.isString()) {
                    v1 = assignConvert(v1, GlobalScope.getCharType().getPointer());
                    v2 = assignConvert(v2, GlobalScope.getCharType().getPointer());
                    res = new CallInst("_string_ge", GlobalScope.getCharType().getPointer(),
                            Arrays.asList(v1, v2), curBlock);
                }
                break;
            }
            case EQ:
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                if (node.getSrc1().getType().isString() && node.getSrc2().getType().isString()) {
                    v1 = assignConvert(v1, GlobalScope.getCharType().getPointer());
                    v2 = assignConvert(v2, GlobalScope.getCharType().getPointer());
                    res = new CallInst("_string_eq", GlobalScope.getCharType().getPointer(),
                            Arrays.asList(v1, v2), curBlock);
                } else {
                    if (v1.getType().isPointer()) v1 = new LoadInst(v1, curBlock);
                    if (v2.getType().isPointer()) v2 = new LoadInst(v2, curBlock);
                    res = new CmpInst("eq", v1, v2, curBlock);
                }
                break;
            case NE:
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                if (node.getSrc1().getType().isString() && node.getSrc2().getType().isString()) {
                    v1 = assignConvert(v1, GlobalScope.getCharType().getPointer());
                    v2 = assignConvert(v2, GlobalScope.getCharType().getPointer());
                    res = new CallInst("_string_ne", GlobalScope.getCharType().getPointer(),
                            Arrays.asList(v1, v2), curBlock);
                } else {
                    if (v1.getType().isPointer()) v1 = new LoadInst(v1, curBlock);
                    if (v2.getType().isPointer()) v2 = new LoadInst(v2, curBlock);
                    res = new CmpInst("ne", v1, v2, curBlock);
                }
                break;
            case XOR:
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                if (vType.isBoolean()) {
                    v1 = assignConvert(v1, GlobalScope.getBoolType());
                    v2 = assignConvert(v2, GlobalScope.getBoolType());
                } else {
                    v1 = assignConvert(v1, getIntType());
                    v2 = assignConvert(v2, getIntType());
                }
                res = new BinaryOpInst("xor", v1, v2, curBlock);
                break;
            case ANL:
            case ORL:
                BasicBlock circuitSecond = curFunction.add("circuitSecond");
                BasicBlock circuitAfter = curFunction.add("circuitAfter");

                res = new AllocaInst(GlobalScope.getBoolType(), curBlock);
                v1 = assignConvert(v1, GlobalScope.getBoolType());
                new StoreInst(v1, res, curBlock);
                if (node.getOp() == ANL) {
                    new BranchInst(v1, circuitSecond, circuitAfter, curBlock);
                } else {
                    new BranchInst(v1, circuitAfter, circuitSecond, curBlock);
                }

                curBlock = circuitSecond;
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
                v2 = assignConvert(v2, GlobalScope.getBoolType());
                new StoreInst(v2, res, curBlock);
                new JumpInst(circuitAfter, curBlock);

                curBlock = circuitAfter;
                break;
            case ASSIGN:
                System.out.println(v1.getType().IRName());
                node.getSrc2().accept(this);
                v2 = node.getSrc2().getValue();
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
        curClass = node.getClassType();
        module.addClass(node.getClassType());
//        node.getFields().forEach(method -> method.accept(this));
        node.getMethods().forEach(method -> method.accept(this));
        curClass = null;
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
        System.out.println(node.getObject().getValue().getIdentifier());
        System.out.println(node.getObject().getValue().getType().IRName());
        Value nowValue = node.getObject().getValue();
        Type type = node.getObject().getValue().getType();
        if (type.isPointer() && !type.isString()) {
            type = ((PointerType) type).getMember();
        }
        if (type instanceof ArrayType) {
            nowValue = assignConvert(nowValue, GlobalScope.getCharType().getPointer());
            type = nowValue.getType();
        }

        if (type.isString()) {
            node.setValue(nowValue);
        } else if (type.isClass() || (((PointerType) type).getMember()).isClass()) {
            ClassType thisClass = type.isClass() ? (ClassType) type : (ClassType) ((PointerType) type).getMember();
            Type fieldType = thisClass.getFieldType(node.getField());
            if (!thisClass.getIsMethod(node.getField())) {
                Value v = type.isClass() ? nowValue : new LoadInst(nowValue, curBlock);
                GEPInst inst = new GEPInst(v, fieldType.getPointer(), curBlock);
                inst.addOperand(new IntConst(0), new IntConst(thisClass.getFieldIndex(node.getField())));
                node.setValue(inst);
            } else {
                node.setValue(nowValue);
            }
        } else {
            Value p = new LoadInst(nowValue, curBlock);
            p = new BitCastInst(p, getIntType().getPointer(), curBlock);
            p = new GEPInst(p, p.getType(), curBlock) {{addOperand(new IntConst(-1));}};
            node.setValue(new LoadInst(p, curBlock));
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
        curBlock = curFunction.add("initBlock");
        retBlock = curFunction.add("retBlock");

        if (curClass != null) {
            thisValue = new Value("this", curClass.getPointer());
            curFunction.addOperand(thisValue);
        }

        for (int i = 0; i < node.getParams().size(); ++i) {
            Type type = node.getSymbol().getParam().get(i);
            Value p = new Value("p", type.derivesFromClass() ? type.getPointer() : type);
            curFunction.addOperand(p);
            Value al = new AllocaInst(type, curBlock);
            new StoreInst(p, al, curBlock);
            node.getParams().get(i).getVarSymbol().setValue(al);
        }

        if (!curFunction.getType().isVoid()) {
            returnValue = new AllocaInst(curFunction.getType(), curBlock, false);
            Value loadedReturnValue = new LoadInst(returnValue, retBlock);
            new ReturnInst(loadedReturnValue, retBlock);
        } else {
            new ReturnInst(retBlock);
        }

        if (node.getIdentifier().equals("main")) {
            new StoreInst(new IntConst(0), returnValue, curBlock);
            new CallInst("_init", GlobalScope.getVoidType(), new ArrayList<>(), curBlock);
        }

        node.getStmt().accept(this);

        List<BasicBlock> blocks = curFunction.getBasicBlockList();

        for (int i = 1; i < blocks.size(); i++) {
            BasicBlock block = blocks.get(i);
            for (Instruction instruction : block.getInstructionList()) {
                if (instruction instanceof AllocaInst) {
                    blocks.get(0).addFront(instruction);
                }
            }
            block.getInstructionList().removeIf(inst -> inst instanceof AllocaInst);
        }

        for (BasicBlock block : blocks) {
            if (!block.isTerminated()) {
                new JumpInst(retBlock, block);
            }
        }

        curFunction = null;
    }

    @Override
    public void visit(FuncCallExprNode node) {
        node.getFunction().accept(this);
        List<Value> arguments = new ArrayList<>();
        List<Type> paramList = node.getFunction().getFunctionSymbol().getParam();

        if (node.getFunction().getValue() != null) {
            if (node.getFunction().getValue().getType().isInt()) {
                node.setValue(node.getFunction().getValue());
                return;
            }
            Type type = node.getFunction().getValue().getType();
            if (type.isString()) {
                type = GlobalScope.getCharType();
            } else {
                type = ((PointerType) type).getBaseType();
                if (type.isString()) type = GlobalScope.getCharType();
            }
            arguments.add(assignConvert(node.getFunction().getValue(), type.getPointer()));
        }
        else if (node.getFunction().getFunctionSymbol().inClass()) {
            arguments.add(thisValue);
        }

        for (int i = 0; i < node.getArguments().size(); ++i) {
            Expr t = node.getArguments().get(i);
            t.accept(this);
            Type type = paramList.get(i);
            arguments.add(assignConvert(t.getValue(), type.derivesFromClass() ? type.getPointer() : type));
        }
        node.setValue(new CallInst(node.getFunction().getFunctionSymbol(), arguments, curBlock));
    }

    @Override
    public void visit(IdentifierListNode node) {}

    @Override
    public void visit(IntLiteralNode node) {
        node.setValue(new IntConst(node.getLiteral()));
    }

    private Value allocate(PointerType type, List<Value> shape, int dimIndex) {
        Value size = new BinaryOpInst("mul", new IntConst(type.getMember().getByteNum()), shape.get(dimIndex), curBlock);
        size = new BinaryOpInst("add", size, new IntConst(getIntType().getByteNum()), curBlock);
        size = new SextInst(size, GlobalScope.getI64Type(), curBlock);
        Value pointer = new CallInst("malloc", GlobalScope.getCharType().getPointer(),
                Collections.singletonList(size), curBlock);
        pointer = new BitCastInst(pointer, getIntType().getPointer(), curBlock);
        new StoreInst(shape.get(dimIndex), pointer, curBlock);
        pointer = new GEPInst(pointer, getIntType().getPointer(), curBlock) {{
            addOperand(new IntConst(1));
        }};
        pointer = new BitCastInst(pointer, type, curBlock);

        if (dimIndex < shape.size() - 1) {
            Value i = new AllocaInst(getIntType(), curBlock);
            new StoreInst(new IntConst(0), i, curBlock);

            BasicBlock forCond = curFunction.add("forCond");
            BasicBlock forBody = curFunction.add("forBody");
            BasicBlock forAfter = curFunction.add("forAfter");

            new JumpInst(forCond, curBlock);

            curBlock = forCond;

            Value condValue = new CmpInst("slt", new LoadInst(i, curBlock), shape.get(dimIndex), curBlock);
            new BranchInst(condValue, forBody, forAfter, curBlock);

            curBlock = forBody;
            Value index = assignConvert(i, GlobalScope.getIntType());
            Value pos = new GEPInst(pointer, type, curBlock) {{addOperand(index);}};
            new StoreInst(allocate((PointerType) type.getMember(), shape, dimIndex + 1), pos, curBlock);
            Value nextI = new BinaryOpInst("add", assignConvert(i, getIntType()),
                    new IntConst(1), curBlock);
            new StoreInst(nextI, i, curBlock);
            new JumpInst(forCond, curBlock);

            curBlock = forAfter;
        }

        return pointer;
    }

    @Override
    public void visit(NewExprNode node) {
        Type type = globalScope.getType(node.getNewType());
        List<Value> shape = new ArrayList<>();
        for (Expr i : node.getShape()) {
            i.accept(this);
            shape.add(assignConvert(i.getValue(), getIntType()));
        }
        if (!type.isPointer()) {
            Value size = new IntConst(type.getByteNum());
            size = new SextInst(size, GlobalScope.getI64Type(), curBlock);
            Value pointer = new CallInst("malloc", GlobalScope.getCharType().getPointer(),
                    Collections.singletonList(size), curBlock);
            pointer = new BitCastInst(pointer, type.getPointer(), curBlock);
            node.setValue(pointer);

            FunctionSymbol constructor = ((ClassType) type).getConstructor();
            if (constructor != null) {
                new CallInst(constructor, Collections.singletonList(pointer), curBlock);
            }
        } else {
            if (type.derivesFromClass()) type = type.getPointer();
            node.setValue(allocate((PointerType) type, shape, 0));
        }
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
        if (node.getExpression() != null) {
            node.getExpression().accept(this);
            new StoreInst(assignConvert(node.getExpression().getValue(),
                    ((PointerType) returnValue.getType()).getMember()), returnValue, curBlock);
        }
        new JumpInst(retBlock, curBlock);
    }

    @Override
    public void visit(SelectionStmtNode node) {
        BasicBlock ifTaken = curFunction.add("ifTaken");
        BasicBlock ifNotTaken = node.getNotTaken() == null? null : curFunction.add("ifNotTaken");
        BasicBlock ifAfter = curFunction.add("ifAfter");

        node.getCond().accept(this);
        Value cond = assignConvert(node.getCond().getValue(), GlobalScope.getBoolType());

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

        curBlock = ifAfter;
    }

    @Override
    public void visit(StringLiteralNode node) {
        String s = node.getStringLiteral();
        s = s.substring(1, s.length() - 1);

        s = s.replace("\\\\", "\\");
        s = s.replace("\\n", "\n");
        s = s.replace("\\\"", "\"");

        int length = s.length();

        s = s.replace("\\", "\\5C");
        s = s.replace("\n", "\\0A");
        s = s.replace("\"", "\\22");

        GlobalVariable res = stringLiteralMap.get(s);
        if (res == null) {
            res = new StringConst(s, length);
            stringLiteralMap.put(s, res);
            module.addGlobalVariable(res);
        }
        node.setValue(res);
    }

    @Override
    public void visit(SubscriptExprNode node) {
        node.getArray().accept(this);
        node.getSubscript().accept(this);
//        Value array = new LoadInst(node.getArray().getValue(), curBlock);
        Value array = assignConvert(node.getArray().getValue(), node.getType().getPointer().getPointer());
        Value num = assignConvert(node.getSubscript().getValue(), getIntType());
        GEPInst inst = new GEPInst(array, array.getType(), curBlock);
        inst.addOperand(num);
        node.setValue(inst);
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
                v = new BinaryOpInst("add", assignConvert(src, getIntType()),
                        new IntConst(1), curBlock);
                new StoreInst(v, src, curBlock);
                res = src;
                break;
            case PRE_DEC:
                v = new BinaryOpInst("sub", assignConvert(src, getIntType()),
                        new IntConst(1), curBlock);
                new StoreInst(v, src, curBlock);
                res = src;
                break;
            case POST_INC:
                v = assignConvert(src, getIntType());
                new StoreInst(new BinaryOpInst("add", v, new IntConst(1), curBlock), src, curBlock);
                res = v;
                break;
            case POST_DEC:
                v = assignConvert(src, getIntType());
                new StoreInst(new BinaryOpInst("sub", v, new IntConst(1), curBlock), src, curBlock);
                res = v;
                break;
            case POS:
                res = src;
                break;
            case NEG:
                res = new BinaryOpInst("sub", new IntConst(0),
                        assignConvert(src, getIntType()), curBlock);
                break;
            case NOT:
                res = new BinaryOpInst("xor", assignConvert(src, getIntType()),
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
                module.addGlobalVariable(np);
            } else {
                np = new AllocaInst(globalScope.getType(node.getType()), curBlock);
            }
            node.getVarSymbol().get(i).setValue(np);
            if (node.ifInitValue()) {
                node.getInitValue().accept(this);
                Value v = assignConvert(node.getInitValue().getValue(), ((PointerType) np.getType()).getMember());
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
        if (node.getVarSymbol() == null)
            node.setValue(null);
        else if(node.getVarSymbol().getValue() == null) {
            Type fieldType = curClass.getFieldType(node.getIdentifier());
            GEPInst inst = new GEPInst(thisValue, fieldType.getPointer(), curBlock);
            inst.addOperand(new IntConst(0), new IntConst(curClass.getFieldIndex(node.getIdentifier())));
            node.setValue(inst);
        } else
            node.setValue(node.getVarSymbol().getValue());
    }

    @Override
    public void visit(WhileStmtNode node) {
        BasicBlock whileCond = curFunction.add("whileCond");
        BasicBlock whileBody = curFunction.add("whileBody");
        BasicBlock whileAfter = curFunction.add("whileAfter");

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
