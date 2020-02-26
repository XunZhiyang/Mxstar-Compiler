grammar Mxstar;

program
    :   (   functionDeclaration
        |   classDeclaration
        |   varDeclaration
        )*
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
    |   '(' expression ')'
    |   src1=expression '[' expression ']'
    |   expression '.' Identifier
    |   op=('++' | '--') expression
    |   unaryOperator expression
    |   src1=expression op=('*' | '/' | '%') src2=expression
    |   src1=expression op=('+' | '-') src2=expression
    |   src1=expression op=('<<' | '>>') src2=expression
    |   src1=expression op=('<' | '>' | '<=' | '>=') src2=expression
    |   src1=expression op=('==' | '!=') src2=expression
    |   src1=expression op='&' src2=expression
    |   src1=expression op='^' src2=expression
    |   src1=expression op='|' src2=expression
    |   src1=expression op='&&' src2=expression
    |   src1=expression op='||' src2=expression
    |   <assoc=right> src1=expression '?' src2=expression ':' src3=expression
    |   <assoc=right> src1=expression '=' src2=expression
    ;

unaryOperator
    :   '+'
    |   '-'
    |   '~'
    |   '!'
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