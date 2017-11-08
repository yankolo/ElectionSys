package election.data;

/**
 * This exception signals that the provided Voter email does not exist in the
 * database
 * 
 * @author Sammy Chaouki
 *
 */
public class InexistentVoterException extends Exception {
	private static final long serialVersionUID = 42031768871L;

	public InexistentVoterException() {
		super("The Following Voter does not exist in the database");
	}

	public InexistentVoterException(String message) {
		super(message);
	}
}
