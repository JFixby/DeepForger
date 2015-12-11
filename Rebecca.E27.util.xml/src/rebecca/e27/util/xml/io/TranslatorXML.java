package rebecca.e27.util.xml.io;

import java.util.List;

import rebecca.e27.util.Base64;
import rebecca.e27.util.io.File;
import rebecca.e27.util.io.Folder;

public class TranslatorXML {

	public static final String MODE_TEXT = "MODE_TEXT";
	public static final String MODE_BASE64 = "MODE_BASE64";

	public XFile transform(File f, String mode) {
		XFile F = new XFile();
		F.setName(f.getName());

		if (MODE_TEXT.equalsIgnoreCase(mode)) {
			F.setData(new String(f.getData()));
			F.setType(MODE_TEXT);
		} else if (MODE_BASE64.equalsIgnoreCase(mode)) {
			F.setData(new String(Base64.encode(f.getData())));
			F.setType(MODE_BASE64);
		}

		return F;
	}

	public File transform(XFile F) {
		File f = new File(F.getName());
		f.setName(F.getName());

		if (MODE_TEXT.equalsIgnoreCase(F.getType())) {
			f.setData(F.getData().getBytes());
			// F.setData(new String(f.getData()));
			// F.setType(MODE_TEXT);
		} else if (MODE_BASE64.equalsIgnoreCase(F.getType())) {
			f.setData(Base64.decode(F.getData().getBytes()));
		}

		return f;
	}

	public Folder transform(XFolder F) {
		Folder f = new Folder();
		f.setName(F.getName());
		List<XFile> fl = F.getFiles();
		for (int i = 0; i < fl.size(); i++) {
			File f0 = transform(fl.get(i));
			f.getFiles().put(f0.getName(), f0);
		}
		List<XFolder> ff = F.getFolders();
		for (int i = 0; i < ff.size(); i++) {
			Folder f0 = transform(ff.get(i));
			f.getSubFolders().put(f0.getName(), f0);
		}

		return f;
	}

	public XFolder transform(Folder F, String mode) {
		XFolder f = new XFolder();
		f.setName(F.getName());
		for (int i = 0; i < F.getFiles().size(); i++) {
			XFile f0 = transform(F.getFiles().getValue(i), mode);
			f0.setName(F.getFiles().getKey(i));
			f.getFiles().add(f0);
		}
		for (int i = 0; i < F.getSubFolders().size(); i++) {
			XFolder f0 = transform(F.getSubFolders().getValue(i), mode);
			f0.setName(F.getSubFolders().getKey(i));
			f.getFolders().add(f0);
		}
		return f;
	}

}
