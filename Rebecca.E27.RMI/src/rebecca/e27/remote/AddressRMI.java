package rebecca.e27.remote;

import java.io.Serializable;

public class AddressRMI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -401591472313093319L;

	public AddressRMI(String host_name, int port, String rmi_server_id) {
		this.host_name = host_name;
		this.port = port;
		this.rmi_server_id = rmi_server_id;
	}

	String host_name;
	int port = 50000;
	String rmi_server_id;

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String hostName) {
		host_name = hostName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getRmi_server_id() {
		return rmi_server_id;
	}

	public void setRmi_server_id(String rmiServerId) {
		rmi_server_id = rmiServerId;
	}

}
