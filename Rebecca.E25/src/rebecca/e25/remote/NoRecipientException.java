package rebecca.e25.remote;

import rebecca.e25.util.DSSException;

public class NoRecipientException extends DSSException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -892484307023209761L;

	public NoRecipientException(String r) {
		super("No recipient " + r);
		// TODO Auto-generated constructor stub
	}

}
