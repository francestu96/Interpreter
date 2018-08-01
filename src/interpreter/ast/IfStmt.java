package interpreter.ast;

import static java.util.Objects.requireNonNull;

import interpreter.visitors.Visitor;

public class IfStmt implements Stmt {
	private final Exp exp;
	private final StmtSeq trueBlock;
	private final StmtSeq falseBlock;

	public IfStmt(Exp exp, StmtSeq trueBlock, StmtSeq falseBlock) {
		this.trueBlock = requireNonNull(trueBlock);
		this.exp = requireNonNull(exp);
		this.falseBlock = requireNonNull(falseBlock);
	}

	public StmtSeq getTrueBlock() {
		return trueBlock;
	}

	public Exp getExp() {
		return exp;
	}

	public StmtSeq getFalseBlock() {
		return falseBlock;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + exp + "," + trueBlock + "," + falseBlock + ")";
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitIfStmt(exp, trueBlock, falseBlock);
	}

}
