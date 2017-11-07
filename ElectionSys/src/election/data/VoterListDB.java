package election.data;

import java.io.IOException;
import java.util.List;

import election.business.interfaces.ElectionFactory;
import election.business.interfaces.Voter;
import election.data.interfaces.InexistentVoterException;
import election.data.interfaces.ListPersistenceObject;
import election.data.interfaces.VoterDAO;
import lib.Email;
import lib.PostalCode;

public class VoterListDB implements VoterDAO {
	private List<Voter> database;
	private final ListPersistenceObject listPersistenceObject;
	private final ElectionFactory factory;
	
	/**
	 * 
	 * @param listPersistenceObject
	 */
	
	public VoterListDB (ListPersistenceObject listPersistenceObject) {
		
		
	}
	
	/**
	 * 
	 * @param listPersistenceObject
	 * @param factory
	 */
	public VoterListDB (ListPersistenceObject listPersistenceObject,
			ElectionFactory factory) {
		
		
		
		
		
		
		
	}
	/**
	 * 
	 * @param voter
	 * @throws DuplicateVoterException
	 */
	@Override
	public void add(Voter voter) throws DuplicateVoterException {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @param email
	 * @param postalCode
	 * @throws InexistentVoterException
	 */
	@Override
	public void update(Email email, PostalCode postalCode) throws InexistentVoterException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 */
	@Override
	public String toString() {
		
		
	}


}
