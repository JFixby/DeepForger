package rebecca.e26.dialogs;

public abstract class DDot<Q> extends Axis {
	private static final long serialVersionUID = -3310018452830468742L;

	// public static final String NULL = "null";
	Q value = null;

	// public void parseValue(String s) {
	// if (s == null) {
	// setValue(null);
	// } else if (s.equalsIgnoreCase("")) {
	// setValue(null);
	// } else if (s.equalsIgnoreCase(NULL)) {
	// setValue(null);
	// } else {
	// try {
	// setValue(spawn(s));
	// } catch (Throwable e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// setValue(null);
	// }
	// }
	//
	// }

	// public abstract Q spawn(String s) throws Throwable;;

	public Q getValue() {
		return value;
	}

	public String getStringValue() {
		return value + "";
	}

	public void setValue(Q value) {
		this.value = value;
	}

	// public String getStringValue() {
	// if (value == null)
	// return NULL;
	// return value.toString();
	// }

}
