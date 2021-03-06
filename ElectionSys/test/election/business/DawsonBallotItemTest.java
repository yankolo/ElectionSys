/**
 * 
 */
package election.business;

import election.business.interfaces.BallotItem;
import lib.Address;
import lib.Email;

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
		testGetMaxValue();
		testGetValue();
		testSetValue();
		testEquals();
		testToString();
		testHashCode();

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
	
	private static void testGetMaxValue() {
		System.out.println("\n\nTesting the getMaxValue method.");
		testGetMaxValue("Case 1: Calling getMaxValue on this DawsonBallotItem (\"Mohamed\",\"18\") should return (18)",
				"Mohamed", 18, 18);
	}

	private static void testGetMaxValue(String testCase, String choice, int maxValue, int expectedMaxValue) {
		System.out.println("   " + testCase);
		DawsonBallotItem item = new DawsonBallotItem(choice, maxValue);
		System.out.println("\tThe Email instance was created: " + item);
		System.out.println("\tThe getChoice method returns: " + item.getMaxValue());

		if (item.getMaxValue() != expectedMaxValue)
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println();
	}
	
	private static void testGetValue() {
		System.out.println("\n\nTesting the getValue method.");
		testGetValue("Case 1: Calling getValue on a DawsonBallotItem (Mohamed,18) in which (value=10)",
				"Mohamed", 18, 10, 10);
	}

	private static void testGetValue(String testCase, String choice, int maxValue, int value, int expectedValue) {
		System.out.println("   " + testCase);
		DawsonBallotItem item = new DawsonBallotItem(choice, maxValue);
		item.setValue(value);
		System.out.println("\tThe Email instance was created: " + item);
		System.out.println("\tThe getChoice method returns: " + item.getValue());

		if (item.getValue() != expectedValue) {
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}

		System.out.println();

	}
	
	private static void testSetValue() {
		System.out.println("\n\nTesting the setValue method.");
		testSetValue("Case 1 - Valid: Setting value to 10. DawsonBallotItem (Yanik,20)", "Yanik", 20, 10, 10);
		testSetValue("Case 2 - Invalid: Setting value to -10. DawsonBallotItem (Nikita,21)", "Nikita", 21, -10, 0);
		testSetValue("Case 3 - Invalid: Setting value=30 to a number bigger than maxValue=22. DawsonBallotItem (Sammy,22)", "Sammy", 22, 30,0);
	}
	
	private static void testSetValue(String testCase, String choice, int maxValue, int value, int expectedValue) {
		System.out.println("   " + testCase);
		
		try {
		DawsonBallotItem item = new DawsonBallotItem(choice, maxValue);
		item.setValue(value);
		System.out.println("\tThe Email instance was created: " + item);

		if (item.getValue() != expectedValue) {
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
	
	private static void testEquals() {
		System.out.println("\n\nTesting the equals method.");
		DawsonBallotItem item1 = new DawsonBallotItem("Mohamed", 12);
		DawsonBallotItem item2 = new DawsonBallotItem("sammy", 14);
		DawsonBallotItem item3 = new DawsonBallotItem("Yanik", 14);
		Address address = new Address("3040", "Sherbrook", "Westmount");
		
		testEquals("Case 1: Testing equality between 2 DawsonBallotItem objects (Mohamed,12) and (Mohamed,12) should return true", "Mohamed",12, item1, true);
		testEquals("Case 2: Testing equality between 2 DawsonBallotItem objects (Sammy,13) and (sammy,14) should return true", "Sammy", 13, item2, true);
		testEquals("Case 3: Testing equality between 2 diffrent DawsonBallotItem objects (Nikita,14) and (Yanik,14) should return false", "Nikita",14, item3, false);
		testEquals("Case 4: Testing equality between a DawsonBallotItem object (Jaya,30) and a null object should return false", "Jaya",30, null, false);
		testEquals("Case 5: Testing equality between a DawsonBallotItem object (Davis,40) and an Address object should return false", "Davis",40, address, false);
	}
	
	private static void testEquals(String testCase, String choice, int maxValue, Object obj, boolean expectValid) {
		System.out.println("   " + testCase);
		DawsonBallotItem item = new DawsonBallotItem(choice, maxValue);
		System.out.println("\tThe DawsonBallotItem instance was created: " + item);
		System.out.println("\tThe equals method returns: " + item.equals(obj));
		
		if (item.equals(obj) != expectValid)
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println();
	}
	
	private static void testToString() {
		System.out.println("\n\nTesting the toString method.");
		testToString("Case 1: Calling toString on choice=Dan, maxValue=100, value=80 should return (Dan*80)", "Dan", 100, 80,"Dan*80");
		testToString("Case 2: Calling toString on a choice with leading/trailing spaces. choice=( Moe ), maxValue=50, value=20 should return (Moe*20)", " Moe ",50,20, "Moe*20");
	}
	
	private static void testToString(String testCase, String choice, int maxValue, int value, String expectedBallot) {
		System.out.println("   " + testCase);
		DawsonBallotItem item = new DawsonBallotItem(choice, maxValue);
		item.setValue(value);
		System.out.println("\tThe DawsonBallotItem instance was created: " + item);
		System.out.println("\tThe toString method returns: " + item.toString());

		if (!item.toString().equals(expectedBallot))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println();
	}

	private static void testHashCode() {
		testHashCode("Case 1: choice=Mohamed choice2=mohamed maxValue=10, maxValue2=20", "Mohamed", "mohamed", 10,20,true);
		testHashCode("Case 2: choice=Mohamed choice2=yanik maxValue=10, maxValue2=20", "Mohamed", "yanik", 10,20,false);
		
	}
	
	private static void testHashCode(String testCase, String choice, String choice2, int maxValue, int maxValue2, boolean expectedValid) {
		System.out.println("   " + testCase);
		DawsonBallotItem item1 = new DawsonBallotItem(choice, maxValue);
		System.out.println("\tThe DawsonBallotItem instance1 was created: " + item1);
		System.out.println("\tThe hashCode is: " + item1.hashCode());
		System.out.println();
		DawsonBallotItem item2 = new DawsonBallotItem(choice2, maxValue2);
		System.out.println("\tThe DawsonBallotItem instance2 was created: " + item2);
		System.out.println("\tThe hashCode is: " + item2.hashCode());
		
		if(item1.hashCode() == item2.hashCode()) {
			if(expectedValid == false) {
				System.out.println("  Error! Expected Invalid. === FAILED TEST ===");
			}
		}
		
		if(item1.hashCode() != item2.hashCode()) {
			if(expectedValid == true) {
				System.out.println("  Error! Expected Invalid. === FAILED TEST ===");
			}
		}
		System.out.println();
	}

}
