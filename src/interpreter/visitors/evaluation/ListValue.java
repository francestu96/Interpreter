package interpreter.visitors.evaluation;

public interface ListValue extends Value, Iterable<Value> {
	ListValue push(Value el);

	Value top();

	Value length();

	ListValue pop();

	@Override
	default ListValue asList() {
		return this;
	}
}
