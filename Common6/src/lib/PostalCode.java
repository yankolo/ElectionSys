/**
 * 
 */
package lib;

/**
 * @author Yanik Kolomatski
 *
 */
public final class PostalCode implements Comparable<PostalCode>{
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
