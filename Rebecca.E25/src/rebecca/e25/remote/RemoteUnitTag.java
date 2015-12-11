package rebecca.e25.remote;

import rebecca.e25.mail.Message;

public interface RemoteUnitTag {

	Message Process(Message Q) throws NotRespondException;

	public boolean sayHi();

}
