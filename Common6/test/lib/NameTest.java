package lib;

/**
 * @author Yanik Kolomatski
 *
 */
public class NameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testTwoParameterConstructor();
		testGetFirstName();
		testGetLastName();
		testGetFullName();
		testToString();
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
			boolean expectedValue) {
		System.out.println("  " + testCase);
		try {
			Name newName = new Name(firstName, lastName);
			System.out.println("\tThe Name instance has been created: " + newName);

			if (!expectedValue) {
				System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			if (expectedValue) {
				System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
			}
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
			if (expectedValue) {
				System.out.println("Expected Valid");
			}
		}
		System.out.print("\n");
	}

	private static void testGetFirstName() {
		System.out.println("\n\nTesting the getFirstName method.");
		testGetFirstName("Case 1: first name without leading/trailing space (Sammy)", "Sammy", "Sammy");
		testGetFirstName("Case 2: first name with leading/trailing spaces (   Sammy  )", "  Sammy   ", "Sammy");
	}

	private static void testGetFirstName(String testCase, String firstName, String expectedFirstName) {
		System.out.println("    " + testCase);
		Name newName = new Name(firstName, "Chaouki");
		System.out.println("\tThe Name instace was created: " + newName);

		if (!newName.getFirstName().equals(expectedFirstName)) {
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		System.out.print("\n");
	}

	private static void testGetLastName() {
		System.out.println("\n\nTesting the getLastName method.");
		testGetLastName("Case 1: last name without leading/trailing space (Chaouki)", "Chaouki", "Chaouki");
		testGetLastName("Case 2: last name with leading/trailing spaces (   Chaouki  )", "  Chaouki   ", "Chaouki");
	}

	private static void testGetLastName(String testCase, String lastName, String expectedLastName) {
		System.out.println("    " + testCase);
		Name newName = new Name("Sammy", lastName);
		System.out.println("\tThe Name instace was created: " + newName);

		if (!newName.getLastName().equals(expectedLastName)) {
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		System.out.print("\n");
	}
	private static void testGetFullName() {
		System.out.println("\n\nTesting the getFullName method.");
		testGetFullName("Case 1: full name without leading/trailing space (Sammy Chaouki)", "Sammy", "Chaouki",
				"Sammy Chaouki");
		testGetFullName(
				"Case 2: full name with leading/trailing spaces for first name and last name ( Sammy    Chaouki  )",
				"  Sammy  ", "  Chaouki   ", "Sammy Chaouki");
	}

	private static void testGetFullName(String testCase, String firstName, String lastName, String expectedFullName) {
		System.out.println("    " + testCase);
		Name newName = new Name(firstName, lastName);
		System.out.println("\tThe Name instace was created: " + newName);

		if (!newName.getFullName().equals(expectedFullName)) {
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		System.out.print("\n");
	}
	
	private static void testToString() {
		System.out.println("\n\nTesting the toString method.");
		testToString("Case 1: name without leading/trailing space (Yanik, Kolomatski)", "Yanik", "Kolomatski", "Yanik*Kolomatski");
		testToString("Case 2: name with leading/trailing spaces (  Yanik,   Kolomatski    )", "  Yanik", "  Kolomatski    ", "Yanik*Kolomatski");
	}
	
	private static void testToString(String testCase, String firstName, String lastName, String expectedString) {
		System.out.println("   " + testCase);
		
		Name name = new Name(firstName, lastName);
		System.out.print("\tThe Name instance was created: " + name);
		
		if (!name.toString().equals(expectedString))
			System.out.print("  Error! Inserted Value Should Have Been Valid. ==== FAILED TEST ====");
		
		System.out.println("\n");
	}

}
