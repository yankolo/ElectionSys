package election.data;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;

import election.business.DawsonElectionFactory;
import election.business.interfaces.Voter;

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

		List<String> lines = Files.readAllLines(file, uncoding);
		String[] linesArray = new String[lines.size()];

		linesArray = lines.toArray(linesArray);

		return createVoterArray(linesArray, filename);
	}

	/**
	 * The createVoterArray takes a String[] array that contains a voter in each
	 * line. It then creates a String[][] array that has the voters as rows and the
	 * (email, first name, last name, postal code) each as a column. It uses the
	 * String[][] array to create new Voters, storing them in a Voter[] array.
	 * Finally, it returns the Voter[] array.
	 * 
	 * @param linesArray
	 * @return
	 */
	private static Voter[] createVoterArray(String[] linesArray, String filename) {
		String[][] allElements = new String[linesArray.length][];
		for (int i = 0; i < linesArray.length; i++) {
			allElements[i] = linesArray[i].split("[*]");
		}

		Voter[] voter = new Voter[linesArray.length];
		int email = 0;
		int firstName = 1;
		int lastName = 2;
		int postal = 3;
		for (int i = 0; i < allElements.length; i++) {
			if (allElements[i].length != 4) {

				System.err.println("Format Error - number of elements not correct at line " + (i + 1) + ": "
						+ linesArray[i] + " \nFrom file name : " + filename);
				continue;
			}
			try {
				voter[i] = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance(allElements[i][email],
						allElements[i][firstName], allElements[i][lastName], allElements[i][postal]);
			} catch (IllegalArgumentException iae) {
				System.out.println("\t" + iae.getMessage());
			}
		}

		return voter;
	}
}
