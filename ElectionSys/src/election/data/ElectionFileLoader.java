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
	 * @param filename
	 * @return Election[]
	 * @throws IOException
	 */
	public static Election[] getElectionListFromSequentialFile(String filename) throws IOException {
		String[] linesArray = createLinesArray(filename);
		return createElectionArray(linesArray, filename);

	}

	/**
	 * 
	 * @param filename
	 * @param elections
	 * @throws IOException
	 */
	public static void setExistingTallyFromSequentialFile(String filename, Election[] elections) throws IOException {
		// TODO Code Method
	}

	/**
	 * The createVoterArray takes a String[] linesArray which is an array that
	 * contains all lines of the text file and a String filename which is the name
	 * of the text file. It creates an empty Voter ArrayList that stores all Voters
	 * created. The method loops trough each line and splits the line on the
	 * asterisk, storing all elements in a String[] voter. If the length of String[]
	 * voter is not equal to 4 (email, first name, last name, postal code), an Error
	 * is printed with the line number and the filename. Else a Voter is created by
	 * passing the elements contained in the String[] voter. Then the created Voter
	 * is added to the ArrayList voterArrayList. Finally a Voter[] validVoters is
	 * created which it's size is equal to the size of the ArrayList voterArrayList.
	 * The Voter[] validVoters is then returned.
	 * 
	 * @param linesArray
	 * @param filename
	 * @return Voter[] validVoters
	 */
	private static Voter[] createVoterArray(String[] linesArray, String filename) {
		List<Voter> voterArrayList = new ArrayList<Voter>();

		for (int i = 0; i < linesArray.length; i++) {

			String[] voter = linesArray[i].split("[*]");

			if (voter.length != 4) {
				System.err.println(
						"Format Error - Invalid number of elements at line: " + (i + 1) + " from file: " + filename);
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
					System.out.println("\t" + iae.getMessage());
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
	 * Elections created. The method loops trough each line and splits the line on
	 * the asterisk, storing all elements in a String[] election. If the length of
	 * String[] voter is not equal to 11 (name, startYear, startMonth, startDay,
	 * endYear, endMonth, endDay, startPostalCode, endPostalCode, type, numOptions),
	 * the File is Discarded to prevent creating faulty Elections and an error
	 * message is printed with the line number, the filename and the line of the
	 * invalid Election. And the boolean invalidElection is set to true which will
	 * be used to return an Empty Election[] based on it's condition, in the case of
	 * a true boolean value it will return an Empty Election[]. Else it will
	 * determine how many choices to store in a String[] choices based on the number
	 * of choices found in the Election header.
	 * 
	 * @param linesArray
	 * @param filename
	 * @return
	 */
	private static Election[] createElectionArray(String[] linesArray, String filename) {
		List<Election> electionArrayList = new ArrayList<Election>();

		boolean invalidElection = false;

		int electionLine = 0;

		while (electionLine < linesArray.length) {
			String[] election = linesArray[electionLine].split("[*]");

			if (election.length != 11) {
				System.err.println(
						"File Discarded \nPossible Error caused by Bad Formated Election or Invalid Option number for one of the Elections in the file. \nError at line: "
								+ (electionLine + 1) + " From file: " + filename + "\nLine: \t"
								+ linesArray[electionLine]);
				invalidElection = true;
				break;

			} else {
				try {
					int numOfChoices = Integer.parseInt(election[10]);

					int firstChoice = electionLine + 1;

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

					electionArrayList.add(electionObj);

					electionLine = firstChoice + numOfChoices;

				} catch (Exception e) {
					invalidElection = true;
					System.err.println(
							"File Discarded \nPossible Error caused by Bad Formated Election or Invalid Option number for one of the Elections in the file. \nError at line: "
									+ (electionLine + 1) + " From file: " + filename + "\nLine: \t"
									+ linesArray[electionLine]);
					System.err.println(e.toString());
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

	/*
	 * Space to create private helper methods for Tally
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
