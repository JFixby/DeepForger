package rebecca.e25.remote;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 4065699311367662679L;
	private String popid;
	private int p;
	private String ip;

	public static String LOCALHOST = "localhost";
	
	public static int PORT_40000 = 40000;
	public static int PORT_40001 = 40001;
	public static int PORT_40002 = 40002;

	public Address(String IpAddress, int Port, String PostOfficeID) {
		popid = PostOfficeID;
		p = Port;
		ip = IpAddress;
	}

	public String getIpAddress() {
		return ip;
	}

	public int getPort() {
		return p;
	}

	public String getPostOfficeID() {
		return popid;

	}

	public String toString() {
		return "rmi://" + getIpAddress() + ":" + getPort() + "/"
				+ getPostOfficeID();

	}

	public boolean equals(Address f) {
		return this.getIpAddress().equalsIgnoreCase(f.getIpAddress())
				&& this.getPort() == f.getPort()
				&& this.getPostOfficeID().equals(f.getPostOfficeID());
	}

}
