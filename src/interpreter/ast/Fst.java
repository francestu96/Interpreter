package interpreter.ast;

import interpreter.visitors.Visitor;

public class Fst extends UnaryOp {

	public Fst(Exp exp) {
		super(exp);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitFst(exp);
	}

	// @Override
	// public Type typecheck(GenEnvironment<Type> env) throws
	// TypecheckerException {
	// Type ty = exp.typecheck(env);
	// ty.checkList();
	// return ty;
	// }
}
