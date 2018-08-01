package interpreter.visitors;

import interpreter.ast.Exp;
import interpreter.ast.ExpSeq;
import interpreter.ast.Ident;
import interpreter.ast.Stmt;
import interpreter.ast.StmtSeq;

public interface Visitor<T> {
	T visitAdd(Exp left, Exp right);

	T visitDiff(Exp left, Exp right);

	T visitAnd(Exp left, Exp right);

	T visitAt(Exp left, Exp right);

	T visitAssignStmt(Ident ident, Exp exp);

	T visitBoolLiteral(boolean value);

	T visitEq(Exp left, Exp right);

	T visitForEachStmt(Ident ident, Exp exp, StmtSeq block);

	T visitIfStmt(Exp exp, StmtSeq trueBlock, StmtSeq FalseBlock);

	T visitWhileStmt(Exp exp, StmtSeq block);

	T visitFst(Exp exp);

	T visitIntLiteral(int value);

	T visitLength(Exp exp);

	T visitPair(Exp left, Exp right);

	T visitListLiteral(ExpSeq exps);

	T visitLth(Exp left, Exp right);

	T visitMinus(Exp left, Exp right);

	T visitMoreExp(Exp first, ExpSeq rest);

	T visitMoreStmt(Stmt first, StmtSeq rest);

	T visitMul(Exp left, Exp right);

	T visitDiv(Exp left, Exp right);

	T visitNot(Exp exp);

	T visitOr(Exp left, Exp right);

	T visitPop(Exp exp);

	T visitPrintStmt(Exp exp);

	T visitProg(StmtSeq stmtSeq);

	T visitPush(Exp left, Exp right);

	T visitSign(Exp exp);

	T visitSnd(Exp exp);

	T visitIdent(String name);

	T visitSingleExp(Exp exp);

	T visitSingleStmt(Stmt stmt);

	T visitTop(Exp exp);

	T visitVarStmt(Ident ident, Exp exp);
}
