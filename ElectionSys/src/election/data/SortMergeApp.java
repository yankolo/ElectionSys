package election.data;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;

import election.business.interfaces.Voter;
import util.ListUtilities;

public class SortMergeApp {

	public static void main(String[] args) {
		File sortedDirectory = new File("datafiles/sorted");
		sortedDirectory.mkdir();
		
		File databaseDirectory = new File("datafiles/database");
		databaseDirectory.mkdir();
		
		
		// Sorting Voters -------
		// Creating String[] that contains the paths of all of the unsorted voter files
		String[] voterFilePathsList = createFilePathsArray("voters", "datafiles\\unsorted");
		// Creating array of Voter[] that will store voter lists
		Voter[][] voterLists = new Voter[voterFilePathsList.length][];
		
		// Populating voterLists with voter lists
		for (int i = 0; i < voterLists.length; i++)
			voterLists[i] = ElectionFileLoader.getVoterListFromSequentialFile(voterFilePathsList[i]);
		
		// Sorting all the voter lists
		for (Voter[] voterList: voterLists)
			ListUtilities.sort(voterList);
		
		// Writing sorted voter lists to files
		for (int i = 0; i < voterLists.length; i++) {
			// To find the original name of the voter file
			String originalFilePath = voterFilePathsList[i];
			
			// To be able to substring the original name of the voter file
			int indexOfFileNameBeginning = originalFilePath.lastIndexOf("\\") + 1;
			
			String fileName = originalFilePath.substring(indexOfFileNameBeginning);
			try {
			ListUtilities.saveListToTextFile(voterLists[i], "datafiles\\sorted\\" + fileName);
			} catch (IOException e) {
				System.out.println("Error writing sorted voters file!");
			}
		}
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
