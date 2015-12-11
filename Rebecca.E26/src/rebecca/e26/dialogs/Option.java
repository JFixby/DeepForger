package rebecca.e26.dialogs;

import java.io.Serializable;

public class Option implements Serializable {

	String value_id = "";
	String name = "";
	String info = "";

	public String getID() {
		return value_id;
	}

	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		} else {
			return obj.toString().equals(toString());
		}

	}

	public void setID(String id) {
		if (id == null) {
			id = "";
		}
		this.value_id = id;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "id=" + value_id + " name=" + name + " info=" + info;
	}

	public void setName(String name) {
		if (name == null) {
			name = "";
		}
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		if (info == null) {
			info = "";
		}
		this.info = info;
	}

}
