/**
 * 
 */
package election.business;

import java.time.DateTimeException;

import election.business.interfaces.Ballot;
import election.business.interfaces.Tally;
import election.business.interfaces.Voter;
import lib.Address;


/**
 * @author katsuragi
 *
 */
public class DawsonElectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testTheTweelveParameterConstructor();
		compareToTest();
		testGetElectionType();
		testGetElectionChoices();
	}

	private static void testTheTweelveParameterConstructor() {
		String s1 = "Brandon";
		String s2 = "Bob";
		String s3 = "Jordy";
		String single = "single";
		String[] s4 = null;
		StubTally st = new StubTally();
		StubTally st_Null = null;
		testTheTweelveParameterConstructor(
				"Case 1 - Enter All valid values through the constructor using the following (Dawson Election , the Type is single , start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : true , String...items)",
				"Dawson Election", single, 2017, 10, 20, 2018, 02, 04, null, null, st, true, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 2 - Enter a invalid value for the name (--- name null referenced string ---)through the constructor using the following (null , the Type is single , start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				null, single, 2017, 10, 20, 2018, 02, 04, null, null, st, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 3 - Enter a invalid value for the name (---  name empty string ---) through the constructor using the following ( empty string , the Type is single , start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				"", single, 2017, 10, 20, 2018, 02, 04, null, null, st, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 4 - Enter a invalid value for the type (---  type null referenced ---) through the constructor using the following (Dawson Election , the Type is null , start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				"DawsonElection", null, 2017, 10, 20, 2018, 02, 04, null, null, st, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 5 - Enter a invalid value for the type (---  type empty string ---) through the constructor using the following (Dawson Election , the Type is empty string , start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				"DawsonElection", "", 2017, 10, 20, 2018, 02, 04, null, null, st, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 6 - Enter a invalid value for the type (--- a Election type that does not exist ---) through the constructor using the following (Dawson Election , the Type wow, start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				"DawsonElection", "wow", 2017, 10, 20, 2018, 02, 04, null, null, st, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 7 - Enter a invalid value for the for the number of months for the start date  ( 42 ) through the constructor using the following (Dawson Election , the Type is single, start date: 2017- 42-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				"DawsonElection", single, 2017, 42, 20, 2018, 02, 04, null, null, st, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 8 - Enter a invalid value for the for the number of days in a month for the start date  ( 35 days in a month ) through the constructor using the following (Dawson Election , the Type is single, start date: 2017- 10-35 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				"DawsonElection", single, 2017, 10, 35, 2018, 02, 04, null, null, st, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 9 - Enter a invalid value for the for the number of months for the end date  ( 14 ) through the constructor using the following (Dawson Election , the Type is single, start date: 2017- 10-20 , end date: 2018-14-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				"DawsonElection", single, 2017, 42, 20, 2018, 14, 04, null, null, st, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 10 - Enter a invalid value for the for the number of days in a month for the start date  ( 33 ) through the constructor using the following (Dawson Election , the Type is single, start date: 2017- 10-20 , end date: 2018-02-33, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				"DawsonElection", single, 2017, 42, 20, 2018, 02, 33, null, null, st, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 11 - Enter a invalid end date , the end date is actually smaller than the start date  ( start: 2017-02-10 ---- end date: 2016-02-20 ) through the constructor using the following (Dawson Election , the Type is single, start date: 2017- 10-20 , end date: 2016-02-20, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				"DawsonElection", single, 2017, 10, 20, 2016, 02, 20, null, null, st, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 12 - Enter a invalid end date , the end date is actually equal to the start date  ( start: 2017-02-10 ---- end date: 2017-02-10 ) through the constructor using the following (Dawson Election , the Type is single, start date: 2017- 10-20 , end date: 2017-10-20, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				"DawsonElection", single, 2017, 10, 20, 2017, 10, 20, null, null, st, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 13 - Enter All valid values expect for the Tally ( --- null referenced tally -- ) through the constructor using the following (Dawson Election , the Type is single , start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally is null , expected Value : false , String...items)",
				"Dawson Election", single, 2017, 10, 20, 2018, 02, 04, null, null, st_Null, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 14 - Enter invalid value for the items ( --- null referenced items --- ) through the constructor using the following (Dawson Election , the Type is single , start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items == null)",
				"Dawson Election", single, 2017, 10, 20, 2018, 02, 04, null, null, st, false, s4);
		testTheTweelveParameterConstructor(
				"Case 15 - Enter invalid value for the items ( --- only one value --- ) through the constructor using the following (Dawson Election , the Type is single , start date: 2017- 10-20 , end date: 2018-02-24, startRange = null , endRange = null  , Tally = StubBallot , expected Value : false , s1 = BRANDON, ",
				"Dawson Election", single, 2017, 10, 20, 2018, 02, 04, null, null, st, false, s1);
		testTheTweelveParameterConstructor(
				"Case 16 - Enter invalid value for the startPostalRange ( --- empty string --- ) through the constructor using the following (Dawson Election , the Type is single , start date: 2017- 10-20 , end date: 2018-02-24, startRange = empty string , endRange = null  , Tally = StubBallot , expected Value : false , s1 ,s2 ,s3 ",
				"Dawson Election", single, 2017, 10, 20, 2018, 02, 04, "", null, st, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 17 - Enter invalid value for the endPostalRange ( --- empty string --- ) through the constructor using the following (Dawson Election , the Type is single , start date: 2017- 10-20 , end date: 2018-02-24, startRange = null , endRange = empty String  , Tally = StubBallot , expected Value : false , s1 ,s2 ,s3 ",
				"Dawson Election", single, 2017, 10, 20, 2018, 02, 04, null, "", st, false, s1, s2, s3);

	}

	private static void testTheTweelveParameterConstructor(String testCase, String name, String type, int startYear,
			int startMonth, int startDay, int endYear, int endMonth, int endDay, String startRange, String endRange,
			Tally tally, boolean expectValid, String... items) {

		System.out.println("   " + testCase);
		try {
			DawsonElection d1 = new DawsonElection(name, type, startYear, startMonth, startDay, endYear, endMonth,
					endDay, startRange, endRange, tally, items);
			System.out.println();
			System.out.println(d1.toString());
			if (!expectValid)
				System.out.print(
						"  Error! Expected Invalid. ==== FAILED TEST ==== SHOULD BE ABLE TO CREATE A DAWSON ELECTION OBJECT");

			System.out.println("\n");
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			if (expectValid) {
				System.out.println(" Error! Expected Valid. ====== FAILED TEST =====");
			}
		} catch (DateTimeException dte) {
			System.out.println("\t" + dte.getMessage());
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

	private static void compareToTest() {
		StubTally st = new StubTally();
		String s1 = "Dawson";
		String s2 = "hello world";
		String s3 = "zebra";
		DawsonElection de1 = new DawsonElection(s1, "single", 1997, 02, 25, 1998, 05, 23, null, null, st, s2, s3);
		System.out.println("\t------------------------------------------");
		compareToTest("Case1 - valid DawsonElection Object - the name of the election of  ", de1, true);
		de1 = new DawsonElection(s2, "single", 1997, 02, 25, 1998, 05, 23, null, null, st, s1, s3);
		compareToTest("Case2 - valid DawsonElection Object - the name of the election   is hello world", de1, true);
		de1 = new DawsonElection(s3, "single", 1997, 02, 25, 1998, 05, 23, null, null, st, s1, s2);
		compareToTest("Case 3 - valid DawsonElection Object - the name of the election is zebra ", de1, true);
		de1 = null;
		compareToTest("Case 4 - invalid DawsonElection Object - the dawson election object is null", de1, false);
	}

	private static void compareToTest(String testCase, DawsonElection test, boolean expectValue) {
		System.out.println("   " + testCase);
		String s1 = "Brandon";
		String s2 = "Bob";
		String s3 = "Jordy";
		StubTally st = new StubTally();
		try {

			DawsonElection d1 = new DawsonElection("Hello World", "single", 2017, 04, 22, 2018, 04, 22, null, null, st,
					s1, s2, s3);
			int compare = d1.compareTo(test);
			System.out.println(
					"The compare method has worked. The compareTo method return the following integer " + compare);
			if (!expectValue)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ==== ");

			System.out.println("\n");
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			if (expectValue) {
				System.out.println(" Error! Expected Valid. ====== FAILED TEST =====");
			}
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
			if (expectValue) {
				System.out.println("Expected Valid");
			}

			System.out.println("\n");

		}

	}

	private static void testGetElectionType() {
		System.out.println("\t------------------------------------------");
		testGetElectionType("Case 1 - Enter a valid Election type -- single election type", "single", true);
		testGetElectionType("Case 2 - Enter a valid Election type -- ranked election type", "ranked", true);
		// did not check if the type string was an empty string or a null referenced
		// because it is tested in the constructor

	}

	private static void testGetElectionType(String testCase, String type, boolean expectValid) {

		System.out.println("   " + testCase);
		String s1 = "Brandon";
		String s2 = "Bob";
		String s3 = "Jordy";
		StubTally st = new StubTally();
		try {

			DawsonElection d1 = new DawsonElection("Hello World", type, 2017, 04, 22, 2018, 04, 22, null, null, st, s1,
					s2, s3);
			System.out.println("This type passed to this test method is a valid Election type: "
					+ d1.getElectionType().toString());
			if (!expectValid)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ==== ");

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

			System.out.println("\n");

		}

	}
	private static void testGetElectionChoices() {
		System.out.println("\t------------------------------------------");
		String s1 = "Brandon";
		String s2 = "Bob";
		String s3 = "Jordy";
		testGetElectionChoices("Case 1 - Return a 3 different choices - Brandon, Bob, Jordy", true, s1, s2, s3);
		s1 = "Sammy";
		s2 = "Hello world";
		s3 = "Mohammed";
		testGetElectionChoices("Case 2 - Return a 3 different choices - Sammy, Hello World, Mohammed", true, s1, s2,
				s3);

	}

	private static void testGetElectionChoices(String testCase, boolean expectValid, String... items) {

		System.out.println("   " + testCase);
		StubTally st = new StubTally();
		try {

			DawsonElection d1 = new DawsonElection("Hello World", "single", 2017, 04, 22, 2018, 04, 22, null, null, st,
					items);
			System.out.println("This array passed to this test method is a valid String array: " + d1.toString());
			if (!expectValid)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ==== ");

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

			System.out.println("\n");

		}

	}

}
