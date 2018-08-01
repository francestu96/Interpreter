package interpreter.ast;

import interpreter.visitors.Visitor;

public class At extends BinaryOp {
	public At(Exp left, Exp right) {
		super(left, right);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitAt(left, right);
	}

	// @Override
	// public Type typecheck(GenEnvironment<Type> env) throws
	// TypecheckerException {
	// left.typecheck(env).checkEqual(INT);
	// return right.typecheck(env).checkEqual(INT);
	// }
}