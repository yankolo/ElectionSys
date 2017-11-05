package election.data;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import election.business.interfaces.*;
import util.ListUtilities;

public class VoterListDBTest {

	public static void main(String[] args) {
		testGetVoter();
		//TODO Add more method invocations to test all methods
	}
	
	private static void setup()
	{
		String[] voters = new String[2];
		voters[0] = "joe.mancini@mail.me*Joe*Mancini*H3C4B7";
		voters[1] = "raj@test.ru*Raj*Wong*H3E1B4";
		//TODO add more voters if needed, but they must be in sorted order!

		String[] elecs = new String[2];
		elecs [0] = "Presidental race*2020*11*1*2020*11*1***single*2" + 
				"\nDonald Trump" + 
				"\nAnyone Else";
		elecs [1] = "Favourite program*2018*5*1*2019*5*31*H4G*H4G*single*2" + 
				"\nGame of Thrones" + 
				"\nNarcos";
		//TODO add more elections if needed, but the must be in sorted order

		String[] tallies = new String[2];
		tallies [0] = "Presidental race*2" + 
				"\n100*0" + 
				"\n0*102";
		tallies [1] = "Favourite program*2" + 
				"\n1000*0" + 
				"\n0*560";
		
		// make the testfiles directory
		Path dir;
		try {
			dir= Paths.get("datafiles/testfiles");
			if( !Files.exists(dir))
				Files.createDirectory(dir);
			ListUtilities.saveListToTextFile(voters, 
					"datafiles/testfiles/testVoters.txt");
			ListUtilities.saveListToTextFile(elecs, 
					"datafiles/testfiles/testElections.txt");
			ListUtilities.saveListToTextFile(tallies, 
					"datafiles/testfiles/testTally.txt");
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

	private static void testGetVoter() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList
				("datafiles/testfiles/testVoters.txt", "datafiles/testfiles/testElections.txt",
						"datafiles/testfiles/testTally.txt");
		VoterListDB db = new VoterListDB(file);
		
		System.out.println("\n** test getVoter ** ");
		System.out.println("\nTest case 1: Voter in database:");
		try {
			Voter voter = db.getVoter("raj@test.ru");
			System.out.println("SUCCESS: Voter found " + voter.toString());
		} catch (InexistentVoterException e) {
			System.out.println("FAILING TEST CASE: voter should be fould");
		}
		
		System.out.println("\nTest case 2: Voter not in database:");
		try {
			Voter voter = db.getVoter("jar@test.ru");
			System.out.println("FAILING TEST CASE: Voter found " + voter.toString());
		} catch (InexistentVoterException e) {
			System.out.println("SUCCESS: voter not found");
		}	

		teardown();
	}

}
