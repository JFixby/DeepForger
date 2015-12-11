package rebecca.e10.core;

public class NObject {
	private static long GN = 2000;
	private long ID;
	private String N = null;

	public NObject(String Name) {
		if (Name == null | Name.length() == 0) {
			stampID();
		} else {
			this.N = Name;
		}
	}

	public NObject() {
		stampID();
	}

	public long GetID() {
		return this.ID;
	}

	public void SetName(String Name) {
		this.N = Name;
	}

	public String GetName() {
		if (this.N == null) {
			return "#" + this.GetID() + "#";
		}
		if (this.N.length() > 0) {
			return this.N;
		}
		return "#" + this.GetID() + "#";

	}

	public void stampID() {
		GN = GN + 1;
		this.ID = GN;

	}
}
