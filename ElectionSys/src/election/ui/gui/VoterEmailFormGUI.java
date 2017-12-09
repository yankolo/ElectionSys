package election.ui.gui;

import election.business.DawsonElectionOffice;
import election.business.ElectionType;
import election.business.interfaces.*;
import election.data.InexistentVoterException;
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
	private Stage primaryStage;
	
	private TextField txtField;
	private Label errorMsg;
	
//TODO add any additional properties
	
	/** 
	 * Constructor validates that the parameters are not null 
	 * and the election has ElectionType.SINGLE. Invokes the
	 * initialize() method
	 *  @throws IllegalArgumentException if the conditions are not met.
	 */
    public VoterEmailFormGUI(ElectionOffice model, Election election) {
		if(model != null && election != null) {
			if(election.getElectionType().equals(ElectionType.SINGLE)){
				this.model = model;
				this.election = election;
				initialize();
			} else {
				throw new IllegalArgumentException();
			}
		} else {
			throw new IllegalArgumentException();
		}
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
    	GridPane grid = new GridPane();
    	
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        row1.setPercentHeight(25);
        row2.setPercentHeight(25);
        row3.setPercentHeight(25);
        grid.getRowConstraints().addAll(row1,row2,row3);
        

        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Label label = new Label("Enter email:");
        txtField = new TextField();
        Button button = new Button("Sign In");
        button.setOnAction(this::signInButtonHandler);
        errorMsg = new Label();
        
        grid.add(label, 0, 0);
        grid.add(txtField, 0, 1);
        grid.add(button, 0, 2);
        grid.add(errorMsg, 0, 3);
        
        return grid;
    }

    /**
     * Event handler for the Sign In Button
     *
     * @param e
     */
    private void signInButtonHandler(ActionEvent e) {
    	try {
    		Voter voter = model.findVoter(txtField.getText());
    		
    		if (voter.isEligible(election)) {
    			Ballot ballot = model.getBallot(voter, election);
    			SingleBallotFormGUI form = new SingleBallotFormGUI(model, election, voter, ballot, this);
    			form.start(primaryStage);
    		} else {
    			errorMsg.setText("The voter is not elligble");
    		}
    	} catch (InexistentVoterException ive) {
    		errorMsg.setText("The voter doesn't exist in the database");
    	} catch (Exception expection) {
    		errorMsg.setText(expection.getMessage());
    	}
		
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
