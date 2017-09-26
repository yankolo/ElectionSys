/**
 * 
 */
package lib;

/**
 * @author Yanik Kolomatski
 *
 */
public class PostalCodeTest {
	public static void main(String[] args)
	{
		testOneParameterConstructor();
	}
	
	private static void testOneParameterConstructor() {
		System.out.println("\nTesting the one parameter constructor.");
		testOneParameterConstructor("Case 1 - Valid data, 6 character (J4W2Y9)", "J4W2Y9", true);
		testOneParameterConstructor("Case 2 - Valid data, 7 character (J4W 2Y9)", "J4W 2Y9", true);
		testOneParameterConstructor("Case 3 - Valid data, 6 character lowercase (j4w2y9)", "j4w2y9", true);
		testOneParameterConstructor("Case 4 - Valid data, 7 character lowercase (j4w 2y9)", "J4w 2y9", true);
		testOneParameterConstructor("Case 5 - Valid data, leading/trailing spaces (  J4W 2Y9  )", "  J4W 2Y9  ", true);
		testOneParameterConstructor("Case 6 - Invalid data, 8 character (J4W 2Y9H)", "J4W 2Y9H", false);
		testOneParameterConstructor("Case 7 - Invalid data, 5 character (J4W 2Y)", "J4W 2Y", false);
		testOneParameterConstructor("Case 8 - Invalid data, two spaces (J4W  2Y9)", "J4W  2Y9", false);
		testOneParameterConstructor("Case 9 - Invalid data, starts with W or Z (W4W  2Y9)", "W4W 2Y9", false);
		testOneParameterConstructor("Case 9 - Invalid data, starts with W or Z (Z4W  2Y9)", "W4W 2Y9", false);
		testOneParameterConstructor("Case 10 - Invalid data, illegal character D (J4W 2D9)", "J4W 2D9", false);
		testOneParameterConstructor("Case 11 - Invalid data, illegal character F (J4F 2Y9)", "J4F 2Y9", false);
		testOneParameterConstructor("Case 11 - Invalid data, illegal character I (J4I 2Y9)", "J4I 2Y9", false);
		testOneParameterConstructor("Case 11 - Invalid data, illegal character U (J4U 2Y9)", "J4U 2Y9", false);
		testOneParameterConstructor("Case 11 - Invalid data, illegal character Q (J4F 2Q9)", "J4F 2Q9", false);
		testOneParameterConstructor("Case 12 - Invalid data, wrong format (JWY 429)", "JWY 429", false);
		testOneParameterConstructor("Case 13 - Valid data, leading/trailing spaces (  J4W2Y9  )", "  J4W2Y9  ", true);
	}

	private static void testOneParameterConstructor(String testCase, String postalCode, boolean expectedValue) 
	{
		System.out.println("  " + testCase);
		try {
			PostalCode code = new PostalCode(postalCode);
			System.out.println("\tThe PostalCode instance has been created: " + code);

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

	
}
