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
		createDirectories();
		sortVoters();
		mergeVoters();
		sortElections();
		mergeElections();
		loadTally();
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
		Path[] filesInDirectory;
		try {
			filesInDirectory = (Path[]) Files.list(directory).toArray();
		} catch (IOException ioe) {
			System.out.println("Couldn't list files in " + directoryPath);
			return new Path[] {}; // Return empty array
		}
		
		// Create ArrayList that will contain the paths of the files that have the right name and extension
		ArrayList<Path> listOfFilePaths= new ArrayList<Path>();
		
		// Store the path of the files that match the specified filename specification 
		// in filePathsList
		for (Path file: filesInDirectory) {
			String filename = file.getFileName().toString();
			if (filename.matches(name + "[0-9]*") && filename.endsWith(fileExtension))
				listOfFilePaths.add(file);
		}
		
		// Transform ArrayList to Array to be able to return an array of Path
		Path[] arrayOfFilePaths = new Path[listOfFilePaths.size()];
		arrayOfFilePaths = listOfFilePaths.toArray(arrayOfFilePaths);
		
		return arrayOfFilePaths;
	}
	
	private static void createDirectories() {
		Path sortedDirectoryPath = Paths.get("datafiles/sorted");
		Path databaseDirectoryPath = Paths.get("datafiles/database");
		
		try {
		Files.createDirectory(sortedDirectoryPath);
		Files.createDirectory(databaseDirectoryPath);
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	
	private static void sortVoters() {
		// Creating Path[] that contains the paths of all unsorted voter files
		Path[] pathsToAllVoterFiles = createFilePathsArray("voters", "datafiles/unsorted");
		// Creating array of Voter[] that will store all voter lists
		Voter[][] arraysOfVoters = new Voter[pathsToAllVoterFiles.length][];
		
		// Populating voterLists with voter lists
		for (int i = 0; i < arraysOfVoters.length; i++)
			try {
			arraysOfVoters[i] = ElectionFileLoader.getVoterListFromSequentialFile(pathsToAllVoterFiles[i].toString());
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		
		// Sorting all the voter lists
		for (Voter[] arrayOfVoters: arraysOfVoters)
			ListUtilities.sort(arrayOfVoters);
		
		// Writing sorted voter lists to files
		for (int i = 0; i < arraysOfVoters.length; i++) {
			// Find the original name of the voter file to be able to create file
			String filename = pathsToAllVoterFiles[i].getFileName().toString();
			
			try {
			ListUtilities.saveListToTextFile(arraysOfVoters[i], "datafiles/sorted/" + filename);
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}
	}
	
	private static void mergeVoters() {
		// Creating Path[] that contains the paths of all sorted voter files
		Path[] pathsToAllSortedVoterFiles = createFilePathsArray("voters", "datafiles/sorted");
		// Creating array of Voter[] that will store all sorted voter lists
		Voter[][] arraysOfSortedVoters = new Voter[pathsToAllSortedVoterFiles.length][];
						
		// Populating sortedVoterLists with sorted voter lists
		for (int i = 0; i < arraysOfSortedVoters.length; i++)
			try {
			arraysOfSortedVoters[i] = ElectionFileLoader.getVoterListFromSequentialFile(pathsToAllSortedVoterFiles[i].toString());
			} catch (IOException ioe){
				System.out.println(ioe.getMessage());
			}
						
		// Creating mergedVoterArrayList that will be used to merge all the sorted voter lists
		ArrayList<Comparable[]> listOfVoterArrays = new ArrayList<Comparable[]>();
		listOfVoterArrays.addAll(Arrays.asList(arraysOfSortedVoters));
					
		// Merging all the sorted voter lists
		while (listOfVoterArrays.size() > 1) {
			Comparable[] merged = ListUtilities.merge(listOfVoterArrays.get(0), listOfVoterArrays.get(1), "datafiles/sorted/duplicateVoters.txt");
			
			// Set index 1 to merged list (of index 0 and 1) and remove index 0 (a neat way to merge all lists with the loop)
			listOfVoterArrays.set(1, merged);
			listOfVoterArrays.remove(0);
		}
		Voter[] mergedVoters = (Voter[]) listOfVoterArrays.get(0);
						
		// Writing mergedVoterList to file
		try {
		ListUtilities.saveListToTextFile(mergedVoters, "datafiles/database/voters.txt");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	};
	
	private static void sortElections() {
		// Creating Path[] that contains the paths of all unsorted elections files
		Path[] pathsToAllElectionFiles = createFilePathsArray("elections", "datafiles/unsorted");
		// Creating array of Path[] that will store all election lists
		Election[][] arraysOfElections = new Election[pathsToAllElectionFiles.length][];
		
		// Populating electionLists with election lists
		for (int i = 0; i < arraysOfElections.length; i++)
			try {
			arraysOfElections[i] = ElectionFileLoader.getElectionListFromSequentialFile(pathsToAllElectionFiles[i].toString());
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		
		// Sorting all the election lists
		for (Election[] arrayOfElections: arraysOfElections)
			ListUtilities.sort(arrayOfElections);
		
		// Writing sorted election lists to files
		for (int i = 0; i < arraysOfElections.length; i++) {
			// Find the original name of the voter file to be able to create file
			String filename = pathsToAllElectionFiles[i].getFileName().toString();
			
			try {
			ListUtilities.saveListToTextFile(arraysOfElections[i], "datafiles/sorted/" + filename);
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}
	}
	
	private static void mergeElections() {
		// Creating Path[] that contains the paths of all sorted election files
		Path[] pathsToAllSortedElectionFiles = createFilePathsArray("elections", "datafiles/sorted");
		// Creating array of Election[] that will store all sorted election lists
		Election[][] arraysOfSortedElections = new Election[pathsToAllSortedElectionFiles.length][];
						
		// Populating sortedElectionLists with sorted election lists
		for (int i = 0; i < arraysOfSortedElections.length; i++)
			try {
			arraysOfSortedElections[i] = ElectionFileLoader.getElectionListFromSequentialFile(pathsToAllSortedElectionFiles[i].toString());
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
						
		// Creating mergedElectionArrayList that will be used to merge all the sorted election lists
		ArrayList<Comparable[]> listOfElectionArrays = new ArrayList<Comparable[]>();
		listOfElectionArrays.addAll(Arrays.asList(arraysOfSortedElections));
					
		// Merging all the sorted election lists
		while (listOfElectionArrays.size() > 1) {
			Comparable[] merged = ListUtilities.merge(listOfElectionArrays.get(0), listOfElectionArrays.get(1), "datafiles\\sorted\\duplicateElections.txt");
			
			// Set index 1 to merged list (of index 0 and 1) and remove index 0 (a neat way to merge all lists with the loop)
			listOfElectionArrays.set(1, merged);
			listOfElectionArrays.remove(0);
		}
		Election[] mergedElections = (Election[]) listOfElectionArrays.get(0);
						
		// Writing mergedElectionList to file
		try {
		ListUtilities.saveListToTextFile(mergedElections, "datafiles/database/elections.txt");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	
	private static void loadTally () {
		// Creating Path[] that contains the paths of all of the tally files (incase we'll have more tally files)
		Path[] pathsToAllTallyFiles = createFilePathsArray("tally", "datafiles/unsorted");
		// Load merged election list
		try {
		Election[] mergedElections = ElectionFileLoader.getElectionListFromSequentialFile("datafiles/database/elections.txt");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		
		// Loading all tally files
		for (int i = 0; i < pathsToAllTallyFiles.length; i++)
			try {
			ElectionFileLoader.setExistingTallyFromSequentialFile(pathsToAllTallyFiles[i].toString(), mergedElections);
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
	}
}
