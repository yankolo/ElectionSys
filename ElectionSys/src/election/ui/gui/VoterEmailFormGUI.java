package election.ui.gui;

import election.business.DawsonElectionOffice;
import election.business.ElectionType;
import election.business.interfaces.*;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

/** Form that gets the voter email, finds the Voter in the model
  * If the voter is eligible for the election, instantiate and start
  * a SingleBallotFormGUI
  */
public class VoterEmailFormGUI {
	
	private ElectionOffice model;
	private Election election;
	
//TODO add any additional properties
	
	/** 
	 * Constructor validates that the parameters are not null 
	 * and the election has ElectionType.SINGLE. Invokes the
	 * initialize() method
	 *  @throws IllegalArgumentException if the conditions are not met.
	 */
    public VoterEmailFormGUI(ElectionOffice model, Election election) {
		//TODO
    }

    /**
     * The stage and the scene are created in the start.
     *
     * @param primaryStage
     */
    public void start(Stage primaryStage) {
    	this.primaryStage=primaryStage;
        // Set Window's Title
        primaryStage.setTitle("Get Voter Email");
        GridPane root = createUserInterface();
        Scene scene = new Scene(root, 500, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Create the user interface as the root
     *
     * @return GridPane with the UI
     */
    private GridPane createUserInterface() {
        // TODO
    }

    /**
     * Event handler for the Sign In Button
     *
     * @param e
     */
    private void signInButtonHandler(ActionEvent e) {
 
		//TODO
    }

    /**
     * This method is usually used for data binding of a "data bean" class
     * to the JavaFX controls. A "bean" class is a simple class with getters
	 * and setters for all properties.
     * Changes to a control are immediately set on the bean and a change to
     * the bean is immediately shown in the control.
     */
    private void initialize() {

    }
}
