package rebecca.e29.dialogs;

import rebecca.e29.util.Set;

public abstract class VSet extends Variable {

	private static final long serialVersionUID = 3038956099132509629L;
	transient volatile Set<String> s;
	String defaultValue = null;

	public synchronized String getDefaultValue() {
		return defaultValue;
	}

	public synchronized void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public synchronized Set<String> getValuesSet() {
		if (s == null) {
			s = new Set<String>();
		}

		return s;
	}

	public synchronized void setValuesSet(Set<String> s) {
		this.s = s;
	}

}
