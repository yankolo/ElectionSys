package election.data;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;

import election.business.interfaces.Election;
import election.business.interfaces.Tally;
import election.business.interfaces.Voter;
import util.ListUtilities;

/**
 * This application class sorts and merges all the voter files and election files.
 * In addition, it loads the tally file
 * 
 * @author Yanik Kolomatski
 *
 */
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
		
		
		// Merging Voters -------
		// Creating String[] that contains the paths of all of the sorted voter files
		String[] sortedVoterFilePathsList = createFilePathsArray("voters", "datafiles\\sorted");
		// Creating array of Voter[] that will store sorted voter lists
		Voter[][] sortedVoterLists = new Voter[sortedVoterFilePathsList.length][];
						
		// Populating sortedVoterLists with sorted voter lists
		for (int i = 0; i < sortedVoterLists.length; i++)
			sortedVoterLists[i] = ElectionFileLoader.getVoterListFromSequentialFile(sortedVoterFilePathsList[i]);
						
		// Creating mergedVoterArrayList that will be used to merge all the sorted voter lists
		@SuppressWarnings("rawtypes")
		ArrayList<Comparable[]> mergedVoterArrayList = new ArrayList<Comparable[]>();
		mergedVoterArrayList.addAll(Arrays.asList(sortedVoterLists));
					
		// Merging all the sorted voter lists
		while (mergedVoterArrayList.size() > 1) {
			@SuppressWarnings("rawtypes")
			Comparable[] merged = ListUtilities.merge(mergedVoterArrayList.get(0), mergedVoterArrayList.get(1), "datafiles\\sorted\\duplicateVoters.txt");
			
			// Set index 1 to merged list (of index 0 and 1) and remove index 0 (a neat way to merge all lists with the loop)
			mergedVoterArrayList.set(1, merged);
			mergedVoterArrayList.remove(0);
		}
		Voter[] mergedVoterList = (Voter[]) mergedVoterArrayList.get(0);
						
		// Writing mergedVoterList to file
		try {
		ListUtilities.saveListToTextFile(mergedVoterList, "datafiles\\database\\voters.txt");
		} catch (IOException e) {
			System.out.println("Error writing merged voters file!");
		}
		
		
		// Sorting Elections -------
		// Creating String[] that contains the paths of all of the unsorted elections files
		String[] electionFilePathsList = createFilePathsArray("elections", "datafiles\\unsorted");
		// Creating array of Election[] that will store election lists
		Election[][] electionLists = new Election[electionFilePathsList.length][];
		
		// Populating electionLists with election lists
		for (int i = 0; i < electionLists.length; i++)
			electionLists[i] = ElectionFileLoader.getElectionListFromSequentialFile(electionFilePathsList[i]);
		
		// Sorting all the election lists
		for (Election[] electionList: electionLists)
			ListUtilities.sort(electionList);
		
		// Writing sorted election lists to files
		for (int i = 0; i < electionLists.length; i++) {
			// To find the original name of the election file
			String originalFilePath = electionFilePathsList[i];
			
			// To be able to substring the original name of the election file
			int indexOfFileNameBeginning = originalFilePath.lastIndexOf("\\") + 1;
			
			String fileName = originalFilePath.substring(indexOfFileNameBeginning);
			try {
			ListUtilities.saveListToTextFile(electionLists[i], "datafiles\\sorted\\" + fileName);
			} catch (IOException e) {
				System.out.println("Error writing sorted election file!");
			}
		}
		
		
		// Merging elections -------
		// Creating String[] that contains the paths of all of the sorted election files
		String[] sortedElectionFilePathsList = createFilePathsArray("elections", "datafiles\\sorted");
		// Creating array of Election[] that will store sorted election lists
		Election[][] sortedElectionLists = new Election[sortedElectionFilePathsList.length][];
						
		// Populating sortedElectionLists with sorted election lists
		for (int i = 0; i < sortedElectionLists.length; i++)
			sortedElectionLists[i] = ElectionFileLoader.getElectionListFromSequentialFile(sortedElectionFilePathsList[i]);
						
		// Creating mergedElectionArrayList that will be used to merge all the sorted election lists
		@SuppressWarnings("rawtypes")
		ArrayList<Comparable[]> mergedElectionArrayList = new ArrayList<Comparable[]>();
		mergedElectionArrayList.addAll(Arrays.asList(sortedElectionLists));
					
		// Merging all the sorted election lists
		while (mergedElectionArrayList.size() > 1) {
			@SuppressWarnings("rawtypes")
			Comparable[] merged = ListUtilities.merge(mergedElectionArrayList.get(0), mergedElectionArrayList.get(1), "datafiles\\sorted\\duplicateElections.txt");
			
			// Set index 1 to merged list (of index 0 and 1) and remove index 0 (a neat way to merge all lists with the loop)
			mergedElectionArrayList.set(1, merged);
			mergedElectionArrayList.remove(0);
		}
		Election[] mergedElectionList = (Election[]) mergedElectionArrayList.get(0);
						
		// Writing mergedElectionList to file
		try {
		ListUtilities.saveListToTextFile(mergedElectionList, "datafiles\\database\\elections.txt");
		} catch (IOException e) {
			System.out.println("Error writing merged elections file!");
		}
		
		
		// Loading Tally -------
		// Creating String[] that contains the paths of all of the tally files (incase we'll have more tally files)
		String[] tallyFilePathsList = createFilePathsArray("tally", "datafiles\\unsorted");

		// Loading all tally files
		for (int i = 0; i < tallyFilePathsList.length; i++)
			ElectionFileLoader.setExistingTallyFromSequentialFile(tallyFilePathsList[i], mergedElectionList);
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
