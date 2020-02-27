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
	 * Visit a parse tree produced by {@link MxstarParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(MxstarParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(MxstarParser.VarDeclarationContext ctx);
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
	 * Visit a parse tree produced by {@link MxstarParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(MxstarParser.ParameterListContext ctx);
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
	 * Visit a parse tree produced by {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MxstarParser.StatementContext ctx);
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
	 * Visit a parse tree produced by {@link MxstarParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterationStatement(MxstarParser.IterationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#forCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForCondition(MxstarParser.ForConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpStatement(MxstarParser.JumpStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(MxstarParser.PrimaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpression(MxstarParser.ConstantExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MxstarParser.ExpressionContext ctx);
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