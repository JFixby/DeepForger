package rebecca.e29.lang;

public class Exception extends RuntimeException {

	private static final long serialVersionUID = 1811988306094364489L;

	public Exception(String Message) {
		super("\n OMG! \n" + Message);

	}

}
