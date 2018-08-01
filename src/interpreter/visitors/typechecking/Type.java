package interpreter.visitors.typechecking;

public interface Type {
	default Type checkEqual(Type expected) throws TypecheckerException {
		if (!equals(expected))
			throw new TypecheckerException(toString(), expected.toString());
		return this;
	}

	default Type checkList() throws TypecheckerException {
		if (!(this instanceof ListType))
			throw new TypecheckerException(toString(), ListType.LIST);
		return ((ListType) this).getElType();
	}

	default Type checkPair() throws TypecheckerException {
		if (!(this instanceof PairType))
			throw new TypecheckerException(toString(), PairType.PAIR);
		return this;
	}

}
