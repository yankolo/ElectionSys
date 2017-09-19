package election.business;

import election.business.interfaces.Ballot;
import election.business.interfaces.Tally;

public class StubTally implements Tally {

	public StubTally() {

	}

	@Override
	public String getElectionName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] getVoteBreakdown() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Ballot ballot) {
		// TODO Auto-generated method stub

	}

}
