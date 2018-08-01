package interpreter.ast;

import interpreter.visitors.Visitor;

public class Snd extends UnaryOp {

	public Snd(Exp exp) {
		super(exp);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitSnd(exp);
	}

	// @Override
	// public Type typecheck(GenEnvironment<Type> env) throws
	// TypecheckerException {
	// Type ty = exp.typecheck(env);
	// ty.checkList();
	// return ty;
	// }
}
