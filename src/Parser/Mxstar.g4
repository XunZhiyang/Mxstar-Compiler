grammar Mxstar;

declaration
    :   functionDeclaration
    |   classDeclaration
    ;

functionDeclaration
    :   type declarator compoundStatement
    ;

classDeclaration
    :   'class' Identifier '{' (varDeclaration | functionDeclaration)* '}'
    ;

varDeclaration
    :   type Identifier ';'
    |   type Identifier '=' expression ';'
    ;

declarator
    :  Identifier '(' parameterList ')'
    ;

parameterList
    :   parameterDeclaration
    |   parameterList ',' parameterDeclaration
    ;

parameterDeclaration
    :   type Identifier
    ;

compoundStatement
    :   '{' statement* '}'
    ;

//compoundFuncStatement
//    :   '{' statement* '}'
//    ;

statement
    :   compoundStatement
    |   varDeclaration
    |   expressionStatement
    |   selectionStatement
    |   iterationStatement
    |   jumpStatement
    ;

expressionStatement
    :   expression? ';'
    ;

selectionStatement
    :   'if' '(' expression ')' statement ('else' statement)?
    ;

iterationStatement
    :   While '(' expression ')' statement
    |   For '(' forCondition ')' statement
    ;

forCondition
    :   init=expression? ';' cond=expression? ';' step=expression?
    ;

jumpStatement
    :   'return' expression? ';'
    |   'break' ';'
    |   'continue' ';'
    ;

primaryExpression
    :   Identifier
    |   Constant
    |   This
    ;

expression
    :   primaryExpression
    |   expression '=' expression
    ;

type
    :   primaryType
    |   type '[]'
    ;

primaryType
    :   Bool
    |   Int
    |   Void
    |   String
    ;

This : 'this';

Bool : 'bool';
Int : 'int';
Void : 'void';
String : 'string';

While : 'while';
For : 'for';

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