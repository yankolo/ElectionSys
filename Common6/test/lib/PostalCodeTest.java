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
		testCompareTo();
		testEquals();
		testHashCode();
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
	
	private static void testCompareTo() {
		System.out.println("\n\nTesting the compareTo method.");
		testCompareTo("Case 1: J4W 2Y9 compare to j4w2y9", "J4W 2Y9", "j4w2y9", "zero");
		testCompareTo("Case 2: j4w2y9 compare to h4w2y9", "j4w2y9", "h4w2y9", "positive");
		testCompareTo("Case 3: h4w2y9 compare to j4w2y9", "h4w2y9", "j4w2y9", "negative");
		testCompareTo("Case 4: null compare to j4w2y9", "null", "j4w2y9", "null");
		
	}
	
	private static void testCompareTo(String testCase, String code1, String code2, String expectedResult) {
		System.out.println("   " + testCase);
		
		try {
		PostalCode potalCode1 = new PostalCode(code1);
		PostalCode potalCode2 = new PostalCode(code2);
		
		System.out.print("\tThe Name instance was created: " + potalCode1);
		System.out.print("\tThe Name instance was created: " + potalCode2);
		
		int comparison = potalCode1.compareTo(potalCode2);
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
			if (expectedResult != "null") {
				System.out.println("  Error! Expected Invalid. ==== FAILED TEST ====");
			}
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
			if (expectedResult != "null") {
				System.out.println("Expected Valid");
			}
		}
		
		System.out.println("\n");
	}
	
	private static void testEquals() {
		System.out.println("\n\nTesting the equals method.");
		testEquals("Case 1: J4W2Y9 compare to J4W2Y9", "J4W2Y9", "J4W2Y9", true);
		testEquals("Case 2: J4W2Y9 compare to H4W2Y9", "J4W2Y9", "H4W2Y9", false);
	}
	
	private static void testEquals(String testCase, String code1, String code2, boolean expectedResult) {
		System.out.println("   " + testCase);
		
		PostalCode potalCode1 = new PostalCode(code1);
		PostalCode potalCode2 = new PostalCode(code2);
		
		System.out.print("\tThe Name instance was created: " + potalCode1);
		System.out.print("\tThe Name instance was created: " + potalCode2);
		
		if (potalCode1.equals(potalCode2))
			if (expectedResult == false)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		if (!potalCode1.equals(potalCode2))
			if (expectedResult == true)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		
		System.out.print("\tReturn: " + potalCode1.equals(potalCode2));
		
		System.out.println("\n");
	}
	
	private static void testHashCode() {
		System.out.println("\n\nTesting hashCode method.");
		testHashCode("Case 1: J4W2Y9 compare to J4W2Y9", "J4W2Y9", "J4W2Y9", true);
		testHashCode("Case 2: J4W2Y9 compare to H4W2Y9", "J4W2Y9", "H4W2Y9", false);
	}
	
	private static void testHashCode(String testCase, String code1, String code2, boolean expectedResult) {
		System.out.println("   " + testCase);
		
		PostalCode potalCode1 = new PostalCode(code1);
		PostalCode potalCode2 = new PostalCode(code2);
		
		System.out.print("\tThe Name instance was created: " + potalCode1);
		System.out.print("\tReturn: " + potalCode1.hashCode());
		System.out.print("\tThe Name instance was created: " + potalCode2);
		System.out.print("\tReturn: " + potalCode2.hashCode());

		
		if (potalCode1.hashCode() == potalCode2.hashCode())
			if (expectedResult == false)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		if (potalCode1.hashCode() != potalCode2.hashCode())
			if (expectedResult == true)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		
		System.out.println("\n");
	}
	

	
}
