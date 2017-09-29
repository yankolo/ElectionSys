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
 	private Name name;
 	private Email email;
 	private PostalCode postalCode;
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
	public PostalCode getPostalCode(){
		return postalCode;
	}
	
	@Override
	public void setPostalCode(PostalCode pCode){
		if(pCode == null)
			throw new IllegalArgumentException("Cannot set DawsonVoter's PostalCode to a null PostalCode");
		
		this.postalCode = new PostalCode(pCode.getCode());
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