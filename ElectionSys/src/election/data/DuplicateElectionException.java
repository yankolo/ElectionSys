package election.data;

/**
 * The exception signals that the provided election name already exists in the database
 * @author Sammy Chaouki
 *
 */

public class DuplicateElectionException extends Exception {
	private static final long serialVersionUID = 42031768871L;
	
	public DuplicateElectionException() {
		super("The provided Election name is associated with an existing election");
	}
	
	public DuplicateElectionException(String message) {
		super(message);
	}
	
}
