package election.business;

/**
 * Test class of the VoterPostalCodeComparator class
 * 
 * @author Nikita
 * @version 13.10.2017
 */
public class VoterPostalCodeComparatorTest {
	public static void main(String[] args) {
		testCompare();
	}
	
	private static void testCompare() {
		System.out.println("Testing the compare method \n");
		testCompare("Case 1: equal Voters", "Yanik", "Kolomatski", "J4W2Y9", "J4W2Y9", "yan@gmail.com", "yan@gmail.com", 0);
		testCompare("Case 2: different postal codes, voter1 after than voter2", "Yanik", "Kolomatski", "J4W2Y9", "H4W2Y9", "yan@gmail.com", "yannnn@gmail.com", 1);
		testCompare("Case 3: different postal codes, voter1 before than voter2", "Yanik", "Kolomatski", "H4W2Y9", "J4W2Y9", "yan@gmail.com", "yannnn@gmail.com", -1);
		testCompare("Case 4: same postal codes, natural order determined by email", "Yanik", "Kolomatski", "J4W2Y9", "J4W2Y9", "yan@gmail.com", "yannnn@gmail.com", -1);
	}
	
	private static void testCompare(String testCase, String fName, String lName, String pCode, String pCodeTwo, String eAddress, String eAddressTwo, int expectedResult) {
		System.out.println(testCase);
		
		try {
			VoterPostalCodeComparator vpcc= new VoterPostalCodeComparator();
			DawsonVoter dv = new DawsonVoter(fName, lName, eAddress, pCode);
			DawsonVoter otherDv = new DawsonVoter(fName, lName, eAddressTwo, pCodeTwo);
			System.out.print("\tA DawsonVoter instance was created: " + dv);
			System.out.print("\n\tA second DawsonVoter instance was created: " + otherDv);

			if (expectedResult < 0 && vpcc.compare(dv, otherDv) <= expectedResult)
				System.out.print("\tReturn: " + expectedResult);
			else if(expectedResult > 0 && vpcc.compare(dv, otherDv) >= expectedResult)
				System.out.print("\tReturn: " + expectedResult);
			else if(expectedResult == 0 && vpcc.compare(dv, otherDv) == expectedResult)
				System.out.print("\tReturn: " + expectedResult);
			else
				System.out.print("Error! Expected Invalid. ==== FAILED TEST ====");
		} 
		catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			System.out.println("Error! Expected Invalid. ==== FAILED TEST ====");
		} 
		catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		}
		System.out.println("\n");
	}
}