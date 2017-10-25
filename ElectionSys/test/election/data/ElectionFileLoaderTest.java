package election.data;

import java.io.IOException;

import election.business.interfaces.Election;
import election.business.interfaces.Voter;

public class ElectionFileLoaderTest {

	public static void main(String[] args) {
		testGetVoterListFromSequentialFile();
		testGetElectionListFromSequentialFile();
	}

	private static void testGetVoterListFromSequentialFile() {
		System.out.println("Testing the getVoterListFromSequentialFile");
		System.out.println();

		testGetVoterListFromSequentialFile(
				"Case 1 - File has only 1 valid Voter (gregoryMWheeler@gmail.com*Gregory*Wheeler*H1A 1B0)",
				"test/election/data/testFiles/case1.txt", 1);

		testGetVoterListFromSequentialFile(
				"Case 2 - File has only 1 invalid Voter (gregoryMWheeler@gmail.com*Gregory*Wheeler*H1A 1B0*Hello)",
				"test/election/data/testFiles/case2.txt", 0);

		testGetVoterListFromSequentialFile("Case 3 - 4 valid Voters and 1 bad Voter at the end of the file (line 5)",
				"test/election/data/testFiles/case3.txt", 4);

		testGetVoterListFromSequentialFile(
				"Case 4 - 4 valid Voters and 1 bad Voter at the begining of the file (line 1)",
				"test/election/data/testFiles/case4.txt", 4);

		testGetVoterListFromSequentialFile("Case 5 - 4 valid Voters and 1 bad Voter in the middle (line 3)",
				"test/election/data/testFiles/case5.txt", 4);

		testGetVoterListFromSequentialFile("Case 6 - 4 valid Voters and 2 bad consecutive Voters at lines 3 and 4",
				"test/election/data/testFiles/case6.txt", 4);

		testGetVoterListFromSequentialFile("Case 7 - Invalid path Should throw an IOException",
				"test/election/data/testFiles", 0);

		testGetVoterListFromSequentialFile("Case 8 - Empty file", "test/election/data/testFiles/case8.txt", 0);

		testGetVoterListFromSequentialFile("Case 9 - File has 1 Voter with an Invalid first name",
				"test/election/data/testFiles/case9.txt", 0);

		testGetVoterListFromSequentialFile(
				"Case 10 - File has 4 valid Voters and a Voter with an Invalid last name at the begining",
				"test/election/data/testFiles/case10.txt", 4);

		testGetVoterListFromSequentialFile(
				"Case 11 - File has 4 valid Voters and a Voter with an Invalid postal code at the end",
				"test/election/data/testFiles/case11.txt", 4);

		testGetVoterListFromSequentialFile(
				"Case 12 - File has 4 valid Voters and a Voter with an Invalid email in the middle (line 3)",
				"test/election/data/testFiles/case12.txt", 4);

		testGetVoterListFromSequentialFile("Case 13 - File with 4 empty lines",
				"test/election/data/testFiles/case13.txt", 0);

		testGetVoterListFromSequentialFile(
				"Case 14 - File has 4 valid Voters. The first Voter has leading/trailing spaces",
				"test/election/data/testFiles/case14.txt", 4);
	}

	private static void testGetVoterListFromSequentialFile(String testCase, String filename, int numOfValidVoters) {
		System.out.println("   " + testCase);
		Voter[] voters;
		try {
			voters = ElectionFileLoader.getVoterListFromSequentialFile(filename);
			System.out.println("\tNumber of Voters created: " + voters.length);

			for (int i = 0; i < voters.length; i++) {
				System.out.println("\t" + voters[i]);

			}

			if (voters.length != numOfValidVoters) {
				System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
			}

		} catch (IOException e) {
			System.out.println("\t" + e.getMessage());
		}
		System.out.println();
	}

	private static void testGetElectionListFromSequentialFile() {
		System.out.println("Testing the getElectionListFromSequentialFile");
		System.out.println();
		testGetElectionListFromSequentialFile("Case 1 - 1 Valid Election", "test/election/data/electionTest/case1.txt",
				1);
		testGetElectionListFromSequentialFile("Case 2 - 3 Valid Election", "test/election/data/electionTest/case2.txt",
				3);
		testGetElectionListFromSequentialFile("Case 3 - 1 Invalid Election: number of elements is 10",
				"test/election/data/electionTest/case3.txt", 0);
		testGetElectionListFromSequentialFile(
				"Case 4 - 1 Invalid Election: number of ballot is less than the number in the Election header",
				"test/election/data/electionTest/case4.txt", 0);
		testGetElectionListFromSequentialFile(
				"Case 5 - 1 Invalid Election: the choice in the Election header is not an int",
				"test/election/data/electionTest/case5.txt", 0);
		testGetElectionListFromSequentialFile(
				"Case 6 - 1 Invalid Election: Number of ballot is grater than the number of choices in the ELection header",
				"test/election/data/electionTest/case6.txt", 0);
		testGetElectionListFromSequentialFile("Case 7 - 2 Valid Election and 1 Invalid Election at the end of the file",
				"test/election/data/electionTest/case7.txt", 0);
		testGetElectionListFromSequentialFile(
				"Case 8 - Text file contains empty lines at the begining and the end of the text file With 2 Valid Elections",
				"test/election/data/electionTest/case8.txt", 2);
	}

	private static void testGetElectionListFromSequentialFile(String testCase, String filename,
			int numOfValidElections) {
		System.out.println("   " + testCase);
		Election[] elections;
		try {
			elections = ElectionFileLoader.getElectionListFromSequentialFile(filename);
			System.out.println("\tNumber of Election created: " + elections.length);

			if (elections.length != numOfValidElections) {
				System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
			}

		} catch (IOException ioe) {
			System.out.println("\t" + ioe.getMessage());
		} catch (Exception e) {
			System.out.println("\t" + e.getMessage());
		}
		System.out.println();
	}

}
