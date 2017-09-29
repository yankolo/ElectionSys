package election.business;

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
		DawsonVoter dv2 = new DawsonVoter("Mo", "Hamza", "moe@gmail.com", postalCode);
		dv.setPostalCode(dv2.getPostalCode());
		System.out.print("\tA DawsonVoter instance was created: " + dv);
		System.out.print("\n\tA second DawsonVoter instance was created: " + dv);

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
}

