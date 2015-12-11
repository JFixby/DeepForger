package rebecca.e26.dialogs;

import java.io.Serializable;

import rebecca.e26.util.Set;

public class Sentence implements Serializable {

	private static final long serialVersionUID = -6084299463979352987L;

	String id = "";
	String name = "";
	String info = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = nn(name);
	}

	protected String nn(String s) {
		if (s == null) {
			s = "";
		}
		return s;
	}

	public String getInfo() {
		return nn(info);
	}

	public void setInfo(String text) {
		this.info = nn(text);
	}

	public Set<Axis> getAxes() {
		return axesList;
	}

	Set<Axis> axesList = new Set<Axis>();

}
