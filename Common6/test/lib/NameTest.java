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
		testCompareTo();
		testEquals();
		testHashCode();
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
		testTwoParameterConstructor("Case 8 - Valid data - non letter characters (S'am-my Chao uki)", "S'am-my", "Chao uki", true);
		testTwoParameterConstructor("Case 8 - Invalid data - illegal characters (Sammy- Chaouki)", "Sammy-", "Chaouki", false);
		testTwoParameterConstructor("Case 9 - Invalid data - illegal characters (Sammy' Chaouki)", "Sammy'", "Chaouki", false);
		testTwoParameterConstructor("Case 10 - Invalid data - illegal characters (Sa  mmy Chaouki)", "Sa  mmy", "Chaouki", false);
		testTwoParameterConstructor("Case 11 - Invalid data - illegal characters (Sammy -Chaouki)", "Sammy", "-Chaouki", false);
		testTwoParameterConstructor("Case 12 - Invalid data - illegal characters (Sammy 'Chaouki)", "Sammy", "'Chaouki", false);
		testTwoParameterConstructor("Case 13 - Invalid data - illegal characters (Sammy 'Cha  ouki)", "Sammy", "Cha  ouki", false);
		testTwoParameterConstructor("Case 14 - Valid data with leading/trailing spaces (  Sammy   Chaouki)", "   Sammy   ", "Chaouki", true);
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
		
		try {
		Name newName = new Name(firstName, "Chaouki");
		System.out.print("\tThe Name instace was created: " + newName);

		if (!newName.getFirstName().equals(expectedFirstName)) {
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		System.out.print("\tReturn: " + newName.getFirstName());
		
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
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
		
		try {
		Name newName = new Name("Sammy", lastName);
		System.out.print("\tThe Name instace was created: " + newName);

		if (!newName.getLastName().equals(expectedLastName)) {
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		System.out.print("\tReturn: " + newName.getLastName());
		
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
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
		
		try {
		Name newName = new Name(firstName, lastName);
		System.out.print("\tThe Name instace was created: " + newName);
		
		if (!newName.getFullName().equals(expectedFullName)) {
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		System.out.print("\tReturn: " + newName.getFullName());
		
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		System.out.println("\n");
	}
	
	private static void testToString() {
		System.out.println("\n\nTesting the toString method.");
		testToString("Case 1: name without leading/trailing space (Yanik, Kolomatski)", "Yanik", "Kolomatski", "Yanik*Kolomatski");
		testToString("Case 2: name with leading/trailing spaces (  Yanik,   Kolomatski    )", "  Yanik", "  Kolomatski    ", "Yanik*Kolomatski");
	}
	
	private static void testToString(String testCase, String firstName, String lastName, String expectedString) {
		System.out.println("   " + testCase);
		
		try {
		
		Name name = new Name(firstName, lastName);
		System.out.print("\tThe Name instance was created: " + name);
		
		if (!name.toString().equals(expectedString))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		
		System.out.println("\n");
	}
	
	private static void testCompareTo() {
		System.out.println("\n\nTesting the compareTo method.");
		testCompareTo("Case 1: Sammy Chaouki compare to Yanik Kolomatski", "Sammy", "Chaouki", "Yanik", "Kolomatski", "negative");
		testCompareTo("Case 2: Nikita Slavin compare to Yanik Kolomatski", "Nikita", "Slavin", "Yanik", "Kolomatski", "positive");
		testCompareTo("Case 3: Mohamed Hamza compare to Sammy Hamza", "Mohamed", "Hamza", "Sammy", "Hamza", "negative");
		testCompareTo("Case 4: Mohamed Hamza compare to Mohamed Hamza", "Mohamed", "Hamza", "Mohamed", "Hamza", "zero");
	}
	
	private static void testCompareTo(String testCase, String firstName1, String lastName1, String firstName2, String lastName2, String expectedResult) {
		System.out.println("   " + testCase);
		
		try {
		
		Name name1 = new Name(firstName1, lastName1);
		Name name2 = new Name(firstName2, lastName2);
		
		System.out.print("\tThe Name instance was created: " + name1);
		System.out.print("\tThe Name instance was created: " + name2);
		
		int comparison = name1.compareTo(name2);
		System.out.print("\tReturn: " + comparison);
		
		String realResult = null;
		if (comparison < 0)
			realResult = "negative";
		else if (comparison > 0)
			realResult = "positive";
		else if (comparison == 0)
			realResult = "zero";
		
		if (!(realResult.equals(expectedResult)))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		
		System.out.println("\n");
	}
	
	private static void testEquals() {
		System.out.println("\n\nTesting the equals method.");
		testEquals("Case 1: Mohamed Hamza compare to Sammy Hamza", "Mohamed", "Hamza", "Sammy", "Hamza", false);
		testEquals("Case 2: Mohamed Hamza compare to Mohamed Hamza", "Mohamed", "Hamza", "Mohamed", "Hamza", true);
	}
	
	private static void testEquals(String testCase, String firstName1, String lastName1, String firstName2, String lastName2, boolean expectedResult) {
		System.out.println("   " + testCase);
		
		try {
		
		Name name1 = new Name(firstName1, lastName1);
		Name name2 = new Name(firstName2, lastName2);
		
		System.out.print("\tThe Name instance was created: " + name1);
		System.out.print("\tThe Name instance was created: " + name2);
		
		if (name1.equals(name2))
			if (expectedResult == false)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		if (!name1.equals(name2))
			if (expectedResult == true)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		
		System.out.print("\tReturn: " + name1.equals(name2));
		
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		
		System.out.println("\n");
	}
	
	private static void testHashCode() {
		System.out.println("\n\nTesting hashCode method.");
		testHashCode("Case 1: Mohamed Hamza compare hashcode to Sammy Hamza", "Mohamed", "Hamza", "Sammy", "Hamza", false);
		testHashCode("Case 2: Mohamed Hamza compare hashcode to  Mohamed Hamza", "Mohamed", "Hamza", "Mohamed", "Hamza", true);
	}
	
	private static void testHashCode(String testCase, String firstName1, String lastName1, String firstName2, String lastName2, boolean expectedResult) {
		System.out.println("   " + testCase);
		
		try {
		
		Name name1 = new Name(firstName1, lastName1);
		Name name2 = new Name(firstName2, lastName2);
		
		System.out.print("\tThe Name instance was created: " + name1);
		System.out.print("\tReturn: " + name1.hashCode());
		System.out.print("\tThe Name instance was created: " + name2);
		System.out.print("\tReturn: " + name2.hashCode());
		
		if (name1.hashCode() == name2.hashCode())
			if (expectedResult == false)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		if (name1.hashCode() != name2.hashCode())
			if (expectedResult == true)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		
		System.out.println("\n");
	}
	

}
