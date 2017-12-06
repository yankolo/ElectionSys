package election.data;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import election.business.DawsonElectionFactory;
import election.business.interfaces.*;
import util.ListUtilities;

public class VoterListDBTest {

	public static void main(String[] args) {
		testGetVoter();
		testToString();
		testUpdate();
		testAdd();
		testDisconnect();
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

		String[] elecs = new String[2];
		elecs[0] = "Presidental race*2020*11*1*2020*11*1***single*2" + "\nDonald Trump" + "\nAnyone Else";
		elecs[1] = "Favourite program*2018*5*1*2019*5*31*H4G*H4G*single*2" + "\nGame of Thrones" + "\nNarcos";
		// TODO add more elections if needed, but the must be in sorted order

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

			SequentialTextFileList file = new SequentialTextFileList("datafiles/testfiles/testVoters.txt",
					"datafiles/testfiles/testElections.txt", "datafiles/testfiles/testTally.txt");

			List<Voter> voterList = file.getVoterDatabase();
			List<Election> electionList = file.getElectionDatabase();

			util.Utilities.serializeObject(voterList, "datafiles/testfiles/testVoters.ser");
			util.Utilities.serializeObject(electionList, "datafiles/testfiles/testElections.ser");
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
			file = Paths.get("datafiles/testfiles/testVoters.ser");
			Files.deleteIfExists(file);
			file = Paths.get("datafiles/testfiles/testElections.ser");
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

	private static void testGetVoter() {
		setup();
		ObjectSerializedList serialized = new ObjectSerializedList("datafiles/testfiles/testVoters.ser",
				"datafiles/testfiles/testElections.ser");
		VoterListDB db = new VoterListDB(serialized);

		System.out.println("\n** test getVoter ** ");
		System.out.println("\nTest case 1: Voter in database (raj@test.ru)");
		try {
			Voter voter = db.getVoter("raj@test.ru");
			System.out.println("SUCCESS: Voter found " + voter.toString());
		} catch (InexistentVoterException e) {
			System.out.println("FAILING TEST CASE: voter should be found");
		}

		System.out.println("\nTest case 2: Voter not in database (jar@test.ru)");
		try {
			Voter voter = db.getVoter("jar@test.ru");
			System.out.println("FAILING TEST CASE: Voter found " + voter.toString());
		} catch (InexistentVoterException e) {
			System.out.println("SUCCESS: voter not found");
		}

		System.out.println("\nTest case 3: Voter in database (bob.flann@gmail.com)");
		try {
			Voter voter = db.getVoter("bob.flann@gmail.com");
			System.out.println("SUCCESS: Voter found " + voter.toString());
		} catch (InexistentVoterException e) {
			System.out.println("FAILING TEST CASE: voter should be found");
		}

		System.out.println("\nTest case 4: Voter in database (callmepeggy@gmail.com)");
		try {
			Voter voter = db.getVoter("callmepeggy@gmail.com");
			System.out.println("SUCCESS: Voter found " + voter.toString());
		} catch (InexistentVoterException e) {
			System.out.println("FAILING TEST CASE: voter should be found");
		}

		System.out.println("\nTest case 5: Voter not in database (moe@gmail.com)");
		try {
			Voter voter = db.getVoter("moe@gmail.com");
			System.out.println("FAILING TEST CASE: Voter found " + voter.toString());
		} catch (InexistentVoterException e) {
			System.out.println("SUCCESS: voter not found");
		}

		teardown();
	}

	private static void testToString() {
		setup();

		ObjectSerializedList serialized = new ObjectSerializedList("datafiles/testfiles/testVoters.ser",
				"datafiles/testfiles/testElections.ser");
		VoterListDB db = new VoterListDB(serialized);

		String dbStringToCompare = null;
		try {
			Voter[] listOfVoters = ElectionFileLoader
					.getVoterListFromSequentialFile("datafiles/testfiles/testVoters.txt");
			dbStringToCompare = "Number of voters in database: " + listOfVoters.length;
			for (Voter voter : listOfVoters) {
				dbStringToCompare += "\n" + voter;
			}
		} catch (IOException e) {
			System.out.println("Can't read from datafiles/testfiles/testVoters.txt");
		}

		System.out.println("\n** test toString ** ");
		System.out.println("\nTest case 1: toString of VoterListDB from datafiles/testfiles/testVoters.txt");
		if (db.toString().equals(dbStringToCompare))
			System.out.println("SUCCESS: Correct toString");
		else
			System.out.println("FAILING TEST CASE: wrong toString");

		teardown();
	}

	private static void testUpdate() {
		setup();
		ObjectSerializedList serialized = new ObjectSerializedList("datafiles/testfiles/testVoters.ser",
				"datafiles/testfiles/testElections.ser");
		VoterListDB db = new VoterListDB(serialized);

		System.out.println("\n** test update ** ");
		System.out.println("\nTest case 1: Voter in database (raj@test.ru, J4W2Y9)");
		try {
			lib.Email email = new lib.Email("raj@test.ru");
			lib.PostalCode code = new lib.PostalCode("J4W2Y9");
			db.update(email, code);
			if (db.getVoter("raj@test.ru").getPostalCode().equals(code))
				System.out.println("SUCCESS: Voter postal code changed");
			else
				System.out.println("FAILING TEST CASE: Voter postal code not changed");
		} catch (InexistentVoterException e) {
			System.out.println("FAILING TEST CASE: Voter not found");
		}

		System.out.println("\nTest case 2: Voter in database (callmepeggy@gmail.com, J4W2Y9)");
		try {
			lib.Email email = new lib.Email("callmepeggy@gmail.com");
			lib.PostalCode code = new lib.PostalCode("J4W2Y9");
			db.update(email, code);
			if (db.getVoter("callmepeggy@gmail.com").getPostalCode().equals(code))
				System.out.println("SUCCESS: Voter postal code changed");
			else
				System.out.println("FAILING TEST CASE: Voter postal code not changed");
		} catch (InexistentVoterException e) {
			System.out.println("FAILING TEST CASE: Voter not found");
		}

		System.out.println("\nTest case 3: Voter in database (jacques.smith@gmail.com, J4W2Y9)");
		try {
			lib.Email email = new lib.Email("jacques.smith@gmail.com");
			lib.PostalCode code = new lib.PostalCode("J4W2Y9");
			db.update(email, code);
			if (db.getVoter("jacques.smith@gmail.com").getPostalCode().equals(code))
				System.out.println("SUCCESS: Voter postal code changed");
			else
				System.out.println("FAILING TEST CASE: Voter postal code not changed");
		} catch (InexistentVoterException e) {
			System.out.println("FAILING TEST CASE: Voter not found");
		}

		System.out.println("\nTest case 4: Voter not in database (yankolo1234@gmail.com, J4W2Y9)");
		try {
			lib.Email email = new lib.Email("yankolo1234@gmail.com");
			lib.PostalCode code = new lib.PostalCode("J4W2Y9");
			db.update(email, code);
			if (db.getVoter("yankolo1234@gmail.com").getPostalCode().equals(code))
				System.out.println("FAILING TEST CAS: Voter postal code changed");
			else
				System.out.println("FAILING TEST CASE: Voter postal code not changed");
		} catch (InexistentVoterException e) {
			System.out.println("SUCCESS: Voter not found");
		}

		teardown();
	}

	private static void testAdd() {
		setup();
		ObjectSerializedList serialized = new ObjectSerializedList("datafiles/testfiles/testVoters.ser",
				"datafiles/testfiles/testElections.ser");
		VoterListDB db = new VoterListDB(serialized);

		System.out.println("\n** test add ** ");
		System.out.println("\nTest case 1: Voter not in database (yankolo1234@gmail.com)");
		try {
			Voter voter = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("yanik", "kolomatski",
					"yankolo1234@gmail.com", "J4W2Y9");
			db.add(voter);
			db.getVoter(voter.getEmail().toString());
			System.out.println("SUCCESS: Voter added");
		} catch (DuplicateVoterException e) {
			System.out.println("FAILING TEST CASE: Voter is already in the database");
		} catch (InexistentVoterException e) {
			System.out.println("FAILING TEST CASE: Voter not added");
		}

		System.out.println("\nTest case 2: Voter not in database (moe@gmail.com)");
		try {
			Voter voter = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("mohamed", "hamza", "moe@gmail.com",
					"H3Z1A4");
			db.add(voter);
			db.getVoter(voter.getEmail().toString());
			System.out.println("SUCCESS: Voter added");
		} catch (DuplicateVoterException e) {
			System.out.println("FAILING TEST CASE: Voter is already in the database");
		} catch (InexistentVoterException e) {
			System.out.println("FAILING TEST CASE: Voter not added");
		}

		System.out.println("\nTest case 3: Voter not in database (sammy@gmail.com)");
		try {
			Voter voter = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("sammy", "chaouki", "sammy@gmail.com",
					"H3Z1A4");
			db.add(voter);
			db.getVoter(voter.getEmail().toString());
			System.out.println("SUCCESS: Voter added");
		} catch (DuplicateVoterException e) {
			System.out.println("FAILING TEST CASE: Voter is already in the database");
		} catch (InexistentVoterException e) {
			System.out.println("FAILING TEST CASE: Voter not added");
		}

		System.out.println("\nTest case 4: Voter already in database (daniel.rafail@gmail.com)");
		try {
			Voter voter = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("Daniel", "Rafail",
					"daniel.rafail@gmail.com", "H3W1N9");
			db.add(voter);
			db.getVoter(voter.getEmail().toString());
			System.out.println("FAILING TEST CASE: Voter added");
		} catch (DuplicateVoterException e) {
			System.out.println("SUCCESS: Voter is already in the database");
		} catch (InexistentVoterException e) {
			System.out.println("FAILING TEST CASE: Voter not added");
		}

		teardown();
	}

	private static void testDisconnect() {
		setup();
		ObjectSerializedList serialized = new ObjectSerializedList("datafiles/testfiles/testVoters.ser",
				"datafiles/testfiles/testElections.ser");
		VoterListDB db = new VoterListDB(serialized);

		System.out.println("\n** test disconnect ** ");
		try {
			db.add(DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("mohamed", "hamza", "moe@gmail.com",
					"H3Z1A4"));
			db.add(DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("sammy", "chaouki", "sammy@gmail.com",
					"H3Z1A4"));
			db.add(DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance("Daniel", "Rafail", "yankolo1234@gmail.com",
					"H3W1N9"));
		} catch (DuplicateVoterException e) {
			System.out.println("Voter is already in the database");
		}

		try {
			db.disconnect();
		} catch (IOException e) {
			System.out.println("FAILING TEST CASE: Unable to write to file");
		}

		db = new VoterListDB(serialized);

		try {
			db.getVoter("moe@gmail.com");
			db.getVoter("sammy@gmail.com");
			db.getVoter("yankolo1234@gmail.com");
			System.out.println("Success: File saved");
		} catch (InexistentVoterException e) {
			System.out.println("FAILING TEST CASE: File not saved");
		}

		teardown();
	}

}
