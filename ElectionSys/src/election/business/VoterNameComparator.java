package election.business;

import java.util.Comparator;
import election.business.interfaces.Voter;

/**
 * This class compares two voters using their names, in addition to the natural order comparison
 * 
 * @author Nikita
 *
 */
public class VoterNameComparator implements Comparator<Voter> {

	 @Override
	 public int compare(Voter voter1, Voter voter2) {
	  //If voters are equal, output of the comparison is 0
	  if (voter1.equals(voter2))
	   return 0;
	  
	  //If names are different, return name comparison
	  if (!voter1.getName().equals(voter2.getName()))
	   return voter1.getName().compareTo(voter2.getName());
	  
	  //If the name are the same, order by default order
	  return voter1.compareTo(voter2);
	 }
}
