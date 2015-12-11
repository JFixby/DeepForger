package rebecca.e26;

public class HollyShitException extends RuntimeException {

	private static final long serialVersionUID = 1811988306094364489L;

	public HollyShitException(String Message) {
		super("\n Holly shit! \n" + Message);

	}

}
