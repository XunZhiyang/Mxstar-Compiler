// Generated from Mxstar.g4 by ANTLR 4.8

package Parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxstarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxstarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#programFraction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramFraction(MxstarParser.ProgramFractionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(MxstarParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDeclaration(MxstarParser.FieldDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorDeclaration(MxstarParser.ConstructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(MxstarParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(MxstarParser.IdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(MxstarParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#funcType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncType(MxstarParser.FuncTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#declarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarator(MxstarParser.DeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#parameterDeclarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterDeclarationList(MxstarParser.ParameterDeclarationListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterDeclaration(MxstarParser.ParameterDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(MxstarParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CompoundStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStmt(MxstarParser.CompoundStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarDeclStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclStmt(MxstarParser.VarDeclStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStmt(MxstarParser.ExprStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SelectionStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectionStmt(MxstarParser.SelectionStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IterationStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterationStmt(MxstarParser.IterationStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JumpStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpStmt(MxstarParser.JumpStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EmptyStmt}
	 * labeled alternative in {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStmt(MxstarParser.EmptyStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(MxstarParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#selectionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectionStatement(MxstarParser.SelectionStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link MxstarParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(MxstarParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link MxstarParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(MxstarParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#forCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForCondition(MxstarParser.ForConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(MxstarParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(MxstarParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(MxstarParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConstExpr}
	 * labeled alternative in {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstExpr(MxstarParser.ConstExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ThisExpr}
	 * labeled alternative in {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisExpr(MxstarParser.ThisExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExpr(MxstarParser.VarExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolExpr}
	 * labeled alternative in {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpr(MxstarParser.BoolExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NullExpr}
	 * labeled alternative in {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullExpr(MxstarParser.NullExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntegerLiteral}
	 * labeled alternative in {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(MxstarParser.IntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(MxstarParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SubscriptExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptExpr(MxstarParser.SubscriptExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpr(MxstarParser.BinaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpr(MxstarParser.NewExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(MxstarParser.PrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenthesesExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesesExpr(MxstarParser.ParenthesesExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FieldExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldExpr(MxstarParser.FieldExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConditionalExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpr(MxstarParser.ConditionalExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(MxstarParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FuncCallExpr}
	 * labeled alternative in {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCallExpr(MxstarParser.FuncCallExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(MxstarParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#unaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperator(MxstarParser.UnaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#newSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewSpecifier(MxstarParser.NewSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MxstarParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#primaryType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryType(MxstarParser.PrimaryTypeContext ctx);
}