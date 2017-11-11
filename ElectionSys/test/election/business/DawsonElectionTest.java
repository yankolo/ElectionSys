/**
 * 
 */
package election.business;

import java.time.DateTimeException;

import com.sun.org.apache.bcel.internal.generic.NEW;

import election.business.interfaces.Ballot;
import election.business.interfaces.Election;
import election.business.interfaces.Tally;
import election.business.interfaces.Voter;

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
		testGetEndDate();
		testGetStartDate();
		testGetPostalRangeEnd();
		testGetPostalRangeStart();
		testIsLimitedToPostalRange();
		testGetName();
		testGetTally();
		testSetTally();
		testGetBallot();
		testCastBallot();
		testEquals();
		testHashCode();
		toStringTest();
	}

	private static void testTheTweelveParameterConstructor() {
		String s1 = "Brandon";
		String s2 = "Bob";
		String s3 = "Jordy";
		String single = "single";
		String name = "Dawson Election";
		String[] s4 = null;
		DawsonTally dt = new DawsonTally(3, name);
		DawsonTally dt_Null = null;
		System.out.println("---------- TESTING THE CONSTRUCTOR ----------");
		testTheTweelveParameterConstructor(
				"Case 1 - Enter All valid values through the constructor using the following (Dawson Election , the Type is single , start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : true , String...items)",
				name, single, 2017, 10, 20, 2018, 02, 04, null, null, dt, true, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 2 - Enter a invalid value for the name (--- name null referenced string ---)through the constructor using the following (null , the Type is single , start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				null, single, 2017, 10, 20, 2018, 02, 04, null, null, dt, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 3 - Enter a invalid value for the name (---  name empty string ---) through the constructor using the following ( empty string , the Type is single , start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				"", single, 2017, 10, 20, 2018, 02, 04, null, null, dt, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 4 - Enter a invalid value for the type (---  type null referenced ---) through the constructor using the following (Dawson Election , the Type is null , start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				name, null, 2017, 10, 20, 2018, 02, 04, null, null, dt, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 5 - Enter a invalid value for the type (---  type empty string ---) through the constructor using the following (Dawson Election , the Type is empty string , start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				name, "", 2017, 10, 20, 2018, 02, 04, null, null, dt, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 6 - Enter a invalid value for the type (--- a Election type that does not exist ---) through the constructor using the following (Dawson Election , the Type wow, start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				name, "wow", 2017, 10, 20, 2018, 02, 04, null, null, dt, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 7 - Enter a invalid value for the for the number of months for the start date  ( 42 ) through the constructor using the following (Dawson Election , the Type is single, start date: 2017- 42-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				name, single, 2017, 42, 20, 2018, 02, 04, null, null, dt, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 8 - Enter a invalid value for the for the number of days in a month for the start date  ( 35 days in a month ) through the constructor using the following (Dawson Election , the Type is single, start date: 2017- 10-35 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				name, single, 2017, 10, 35, 2018, 02, 04, null, null, dt, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 9 - Enter a invalid value for the for the number of months for the end date  ( 14 ) through the constructor using the following (Dawson Election , the Type is single, start date: 2017- 10-20 , end date: 2018-14-24, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				name, single, 2017, 42, 20, 2018, 14, 04, null, null, dt, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 10 - Enter a invalid value for the for the number of days in a month for the start date  ( 33 ) through the constructor using the following (Dawson Election , the Type is single, start date: 2017- 10-20 , end date: 2018-02-33, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				name, single, 2017, 42, 20, 2018, 02, 33, null, null, dt, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 11 - Enter a invalid end date , the end date is actually smaller than the start date  ( start: 2017-02-10 ---- end date: 2016-02-20 ) through the constructor using the following (Dawson Election , the Type is single, start date: 2017- 10-20 , end date: 2016-02-20, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				name, single, 2017, 10, 20, 2016, 02, 20, null, null, dt, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 12 - Enter a invalid end date , the end date is actually equal to the start date  ( start: 2017-02-10 ---- end date: 2017-02-10 ) through the constructor using the following (Dawson Election , the Type is single, start date: 2017- 10-20 , end date: 2017-10-20, startRange: , endRange:  , Tally  , expected Value : false , String...items)",
				name, single, 2017, 10, 20, 2017, 10, 20, null, null, dt, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 13 - Enter All valid values expect for the Tally ( --- null referenced tally -- ) through the constructor using the following (Dawson Election , the Type is single , start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally is null , expected Value : false , String...items)",
				name, single, 2017, 10, 20, 2018, 02, 04, null, null, dt_Null, false, s1, s2, s3);
		testTheTweelveParameterConstructor(
				"Case 14 - Enter invalid value for the items ( --- null referenced items --- ) through the constructor using the following (Dawson Election , the Type is single , start date: 2017- 10-20 , end date: 2018-02-24, startRange: , endRange:  , Tally  , expected Value : false , String...items == null)",
				name, single, 2017, 10, 20, 2018, 02, 04, null, null, dt, false, s4);
		testTheTweelveParameterConstructor(
				"Case 15 - Enter invalid value for the items ( --- only one value --- ) through the constructor using the following (Dawson Election , the Type is single , start date: 2017- 10-20 , end date: 2018-02-24, startRange = null , endRange = null  , Tally = StubBallot , expected Value : false , s1 = BRANDON, ",
				name, single, 2017, 10, 20, 2018, 02, 04, null, null, dt, false, s1);

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
		String s1 = "Dawson";
		String s2 = "hello world";
		String s3 = "zebra";
		DawsonTally dt = new DawsonTally(2, s1);
		DawsonTally dt1 = new DawsonTally(2, s2);
		DawsonTally dt2 = new DawsonTally(2, s3);
		DawsonElection de1 = new DawsonElection(s1, "single", 1997, 02, 25, 1998, 05, 23, null, null, dt, s2, s3);
		System.out.println("---------- TESTING THE COMPARE TO METHOD ----------");
		compareToTest("Case1 - valid DawsonElection Object - the name of the election of  ", de1, true);
		de1 = new DawsonElection(s2, "single", 1997, 02, 25, 1998, 05, 23, null, null, dt1, s1, s3);
		compareToTest("Case2 - valid DawsonElection Object - the name of the election   is hello world", de1, true);
		de1 = new DawsonElection(s3, "single", 1997, 02, 25, 1998, 05, 23, null, null, dt2, s1, s2);
		compareToTest("Case 3 - valid DawsonElection Object - the name of the election is zebra ", de1, true);
		de1 = null;
		compareToTest("Case 4 - invalid DawsonElection Object - the dawson election object is null", de1, false);
	}

	private static void compareToTest(String testCase, DawsonElection test, boolean expectValue) {
		System.out.println("   " + testCase);
		String s1 = "Brandon";
		String s2 = "Bob";
		String s3 = "Jordy";

		try {
			DawsonTally dt = new DawsonTally(3, "Hello World");
			DawsonElection d1 = new DawsonElection("Hello World", "single", 2017, 04, 22, 2018, 04, 22, null, null, dt,
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
		System.out.println("---------- TESTING THE GETELECTIONTYPE() ----------");
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
		try {
			DawsonTally dt = new DawsonTally(3, "Hello World");
			DawsonElection d1 = new DawsonElection("Hello World", type, 2017, 04, 22, 2018, 04, 22, null, null, dt, s1,
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
		System.out.println("---------- TESTING THE GETELECTIONCHOICES ----------");
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
		try {
			DawsonTally dt = new DawsonTally(items.length, "Hello World");
			DawsonElection d1 = new DawsonElection("Hello World", "single", 2017, 04, 22, 2018, 04, 22, null, null, dt,
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

	private static void testGetEndDate() {
		System.out.println("---------- TESTING THE GETENDDATE ----------");
		int year = 2017;
		int month = 9;
		int day = 28;
		testGetEndDate("Case 1 - Passed a valid integer numbers to create a date (EndDate localDate object)", year,
				month, day, true);
		year = 1997;
		month = 10;
		day = 04;
		testGetEndDate(
				"Case 2 - Passed a valid Integer numbers to create a date (EndDate localDate Object) but the startDate is bigger than the endDate",
				year, month, day, false);
	}

	private static void testGetEndDate(String testCase, int year, int month, int day, boolean expectValid) {

		System.out.println("   " + testCase);

		try {
			String s1 = "Brandon";
			String s2 = "Bob";
			String s3 = "Jordy";
			DawsonTally dt = new DawsonTally(3, "Hello World");
			DawsonElection d1 = new DawsonElection("Hello World", "single", 2017, 04, 22, year, month, day, null, null,
					dt, s1, s2, s3);
			System.out.println("The integers passed to this test method is a date : " + d1.getEndDate().toString());
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

	private static void testGetStartDate() {
		System.out.println("---------- TESTING THE GETSTARTDATE ----------");
		int year = 1994;
		int month = 11;
		int day = 05;
		testGetStartDate("Case 1 - Passed a valid integer numbers to create a date (StartDate localDate object)", year,
				month, day, true);
		year = 2019;
		month = 06;
		day = 07;
		testGetStartDate(
				"Case 2 - Passed a valid Integer numbers to create a date (StartDate localDate Object) but is Bigger than the endDate",
				year, month, day, false);
	}

	private static void testGetStartDate(String testCase, int year, int month, int day, boolean expectValid) {

		System.out.println("   " + testCase);

		try {
			String s1 = "Brandon";
			String s2 = "Bob";
			String s3 = "Jordy";
			DawsonTally st = new DawsonTally(3, "Hello World");
			DawsonElection d1 = new DawsonElection("Hello World", "single", year, month, day, 2017, 04, 22, null, null,
					st, s1, s2, s3);
			System.out.println("The integers passed to this test method is a date : " + d1.getStartDate().toString());
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

	private static void testGetPostalRangeEnd() {
		System.out.println("---------- TESTING THE GET_POSTALRANGE_END ----------");
		testGetPostalRangeEnd(
				"Case 1 - Pass a null referenced String while the PostalRangeEnd is null referenced and the PostalRangeStart is null as well ",
				null, true);
		testGetPostalRangeEnd(
				"Case 2 - Pass a valid  String while the PostalRangeStart is not null and the PostalRangeEnd is not null",
				"G", true);

	}

	private static void testGetPostalRangeEnd(String testCase, String postalRangeEnd, boolean expectValid) {

		System.out.println("   " + testCase);

		try {
			String s1 = "Brandon";
			String s2 = "Bob";
			String s3 = "Jordy";
			DawsonTally dt = new DawsonTally(3, "Hello World");
			if (postalRangeEnd == null) {
				DawsonElection d1 = new DawsonElection("Hello World", "single", 2016, 12, 2, 2017, 04, 22, null,
						postalRangeEnd, dt, s1, s2, s3);

				System.out.println(
						"Nulls are  valid postal range - means that the election is not limited to postal code ");
			} else {
				DawsonElection d1 = new DawsonElection("Hello World", "single", 2016, 12, 2, 2017, 04, 22, "F",
						postalRangeEnd, dt, s1, s2, s3);
				System.out.println("The string passed to this test method which is " + d1.getPostalRangeEnd()
						+ " is a valid postalRange end ");
			}
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

	private static void testGetPostalRangeStart() {
		System.out.println("---------- TESTING THE GET_POSTAL_RANGE_START ----------");
		testGetPostalRangeStart(
				"Case 1 - Pass a null referenced String while the PostalRangeStart is null referenced and the PostalRangeEnd is null as well ",
				null, true);
		testGetPostalRangeStart(
				"Case 2 - Pass a valid  String while the PostalRangeStart is not null and the PostalRangeEnd is not null",
				"G", true);

	}

	private static void testGetPostalRangeStart(String testCase, String postalRangeStart, boolean expectValid) {

		System.out.println("   " + testCase);

		try {
			String s1 = "Brandon";
			String s2 = "Bob";
			String s3 = "Jordy";
			DawsonTally dt = new DawsonTally(3, "Hello World");
			if (postalRangeStart == null) {
				DawsonElection d1 = new DawsonElection("Hello World", "single", 2016, 12, 2, 2017, 04, 22, null,
						postalRangeStart, dt, s1, s2, s3);
				System.out.println(
						"Nulls are  valid postal range - means that the election is not limited to postal code ");
			} else {
				DawsonElection d1 = new DawsonElection("Hello World", "single", 2016, 12, 2, 2017, 04, 22, "F",
						postalRangeStart, dt, s1, s2, s3);
				System.out.println("The string passed to this test method which is " + postalRangeStart
						+ " is a valid postalRange start ");
			}
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

	private static void testIsLimitedToPostalRange() {
		System.out.println("---------- TESTING THE IS_LIMITED_TO_POSTAL_RANGE ----------");
		String s1 = "H";
		String s2 = "G";
		testIsLimitedToPostalRange("Case 1 - both startRange and the endRange are null", null, null, true);
		testIsLimitedToPostalRange("Case 2 - only startRange is null referenced and the endRange contains a string",
				null, s1, true);
		testIsLimitedToPostalRange("Case 3 - startRange is a valid string and the endRange is null", s1, null, true);
		testIsLimitedToPostalRange("Case 4 - both startRange and the endRange are valid Strings", s2, s1, true);

	}

	private static void testIsLimitedToPostalRange(String testCase, String startRange, String endRange,
			boolean expectValid) {

		System.out.println("   " + testCase);

		try {
			String s1 = "Brandon";
			String s2 = "Bob";
			String s3 = "Jordy";
			DawsonTally st = new DawsonTally(3, "Hello World");
			DawsonElection d1 = new DawsonElection("Hello World", "single", 2016, 12, 2, 2017, 04, 22, startRange,
					endRange, st, s1, s2, s3);
			if (d1.isLimitedToPostalRange()) {
				System.out.println(
						"The two strings passed to this test method tells that the postal ranges is not important for this election ");
			} else {
				System.out.println(
						"The two strings passed to this test method are valid postal range it is Limited to range");
			}
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

	private static void testGetName() {
		System.out.println("---------- TESTING THE GET_NAME  ---------");
		String s1 = "USA ELECTION";
		String s2 = "CANADIAN ELECTION";
		testGetName("Case 1 - The name of the election is USA ELECTION - getName should return that name", s1, true);
		testGetName("Case 2 - The name of the election is CANADIAN ELECTION - getName should return that name", s2,
				true);

	}

	private static void testGetName(String testCase, String name, boolean expectValid) {

		System.out.println("   " + testCase);

		try {
			String s1 = "Brandon";
			String s2 = "Bob";
			String s3 = "Jordy";
			DawsonTally dt = new DawsonTally(3, name);
			DawsonElection d1 = new DawsonElection(name, "single", 2016, 12, 2, 2017, 04, 22, null, null, dt, s1, s2,
					s3);
			if (d1.getName().equalsIgnoreCase(name)) {
				System.out.println("The getName returns the correct string which is " + d1.getName());
			} else {
				System.out.println(d1.getName() + " and " + name + " are equal ==== FAILED TEST ====  ");
			}
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

	private static void testGetTally() {
		System.out.println("---------- TESTING THE GET_TALLY ----------");
		DawsonTally tal = new DawsonTally(3, "Hello World");
		testGetTally("Case 1 - Should return the same tally object since we are not deep copying it ", tal, true);
	}

	private static void testGetTally(String testCase, Tally tal, boolean expectValid) {

		System.out.println("   " + testCase);
		try {
			String s1 = "Brandon";
			String s2 = "Bob";
			String s3 = "Jordy";

			DawsonElection d1 = new DawsonElection("Hello World", "single", 2016, 12, 2, 2017, 04, 22, null, null, tal,
					s1, s2, s3);
			if (d1.getTally().equals(tal)) {
				System.out.println("The getTally returns the correct string which is " + d1.getTally());
			} else {
				System.out.println(
						" ==== FAILED TEST ==== GETTALLY SHOULD RETURN SIMILAR THE SAME TALLY OBJECT AS IT WAS PASSED THROUGH THE CONSTRUCTOR ");
			}
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

	private static void testSetTally() {
		System.out.println("---------- TESTING THE SET_METHOD ----------");
		DawsonTally tal = new DawsonTally(3, "Hi");
		testSetTally(
				"Case 1 - Should set the DawsonElection tally to the one passed in the parameter since they have same electionName ",
				tal, true);
		DawsonTally tal1 = new DawsonTally(3, "HelloWorld");
		testSetTally(
				"Case 2 - Should throw exception since the DawsonElection tally election name is different from  the one passed in the parameter of the setTally method ",
				tal1, false);
		testSetTally(
				"Case 3 - Should throw an exception since the tally objec that is passed in the parameter is null referenced ",
				null, false);
		System.out.println();

	}

	private static void testSetTally(String testCase, Tally tal, boolean expectValid) {

		System.out.println("   " + testCase);
		try {
			DawsonTally st = new DawsonTally(3, "Hi");
			String s1 = "Brandon";
			String s2 = "Bob";
			String s3 = "Jordy";

			DawsonElection d1 = new DawsonElection("Hello World", "single", 2016, 12, 2, 2017, 04, 22, null, null, st,
					s1, s2, s3);
			d1.setTally(tal);
			System.out.println(
					"The getTally returns the correct string since the Tally object in the set parameter has the same election name  which is "
							+ d1.getTally().getElectionName());

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

	private static void testGetBallot() {
		System.out.println("---------- TESTING THE GET_BALLOT ----------");
		DawsonVoter dv = new DawsonVoter("Sammy", "Chaouki", "sammychaouki@gmail.com", "J4Y2N3");
		DawsonVoter dv2 = new DawsonVoter("Mohamed", "Hamza", "mohamza@gmail.com", "J4Y2N4");
		DawsonVoter dv3 = new DawsonVoter("Yanik", "Kolomatski", "yanikolo@gmail.com", "J4Y2N5");
		DawsonVoter dv4 = new DawsonVoter("Nikita", "Slavin", "nikitaSlavin@gmail.com", "J4Y2N6");
		DawsonVoter dv5 = new DawsonVoter("Bob", "Bobson", "bob@gmail.com", "J4Y2N9");
		DawsonVoter dvNull = null;
		String s1 = "Brandon";
		String s2 = "Bob";
		String s3 = "Jordy";
		DawsonTally dt = new DawsonTally(3, "Hello World");
		DawsonElection d1 = new DawsonElection("Hello World", "single", 2016, 12, 2, 2018, 04, 22, "J4Y2N1", "J4Y2N8",
				dt, s1, s2, s3);
		testGetBallot("Case 1 -- pass a voter that has yet to request a ballot", dv, d1, true);
		System.out.println("\tThe following voters have requested a ballot: \n"
				+ "\t- Mohamed Hamza -- mohamza@gmail.com -- J4Y2N4 \n"
				+ "\t- Yanik Kolomatski -- yanikolo@gmail.com -- J4Y2N5 \n"
				+ "\t- Nikita Slavin -- nikitaSlavin@gmail.com -- J4Y2N6\n");
		Ballot sb1 = d1.getBallot(dv2);
		Ballot sb2 = d1.getBallot(dv3);
		Ballot sb3 = d1.getBallot(dv4);
		testGetBallot("Case 2 -- pass a null referenced voter", dvNull, d1, false);
		System.out.println("\n \t--- The Following voter has casted a ballot: " + dv2.toString() + "\n");
		d1.castBallot(sb1, dv2);
		testGetBallot("Case 3 -- pass a voter that has already to request a ballot and has casted his ballot", dv2, d1,
				false);
		System.out.println();
		testGetBallot("Case 4 -- pass a voter that is not eligible for the election", dv5, d1, false);
		System.out.println();

	}

	private static void testGetBallot(String testCase, Voter v, DawsonElection d1, boolean expectValid) {
		System.out.println("   " + testCase);
		try {

			Ballot sb = d1.getBallot(v);
			if (!expectValid)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ==== ");
			System.out.println("\tPassed -- Test --- the following Voter : " + v.toString()
					+ " has successfuly requested a ballot for this election : " + d1.getName());
			System.out.println("\n");
		} catch (InvalidVoterException ive) {
			System.out.println("\t" + ive.getMessage());
			if (expectValid) {
				System.out.println(" Error! Expected Valid. ====== FAILED TEST =====");
			}
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

	private static void testCastBallot() {

		System.out.println("---------- TESTING THE CAST_BALLOT ----------");
		DawsonBallotItem dbe = new DawsonBallotItem("Nikita", 20);
		dbe.setValue(2);
		DawsonBallotItem dbe1 = new DawsonBallotItem("Yanik", 21);
		DawsonBallotItem dbe2 = new DawsonBallotItem("Mohamed", 22);
		DawsonBallotItem dbe3 = new DawsonBallotItem("Sammy", 23);
		DawsonBallotItem[] dbeArray = new DawsonBallotItem[4];
		dbeArray[0] = dbe;
		dbeArray[1] = dbe1;
		dbeArray[2] = dbe2;
		dbeArray[3] = dbe3;
		String s1 = "Nikita";
		String s2 = "Yanik";
		String s3 = "Mohammed";
		String s4 = "Sammy";
		DawsonTally st = new DawsonTally(4, "Hello World");
		DawsonVoter dv = new DawsonVoter("Ahmed", "Mo", "moe@gmail.com", "J4W2Y8");
		DawsonVoter dv1 = new DawsonVoter("Momo", "Hello", "m@gmail.com", "J4W2Y9");
		DawsonVoter dvNull = null;
		DawsonElection de = new DawsonElection("Hello World", "single", 2016, 12, 2, 2017, 12, 30, "J4W2Y3", "J4W2Y9",
				st, s1, s2, s3, s4);
		de.getBallot(dv);
		Ballot sb = DawsonElectionFactory.DAWSON_ELECTION.getBallot(dbeArray, de.getElectionType(), de);
		Ballot sbNull = null;
		System.out.println("---------- TESTING USING A SINGLE BALLOT SYSTEM ----------");
		testCastBallot("Case 1 - Pass a valid Ballot object and voter to a Single Ranked Election", sb, de, dv, true);
		de.getBallot(dv1);
		testCastBallot("Case 2 - Pass a valid Ballot object and voter to a Single Ranked Election", sb, de, dv1, true);
		testCastBallot("Case 3 - Pass a null voter to the cast ballot method.", sb, de, dvNull, false);
		DawsonVoter dvNotEligible = new DawsonVoter("Sam", "Hello", "sammy@gmail.com", "J4W2Y2");
		testCastBallot("Case 4 - Pass a voter that is not eligible for the election to the cast ballot method", sb, de,
				dvNotEligible, false);
		testCastBallot("Case 5 - Pass a null referenced ballot to the cast ballot method", sbNull, de, dv, false);
		DawsonVoter dv2 = new DawsonVoter("Sammy", "Chaouki", "sammychaouki@gmail.com", "J4W2Y4");
		testCastBallot("Case 6 - Pass a voter that has yet to request a ballot to the cast ballot method", sb, de, dv2,
				false);
		testCastBallot("Case 7 - Pass a voter that has already casted his vote to the cast ballot method", sb, de, dv,
				false);
		dbe2.setValue(4);
		de.getBallot(dv2);
		testCastBallot("Case 8 - Pass a ballot that is not filled correctly to the cast ballot method", sb, de, dv2,
				false);
	}

	private static void testCastBallot(String testCase, Ballot sb, Election de, Voter v, boolean expectValid) {
		System.out.println("   " + testCase);
		try {

			de.castBallot(sb, v);
			System.out.println("\tPASSED TEST -- " + " The following  voter: " + v.getName()
					+ " has successfuly casted his ballot");
			if (!expectValid) {
				System.out.println(" Error! Expected InValid. ====== FAILED TEST =====");
			}
			System.out.println("\n");
		} catch (InvalidVoterException ive) {
			System.out.println("\t" + ive.getMessage());
			System.out.println();
			if (expectValid) {
				System.out.println(" Error! Expected Valid. ====== FAILED TEST =====");
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			System.out.println();
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

	private static void testEquals() {
		DawsonTally dt = new DawsonTally(3, "Hello World");
		DawsonTally dt2 = new DawsonTally(3, "Hek");
		String s1 = "Brandon";
		String s2 = "Bob";
		String s3 = "Jordy";
		System.out.println("---------- TESTING THE EQUALS  ----------");

		DawsonElection de = new DawsonElection("Hello World", "single", 2016, 12, 2, 2017, 04, 22, null, null, dt, s1,
				s2, s3);
		DawsonElection de1 = null;
		DawsonElection de2 = new DawsonElection("Hek", "single", 2016, 12, 2, 2017, 04, 22, null, null, dt2, s1, s2,
				s3);
		DawsonElection de3 = de;
		DawsonElection de4 = new DawsonElection("Hello World", "single", 2016, 11, 2, 2017, 04, 22, null, null, dt, s1,
				s2, s3);
		testEquals("Case 1 -- if both DawsonElection objects reference to the same object - should return true ", de,
				de3, true);
		testEquals("Case 2 -- if either DawsonElection object is null referenced  - should return false ", de, de1,
				false);
		testEquals("Case 3 -- if both DawsonElection objects have the same Election name - should return true ", de,
				de4, true);
		testEquals("Case 4 -- if both DawsonElection objects have the different Election name - should return false ",
				de, de2, false);

	}

	private static void testEquals(String testCase, DawsonElection de, DawsonElection d1, boolean expectValid) {
		System.out.println("   " + testCase);
		try {

			if (de.equals(d1) == expectValid) {
				if (de.equals(d1)) {
					System.out.println(" Passed test --- " + de.getName() + " and " + d1.getName() + " are equal");
				} else {
					if (de == null || d1 == null) {
						System.out.println("Passed test -- null referenced objects should return false");
					} else {
						System.out.println(
								" Passed test --- " + d1.getName() + " and " + de.getName() + " are  not equal");
					}
				}
			} else {
				System.out.print(" ==== FAILED TEST ==== ERROR OOCURED IN THE EQUALS METHOD  ");
			}

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

	private static void testHashCode() {
		DawsonTally dt = new DawsonTally(3, "Hello World");
		DawsonTally dt1 = new DawsonTally(3, "Hek");
		String s1 = "Brandon";
		String s2 = "Bob";
		String s3 = "Jordy";
		System.out.println("---------- TESTING THE HASH_CODE ----------");
		DawsonElection de = new DawsonElection("Hello World", "single", 2016, 12, 2, 2017, 04, 22, null, null, dt, s1,
				s2, s3);
		DawsonElection de2 = new DawsonElection("Hek", "single", 2016, 12, 2, 2017, 04, 22, null, null, dt1, s1, s2,
				s3);
		DawsonElection de3 = de;
		DawsonElection de4 = new DawsonElection("Hello World", "single", 2016, 11, 2, 2017, 04, 22, null, null, dt, s1,
				s2, s3);
		testHashCode(
				"Case 1 -- if both DawsonElection objects reference to the same object - hashCode should be the same ",
				de, de3, true);
		testHashCode(
				"Case 2 -- if both DawsonElection objects have the same Election name - hashCode should be the same ",
				de, de4, true);
		testHashCode(
				"Case 3 -- if both DawsonElection objects have the different Election name - hashCode should be different ",
				de, de2, false);

	}

	private static void testHashCode(String testCase, DawsonElection de, DawsonElection d1, boolean expectValid) {
		System.out.println("   " + testCase);
		try {

			if ((d1.hashCode() == de.hashCode() && expectValid)) {

				System.out.println(" Passed test ---  The hashCode of the first DawsonElection object is "
						+ de.hashCode() + " and  the hash Code of the second DawsonElection Object is " + d1.hashCode()
						+ " have  equal hashCode");

			} else {
				System.out.println(" Passed test ---  The hashCode of the first DawsonElection object is "
						+ de.hashCode() + " and  the hash Code of the second DawsonElection Object is " + d1.hashCode()
						+ "  have  different hashCode");
			}

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

	private static void toStringTest() {
		DawsonTally dt = new DawsonTally(3, "Hello World");
		String s1 = "Brandon";
		String s2 = "Bob";
		String s3 = "Jordy";

		DawsonElection de = new DawsonElection("Hello World", "single", 2016, 12, 2, 2017, 04, 22, null, null, dt, s1,
				s2, s3);
		DawsonElection de1 = new DawsonElection("Hello World", "ranked", 2016, 12, 2, 2017, 04, 22, "G", "H", dt, s1,
				s2);
		System.out.println("---------- TESTING THE TO_STRING ----------");
		System.out.println(
				"Case 1 - A DawsonElection object that has no postal code range and has 3 ballot item choices");
		System.out.println(de.toString());
		System.out
				.println("Case 2 - A DawsonElection object that has a postal code range and has 2 ballot item choices");
		System.out.println(de1.toString());

	}

}
