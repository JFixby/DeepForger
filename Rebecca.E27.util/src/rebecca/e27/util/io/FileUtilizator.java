package rebecca.e27.util.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import rebecca.e26.util.Map;
import rebecca.e27.util.lang.NotFileException;
import rebecca.e27.util.lang.NotFolderException;

public class FileUtilizator {

	public static void DeleteFile(String Path, boolean inform) {
		java.io.File f = new java.io.File(Path);

		if (inform) {
			System.err.println("Deleting " + f);
		}

		if (f.isDirectory()) {
			String[] f_list = f.list();
			for (int i = 0; i < f_list.length; i++) {
				DeleteFile(Path + separator + f_list[i], inform);
			}
		}

		if (f.delete() != true) {
			System.err.println("Unable to delete: " + f + "\n");
		}

	}

	public static File loadFile(String filepath, boolean debug)
			throws IOException, NotFileException {
		java.io.File f = new java.io.File(filepath);
		if (f.isFile()) {
			File F = new File(f.getName(), loadFileData(filepath, debug));
			return F;
		}
		throw new NotFileException();

	}

	public static void saveFile(String filePath, File f, boolean debug)
			throws IOException {
		saveFileData(filePath, f.getData(), debug);
	}

	public static byte[] loadFileData(String filepath, boolean debug)
			throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fstream = new FileInputStream(filepath);
		BufferedInputStream in = new BufferedInputStream(fstream);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int bb;

		if (debug) {
			System.out.println("loadFileData-Reading: " + filepath);
		}
		while ((bb = in.read()) != -1) {
			out.write(bb);
		}

		out.close();// -Djava.security.policy=C:/rmi.plc
		in.close();
		fstream.close();

		return out.toByteArray();
	}

	public static void saveFileData(String filePath, byte[] data, boolean debug)
			throws IOException {
		// TODO Auto-generated method stub
		if (debug)
			System.err.println("Writing: " + filePath);
		FileOutputStream fos = new FileOutputStream(filePath);
		fos.write(data);
		fos.flush();
		fos.close();

	}

	public static String separator = System.getProperty("file.separator");

	public static Folder loadFolder(String filepath, boolean debug)
			throws IOException, NotFolderException {
		java.io.File f = new java.io.File(filepath);
		if (f.isDirectory()) {
			Folder F = new Folder();
			loadFolder(filepath, F, debug);
			F.setName(f.getName());
			return F;
		}
		throw new NotFolderException();

	}

	public static void makeDir(String Dir, boolean b) {
		// TODO Auto-generated method stub

		if (b)
			System.out.println("Creating: " + Dir);

		if (!new java.io.File(Dir).mkdir()) {
			if (b)
				System.out.println("Unable to create folder: " + Dir + " "
						+ "(Already exist?)");
		}
	}

	public static void loadFolder(String filepath, Folder F, boolean debug)
			throws IOException, NotFolderException {
		java.io.File f = new java.io.File(filepath);
		if (f.isDirectory()) {
			// File F = new File(f.getName(), loadFileData(filepath, debug));
			String[] list = f.list();
			for (int i = 0; i < list.length; i++) {
				String aim = filepath + separator + list[i];
				java.io.File ff = new java.io.File(aim);

				if (ff.isFile()) {
					File f0 = loadFile(aim, debug);
					F.getFiles().put(f0.getName(), f0);
				} else if (ff.isDirectory()) {
					Folder f0 = loadFolder(aim, debug);
					F.getSubFolders().put(f0.getName(), f0);
				}
			}

		} else {
			throw new NotFolderException();
		}
	}

	public static void saveFolder(String dirpath, Folder F, boolean debug)
			throws IOException {

		makeDir(dirpath, debug);

		Map<String, File> l = F.getFiles();
		for (int i = 0; i < l.size(); i++) {
			File f = l.getValue(i);
			saveFile(dirpath + separator + l.getKey(i), f, debug);
		}

		Map<String, Folder> j = F.getSubFolders();
		for (int i = 0; i < j.size(); i++) {
			Folder f = j.getValue(i);
			saveFolder(dirpath + separator + j.getKey(i), f, debug);

		}

	}

}
