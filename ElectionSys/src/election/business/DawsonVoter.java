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
  * @version 29.09.2017
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
 	 * return the name field which is a Name instance
 	 */
 	@Override
	public Name getName(){
		return name;
 	}

 	/**
 	 * return the email field which is an Email instance
 	 */
	@Override
	public Email getEmail(){
		return email;
	}

	/**
	 * return the postal code which is a PostalCode instance
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
	 */
	@Override
	public boolean isEligible(Election elect){
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
	
	@Override
	public int compareTo(Voter arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
 } 

