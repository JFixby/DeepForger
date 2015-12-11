package rebecca.e26.dialogs;

import java.io.Serializable;

import javax.xml.bind.JAXBException;

import rebecca.e26.dialogs.xmlmatrix.XPassage;
import rebecca.e26.xml.XMLTransmitter;

public abstract class Axis implements Serializable {

	private String name = "xxx";
	private String info = "";
	private String id = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInfo() {
		if (info == null) {
			return "";
		}
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	 public String getName() {
	 return name;
	 }

	public void setName(String name) {
		this.name = name;
	}

}
