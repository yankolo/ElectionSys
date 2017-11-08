package election.business;

/**
 * This exception signals that the provided voter is trying to access or cast a ballot for an election which is not in progress, or that the voter is not eligible or the voter has already voted
 * @author Sammy Chaouki
 *
 */
public class InvalidVoterException extends RuntimeException {
	private static final long  serialVersionUID = 42031768871L;
	
	
	public InvalidVoterException() {
		super("The voter might have tried to get or cast a ballot for an election that is not"
				+ " in progress, the voter might not be eligible to vote for the election, or "
				+ " the voter might have already voted for the election");
	}
	
	public InvalidVoterException(String message) {
		super(message);
	}
}
