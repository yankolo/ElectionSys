/**
 * 
 */
package lib;

import java.io.Serializable;

/**
 * The class stores a valid postal code
 * It provides some methods to interact with the data
 * @author Yanik Kolomatski
 */
public final class PostalCode implements Serializable, Comparable<PostalCode>{
	private static final long serialVersionUID = 4203172017L;
	private final String code;
	
	/**
	 * Instanstiate a new PostalCode object using a postal code
	 * A valid postal code must have the following format: ANA NAN or ANANAN
	 * (A - alphabetical character, N - numerical character)
	 * D, F, I, O, Q and U are NOT permitted. Also, the first position cannot be W or Z.
	 * 
	 * @author Yanik Kolomatski
	 * @param code The code to store
	 * @throws IllegalArgumentException If code is not valid (according to rules)
	 */
	public PostalCode (String code) throws IllegalArgumentException
	{
		this.code = validate(code);
	}
	
	/**
	 * Compares two PostalCode objects according to the Comparable<T> interface contract
	 * @param postalCode The postalObject object to compare
	 * @throws IllegamArgumentException If the PostalCode parameter is null
	 */
	public int compareTo(PostalCode postalCode)
	{
		if (postalCode == null)
			throw new IllegalArgumentException("PostalCode Error - PostalCode parameter null");
		
		return this.code.compareToIgnoreCase(postalCode.code);
	}
	
	/**
	 * @return String The code
	 */
	public String getCode()
	{
		return code;
	}
	
	/**
	 * Checks if the PostalCode object is between the specified boundries (inclusive)
	 * Boundries are specified with an another postal code or little segment from the postal code
	 * Ex: postalCodeInstance.inRange("J2", "H"), postalCodeInstance.inRange("J4W2Y9", "H")
	 * 
	 * @param start The lower boundry 
	 * @param end The upper boundry
	 * @return boolean true - postal code in the range, false - postal code not in the range
	 * @throws IllegalArgumentException If the string parameters are null/invalid
	 */
	public boolean inRange(String lowerBoundry, String upperBoundry)
	{
		if (lowerBoundry == null || upperBoundry == null)
			throw new IllegalArgumentException("Error - inRange() params are null");
		
		lowerBoundry = lowerBoundry.trim();
		upperBoundry = upperBoundry.trim();
		
		// Validates that lowerBoundry and upperBoundry are in the format ANANAN or ANA ANA with regex
		// Doesn't have to be a full postal code (ex: can be AN, ANA, or A)
		// Doesn't have the same character restrictions as a real postal code
		if (!lowerBoundry.matches("(?i)^[a-z](?:[0-9](?:[a-z](?: ?[0-9](?:[a-z](?:[0-9])?)?)?)?)?$"))
			throw new IllegalArgumentException("Error - inRange() params are"
					+ " not valid. Value = " + lowerBoundry);
		if (!upperBoundry.matches("(?i)^[a-z](?:[0-9](?:[a-z](?: ?[0-9](?:[a-z](?:[0-9])?)?)?)?)?$"))
			throw new IllegalArgumentException("Error - inRange() params are"
					+ " not valid. Value = " + upperBoundry);
		
		// Remove space in the middle of the code if there is one and put code in all uppercase
		// That way the comparison of the two strings (this.code and lowerBoundry/upperBoundry
		// will be more accurate)
		lowerBoundry = lowerBoundry.replaceAll(" ", "").toUpperCase();
		upperBoundry = upperBoundry.replaceAll(" ", "").toUpperCase();
		
		// Checks if this.code is between lowerBoundry and upperBoundry inclusively
		if (lowerBoundry.compareToIgnoreCase(this.code) <= 0 && upperBoundry.compareToIgnoreCase(this.code) >= 0)
			return true;
		return false;
	}
	
	/**
	 * @return int The hashcode of the object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	/**
	 * @param obj The object to compare
	 * @return boolean true - the object is equal, false - the object is not equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PostalCode))
			return false;
		PostalCode other = (PostalCode) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	/**
	 * @return String The postal code
	 */
	@Override
	public String toString() {
		return code;
	}
	
	/**
	 * Verify if the code string is valid or not
	 * A postal code is valid if it is in the format ANANAN or 
	 * ANA NAN where A is an alphabetical letter and N is a number.
	 * The code cannot contain the letters  D, F, I, O, Q and U. 
	 * In addition, the letters W and Z cannot be in the first position
	 * @param code The code string to validate
	 * @return String valid code string
	 */
	private String validate(String code)
	{
		if (code == null) 
			throw new IllegalArgumentException("PostalCode Error - Invalid value = " + code);
			
		String trimmedCode = code.trim();
		
		// Checks if postal code is valid (according to rules) using regex
		if (!trimmedCode.matches("(?i)^[a-ceghj-npr-tvxy][0-9][a-ceghj-npr-tv-z] ?[0-9][a-ceghj-npr-tv-z][0-9]$"))
			throw new IllegalArgumentException("PostalCode Error - Invalid value = " + code);
		
		// Remove the space in the middle of the postal code if there is one 
		trimmedCode = trimmedCode.replaceAll(" ", "").toUpperCase();
		return trimmedCode;
	}
}
