package interpreter.ast;

import interpreter.visitors.Visitor;

public class SingleExp extends Single<Exp> implements ExpSeq {

	public SingleExp(Exp single) {
		super(single);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitSingleExp(single);
	}

	// @Override
	// public Type typecheck(GenEnvironment<Type> env) throws
	// TypecheckerException {
	// return single.typecheck(env);
	// }

}
