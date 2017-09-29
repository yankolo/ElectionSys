/**
 * 
 */
package election.business;

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

}
