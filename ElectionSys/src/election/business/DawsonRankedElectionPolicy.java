package election.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import election.business.interfaces.Election;
import election.business.interfaces.ElectionPolicy;

/**
 * This class has a getWinner method specifically designed for Ranked elections.
 *
 * @author Nikita
 *
 */
public class DawsonRankedElectionPolicy implements ElectionPolicy{
	
	private Election election;
	private static final long serialVersionUID = 42031768871L;
	
	public DawsonRankedElectionPolicy(Election elec) {
		if(!elec.getElectionType().toString().equalsIgnoreCase("Ranked"))
			throw new IllegalArgumentException("The given election has to be ranked, not single.");
		election = elec;
	}
	
	/**
	 * Retrieves (a) winning choice(s) from an array obtained with the getVoteBreakdown method, based
	 * on the points this(these) choice(s) accumulated. Stores the winner(s) in a List and returns it
	 * 	
	 * @return List<String> collection of winning choice(s)
	 * @throws IncompleteElectionException if the election has not ended yet (current time < end time)
	 */
	@Override
	public List<String> getWinner() throws IncompleteElectionException{
		if(election.getEndDate().isAfter(LocalDate.now())) {
			throw new IncompleteElectionException();
		}
		
		List<String> winnerList = new ArrayList<String>();
		int[][] rankedChoices = election.getTally().getVoteBreakdown();
		System.out.println("\nlength: " + rankedChoices.length);
		for(int s = 0; s < rankedChoices.length; s++) {
			System.out.println("\n");
			for(int x = 0; x < rankedChoices[s].length; x++) {
				System.out.print(rankedChoices[s][x]);
			}
		}
		int winnerIndex = 0;
		int max = 0;
		
		for(int i = 0; i < rankedChoices.length; i++) {
			if((rankedChoices[i][0]) + (rankedChoices[i][1] * 2) > max) {
				max = (rankedChoices[i][0] * 5) + (rankedChoices[i][1] * 2);			
				winnerIndex = i;
			}		
		}		
		
		winnerList.add(rankedChoices[winnerIndex].toString());
		
		for(int i = 0; i < rankedChoices.length; i++) {
			if((rankedChoices[i][0] * 5) + (rankedChoices[i][1] * 2) == max)
				winnerList.add(rankedChoices[winnerIndex].toString());
		}
		
		return winnerList;
	}

}
