package election.business;
 
import java.io.Serializable;
import election.business.interfaces.Election;
import election.business.interfaces.Voter;
import lib.*;

 /**
  * 
  * 
  * @author Nikita
  * @version 29.09.2017
  */
 public class DawsonVoter implements Voter, Serializable{
 	private final Name name;
 	private final Email email;
 	private final PostalCode postalCode;
 	private static final long serialVersionUID = 42031768871L;
 	
 	public DawsonVoter(String fName, String lName, String eAddress, String pCode){
 		name = new Name(fName, lName);
 		email = new Email(eAddress);
 		postalCode = new PostalCode(pCode);
 	}

 	@Override
	public Name getName(){
		return name;
 	}

	@Override
	public Email getEmail(){
		return email;
	}

	@Override
	public PostalCode getPostalCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setPostalCode(PostalCode newCode) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int compareTo(Voter o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEligible(Election election) {
		// TODO Auto-generated method stub
		return false;
	}

	
 } 