package util;

import java.io.IOException;
import java.util.Arrays;

public class TestListUtilities {

	public static void main(String[] args) {
	
	TestSortMethod();
	}

	public static void TestSortMethod() {
		System.out.println("-----------Testing The Generic Sort method-----------");
		String[] array1 = {"Sammy" , "Mohammed" , "Nikita" , "Yanick" };
		TestSortMethod("Case 1 - valid String array should be sorted in alphabetic order", array1, true);
		String [] array2 = null;
		TestSortMethod("Case 2 - invalid String array, should not sort the array and should throw a NullPointerException", array2, false);
		String [] array3 = {"Sammy" , "Mohammed" , "Nikita" , "Yanick" , null };
		TestSortMethod("Case 3 - invalid String array, should not sort the array and should throw a IllegalArgumentException", array3, false);

	}

	public static void TestSortMethod(String testCase, String[] array, boolean expectValid) {
		System.out.println("   " + testCase);
		try {
			ListUtilities.sort(array);
			System.out.print("\tThe String array instance was sorted\n" );
			System.out.println("\t This the content of the Sorted String " + Arrays.toString(array));

			if (!expectValid)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

			System.out.println("\n");
		} catch (IllegalArgumentException iae) {
			System.out.println("\t" + iae.getMessage());
			if (expectValid) {
				System.out.println(" Error! Expected Valid. ====== FAILED TEST =====");
			}
		} catch (NullPointerException npe) {
			System.out.println("\t" + npe.getMessage());
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
	

}
