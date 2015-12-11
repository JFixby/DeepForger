package rebecca.e29.dialogs;

public abstract class Range<T extends Comparable<T>> extends Variable implements
		Filter<T> , java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4488410937452416775L;
	public static final String MIN_INFINITY = "-INFINITY";
	public static final String MAX_INFINITY = "+INFINITY";
	public static final String NOT_DEFINED = "NOT_DEFINED";
	T fromValue;
	T toValue;
	private T defaultValue;

	// T defaultValue;

	public synchronized T getFromValue() {
		return fromValue;
	}

	public synchronized T getToValue() {
		return toValue;
	}

	public synchronized void setRange(T fromValue, T toValue)
			throws IncorrectRangeException {

		if (fromValue != null && toValue != null) {
			if (fromValue.compareTo(toValue) <= 0) {
				this.fromValue = fromValue;
				this.toValue = toValue;
				defaultValue = null;
			} else {
				throw new IncorrectRangeException(fromStr(fromValue),
						toStr(toValue));
			}
		} else {
			this.fromValue = fromValue;
			this.toValue = toValue;
			defaultValue = null;
		}
	}

	private String toStr(T toValue2) {
		if (toValue2 == null) {
			return MAX_INFINITY;
		}
		return toValue2.toString();

	}

	private String fromStr(T fromValue2) {
		// TODO Auto-generated method stub
		if (fromValue2 == null) {
			return MIN_INFINITY;
		}
		return fromValue2.toString();
	}

	public synchronized T getDefaultValue() {
		return defaultValue;
	}

	public synchronized void setDefaultValue(T defaultValue)
			throws OutOfRangeException {
		if (valueFits(defaultValue)) {
			this.defaultValue = defaultValue;
		} else {
			throw new OutOfRangeException(defStr(defaultValue),
					fromStr(fromValue), toStr(toValue));
		}

	}

	private String defStr(T defaultValue2) {
		if (defaultValue2 == null) {
			return NOT_DEFINED;
		}
		return defaultValue2.toString();
	}

	@Override
	public synchronized  boolean valueFits(T value) {
		// TODO Auto-generated method stub
		if (value == null) {
			return true;
		} else if (fromValue == null && toValue != null) {
			if (value.compareTo(toValue) <= 0) {
				return true;
			} else {
				return false;
			}
		} else if (fromValue != null && toValue == null) {
			if (value.compareTo(fromValue) >= 0) {
				return true;
			} else {
				return false;
			}

		} else if (fromValue != null && toValue != null) {
			if (value.compareTo(fromValue) >= 0
					&& value.compareTo(toValue) <= 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

}
