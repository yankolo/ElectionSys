/**
 * 
 */
package lib;

/**
 * @author katsuragi
 *
 */
public class AddressTest {

	/**
	 * @param args
	 * Main method of the AddressTest class 
	 * Test all of the Address instance methods
	 */
	//Ask the teacher how to test the getAddress method
	public static void main(String[] args) {
		testTheThreeParameterConstructor();
		testGetCivicNumber();
		testSetCivicNumber();
		testGetCity();
		testSetCity();
		testGetProvince();
		testSetProvince();
		testGetCode();
		testSetCode();
		testGetStreetName();
		testSetStreetName();
	}
	//ask teacher how to properly test a no parameter constructor and ask how to properly test the getAddress method 
	private static void testNoParameterConstructor() {
		System.out.println("\nTesting the no parameter constructor.");
	}

	private static void testNoParameterConstructor(String testCase) {
		System.out.println("   " + testCase);
		try {
			Address theAddress = new Address();
			System.out.println(theAddress.getCivicNumber());
		} catch (NullPointerException npe) {

		} catch (IllegalArgumentException iae) {

		}

	}

	private static void testTheThreeParameterConstructor() {
		System.out.println("\nTesting the three parameter constructor.");
		testTheThreeParameterConstructor("Case 1 - Valid data (3040 Sherbrooke Westmount)", "3040", "Sherbrooke",
				"Westmount", true);
		testTheThreeParameterConstructor("Case 2 - Invalid data – empty civicNumber ( Sherbrooke Westmount)", "",
				"Sherbrooke", "Westmount", false);
		testTheThreeParameterConstructor("Case 3 - Invalid data - all spaces civiNumber (    Sherbrooke Westmount)",
				"       ", "Sherbrooke", "Westmount", false);
		testTheThreeParameterConstructor("Case 4 - Invalid data - empty streetName (3040 Westmount)", "3040", "",
				"Westmount", false);
		testTheThreeParameterConstructor("Case 5 - Invalid data - all spaces streetName (3040       WestMount)", "3040",
				"       ", "Westmount", false);
		testTheThreeParameterConstructor("Case 6 - Invalid data - empty city (3040 Sherbrooke)", "3040", "Sherbrooke",
				"", false);
		testTheThreeParameterConstructor("Case 7 - Invalid data - all spaces city (3040 Sherbrooke      )", "3040",
				"Sherbrooke", "        ", false);
		testTheThreeParameterConstructor("Case 8 - Invalid data – null civicNumber (null Sherbrooke Westmount)", null,
				"Sherbrooke", "Westmount", false);
		testTheThreeParameterConstructor("Case 8 - Invalid data – null streetName (null Sherbrooke Westmount)", "3040",
				null, "Westmount", false);
		testTheThreeParameterConstructor("Case 8 - Invalid data – null streetName (null Sherbrooke Westmount)", "3040",
				"Sherbrooke", null, false);

	}

	private static void testTheThreeParameterConstructor(String testCase, String civicNumber, String streetName,
			String city, boolean expectValid) {

		System.out.println("   " + testCase);
		try {
			Address theAddress = new Address(civicNumber, streetName, city);
			System.out.print("\tThe Address instance was created: " + theAddress);

			if (!expectValid)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

			System.out.println("\n");
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			if (expectValid) {
				System.out.println(" Error! Expected Valid. ====== FAILED TEST =====");
			}
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
			if (expectValid) {
				System.out.println("Expected Valid");
			}
		}
		System.out.println("\n");

	}

	private static void testGetCivicNumber() {
		System.out.println("\n\nTesting the getCivicNumber method.");
		testGetCivicNumber("Case 1: 3040 without leading/trailing spaces", "3040", "3040");
		testGetCivicNumber("Case 2: 3040 with leading/trailing spaces", "    3040    ", "3040");
	}

	private static void testGetCivicNumber(String testCase, String civicNumber, String expectedCivicNumber) {
		System.out.println("   " + testCase);
		Address theAddress = new Address(civicNumber, "Sherbrooke", "Westmount");
		System.out.print("\tThe Address instance was created: " + theAddress);

		if (!theAddress.getCivicNumber().equals(expectedCivicNumber))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println("\n");
	}

	private static void testSetCivicNumber() {
		System.out.println("\n\nTesting the setCivicNumber method.");
		testSetCivicNumber("Case 1: Valid - 2086 without leading/trailing spaces", "2086", "2086", true);
		testSetCivicNumber("Case 2: Invalid null civic number", null, "", false);
		testSetCivicNumber("Case 3 Empty String as a civic number", "", "", false);
		testSetCivicNumber("Case 4 all spaces as a civic number", "        ", "      ", false);
	}

	private static void testSetCivicNumber(String testCase, String civicNumber, String expectedCivicNumber,
			boolean expectValid) {
		System.out.println("   " + testCase);
		Address theAddress = new Address("3040", "Sherbrooke", "Westmount");
		try {
			theAddress.setCivicNumber(civicNumber);
			System.out.print("\tThe Address instance was created: " + theAddress);

			if (!theAddress.getCivicNumber().equals(expectedCivicNumber))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.print(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}

	private static void testGetCity(String testCase, String city, String expectedCity) {
		System.out.println("   " + testCase);
		Address theAddress = new Address("3040", "Sherbrooke", city);
		System.out.println("\tThe Address instance was created: " + theAddress);
		if (!theAddress.getCity().equals(expectedCity)) {
			System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		System.out.println("\n");
	}

	private static void testGetCity() {
		System.out.println("\n\nTesting the getCivicNumber method.");
		testGetCity("Case 1: Sherbrooke without leading trailing spaces", "Westmount", "Westmount");
		testGetCity("Case 2: Sherbrooke with leading/trailing spaces", "    Westmount    ", "Westmount");

	}

	private static void testSetCity(String testCase, String city, String expectedCity, boolean expectValid) {
		System.out.println("   " + testCase);
		Address theAddress = new Address("3040", "Sherbrooke", "Westmount");
		try {
			theAddress.setCity(city);
			System.out.print("\tThe Address instance was created: " + theAddress);

			if (!theAddress.getCity().equals(expectedCity))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.print(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}

	private static void testSetCity() {
		System.out.println("\n\nTesting the setCity method.");
		testSetCity("Case 1: Valid - HelloWorld without leading/trailing spaces", "HelloWorld", "HelloWorld", true);
		testSetCity("Case 1: Valid - HelloWorld with leading/trailing spaces", "     HelloWorld    ", "HelloWorld",
				true);
		testSetCity("Case 2: Invalid - null city name", null, "", false);
		testSetCity("Case 3: Invalid - Empty String as a city", "", "", false);
		testSetCity("Case 4: Invalid - all spaces as a city", "        ", "      ", false);
	}

	private static void testGetProvince(String testCase, String province, String expectedProvince) {
		System.out.println("   " + testCase);
		Address theAddress = new Address("3040", "Sherbrooke", "Westmount");
		System.out.println("\tThe Address instance was created: " + theAddress);
		if (!theAddress.getProvince().equals(expectedProvince)) {
			System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		System.out.println("\n");
	}

	private static void testGetProvince() {
		System.out.println("\n\nTesting the getProvince method.");
		testGetProvince("Case 1: Creating an Address Object that has no province", "", "");

	}

	private static void testSetProvince(String testCase, String province, String expectedProvince,
			boolean expectValid) {
		System.out.println("   " + testCase);
		Address theAddress = new Address("3040", "Sherbrooke", "Westmount");
		try {
			theAddress.setProvince(province);
			System.out.print("\tThe Address instance was created: " + theAddress);

			if (!theAddress.getProvince().equals(expectedProvince))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.print(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}

	private static void testSetProvince() {
		System.out.println("\n\nTesting the setCity method.");
		testSetProvince("Case 1: Valid - Quebec without leading/trailing spaces", "Quebec", "Quebec", true);
		testSetProvince("Case 1: Valid - Quebec with leading/trailing spaces", "     Quebec    ", "Quebec", true);
		testSetProvince("Case 2: Invalid - null province name", null, "", false);
		testSetProvince("Case 3: Invalid - Empty String as a Province", "", "", false);
		testSetProvince("Case 4: Invalid - all spaces as a Province", "        ", "      ", false);
	}

	private static void testGetCode(String testCase, String code, String expectedCode) {
		System.out.println("   " + testCase);
		Address theAddress = new Address("3040", "Sherbrooke", "Westmount");
		System.out.println("\tThe Address instance was created: " + theAddress);
		if (!theAddress.getCode().equals(expectedCode)) {
			System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		System.out.println("\n");
	}

	private static void testGetCode() {
		System.out.println("\n\nTesting the getCode method.");
		testGetCode("Case 1: Creating an Address Object that has no postal code", "", "");

	}

	private static void testSetCode(String testCase, String code, String expectedCode, boolean expectValid) {
		System.out.println("   " + testCase);
		Address theAddress = new Address("3040", "Sherbrooke", "Westmount");
		try {
			theAddress.setCode(code);
			System.out.print("\tThe Address instance was created: " + theAddress);

			if (!theAddress.getCode().equals(expectedCode))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.print(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}

	private static void testSetCode() {
		System.out.println("\n\nTesting the setCode method.");
		testSetCode("Case 1: Valid -  J4Y 2N3 without leading/trailing spaces", "J4Y 2N3", "J4Y 2N3", true);
		testSetCode("Case 1: Valid -  J4Y 2N3 with leading/trailing spaces", "     J4Y 2N3    ", "J4Y 2N3", true);
		testSetCode("Case 2: Invalid - null postal code", null, "", false);
		testSetCode("Case 3: Invalid - Empty String as postal code", "", "", false);
		testSetCode("Case 4: Invalid - all spaces as a postal code", "        ", "      ", false);
	}

	private static void testGetStreetName(String testCase, String streetName, String expectedStreetName) {
		System.out.println("   " + testCase);
		Address theAddress = new Address("3040", streetName, "Westmount");
		System.out.print("\tThe Address instance was created: " + theAddress);

		if (!theAddress.getStreetName().equals(expectedStreetName))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println("\n");
	}

	private static void testGetStreetName() {
		System.out.println("\n\nTesting the getCivicNumber method.");
		testGetStreetName("Case 1: Sherbrooke without leading/trailing spaces", "Sherbrooke", "Sherbrooke");
		testGetStreetName("Case 2: Sherbrooke with leading/trailing spaces", "    Sherbrooke    ", "Sherbrooke");
	}

	private static void testSetStreetName(String testCase, String streetName, String expectedStreetName,
			boolean expectValid) {
		System.out.println("   " + testCase);
		Address theAddress = new Address("3040", "Bob", "Westmount");
		try {
			theAddress.setStreetName(streetName);
			System.out.print("\tThe Address instance was created: " + theAddress);

			if (!theAddress.getStreetName().equals(expectedStreetName))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		} catch (IllegalArgumentException iae) {
			System.out.print("\t" + iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		} catch (Exception e) {
			System.out.print(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}

	private static void testSetStreetName() {
		System.out.println("\n\nTesting the setStreetName method.");
		testSetStreetName("Case 1: Valid -  Sherbrooke without leading/trailing spaces", "Sherbrooke", "Sherbrooke",
				true);
		testSetStreetName("Case 1: Valid -  Sherbrooke with leading/trailing spaces", "     Sherbrooke    ",
				"Sherbrooke", true);
		testSetStreetName("Case 2: Invalid - null as a street name", null, "", false);
		testSetStreetName("Case 3: Invalid - Empty String as street name", "", "", false);
		testSetStreetName("Case 4: Invalid - all spaces as a street name", "        ", "      ", false);
	}

}
