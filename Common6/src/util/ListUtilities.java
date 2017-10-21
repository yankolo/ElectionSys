/**
 * 
 */
package util;

/**
 * @author katsuragi
 *
 */
public class ListUtilities {
	
	/*
	 * Sorts a list of objects in ascending natural order using 
	 * selection sort.
	 * 
	 * Precondition: 	Assumes that the list is not null and that the 
	 *	list's capacity is equal to the list's size.
	 * 
	 *
	 * @param list 	A list of objects. Assumes that the
	 *             	list's capacity is equal to the list's size. 
	 * 
	* @throws  		IllegalArgumentException if the parameter is
	*			not full to capacity.
	*
	* @throws		NullPointerException if the list is null.
	 */
		 @SuppressWarnings({ "rawtypes", "unchecked" })
		 public static void sort (Comparable[] list) throws IllegalArgumentException , NullPointerException{
			 if(list == null) {
				 throw new NullPointerException("The array that is passed through this method cannot be null referenced");
			 }
			 for(Comparable element : list) {
				 if(element == null) {
					 throw new IllegalArgumentException("The list array must be filled if you wish to sort it");
				 }
			 }
			 for (int i = 0 ; i < list.length - 1 ; i ++ ) {
				 int smallIndex = i ;
				 for(int j = i + 1 ; j < list.length; j ++) {
					 if(list[j].compareTo(list[smallIndex]) < 0 ) {
						 smallIndex =j;
					 }
				 }
				 Comparable temp = list[smallIndex];
				 list[smallIndex] = list[i];
				 list[i] = temp;
			 }
			 
		 }
	
}
