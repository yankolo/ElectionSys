package util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class TestListUtilities {

	public static void main(String[] args) {
	
	TestGeneralSortMethod();
	TestMergeMethod();
	testSecondSort();
	}

	public static void TestGeneralSortMethod() {
		System.out.println("-----------Testing The Generic Sort method-----------");
		String[] array1 = {"Sammy" , "Mohammed" , "Nikita" , "Yanick" };
		TestGeneralSortMethod("Case 1 - valid String array should be sorted in alphabetic order", array1, true);
		String [] array2 = null;
		TestGeneralSortMethod("Case 2 - invalid String array, should not sort the array and should throw a NullPointerException", array2, false);
		String [] array3 = {"Sammy" , "Mohammed" , "Nikita" , "Yanick" , null };
		TestGeneralSortMethod("Case 3 - invalid String array, should not sort the array and should throw a IllegalArgumentException", array3, false);

	}

	public static void TestGeneralSortMethod(String testCase, String[] array, boolean expectValid) {
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
	//defining method headers for methods
	public static void TestMergeMethod() {
		System.out.println("-----------Testing The Merge method-----------");
		String[] array1 = {"Sammy"  , "Nikita" , "Yanick" };
		ListUtilities.sort(array1);
		String[] array2 = {"Lamelo" , "Sammy" , "Mohammed" , "Liangelo" , "Lavar", "Zack" };
		ListUtilities.sort(array2);
		TestMergeMethod("Case 1 -- Valid sorted arrays are passed to the merge method -- ", array1, array2, true);
		String [] array3 = null;
		String [] array4 = null;
		TestMergeMethod("Case 2 -- Invalid sorted arrays are passed to the merge method - ( the first array is null referenced) -- ", array3, array2, false);
		TestMergeMethod("Case 3 -- Invalid sorted arrays are passed to the merge method - ( the second array is null referenced) -- ", array1, array4, false);
		TestMergeMethod("Case 4 -- Invalid sorted arrays are passed to the merge method - ( the both arrays are null referenced) -- ", array3, array4, false);
		String [] array5 = {"Sammy" , null , "Nikita" , "Yanick" };
		TestMergeMethod("Case 5 -- Invalid sorted arrays are passed to the merge method - ( first array contains a null referenced value ) -- ", array5, array2, false);
		TestMergeMethod("Case 6 -- Invalid sorted arrays are passed to the merge method - ( second array contains a null referenced value ) -- ", array2, array5, false);

		
	}
	public static void TestMergeMethod(String testCase, String[] array1, String [] array2 ,  boolean expectValid) {
	
		System.out.println(" " + testCase);
		try {
			Comparable[] c = ListUtilities.merge(array1, array2, "hello.txt");
			System.out.print("\tThe String array instance was sorted\n" );
			System.out.println("\t This the content of the Sorted String " + Arrays.toString(c));

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
		} catch (IOException e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
			if (expectValid) {
				System.out.println("Expected Valid");
			}
		}
		System.out.println("\n");
	}
	public static void testSecondSort(){
		System.out.println("Testing the second sort method \n");
		
		Comparable[] array = {"Yan", "Sam", "Mo", "Nik"};
		Comparable[] arrayWithNullElement = {"Yan", null, "Mo", "Nik"};
		
		StringComparator sc = new StringComparator();
		
		testSecondSort("Case 1: null array", null, sc, false);
		testSecondSort("Case 2: array with null element", arrayWithNullElement, sc, false);
		testSecondSort("Case 3: valid array sorted with StringComparator", array, sc, true);
		
	}
	
	public static void testSecondSort(String testCase, Comparable[] c, Comparator sort, boolean expectValid) {
	    System.out.println(testCase);
		  
	    try { 
		   ListUtilities.sort(c, sort);
		   System.out.print("\tThe array was sorted\n" );
		   System.out.println("\tThis the content of the Sorted String " + Arrays.toString(c));
		   	    
		   if (!expectValid)
		     System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
	    } 
	    
	    catch (IllegalArgumentException iae) {
	    	System.out.println("\t" + iae.getMessage());
		    if (expectValid)
		      System.out.println(" Error! Expected Valid. ====== FAILED TEST =====");
		} 
	    
	    catch (NullPointerException npe) {
		    System.out.println("\t" + npe.getMessage());
		    if (expectValid)
		      System.out.println(" Error! Expected Valid. ====== FAILED TEST =====");   
		} 
	    
	    catch (Exception e) {
		    System.out.println("\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
		    if (expectValid)
		      System.out.println("Expected Valid");
	    }
		System.out.println("\n");
	}
}
