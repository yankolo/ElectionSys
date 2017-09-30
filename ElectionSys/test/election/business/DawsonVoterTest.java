package election.business;

import lib.*;
import election.business.interfaces.*;
import election.business.*;

/**
 * 
 * @author Nikita
 * @version 29.09.2017
 */
public class DawsonVoterTest {
	public static void main(String[] args) {
		//No need to test DawsonVoter's constructor since all
		//validation is done in the other classes
		testGetName();
		testGetEmail();
		testGetPostalCode();
		testSetPostalCode();
		testIsEligible();
	}

	private static void testGetName() {
		System.out.println("Testing the getName method \n");
		testGetName("Case 1: valid name (Mo Hamza)", "Mo", "Hamza", "Mo*Hamza");
	}
	
	private static void testGetName(String testCase, String fName, String lName, String expectedResult) {
		System.out.println(testCase);
		
		try {
		DawsonVoter dv = new DawsonVoter(fName, lName, "moe@gmail.com", "J4W2Y9");
		System.out.print("\tThe DawsonVoter instance was created: " + dv);

		if (!dv.getName().toString().equals(expectedResult)) {
			System.out.print("Error! Expected Invalid. ==== FAILED TEST ====");
		}
		System.out.print("\tReturn: " + dv.getName());
		
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		System.out.println("\n");
	}
	
	private static void testGetEmail() {
		System.out.println("\nTesting the getEmail method \n");
		testGetEmail("Case 1: valid email (moe@gmail.com)", "moe@gmail.com", "moe@gmail.com");
	}
	
	private static void testGetEmail(String testCase, String email, String expectedResult) {
		System.out.println(testCase);
		
		try {
		DawsonVoter dv = new DawsonVoter("Mo", "Hamza", email, "J4W2Y9");
		System.out.print("\tThe DawsonVoter instance was created: " + dv);

		if (!dv.getEmail().toString().equals(expectedResult)) {
			System.out.print("Error! Expected Invalid. ==== FAILED TEST ====");
		}
		System.out.print("\tReturn: " + dv.getEmail());
		
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		System.out.println("\n");
	}
	
	private static void testGetPostalCode() {
		System.out.println("\nTesting the getPostalCode method \n");
		testGetPostalCode("Case 1: valid postal code (J4W2Y9)", "J4W2Y9", "J4W2Y9");
	}
	
	private static void testGetPostalCode(String testCase, String postalCode, String expectedResult) {
		System.out.println(testCase);
		
		try {
		DawsonVoter dv = new DawsonVoter("Mo", "Hamza", "moe@gmail.com", postalCode);
		System.out.print("\tThe DawsonVoter instance was created: " + dv);

		if (!dv.getPostalCode().toString().equals(expectedResult)) {
			System.out.print("Error! Expected Invalid. ==== FAILED TEST ====");
		}
		System.out.print("\tReturn: " + dv.getPostalCode());
		
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		System.out.println("\n");
	}
	
	private static void testSetPostalCode() {
		System.out.println("\nTesting the setPostalCode method \n");
		testSetPostalCode("Case 1: valid postal code (J4W2Y9)", "J4W2Y9", "J4W2Y9");
	}
	
	private static void testSetPostalCode(String testCase, String postalCode, String expectedResult) {
		System.out.println(testCase);
		
		try {
		DawsonVoter dv = new DawsonVoter("Mo", "Hamza", "moe@gmail.com", "H4W2Y9");
		PostalCode pc = new PostalCode(postalCode);
		dv.setPostalCode(pc);
		System.out.print("\tA DawsonVoter instance was created: " + dv);
		System.out.print("\n\tA PostalCode instance was created: " + pc);

		if (!dv.getPostalCode().toString().equals(expectedResult)) {
			System.out.print("Error! Expected Invalid. ==== FAILED TEST ====");
		}
		System.out.print("\tPostal code changed from H4W2Y9 to: " + dv.getPostalCode());
		
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (Exception e) { 
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		System.out.println("\n");
	}
	
	private static void testIsEligible() {
		System.out.println("\nTesting the IsEligible method \n");
		testIsEligible("1", "Case 1: date in range, active postal code range, postal code in range", "J4W2Y9", 29, 30, true);
		testIsEligible("2", "Case 2: date in range, active postal code range, postal code not in range", "J5W2Y9", 29, 30, false);
		testIsEligible("3", "Case 3: date not in range", "J4W2Y9", 28, 29, false);
	}
	
	private static void testIsEligible(String caseNum, String testCase, String postalCode, int startDay, int endDay, boolean expectedResult) {
		System.out.println(testCase);
		
		try {
		DawsonVoter dv = new DawsonVoter("Mo", "Hamza", "moe@gmail.com", "J4W2Y9");
		DawsonElection de = new DawsonElection("e1", "RANKED", 2017, 9, startDay, 2017, 9, endDay, "H4W2Y9", "J4W2Y9", new StubTally(), "bi1", "bi2");
		System.out.print("\tA DawsonVoter instance was created: " + dv);
		System.out.print("\n\tA DawsonElection instance was created: " + de);

		if (dv.isEligible(de)){
			System.out.println("\n\t\tCase " + caseNum + " checked");
			System.out.print("\t\tisEligible: " + dv.isEligible(de));
		}
		
		else{
			System.out.println("\n\t\tCase " + caseNum + " checked");
			System.out.print("\t\tisEligible: " + dv.isEligible(de));
		}
		
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		System.out.println("\n");
	}
}

