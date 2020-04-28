package Backend;

import AST.*;
import IR.*;
import IR.Constant.BoolConst;
import IR.Constant.Function;
import IR.Constant.IntConst;
import IR.Instruction.BranchInst;
import IR.Instruction.CallInst;
import IR.Instruction.FieldInst;
import IR.Module;
import Symbol.ClassType;
import Symbol.GlobalScope;
import Symbol.Type;

public class IRBuilder implements ASTVisitor {
    Module module;
    GlobalScope globalScope;
    ClassType curClass;
    Function curFunction;
    BasicBlock curBlock;
    boolean justScan;

    public IRBuilder(GlobalScope globalScope) {
        this.globalScope = globalScope;
    }

    public Module getModule() {
        return module;
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
        if
        new FieldInst(node.getObject().getValue(), fieldType, thisClass.getFieldIndex(node.getField()), curBlock);
    }

    @Override
    public void visit(ForStmtNode node) {

    }


    @Override
    public void visit(FuncDeclNode node) {
        String identifier = curClass == null ? node.getIdentifier()
                : curClass.getIdentifier() + "." + node.getIdentifier();
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
