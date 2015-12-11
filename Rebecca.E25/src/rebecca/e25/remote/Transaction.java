package rebecca.e25.remote;

import java.io.Serializable;

import rebecca.e25.mail.Message;

public class Transaction implements Serializable {

	private static final long serialVersionUID = -1593688663766040476L;

	Message m;

	public Transaction(Message mm) {
		// TODO Auto-generated constructor stub
		m = mm;
	}

	public Message getMessage() {
		return m;
	}

}
