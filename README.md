Java interpreter for the following grammar

Sintax:

Language sintax is defined from the not-terminal Prog by the following ambiguos grammar BNF.

Prog ::= StmtSeq EOF
StmtSeq ::= Stmt | Stmt;StmtSeq
Stmt ::= ID=Exp | var ID=Exp | print Exp | for ID in Exp {StmtSeq}
	| if (Exp) {StmtSeq} else {StmtSeq} | while (Exp) {StmtSeq}
ExpSeq ::= Exp | Exp,ExpSeq
Exp ::= Exp||Exp | Exp&&Exp | Exp==Exp | Exp<Exp | Exp+Exp | Exp-Exp | Exp * Exp | Exp/Exp
	| !Exp | -Exp | top Exp | pop Exp | push(Exp,Exp) | [ExpSeq] | Exp@Exp | length Exp
	| pair(Exp,Exp) | fst Exp | snd Exp | NUM | BOOL | ID | (Exp)

Further specifications are provided in italian in "text.pdf"
