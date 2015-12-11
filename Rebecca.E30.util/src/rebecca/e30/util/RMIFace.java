package rebecca.e30.util;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIFace extends Remote {

	public boolean ping() throws java.rmi.RemoteException, RemoteException,
			MalformedURLException, NotBoundException;

}
