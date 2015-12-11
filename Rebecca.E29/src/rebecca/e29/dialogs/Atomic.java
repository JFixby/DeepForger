package rebecca.e29.dialogs;

public abstract class Atomic implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	String name;

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

}
