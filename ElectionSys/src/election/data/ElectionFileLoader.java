package election.data;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import election.business.DawsonElectionFactory;
import election.business.interfaces.Voter;
import election.business.interfaces.Election;

public class ElectionFileLoader {
	private ElectionFileLoader() {

	}

	
	public static Voter[] getVoterListFromSequentialFile(String filename) throws IOException {
		String[] linesArray = createLinesArray(filename);
		return createVoterArray(linesArray, filename);
	}
		
	/**
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Election[] getElectionListFromSequentialFile(String filename) throws IOException {
		// TODO Code Method
	}
	
	/**
	 * 
	 * @param filename
	 * @param elections
	 * @throws IOException
	 */
	public static void setExistingTallyFromSequentialFile (String filename, Election[] elections) 
			throws IOException {
		// TODO Code Method
	}
	
	private static Voter[] createVoterArray(String[] linesArray, String filename) {
		List<Voter> voterArrayList = new ArrayList<Voter>();
		for(int i=0; i<linesArray.length; i++) {
			String[] voter = linesArray[i].split("[*]");
			
			if(voter.length != 4 ) {
				System.err.println("Format Error - Invalid number of elements at line: " + (i+1) + " from file: " +filename);
			}
			else {
				String email = voter[0];
				String firstName = voter[1];
				String lastName = voter[2];
				String postal = voter[3];
				
				try {
					Voter newVoters = DawsonElectionFactory.DAWSON_ELECTION.getVoterInstance(firstName, lastName, email, postal);
					voterArrayList.add(newVoters);
				}catch(IllegalArgumentException iae) {
					System.out.println("\t" +iae.getMessage());
				}
			}
		}
		Voter[] validVoters = new Voter[voterArrayList.size()];
		return validVoters = voterArrayList.toArray(validVoters);
	}

	/*
	 *  Space to create private helper methods for Election
	 */
	
	/*
	 *  Space to create private helper methods for Tally
	 */
	

	private static String[] createLinesArray(String filename) throws IOException{
		Path file = Paths.get(filename);
		Charset uncoding = Charset.forName("UTF-8");
		
		try {
			List<String> lines = Files.readAllLines(file, uncoding);
			
			for(int i=0; i<lines.size(); i++) {
				
				if(lines.get(i).trim().isEmpty()) {
					lines.remove(i);
					i--;
				}
			}
			
			String[] linesArray = new String[lines.size()];
			linesArray = lines.toArray(linesArray);
			
			return linesArray;
		}catch(IOException ioe) {
			throw new IOException("Error reading lines from file: " + filename);
		}
	}
	

}
