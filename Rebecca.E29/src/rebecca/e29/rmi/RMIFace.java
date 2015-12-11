package rebecca.e29.rmi;

import java.rmi.Remote;

public interface RMIFace extends Remote {

	public boolean ping() throws java.rmi.RemoteException;

}
