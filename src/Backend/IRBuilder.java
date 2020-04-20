package Backend;

import AST.*;
import IR.*;
import IR.Constant.Function;
import IR.Instruction.BranchInst;
import IR.Module;
import Symbol.GlobalScope;

public class IRBuilder implements ASTVisitor {
    Module module;
    GlobalScope globalScope;
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
    }

    @Override
    public void visit(BoolExprNode node) {

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

    }

    @Override
    public void visit(ConditionalExprNode node) {
//        Suddenly found that there is no such Expr
//        node.getCondition().accept(this);
//        node.getOpt1().accept(this);
//        node.getOpt2().accept(this);
    }

    @Override
    public void visit(ContinueNode node) {

    }

    @Override
    public void visit(EmptyStmtNode node) {

    }

    @Override
    public void visit(ExprStmtNode node) {

    }

    @Override
    public void visit(FieldExprNode node) {

    }

    @Override
    public void visit(ForStmtNode node) {

    }


    @Override
    public void visit(FuncDeclNode node) {

    }
    @Override
    public void visit(FuncCallExprNode node) {

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

    }

    @Override
    public void visit(ParamDeclNode node) {

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
