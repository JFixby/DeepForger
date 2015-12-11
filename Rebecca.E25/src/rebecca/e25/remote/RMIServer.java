/**

	Rebecca-E

    Copyright (C) 2008  by Sergey Stankevich (fieral(about)gmail.com),

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
 */

package rebecca.e25.remote;

import java.rmi.NoSuchObjectException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rebecca.e25.util.Map;
import rebecca.e25.util.MapArray;
import rebecca.e25.util.Printable;

public class RMIServer extends UnicastRemoteObject implements RemoteUnit, Printable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -393912772332642695L;
	public static String DEFAULT_REMOTE_HUB_ID = "RebeccaE-Remote-Hub";
	private Registry stReg;

	private RMISecurityManager RM;

	public RMIServer(Address my) throws UnableToBuildThere, RemoteException {
		super();
		try {

			loc = my;

			stReg = LocateRegistry.createRegistry(loc.getPort());

			if (System.getSecurityManager() == null) {
				RM = new RMISecurityManager();
				System.setSecurityManager(RM);

			}
			stReg.rebind(loc.getPostOfficeID(), this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new UnableToBuildThere(e.getMessage());
		}

	}

	public void ShutDown(boolean force) {

		try {
			UnicastRemoteObject.unexportObject(this, force);
			UnicastRemoteObject.unexportObject(stReg, force);
			System.out.println("Closed: " + stReg);

		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Response Process(Request m) throws RemoteException,
			NoRecipientException {
		// TODO Auto-generated method stub

		String r_pid = m.getRecipientName();
		if (r_pid == null) {
			throw new NoRecipientException("null at "
					+ this.getThisPostOfficeAddress());
		} else {
			Recipient r = this.getRecipient(r_pid);
			if (r == null) {
				throw new NoRecipientException(r_pid + " at "
						+ this.getThisPostOfficeAddress());
			} else {
				return new Response(r.Process(m.getMessage()));
			}
		}

	}

	@Override
	public boolean sayHi() throws RemoteException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean sayHiToRecipient(String r_pid) throws RemoteException,
			NoRecipientException {
		if (r_pid == null) {
			throw new NoRecipientException("null at "
					+ this.getThisPostOfficeAddress());
		} else {
			Recipient r = this.getRecipient(r_pid);
			if (r == null) {
				throw new NoRecipientException(r_pid + " at "
						+ this.getThisPostOfficeAddress());
			} else {

				return r.Hi();
			}
		}
	}

	public void bindRecipient(Recipient r, String r_pid)
			throws SorryDuplicateAddress {
		if (getRecipient(r_pid) == null) {
			R.put(r_pid, r);
		} else {
			throw new SorryDuplicateAddress(r_pid + " at "
					+ getThisPostOfficeAddress());
		}

	}

	public void unbindRecipient(String r_pid) {
		R.remove(r_pid);
	}

	public Address getThisPostOfficeAddress() {
		// TODO Auto-generated method stub
		return loc;
	}

	//
	Address loc;

	private Map<String, Recipient> R = new MapArray<String, Recipient>();

	public Recipient getRecipient(String recipientID) {
		return R.get(recipientID);
	}

	@Override
	public void print() {

		System.out.println("> " + this.getThisPostOfficeAddress());
		System.out.println("------------------");
		System.out.println(this.R.keys());
		System.out.println("------------------");
	}

}
