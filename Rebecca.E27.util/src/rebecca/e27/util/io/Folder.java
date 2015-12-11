package rebecca.e27.util.io;

import java.io.Serializable;


import rebecca.e26.util.Map;
import rebecca.e27.util.Printable;
import rebecca.e27.util.Printer;

public class Folder implements Serializable, Printable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4390395244215355774L;

	public static final String NO_NAME_FOLDER = "-no-name-";

	Map<String, File> files = new Map<String, File>();
	Map<String, Folder> folders = new Map<String, Folder>();

	public Map<String, File> getFiles() {
		return files;
	}

	public Map<String, Folder> getSubFolders() {
		return folders;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		Printer.print(this);
	}

	public String getName() {
		if (name == null) {
			name = NO_NAME_FOLDER;
		}

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	String name = null;

}
