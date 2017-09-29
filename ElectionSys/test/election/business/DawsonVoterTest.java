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
	}

	private static void testGetName() {
		System.out.println("Testing the getName method \n");
		testGetName("Case 1: valid name (Mo Hamza)", "Mo", "Hamza", "Mo*Hamza");
	}
	
	private static void testGetName(String testCase, String fName, String lName, String expectedResult) {
		System.out.println(testCase);
		
		try {
		DawsonVoter dv = new DawsonVoter(fName, lName, "moe@gmail.com", "J4W2Y9");
		System.out.print("\tThe Name instance was created: " + dv);

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
		System.out.print("\tThe Email instance was created: " + dv);

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
}

