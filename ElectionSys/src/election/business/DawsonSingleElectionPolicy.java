package election.business;

import java.util.List;

import election.business.interfaces.Election;
import election.business.interfaces.ElectionPolicy;

public class DawsonSingleElectionPolicy implements ElectionPolicy {

	private Election election;
	private static final long serialVersionUID = 42031768871L;

	public DawsonSingleElectionPolicy(Election election) {
		if (!election.getElectionType().equals(ElectionType.SINGLE)) {
			throw new IllegalArgumentException("The following election: " + election.getName() + " is a "
					+ election.getElectionType().toString() + ", " + "only single election type are valid ");
		}
		this.election = election;
	}
	
	
	@Override
	public List<String> getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

}
