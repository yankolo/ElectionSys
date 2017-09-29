package election.business;
 
 import java.io.Serializable;
 /**
  * 
  * 
  * @author Nikita
  * @version 30.09.2017
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
 } 