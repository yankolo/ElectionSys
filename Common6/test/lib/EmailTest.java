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
		testGetAddress();
		testGetUserId();
		testGetHost();
		testEquals();
		testToString();
		testCompareTo();

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
	
	private static void testGetAddress() {
		System.out.println("\n\nTesting the getAddress method.");
		testGetAddress("Case 1: getting the following email (moe@gmail.com)", "moe@gmail.com", "moe@gmail.com");
		testGetAddress("Case 2: getting the following email with leading/trailing spaces ( moe@gmail.com )", " moe@gmail.com ", "moe@gmail.com");
		
	}
	
	private static void testGetAddress(String testCase, String address, String expectedAddress) {
		System.out.println("   " + testCase);
		Email emailObj = new Email (address);
		System.out.println("\tThe Email instance was created: " + emailObj);
		System.out.println("\tThe getAddress returns: " + emailObj.getAddress());

		if (!emailObj.getAddress().equals(expectedAddress))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println();
	}
	
	private static void testGetUserId() {
		System.out.println("\n\nTesting the getUserId method.");
		testGetUserId("Case 1: Calling getUserId on this email (moe@gmail) should return (moe)", "moe@gmail", "moe");
		testGetUserId("Case 2: Calling getUserId on this email with leading/trailing spaces ( mOe3@gmail2 )", "mOe3@gmail2", "mOe3");
	}
	
	private static void testGetUserId(String testCase, String address, String expectedUserId) {
		System.out.println("   " + testCase);
		Email emailObj = new Email (address);
		System.out.println("\tThe Email instance was created: " + emailObj);
		System.out.println("\tThe getUserId returns: " + emailObj.getUserId());

		if (!emailObj.getUserId().equals(expectedUserId))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println();
	}
	
	private static void testGetHost() {
		System.out.println("\n\nTesting the getHost method.");
		testGetHost("Case 1: Calling getHost on this email (sam_my@m-e) should return (m-e)", "sam_my@m-e", "m-e");
		testGetHost("Case 2: Calling getHost on this email with 2 segments (nikita@yanik.ca) should return (yanik.ca)", "nikita@yanik.ca", "yanik.ca");
		testGetHost("Case 3: Calling getHost on this email with leading/trailing spaces ( miss@Jaya ) should return (Jaya)", " miss@Jaya ", "Jaya");
	}
	
	private static void testGetHost(String testCase, String address, String expectedHost) {
		System.out.println("   " + testCase);
		Email emailObj = new Email (address);
		System.out.println("\tThe Email instance was created: " + emailObj);
		System.out.println("\tThe getHost returns: " + emailObj.getHost());

		if (!emailObj.getHost().equals(expectedHost))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println();
	}
	
	private static void testEquals() {
		System.out.println("\n\nTesting the equals method.");
		Email emailObj1 = new Email("moe@dawson");
		Email emailObj2 = new Email("Sammy@Mtl");
		Email emailObj3 = new Email("yanik@com");
		Address address = new Address("3040", "Sherbrook", "Westmount");
		
		testEquals("Case 1: Testing equality between 2 email objects (moe@dawson) and (moe@dawson) should return true", "moe@dawson", emailObj1, true);
		testEquals("Case 2: Testing equality between 2 email objects (sammy@mtl) and (Sammy@Mtl) should return true", "sammy@mtl", emailObj2, true);
		testEquals("Case 3: Testing equality between 2 diffrent email objects (nikita@ca) and (yanik@com) should return false", "nikita@ca", emailObj3, false);
		testEquals("Case 4: Testing equality between an email object (Iam@Home) and a null object should return false", "Iam@Home", null, false);
		testEquals("Case 5: Testing equality between an email object (callMe@514) and an Address object should return false", "callMe@514", address, false);
	}
	
	private static void testEquals(String testCase, String address, Object obj, boolean expectValid) {
		System.out.println("   " + testCase);
		Email emailObj = new Email (address);
		System.out.println("\tThe Email instance was created: " + emailObj);
		System.out.println("\tThe equals method returns: " + emailObj.equals(obj));
		
		if (emailObj.equals(obj) != expectValid)
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println();
	}
	
	private static void testToString() {
		System.out.println("\n\nTesting the toString method.");
		testToString("Case 1: Calling toString on (da-ws_on@qc) should return (da-ws_on@qc)", "da-ws_on@qc", "da-ws_on@qc");
		testToString("Case 2: Calling toString on an email with leading/trailing spaces ( pc@local ) should return (pc@local)", " pc@local ", "pc@local");
	}
	
	private static void testToString(String testCase, String address, String expectedAddress) {
		System.out.println("   " + testCase);
		Email emailObj = new Email (address);
		System.out.println("\tThe Email instance was created: " + emailObj);
		System.out.println("\tThe toString method returns: " + emailObj.toString());

		if (!emailObj.toString().equals(expectedAddress))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println();
	}
	
	private static void testCompareTo() {
		System.out.println("\n\nTesting the compareTo method.");
		Email emailObj1 = null;
		Email emailObj2 = new Email("dell@xps");
		Email emailObj3 = new Email("hp@xps");
		Email emailObj4 = new Email("M0hamed@Sammy");
		Email emailObj5 = new Email("Apple@zx");
		Email emailObj6 = new Email("Lg@ab");
		
		testCompareTo("Case 1: Comparing (dell@Inspiron) with a null Email object should return an exception", "dell@inspiron", emailObj1, 0);
		testCompareTo("Case 2: Comparing (hp@xps) with (dell@xps) should return 1", "hp@xps", emailObj2, 1);
		testCompareTo("Case 3: Comparing (dell@xps) with (hp@xps) should return -1", "dell@xps", emailObj3, -1);
		testCompareTo("Case 4: Comparing (M0hamed@Sammy) with (M0hamed@Sammy) should return 0", "M0hamed@Sammy", emailObj4, 0);
		testCompareTo("Case 5: Comparing (Lg@ab) with (Apple@zx) should return a negative number", "Lg@ab", emailObj5, -25);
		testCompareTo("Case 6: Comparing (Apple@zx) with (Lg@ab) should return a positive number", "Apple@zx", emailObj6, 25);
	}
	
	private static void testCompareTo(String testCase, String address, Email object, int expectValid) {
		System.out.println("   " + testCase);
		Email emailObj = new Email (address);
		System.out.println("\tThe Email instance was created: " + emailObj);
		
		try {
			System.out.println("\tThe compareTo method returns: " + emailObj.compareTo(object));
			if (emailObj.compareTo(object) != expectValid) {
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
			}
		
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
			
		}
		System.out.println();
	}


}
