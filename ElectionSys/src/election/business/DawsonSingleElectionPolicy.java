package election.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import election.business.interfaces.*;

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
	public List<String> getWinner() throws IncompleteElectionException {
		if (election.getEndDate().isAfter(LocalDate.now())) {
			throw new IncompleteElectionException(
					"The following election: " + election.getName() + " has yet to end, so a winner cannot"
							+ " be determined until it ends. " + "This election end on: " + election.getEndDate());
		}
		List<String> winnerList = new ArrayList<String>();
		int castVoters = (election.getTotalVotesCast() / 2) + 1;
		String winner = null;
		for (int i = 0; i < election.getTally().getVoteBreakdown().length; i++) {
			if (election.getTally().getVoteBreakdown()[i][i] >= castVoters) {
				winner = election.getElectionChoices()[i];
			}
		}
		winnerList.add(winner);
		return winnerList;
	}


}
