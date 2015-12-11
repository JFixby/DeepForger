package rebecca.e29.util.io;

import java.io.Serializable;

import rebecca.e29.util.Map;

public class Folder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8543333012996559782L;

	Map<String, Folder> subFolders = new Map<String, Folder>();
	Map<String, byte[]> files = new Map<String, byte[]>();

	public Map<String, Folder> getSubFolders() {
		return subFolders;
	}

	static String s(int k) {
		char c = ' ';
		char[] C = new char[k];
		for (int i = 0; i < k; i++) {
			C[i] = c;
		}
		return String.copyValueOf(C);
	}

	public Map<String, byte[]> getFiles() {
		return files;
	}

	public synchronized void print() {
		System.out.println("----<" + Folder.class.toString() + "@"
				+ Integer.toHexString(hashCode()) + ">--------------------");
		print(0);
	}

	public synchronized void print(int spc) {
		System.out.println(s(spc) + "|-------------------");
		if (!files.isEmpty()) {
			System.out.println(s(spc + 2) + "<Files>");
			for (int i = 0; i < files.size(); i++) {
				String el = files.getKey(i);
				// byte[] vl = files.getVal(i);

				System.out.println(s(spc + 4) + "[" + i + "] " + el);

			}
		}
		if (!subFolders.isEmpty()) {
			System.out.println(s(spc + 1) + "<Subfolders>");
			for (int i = 0; i < subFolders.size(); i++) {
				String el = subFolders.getKey(i);
				Folder vl = subFolders.getVal(i);
				System.out.println(s(spc + 4) + "[" + i + "] " + el);
				vl.print(spc + 6);

			}

		}
	}
}
