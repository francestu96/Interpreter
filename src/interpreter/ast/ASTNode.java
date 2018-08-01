package interpreter.ast;

import interpreter.visitors.Visitor;

public interface ASTNode {
	<T> T accept(Visitor<T> visitor);
}
