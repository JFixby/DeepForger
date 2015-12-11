package rebecca.e25.remote;

import rebecca.e25.util.DSSException;

public class SorryDuplicateAddress extends DSSException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8934705078677263666L;

	public SorryDuplicateAddress(String Message) {
		super(Message + " is  already in use!");
		// TODO Auto-generated constructor stub
	}

}
