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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static Symbol.GlobalScope.getIntType;

public class IRBuilder implements ASTVisitor {
    Module module;
    GlobalScope globalScope;
    ClassType curClass;
    Function curFunction;
    BasicBlock curBlock, breakBlock, continueBlock, retBlock;
    Value returnValue;
    boolean justScan;

    Map<String, GlobalVariable> stringLiteralMap = new LinkedHashMap<>;

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
        justScan = true;
        for (ProgramFragment i : node.getList()) {
            i.accept(this);
        }
        justScan = false;
        for (ProgramFragment i : node.getList()) {
            i.accept(this);
        }
    }

    @Override
    public void visit(BinaryExprNode node) {
        node.getSrc1().accept(this);
        node.getSrc2().accept(this);
        Value v1 = node.getSrc1().getValue();
        Value v2 = node.getSrc2().getValue();
        switch (node.getOp()) {
//            case MUL:
//                new OpInst;
        }
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
        ClassType thisClass = (ClassType) node.getObject().getValue().getType();
        Type fieldType = thisClass.getFieldType(node.getField());
        if (!thisClass.getIsMethod(node.getField())) {
//            node.setValue(new Function(convertFuncName(node.getField()), fieldType));
//        }
//        else{
            node.setValue(new FieldInst(node.getObject().getValue(), fieldType, thisClass.getFieldIndex(node.getField()), curBlock));
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
        continueBlock = forBody;
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
        for (int i = 0; i < node.getParams().size(); ++i) {
            curFunction.addOperand(new Value("p", node.getSymbol().getParam().get(i)));
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

    }

    @Override
    public void visit(ThisNode node) {

    }

    @Override
    public void visit(TypeNode node) {

    }

    @Override
    public void visit(UnaryExprNode node) {

    }

    @Override
    public void visit(VarDeclNode node) {

    }

    @Override
    public void visit(VarDeclStmtNode node) {

    }

    @Override
    public void visit(VarExprNode node) {

    }

    @Override
    public void visit(WhileStmtNode node) {

    }
}
