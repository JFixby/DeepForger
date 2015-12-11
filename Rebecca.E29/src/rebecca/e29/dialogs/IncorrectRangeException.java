package rebecca.e29.dialogs;

public class IncorrectRangeException extends rebecca.e29.lang.Exception implements  java.io.Serializable{

	public IncorrectRangeException(String fromValue, String toValue) {
		// TODO Auto-generated constructor stub
		super(fromValue + " is bigger than " + toValue
				+ "! Kind of a strange circumstance, right?");
	}

	private static final long serialVersionUID = -8591249055859838979L;

}
