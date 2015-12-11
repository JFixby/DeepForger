package rebecca.e29.dialogs;

import rebecca.e30.util.Exception;

public class OutOfRangeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4945393708778720102L;

	public OutOfRangeException(String defaultValue, String fromValue,
			String toValue) {
		// TODO Auto-generated constructor stub
		super("Value " + defaultValue + " is out of range [" + fromValue + ","
				+ toValue + "]");
	}
}
