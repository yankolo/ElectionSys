package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestListUtilities {

	public static void main(String[] args) {
	
	TestGeneralSortMethod();
	TestMergeMethod();
	testSecondSort();
	testBinarySearch();
	testRecBinarySearch();
	}

	public static void TestGeneralSortMethod() {
		System.out.println("-----------Testing The Generic Sort method-----------");
		String[] array1 = {"Sammy" , "Mohammed" , "Nikita" , "Yanick" };
		TestGeneralSortMethod("Case 1 - valid String array should be sorted in alphabetic order", array1, true);
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
		} catch (Exception e) {
			System.out.println(
					"\tUNEXPECTED EXCEPTION TYPE!" + e.getClass() + " " + e.getMessage() + "====FAILED TEST====");
			if (expectValid) {
				System.out.println("Expected Valid");
			}
		}
		System.out.println("\n");
	}
	
	/*
	 * As it is not possible to use the VoterName and PostalCode comparators - since they are located in ElectionSys -.
	 * The sort method is test using a custom StringComparator that works in a way similar to the other two Comparator
	 * only it takes 2 Strings instead of 2 Voters
	 */
	public static void testSecondSort(){
		System.out.println("Testing the second sort method \n");
		
		Comparable[] array = {"Yan", "Sam", "Mo", "Nik"};
		
		StringComparator sc = new StringComparator();
		testSecondSort("Case 1: valid array of strings sorted with StringComparator", array, sc, true);
	}
	
	public static void testSecondSort(String testCase, Comparable[] c, Comparator sort, boolean expectValid) {
	    System.out.println(testCase);
		  
	    try { 
		   ListUtilities.sort(c, sort);
		   System.out.print("\t\tThe array was sorted\n" );
		   System.out.println("\t\tThis the content of the Sorted String " + Arrays.toString(c));
		   	    
		   if (!expectValid)
		     System.out.print(" Error! Expected Invalid. ==== FAILED TEST ====");
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
	
	public static void testBinarySearch() {
		System.out.println("Testing the binarySearch method \n");
		
		Comparable[] array = {"a", "b", "c", "d", "e"};
		String key = "d";
		String invalidKey = "f";
		testBinarySearch("Case 1 - look for a valid key (" + key + ") in a valid string array {a, b, c, d, e}", array, key, true);
		testBinarySearch("Case 2 - look for an invalid key (" + invalidKey + ") in a valid string array {a, b, c, d, e}", array, invalidKey, false);		
	}
	
	public static void testBinarySearch(String testCase, Comparable[] array, String key, boolean expectValid) {
	    System.out.println(testCase);
	    try { 
	       if(expectValid == false){
	    	   System.out.println("\t\tTest Passed! As expected, the key (" + key + ") was not found");
	    	   System.out.println("\t\tReturn: " + ListUtilities.binarySearch(array, key));
	       }
	       else{
	    	   ListUtilities.binarySearch(array, key);
	    	   System.out.print("\t\tTest Passed! The key (" + array[ListUtilities.binarySearch(array, key)] + ") was found at index " + ListUtilities.binarySearch(array, key));
	       }
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
	
	public static void testRecBinarySearch() {
		System.out.println("Testing the recursive binarySearch method \n");
		
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		String key = "b";
		String invalidKey = "f";
		testRecBinarySearch("Case 1 - look for a valid key (" + key + ") in a valid string list {a, b, c, d, e}", list, key, true);
		testRecBinarySearch("Case 2 - look for an invalid key (" + invalidKey + ") in a valid string list {a, b, c, d, e}", list, invalidKey, false);	
	}
	
	public static void testRecBinarySearch(String testCase, List<String> list, String key, boolean expectValid) {
	    System.out.println(testCase);
		  
	    try { 
	       if(expectValid == false){
	    		System.out.println("\t\tTest Passed! As expected, the key (" + key + ") was not found");
	    		System.out.println("\t\tReturn: " + ListUtilities.binarySearch(list, key, 0, list.size() - 1));
	       }	       
	       else{
	    	   ListUtilities.binarySearch(list, key, 0, list.size() - 1);
			   System.out.print("\t\tTest Passed! The key (" + list.get(ListUtilities.binarySearch(list, key, 0, list.size() - 1)) + ") was found at index " + ListUtilities.binarySearch(list, key, 0, list.size() - 1));
	       }
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
