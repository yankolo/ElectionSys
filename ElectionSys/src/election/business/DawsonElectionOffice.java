package election.business;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import election.business.interfaces.Ballot;
import election.business.interfaces.Election;
import election.business.interfaces.ElectionFactory;
import election.business.interfaces.ElectionOffice;
import election.business.interfaces.Voter;
import election.data.*;
import election.data.interfaces.*;

/**
 * Provides useful methods to manage the election process.
 * 
 * @author Nikita
 */
public class DawsonElectionOffice extends Observable implements ElectionOffice, Serializable{
	
	 private final ElectionFactory factory;
	 private final ElectionDAO elections;
	 private final VoterDAO voters;
	 private static final long serialVersionUID = 42031768871L;
	 
	 /**
	  * Constructor initializes all private attributes
	  * 
	  * @param factory
	  * @param elections
	  * @param voters
	  */
	 public DawsonElectionOffice (ElectionFactory factory, ElectionDAO elections, VoterDAO voters) {
		 this.factory = factory;
		 this.elections = elections;
		 this.voters = voters;
	 }

	 /**
	  * Get a Ballot for a given Election for a given Voter
	  * 
	  * @param voter who wants to vote
	  * @param election to vote in
	  * @return Ballot
	  * @throws InvalidVoterException if voter cannot vote in the election
	  */
	  @Override
	  public Ballot getBallot(Voter voter, Election election) throws InvalidVoterException{
		  return election.getBallot(voter);
	  }
	 
	 /**
	  * Enable a given voter to cast a given ballot
	  * 
	  * @param voter who wants to cast a ballot
	  * @param ballot to cast
	  */
	  @Override
	  public void castBallot(Voter voter, Ballot b) {
		  b.cast(voter);
	  }
	 
	 /**
	  * Save all data and close the admin office and voting booth
	  * 
	  * @throws IOException if there is a problem closing the files.
	  */
	  @Override
	  public void closeOffice() throws IOException{
			  elections.disconnect();
			  voters.disconnect();
	  }

	 /**
	  * Create a new Election
	  * 
	  * @param name
	  * @param type
	  * @param startYear
	  * @param startMonth
	  * @param startDay
	  * @param endYear
	  * @param endMonth
	  * @param endDay
	  * @param startRange
	  * @param endRange
	  * @param choices
	  * @return the new election
	  * @throws DuplicateElectionException if an election with same name exists
	  */
	  @Override
	  public Election createElection(String name, String type, int startYear, int startMonth, int startDay, 
	   int endYear, int endMonth, int endDay, String startRange, String endRange, String... choices)
	   throws DuplicateElectionException{
		  return factory.getElectionInstance(name, type, startYear, startMonth, startDay, endYear, endMonth, endDay, startRange, endRange, choices);		  
	 }

	 /**
	  * Determine the winner of an election
	  * 
	  * @param election
	  * @throws IncompleteElectionException if the election is ongoing
	  */
	  @Override
	  public List<String> getWinner(Election election) {
		  return factory.getElectionPolicy(election).getWinner();
	  }
	  
	 /**
	  * Register a new Voter
	  * 
	  * @param firstName
	  * @param lastName
	  * @param email
	  * @param postalcode
	  * @return The Voter object
	  * @throws DuplicateVoterException if a voter with same e-mail address exists
	  */
	  @Override
	  public Voter registerVoter(String firstName, String lastName, String email, String postalcode) throws DuplicateVoterException {
		  return factory.getVoterInstance(firstName, lastName, email, postalcode);
	  }
	 
	 /**
	  * Find and return the Election with the given name
	  * 
	  * @param name of the election
	  * @return Election object
	  * @throws InexistentElectionException if an election with that name cannot be found
	  */
	  @Override
	  public Election findElection(String name) throws InexistentElectionException{
		  return elections.getElection(name);
	  }
	   
	 /**
	  * Find and return the Voter with the given email address
	  * 
	  * @param email address
	  * @return Voter object
	  * @throws InexistentVoterException if a voter with that email address cannot be found
	  */
	  @Override
	  public Voter findVoter (String email)throws InexistentVoterException{
		  return voters.getVoter(email);
	  }
}
