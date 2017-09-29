/**
 * 
 */
package lib;

/**
 * @author Mohamed Hamza
 *
 */
public class EmailTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testTheConstructor();

	}
	
	private static void testTheConstructor() {
		System.out.println("\nTesting the constructor.");
		testTheConstructor("Case 1 - Valid email (moe@gmail.com)", "moe@gmail.com", true);
		testTheConstructor("Case 2 - Valid email with leading/trailing spaces ( moe@gmail.com )", " moe@gmail.com ",
				true);
		testTheConstructor("Case 3 - Invalid email. Email address with 2 '@' (moe@@gmail.com)", "moe@@gmail.com",
				false);
		testTheConstructor("Case 4 - Valid email. UserId with upper case letters (MOE@gmail.com)", "MOE@gmail.com",
				true);
		testTheConstructor("Case 5 - Valid email. UserId with only numbers (123@gmail.com)", "123@gmail.com", true);
		testTheConstructor("Case 6 - Valid email. UserId with only hyphen (---@gmail.com)", "---@gmail.com", true);
		testTheConstructor("Case 7 - Valid email. UserId with only underscore (___@gmail.com)", "___@gmail.com", true);
		testTheConstructor("Case 8 - Invalid email. UserId starts with a dot (.moe@gmail.com)", ".moe@gmail.com",
				false);
		testTheConstructor("Case 9 - Invalid email. UserId ends with a dot (moe.@gmail.com)", "moe.@gmail.com", false);
		testTheConstructor("Case 10 - Invalid email. UserId with 2 consecutive dots (mo..e@gmail.com)",
				"mo..e@gmail.com", false);
		testTheConstructor("Case 11 - Invalid email. Empty UserId (@gmail.com)", "@gmail.com", false);
		testTheConstructor("Case 12 - Invalid email. UserId is just spaces (  @gmail.com)", "  @gmail.com", false);
		testTheConstructor(
				"Case 13 - Invalid email. The length of UserId is greater than 32 characters \n   (qwertyuiopasdfghjklzxcvbnmqwertyu@gmail.com)",
				"qwertyuiopasdfghjklzxcvbnmqwertyu@gmail.com", false);
		testTheConstructor("Case 14 - Invalid email. UserId contain spaces (m oe@gmail.com)", "m oe@gmial.com", false);
		testTheConstructor("Case 15 - Valid email. Host name with a single segment (moe@gmail)", "moe@gmail", true);
		testTheConstructor("Case 16 - Valid email. Host name with 3 segments (moe@dawsoncollege.qc.ca)",
				"moe@dawsoncollege.qc.ca", true);
		testTheConstructor("Case 17 - Valid email. Host name with upper case letters (moe@GMAIL)", "moe@GMAIL", true);
		testTheConstructor("Case 18 - Valid email. Host name with only digits (moe@123)", "moe@123", true);
		testTheConstructor("Case 19 - Valid email. Host name with hyphen (moe@gm-ail)", "moe@gm-ail", true);
		testTheConstructor("Case 20 - Valid email. Host name contains valid characters (moe@gm-AI2L)", "moe@gm-AI2L", true);
		testTheConstructor("Case 21 - Invalid email. Host name contain spaces (moe@gm ail)", "moe@gm ail", false);
		testTheConstructor("Case 22 - Invalid email. Host name starts with a hyphen (moe@-gmail)", "moe@-gmail", false);
		testTheConstructor("Case 23 - Invalid email. Host name ends with a hyphen (moe@gmail-)", "moe@gmail-", false);
		testTheConstructor("Case 24 - Invalid email. Empty host name (moe@)", "moe@", false);
		testTheConstructor(
				"Case 25 - Invalid email. The length of a single segment of a host name is greater than 32 characters \n   (moe@qwertyuiopasdfghjklzxcvbnmqwertyu)",
				"moe@qwertyuiopasdfghjklzxcvbnmqwertyu", false);
		testTheConstructor("Case 26 - Invalid email. Null email (null)", null, false);
		

	}

	private static void testTheConstructor(String testCase, String address, boolean expectValid) {

		System.out.println("   " + testCase);
		try {
			Email emailObj = new Email(address);
			System.out.println("\tThe Email instance was created: " + emailObj);

			if (!expectValid)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

			// System.out.println("\n");
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
		System.out.println();
		}

}
