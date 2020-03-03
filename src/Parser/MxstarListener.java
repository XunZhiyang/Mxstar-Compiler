// Generated from Mxstar.g4 by ANTLR 4.8

package Parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxstarParser}.
 */
public interface MxstarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#programFragment}.
	 * @param ctx the parse tree
	 */
	void enterProgramFragment(MxstarParser.ProgramFragmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#programFragment}.
	 * @param ctx the parse tree
	 */
	void exitProgramFragment(MxstarParser.ProgramFragmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(MxstarParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(MxstarParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(MxstarParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(MxstarParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaration(MxstarParser.ConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaration(MxstarParser.ConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(MxstarParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(MxstarParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierList(MxstarParser.IdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierList(MxstarParser.IdentifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(MxstarParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(MxstarParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#funcType}.
	 * @param ctx the parse tree
	 */
	void enterFuncType(MxstarParser.FuncTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#funcType}.
	 * @param ctx the parse tree
	 */
	void exitFuncType(MxstarParser.FuncTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#declarator}.
	 * @param ctx the parse tree
	 */
	void enterDeclarator(MxstarParser.DeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#declarator}.
	 * @param ctx the parse tree
	 */
	void exitDeclarator(MxstarParser.DeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#parameterDeclarationList}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclarationList(MxstarParser.ParameterDeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#parameterDeclarationList}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclarationList(MxstarParser.ParameterDeclarationListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration(MxstarParser.ParameterDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration(MxstarParser.ParameterDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(MxstarParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(MxstarParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CompoundStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStmt(MxstarParser.CompoundStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CompoundStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStmt(MxstarParser.CompoundStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarDeclStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclStmt(MxstarParser.VarDeclStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarDeclStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclStmt(MxstarParser.VarDeclStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(MxstarParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(MxstarParser.ExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SelectionStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStmt(MxstarParser.SelectionStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SelectionStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStmt(MxstarParser.SelectionStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IterationStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStmt(MxstarParser.IterationStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IterationStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStmt(MxstarParser.IterationStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JumpStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStmt(MxstarParser.JumpStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JumpStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStmt(MxstarParser.JumpStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EmptyStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStmt(MxstarParser.EmptyStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EmptyStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStmt(MxstarParser.EmptyStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(MxstarParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(MxstarParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(MxstarParser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(MxstarParser.SelectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link MxstarParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(MxstarParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link MxstarParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(MxstarParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link MxstarParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(MxstarParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link MxstarParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(MxstarParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#forCondition}.
	 * @param ctx the parse tree
	 */
	void enterForCondition(MxstarParser.ForConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#forCondition}.
	 * @param ctx the parse tree
	 */
	void exitForCondition(MxstarParser.ForConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(MxstarParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(MxstarParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(MxstarParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(MxstarParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(MxstarParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(MxstarParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstExpr}
	 * labeled alternative in {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterConstExpr(MxstarParser.ConstExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstExpr}
	 * labeled alternative in {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitConstExpr(MxstarParser.ConstExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ThisExpr}
	 * labeled alternative in {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterThisExpr(MxstarParser.ThisExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ThisExpr}
	 * labeled alternative in {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitThisExpr(MxstarParser.ThisExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterVarExpr(MxstarParser.VarExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitVarExpr(MxstarParser.VarExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolExpr}
	 * labeled alternative in {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(MxstarParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolExpr}
	 * labeled alternative in {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(MxstarParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NullExpr}
	 * labeled alternative in {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterNullExpr(MxstarParser.NullExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NullExpr}
	 * labeled alternative in {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitNullExpr(MxstarParser.NullExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntegerLiteral}
	 * labeled alternative in {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(MxstarParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntegerLiteral}
	 * labeled alternative in {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(MxstarParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(MxstarParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(MxstarParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubscriptExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptExpr(MxstarParser.SubscriptExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubscriptExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptExpr(MxstarParser.SubscriptExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinaryExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(MxstarParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinaryExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(MxstarParser.BinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NewExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpr(MxstarParser.NewExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NewExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpr(MxstarParser.NewExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(MxstarParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(MxstarParser.PrimaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenthesesExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesesExpr(MxstarParser.ParenthesesExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenthesesExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesesExpr(MxstarParser.ParenthesesExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FieldExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFieldExpr(MxstarParser.FieldExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FieldExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFieldExpr(MxstarParser.FieldExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConditionalExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpr(MxstarParser.ConditionalExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConditionalExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpr(MxstarParser.ConditionalExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(MxstarParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(MxstarParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FuncCallExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFuncCallExpr(MxstarParser.FuncCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FuncCallExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFuncCallExpr(MxstarParser.FuncCallExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(MxstarParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(MxstarParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperator(MxstarParser.UnaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperator(MxstarParser.UnaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#newSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterNewSpecifier(MxstarParser.NewSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#newSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitNewSpecifier(MxstarParser.NewSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MxstarParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MxstarParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#primaryType}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryType(MxstarParser.PrimaryTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#primaryType}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryType(MxstarParser.PrimaryTypeContext ctx);
}