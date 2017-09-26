/**
 * 
 */
package lib;

/**
 * @author Yanik Kolomatski
 *
 */
public final class PostalCode {
	private final String code;
	
	public PostalCode (String code) throws IllegalArgumentException
	{
		this.code = validate(code);
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
