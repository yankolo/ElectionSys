/**
 * 
 */
package election.data;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import election.business.DawsonElectionFactory;
import election.business.interfaces.Election;
import util.ListUtilities;

/**
 * @author Mohamed Hamza
 *
 */
public class ElectionListDBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testGetElection();
		testToString();
		testAdd();
	}

	private static void setup() {
		String[] voters = new String[10];
		voters[0] = "bob.flann@gmail.com*Bob*Flann*H1V5G7";
		voters[1] = "callmepeggy@gmail.com*Margaret*Carter*C1K9G6";
		voters[2] = "curtis-Rocks@gmail.com*Summer*Curtis*V1J0C8";
		voters[3] = "daniel.rafail@gmail.com*Daniel*Rafail*H3W1N9";
		voters[4] = "gregoryMWheeler@gmail.com*Gregory*Wheeler*H1A1B0";
		voters[5] = "h_brassington5@gmail.com*Holly*Brassington*H4H6P6";
		voters[6] = "ian.york@gmail.com*Ian*York*Y4H5Z1";
		voters[7] = "jacques.smith@gmail.com*Jacques*Smith*P2V4H6";
		voters[8] = "joe.mancini@mail.me*Joe*Mancini*H3C4B7";
		voters[9] = "raj@test.ru*Raj*Wong*H3E1B4";

		String[] elecs = new String[5];
		elecs[0] = "Brittany independence referendum*2017*9*6*2018*8*5*H3A*M1Z*single*2" + "\nYes, I want independence"
				+ "\nNo, I do not want independence";
		elecs[1] = "Dawson College Student Union Election*2018*9*10*2018*9*14***ranked*4" + "\nJonathan Pesce"
				+ "\nNicholas Apanian" + "\nNiv Abecassis" + "\nAnh Quan Nguyen";
		elecs[2] = "DSU Referendum*2017*9*5*2017*9*6*H1A*H2A*single*2" + "\nYes, I want classes on friday"
				+ "\nNo, I don't want classes on friday";
		elecs[3] = "Favourite program*2018*5*1*2019*5*31*H4G*H4G*single*2" + "\nGame of Thrones" + "\nNarcos";
		elecs[4] = "Presidental race*2020*10*1*2020*11*1***single*2" + "\nDonald Trump" + "\nAnyone Else";

		String[] tallies = new String[2];
		tallies[0] = "Presidental race*2" + "\n100*0" + "\n0*102";
		tallies[1] = "Favourite program*2" + "\n1000*0" + "\n0*560";

		// make the testfiles directory
		Path dir;
		try {
			dir = Paths.get("datafiles/testfiles");
			if (!Files.exists(dir))
				Files.createDirectory(dir);
			ListUtilities.saveListToTextFile(voters, "datafiles/testfiles/testVoters.txt");
			ListUtilities.saveListToTextFile(elecs, "datafiles/testfiles/testElections.txt");
			ListUtilities.saveListToTextFile(tallies, "datafiles/testfiles/testTally.txt");
		} catch (InvalidPathException e) {
			System.err.println("could not create testfiles directory " + e.getMessage());
		} catch (FileAlreadyExistsException e) {
			System.err.println("could not create testfiles directory " + e.getMessage());
		} catch (IOException e) {
			System.err.println("could not create testfiles in setup() " + e.getMessage());
		}

	}

	private static void teardown() {
		Path file;
		try {
			file = Paths.get("datafiles/testfiles/testVoters.txt");
			Files.deleteIfExists(file);
			file = Paths.get("datafiles/testfiles/testElections.txt");
			Files.deleteIfExists(file);
			file = Paths.get("datafiles/testfiles/testTally.txt");
			Files.deleteIfExists(file);
		} catch (InvalidPathException e) {
			System.err.println("could not delete test files " + e.getMessage());
		} catch (NoSuchFileException e) {
			System.err.println("could not delete test files " + e.getMessage());
		} catch (DirectoryNotEmptyException e) {
			System.err.println("could not delete test files " + e.getMessage());
		} catch (IOException e) {
			System.err.println("could not delete test files " + e.getMessage());
		}

	}

	private static void testGetElection() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList("datafiles/testfiles/testVoters.txt",
				"datafiles/testfiles/testElections.txt", "datafiles/testfiles/testTally.txt");
		ElectionListDB db = new ElectionListDB(file);

		System.out.println("\n** test getElection **");
		System.out.println("\nTest case 1: Election in the database (Favourite program)");
		try {
			Election election = db.getElection("Favourite program");
			System.out.println("SUCCESS: Election found " + election.toString());
		} catch (InexistentElectionException iee) {
			System.out.println("FAILING TEST CASE: Election should be found");
		}

		System.out.println("\nTest case 2: Election not in the database (Hello)");
		try {
			Election election = db.getElection("Hello");
			System.out.println("FAILING TEST CASE: Election found " + election.toString());
		} catch (InexistentElectionException iee) {
			System.out.println("SUCCESS: Election not found");
		}

		System.out.println("\nTest case 3: Election in the middle of the database (DSU Referendum)");
		try {
			Election election = db.getElection("DSU Referendum");
			System.out.println("SUCCESS: Election found " + election.toString());
		} catch (InexistentElectionException iee) {
			System.out.println("FAILING TEST CASE: Election should be found");
		}

		System.out
				.println("\nTest case 4: Election at the begining of the database (Brittany independence referendum)");
		try {
			Election election = db.getElection("Brittany independence referendum");
			System.out.println("SUCCESS: Election found " + election.toString());
		} catch (InexistentElectionException iee) {
			System.out.println("FAILING TEST CASE: Election should be found");
		}

		System.out.println("\nTest case 5: Election at tthe end of the database (Presidental race)");
		try {
			Election election = db.getElection("Presidental race");
			System.out.println("SUCCESS: Election found " + election.toString());
		} catch (InexistentElectionException iee) {
			System.out.println("FAILING TEST CASE: Election should be found");
		}

		teardown();
	}

	private static void testToString() {
		setup();

		SequentialTextFileList file = new SequentialTextFileList("datafiles/testfiles/testVoters.txt",
				"datafiles/testfiles/testElections.txt", "datafiles/testfiles/testTally.txt");
		ElectionListDB db = new ElectionListDB(file);

		String dbStringToCompare = null;
		try {
			Election[] listOfElections = ElectionFileLoader
					.getElectionListFromSequentialFile("datafiles/testfiles/testElections.txt");
			dbStringToCompare = "Number of elections in the database: " + listOfElections.length;
			for (Election election : listOfElections) {
				dbStringToCompare += "\n" + election;
			}
		} catch (IOException e) {
			System.out.println("Can't read from datafiles/testfiles/testElections.txt");
		}

		System.out.println("\n** test toString **");
		System.out
				.println("\nTest case 1: toString output of ElectionListDB from datafiles/testfiles/testElections.txt");
		System.out.println(db.toString());

		System.out.println("\nTest case 2: toString of ElectionListDB from datafiles/testfiles/testElections.txt");
		if (db.toString().equals(dbStringToCompare)) {
			System.out.println("SUCCESS: Correct toString");
		} else {
			System.out.println("FAILING TEST CASE: wrong toString");
		}
		teardown();
	}

	private static void testAdd() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList("datafiles/testfiles/testVoters.txt",
				"datafiles/testfiles/testElections.txt", "datafiles/testfiles/testTally.txt");
		ElectionListDB db = new ElectionListDB(file);

		System.out.println("\n** test Add **");
		System.out.println("\nTest case 1: election not in the database (Favorite color)");

		try {
			String[] choices = { "White", "Black" };
			Election election = DawsonElectionFactory.DAWSON_ELECTION.getElectionInstance("Favorite color", "single",
					2017, 9, 6, 2018, 8, 5, "H3A", "M1Z", choices);
			db.add(election);
			db.getElection(election.getName());
			System.out.println("SUCCESS: Election added");
			System.out.println(db.toString());
		} catch (DuplicateElectionException dee) {
			System.out.println("FALING TEST CASE: Election is already in the database");
		} catch (InexistentElectionException iee) {
			System.out.println("FAILING TEST CASE: Election not added");
		}

		System.out.println("\nTest case 2: election not in the database (Mobile OS)");

		try {
			String[] choices = { "Android", "IOS" };
			Election election = DawsonElectionFactory.DAWSON_ELECTION.getElectionInstance("Mobile OS", "single", 2020,
					11, 1, 2020, 11, 1, null, null, choices);
			db.add(election);
			db.getElection(election.getName());
			System.out.println("SUCCESS: Election added");
			System.out.println(db.toString());
		} catch (DuplicateElectionException dee) {
			System.out.println("FAILING TEST CASE: Election is already in the database");
		} catch (InexistentElectionException iee) {
			System.out.println("FAILING TEST CASE: Election not added");
		}

		System.out.println("\nTest case 3: election already in the database (Presidental race)");

		try {
			String[] choices = { "Donald Trump", "Anyone Else" };
			Election election = DawsonElectionFactory.DAWSON_ELECTION.getElectionInstance("Presidental race", "single",
					2020, 11, 1, 2020, 11, 1, null, null, choices);
			db.add(election);
			db.getElection(election.getName());
			System.out.println("FAILING TEST CASE: Election added");
		} catch (DuplicateElectionException dee) {
			System.out.println("SUCCESS: Election already in the database");
		} catch (InexistentElectionException iee) {
			System.out.println("FAILING CASE: Election not added");
		}

		System.out.println("\nTest case 4: election already in the database");

		try {
			String[] choices = { "Yes, I want classes on friday", "NNo, I don't want classes on friday" };
			Election election = DawsonElectionFactory.DAWSON_ELECTION.getElectionInstance("DSU Referendum", "single",
					2017, 9, 5, 2017, 9, 6, "H1A", "H2A", choices);
			db.add(election);
			db.getElection(election.getName());
			System.out.println("FAILING TEST CASE: Election added");
		} catch (DuplicateElectionException dee) {
			System.out.println("SUCCESS: Election already in the database");
		} catch (InexistentElectionException iee) {
			System.out.println("FAILING CASE: Election not added");
		}
		teardown();
	}
}
