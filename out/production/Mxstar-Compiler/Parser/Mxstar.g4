grammar Mxstar;

program
    :   (   functionDeclaration
        |   classDeclaration
        |   varDeclaration
        )*
    ;

classDeclaration
    :   'class' Identifier '{' (varDeclaration | functionDeclaration)* '}'
    ;

varDeclaration
    :   type Identifier ';'
    |   type Identifier '=' expression ';'
    ;

functionDeclaration
    :   funcType declarator compoundStatement
    ;

funcType
    :   type
    |   Void
    ;

declarator
    :  Identifier '(' parameterList? ')'
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

statement
    :   compoundStatement
    |   varDeclaration
    |   expressionStatement
    |   selectionStatement
    |   iterationStatement
    |   jumpStatement
    |   ';'
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
    :   constantExpression
    |   This
    |   Identifier
    ;

constantExpression
    :   True
    |   False
    |   Null
    |   IntegerLiteral
    |   StringLiteral
    ;


expression
    :   primaryExpression
    |   '(' expression ')'
    |   expression '.' Identifier
    |   expression '(' parameterList? ')'
    |   'new' newSpecifier
    |   src1=expression '[' src2=expression ']'
    |   expression op=('++' | '--')
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

newSpecifier
    :   primaryType
    |   primaryType '(' ')'
    |   primaryType ('[' expression ']')+ ('[' ']')*
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