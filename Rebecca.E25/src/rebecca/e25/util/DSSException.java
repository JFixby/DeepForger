package rebecca.e25.util;

public class DSSException extends RuntimeException {

	private static final long serialVersionUID = 1811988306094364489L;

	public DSSException(String Message) {
		super("\n DSS Exception! \n" + Message);
	}

}
