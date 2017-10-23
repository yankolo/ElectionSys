package election.data;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;

import election.business.DawsonElectionFactory;
import election.business.interfaces.Voter;
import election.business.interfaces.Election;

public class ElectionFileLoader {
	private ElectionFileLoader() {

	}

	
	/**
	 * The getVoterListFromSequentialFile reads a voter file and puts every line in
	 * a list of Strings. It then converts the list of Strings into a String[]
	 * array. And it calls the createVoterArray to create a Voter[] array by passing
	 * the String[] array. Finally, it returns the Voter[] array returned by the
	 * createVoterArray method.
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Voter[] getVoterListFromSequentialFile(String filename) throws IOException {
		Path file = Paths.get(filename);
		Charset uncoding = Charset.forName("UTF-8");

		try {
			List<String> lines = Files.readAllLines(file, uncoding);
			String[] linesArray = new String[lines.size()];

			linesArray = lines.toArray(linesArray);

			return createVoterArray(linesArray, filename);
		} catch (IOException ioe) {
			throw new IOException("Error reading lines from file: " + filename);
		}

	}
		
	/**
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Election[] getElectionListFromSequentialFile(String filename) throws IOException {
		// TODO Code Method
	}
	
	/**
	 * 
	 * @param filename
	 * @param elections
	 * @throws IOException
	 */
	public static void setExistingTallyFromSequentialFile (String filename, Election[] elections) 
			throws IOException {
		// TODO Code Method
	}
	
	/**
	 * The createVoterArray takes a String[] linesArray (that contains every line
	 * from the Voter text file) and the Voter filename so that the message error
	 * can be specific by printing the filename. The createVoterArray method creates
	 * a 2D array (String[][] votersInformation) that has the number of lines from
	 * the linesArray as the number of rows. As for the columns they are created by
	 * using the split method on the linesArray. The result of the 2D array
	 * (String[][] votersInformation): each row has a Voter and each column contains
	 * the email, first name, last name and postal code of the Voter. The String[][]
	 * votersInformation then
	 * 
	 * @param linesArray
	 * @return
	 */
	private static Voter[] createVoterArray(String[] linesArray, String filename) {
		String[][] votersInformation = new String[linesArray.length][];

		/*
		 * validLines keeps track of the valid lines that have 4 elements (email, first
		 * name, last name, postal code)
		 */
		int validLines = 0;

		/*
		 * The for loop, loops through linesArray going line by line and splitting on
		 * the "*". Each line is assigned to a row in the votersInformation array and
		 * each column contains the elements returned by the split method. After each
		 * assignment, rows from votersInformation must contain 4 columns (email, first
		 * name, last name, postal code). If the row doesn't have 4 columns, and error
		 * is printed and the votersInformation array stays at the same row while
		 * linesArray keeps going to the next line. If the row of has 4 columns,
		 * validLines is incremented. The result of this for loop, is a 2D array
		 * (String[][] votersInformation) with valid lines (containing 4 columns) at the
		 * top and Invalid lines at the bottom (containing other than 4 columns) with
		 * the number of valid lines.
		 */
		for (int i = 0, line = 0; line < linesArray.length; i++, line++) {
			votersInformation[i] = linesArray[line].split("[*]");
			if (votersInformation[i].length != 4) {
				System.err.println("Format Error - Number of Elements Not Correct at Line " + (line + 1) + ": "
						+ linesArray[line] + "\nFrom Filename: " + filename);
				i--;
			} else {
				validLines++;
			}
		}

		/*
		 * A Voter[] array is created with the number of valid lines as the size of the
		 * array.
		 */
		Voter[] voter = new Voter[validLines];

		// nonNullVoters keeps track of the successfully created Voters.
		int nonNullVoters = 0;

		/*
		 * The for loop, loops through each row of the voterInformation array and
		 * assigns the the values of each columns to Strings. A Voter is then created by
		 * invoking the DawsonElectionFactory constructor and passing to it (first name,
		 * last name, email, postal code). If the Voter is fully created, nonNullVoters
		 * is increased.
		 */
		for (int i = 0; i < voter.length; i++) {

			try {
				String email = votersInformation[i][0];
				String firstName = votersInformation[i][1];
				String lastName = votersInformation[i][2];
				String postal = votersInformation[i][3];

				voter[i] = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance(firstName, lastName, email, postal);
				nonNullVoters++;
			} catch (IllegalArgumentException iae) {
				System.out.println("\t" + iae.getMessage());
			}
		}

		// nonNullVoterArray contains valid Voters filled to its max capacity.
		Voter[] nonNullVotersArray = filledValidVotersArray(voter, nonNullVoters);
		return nonNullVotersArray;
	}

	/*
	 * The filledValidVotersArray takes a Voter[] voter and the number of non null
	 * voters (int nonNullVoters). It creates a Voter[] array (nonNullVotersArray)
	 * with the number of non null voters as the size. It then loops through the
	 * passed Voter[] voter that might contain null Voters, and assign only non null
	 * Voters to the created Voter[] nonNullVoterArray. Null Voters can occur if the
	 * Voter has 4 elements (email, first name, last name, postal code) but one of
	 * the information is not valid, which will result the constructor to throw an
	 * Exception and not creating the Voter.
	 */
	private static Voter[] filledValidVotersArray(Voter[] voter, int nonNullVoters) {
		Voter[] nonNullVotersArray = new Voter[nonNullVoters];
		for (int i = 0, j = 0; i < voter.length; i++, j++) {
			if (voter[i] != null) {
				nonNullVotersArray[j] = voter[i];
			} else {
				j--;
			}
		}
		return nonNullVotersArray;
	}

	
	/*
	 *  Space to create private helper methods for Election
	 */
	
	/*
	 *  Space to create private helper methods for Tally
	 */
	

	private static String[] createLinesArray(String filename) throws IOException{
		Path file = Paths.get(filename);
		Charset uncoding = Charset.forName("UTF-8");
		
		try {
			List<String> lines = Files.readAllLines(file, uncoding);
			
			for(int i=0; i<lines.size(); i++) {
				
				if(lines.get(i).trim().isEmpty()) {
					lines.remove(i);
				}
			}
			
			String[] linesArray = new String[lines.size()];
			linesArray = lines.toArray(linesArray);
			
			return linesArray;
		}catch(IOException ioe) {
			throw new IOException("Error reading lines from file: " + filename);
		}
	}
	

}
