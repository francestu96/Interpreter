package interpreter.ast;

import interpreter.visitors.Visitor;

public class IntLiteral extends PrimLiteral<Integer> {

	public IntLiteral(int n) {
		super(n);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitIntLiteral(value);
	}
	
//	@Override
//	public Type typecheck(GenEnvironment<Type> env) {
//		return INT;
//	}

}
