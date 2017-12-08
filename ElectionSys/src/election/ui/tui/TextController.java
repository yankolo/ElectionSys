package election.ui.tui;

import java.io.IOException;
import java.util.Scanner;

import election.business.*;
import election.business.interfaces.*;
import election.data.*;
import lib.*;

public class TextController {
	private ElectionOffice model;  

	private enum Command {
		FIND_VOTER, REGISTER_VOTER, DISPLAY_WINNERS, LAUNCH_ELECTION, STOP
	}

	public TextController(ElectionOffice model) {
		this.model = model;
	}

	/** Main loop of the menu system
	 *  @author Maja, Jaya
	 **/
	public void run() {
		Scanner keyboard = new Scanner (System.in);
		//recall: enum static method values returns an 
		//array with all enum values 
		Command[] commands = Command.values ();
		String menu = createMenu (commands);
		Command choice;

		do
		{       
			System.out.print (menu);
			choice = getUserChoice (commands,keyboard);
			switch (choice)
			{
			case FIND_VOTER:
				voterInfo(keyboard);
				break;
			case REGISTER_VOTER:
				newVoter(keyboard);
				break;
			case DISPLAY_WINNERS:
				getWinner(keyboard);
				break;
			case LAUNCH_ELECTION:
				electionInfoLaunch(keyboard);
				break;
			case STOP:
				//nothing
			}
		}
		while (choice != Command.STOP);
		
		//When stopped, need to disconnect
		try {
			this.model.closeOffice();
		}
		catch (IOException ioe) {
			System.out.println("An error occured when closing the database.");
		}
	}

	//Private method used to retrieve a voter from the model based on their
	// email.
	// Note that there are other private methods below that can be 
	// helpful.
	private Voter voterInfo(Scanner keyboard) {
		keyboard.nextLine (); //consume any previous value   
				
		//TODO

	}
	
    // Private method that is invoked to create a new Voter object and
	//   add to the model.
	// Note that there are other private methods below that can be 
	// helpful.
	private void newVoter(Scanner keyboard) {
		keyboard.nextLine (); //consume any previous value
		
		//TODO

	}
	
	//Private method used to ask the user for the name of an election
	//and return the list of winners, if possible.
	// Note that there are other private methods below that can be 
	// helpful.
	private void getWinner(Scanner keyboard) {
		keyboard.nextLine (); //consume any previous value   
		
		//TODO

	}
	
	//Private method used to ask the user for the name of an election
	//and launch the JavaFX voting booth application.
	private Election electionInfoLaunch(Scanner keyboard) {
		keyboard.nextLine (); //consume any previous value   

		//get name
		String name = getInput(keyboard, "\nPlease enter the name of the election: ");
		Election e;
		
		//get Election
		try {
			e = this.model.findElection(name);
		}
		catch (InexistentElectionException iee){
			System.out.println("\nNo election found with that name!");
			System.out.println("Please try again.");
			return null;
		}
		//launch the JavaFX application
		//Assumes that the election.ui.gui.MainApp class has been compiled
		new Thread() {
            @Override
            public void run() {
            	election.ui.gui.MainApp.initContext(model, e);
                javafx.application.Application.launch(election.ui.gui.MainApp.class);
            }
        }.start();


		return e;
	}
	
	//Private helper method to ask for an email string.
	//Invokes the Email constructor for validation. If the
	//string is invalid, it repeatedly asks the user
	private String getEmail(Scanner keyboard) {
		boolean invalid;

		String email;

		do
		{
			invalid = false;
			email = getInput(keyboard, "Please enter the email address: ");
			try {
				@SuppressWarnings("unused")
				Email emailObj = new Email(email);
			}
			catch (IllegalArgumentException e){
				System.out.println ("Invalid email!" + e.getMessage());
				System.out.print("Please try again: ");

				invalid = true;
			}
		}
		while (invalid);
		return email;
	}
	
	//Private helper method to ask for a postal code string.
	//Invokes the PostalCode constructor for validation. If the
	//string is invalid, it repeatedly asks the user
	private String getPostalcode(Scanner keyboard) {
		//TODO
	}

	//Helper method for string input
	private String getInput(Scanner keyboard, String message) {
		System.out.print(message);
		return keyboard.nextLine();
	}

	//helper method for integer input
	private int getInt(Scanner keyboard, String message, int highest) {
		System.out.print(message);
		boolean done = false;
		int result = -1;
		while (!done) {
			//user entered something that is not an int
			if (!keyboard.hasNextInt()) {
				//consume the invalid token, including any leading whitespace
				keyboard.next();
				System.out.print("Invalid – Enter only a whole number ");
			}
			else {
				result = keyboard.nextInt();
				if (result > highest || result < 0) {
					System.out.print("Invalid – Enter number less than or equal to " + highest);
				}
				else
					done = true;
			}
		}
		return result;
	}


	//Helper method that displays the menu based on the enum values
	private String createMenu (Command[] commands)
	{
		String menu = "\nDawson Election Office Menu\nSelect a choice from the menu:\n";
		int numChoices = commands.length;
		for (int i = 0 ; i < numChoices ; i++)
			menu += "\t" + (i + 1) + " - " + commands [i] + "\n";
		menu += "\nEnter your choice: ";

		return menu;
	}

	//Helper method that validates that the user choice of menu item is
	// valid.
	private Command getUserChoice (Command[] commands,Scanner keyboard)
	{ 
		boolean invalid;
		int maxChoiceValue = commands.length;
		int userChoice = 0;
		do
		{
			try
			{
				invalid = false;
				userChoice = keyboard.nextInt ();
				if (userChoice <= 0 || userChoice > maxChoiceValue)
				{
					System.out.print ("Invalid choice! Enter a number in "
							+ " the range of 1 to " + maxChoiceValue+ " ");
					invalid = true;
				}
			}
			catch (java.util.InputMismatchException e)
			{
				System.out.print ("Invalid choice! You must enter a" +
						" numeric value in the range of 1 to " + maxChoiceValue+ " ");
				invalid = true;
				keyboard.nextLine (); //consume the invalid value
			}
		}
		while (invalid);
		return commands [userChoice - 1];
	}

}
