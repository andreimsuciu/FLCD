%{
#include <stdio.h>
#include <stdlib.h>
#define YYDEBUG 1

#define TYPE_INT 1
#define TYPE_BOOL 2
#define TYPE_CHAR 3
#define TYPE_STRING 4

double stack[20];
int sp;

void push(double x)
{ stack[sp++]=x; }

double pop()
{ return stack[--sp]; }

%}

%union {
  	int l_val;
	char *p_val;
}

%token soma
%token Nambari
%token Tabia
%token Kamba
%token Mbili
%token kama
%token mkama
%token andika
%token kwa


%token ID
%token <p_val> CONST_INT
%token <p_val> CONST_BOOL
%token <p_val> CONST_CHAR
%token CONST_STR


%token >>
%token <
%token >+
%token >-
%token >
%token -
%token +

%left :) :(
%left * /
%left OR
%left AND
%left NOT

%%
program:    statementList
		;
statementList:  statement
        | statement statementList
		;
statement:  simpleStatement
        | structStatement
		;
compoundStatement: statement | statement compoundStatement
        ;
simpleStatement:    variableDeclarationStatement
        | assignmentStatement
        | printStatement
        | readStatement
		;
variableDeclarationStatement:   type ID 
        | type ID >> expression
		;
assignmentStatement:    ID >> expression
        | ID '[' CONST_INT ']' >> expression
		;
printStatement: PRINT '(' ID ')'
        | PRINT '(' constant ')'
        | PRINT '(' expression ')'
		;
readStatement:  soma '(' CONST_STR ')'
		;
basicType:  Nambari
        | Tabia
        | Kamba
        | Mbili
		;
arrayType:  basicType '[' CONST_INT ']'
		;
type:   basicType
		| arrayType
		;
expression: arithmeticExpression
        | logicExpression
        | relationalExpression
        ;
condition:  logicExpression
        | relationalExpression
        | ID
        | constant
        | NOT logicExpression
        | NOT relationalExpression
        | NOT ID
        | NOT constant
		;
arithmeticExpression:   arithmeticExpression :) term
        | arithmeticExpression :( term
        | term
		;
term:   term * factor
        | term / factor
        | factor
		;
factor: '(' arithmeticExpression ')'
        | ID
        | CONST_INT
		;
logicExpression:    expression OR expression
        | expression AND expression
		;
relationalExpression:   arithmeticExpression relation arithmeticExpression
        ;
relation: >
        | <
        | >-
        | >+
        | -
        | +
        ;
constant: CONST_INT
        | CONST_CHAR
        | CONST_STR
        ;
structStatement:    compoundStatement
        | ifStatement
        | whileStatement
        | forStatement
		;
ifStatement:	kama condition ':' statement
		| kama condition ':' statement mkama ':' statement
		;
		
forStatement:	kwa variableDeclarationStatement ',' condition ',' assignmentStatement ':' statement
		;

%%

yyerror(char *s)
{
  printf("%s\n", s);
}

extern FILE *yyin;

main(int argc, char **argv)
{
  if(argc>1) yyin = fopen(argv[1], "r");
  if((argc>2)&&(!strcmp(argv[2],"-d"))) yydebug = 1;
  if(!yyparse()) fprintf(stderr,"\tO.K.\n");
}

