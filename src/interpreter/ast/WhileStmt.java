package interpreter.ast;

import static java.util.Objects.requireNonNull;

import interpreter.visitors.Visitor;

public class WhileStmt implements Stmt {
	private final Exp exp;
	private final StmtSeq block;

	public WhileStmt(Exp exp, StmtSeq block) {
		this.block = requireNonNull(block);
		this.exp = requireNonNull(exp);
	}

	public StmtSeq getBlock() {
		return block;
	}

	public Exp getExp() {
		return exp;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + exp + "," + block + ")";
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitWhileStmt(exp, block);
	}

}
