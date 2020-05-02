package Backend;

import AST.*;
import IR.*;
import IR.Constant.BoolConst;
import IR.Constant.Function;
import IR.Constant.IntConst;
import IR.Instruction.BranchInst;
import IR.Instruction.CallInst;
import IR.Instruction.FieldInst;
import IR.Instruction.JumpInst;
import IR.Module;
import Symbol.ClassType;
import Symbol.GlobalScope;
import Symbol.Type;

public class IRBuilder implements ASTVisitor {
    Module module;
    GlobalScope globalScope;
    ClassType curClass;
    Function curFunction;
    BasicBlock curBlock, breakBlock, continueBlock;
    boolean justScan;

    public IRBuilder(GlobalScope globalScope) {
        this.globalScope = globalScope;
    }

    public Module getModule() {
        return module;
    }

    public String convertFuncName(String identifier) {
        return curClass == null ? identifier : curClass.getIdentifier() + "." + identifier;
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
            case MUL:
                new OpInst;
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
    public void visit(EmptyStmtNode node) {

    }

    @Override
    public void visit(ExprStmtNode node) {
        node.getExpression().accept(this);
    }

    @Override
    public void visit(FieldExprNode node) {
        node.getObject().accept(this);
        ClassType thisClass = (ClassType) node.getObject().getValue().getType();
        Type fieldType = thisClass.getFieldType(node.getField());
        if (thisClass.getIsMethod(node.getField())) {
            node.setValue(new Function(convertFuncName(node.getField()), fieldType));
        }
        else{
            node.setValue(new FieldInst(node.getObject().getValue(), fieldType, thisClass.getFieldIndex(node.getField()), curBlock));
        }
    }

    @Override
    public void visit(ForStmtNode node) {
        BasicBlock forCond = curFunction.add("forCond");
        BasicBlock forStep = curFunction.add("forStep");
        BasicBlock forBody = curFunction.add("forBody");

        node.getInit().accept(this);
        new JumpInst(forCond, curBlock);
        curBlock = forCond;
        node.getCondition().accept(this);
        curBlock = forStep;
        node.getStep().accept(this);
        new JumpInst(forCond, curBlock);

    }


    @Override
    public void visit(FuncDeclNode node) {
        String identifier = convertFuncName(node.getIdentifier());
        curFunction = new Function(identifier, node.getSymbol().getType());
        module.addFunction(curFunction);
        for (int i = 0; i < node.getParams().size(); ++i) {
            curFunction.addOperand(new Value("p", node.getSymbol().getParam().get(i)));
        }
        node.getStmt().accept(this);
    }

    @Override
    public void visit(FuncCallExprNode node) {
        node.getFunction().accept(this);
        new CallInst((Function) node.getFunction().getValue(), curBlock);
    }

    @Override
    public void visit(IdentifierListNode node) {

    }

    @Override
    public void visit(IntLiteralNode node) {

    }

    @Override
    public void visit(NewExprNode node) {

    }

    @Override
    public void visit(NullNode node) {

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

    }

    @Override
    public void visit(ReturnNode node) {

    }

    @Override
    public void visit(SelectionStmtNode node) {

    }

    @Override
    public void visit(StringLiteralNode node) {

    }

    @Override
    public void visit(SubscriptExprNode node) {

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
