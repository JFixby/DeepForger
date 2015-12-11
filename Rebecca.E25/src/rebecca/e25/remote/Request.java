package rebecca.e25.remote;

import rebecca.e25.mail.Message;

public class Request extends Transaction implements
		java.io.Serializable {
	/**
	 * 
	 */

	private String n;

	public Request(String to, Message mm) {
		super(mm);
		n = to;

	}

	public String getRecipientName() {
		return n;
	}

}
