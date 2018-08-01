package interpreter.ast;

import interpreter.visitors.Visitor;

public class Pop extends UnaryOp {

	public Pop(Exp exp) {
		super(exp);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitPop(exp);
	}
}
