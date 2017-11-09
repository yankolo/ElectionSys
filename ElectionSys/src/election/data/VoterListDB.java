package election.data;

import java.io.IOException;
import java.util.List;

import election.business.DawsonElection;
import election.business.DawsonElectionFactory;
import election.business.interfaces.ElectionFactory;
import election.business.interfaces.Voter;
import election.data.interfaces.InexistentVoterException;
import election.data.interfaces.ListPersistenceObject;
import election.data.interfaces.VoterDAO;
import lib.Email;
import lib.PostalCode;
import util.ListUtilities;

public class VoterListDB implements VoterDAO {
	private List<Voter> database;
	private final ListPersistenceObject listPersistenceObject;
	private final ElectionFactory factory;
	
	/**
	 * 
	 * @param listPersistenceObject
	 */
	
	public VoterListDB (ListPersistenceObject listPersistenceObject) {
		this.database = listPersistenceObject.getVoterDatabase();
		this.listPersistenceObject = listPersistenceObject;
		this.factory = DawsonElectionFactory.DAWSON_ELECTION;
	}
	
	/**
	 * 
	 * @param listPersistenceObject
	 * @param factory
	 */
	public VoterListDB (ListPersistenceObject listPersistenceObject,
			ElectionFactory factory) {
		this.database = listPersistenceObject.getVoterDatabase();
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
	}
	/**
	 * 
	 * @param voter
	 * @throws DuplicateVoterException
	 */
	@Override
	public void add(Voter voter) throws DuplicateVoterException {
		int insertionIndex = ListUtilities.binarySearch(database, voter, 0, database.size()-1);
		if (insertionIndex >= 0) 
			throw new DuplicateVoterException("The provided Voter is already in the database");
		
		insertionIndex = (insertionIndex * -1) - 1;

		database.add(insertionIndex, factory.getVoterInstance(voter));
	}
	/**
	 * 
	 * @throws IOException
	 */

	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 * @param email
	 * @return
	 * @throws InexistentVoterException
	 */
	@Override
	public Voter getVoter(String email) throws InexistentVoterException {
		if(database.size() == 0) {
			throw new InexistentVoterException("The Voter with the email " + email + " does not exist in the database");
		}
		
		Voter dummyVoter = factory.getVoterInstance(database.get(0).getName().getFirstName(), database.get(0).getName().getLastName(), email, database.get(0).getPostalCode().getCode());
		int indexOfVoter = ListUtilities.binarySearch(database, dummyVoter, 0, database.size()-1);
		
		if(indexOfVoter < 0) {
			throw new InexistentVoterException("The Voter with the email " + email + " does not exist in the database");
		}
		
		
		return database.get(indexOfVoter);
	}
	/**
	 * 
	 * @param email
	 * @param postalCode
	 * @throws InexistentVoterException
	 */
	@Override
	public void update(Email email, PostalCode postalCode) throws InexistentVoterException {
		Voter voter = this.getVoter(email.toString());
		voter.setPostalCode(postalCode);
	}
	/**
	 * 
	 */
	@Override
	public String toString() {
		String voterListDB = "Number of voters in database: " + database.size(); 
		
		for(Voter voter : database) 
			voterListDB += "\n" + voter;
		
		return voterListDB;
	}


}
