package election.data;

import java.nio.file.*;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
		// Create an array that contains the paths of all the files in the specified directory
		// to later be able to go through each file and choose only the files that have the 
		// right name and extension
		Path directory = Paths.get(directoryPath);
		Path[] filesInDirectory;
		try {
			filesInDirectory = (Path[]) Files.list(directory).toArray();
		} catch (IOException ioe) {
			System.out.println("Couldn't list files in " + directoryPath);
			return new Path[] {}; // Return empty array
		}
		
		// Create an ArrayList that will only contain the paths of the files that have the right name and extension.
		// An ArrayList is used because it will be easier to dynamically add the right file paths 
		List<Path> listOfFilePaths= new ArrayList<Path>();
		
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
		Path duplicateDirectoryPath = Paths.get("datafiles/duplicate");
		
		try {
		Files.createDirectory(sortedDirectoryPath);
		Files.createDirectory(databaseDirectoryPath);
		Files.createDirectory(duplicateDirectoryPath);
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	
	private static void sortVoters() {
		// Creating Path[] that contains the paths of all the unsorted voter files. That way
		// it will be easier to load all the Voter files into one 2D array
		Path[] pathsToAllVoterFiles = createFilePathsArray("voters", "datafiles/unsorted");
		// Creating a 2D array that will contain all the unsorted Voter files (but as actual Voter objects).
		// That way, it will be easier to manipulate all the files at once.
		Voter[][] arraysOfVoters = new Voter[pathsToAllVoterFiles.length][];
		
		// Populating arraysOfVoters with arrays that contain voters from the unsorted Voter files
		for (int i = 0; i < arraysOfVoters.length; i++)
			try {
			arraysOfVoters[i] = ElectionFileLoader.getVoterListFromSequentialFile(pathsToAllVoterFiles[i].toString());
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		
		// Sorting all the Voter arrays
		for (Voter[] arrayOfVoters: arraysOfVoters)
			ListUtilities.sort(arrayOfVoters);
		
		// Writing the sorted voter arrays to files
		for (int i = 0; i < arraysOfVoters.length; i++) {
			// Finding the original name of the voter file to be able to create the file
			String filename = pathsToAllVoterFiles[i].getFileName().toString();
			
			try {
			ListUtilities.saveListToTextFile(arraysOfVoters[i], "datafiles/sorted/" + filename);
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}
	}
	
	private static void mergeVoters() {
		// Creating Path[] that contains the paths of all the sorted voter files. That way
		// it will be easier to load all the Voter files into one 2D array
		Path[] pathsToAllSortedVoterFiles = createFilePathsArray("voters", "datafiles/sorted");
		// Creating a 2D array that will contain all the sorted Voter files (but as actual Voter objects).
		// That way, it will be easier to manipulate all the files at once.
		Voter[][] arraysOfSortedVoters = new Voter[pathsToAllSortedVoterFiles.length][];
						
		// Populating arraysOfSortedVoters with arrays that contains voters from the sorted Voter files
		for (int i = 0; i < arraysOfSortedVoters.length; i++)
			try {
			arraysOfSortedVoters[i] = ElectionFileLoader.getVoterListFromSequentialFile(pathsToAllSortedVoterFiles[i].toString());
			} catch (IOException ioe){
				System.out.println(ioe.getMessage());
			}
						
		// Creating listOfVoterArrays that will be used to merge all the sorted voter arrays
		List<Comparable[]> listOfVoterArrays = new ArrayList<Comparable[]>();
		listOfVoterArrays.addAll(Arrays.asList(arraysOfSortedVoters));
					
		/* 
		 * Merging all the sorted voter arrays. 
		 * 
		 * The algorithm works as follows (example with 4 voter arrays):
		 * 		listOfVoterArrays --> [voters1, voters2, voters3, voters4]
		 * 
		 * 		First iteration: merges voters1 and voters2, puts merged voters at index 1, then removes index 0
		 * 			listOfVoterArrays --> [voters1, merged, voters3, voters4]
		 * 			listOfVoterArrays --> [merged, voters3, voters4]
		 * 
		 * 		Second iteration: merges "merged" and voters3, puts merged voters at index 1, then removes index 0
		 * 			listOfVoterArrays --> [merged, merged, voters4]
		 * 			listOfVoterArrays --> [merged, voters4]
		 * 
		 * 		Third iteration: merges "merged" and voters4, puts merged voters at index 1, then removes index 0
		 * 			listOfVoterArrays --> [merged, merged]
		 * 			listOfVoterArrays --> [merged]
		 * 
		 * 		After the algorithm, the array of the merged voters is at index 0
		 * 			mergedVoters --> listOfVoterArrays[0]
		 * 			mergedVoters --> merged
		 */
		while (listOfVoterArrays.size() > 1) {
			Comparable[] merged = ListUtilities.merge(listOfVoterArrays.get(0), listOfVoterArrays.get(1), "datafiles/sorted/duplicateVoters.txt");
			
			// Set index 1 to merged list (of index 0 and 1) and remove index 0 (a neat way to merge all lists with the loop)
			listOfVoterArrays.set(1, merged);
			listOfVoterArrays.remove(0);
		}
		Voter[] mergedVoters = (Voter[]) listOfVoterArrays.get(0);
						
		// Writing mergedVoters to file
		try {
		ListUtilities.saveListToTextFile(mergedVoters, "datafiles/database/voters.txt");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	};
	
	private static void sortElections() {
		// Creating Path[] that contains the paths of all the unsorted election files. That way
		// it will be easier to load all the Election files into one 2D array
		Path[] pathsToAllElectionFiles = createFilePathsArray("elections", "datafiles/unsorted");
		// Creating a 2D array that will contain all the unsorted Election files (but as actual Election objects).
		// That way, it will be easier to manipulate all the files at once.
		Election[][] arraysOfElections = new Election[pathsToAllElectionFiles.length][];
		
		// Populating arraysOfElections with arrays that contain elections from the unsorted Election files
		for (int i = 0; i < arraysOfElections.length; i++)
			try {
			arraysOfElections[i] = ElectionFileLoader.getElectionListFromSequentialFile(pathsToAllElectionFiles[i].toString());
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		
		// Sorting all the Election arrays
		for (Election[] arrayOfElections: arraysOfElections)
			ListUtilities.sort(arrayOfElections);
		
		// Writing the sorted election arrays to files
		for (int i = 0; i < arraysOfElections.length; i++) {
			// Finding the original name of the election file to be able to create file
			String filename = pathsToAllElectionFiles[i].getFileName().toString();
			
			try {
			ListUtilities.saveListToTextFile(arraysOfElections[i], "datafiles/sorted/" + filename);
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}
	}
	
	private static void mergeElections() {
		// Creating Path[] that contains the paths of all the sorted election files. That way
		// it will be easier to load all the Election files into one 2D array
		Path[] pathsToAllSortedElectionFiles = createFilePathsArray("elections", "datafiles/sorted");
		// Creating a 2D array that will contain all the sorted Election files (but as actual Election objects).
		// That way, it will be easier to manipulate all the files at once.
		Election[][] arraysOfSortedElections = new Election[pathsToAllSortedElectionFiles.length][];
						
		// Populating arraysOfSortedElections with arrays that contains elections from the sorted Election files
		for (int i = 0; i < arraysOfSortedElections.length; i++)
			try {
			arraysOfSortedElections[i] = ElectionFileLoader.getElectionListFromSequentialFile(pathsToAllSortedElectionFiles[i].toString());
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
						
		// Creating listOfElectionArrays that will be used to merge all the sorted election arrays
		List<Comparable[]> listOfElectionArrays = new ArrayList<Comparable[]>();
		listOfElectionArrays.addAll(Arrays.asList(arraysOfSortedElections));
					
		/* 
		 * Merging all the sorted election arrays. 
		 * 
		 * The algorithm works as follows (example with 4 election arrays):
		 * 		listOfElectionArrays --> [elections1, elections2, elections3, elections4]
		 * 
		 * 		First iteration: merges elections1 and elections2, puts merged elections at index 1, then removes index 0
		 * 			listOfElectionArrays --> [elections1, merged, elections3, elections4]
		 * 			listOfElectionArrays --> [merged, elections3, elections4]
		 * 
		 * 		Second iteration: merges "merged" and election3, puts merged elections at index 1, then removes index 0
		 * 			listOfElectionArrays --> [merged, merged, elections4]
		 * 			listOfElectionArrays --> [merged, elections4]
		 * 
		 * 		Third iteration: merges "merged" and election4, puts merged elections at index 1, then removes index 0
		 * 			listOfElectionArrays --> [merged, merged]
		 * 			listOfElectionArrays --> [merged]
		 * 
		 * 		After the algorithm, the array of the merged elections is at index 0
		 * 			mergedElections --> listOfElectionArrays[0]
		 * 			mergedElections --> merged
		 */
		while (listOfElectionArrays.size() > 1) {
			Comparable[] merged = ListUtilities.merge(listOfElectionArrays.get(0), listOfElectionArrays.get(1), "datafiles\\sorted\\duplicateElections.txt");
			
			// Set index 1 to merged list (of index 0 and 1) and remove index 0 (a neat way to merge all lists with the loop)
			listOfElectionArrays.set(1, merged);
			listOfElectionArrays.remove(0);
		}
		Election[] mergedElections = (Election[]) listOfElectionArrays.get(0);
						
		// Writing mergedElections to file
		try {
		ListUtilities.saveListToTextFile(mergedElections, "datafiles/database/elections.txt");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	
	private static void loadTally () {
		// Creating Path[] that contains the paths of all the tally files. That way
		// it will be easier to load all the Tally files into one 2D array (incase we'll have more tally files)
		Path[] pathsToAllTallyFiles = createFilePathsArray("tally", "datafiles/unsorted");
		// Load the merged elections file to be able to set the tally (a method which accepts an array of elections)
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
