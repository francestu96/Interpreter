package interpreter.visitors.typechecking;

public class PairType implements Type {

	private final Type fstType;
	private final Type sndType;

	public static final String PAIR = "PAIR";

	public PairType(Type fstType, Type sndType) {
		this.fstType = fstType;
		this.sndType = sndType;
	}

	public Type fst() {
		return fstType;
	}

	public Type snd() {
		return sndType;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PairType))
			return false;
		PairType pt = (PairType) obj;
		return fstType.equals(pt.fstType) && sndType.equals(pt.sndType);
	}


	@Override
	public String toString() {
		return fstType + " * " + sndType;
	}
}
