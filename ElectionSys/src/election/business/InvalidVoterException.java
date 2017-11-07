package election.business;

/**
 * This exception signals that the provided voter is trying to access or cast a ballot for an election which is not in progress, or that the voter is not eligible or the voter has already voted
 * @author Sammy Chaouki
 *
 */
public class InvalidVoterException extends RuntimeException {
	private static final long  serialVersionUID = 42031768871L;
	
	
	public InvalidVoterException() {
		super("The current voter is not allowed to either get or cast a ballot for an election that is not in progress"
				+ ", or for which this voter is not eligible, or for which this voter has already voted.");
	}
	
	public InvalidVoterException(String message) {
		super(message);
	}
}
