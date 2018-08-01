package interpreter.visitors.typechecking;

import static interpreter.visitors.typechecking.PrimtType.*;

import interpreter.GenEnvironment;
import interpreter.ast.Exp;
import interpreter.ast.ExpSeq;
import interpreter.ast.Ident;
import interpreter.ast.SimpleIdent;
import interpreter.ast.Stmt;
import interpreter.ast.StmtSeq;
import interpreter.visitors.Visitor;

public class TypeCheck implements Visitor<Type> {

	private final GenEnvironment<Type> env = new GenEnvironment<>();

	private void checkBinOp(Exp left, Exp right, Type type) {
		left.accept(this).checkEqual(type);
		right.accept(this).checkEqual(type);
	}

	@Override
	public Type visitAdd(Exp left, Exp right) {
		checkBinOp(left, right, INT);
		return INT;
	}

	@Override
	public Type visitDiff(Exp left, Exp right) {
		checkBinOp(left, right, INT);
		return INT;
	}

	@Override
	public Type visitAnd(Exp left, Exp right) {
		checkBinOp(left, right, BOOL);
		return BOOL;
	}


	@Override
	public Type visitAt(Exp left, Exp right) {
		checkBinOp(left, right, new ListType(INT));
		return new ListType(INT); //?
	}


	@Override
	public Type visitAssignStmt(Ident ident, Exp exp) {
		Type expected = env.lookup(ident);
		exp.accept(this).checkEqual(expected);
		return null;
	}

	@Override
	public Type visitBoolLiteral(boolean value) {
		return BOOL;
	}

	@Override
	public Type visitEq(Exp left, Exp right) {
		Type expected = left.accept(this);
		right.accept(this).checkEqual(expected);
		return BOOL;
	}

	@Override
	public Type visitForEachStmt(Ident ident, Exp exp, StmtSeq block) {
		Type ty = exp.accept(this).checkList();
		env.enterScope();
		env.newFresh(ident, ty);
		block.accept(this);
		env.exitScope();
		return null;
	}

	@Override
	public Type visitIfStmt(Exp exp, StmtSeq trueBlock, StmtSeq falseBlock) {
		exp.accept(this);
		trueBlock.accept(this);
		falseBlock.accept(this);
		return null;
	}

	@Override
	public Type visitWhileStmt(Exp exp, StmtSeq block) {
		exp.accept(this);
		block.accept(this);
		return null;
	}

	@Override
	public Type visitFst(Exp exp) {
		return exp.accept(this).checkPair();
	}

	@Override
	public Type visitSnd(Exp exp) {
		return exp.accept(this).checkPair();
	}

	@Override
	public Type visitLength(Exp exp) {
		return exp.accept(this).checkList();
	}

	@Override
	public Type visitPair(Exp left, Exp right) {
		Type fstType = left.accept(this);
		Type sndType = right.accept(this);
		return new PairType(fstType,sndType);
	}

	@Override
	public Type visitIntLiteral(int value) {
		return INT;
	}

	@Override
	public Type visitListLiteral(ExpSeq exps) {
		return new ListType(exps.accept(this));
	}

	@Override
	public Type visitLth(Exp left, Exp right) {
		checkBinOp(left, right, INT);
		return BOOL;
	}

	@Override
	public Type visitMinus(Exp left, Exp right) {
		checkBinOp(left, right, INT);
		return INT;
	}

	@Override
	public Type visitMoreExp(Exp first, ExpSeq rest) {
		Type expected = first.accept(this);
		return rest.accept(this).checkEqual(expected);
	}

	@Override
	public Type visitMoreStmt(Stmt first, StmtSeq rest) {
		first.accept(this);
		rest.accept(this);
		return null;
	}

	@Override
	public Type visitMul(Exp left, Exp right) {
		checkBinOp(left, right, INT);
		return INT;
	}

	@Override
	public Type visitDiv(Exp left, Exp right) {
		checkBinOp(left, right, INT);
		return INT;
	}

	@Override
	public Type visitNot(Exp exp) {
		return exp.accept(this).checkEqual(BOOL);
	}

	@Override
	public Type visitOr(Exp left, Exp right) {
		checkBinOp(left, right, BOOL);
		return BOOL;
	}

	@Override
	public Type visitPop(Exp exp) {
		Type type = exp.accept(this);
		type.checkList();
		return type;
	}

	@Override
	public Type visitPrintStmt(Exp exp) {
		exp.accept(this);
		return null;
	}

	@Override
	public Type visitProg(StmtSeq stmtSeq) {
		stmtSeq.accept(this);
		return null;
	}

	@Override
	public Type visitPush(Exp left, Exp right) {
		Type elType = left.accept(this);
		return right.accept(this).checkEqual(new ListType(elType));
	}

	@Override
	public Type visitSign(Exp exp) {
		return exp.accept(this).checkEqual(INT);
	}

	@Override
	public Type visitIdent(String name) {
		return env.lookup(new SimpleIdent(name));
	}

	@Override
	public Type visitSingleExp(Exp exp) {
		return exp.accept(this);
	}

	@Override
	public Type visitSingleStmt(Stmt stmt) {
		stmt.accept(this);
		return null;
	}

	@Override
	public Type visitTop(Exp exp) {
		return exp.accept(this).checkList();
	}

	@Override
	public Type visitVarStmt(Ident ident, Exp exp) {
		env.newFresh(ident, exp.accept(this));
		return null;
	}

}
