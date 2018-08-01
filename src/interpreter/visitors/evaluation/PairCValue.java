package interpreter.visitors.evaluation;

import static java.util.Objects.requireNonNull;

public class PairCValue implements PairValue {
	private Value fst;
	private Value snd;

	public PairCValue(Value fst, Value snd){
		this.fst=requireNonNull(fst);
		this.snd=requireNonNull(snd);
	}

	public Value fst(){
		return fst; 
	}

	public Value snd(){
		return snd;
	}

	@Override
	public String toString() {
		return "(" + fst + "," + snd + ")";
	}

	@Override
	public boolean equals(Object obj) {
		return fst.equals(((PairCValue) obj).fst) && snd.equals(((PairCValue) obj).snd);
	}
}