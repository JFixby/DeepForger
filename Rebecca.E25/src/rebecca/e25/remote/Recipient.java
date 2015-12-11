package rebecca.e25.remote;

import rebecca.e25.mail.Message;

public interface Recipient {

	public Message Process(Message Q);

	public boolean Hi();

	

}
