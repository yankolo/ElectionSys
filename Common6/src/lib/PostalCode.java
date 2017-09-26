/**
 * 
 */
package lib;

import java.io.Serializable;

/**
 * @author Yanik Kolomatski
 *
 */
public final class PostalCode implements Serializable, Comparable<PostalCode>{
	private static final long serialVersionUID = 4203172017L;
	private final String code;
	
	public PostalCode (String code) throws IllegalArgumentException
	{
		this.code = validate(code);
	}
	
	public int compareTo(PostalCode postalCode)
	{
		if (postalCode == null)
			throw new IllegalArgumentException("PostalCode Error - PostalCode parameter null");
		
		return this.code.compareToIgnoreCase(postalCode.code);
	}
	
	public String getCode()
	{
		return code;
	}
	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}


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
	
	@Override
	public String toString() {
		return code;
	}

	private String validate(String code)
	{
		if (code == null) 
			throw new IllegalArgumentException("PostalCode Error - Invalid value = " + code);
			
		String trimmedCode = code.trim();
		
		if (!trimmedCode.matches("(?i)^[a-ceghj-npr-tvxy][0-9][a-ceghj-npr-tv-z] ?[0-9][a-ceghj-npr-tv-z][0-9]$"))
			throw new IllegalArgumentException("PostalCode Error - Invalid value = " + code);
		
		trimmedCode = trimmedCode.replaceAll(" ", "").toUpperCase();
		return trimmedCode;
	}
}
