package Frontend;

import AST.*;
import Parser.MxstarBaseVisitor;
import Parser.MxstarParser;
import Utils.BinaryOp;
import Utils.Position;
import Utils.UnaryOp;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ASTBuilder extends MxstarBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitProgram(MxstarParser.ProgramContext ctx) {
        List<ProgramFragment> fragmentList = new ArrayList<>();

        for (ParserRuleContext fragment : ctx.programFragment()) {
            fragmentList.add((ProgramFragment) visit(fragment));
        }
        return new ProgramNode(fragmentList, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitProgramFragment(MxstarParser.ProgramFragmentContext ctx) {
        ASTNode returnValue = null;
        if (ctx.functionDeclaration() != null)
            returnValue = visit(ctx.functionDeclaration());
        if (ctx.classDeclaration() != null)
            returnValue = visit(ctx.classDeclaration());
        if (ctx.varDeclaration() != null)
            returnValue = visit(ctx.varDeclaration());

        return returnValue;
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
        CompoundStmtNode stmt = (CompoundStmtNode) visit(ctx.compoundStatement());
        return new FuncDeclNode(true, null, identifier, new ArrayList<>(), stmt, new Position(ctx.getStart()));
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

        List<ParamDeclNode> params;
        ParserRuleContext paramList = ctx.declarator().parameterDeclarationList();
        if (paramList != null)
            params = ((ParamDeclList) visit(paramList)).getList();
        else {
            params = new ArrayList<>();
        }

        CompoundStmtNode stmt = (CompoundStmtNode) visit(ctx.compoundStatement());
        return new FuncDeclNode(false, type, identifier, params, stmt, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitFuncType(MxstarParser.FuncTypeContext ctx) {
        if (ctx.Void() == null) return visit(ctx.type());
        else return new TypeNode("void", 0, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitDeclarator(MxstarParser.DeclaratorContext ctx) {
        throw new RuntimeException();
    }

    @Override
    public ASTNode visitParameterDeclarationList(MxstarParser.ParameterDeclarationListContext ctx) {
        ParamDeclNode decl = (ParamDeclNode) visit(ctx.parameterDeclaration());
        List<ParamDeclNode> list;
        if (ctx.parameterDeclarationList() == null) {
            list = new ArrayList<>();
        }
        else {
            ParamDeclList subList = (ParamDeclList) visit(ctx.parameterDeclarationList());
            list = subList.getList();
        }

        list.add(decl);
        return new ParamDeclList(list, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitParameterDeclaration(MxstarParser.ParameterDeclarationContext ctx) {
        TypeNode type = (TypeNode) visit(ctx.type());
        String identifier = ctx.Identifier().getText();

        return new ParamDeclNode(type, identifier, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitCompoundStatement(MxstarParser.CompoundStatementContext ctx) {
        List<Stmt> stmtList = new ArrayList<>();
        for (ParserRuleContext statement : ctx.statement()) {
            stmtList.add((Stmt) visit(statement));
        }
        return new CompoundStmtNode(stmtList, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitCompoundStmt(MxstarParser.CompoundStmtContext ctx) {
        return visit(ctx.compoundStatement());
    }

    @Override
    public ASTNode visitVarDeclStmt(MxstarParser.VarDeclStmtContext ctx) {
        return new VarDeclStmtNode((VarDeclNode) visit(ctx.varDeclaration()), new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitExprStmt(MxstarParser.ExprStmtContext ctx) {
        return visit(ctx.expressionStatement());
    }

    @Override
    public ASTNode visitSelectionStmt(MxstarParser.SelectionStmtContext ctx) {
        return visit(ctx.selectionStatement());
    }

    @Override
    public ASTNode visitIterationStmt(MxstarParser.IterationStmtContext ctx) {
        return visit(ctx.iterationStatement());
    }

    @Override
    public ASTNode visitJumpStmt(MxstarParser.JumpStmtContext ctx) {
        return visit(ctx.jumpStatement());
    }

    @Override
    public ASTNode visitEmptyStmt(MxstarParser.EmptyStmtContext ctx) {
        return new EmptyStmtNode(new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitExpressionStatement(MxstarParser.ExpressionStatementContext ctx) {
        Expr expression = (Expr) visit(ctx.expression());
        return new ExprStmtNode(expression, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitSelectionStatement(MxstarParser.SelectionStatementContext ctx) {
        Expr cond = (Expr) visit(ctx.expression());
        Stmt[] branch = new Stmt[2];
        branch[0] = (Stmt) visit(ctx.opt1);
        if (ctx.opt2 != null) {
            branch[1] = (Stmt) visit(ctx.opt2);
        } else {
            branch[1] = new EmptyStmtNode(new Position(ctx.getStart()));
        }
        return new SelectionStmtNode(cond, branch, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitWhileStmt(MxstarParser.WhileStmtContext ctx) {
        Expr condition = (Expr) visit(ctx.expression());
        Stmt statement = (Stmt) visit(ctx.compoundStatement());
        return new WhileStmtNode(condition, statement, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitForStmt(MxstarParser.ForStmtContext ctx) {
        Expr init = (Expr) visit(ctx.forCondition().init);
        Expr cond = (Expr) visit(ctx.forCondition().cond);
        Expr step = (Expr) visit(ctx.forCondition().step);
        Stmt statement = (Stmt) visit(ctx.compoundStatement());

        return new ForStmtNode(init, cond, step, statement, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitForCondition(MxstarParser.ForConditionContext ctx) {
        throw new RuntimeException("visitForCondition");
    }

    @Override
    public ASTNode visitReturnStmt(MxstarParser.ReturnStmtContext ctx) {
        Expr expression = null;
        if (ctx.expression() != null)
            expression = (Expr) visit(ctx.expression());
        return new ReturnNode(expression, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitBreakStmt(MxstarParser.BreakStmtContext ctx) {
        return new BreakNode(new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitContinueStmt(MxstarParser.ContinueStmtContext ctx) {
        return new ContinueNode(new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitConstExpr(MxstarParser.ConstExprContext ctx) {
        return visit(ctx.constantExpression());
    }

    @Override
    public ASTNode visitThisExpr(MxstarParser.ThisExprContext ctx) {
        return new ThisNode(new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitVarExpr(MxstarParser.VarExprContext ctx) {
        return new VarExprNode(ctx.Identifier().getText(), new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitBoolExpr(MxstarParser.BoolExprContext ctx) {
        boolean value;
        value = ctx.True() != null;
        return new BoolExprNode(value, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitNullExpr(MxstarParser.NullExprContext ctx) {
        return new NullNode(new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitIntegerLiteral(MxstarParser.IntegerLiteralContext ctx) {
        Integer value = Integer.valueOf(ctx.IntegerLiteral().getText());
        return new IntLiteralNode(value, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitStringLiteral(MxstarParser.StringLiteralContext ctx) {
        String value = ctx.StringLiteral().getText();
        return new StringLiteralNode(value, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitSubscriptExpr(MxstarParser.SubscriptExprContext ctx) {
        Expr array = (Expr) visit(ctx.src1);
        Expr subscript = (Expr) visit(ctx.src2);
        return new SubscriptExprNode(array, subscript, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitBinaryExpr(MxstarParser.BinaryExprContext ctx) {
        Expr src1 = (Expr) visit(ctx.src1);
        Expr src2 = (Expr) visit(ctx.src2);
        BinaryOp op = null;
        switch (ctx.op.getText()) {
            case "*":
                op = BinaryOp.MUL;
                break;
            case "/":
                op = BinaryOp.DIV;
                break;
            case "%":
                op = BinaryOp.MOD;
                break;
            case "+":
                op = BinaryOp.ADD;
                break;
            case "-":
                op = BinaryOp.SUB;
                break;
            case "<<":
                op = BinaryOp.SAL;
                break;
            case ">>":
                op = BinaryOp.SAR;
                break;
            case "<":
                op = BinaryOp.LT;
                break;
            case ">":
                op = BinaryOp.GT;
                break;
            case "<=":
                op = BinaryOp.LE;
                break;
            case ">=":
                op = BinaryOp.GE;
                break;
            case "==":
                op = BinaryOp.EQ;
                break;
            case "!=":
                op = BinaryOp.NE;
                break;
            case "&":
                op = BinaryOp.AND;
                break;
            case "^":
                op = BinaryOp.XOR;
                break;
            case "|":
                op = BinaryOp.OR;
                break;
            case "&&":
                op = BinaryOp.ANL;
                break;
            case "||":
                op = BinaryOp.ORL;
                break;
            case "=":
                op = BinaryOp.ASSIGN;
                break;
        }
        return new BinaryExprNode(src1, src2, op, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitNewExpr(MxstarParser.NewExprContext ctx) {
        MxstarParser.NewSpecifierContext sp = ctx.newSpecifier();
        TypeNode type = (TypeNode) visit(sp.primaryType());
        int dim = (sp.getChildCount() - sp.expression().size() - 1) / 2;
        type.setDim(dim);

        List<Expr> shape = new ArrayList<>();
        for (ParserRuleContext expr : sp.expression()) {
            shape.add((Expr) visit(expr));
        }

        return new NewExprNode(type, shape, new Position(ctx.getStart()));

    }

    @Override
    public ASTNode visitPrimaryExpr(MxstarParser.PrimaryExprContext ctx) {
        return visit(ctx.primaryExpression());
    }

    @Override
    public ASTNode visitParenthesesExpr(MxstarParser.ParenthesesExprContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public ASTNode visitFieldExpr(MxstarParser.FieldExprContext ctx) {
        Expr object = (Expr) visit(ctx.expression());
        String field = ctx.Identifier().getText();

        return new FieldExprNode(object, field, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitConditionalExpr(MxstarParser.ConditionalExprContext ctx) {
        Expr condition = (Expr) visit(ctx.src1);
        Expr opt1 = (Expr) visit(ctx.src2);
        Expr opt2 = (Expr) visit(ctx.src3);

        return new ConditionalExprNode(condition, opt1, opt2, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitUnaryExpr(MxstarParser.UnaryExprContext ctx) {
        Expr expr = (Expr) visit(ctx.expression());
        UnaryOp op = null;
        switch (ctx.op.getText()) {
            case "++":
                op = UnaryOp.PRE_INC;
                break;
            case "--":
                op = UnaryOp.PRE_DEC;
                break;
            case "+":
                op = UnaryOp.POS;
                break;
            case "-":
                op = UnaryOp.NEG;
                break;
            case "~":
                op = UnaryOp.NOT;
                break;
            case "!":
                op = UnaryOp.NOTL;
                break;
        }
        return new UnaryExprNode(expr, op, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitPostfixExpr(MxstarParser.PostfixExprContext ctx) {
        Expr expr = (Expr) visit(ctx.expression());
        UnaryOp op = null;
        switch (ctx.op.getText()) {
            case "++":
                op = UnaryOp.POST_INC;
                break;
            case "--":
                op = UnaryOp.POST_DEC;
                break;
        }
        return new UnaryExprNode(expr, op, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitFuncCallExpr(MxstarParser.FuncCallExprContext ctx) {
        Expr func = (Expr) visit(ctx.expression());
        List<Expr> param;
        if (ctx.parameterList() != null) {
            param = ((ParamList) visit(ctx.parameterList())).getList();
        }
        else {
            param = new ArrayList<>();
        }
        return new FuncCallExprNode(func, param, new Position(ctx.getStart()));
    }

    @Override
    public ASTNode visitParameterList(MxstarParser.ParameterListContext ctx) {
        Expr expr = (Expr) visit(ctx.expression());
        List<Expr> list;
        if (ctx.parameterList() == null) {
            list = new ArrayList<>();
        }
        else {
            ParamList subList = (ParamList) visit(ctx.parameterList());
            list = subList.getList();
        }

        list.add(expr);
        return new ParamList(list, new Position(ctx.getStart()));
    }

//    @Override
//    public ASTNode visitUnaryOperator(MxstarParser.UnaryOperatorContext ctx) {
//        return visitChildren(ctx);
//    }

    @Override
    public ASTNode visitNewSpecifier(MxstarParser.NewSpecifierContext ctx) {
        throw new RuntimeException();
    }

    @Override
    public ASTNode visitType(MxstarParser.TypeContext ctx) {
        if (ctx.type() != null) {
            TypeNode ret = (TypeNode) visit(ctx.type());
            ret.setDim(ret.getDim() + 1);
            return ret;
        }
        else {
            return visit(ctx.primaryType());
        }
    }

    @Override
    public ASTNode visitPrimaryType(MxstarParser.PrimaryTypeContext ctx) {
        String identifier = null;
        if (ctx.Bool() != null) identifier = "bool";
        if (ctx.Int() != null) identifier = "int";
        if (ctx.String() != null) identifier = "String";
        if (ctx.Identifier() != null) identifier = ctx.Identifier().getText();

        return new TypeNode(identifier, 0, new Position(ctx.getStart()));
    }
}
