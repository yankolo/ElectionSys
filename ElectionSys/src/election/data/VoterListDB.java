package election.data;

import java.io.IOException;
import java.util.List;

import election.business.DawsonElection;
import election.business.DawsonElectionFactory;
import election.business.interfaces.ElectionFactory;
import election.business.interfaces.Voter;
import election.data.InexistentVoterException;
import election.data.interfaces.ListPersistenceObject;
import election.data.interfaces.VoterDAO;
import lib.Email;
import lib.PostalCode;
import util.ListUtilities;

/**
 * Class that holds the Voter Database
 * Implements VoterDAO
 * 
 * @author Yanik Kolomatski
 * @author Mohamed Hamza
 *
 */
public class VoterListDB implements VoterDAO {
	private List<Voter> database;
	private final ListPersistenceObject listPersistenceObject;
	private final ElectionFactory factory;
	
	/**
	 * Initializes the database using a ListPersistenceObject
	 * 
	 * @param listPersistenceObject The object that holds the file paths (for the databases)
	 */
	
	public VoterListDB (ListPersistenceObject listPersistenceObject) {
		this.database = listPersistenceObject.getVoterDatabase();
		this.listPersistenceObject = listPersistenceObject;
		this.factory = DawsonElectionFactory.DAWSON_ELECTION;
	}
	
	/**
	 * Initializes the database using a ListPersistenceObject and sets a 
	 * different ElectionFactory (factory class used to initialize objects)
	 * 
	 * @param listPersistenceObject The object that holds the file paths (for the databases)
	 * @param factory Factory class used to initialize objects
	 */
	public VoterListDB (ListPersistenceObject listPersistenceObject,
			ElectionFactory factory) {
		this.database = listPersistenceObject.getVoterDatabase();
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
	}
	/**
	 * Adds a voter to the database. The voter is added in
	 * natural sort order to keep the database sorted.
	 * Only one customer can be inserted for an email address.
	 * 
	 * @param voter The voter to add to the database.
	 * @throws DuplicateVoterException The voter already exists. 
	 */
	@Override
	public void add(Voter voter) throws DuplicateVoterException {
		int insertionIndex = ListUtilities.binarySearch(database, voter, 0, database.size()-1);
		if (insertionIndex >= 0) 
			throw new DuplicateVoterException("The Voter " + voter + " is already in the database");
		
		insertionIndex = (insertionIndex * -1) - 1;

		database.add(insertionIndex, factory.getVoterInstance(voter));
	}
	
	/**
	 * Saves the list of voters and disconnects from the database. 
	 * 
	 * @throws IOException Problems saving or disconnecting from database.
	 */
	@Override
	public void disconnect() throws IOException {
		listPersistenceObject.saveVoterDatabase(database);
	}
	
	/**
	 * Returns the voter with the specified email address. 
	 * 
	 * @param email The email of the requested voter.
	 * @return The voter with the specified email.
	 * @throws InexistentVoterException If there is no voter with the specified email. 
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
	 * Modifies a voter's postal code. 
	 * 
	 * @param email The email of the voter to be updated
	 * @param postalCode The new postal code
	 * @throws InexistentVoterException The voter is not in database.
	 */
	@Override
	public void update(Email email, PostalCode postalCode) throws InexistentVoterException {
		Voter voter = this.getVoter(email.toString());
		voter.setPostalCode(postalCode);
	}
	
	/**
	 * @return The string representation of the voter database
	 */
	@Override
	public String toString() {
		String voterListDB = "Number of voters in database: " + database.size(); 
		
		for(Voter voter : database) 
			voterListDB += "\n" + voter;
		
		return voterListDB;
	}


}
