package election.business;

/**
 * Test class of the VoterNameComparator class
 * 
 * @author Nikita
 * @version 13.10.2017
 */
public class VoterNameComparatorTest {
	public static void main(String[] args) {
		testCompare();
	}
	
	private static void testCompare() {
		System.out.println("Testing the compare method \n");
		testCompare("Case 1: different names, voter1 after voter2", "Yanik", "Sammy", "Kolomatski", "Chaouki", "J4W2Y9", "yan@gmail.com", "yannnn@gmail.com", 1);
		testCompare("Case 2: different names, voter1 before voter2", "Sammy", "Yanik", "Chaouki", "Kolomatski", "J4W2Y9", "yan@gmail.com", "yannnn@gmail.com", -1);
		testCompare("Case 3: same names, natural order determined by email", "Yanik", "Yanik", "Kolomatski", "Kolomatski", "J4W2Y9", "yan@gmail.com", "yannnn@gmail.com", -1);
		testCompare("Case 4: same names, same emails, natural order determined by email", "Yanik", "Yanik", "Kolomatski", "Kolomatski", "J4W2Y9", "yan@gmail.com", "yan@gmail.com", 0);
	}
	
	private static void testCompare(String testCase, String fName, String fNameTwo, String lName, String lNameTwo, String pCode, String eAddress, String eAddressTwo, int expectedResult) {
		System.out.println(testCase);
		
		try {
			VoterNameComparator vnc= new VoterNameComparator();
			DawsonVoter dv = new DawsonVoter(fName, lName, eAddress, pCode);
			DawsonVoter otherDv = new DawsonVoter(fNameTwo, lNameTwo, eAddressTwo, pCode);
			System.out.print("\tA DawsonVoter instance was created: " + dv);
			System.out.print("\n\tA second DawsonVoter instance was created: " + otherDv);

			if (expectedResult < 0 && vnc.compare(dv, otherDv) <= expectedResult)
				System.out.print("\tReturn: " + expectedResult);
			else if(expectedResult > 0 && vnc.compare(dv, otherDv) >= expectedResult)
				System.out.print("\tReturn: " + expectedResult);
			else if(expectedResult == 0 && vnc.compare(dv, otherDv) == expectedResult)
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