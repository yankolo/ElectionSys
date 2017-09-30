package election.business;

import java.io.Serializable;
import java.time.LocalDate;
import election.business.interfaces.Election;
import election.business.interfaces.Voter;
import lib.*;

 /**
  * This class lets the user create a Dawson voter entry
  * and provides some useful methods
  * 
  * @author Nikita
  * @version 30.09.2017
  */
 public class DawsonVoter implements Voter, Serializable{
 	private Name name;
 	private Email email;
 	private PostalCode postalCode;
 	private static final long serialVersionUID = 42031768871L;
 	
 	/**
 	 * Four-param. constructor sets the three instance fields
 	 * of the DV class. No data validation required since in is
 	 * already kindly done in the constructors for the involved
 	 * objects
 	 * 
 	 * @param fName is first name of voter
 	 * @param lName is last name of voter
 	 * @param eAddress is email of voter
 	 * @param pCode is postal code of voter
 	 */
 	public DawsonVoter(String fName, String lName, String eAddress, String pCode){
 		name = new Name(fName, lName);
 		email = new Email(eAddress);
 		postalCode = new PostalCode(pCode);
 	}

 	/**
 	 * Returns the name field which is a Name instance
 	 * 
 	 * @return name instance field
 	 */
 	@Override
	public Name getName(){
		return name;
 	}

 	/**
 	 * Returns the email field which is an Email instance
 	 * 
 	 * @return email instance field
 	 */
	@Override
	public Email getEmail(){
		return email;
	}

	/**
	 * Returns the postal code which is a PostalCode instance
	 * 
	 * @return postalCode instance field
	 */
	@Override
	public PostalCode getPostalCode(){
		return postalCode;
	}
	
	/**
	 * Lets the user change a voter's postal code
	 * by reassigning a new value into the 
	 * corresponding instance field. No validation
	 * needed
	 * 
	 * @param pCode is new postal code
	 */
	@Override
	public void setPostalCode(PostalCode pCode){
		this.postalCode = new PostalCode(pCode.toString());
	}
	
	/**
	 * Checks for eligibility according to three factors:
	 * 		1. If the election is still ongoing
	 * 		2. If the election is limited to a 
	 * 		   postal range
	 * 		3. Whether the postal code of the 
	 * 		   voter is in its bounds
	 * 
	 * @param elect is an Election type object
	 * @throws IllegalArgumentException if elect is null
	 * @return true/false for eligibilty
	 */
	@Override
	public boolean isEligible(Election elect){
		if(elect == null)
			throw new IllegalArgumentException("Cannot verify eligibility to vote in a null Election");
		LocalDate currentDate = LocalDate.now();
		if(currentDate.isBefore(elect.getEndDate().plusDays(1)) && currentDate.isAfter(elect.getStartDate().minusDays(1))){
			if(elect.isLimitedToPostalRange()){
				if(!(postalCode.inRange(elect.getPostalRangeStart(), elect.getPostalRangeEnd())))
					return false;
				return true;
			}
			return true;
		}
		return false;
	}	
	
	/**
	 * Checks for equality of two objects according to two factors:
	 * 		1. If they are the instances of the same class
	 * 		2. If they have the same emails
	 * 
	 * @param object is object to use for equality check
	 * @return true/false for object equality
	 */
	@Override
	public final boolean equals(Object object){
		if (this == object)
			return true; 
		if (object == null) 
			return false;
		if (!(object instanceof DawsonVoter))
			return false;
		DawsonVoter voter = (DawsonVoter) object;
		if(email.getAddress().equalsIgnoreCase(voter.email.getAddress()))
				return true;
		return false;
	}

	/**
	 * Compares two Voter type objects based on their emails
	 * 
	 * @param voter is object use for comparison
	 * @throws IllegalArgumentException if voter is null 
	 * @return compareTo's output
	 */
	@Override
	public int compareTo(Voter voter){
		if(voter == null)
			throw new IllegalArgumentException("Cannot compare a DawsonVoter to a null Voter object");
		return email.getAddress().compareToIgnoreCase(voter.getEmail().getAddress());
	}
	
	/**
	 * Returns an object's hashcode based on it's email attribute
	 * 
	 * @return hashcode
	 */
	@Override
	public final int hashCode(){
		return email.toString().toUpperCase().hashCode();
	}
	
	/**
	 * Returns a formatted String representation of a DawsonVoter instance
	 * 
	 * @return formatted String
	 */
	@Override
	public String toString(){
		return (email.getAddress() + "*" + name.getFirstName() + "*" + name.getLastName() + "*" + postalCode.getCode());
	}
 } 

