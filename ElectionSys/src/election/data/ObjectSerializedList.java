/**
 * 
 */
package election.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sun.webkit.Utilities;

import election.business.interfaces.Election;
import election.business.interfaces.Voter;
import election.data.interfaces.ListPersistenceObject;

/**
 * @author Yanik, Mohamed
 *
 */
public class ObjectSerializedList implements ListPersistenceObject {

	private final String voterFilename;
	private final String electionFilename;

	public ObjectSerializedList(String voterFilename, String electionFilename) {
		this.voterFilename = voterFilename;
		this.electionFilename = electionFilename;
	}

	@Override
	public List<Voter> getVoterDatabase() {
		List<Voter> voters = new ArrayList<>();
		
		try {
			voters = (List<Voter>) util.Utilities.deserializeObject(voterFilename);
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} 
		
		return voters;
	}

	@Override
	public List<Election> getElectionDatabase() {
		List<Election> elections = new ArrayList<>();
		
		try {
			elections = (List<Election>) util.Utilities.deserializeObject(electionFilename);
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} 
		
		return elections;
	}

	@Override
	public void saveVoterDatabase(List<Voter> voters) throws IOException {
		util.Utilities.serializeObject(voters, voterFilename);
	}

	@Override
	public void saveElectionDatabase(List<Election> elections) throws IOException {
		util.Utilities.serializeObject(elections, electionFilename);

	}

}
