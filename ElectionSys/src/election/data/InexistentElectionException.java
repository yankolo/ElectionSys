package election.data;

/**
 * This exception signals that the provided Election name is does not exist in the database
 * @author Sammy Chaouki
 *
 */
public class InexistentElectionException extends Exception{
	private static final long  serialVersionUID = 42031768871L;
	
	public InexistentElectionException() {
		super("The Following Election does not exist in the database");
	}
	
	public InexistentElectionException(String message) {
		super(message);
	}
	
}
