package interpreter.visitors.evaluation;

import interpreter.GenEnvironment;
import interpreter.ast.Exp;
import interpreter.ast.ExpSeq;
import interpreter.ast.Ident;
import interpreter.ast.SimpleIdent;
import interpreter.ast.Stmt;
import interpreter.ast.StmtSeq;
import interpreter.visitors.Visitor;

import java.io.PrintStream;;
import java.util.*;

public class Eval implements Visitor<Value> {

	private final GenEnvironment<Value> env = new GenEnvironment<>();
	private PrintStream out;

	public Eval(PrintStream out){
		this.out=out;
	}

	@Override
	public Value visitAdd(Exp left, Exp right) {
		return new IntValue(left.accept(this).asInt() + right.accept(this).asInt());
	}

	@Override
	public Value visitDiff(Exp left, Exp right) {
		return new IntValue(left.accept(this).asInt() - right.accept(this).asInt());
	}

	@Override
	public Value visitAnd(Exp left, Exp right) {
		return new BoolValue(left.accept(this).asBool() && right.accept(this).asBool());
	}

	@Override
	public Value visitAt(Exp left, Exp right) {
		ListValue l = left.accept(this).asList();
		ListValue r = right.accept(this).asList();
		for (Value src : r)
			l = l.push(src);
		return l;
	}

	@Override
	public Value visitAssignStmt(Ident ident, Exp exp) {
		env.update(ident, exp.accept(this));
		return null;
	}

	@Override
	public Value visitBoolLiteral(boolean value) {
		return new BoolValue(value);
	}

	@Override
	public Value visitEq(Exp left, Exp right) {
		return new BoolValue(left.accept(this).equals(right.accept(this)));
	}

	@Override
	public Value visitForEachStmt(Ident ident, Exp exp, StmtSeq block) {
		ListValue list = exp.accept(this).asList();
		for (Value val : list) {
			env.enterScope();
			env.newFresh(ident, val);
			block.accept(this);
			env.exitScope();
		}
		return null;
	}

	@Override
	public Value visitIfStmt(Exp exp, StmtSeq trueBlock, StmtSeq falseBlock) {
		if(exp.accept(this).asBool())
			trueBlock.accept(this);
		else
			falseBlock.accept(this);

		return null;
	}

	@Override
	public Value visitWhileStmt(Exp exp, StmtSeq block) {
		while(exp.accept(this).asBool())
			block.accept(this);

		return null;
	}

	@Override
	public Value visitFst(Exp exp) {
		return exp.accept(this).asPair().fst();
	}

	@Override
	public Value visitSnd(Exp exp) {
		return exp.accept(this).asPair().snd();
	}

	@Override
	public Value visitLength(Exp exp) {
		return exp.accept(this).asList().length();
	}

	@Override
	public Value visitIntLiteral(int value) {
		return new IntValue(value);
	}

	@Override
	public Value visitListLiteral(ExpSeq exps) {
		return exps.accept(this);
	}

	@Override
	public Value visitLth(Exp left, Exp right) {
		return new BoolValue(left.accept(this).asInt() < right.accept(this).asInt());
	}

	@Override
	public Value visitMinus(Exp left, Exp right) {
		return new IntValue(left.accept(this).asInt() - right.accept(this).asInt());
	}

	@Override
	public Value visitMoreExp(Exp first, ExpSeq rest) {
		return new LinkedListValue(first.accept(this), rest.accept(this).asList());
	}

	@Override
	public Value visitMoreStmt(Stmt first, StmtSeq rest) {
		first.accept(this);
		rest.accept(this);
		return null;
	}

	@Override
	public Value visitMul(Exp left, Exp right) {
		return new IntValue(left.accept(this).asInt() * right.accept(this).asInt());
	}

	@Override
	public Value visitDiv(Exp left, Exp right) {
		return new IntValue(left.accept(this).asInt() / right.accept(this).asInt());
	}

	@Override
	public Value visitNot(Exp exp) {
		return new BoolValue(!exp.accept(this).asBool());
	}

	@Override
	public Value visitOr(Exp left, Exp right) {
		return new BoolValue(left.accept(this).asBool() || right.accept(this).asBool());
	}

	@Override
	public Value visitPop(Exp exp) {
		return exp.accept(this).asList().pop();
	}

	@Override
	public Value visitPair(Exp left, Exp right) {
		Value fst = left.accept(this);
		Value snd = right.accept(this);
		return new PairCValue(fst,snd);
	}

	@Override
	public Value visitPrintStmt(Exp exp) {
		out.println(exp.accept(this));
		return null;
	}

	@Override
	public Value visitProg(StmtSeq stmtSeq) {
		stmtSeq.accept(this);
		return null;
	}

	@Override
	public Value visitPush(Exp left, Exp right) {
		Value el = left.accept(this);
		return right.accept(this).asList().push(el);
	}

	@Override
	public Value visitSign(Exp exp) {
		return new IntValue(-exp.accept(this).asInt());
	}

	@Override
	public Value visitIdent(String name) {
		return env.lookup(new SimpleIdent(name));
	}

	@Override
	public Value visitSingleExp(Exp exp) {
		return new LinkedListValue(exp.accept(this), new LinkedListValue());
	}

	@Override
	public Value visitSingleStmt(Stmt stmt) {
		stmt.accept(this);
		return null;
	}

	@Override
	public Value visitTop(Exp exp) {
		return exp.accept(this).asList().top();
	}

	@Override
	public Value visitVarStmt(Ident ident, Exp exp) {
		env.newFresh(ident, exp.accept(this));
		return null;
	}

}
