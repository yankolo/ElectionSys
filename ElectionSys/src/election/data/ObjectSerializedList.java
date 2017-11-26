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
 *         ObjectSerializedList interacts with object serialized
 *         files. It allow the election system to retrieve/save the database
 *         from/to object serialized files.
 */
public class ObjectSerializedList implements ListPersistenceObject {

	private final String voterFilename;
	private final String electionFilename;

	public ObjectSerializedList(String voterFilename, String electionFilename) {
		this.voterFilename = voterFilename;
		this.electionFilename = electionFilename;
	}

	/**
	 * It creates an empty Voter List. Voters are added to the empty List using the
	 * deserializeObject method from the Utilities class. If an exception occurs
	 * while deserializing, an error message is printed and the Voters will not be
	 * added into the List.
	 */
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

	/**
	 * It creates an empty Election List. Elections are added to the empty List
	 * using the deserializeObject method from the Utilities class. If an exception
	 * occurs while deserializing, an error message is printed and the Elections
	 * will not be added into the List.
	 */
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

	/**
	 * It serializes the List of Voters using the serializedObject method from the
	 * Utilities class.
	 */
	@Override
	public void saveVoterDatabase(List<Voter> voters) throws IOException {
		util.Utilities.serializeObject(voters, voterFilename);
	}

	/**
	 * It serializes the List of Elections using the serializedObject method from
	 * the utilities class.
	 */
	@Override
	public void saveElectionDatabase(List<Election> elections) throws IOException {
		util.Utilities.serializeObject(elections, electionFilename);

	}

}
