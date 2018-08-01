package interpreter;

import interpreter.ast.Ident;
import interpreter.visitors.typechecking.TypecheckerException;

public interface Environment<T> {

	void enterScope();

	void exitScope();

	T lookup(Ident id) throws TypecheckerException;

	T update(Ident id, T info) throws TypecheckerException;
	
	T newFresh(Ident id, T info) throws TypecheckerException;

}
