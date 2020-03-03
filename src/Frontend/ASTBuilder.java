package Frontend;

import AST.*;
import Parser.MxstarBaseVisitor;
import Parser.MxstarParser;
import Utils.Position;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class ASTBuilder extends MxstarBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitProgram(MxstarParser.ProgramContext ctx) {
        List<ProgramFragment> fragmentList = new ArrayList<>();

        for (ParserRuleContext fragment : ctx.programFraction()) {
            fragmentList.add((ProgramFragment) visit(fragment));
        }

        return new ProgramNode(fragmentList, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitProgramFraction(MxstarParser.ProgramFractionContext ctx) {
        if (ctx.functionDeclaration() != null) return visit(ctx.functionDeclaration());
        if (ctx.classDeclaration() != null) return visit(ctx.classDeclaration());
        if (ctx.varDeclaration() != null) return visit(ctx.varDeclaration());
    }

    @Override
    public ASTNode visitClassDeclaration(MxstarParser.ClassDeclarationContext ctx) {
        String identifier = ctx.Identifier().getText();
        List<VarDeclNode> fields = new ArrayList<>();
        List<FuncDeclNode> methods = new ArrayList<>();

        for (ParserRuleContext decl : ctx.fieldDeclaration()) {
            fields.add((VarDeclNode) visit(decl));
        }
        for (ParserRuleContext decl : ctx.functionDeclaration()) {
            methods.add((FuncDeclNode) visit(decl));
        }
        for (ParserRuleContext decl : ctx.constructorDeclaration()) {
            methods.add((FuncDeclNode) visit(decl));
        }

        return new ClassDeclNode(identifier, fields, methods, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitFieldDeclaration(MxstarParser.FieldDeclarationContext ctx) {
        TypeNode type = (TypeNode) visit(ctx.type());
        List<String> variables = new ArrayList<>();

        for (TerminalNode identifier : ctx.identifierList().Identifier()) {
            variables.add(identifier.getText());
        }

        return new VarDeclNode(type, variables, false, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitConstructorDeclaration(MxstarParser.ConstructorDeclarationContext ctx) {
        String identifier = ctx.Identifier().getText();
        return new FuncDeclNode(true, null, identifier,null, visit(ctx.compoundStatement()),
                                new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitVarDeclaration(MxstarParser.VarDeclarationContext ctx) {
        TypeNode type = (TypeNode) visit(ctx.type());
        List<String> variables = new ArrayList<>();

        if (ctx.identifierList() == null) {
            variables.add(ctx.Identifier().getText());
            Expr expression = (Expr) visit(ctx.expression());
            return new VarDeclNode(type, variables, true, expression, new Position(ctx.getStart()));
        }
        else {
            for (TerminalNode identifier : ctx.identifierList().Identifier()) {
                variables.add(identifier.getText());
            }
            return new VarDeclNode(type, variables, false, new Position(ctx.getStart()));
        }
    }

    @Override
    public ASTNode visitIdentifierList(MxstarParser.IdentifierListContext ctx) {
        throw new RuntimeException("visitIdentifierList");
    }

    @Override
    public ASTNode visitFunctionDeclaration(MxstarParser.FunctionDeclarationContext ctx) {
        TypeNode type = (TypeNode) visit(ctx.funcType());
        String identifier = ctx.declarator().Identifier().getText();
        List<ParamDeclNode> params = ((ParamDeclList) visit(ctx.declarator().parameterDeclarationList())).getList();
        CompoundStmtNode stmt = (CompoundStmtNode) visit(ctx.compoundStatement());
        return new FuncDeclNode(false, type, identifier, params, stmt, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitFuncType(MxstarParser.FuncTypeContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitDeclarator(MxstarParser.DeclaratorContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitParameterDeclarationList(MxstarParser.ParameterDeclarationListContext ctx) {
        if (ctx.parameterDeclarationList() != null) {

        }
        else {

        }
    }

    @Override
    public ASTNode visitParameterDeclaration(MxstarParser.ParameterDeclarationContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitCompoundStatement(MxstarParser.CompoundStatementContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitCompoundStmt(MxstarParser.CompoundStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitVarDeclStmt(MxstarParser.VarDeclStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitExprStmt(MxstarParser.ExprStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitSelectionStmt(MxstarParser.SelectionStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitIterationStmt(MxstarParser.IterationStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitJumpStmt(MxstarParser.JumpStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitEmptyStmt(MxstarParser.EmptyStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitExpressionStatement(MxstarParser.ExpressionStatementContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitSelectionStatement(MxstarParser.SelectionStatementContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitWhileStmt(MxstarParser.WhileStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitForStmt(MxstarParser.ForStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitForCondition(MxstarParser.ForConditionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitReturnStmt(MxstarParser.ReturnStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitBreakStmt(MxstarParser.BreakStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitContinueStmt(MxstarParser.ContinueStmtContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitConstExpr(MxstarParser.ConstExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitThisExpr(MxstarParser.ThisExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitVarExpr(MxstarParser.VarExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitBoolExpr(MxstarParser.BoolExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitNullExpr(MxstarParser.NullExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitIntegerLiteral(MxstarParser.IntegerLiteralContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitStringLiteral(MxstarParser.StringLiteralContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitSubscriptExpr(MxstarParser.SubscriptExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitBinaryExpr(MxstarParser.BinaryExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitNewExpr(MxstarParser.NewExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitPrimaryExpr(MxstarParser.PrimaryExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitParenthesesExpr(MxstarParser.ParenthesesExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitFieldExpr(MxstarParser.FieldExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitConditionalExpr(MxstarParser.ConditionalExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitUnaryExpr(MxstarParser.UnaryExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitFuncCallExpr(MxstarParser.FuncCallExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitParameterList(MxstarParser.ParameterListContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitUnaryOperator(MxstarParser.UnaryOperatorContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitNewSpecifier(MxstarParser.NewSpecifierContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitType(MxstarParser.TypeContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitPrimaryType(MxstarParser.PrimaryTypeContext ctx) {
        return visitChildren(ctx);
    }

}
