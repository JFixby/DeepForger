package rebecca.e25.remote;

import java.rmi.Remote;



import rebecca.e25.mail.Message;

public interface RemoteUnit extends Remote {

	public Response Process(Request Q) throws java.rmi.RemoteException,
			NoRecipientException;

	public boolean sayHiToRecipient(String remote_recipient_pid)
			throws java.rmi.RemoteException;

	public boolean sayHi() throws java.rmi.RemoteException;

}
