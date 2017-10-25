package lib;

import java.io.Serializable;

/**
 * The class stores a valid first name and last name
 * It provides some methods to interact with the data
 * @author Yanik Kolomatski
 *
 */
public final class Name implements Serializable, Comparable<Name>{
	private static final long serialVersionUID = 42031768871L;
	private final String firstName;
	private final String lastName;

	/**
	 * A constructer to make a name object with valid first and last names
	 * A valid name contains letters, must be at least two letters long and 
	 * may contain the character " -'" if they are between letters
	 * @param firstName The first name to store
	 * @param lastName The last name to store
	 * @throws IllegalArgumentException Throws excpetion if the name is not valid (
	 */
	public Name(String firstName, String lastName) {
		this.firstName = validate(firstName);
		this.lastName = validate(lastName);
	}

	/**
	 * @return String The first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return String The last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return String The full name
	 */

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}


	/**
	 * @return String The string representation of the name object
	 */
	public String toString() {
		return (firstName + "*" + lastName);
	}
	
	/**
	 * Compares two name objects according to the Comparable<T> interface contract
	 * @param name The name object to compare
	 * @throws IllegalArgumentException If Name object parameter is null
	 */
	public int compareTo(Name name) {
		if (name == null)
			throw new IllegalArgumentException("Name Error - Name parameter null");

		// If the last names are equal, the first names will be compared
		if (this.lastName.compareToIgnoreCase(name.lastName) == 0)
			return this.firstName.compareToIgnoreCase(name.firstName);
		
		return this.lastName.compareToIgnoreCase(name.lastName);
	}

	/**
	 * @return int The hashcode of the object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		if (!(obj instanceof Name))
			return false;
		Name other = (Name) obj;
		if (!firstName.equals(other.firstName))
			return false;
		if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	/**
	 * Verify if the name string if valid or not
	 * A first/last name is valid if it contains at
	 * least two letters. The characters  ‘ (apostrophe),
	 *  - (hyphen) and space are only valid between two letters
	 * 
	 * @param name The name string to validate
	 * @return String valid name string
	 */
	private String validate(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Name Error - Invalid value = " + name);
		}
		
		String valid = name.trim();
		
		// Regex to validate the name according the the rules
		if(!valid.matches("^[a-zA-Z](?:[a-zA-Z]|(?:([ '-])(?![ '-])))*[a-zA-Z]$")) 
			throw new IllegalArgumentException("Name Error - Invalid value = " + name);
		
		return valid;
	}
}
