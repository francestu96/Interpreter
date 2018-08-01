package interpreter.ast;

import static java.util.Objects.requireNonNull;

import interpreter.visitors.Visitor;

public class ProgClass implements Prog {
	private final StmtSeq stmtSeq;

	public ProgClass(StmtSeq stmtSeq) {
		this.stmtSeq = requireNonNull(stmtSeq);
	}

	public StmtSeq getStmtSeq() {
		return stmtSeq;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + stmtSeq + ")";
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitProg(stmtSeq);
	}

	// @Override
	// public void typecheck() throws TypecheckerException {
	// stmtSeq.typecheck(new GenEnvironment<>());
	// }
}
