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
	 * Enter a parse tree produced by {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MxstarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MxstarParser.StatementContext ctx);
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
	 * Enter a parse tree produced by {@link MxstarParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement(MxstarParser.IterationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement(MxstarParser.IterationStatementContext ctx);
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
	 * Enter a parse tree produced by {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement(MxstarParser.JumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement(MxstarParser.JumpStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(MxstarParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(MxstarParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(MxstarParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(MxstarParser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MxstarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MxstarParser.ExpressionContext ctx);
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