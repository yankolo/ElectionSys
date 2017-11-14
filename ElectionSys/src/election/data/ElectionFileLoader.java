package election.data;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import election.business.DawsonElectionFactory;
import election.business.interfaces.Voter;
import election.business.interfaces.Election;

public class ElectionFileLoader {
	private ElectionFileLoader() {

	}

	/**
	 * The getVoterListFromSequentialFile method takes a String filename which is
	 * the name of the text file. It creates a String[] linesArray that contains all
	 * the lines of a text file by calling the createLinesArray method. Then it
	 * returns the Voter[] that is created and passed by the createVoterArray
	 * method.
	 * 
	 * @author Mohamed
	 * @param filename
	 * @return Voter[]
	 * @throws IOException
	 */
	public static Voter[] getVoterListFromSequentialFile(String filename) throws IOException {
		String[] linesArray = createLinesArray(filename);
		return createVoterArray(linesArray, filename);
	}

	/**
	 * The getElectionListFromSequentialFile method takes a String filename which is
	 * the name of the text file. It creates a String[] linesArray that contains all
	 * the lines of a text file by calling the createLinesArray method. Then it
	 * returns the Election[] that is created and passed by the createElectionArray
	 * method.
	 * 
	 * @author Mohamed
	 * @param filename
	 * @return Election[]
	 * @throws IOException
	 */
	public static Election[] getElectionListFromSequentialFile(String filename) throws IOException {
		String[] linesArray = createLinesArray(filename);
		return createElectionArray(linesArray, filename);

	}

	/**
	 * This method will go through a file and look for any election name and try to
	 * match them to one of the elements in the elections array. If one of the
	 * elements is matched, it will update the election with its corresponding Tally
	 * in the file. If it does not match any elements in the elections array, the
	 * election's tally will not be updated.
	 * 
	 * @author Sammy, Mohamed and Yanick
	 * @param filename
	 * @param elections
	 * @throws IOException
	 */
	public static void setExistingTallyFromSequentialFile(String filename, Election[] elections) throws IOException {
		String[] tallyFile = createLinesArray(filename);
		int[][] results;

		int electionLine = 0;
		int firstResultLine = 0;
		int numOfResults = 0;
		while (electionLine < tallyFile.length) {
			try {
				// first gets the name of the election that will be stored in the following
				// variable
				String[] electionPart = tallyFile[electionLine].split("[*]");
				// the number of choices will be in the following variable.
				numOfResults = Integer.parseInt(electionPart[1]);
				// we need to have a variable that keeps track of the first result line of a
				// tally
				firstResultLine = electionLine + 1;
				// this will contain all the results of a tally
				String[] resultLines = Arrays.copyOfRange(tallyFile, firstResultLine, firstResultLine + numOfResults);
				// then we will store each line of the tally, to get each result when we will
				// split
				String[][] resultsInString = new String[resultLines.length][];
				// use split to get each line of result without the asterix
				for (int i = 0; i < resultLines.length; i++) {
					resultsInString[i] = resultLines[i].split("[*]");
				}

				// Transform string[][] to int[][]
				results = new int[resultsInString.length][];
				for (int i = 0; i < resultsInString.length; i++) {
					results[i] = new int[resultsInString[i].length];
					for (int j = 0; j < resultsInString[i].length; j++) {
						results[i][j] = Integer.parseInt(resultsInString[i][j]);
					}
				}
				/**
				 * checks if the name of the tally is in the corresponding the election array
				 * that is passed to the method if the name is not in the array, it will turn a
				 * boolean value to true, meaning that the tally was found
				 */

				boolean tallyFoundInElections = false;
				Election foundElection = null;
				for (Election election : elections) {
					if (electionPart[0].equals(election.getName())) {
						tallyFoundInElections = true;
						foundElection = election;
					}
				}
				// if the tally is found then update the election with the corresponding tally
				// result
				if (tallyFoundInElections)
					DawsonElectionFactory.DAWSON_ELECTION.setExistingTally(results, foundElection);
				// if not found then it will look at the next line after the number of choices
				// possible
				else
					System.err.println(
							"Error:  Tally not set - Tally's election name not found in election list \n\tIn file: "
									+ filename + "\n\tAt line " + (electionLine + 1) + ": " + tallyFile[electionLine]);
				// increment to the next election line
				electionLine = firstResultLine + numOfResults;
			} catch (Exception e) {
				// if any error is caught then increment to the next election line without
				// updating the election of a tally.

				System.err.println("Error:  Tally not set - Bad Format \n\tIn file: " + filename + "\n\tAt line "
						+ (electionLine + 1) + ": " + tallyFile[electionLine]);
				electionLine = firstResultLine + numOfResults;
			}

		}
	}

	/**
	 * The createVoterArray takes a String[] linesArray which is an array that
	 * contains all lines of the text file and a String filename which is the name
	 * of the text file. It creates an empty Voter ArrayList that stores all Voters
	 * created. The method loops through each line and splits the line on the
	 * asterisk, storing all elements in a String[] voter. If the length of String[]
	 * voter is not equal to 4 (email, first name, last name, postal code), an Error
	 * is printed with the line number and the filename. Else a Voter is created by
	 * passing the elements contained in the String[] voter. Then the created Voter
	 * is added to the ArrayList voterArrayList. Finally a Voter[] validVoters is
	 * created which it's size is equal to the size of the ArrayList voterArrayList.
	 * The Voter[] validVoters is then returned.
	 * 
	 * @author Mohamed
	 * @param linesArray
	 * @param filename
	 * @return Voter[] validVoters
	 */
	private static Voter[] createVoterArray(String[] linesArray, String filename) {
		List<Voter> voterArrayList = new ArrayList<Voter>();

		for (int i = 0; i < linesArray.length; i++) {

			String[] voter = linesArray[i].split("[*]");

			if (voter.length != 4) {
				System.err.println("Error:  Bad Format - Invalid number of elements \n\tIn file: " + filename
						+ "\n\tAt line " + (i + 1) + ": " + linesArray[i]);
			} else {
				String email = voter[0];
				String firstName = voter[1];
				String lastName = voter[2];
				String postal = voter[3];

				try {
					Voter newVoters = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance(firstName, lastName, email,
							postal);

					voterArrayList.add(newVoters);

				} catch (IllegalArgumentException iae) {
					System.err.println("Error:  Invalid voter information\n\t" + iae.getMessage() + "\n\tIn file: "
							+ filename + "\n\tAt line " + (i + 1) + ": " + linesArray[i]);
				}
			}
		}
		Voter[] validVoters = new Voter[voterArrayList.size()];
		return validVoters = voterArrayList.toArray(validVoters);
	}

	/**
	 * The craeteElectionArray method takes a String[] linesArray which is an array
	 * that contains all lines of the text file and a String filename which is the
	 * name of the text file. It creates an empty Election ArrayList that stores all
	 * Elections created. The method loops through each election line (int
	 * electionLine) which will always point to the line that contains the election
	 * and splits the line on the asterisk, storing all elements in a String[]
	 * election. If the length of String[] election is not equal to 11 (name,
	 * startYear, startMonth, startDay, endYear, endMonth, endDay, startPostalCode,
	 * endPostalCode, type, numOptions), the File is Discarded to prevent creating
	 * faulty Elections and an error message is printed with the line number, the
	 * filename and the line of the invalid Election. And the boolean
	 * invalidElection is set to true which will be used to return an Empty
	 * Election[] based on it's condition, in the case of a true boolean value it
	 * will return an Empty Election[]. Else it will determine how many choices to
	 * store in a String[] choices based on the number of choices found in the
	 * Election header. Using copyOfRange method, the choices will be stored in the
	 * String[] choices. It will then create an Election electionObj passing the
	 * elements in the String[] election and the String[] choices to the
	 * constructor. The Election created will then be added to the ArrayList
	 * electionArrayList. Finally if anything resulted in an error or thrown an
	 * exception, the createElectionArray will return an empty Election[]
	 * electionArray. Otherwise it will convert the ArrayList electionArrayList to
	 * Election[] electionArray and return it.
	 * 
	 * @author Mohamed
	 * @param linesArray
	 * @param filename
	 * @return Election[] electionArray
	 */
	private static Election[] createElectionArray(String[] linesArray, String filename) {
		List<Election> electionArrayList = new ArrayList<Election>();

		/*
		 * The boolean invalidElection is set to false by default, if an Error occurs in
		 * the method, the invalidElection will be set to true. If it's false, it will
		 * return a filled Election[] electionArray. if it's true it will return an
		 * empty Election[] electionArray.
		 * 
		 */
		boolean invalidElection = false;

		// electionLine points to the Election line.
		int electionLine = 0;

		// The loop finds all the Elections in the text file.
		while (electionLine < linesArray.length) {

			/*
			 * election contains all the 11 elements of the Election (name, startYear,
			 * startMonth, startDay, endYear, endMonth, endDay, startPostalCode,
			 * endPostalCode, type, numOptions)
			 */
			String[] election = linesArray[electionLine].split("[*]");

			// If election doesn't have all the 11 elements an Error is printed.
			if (election.length != 11) {

				// The Error prints the line number, file, and the line of the invalid Election.

				System.err.println("Error:  File Discarded: " + filename
						+ "\n\tPossible Error caused by Bad Formated Election or Invalid Option number for one of the Elections in the file.\n\tAt line "
						+ (electionLine + 1) + ": " + linesArray[electionLine]);
				invalidElection = true;
				break;

			} else {
				try {
					/**
					 * numOfChoices is equal to the last element in the Election which is the
					 * choice. (e.g. For the following Election: DSU
					 * Referendum*2017*09*05*2017*09*06*H1A*H2A*Single*2. numOfChoices is equal to
					 * 2).
					 */
					int numOfChoices = Integer.parseInt(election[10]);

					/*
					 * fistChoices is the first ballot of the Election which is the line under the
					 * electionLine
					 */
					int firstChoice = electionLine + 1;

					// choices contains all the ballots of the Election
					String[] choices = Arrays.copyOfRange(linesArray, firstChoice, numOfChoices + firstChoice);

					String name = election[0];
					int startYear = Integer.parseInt(election[1]);
					int startMonth = Integer.parseInt(election[2]);
					int startDay = Integer.parseInt(election[3]);
					int endYear = Integer.parseInt(election[4]);
					int endMonth = Integer.parseInt(election[5]);
					int endDay = Integer.parseInt(election[6]);
					String startPostal = election[7];
					String endPostal = election[8];
					String type = election[9];

					Election electionObj = DawsonElectionFactory.DAWSON_ELECTION.getElectionInstance(name, type,
							startYear, startMonth, startDay, endYear, endMonth, endDay, startPostal, endPostal,
							choices);

					/*
					 * If the electionObj is created it will be added to the ArrayList
					 * ectionArrayList
					 * 
					 */
					electionArrayList.add(electionObj);

					// electionLine will point to the next Election.
					electionLine = firstChoice + numOfChoices;

				} catch (Exception e) {
					invalidElection = true;
					// Same descriptive Error as above but also has the exception Error is printed.
					System.err.println("Error:  File Discarded: " + filename
							+ "\n\tPossible Error caused by Bad Formated Election or Invalid Option number for one of the Elections in the file."
							+ "\n\t" + e.toString() + "\n\tAt line " + (electionLine + 1)
							+ ": " + linesArray[electionLine]);
					break;
				}
			}
		}
		if (invalidElection) {
			Election[] electionArray = new Election[0];
			return electionArray;
		} else {
			Election[] electionArray = new Election[electionArrayList.size()];
			return electionArray = electionArrayList.toArray(electionArray);
		}

	}

	/**
	 * The createLinesArray method takes a String filename which is the name of the
	 * text file and return a String[] linesArray contains all the lines of the text
	 * file without the empty lines.
	 * 
	 * @author Mohamed , Yanick
	 * @param filename
	 * @return String[] linesArray
	 * @throws IOException
	 */
	private static String[] createLinesArray(String filename) throws IOException {
		Path file = Paths.get(filename);
		Charset uncoding = Charset.forName("UTF-8");

		try {
			List<String> lines = Files.readAllLines(file, uncoding);

			for (int i = 0; i < lines.size(); i++) {

				if (lines.get(i).trim().isEmpty()) {
					lines.remove(i);
					i--;
				}
			}

			String[] linesArray = new String[lines.size()];
			linesArray = lines.toArray(linesArray);

			return linesArray;
		} catch (IOException ioe) {
			throw new IOException("Error reading lines from file: " + filename);
		}
	}

}
