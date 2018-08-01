package interpreter.ast;

import interpreter.visitors.Visitor;

public class Top extends UnaryOp {

	public Top(Exp exp) {
		super(exp);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitTop(exp);
	}
}
