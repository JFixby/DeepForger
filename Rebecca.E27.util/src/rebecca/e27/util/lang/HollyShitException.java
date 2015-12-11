package rebecca.e27.util.lang;

import java.io.Serializable;

public class HollyShitException extends RuntimeException implements
		Serializable {

	private static final long serialVersionUID = 1811988306094364489L;

	public HollyShitException(String Message) {
		super("\n Holly shit! \n" + Message);

	}

}
