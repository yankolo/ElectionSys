package election.data;

import java.nio.file.*;
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
		Path sortedDirectory = Paths.get("datafiles/sorted");
		Files.createDirectory(sortedDirectory);
		
		Path databaseDirectory = Paths.get("datafiles/database");
		Files.createDirectory(databaseDirectory);
		
		
		// Sorting Voters -------
		// Creating Path[] that contains the paths of all unsorted voter files
		Path[] voterFilePathsList = createFilePathsArray("voters", "datafiles/unsorted");
		// Creating array of Voter[] that will store all voter lists
		Voter[][] voterLists = new Voter[voterFilePathsList.length][];
		
		// Populating voterLists with voter lists
		for (int i = 0; i < voterLists.length; i++)
			voterLists[i] = ElectionFileLoader.getVoterListFromSequentialFile(voterFilePathsList[i].toString());
		
		// Sorting all the voter lists
		for (Voter[] voterList: voterLists)
			ListUtilities.sort(voterList);
		
		// Writing sorted voter lists to files
		for (int i = 0; i < voterLists.length; i++) {
			// Find the original name of the voter file to be able to create file
			String filename = voterFilePathsList[i].getFileName().toString();
			
			try {
			ListUtilities.saveListToTextFile(voterLists[i], "datafiles/sorted/" + filename);
			} catch (IOException e) {
				System.out.println("Error writing sorted voters file!");
			}
		}
		
		
		// Merging Voters -------
		// Creating Path[] that contains the paths of all sorted voter files
		Path[] sortedVoterFilePathsList = createFilePathsArray("voters", "datafiles/sorted");
		// Creating array of Voter[] that will store all sorted voter lists
		Voter[][] sortedVoterLists = new Voter[sortedVoterFilePathsList.length][];
						
		// Populating sortedVoterLists with sorted voter lists
		for (int i = 0; i < sortedVoterLists.length; i++)
			sortedVoterLists[i] = ElectionFileLoader.getVoterListFromSequentialFile(sortedVoterFilePathsList[i].toString());
						
		// Creating mergedVoterArrayList that will be used to merge all the sorted voter lists
		ArrayList<Comparable[]> mergedVoterArrayList = new ArrayList<Comparable[]>();
		mergedVoterArrayList.addAll(Arrays.asList(sortedVoterLists));
					
		// Merging all the sorted voter lists
		while (mergedVoterArrayList.size() > 1) {
			Comparable[] merged = ListUtilities.merge(mergedVoterArrayList.get(0), mergedVoterArrayList.get(1), "datafiles/sorted/duplicateVoters.txt");
			
			// Set index 1 to merged list (of index 0 and 1) and remove index 0 (a neat way to merge all lists with the loop)
			mergedVoterArrayList.set(1, merged);
			mergedVoterArrayList.remove(0);
		}
		Voter[] mergedVoterList = (Voter[]) mergedVoterArrayList.get(0);
						
		// Writing mergedVoterList to file
		try {
		ListUtilities.saveListToTextFile(mergedVoterList, "datafiles/database/voters.txt");
		} catch (IOException e) {
			System.out.println("Error writing merged voters file!");
		}
		
		
		// Sorting Elections -------
		// Creating Path[] that contains the paths of all unsorted elections files
		Path[] electionFilePathsList = createFilePathsArray("elections", "datafiles/unsorted");
		// Creating array of Path[] that will store all election lists
		Election[][] electionLists = new Election[electionFilePathsList.length][];
		
		// Populating electionLists with election lists
		for (int i = 0; i < electionLists.length; i++)
			electionLists[i] = ElectionFileLoader.getElectionListFromSequentialFile(electionFilePathsList[i].toString());
		
		// Sorting all the election lists
		for (Election[] electionList: electionLists)
			ListUtilities.sort(electionList);
		
		// Writing sorted election lists to files
		for (int i = 0; i < electionLists.length; i++) {
			// Find the original name of the voter file to be able to create file
			String filename = electionFilePathsList[i].getFileName().toString();
			
			try {
			ListUtilities.saveListToTextFile(electionLists[i], "datafiles/sorted/" + filename);
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
	 * Create Path array containing the paths of all of the files
	 * with a .txt extension that have the same file name (followed by numbers) 
	 * 
	 * @param name The name of the files
	 * @param directoryPath The directory of the files
	 * @return Path[]
	 */
	private static Path[] createFilePathsArray(String name, String directoryPath) {
		return createFilePathsArray(name, directoryPath, ".txt");
	}
	
	/**
	 * Create Path array  containing the paths of all of the files
	 * that have the same file name (followed by numbers)
	 * 
	 * @param name The name of the files
	 * @param directoryPath The directory of the files
	 * @param fileExtension The extension of the files
	 * @return Path[]
	 */
	private static Path[] createFilePathsArray(String name, String directoryPath, String fileExtension) {
		// Create list of all files in the specified directory
		Path directory = Paths.get(directoryPath);
		Path[] listOfFiles;
		try {
			listOfFiles = (Path[]) Files.list(directory).toArray();
		} catch (IOException e) {
			System.out.println("Couldn't list files in " + directoryPath);
			return new Path[] {}; // Return empty array
		}
		
		// Create ArrayList that will contain the paths of the files that have the right name and extension
		ArrayList<Path> filePaths= new ArrayList<Path>();
		
		// Store the path of the files that match the specified filename specification 
		// in the filePath ArrayList
		for (Path file: listOfFiles) {
			String filename = file.getFileName().toString();
			if (filename.matches(name + "[0-9]*") && filename.endsWith(fileExtension))
				filePaths.add(file);
		}
		
		// Transform ArrayList to Array to be able to return an array of Path
		Path[] pathsArray = new Path[filePaths.size()];
		pathsArray = filePaths.toArray(pathsArray);
		
		return pathsArray;
	}
}
