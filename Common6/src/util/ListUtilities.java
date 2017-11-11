package util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListUtilities {
	private static final Charset CHARACTER_ENCODING = StandardCharsets.UTF_8;

	/**
	 * Default ListUtilities constructor
	 */
	private ListUtilities(){}

	/**
	 * Takes in a list of objects and writes them to a given file. This method
	 * overwrites data in file and uses UTF8 character set.
	 * 
	 * @param objects
	 *            Array of items to be written to file.
	 * @param filename
	 *            filename and location of the file where list of items will be
	 *            written.
	 * @throws IOException
	 *             if an I/O error occurs writing to or creating the file
	 */
	public static void saveListToTextFile(Object[] objects, String filename) throws IOException {
		saveListToTextFile(objects, filename, false, CHARACTER_ENCODING);
	}

	/**
	 * Takes in a list of objects and writes them to a given file. This method uses
	 * UTF8 character set.
	 * 
	 * @param objects
	 *            Array of items to be written to file.
	 * @param filename
	 *            filename and location of the file where list of items will be
	 *            written.
	 * @param append
	 *            boolean indicating if the file is overwritten or if the items are
	 *            written to the end of the file.
	 * @throws IOException
	 *             if an I/O error occurs writing to or creating the file
	 */
	public static void saveListToTextFile(Object[] objects, String filename, boolean append) throws IOException {
		saveListToTextFile(objects, filename, append, CHARACTER_ENCODING);
	}

	/**
	 * Takes in a list of objects and writes them to a given file.
	 * 
	 * @param objects
	 *            Array of items to be written to file.
	 * @param filename
	 *            filename and location of the file where list of items will be
	 *            written.
	 * @param append
	 *            boolean indicating if the file is overwritten or if the items are
	 *            written to the end of the file.
	 * @param characterEncoding
	 *            the Charset to be used when encoding
	 * @throws IOException
	 *             if an I/O error occurs writing to or creating the file
	 */
	public static void saveListToTextFile(Object[] objects, String filename, boolean append, Charset characterEncoding)
			throws IOException {

		Path path = Paths.get(filename);
		OpenOption option;
		if (append)
			option = StandardOpenOption.APPEND;
		else
			option = StandardOpenOption.CREATE;

		// create list of strings
		List<String> toWrite = new ArrayList<String>();
		for (Object obj : objects)
			if (obj != null)
				toWrite.add(obj.toString());

		// write list to file
		Files.write(path, toWrite, characterEncoding, StandardOpenOption.WRITE, option);
	}

	/**
	 * @author Sammy Chaouki
	 * 
	 * Sorts a list of objects in ascending natural order using selection sort.
	 * 
	 * Precondition: Assumes that the list is not null and that the list's capacity
	 * is equal to the list's size.
	 * 
	 *
	 * @param list A list of objects. Assumes that the list's capacity is equal to
	 * the list's size.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] list){
		for (int i = 0; i < list.length - 1; i++) {
			int smallIndex = i;
			for (int j = i + 1; j < list.length; j++) {
				if (list[j].compareTo(list[smallIndex]) < 0) {
					smallIndex = j;
				}
			}
			Comparable temp = list[smallIndex];
			list[smallIndex] = list[i];
			list[i] = temp;
		}

	}
	/**
	 * @author - Sammy Chaouki
	 * 
	 * Efficiently merges two sorted lists of objects in ascending natural order. If
	 * the duplicate objects are in both lists, the object from list1 is merged into
	 * the resulting list, and both objects are written to the duplicate file.
	 * 
	 * Precondition: Assumes that the lists are not null and that both lists contain
	 * objects that can be compared to each other and are filled to capacity.
	 * 
	 * This method will not throw an IO exception. We have decided that throwing an exception in this method
	 * will result in a problem in any application that expects a merged array to be returned even if the file 
	 * does not exist or any other reason a IOException is thrown
	 *
	 * @param list1 A naturally sorted list of objects. Assumes that the list
	 * contains no duplicates and that its capacity is equal to its size.
	 * 
	 * @param list2 A naturally sorted list of objects. Assumes that the list
	 * contains no duplicates and that its capacity is equal to its size.
	 * 
	 * @param duplicateFileName The name of the file in datafiles\duplicates to
	 * which duplicate pairs will be appended.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Comparable[] merge(Comparable[] list1, Comparable[] list2, String duplicateFileName)
			throws IllegalArgumentException{
		List<String> duplicateArrayList = new ArrayList<String>();
	
		if (duplicateFileName == null) {
			throw new IllegalArgumentException("You may not pass a null referenced path to this file");
		}
		Comparable[] list3 = (Comparable[]) Array.newInstance(list1.getClass().getComponentType(),
				list1.length + list2.length);
		int indexArrayForList1 = 0; // this variable is used to keep track of the index for the list1 array
		int indexArrayForList2 = 0; // this variable is used to keep track fo the index for the list2 array
		int indexArrayDuplicate = 0; // this variable is used to keep track of the index for the duplicate array
		for (int i = 0; i < list3.length; i++) {
			/*
			 * This if statement is used to check if the index of the list1 array is equal to its length
			 * this means that the list1 has been fully filled in the merge method.
			 * It will just add all the list2 elements to the merge array
			 */
			if (indexArrayForList1 == list1.length) {
				for (int j = indexArrayForList2; j < list2.length; j++) {
					list3[i] = list2[j];
					i++;
				}
				break;
			}
			/*
			 * This if statement is used to check if the index of the list2 array is equal to its length
			 * this means that the list1 has been fully filled in the merge method.
			 * It will just add all the list2 elements to the merge array
			 */
			if (indexArrayForList2 == list2.length) {
				for (int j = indexArrayForList1; j < list1.length; j++) {
					list3[i] = list1[j];
					i++;
				}
				break;
			}
			/*
			 * This just compares the element of list1 to list2, 
			 * and if the list1 element is lower than the element of list2 
			 * store the element of list1 to the merge array and increment the index for the list1 array
			 */
			if (list1[indexArrayForList1].compareTo(list2[indexArrayForList2]) < 0) {
				list3[i] = list1[indexArrayForList1];
				indexArrayForList1++;
				continue;

			}
			/*
			 * This just compares the element of list1 to list2, 
			 * and if the list1 element is greater than the element of list2 
			 * store the element of list2 to the merge array and increment the index for the list2 array
			 */
			else if (list1[indexArrayForList1].compareTo(list2[indexArrayForList2]) > 0) {
				list3[i] = list2[indexArrayForList2];
				indexArrayForList2++;
				continue;
			}
			/*
			 *This will only execute if both elements are the same.
			 *It will then store the first element to the duplicate array with a message indicating that the first element was merged
			 *and it will only add one of the elements to the merge array
			 */
			else {
				list3[i] = list1[indexArrayForList1];
				duplicateArrayList.add(indexArrayDuplicate, list1[indexArrayForList1].toString() + " (merged)");
				indexArrayDuplicate++;
				duplicateArrayList.add(indexArrayDuplicate, list2[indexArrayForList2].toString());
				indexArrayForList1++;
				indexArrayForList2++;
				continue;
			}

		}
		/*
		 * If the size of the duplicate array is not equal 0,
		 *  this means that the array has duplicates. If true, 
		 *  it will write the duplicate array in the file passed to the merge method 
		 */
		if (duplicateArrayList.size() != 0) {
			try {
				
				saveListToTextFile(duplicateArrayList.toArray(), duplicateFileName);
			}
			catch(IOException io) {
				System.err.println("Error:  Cannot write duplicate elements\n\tTo file: " + duplicateFileName  );
			}
		}
		list3 = Arrays.copyOf(list3, list3.length - indexArrayDuplicate);
		return list3;
	}
	
	 /**
	  * @author Nikita
	  * 
	  * Sorts a list of objects in the given order.
	  * 
	  * Precondition: Assumes that the list is not null and that the 
	  *	list's capacity is equal to the list's size.
	  * 
	  * @param list A list of objects. Assumes that the list's capacity is equal to the list's size. 
	  * @param sortOrder A Comparator object that defines the sort order 
	  */
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public static void sort(Comparable[] list, Comparator sortOrder){	 
		 Comparable temp;
		 for (int a = 0; a < list.length - 1; a++) {
			 for (int b = a + 1; b < list.length; b++) {
				 if (sortOrder.compare(list[a], list[b]) > 0) {
					 temp = list[b];
					 list[b] = list[a];
					 list[a] = temp;
				 }
			 }
		 }
	 }
	 /**
	  * @author Nikita
	  * 
	  * Goes through an array of Comparables and looks for the key using a binary search algorithm
	  * 
	  * @param database is an array of Comparables
	  * @param key is object of type Comparable to look for in database
	  * @return int index at which key is found
	  */
	 @SuppressWarnings({"unchecked","rawtypes"})
	 public static int binarySearch(Comparable[] database, Comparable key) {
		 int high = database.length - 1;
		 int low = 0;
		 int mid = (low + high) / 2;
		 
		 while(high >= low) {
			 if(database[mid].compareTo(key) == 0)
				 return mid;
			 else if(database[mid].compareTo(key) > 0)
				 high = mid - 1;
			 else
				 low = mid + 1;
		 }
		 
		 return (-low - 1);
	 }
	 
	 /**
	  * @author Nikita
	  * 
	  * Recursive version of the binarySearch.
	  * 
	  * @param database is a List containing elements of type T
	  * @param key is object of type T to look for in database
	  * @param low is lower boundary of the search-portion of the list
	  * @param high is the higher boundary of the search-portion of the list
	  * @return int index at which key is found 
	  */
	 public static <T extends Comparable<? super T>> int binarySearch(List<T> database, T key, int low, int high) {
		 if(low > high)
			 return (-low - 1);
		 
		 int midpoint = (low + high) / 2;
		 if(database.get(midpoint).compareTo(key) == 0)
			 return midpoint;
		 else if(database.get(midpoint).compareTo(key) > 0)
			 return binarySearch(database, key, low, midpoint-1);
		 else 
			 return binarySearch(database, key, midpoint+1, high); 	 
	 }
}
