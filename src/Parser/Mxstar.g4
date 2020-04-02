grammar Mxstar;

@header {
package Parser;
}

program
    :   programFragment*
    ;

programFragment
    :   functionDeclaration
    |   classDeclaration
    |   varDeclaration
    ;

classDeclaration
    :   'class' Identifier '{' (fieldDeclaration | functionDeclaration | constructorDeclaration)* '}' ';'
    ;

fieldDeclaration
    :   type identifierList ';'
    ;

constructorDeclaration
    :   Identifier '(' ')' compoundStatement
    ;

varDeclaration
    :   type identifierList ';'
    |   type Identifier '=' expression ';'
    ;

identifierList
    :   Identifier  (',' Identifier)*
    ;

functionDeclaration
    :   funcType declarator compoundStatement
    ;

funcType
    :   type
    |   Void
    ;

declarator
    :  Identifier '(' parameterDeclarationList? ')'
    ;

parameterDeclarationList
    :   parameterDeclaration
    |   parameterDeclarationList ',' parameterDeclaration
    ;

parameterDeclaration
    :   type Identifier
    ;

compoundStatement
    :   '{' statement* '}'
    ;

statement
    :   compoundStatement           #CompoundStmt
    |   varDeclaration              #VarDeclStmt
    |   expressionStatement         #ExprStmt
    |   selectionStatement          #SelectionStmt
    |   iterationStatement          #IterationStmt
    |   jumpStatement               #JumpStmt
    |   ';'                         #EmptyStmt
    ;

expressionStatement
    :   expression ';'
    ;

selectionStatement
    :   'if' '(' expression ')' opt1=statement ('else' opt2=statement)?
    ;

iterationStatement
    :   While '(' expression ')' statement      #WhileStmt
    |   For '(' forCondition ')' statement      #ForStmt
    ;

forCondition
    :   init=expression? ';' cond=expression? ';' step=expression?
    ;

jumpStatement
    :   'return' expression? ';'    #ReturnStmt
    |   'break' ';'                 #BreakStmt
    |   'continue' ';'              #ContinueStmt
    ;

primaryExpression
    :   constantExpression      #ConstExpr
    |   This                    #ThisExpr
    |   Identifier              #VarExpr
    ;

constantExpression
    :   True                    #BoolExpr
    |   False                   #BoolExpr
    |   Null                    #NullExpr
    |   IntegerLiteral          #IntegerLiteral
    |   StringLiteral           #StringLiteral
    ;


expression
    :   primaryExpression                                                       #PrimaryExpr
    |   '(' expression ')'                                                      #ParenthesesExpr
    |   expression '.' Identifier                                               #FieldExpr
    |   expression '(' parameterList? ')'                                       #FuncCallExpr
    |   'new' newSpecifier                                                      #NewExpr
    |   src1=expression '[' src2=expression ']'                                 #SubscriptExpr
    |   expression op=('++' | '--')                                             #PostfixExpr
    |   op=('++' | '--') expression                                             #UnaryExpr
    |   op=('+' | '-' | '~' | '!') expression                                   #UnaryExpr
    |   src1=expression op=('*' | '/' | '%') src2=expression                    #BinaryExpr
    |   src1=expression op=('+' | '-') src2=expression                          #BinaryExpr
    |   src1=expression op=('<<' | '>>') src2=expression                        #BinaryExpr
    |   src1=expression op=('<' | '>' | '<=' | '>=') src2=expression            #BinaryExpr
    |   src1=expression op=('==' | '!=') src2=expression                        #BinaryExpr
    |   src1=expression op='&' src2=expression                                  #BinaryExpr
    |   src1=expression op='^' src2=expression                                  #BinaryExpr
    |   src1=expression op='|' src2=expression                                  #BinaryExpr
    |   src1=expression op='&&' src2=expression                                 #BinaryExpr
    |   src1=expression op='||' src2=expression                                 #BinaryExpr
    |   <assoc=right> src1=expression '?' src2=expression ':' src3=expression   #ConditionalExpr
    |   <assoc=right> src1=expression op='=' src2=expression                       #BinaryExpr
    ;

parameterList
    :   expression
    |   expression ',' parameterList
    ;

//unaryOperator
//    :   '+'
//    |   '-'
//    |   '~'
//    |   '!'
//    ;

newSpecifier
    :   primaryType '(' ')'
    |   primaryType ('[' expression ']')+ ('[' ']')*
    |   primaryType
    ;

type
    :   primaryType
    |   type '[' ']'
    ;

primaryType
    :   Bool
    |   Int
    |   String
    |   Identifier
    ;

True : 'true';
False : 'false';
This : 'this';
Null : 'null';


Bool : 'bool';
Int : 'int';
Void : 'void';
String : 'string';

While : 'while';
For : 'for';

IntegerLiteral
    :   [0-9]+
    ;

StringLiteral
    :   '"' SChar* '"'
    ;

fragment
SChar
    :   ~["\\\r\n]
    |   EscapeSequence
    ;

fragment
EscapeSequence
    :   '\\' ["n\\]
    ;


Identifier
    :   Letter
        (   Letter
        |   Digit
        |   '_'
        )*
    ;

fragment
Letter
    :   [a-zA-Z]
    ;

fragment
Digit
    :   [0-9]
    ;

Whitespace
    :   [ \t]+
        -> skip
    ;

Newline
    :   (   '\r' '\n'?
        |   '\n'
        )
        -> skip
    ;

BlockComment
    :   '/*' .*? '*/'
        -> skip
    ;

LineComment
    :   '//' ~[\r\n]*
        -> skip
    ;