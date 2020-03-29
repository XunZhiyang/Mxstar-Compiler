package AST;

public interface ASTVisitor {
    void visit(BinaryExprNode node);

    void visit(BoolExprNode node);

    void visit(BreakNode node);

    void visit(ClassDeclNode node);

    void visit(CompoundStmtNode node);

    void visit(ConditionalExprNode node);

    void visit(ContinueNode node);

    void visit(EmptyStmtNode node);

    void visit(ExprStmtNode node);

    void visit(FieldExprNode node);

    void visit(ForStmtNode node);

    void visit(FuncCallExprNode node);

    void visit(FuncDeclNode node);

    void visit(IdentifierListNode node);

    void visit(IntLiteralNode node);

    void visit(NewExprNode node);

    void visit(NullNode node);

    void visit(ParamDeclList node);

    void visit(ParamDeclNode node);

    void visit(ParamList node);

    void visit(ProgramNode node);

    void visit(ReturnNode node);

    void visit(SelectionStmtNode node);

    void visit(StringLiteralNode node);

    void visit(SubscriptExprNode node);

    void visit(ThisNode node);

    void visit(TypeNode node);

    void visit(UnaryExprNode node);

    void visit(VarDeclNode node);

    void visit(VarDeclStmtNode node);

    void visit(VarExprNode node);

    void visit(WhileStmtNode node);
}
