package rebecca.e29.util.io;

import java.io.*;

import javax.xml.bind.JAXBException;

import rebecca.e29.lang.Exception;
import rebecca.e29.xml.XMLMarshal;

public class FileManager {

	public static String SEPARATOR = System.getProperty("file.separator");

	public static byte[] readFile(String path) throws IOException {
		return readFile(path, false);
	}

	public static <T> T readXMLFile(String path, Class<T> clazz)
			throws IOException, JAXBException {
		return XMLMarshal.UnMarshall(readFile(path, false), clazz);
	}

	public static byte[] readFile(String path, boolean log) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fstream = new FileInputStream(path);
		BufferedInputStream in = new BufferedInputStream(fstream);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int bb;

		if (log) {
			System.out.println("Reading: " + path);
		}
		while ((bb = in.read()) != -1) {
			out.write(bb);
		}

		out.close();// -Djava.security.policy=C:/rmi.plc
		in.close();
		fstream.close();

		return out.toByteArray();
	}

	public static void writeFile(String path, byte[] data, boolean log)
			throws IOException {
		if (log)
			System.out.println("Writing: " + path);
		FileOutputStream fos = new FileOutputStream(path);
		fos.write(data);
		fos.flush();
		fos.close();

	}

	public static void writeFile(String path, byte[] data) throws IOException {
		writeFile(path, data, false);
	}

	public static void writeXMLFile(String path, Object o) throws IOException,
			JAXBException {
		writeFile(path, XMLMarshal.Marshall(o), false);
	}

	public static Folder LoadFolder(String path, boolean log)
			throws IOException {
		java.io.File f = new java.io.File(path);
		if (f.isDirectory()) {
			Folder F = new Folder();
			addContent2Folder(F, path, log);
			return F;
		}
		throw new Exception("This is not a folder! (" + path + ")");
	}

	public static void SaveFolder(Folder F, String path, boolean log)
			throws IOException {
		makeDir(path, log);
		java.io.File f = new java.io.File(path);
		if (f.isDirectory()) {
			for (int i = 0; i < F.getFiles().size(); i++) {
				writeFile(path + SEPARATOR + F.getFiles().getKey(i), F
						.getFiles().getVal(i), log);

			}

			for (int i = 0; i < F.getSubFolders().size(); i++) {
				// makeDir(path + SEPARATOR + F.getSubFolders().getKey(i), log);
				SaveFolder(F.getSubFolders().getVal(i), path + SEPARATOR
						+ F.getSubFolders().getKey(i), log);
			}

		} else {
			throw new Exception("This is not a folder! (" + path + ")");
		}
	}

	public static void makeDir(String path, boolean log) {
		// TODO Auto-generated method stub

		if (log)
			System.out.println("Writing: " + path);

		if (!new java.io.File(path).mkdir()) {
			// if (log)
			// System.out.println("   unable to create folder: " + path + " "
			// + "(already exist?)");

		}

	}

	public static void Delete(String Path, boolean log) {
		File f = new File(Path);
		if (log) {
			System.err.println("Deleting: " + Path);
		}
		if (f.isDirectory()) {
			String[] f_list = f.list();
			for (int i = 0; i < f_list.length; i++) {
				Delete(Path + SEPARATOR + f_list[i], log);
			}

		}
		
		if (!f.delete()) {
			System.out.println("  Unable to delete: " + Path);
		}
	}

	public static void addContent2Folder(Folder D, String path, boolean log)
			throws IOException {
		if (log) {
			System.out.println("Reading: " + path);
		}
		java.io.File f = new java.io.File(path);

		if (f.isDirectory()) {
			String[] l = f.list();

			for (int i = 0; i < l.length; i++) {
				File ff = new File(path + SEPARATOR + l[i]);
				if (ff.isFile()) {
					D.getFiles().put(ff.getName(),
							readFile(path + SEPARATOR + l[i], log));
				}
				if (ff.isDirectory()) {
					Folder F = new Folder();
					D.getSubFolders().put(ff.getName(), F);
					addContent2Folder(F, path + SEPARATOR + l[i], log);
				}

			}
		} else if (f.isFile()) {
			D.getFiles().put(f.getName(), readFile(path, log));
		}
	}

	public static void cleanFolder(String home, boolean log) {
		// TODO Auto-generated method stub
		File f = new File(home);
		if (log) {
			System.out.println("Cleaning: " + home);
		}
		if (f.isDirectory()) {
			String[] f_list = f.list();
			for (int i = 0; i < f_list.length; i++) {
				Delete(home + SEPARATOR + f_list[i], log);
			}

		}

	}

	public static void copyFolderContent(String from, String to, boolean log)
			throws IOException {
		// TODO Auto-generated method stub
		File f = new File(from);
		File t = new File(to);
		if (!f.isDirectory()) {
			throw new Exception("This is not a folder! (" + from + ")");
		}
		if (!t.isDirectory()) {
			throw new Exception("This is not a folder! (" + to + ")");
		}

		String[] l = f.list();
		for (int i = 0; i < l.length; i++) {
			File ff = new File(from + SEPARATOR + l[i]);
			if (ff.isFile()) {
				copyFile(from + SEPARATOR + l[i], to + SEPARATOR + l[i], log);
			}
			if (ff.isDirectory()) {
				copyFolderContent(from + SEPARATOR + l[i], to + SEPARATOR
						+ l[i], log);
			}

		}

	}

	private static void copyFile(String path, String t, boolean log)
			throws IOException {
		// TODO Auto-generated method stub

		FileInputStream fstream = new FileInputStream(path);
		BufferedInputStream in = new BufferedInputStream(fstream);

		FileOutputStream fos = new FileOutputStream(t);
		BufferedOutputStream out = new BufferedOutputStream(fos);
		int bb;

		if (log) {
			System.out.println("Copying: " + path + "\n      to: " + t);
		}
		while ((bb = in.read()) != -1) {
			out.write(bb);
		}
		out.flush();

		out.close();// -Djava.security.policy=C:/rmi.plc
		fos.close();
		in.close();
		fstream.close();

	}
}
