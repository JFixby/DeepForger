package rebecca.e25.remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rebecca.e25.mail.Message;
import rebecca.e25.util.Map;
import rebecca.e25.util.MapArray;

public class RMIClient {

	Map<String, RemoteUnitTag> at = new MapArray<String, RemoteUnitTag>();

	public RemoteUnitTag getRemoteUnitTag(Address ad, String RecipientName) {
		// String k = RecipientName + "@" + ad.toString();
		//		
		String k = "rmi://" + RecipientName + "@" + ad.getIpAddress() + ":"
				+ ad.getPort() + "/" + ad.getPostOfficeID();
		RemoteUnitTag t = at.get(k);
		if (t == null) {
			t = new RTag(ad, RecipientName);
			this.at.put(k, t);
		}
		return t;

	}

	// public Letter SendMail(Letter l) throws MalformedURLException,
	// RemoteException, NotBoundException, NoRecipientException {
	//
	// PostOfficeAddress ad = l.getPostOfficeAddressTo();
	//
	// RemoteUnit r = (RemoteUnit) Naming.lookup("rmi://" + ad.getIpAddress()
	// + ":" + ad.getPort() + "/" + ad.getPostOfficeID());
	//
	// Letter L = r.ProcessMail(l);
	//
	// return L;
	//
	// }

	private class RTag implements RemoteUnitTag {

		Address d;
		String p;

		public RTag(Address ad, String pid) {
			// TODO Auto-generated constructor stub
			p = pid;
			d = ad;
		}

		public String toString() {
			return d.toString() + "#" + p;
		}

		public boolean sayHi() {
			try {
				RemoteUnit r = (RemoteUnit) Naming.lookup(d.toString());
				return r.sayHiToRecipient(p);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				return false;
			}

		}

		@Override
		public Message Process(Message M) throws NotRespondException {
			Request Q = new Request(this.p, M);
			// TODO Auto-generated method stub
			try {
				RemoteUnit r = (RemoteUnit) Naming.lookup(d.toString());
				return r.Process(Q).getMessage();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new NotRespondException(e.getMessage());
			}

		}

	}

}
