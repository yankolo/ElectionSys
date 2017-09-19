/**
 * 
 */
package lib;

/**
 * @author katsuragi
 *
 */
public class PersonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testTwoParameterConstructor();
		testThreeParameterConstructor();

	}

	private static void testTwoParameterConstructor() {
		System.out.println("\nTesting the two parameter constructor.");
		testTwoParameterConstructor("Case 1 - Valid data (Sammy Chaouki)", "Sammy", "Chaouki", true);
		testTwoParameterConstructor("Case 2 - Invalid data - empty first name ( Chaouki)", "", "Chaouki", false);
		testTwoParameterConstructor("Case 3 - Invalid data - all spaces firstname (      Chaouki)", "     ", "Chaouki",
				false);
		testTwoParameterConstructor("Case 4 - Invalid data - null first name (null Chaouki)", null, "Chaouki", false);
		testTwoParameterConstructor("Case 5 - Invalid data - empty last name (Sammy )", "Sammy", "", false);
		testTwoParameterConstructor("Case 6 - Invalid data - all spaces last name (Sammy      )", "Sammy", "     ",
				false);
		testTwoParameterConstructor("Case 7 - Invalid data - null last name (Sammy null)", "Sammy", null, false);
	}

	private static void testTwoParameterConstructor(String testCase, String firstName, String lastName,
			boolean expectValue) {
		System.out.println("   " + testCase);
		try {
			Person pep = new Person(firstName, lastName);
			System.out.print("\tThe Person instance was created: " + pep);

			if (!expectValue)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

			System.out.println("\n");
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			if (expectValue) {
				System.out.println(" Error! Expected Valid. ====== FAILED TEST =====");
			}
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
			if (expectValue) {
				System.out.println("Expected Valid");
			}
		}
		System.out.println("\n");
	}

	private static void testThreeParameterConstructor() {
		//ask teacher if we need to check the address object with different String first name , last name 
		// create a valid Address object
		Address add1 = new Address("3040", "Sherbrooke", "Westmount");
		//create a Address with no parameters 
		Address add2 = new Address();
		//create a non-valid Address object - null referenced 
		Address add3 = null;
		// create a non-valid Address object - with empty strings
		Address add4 = new Address("", "","");
		testThreeParameterConstructor(
				"Case 1 - Valid data (Creation of a valid address object (3040 Sherbrooke Westmount) and use String that are not empty or null referenced)",
				"Sammy", "Chaouki", add1, true);
		testThreeParameterConstructor(
				"Case 2 - valid data (Creation of a valid address object ( contains empty spaces) and use String that are not empty or null referenced)",
				"Sammy", "Chaouki", add2, true);
		testThreeParameterConstructor(
				"Case 1 - invalid data (Creation of a invalid address object (null referenced) and use String that are not empty or null referenced)",
				"Sammy", "Chaouki", add3, false);
		testThreeParameterConstructor(
				"Case 1 - invalid data (Creation of a invalid address object ( uses empty strings as its parameters) and use String that are not empty or null referenced)",
				"Sammy", "Chaouki", add4, false);
		
	}

	private static void testThreeParameterConstructor(String testCase, String firstName, String lastName,
			Address address, boolean expectValue) {
		System.out.println("   " + testCase);
		try {
			Person pep = new Person(firstName, lastName, address);
			System.out.print("\tThe Person instance was created: " + pep);

			if (!expectValue)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

			System.out.println("\n");
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			if (expectValue) {
				System.out.println(" Error! Expected Valid. ====== FAILED TEST =====");
			}
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
			if (expectValue) {
				System.out.println("Expected Valid");
			}
		}
		System.out.println("\n");
	}
	private static void testGetNameMethod() {
		
	}
	//ask teacher how to properly test setName, 
	private static void testSetName() {
		
	}
}
