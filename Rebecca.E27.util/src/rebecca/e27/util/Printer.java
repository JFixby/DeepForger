package rebecca.e27.util;

import rebecca.e26.util.Map;
import rebecca.e26.util.Vector;
import rebecca.e27.util.io.File;
import rebecca.e27.util.io.Folder;
import rebecca.e27.util.lang.HollyShitException;

public class Printer {

	private static final int MAX_SIZE = 150;

	public static void print(Collection o) {
		System.out.println("-<" + o.getClass().getName()
				+ ">-----------------------");
		for (int i = 0; i < o.size(); i++) {
			System.out.println("  [" + i + "] <" + o.get(i) + ">");
		}

	}

	
	

	public static void print(Map m) {
		System.out.println("-<" + m.getClass().getName()
				+ ">-----------------------");
		for (int i = 0; i < m.size(); i++) {
			System.out.println("  [" + i + "] <" + m.getKey(i) + "> :-> <"
					+ m.getValue(i) + ">");
		}
	}

	public static void print(File file) {
		// TODO Auto-generated method stub
		System.out.println("-<" + file.getClass().getName()
				+ ">-----------------------");
		System.out.println("    name: <" + file.getName() + ">");
		byte[] d = file.getData();
		if (d.length > MAX_SIZE) {
			System.out.println("    data: " + new String(d, 0, MAX_SIZE)
					+ "....");
		} else {
			System.out.println("    data: " + new String(d, 0, d.length));
		}

	}

	public static void print(Folder folder) {
		System.out.println("-<" + folder.getClass().getName()
				+ ">-----------------------");
		System.out.println("    name: <" + folder.getName() + ">");

		folder.getFiles().print();
		folder.getSubFolders().print();
		// byte[] d = file.getData();
		// if (d.length > MAX_SIZE) {
		// System.out.println("    data: " + new String(d, 0, MAX_SIZE)
		// + "....");
		// } else {
		// System.out.println("    data: " + new String(d, 0, d.length));
		// }

		// throw new HollyShitException("Not implemented yet!");
	}

}
