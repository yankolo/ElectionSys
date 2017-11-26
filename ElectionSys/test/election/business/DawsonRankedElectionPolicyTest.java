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
		teardown();
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

		String[] elecs = new String[4];
		
		elecs[0] = "Best Show*2018*5*1*2019*5*31*H4G*H4G*single*2" + "\nGame of Thrones" + "\nNarcos";
		elecs[1] = "DSU Election*2017*9*10*2017*9*14***ranked*4" + "\nJonathan Pesce"
				+ "\nNicholas Apanian" + "\nNiv Abecassis" + "\nAnh Quan Nguyen";
		elecs[2] = "MTL Election*2017*9*10*2017*9*14***ranked*4" + "\nJonathan Pesce"
				+ "\nNicholas Apanian" + "\nNiv Abecassis" + "\nAnh Quan Nguyen";
		elecs[3] = "WorldWide Election*2018*9*10*2018*9*14***ranked*4" + "\nJonathan Pesce"
				+ "\nNicholas Apanian" + "\nNiv Abecassis" + "\nAnh Quan Nguyen";
		


		String[] tallies = new String[4];
		tallies[0] = "Best Show*2" + "\n1000*0" + "\n0*560";
		tallies[1] = "DSU Election*4" + "\n2*3*0*0" + "\n10*1*0*0" + "\n1*0*0*0" + "\n1*10*0*0";
		tallies[2] = "MTL Election*4" + "\n2*3*0*0" + "\n4*1*0*0" + "\n5*0*0*0" + "\n1*10*0*0";
		tallies[3] = "WorldWide Election*4" + "\n2*3*0*0" + "\n10*1*0*0" + "\n5*0*0*0" + "\n1*10*0*0";
		

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
		System.out.println("Testing the getWinner method\n");
		System.out.println("Case 1: Complete Election with 1 winner");
		setup();
		SequentialTextFileList file = new SequentialTextFileList ("datafiles/testfiles/testVoters.txt", "datafiles/testfiles/testElections.txt", "datafiles/testfiles/testTally.txt");
		ElectionListDB eldb = new ElectionListDB(file);

		try {
			DawsonRankedElectionPolicy drep1Winner = new DawsonRankedElectionPolicy(eldb.getElection("DSU Election"));
			System.out.print("\tA DawsonRankedElectionPolicy instance was created");
			List<String> winner = drep1Winner.getWinner();
			System.out.println("\n\tTest Passed... There is only " + winner.size() + " winner: " + winner.get(0) + ".");

		} 
		catch (Exception e) { 
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		
		//--------------------------------------------------------- Case 2 ---------------------------------------------------------
		
		System.out.println("\n\nCase 2: Complete Election with multiple winners (tie)");
		
		try {
			DawsonRankedElectionPolicy drepTie = new DawsonRankedElectionPolicy(eldb.getElection("MTL Election"));
			System.out.print("\tA DawsonRankedElectionPolicy instance was created");
			List<String> winners = drepTie.getWinner();
			System.out.println("\n\tTest Passed... There are " + winners.size() + " winners (" + winners.get(0) + " and " + winners.get(1) + "). It is a draw.");
		} 
		catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		
		//--------------------------------------------------------- Case 3 ---------------------------------------------------------
		
		System.out.println("\n\nCase 3: Incomplete Election (Should throw iee)");
		
		try {
			DawsonRankedElectionPolicy drepIncompleteElec = new DawsonRankedElectionPolicy(eldb.getElection("WorldWide Election"));
			drepIncompleteElec.getWinner();
		} 
		catch(IncompleteElectionException iee) {
			System.out.println("\tTest passed... The IncompleteElectionException with the following message was thrown:");
			System.out.print("\n\t" + iee.getMessage());
		}
		catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		
		//--------------------------------------------------------- Case 4 ---------------------------------------------------------
		
		System.out.println("\n\n\nCase 4: Single Election Type (Should throw iae)");
		
		try {
			DawsonRankedElectionPolicy drepSingleElec = new DawsonRankedElectionPolicy(eldb.getElection("Best Show"));
			drepSingleElec.getWinner();
		} 
		catch (IllegalArgumentException iae) {
			System.out.println("\tTest passed... The IllegalArgumentException with the following message was thrown: \t");
			System.out.println("\n\t" + iae.getMessage());
		} 
		catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		
		teardown();
	}
}
