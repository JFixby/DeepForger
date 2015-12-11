package rebecca.e25.util.io;

import java.io.File;

public class FolderCleaner {

	public static void CleanDir(String Path, boolean inform) {
		File f = new File(Path);
		String[] f_list = f.list();

		for (int i = 0; i < f_list.length; i++) {
			File ff = new File(Path + iV + f_list[i]);
			if (ff.isDirectory()) {
				CleanDir(Path + iV + f_list[i], inform);
			}
			if (inform) {
				System.out.println("Deleting " + ff);
			}
			if (ff.delete() != true) {
				System.err.println("\nUnable to delete: " + ff + "\n");
			}

		}
	}

	private static String iV = System.getProperty("file.separator");
	public static void deleteDirectory(String Path){
		File pathf = new File(Path);
		deleteDirectory(pathf);

	}
		
	
	private static void deleteDirectory(File f){
		if( f.exists() ) {
		      File[] files = f.listFiles();
		      for(int i=0; i<files.length; i++) {
		         if(files[i].isDirectory()) {
		           deleteDirectory(files[i]);
		         }
		         else {
		           files[i].delete();
		         }
		      }
		    }
		    f.delete() ;
	}
		
}
