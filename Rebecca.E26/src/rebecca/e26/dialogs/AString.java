package rebecca.e26.dialogs;

public class AString extends Axis {

	String Default = "";
	String regex = null;
	
	public String getDefault() {
		return Default;
	}
	public void setDefault(String defaultv) {
		Default = defaultv;
	}
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}

}
