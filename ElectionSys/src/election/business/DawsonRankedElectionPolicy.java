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
		if(!elec.getElectionType().equals(ElectionType.RANKED))
			throw new IllegalArgumentException("The election \"" + elec.getName() + "\" is of type \'" 
					+ elec.getElectionType() + "\'. Only ranked elections are valid.");
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
		//Don't be afraid of that long print statement. It is just a beautiful exception message :)
		if(election.getEndDate().isAfter(LocalDate.now())) {
			throw new IncompleteElectionException( "Please, be patient. The election \"" + election.getName() + "\" has not yet ended, " + 
					"\n\tso a winner cannot be determined as of the " + LocalDate.now().getMonth().toString().toLowerCase() + " " + LocalDate.now().getDayOfMonth() + ", " + 
						LocalDate.now().getYear() + ". This election ends on " + election.getEndDate().getMonth().toString().toLowerCase() + " " + election.getEndDate().getDayOfMonth() + 
							", " + election.getEndDate().getYear() + ".");
		}
		
		List<String> winnerList = new ArrayList<String>();
		int[][] rankedChoices = election.getTally().getVoteBreakdown();
		int winnerIndex = 0;
		int max = 0;
		
		for(int i = 0; i < rankedChoices.length; i++) {
			if((rankedChoices[i][0] * 5) + (rankedChoices[i][1] * 2) > max) {
				max = (rankedChoices[i][0] * 5) + (rankedChoices[i][1] * 2);			
				winnerIndex = i;
			}		
		}		
		
		winnerList.add(election.getElectionChoices()[winnerIndex] );
		
		for(int i = 0; i < rankedChoices.length; i++) {
			if((i != winnerIndex) && ((rankedChoices[i][0] * 5) + (rankedChoices[i][1] * 2) == max))
				winnerList.add(election.getElectionChoices()[i] );
		}
		
		return winnerList;
	}

}
