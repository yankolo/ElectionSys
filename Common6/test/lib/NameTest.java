/**
 * 
 */
package lib;

/**
 * @author katsuragi
 *
 */
public class NameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testTwoParameterConstructor();
		testGetFirstName();
		testSetFirstName();
		testGetLastName();
		testSetLastName();
		testGetFullName();

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
		System.out.println("\n");
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
		System.out.println("\n");
	}

	private static void testSetFirstName() {
		System.out.println("\n\nTesting the setFirstName method.");
		testSetFirstName("Case 1: Valid - Adam without leading/trailing spaces", "Adam", "Adam", true);
		testSetFirstName("Case 2: Valid - Adam with leading/trailing spaces", "  Adam  ", "Adam", true);
		testSetFirstName("Case 3: Invalid null first name", null, "", false);
		testSetFirstName("Case 4 Empty String as a first name", "", "", false);
		testSetFirstName("Case 5 all spaces as a first name", "        ", "      ", false);
	}

	private static void testSetFirstName(String testCase, String firstName, String expectedFirstName,
			boolean expectedValue) {
		System.out.println("   " + testCase);
		Name name = new Name("Sammy", "Chaouki");
		try {
			name.setFirstName(firstName);
			System.out.print("\tThe Name instance was created: " + name);

			if (!name.getFirstName().equals(expectedFirstName))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectedValue)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.print(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectedValue)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
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
		System.out.println("\n");
	}

	private static void testSetLastName() {
		System.out.println("\n\nTesting the setLastName method.");
		testSetLastName("Case 1: Valid - Oumbarek without leading/trailing spaces", "Oumbarek", "Oumbarek", true);
		testSetLastName("Case 2: Valid - Oumbarek with leading/trailing spaces", "  Oumbarek  ", "Oumbarek", true);
		testSetLastName("Case 3: Invalid null last name", null, "", false);
		testSetLastName("Case 4 Empty String as a last name", "", "", false);
		testSetLastName("Case 5 all spaces as a last name", "        ", "      ", false);
	}

	private static void testSetLastName(String testCase, String lastName, String expectedLastName,
			boolean expectedValue) {
		System.out.println("   " + testCase);
		Name name = new Name("Sammy", "Chaouki");
		try {
			name.setLastName(lastName);
			System.out.print("\tThe Name instance was created: " + name);

			if (!name.getLastName().equals(expectedLastName))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectedValue)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.print(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectedValue)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
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
		System.out.println("\n");
	}

}
