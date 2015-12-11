package rebecca.e29.dialogs;

import java.util.regex.Pattern;

public class VString extends Variable implements Filter<String>,
		java.io.Serializable {

	private static final long serialVersionUID = 3796514014122817931L;

	public VString() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VString(String Regex) {
		this.regex = Regex;
	}

	private String regex;

	public synchronized String getRegularExpression() {
		return regex;
	}

	public synchronized void setRegularExpression(String regex) {
		this.regex = regex;
	}

	@Override
	public synchronized boolean valueFits(String t) {
		// TODO Auto-generated method stub
		if (regex == null) {
			return true;
		} else {
			return Pattern.matches(regex, t);
		}
	}

}
