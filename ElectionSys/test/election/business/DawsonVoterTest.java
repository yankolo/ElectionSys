package election.business;

import lib.*;
import election.business.interfaces.*;
import election.business.*;

/**
 * 
 * @author Nikita
 * @version 30.09.2017
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
		testEquals();
		testCompareTo();
		testHashCode();
		testToString();
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
			else
				System.out.print("\tReturn: " + dv.getName());
		} 
		catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} 
		catch (Exception e) {
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
			else
				System.out.print("\tReturn: " + dv.getEmail());
		} 
		catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} 
		catch (Exception e) {
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
			else
				System.out.print("\tReturn: " + dv.getPostalCode());
		}
		catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} 
		catch (Exception e) {
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
			else
				System.out.print("\n\n\t\tPostal code changed from H4W2Y9 to: " + dv.getPostalCode());
		} 
		catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (Exception e) { 
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		System.out.println("\n");
	}
	
	private static void testIsEligible() {
		System.out.println("\nTesting the isEligible method \n");
		testIsEligible("1", "Case 1: date in range, active postal code range, postal code in range", "J4W2Y9", 29, 30, true);
		testIsEligible("2", "Case 2: date in range, active postal code range, postal code not in range", "G4W2Y9", 29, 30, false);
		testIsEligible("3", "Case 3: date not in range", "J4W2Y9", 28, 29, false);
	}
	
	private static void testIsEligible(String caseNum, String testCase, String postalCode, int startDay, int endDay, boolean expectedResult) {
		System.out.println(testCase);
		
		try {
			DawsonVoter dv = new DawsonVoter("Mo", "Hamza", "moe@gmail.com", postalCode);
			DawsonElection de = new DawsonElection("e1", "RANKED", 2017, 9, startDay, 2017, 9, endDay, "H4W2Y9", "J4W2Y9", new StubTally(), "bi1", "bi2");
			System.out.print("\tA DawsonVoter instance was created: " + dv);
			System.out.print("\n\tA DawsonElection instance was created: " + de);
		
			if (dv.isEligible(de) == expectedResult){
				System.out.println("\n\t\tCase " + caseNum + " checked");
				System.out.print("\t\tisEligible: " + dv.isEligible(de));
			}	
			else{
				System.out.print("Error! Expected Invalid. ==== FAILED TEST ====");
			}
		}
		catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} 
		catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		System.out.println("\n");
	}
	
	private static void testEquals() {
		System.out.println("\nTesting the equals method \n");
		testEquals("1", "Case 1: same emails 2x\"moe@gmail.com\"", "moe@gmail.com", "moe@gmail.com", true);
		testEquals("2", "Case 2: different emails \"moe@gmail.com\" and \"moeee@gmail.com\"", "moe@gmail.com", "moeee@gmail.com", false);
	}
	private static void testEquals(String caseNum, String testCase, String email, String emailTwo, boolean expectedResult) {
		System.out.println(testCase);
		
		try {
			DawsonVoter dv = new DawsonVoter("Mo", "Hamza", email, "J4W2Y9");
			DawsonVoter otherDv = new DawsonVoter("Mo", "Hamza", emailTwo, "J4W2Y9");
			System.out.print("\tA DawsonVoter instance was created: " + dv);
			System.out.print("\n\tA second DawsonVoter instance was created: " + otherDv);

			if (dv.equals(otherDv) == expectedResult){
				System.out.println("\n\n\t\tCase " + caseNum + " checked");
				System.out.print("\t\tequals: " + dv.equals(otherDv));
			}	
			else{
				System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
			}
		} 
		catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} 
		catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		System.out.println("\n");
	}
	
	private static void testCompareTo() {
		System.out.println("\nTesting the compareTo method \n");
		testCompareTo("1", "Case 1: same emails 2x\"moe@gmail.com\"", "moe@gmail.com", "moe@gmail.com", 0);
		testCompareTo("2", "Case 2: different emails \"moe@gmail.com\" and \"zoe@gmail.com\"", "moe@gmail.com", "zoe@gmail.com", -1);
	}
	private static void testCompareTo(String caseNum, String testCase, String email, String emailTwo, int expectedResult) {
		System.out.println(testCase);
		
		try {
			DawsonVoter dv = new DawsonVoter("Mo", "Hamza", email, "J4W2Y9");
			DawsonVoter otherDv = new DawsonVoter("Mo", "Hamza", emailTwo, "J4W2Y9");
			System.out.print("\tA DawsonVoter instance was created: " + dv);
			System.out.print("\n\tA second DawsonVoter instance was created: " + otherDv);
			
			if (dv.compareTo(otherDv) == expectedResult && expectedResult == 0){
				System.out.println("\n\n\t\tCase " + caseNum + " checked");
				System.out.print("\t\tcompareTo: " + dv.compareTo(otherDv) + " --- (they are the same)");
			}
			
			else if (dv.compareTo(otherDv) >= expectedResult && expectedResult > 0){
				System.out.println("\n\n\t\tCase " + caseNum + " checked");
				System.out.print("\t\tcompareTo: " + dv.compareTo(otherDv) + " --- (" + email + " comes after " + emailTwo + ")");
			}
		
			else if (dv.compareTo(otherDv) <= expectedResult && expectedResult < 0){
				System.out.println("\n\n\t\tCase " + caseNum + " checked");
				System.out.print("\t\tcompareTo: " + dv.compareTo(otherDv) + " --- (" + email + " comes before " + emailTwo + ")");
			}
		
			else{
				System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
			}	
		} 
		catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} 
		catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		System.out.println("\n");
	}
	
	private static void testHashCode() {
		System.out.println("\nTesting the hashCode method \n");
		testHashCode("Case 1: objects with same emails", "moe@gmail.com", "moe@gmail.com", true);
		testHashCode("Case 2: objects with different emails", "moe@gmail.com", "zoe@gmail.com", false);
	}
	
	private static void testHashCode(String testCase, String email, String emailTwo, boolean expectedResult) {
		System.out.println(testCase);
		
		try {
			DawsonVoter dv = new DawsonVoter("Mo", "Hamza", email, "J4W2Y9");
			DawsonVoter otherDv = new DawsonVoter("Mo", "Hamza", emailTwo, "J4W2Y9");
			System.out.print("\tA DawsonVoter instance was created: " + dv);
			System.out.print("\n\tA second DawsonVoter instance was created: " + otherDv);

			if (dv.hashCode() == otherDv.hashCode() && expectedResult == true) {
				System.out.print("\n\n\t\tTest passed, return for first object: " + dv.hashCode());
				System.out.print("\n\t\t\t     return for second object: " + otherDv.hashCode());
			}
			else if (dv.hashCode() != otherDv.hashCode() && expectedResult == false){
				System.out.print("\n\n\t\tTest passed, return for first object: " + dv.hashCode());
				System.out.print("\n\t\t\t     return for second object: " + otherDv.hashCode());
			}
			else
				System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} 
		catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		System.out.println("\n");
	}
	
	private static void testToString() {
		System.out.println("\nTesting the toString method \n");
		testToString("Case 1: formatted DawsonVoter(\"Mo\", \"Hamza\", \"moe@gmail.com\", \"J4W2Y9\") string representation", "moe@gmail.com", "Mo", "Hamza", "J4W2Y9", "moe@gmail.com*Mo*Hamza*J4W2Y9");
	}
	
	private static void testToString(String testCase, String email, String fName, String lName, String pCode, String expectedResult){
		System.out.println(testCase);
		
		try {
			DawsonVoter dv = new DawsonVoter(fName, lName, email, pCode);
			System.out.print("\tA DawsonVoter instance was created: " + dv);
			
			if(dv.toString().compareTo(expectedResult) == 0)
				System.out.print("\n\n\t\tTest passed, formatted output: " + dv.toString());
			else
				System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} 
		catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		System.out.println("\n");
	}
}

	

