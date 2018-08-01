package interpreter.ast;

import interpreter.visitors.Visitor;

public class Push implements Exp {
	private final Exp left;
	private final Exp right;

	public Push(Exp left, Exp right) {
		this.left = left;
		this.right = right;
	}

	public Exp getLeft() {
		return left;
	}

	public Exp getRight() {
		return right;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + left + "," + right + ")";
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitPush(left, right);
	}
}
