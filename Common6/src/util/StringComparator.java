package util;

import java.util.Comparator;

/**
 * This class is used in the ListUtilitiesTest as an alternative to the VoterComparator
 * classes found in ElectionSys, since ListUtilitiesTest cannot use them.
 * 
 * @author Nikita
 *
 */
public class StringComparator implements Comparator<String> {

 @Override
 public int compare(String s1, String s2) {
  //if the strings are equal, comparing them should yield 0
  if (s1.equals(s2))
   return 0;
  
  //if the strings are not equal, return the uppercase string comparison
  else
   return s1.toUpperCase().compareTo(s2.toUpperCase());
 }
}
