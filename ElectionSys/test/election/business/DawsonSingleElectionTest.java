package election.business;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import election.business.interfaces.Election;
import election.data.SequentialTextFileList;
import util.ListUtilities;

public class DawsonSingleElectionTest {
	public static void main(String[] args) {

		testConstructor();
		

	}

	private static void setup() {

		String[] elecs = new String[4];

		elecs[0] = "Best OS*2016*5*1*2017*5*31*H4G*H4G*single*2" + "\nWindows" + "\nMacOS";
		elecs[1] = "Favourite program*2018*5*1*2019*5*31*H4G*H4G*single*2" + "\nGame of Thrones" + "\nNarcos";
		elecs[2] = "Presidental race*2010*10*1*2012*11*1***single*2" + "\nDonald Trump" + "\nAnyone Else";
		elecs[3] = "Dawson College Student Union Election*2018*9*10*2018*9*14***ranked*4" + "\nJonathan Pesce"
				+ "\nNicholas Apanian" + "\nNiv Abecassis" + "\nAnh Quan Nguyen";

		String[] tallies = new String[4];
		tallies[0] = "Best OS*2" + "\n150*0" + "\n0*100";
		tallies[1] = "Favourite program*2" + "\n1000*0" + "\n0*560";
		tallies[2] = "Presidental race*2" + "\n100*0" + "\n0*100";

		Path dir;
		try {
			dir = Paths.get("datafiles/testfiles");
			if (!Files.exists(dir))
				Files.createDirectory(dir);
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

	public static void testConstructor() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList(null, "datafiles/testfiles/testElections.txt",
				"datafiles/testfiles/testTally.txt");
		Election de1 = file.getElectionDatabase().get(0);
		Election de2 = file.getElectionDatabase().get(3);
		System.out.println("-------- TEST THE CONSTRUCTOR FOR THE DAWSON SINGLE ELECTION POLICY CLASS --------");
		testConstructor("Case 1 - Pass a valid Election: A election which its type is single", de1, true);
		testConstructor("Case 2 - Pass a valid election but its election type is Ranked and not single", de2, false);

	}

	public static void testConstructor(String testCase, Election election, boolean expectedResult) {
		System.out.println("\t " + testCase);
		try {
			DawsonSingleElectionPolicy dsep = new DawsonSingleElectionPolicy(election);
			System.out.println(
					" \t \t --- Passed test -- the election passed to the DawsonSingleElectionPolicy is a single type election");
			if (!expectedResult)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.println("\t \t --- " + iae.getMessage());
			if (expectedResult) {
				System.out.println("\t \t --- Error! Expected Valid. ====== FAILED TEST =====");
			}
		} catch (Exception e) {
			System.out.println("\t \t --- UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage()
					+ "====FAILED TEST====");
			if (expectedResult) {
				System.out.println("\t \t --- Error! Expected Valid. ====== FAILED TEST =====");
			}
		}
		System.out.println();
	}
}
