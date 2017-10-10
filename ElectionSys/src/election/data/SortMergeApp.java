package election.data;

import java.io.File;
import java.util.ArrayList;

public class SortMergeApp {

	public static void main(String[] args) {
		File sortedDirectory = new File("datafiles/sorted");
		sortedDirectory.mkdir();
		
		File databaseDirectory = new File("datafiles/database");
		databaseDirectory.mkdir();
	}
	
	/**
	 * Create string array containing the paths of all of the files
	 * with a .txt extension that have the same file name (followed by numbers) 
	 * 
	 * @param name The name of the files
	 * @param directoryPath The directory of the files
	 * @return String[]
	 */
	private static String[] createFilePathsArray(String name, String directoryPath) {
		return createFilePathsArray(name, directoryPath, ".txt");
	}
	
	/**
	 * Create string array  containing the paths of all of the files
	 * that have the same file name (followed by numbers)
	 * 
	 * @param name The name of the files
	 * @param directoryPath The directory of the files
	 * @param fileExtension The extension of the files
	 * @return String[]
	 */
	private static String[] createFilePathsArray(String name, String directoryPath, String fileExtension) {
		// Create ArrayList containing Strings
		ArrayList<String> filePaths= new ArrayList<String>();
		
		// Create list of all files in the specified directory
		File directory = new File(directoryPath);
		File[] listOfFiles = directory.listFiles();
		
		// Iterate through the listOfFiles array, store the path of the file in the filePath array list
		// if it matches the specified pattern and if it is a file
		for (int i = 0; i < listOfFiles.length; i++) {
			String path = listOfFiles[i].getPath();
			// Replace all \\ with \\\\ of the directory path for java regex pattern compatibility
			directoryPath = directory.getPath().replaceAll("\\\\", "\\\\\\\\");
			// Create regex match pattern
			String matchPattern = directoryPath + "\\\\" + name + "[0-9]*\\" + fileExtension +"$";
			if (path.matches(matchPattern))
				if (listOfFiles[i].isFile())
					filePaths.add(listOfFiles[i].getPath());
		}
		
		return filePaths.toArray(new String[filePaths.size()]);
	}
}
