package rebecca.e27.util.io;

import java.io.Serializable;

import rebecca.e27.util.Printable;
import rebecca.e27.util.Printer;

public class File implements Serializable, Printable {

	/**
	 * 
	 */
	public File(String name) {
		this.name = name;
		this.data = null;
	}

	public File(String name, byte[] data) {
		this.name = name;
		this.data = data;
	}

	private static final long serialVersionUID = -9025412316279244423L;
	public static final String NO_NAME_FILE = "no-name";

	public String getName() {
		if (name == null) {
			name = NO_NAME_FILE;
		}

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getData() {
		if (data == null) {
			data = new byte[0];
		}
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	String name = null;
	byte[] data = null;

	@Override
	public void print() {
		// TODO Auto-generated method stub
		Printer.print(this);
	}

}
