package election.ui.tui;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import election.business.interfaces.*;

public class TextView implements Observer{
	
	public TextView(Observable o) {
		o.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg != null) {
			if(arg instanceof Voter) {
				displayVoter((Voter)arg);
			}
			else if (arg instanceof Election) {
				displayElection((Election)arg);
			}
			else if (arg instanceof List<?>){
				displayWinners((List)arg);
			}
		}
	}
	
	private void displayVoter(Voter voter) {
		System.out.println();
		System.out.println("Voter Information");
		System.out.println("Name: " + voter.getName());
		System.out.println("Email: " + voter.getEmail());
		System.out.println("Postal Code: " + voter.getPostalCode());
	}
	
	private void displayElection(Election election) {
		System.out.println();
		System.out.println("Election Information");
		System.out.println("Name: " + election.getName());
		System.out.println("Start date: " + election.getStartDate());
		System.out.println("End date: " + election.getEndDate());
	}
	
	private void displayWinners(List<String> strList) {
		System.out.println();
		if (strList.size() == 0) {
			System.out.println("There are no winners for this election!");
		}
		else {
			for(String str: strList)
				System.out.println(str);
		}
		
	}
}
