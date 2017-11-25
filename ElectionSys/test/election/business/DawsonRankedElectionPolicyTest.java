package election.business;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import election.business.interfaces.Election;
import election.data.ElectionListDB;
import election.data.SequentialTextFileList;
import election.data.VoterListDB;
import util.ListUtilities;

/**
 * Test class of the VoterNameComparator class
 * 
 * @author Nikita
 * @version 13.10.2017
 */
public class DawsonRankedElectionPolicyTest {
	public static void main(String[] args) {
		testGetWinner();
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
		elecs[1] = "Dawson College Student Union Election*2017*9*10*2017*9*14***ranked*4" + "\nJonathan Pesce"
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
	
	private static void testGetWinner() {
		System.out.println("Testing the getWinner \n");
		System.out.println("Case: Complete Election");
		setup();
		SequentialTextFileList file = new SequentialTextFileList ("datafiles/testfiles/testVoters.txt", "datafiles/testfiles/testElections.txt", "datafiles/testfiles/testTally.txt");
		ElectionListDB eldb = new ElectionListDB(file);

		try {
			DawsonRankedElectionPolicy drep = new DawsonRankedElectionPolicy(eldb.getElection("Dawson College Student Union Election"));
			System.out.print("\nA DawsonRankedElectionPolicy instance was created");
			List<String> list = drep.getWinner();
		} 
		catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} 
		catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		System.out.println("\n");
		
		teardown();
	}
}
