package interpreter.ast;

import interpreter.visitors.Visitor;

public class Length extends UnaryOp {

	public Length(Exp exp) {
		super(exp);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitLength(exp);
	}
}
