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
 * The SerializedFileLoaderApp loads the data from the
 * sequential text files into a Lists. Then takes the Voter List and
 * the Election List databases and serializes them to object serialized
 * files.
 *
 */
public class SerializedFileLoaderApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SequentialTextFileList file = new SequentialTextFileList("datafiles/database/voters.txt",
					"datafiles/database/elections.txt", "datafiles/database/tally.txt");

			List<Voter> voterList = file.getVoterDatabase();
			List<Election> electionList = file.getElectionDatabase();

			util.Utilities.serializeObject(voterList, "datafiles/database/voters.ser");
			util.Utilities.serializeObject(electionList, "datafiles/database/elections.ser");

		} catch (IOException ioe) {
			System.err.println(ioe);
		}

	}

}
