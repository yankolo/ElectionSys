/**
 * 
 */
package election.data;

import java.io.IOException;
import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Election> getElectionDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveVoterDatabase(List<Voter> voters) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveElectionDatabase(List<Election> elections) throws IOException {
		// TODO Auto-generated method stub

	}

}
