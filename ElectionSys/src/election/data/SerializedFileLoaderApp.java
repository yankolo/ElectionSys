/**
 * 
 */
package election.data;

import java.io.IOException;
import java.util.List;

import election.business.interfaces.Election;
import election.business.interfaces.Voter;

/**
 * @author Yanik, Mohamed
 *
 */
public class SerializedFileLoaderApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		SequentialTextFileList file = new SequentialTextFileList("datafiles/databse/voters.txt",
				"datafiles/databse/elections.txt", "datafiles/databse/tally.txt");
		
		List<Voter> voterList = file.getVoterDatabase();
		List<Election> electionList = file.getElectionDatabase();
		
		util.Utilities.serializeObject(voterList, "datafiles/database/voters.ser");
		util.Utilities.serializeObject(electionList, "datafiles/database/elections.ser");
		
		}catch(IOException ioe) {
			System.err.println(ioe);
		}

	}

}
