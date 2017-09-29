/**
 * 
 */
package election.business;

import election.business.interfaces.BallotItem;

/**
 * @author moham
 *
 */
public class DawsonBallotItemTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testTheTwoParametersConstructor();
		testTheCopyConstructor();
		testCompareTo();
		testGetChoice();

	}
	
	private static void testTheTwoParametersConstructor() {
		System.out.println("\nTesting the 2 parameters constructor.");
		testTheTwoParametersConstructor("Case 1 - Valid data (\"Mohamed\", \"10\")", "Mohamed", 10, true);
		testTheTwoParametersConstructor(
				"Case 2 - Valid data. Choice has leading/trailing spaces (\" Mohamed \", \"20\")", " Mohamed ", 20,
				true);
		testTheTwoParametersConstructor("Case 3 - Invalid data. Choice is null (null, \"15\")", null, 15, false);
		testTheTwoParametersConstructor("Case 4 - Invalid data. Choice is empty (\"  \" , \"18\")", "  ", 18, false);
		testTheTwoParametersConstructor("Case 5 - Invalid data. MaxValue < 1 (\"Moe\" , \"-5\")", "Moe", -5, false);
	}

	private static void testTheTwoParametersConstructor(String testCase, String choice, int maxvalue,
			boolean expectValid) {
		System.out.println("   " + testCase);
		try {
			DawsonBallotItem item = new DawsonBallotItem(choice, maxvalue);
			System.out.println("\tThe DawsonBallotItem instance was created: " + item);
			System.out.println("\tThe maxValue = " + item.getMaxValue());

			if (!expectValid)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

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
	
	private static void testTheCopyConstructor() {
		System.out.println("\n\nTesting the copy constructor.");
		testTheCopyConstructor("Case 1 - Valid data (\"Mohamed\", \"10\")", "Mohamed", 10, true);
		testTheCopyConstructor("Case 2 - Valid data. Choice has leading/trailing spaces (\" Mohamed \", \"20\")",
				" Mohamed ", 20, true);
		testTheCopyConstructor("Case 3 - Invalid data. Choice is null (null, \"15\")", null, 15, false);
		testTheCopyConstructor("Case 4 - Invalid data. Choice is empty (\"  \" , \"18\")", "  ", 18, false);
		testTheCopyConstructor("Case 5 - Invalid data. MaxValue < 1 (\"Moe\" , \"-5\")", "Moe", -5, false);
	}

	private static void testTheCopyConstructor(String testCase, String choice, int maxValue, boolean expectValid) {
		System.out.println("   " + testCase);

		try {
			DawsonBallotItem item = new DawsonBallotItem(choice, maxValue);
			DawsonBallotItem copy = new DawsonBallotItem(item);
			System.out.println("\tThe original DawsonBallotItem instance was created: " + item + " and maxValue = "
					+ item.getMaxValue());
			System.out.println("\tThe copy DawsonBallotItem instance was created: " + copy + " and maxValue = "
					+ copy.getMaxValue());

			if (!expectValid)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

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
	
	private static void testCompareTo() {
		System.out.println("\n\nTesting the compareTo method.");
		DawsonBallotItem item1 = new DawsonBallotItem("Alpha", 18);
		DawsonBallotItem item2 = new DawsonBallotItem("Mohamed", 18);

		testCompareTo("Case 1: Comparing (Mohamed) with (Alpha) should return a positive number", "Mohamed", 20, item1,
				12);
		testCompareTo("Case 2: Comparing (MOHAMED) with (Alpha) should return a positive number", "MOHAMED", 20, item1,
				12);
		testCompareTo("Case 3: Comparing (Alpha) with (Mohamed) should return a negative number", "Alpha", 20, item2,
				-12);
		testCompareTo("Case 4: Comparing (ALPHA) with (Mohamed) should return a negative number", "ALPHA", 20, item2,
				-12);
		testCompareTo("Case 5: Comparing (Mohamed) with (Mohamed) should return 0", "Mohamed", 20, item2, 0);
		testCompareTo("Case 6: Comparing (MOHAMED) with (Mohamed) should return 0", "MOHAMED", 20, item2, 0);
	}

	private static void testCompareTo(String testCase, String choice, int maxValue, BallotItem item, int expectValid) {
		System.out.println("   " + testCase);

		try {
			DawsonBallotItem object = new DawsonBallotItem(choice, maxValue);
			System.out.println("\tThe Email instance was created: " + object);
			System.out.println("\tThe compareTo method returns: " + object.compareTo(item));
			if (object.compareTo(item) != expectValid) {
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
	
	private static void testGetChoice() {
		System.out.println("\n\nTesting the getChoice method.");
		testGetChoice("Case 1: Calling getChoice on this DawsonBallotItem (\"Mohamed\",\"18\") should return (Mohamed)",
				"Mohamed", 18, "Mohamed");
		testGetChoice(
				"Case 2: Calling getChoice on this DawsonBallotItem in which choice contain leading/trailing spaces (\" Moe \",\"18\") should return (Mohamed)",
				"Mohamed", 18, "Mohamed");
	}

	private static void testGetChoice(String testCase, String choice, int maxValue, String expectedChoice) {
		System.out.println("   " + testCase);
		DawsonBallotItem item = new DawsonBallotItem(choice, maxValue);
		System.out.println("\tThe Email instance was created: " + item);
		System.out.println("\tThe getChoice method returns: " + item.getChoice());

		if (!item.getChoice().equals(expectedChoice))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println();
	}

}
