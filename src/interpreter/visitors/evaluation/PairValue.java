package interpreter.visitors.evaluation;

public interface PairValue extends Value {

	Value fst();

	Value snd();

	@Override
	default PairValue asPair() {
		return this;
	}
}
