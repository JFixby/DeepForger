package rebecca.e25.util.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FilePacker {

	public static byte[] decodeString2ByteArray(String data, int Algorithm)
			throws IOException {
		byte[] s = null;

		if (Algorithm == CODE_TEXT_FILE) {
			s = data.getBytes();
		} else if (Algorithm == CODE_BASE64) {
			// s = Base64.base64Decode(data).getBytes();
			s = rebecca.e25.util.Base64.decode(data.toCharArray());
		}

		return s;

	}

	public static File LoadFile(String path, boolean b) throws IOException {
		java.io.File f = new java.io.File(path);
		if (f.isFile()) {
			File F = new File();

			byte[] by = File2ByteArray(path, b);

			String d = codeByteArray2String(by, CODE_BASE64);

			F.setData(d);
			F.setName(f.getName());

			return F;
		}
		return null;
	}

	public static Dir LoadFolder(String path, boolean b) throws IOException {
		java.io.File f = new java.io.File(path);
		if (f.isDirectory()) {
			Dir F = new Dir();

			addAllFiles2Dir(F, path, b);

			F.setName(f.getName());

			return F;
		}
		return null;
	}

	public static void ByteArray2File(String f, byte[] data, boolean b)
			throws IOException {
		// TODO Auto-generated method stub
		if (b)
			System.out.println("Writing: " + f);
		FileOutputStream fos = new FileOutputStream(f);

		// Base64 bs = new Base64();
		// bs.e

		fos.write(data);

		// new sun.misc.BASE64Decoder().decodeBuffer(data));
		fos.flush();
		fos.close();

	}

	public static void addAllFiles2Dir(Dir D, String folderPath, boolean b)
			throws IOException {
		java.io.File f = new java.io.File(folderPath);
		if (f.isFile()) {
			addFile2Dir(D, folderPath, b);
		}
		if (f.isDirectory()) {
			String[] l = f.list();

			for (int i = 0; i < l.length; i++) {
				addFile2Dir(D, folderPath + iV + l[i], b);
			}

		}
	}

	void printfile(String p, File f) {
		System.out.println(p + f.getName() + ":" + f.getData());
		// this.Attributes.get(i)
	}

	void printfolder(String p, Dir d) {
		print0(p, d);
	}

	public void print(Dir d) {
		this.print0(" ", d);
		// TODO Auto-generated method stub

	}

	public void print(File f) {
		this.printfile(" ", f);
		// TODO Auto-generated method stub

	}

	void print0(String p, Dir d) {
		System.out.println(p + "<" + d.getName() + "> ");
		for (int i = 0; i < d.getFolder().size(); i++) {
			this.printfolder(p + "   ", d.getFolder().get(i));

		}

		for (int i = 0; i < d.getFile().size(); i++) {
			this.printfile(p + " ", d.getFile().get(i));
		}

		// // TODO Auto-generated method stub
		// String t0 = p + t + "\n";
		// t0 = t0 + p + "<" + this.getName() + ">";
		// for (int i = 0; i < this.Attributes.size(); i++) {
		// t0 = t0 + p + Attributes.getKey(i) + ":" + this.Attributes.get(i)
		// + "\n";
		//
		// }
		// for (int i = 0; i < this.SubNodes.size(); i++) {
		// print0(" " + p, SubNodes.get(i).toString());
		//
		// }
		//
		// return t0;
	}

	public static int CODE_TEXT_FILE = 1;

	public static int CODE_BASE64 = 2;

	public static String codeByteArray2String(byte[] data, int Algorithm)
			throws IOException {
		String s = "";

		if (Algorithm == CODE_TEXT_FILE) {
			s = new String(data);
		} else if (Algorithm == CODE_BASE64) {
			// System.out.println(">> " + new String(data));
			s = rebecca.e25.util.Base64.encodeToString(data, true);
			// s = new String(rebecca.e25.util.Base64.encode(data));
			// Base64.encode(data).toString();
			// s = Base64.encode(data).toString();
		}

		return s;
	}

	public static byte[] File2ByteArray(String f, boolean b) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fstream = new FileInputStream(f);
		BufferedInputStream in = new BufferedInputStream(fstream);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int bb;

		if (b) {
			System.out.println("File2ByteArray-Reading: " + f);
		}
		while ((bb = in.read()) != -1) {
			out.write(bb);
		}

		out.close();// -Djava.security.policy=C:/rmi.plc
		in.close();
		fstream.close();

		return out.toByteArray();
	}

	public static String iV = System.getProperty("file.separator");

	public static void addFile2Dir(Dir D, String filePath, boolean b)
			throws IOException {
		java.io.File f = new java.io.File(filePath);
		if (f.isFile()) {
			File F = new File();
			F.setName(f.getName());
			F.setData(codeByteArray2String(File2ByteArray(filePath, true),
					CODE_BASE64));
			// F.setData("some data");
			D.getFile().add(F);
		}
		if (f.isDirectory()) {
			Dir Dr = new Dir();
			Dr.setName(f.getName());

			String[] l = f.list();

			for (int i = 0; i < l.length; i++) {
				addFile2Dir(Dr, filePath + iV + l[i], b);
			}
			D.getFolder().add(Dr);
		}
	}

	public static void ExtractToFolder(Dir d, String Dir, boolean b)
			throws IOException {

		mkDir(Dir, b);

		List<File> l = d.getFile();
		for (int i = 0; i < l.size(); i++) {
			File f = l.get(i);
			String name = f.getName();
			String data = f.getData();

			ByteArray2File(Dir + iV + name, decodeString2ByteArray(data,
					CODE_BASE64), b);

		}

		List<Dir> j = d.getFolder();
		for (int i = 0; i < j.size(); i++) {
			Dir f = j.get(i);
			String name = f.getName();
			// mkDir(Dir + iV + name, b);
			ExtractToFolder(f, Dir + iV + name, b);
			// String2File(Dir + iV + name, data, b);

		}

	}

	public static void ExtractToFolder(Dir d, String Dir, boolean b,
			int Algorithm) throws IOException {

		mkDir(Dir, b);

		List<File> l = d.getFile();
		for (int i = 0; i < l.size(); i++) {
			File f = l.get(i);
			String name = f.getName();
			String data = f.getData();

			ByteArray2File(Dir + iV + name, decodeString2ByteArray(data,
					Algorithm), b);

		}

		List<Dir> j = d.getFolder();
		for (int i = 0; i < j.size(); i++) {
			Dir f = j.get(i);
			String name = f.getName();
			// mkDir(Dir + iV + name, b);
			ExtractToFolder(f, Dir + iV + name, b);
			// String2File(Dir + iV + name, data, b);

		}

	}

	public static void mkDir(String Dir, boolean b) {
		// TODO Auto-generated method stub

		if (b)
			System.out.println("Creating: " + Dir);

		if (!new java.io.File(Dir).mkdir()) {
			if (b)
				System.out.println("Unable to create folder: " + Dir + " "
						+ "(Already exist?)");

		}

	}

	public static String TextFile2String(String fn, boolean b)
			throws IOException {
		// TODO Auto-generated method stub
		return codeByteArray2String(File2ByteArray(fn, b), CODE_TEXT_FILE);
	}

	public static void String2TextFile(String file, String data, boolean b)
			throws IOException {
		// TODO Auto-generated method stub
		ByteArray2File(file, decodeString2ByteArray(data, CODE_TEXT_FILE), b);
		;

	}

}
