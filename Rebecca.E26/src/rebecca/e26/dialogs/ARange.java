package rebecca.e26.dialogs;

import java.io.Serializable;

import rebecca.e26.HollyShitException;

public abstract class ARange<T extends Comparable<T>> extends Axis implements
		Serializable {

	T From = null;
	T To = null;
	T Defv = null;

	// T Value = null;

	public void setRange(T from, T to) {
		// this.Value = null;
		this.Defv = null;
		if (from == null || to == null) {
			this.From = from;
			this.To = to;
		} else if (from.compareTo(to) <= 0) {
			this.From = from;
			this.To = to;
		} else if (from.compareTo(to) > 0) {
			throw new HollyShitException("From = " + from
					+ " is bigger than To = " + to + " : " + from.compareTo(to));
		}

	}

	public T getFrom() {
		return From;
	}

	public T getTo() {
		return To;
	}

	public T getDefault() {
		return Defv;
	}

	// public T getValue() {
	// return Value;
	// }

	public void setDefault(T default_val) throws DefaultSetException {
		if (this.satisfiable(default_val)) {
			this.Defv = default_val;
		} else {
			throw new DefaultSetException(default_val + " is out of range ["
					+ From + " ; " + To + "]");
		}
	}

	public boolean satisfiable(T o) {
		if (o == null) {
			return true;
		}
		if (From == null && To == null) {
			return true;
		} else if (From == null && To != null) {
			return o.compareTo(To) <= 0;
		} else if (From != null && To == null) {
			return o.compareTo(From) >= 0;
		} else {
			return o.compareTo(From) >= 0 && o.compareTo(To) <= 0;
		}
	}

}
