package lib;

import java.io.Serializable;

/**
 * @author Yanik Kolomatski
 *
 */
public final class Name implements Serializable, Comparable<Name>{
	private static final long serialVersionUID = 42031768871L;
	private final String firstName;
	private final String lastName;

	public Name(String firstName, String lastName) {
		this.firstName = validNameString("first name", firstName);
		this.lastName = validNameString("last Name", lastName);
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}


	/**
	 * 
	 */
	public String toString() {
		return (firstName + "*" + lastName);
	}
	public int compareTo(Name name) {
		if (name == null)
			throw new IllegalArgumentException("Name Error - Name parameter null");
		
		// If the last names are equal, the first names will be compared
		if (this.lastName.compareToIgnoreCase(name.lastName) == 0)
			return this.firstName.compareToIgnoreCase(name.firstName);
		
		return this.lastName.compareToIgnoreCase(name.lastName);
	}

	private String validNameString(String fieldName, String name) {
		if (name == null) {
			throw new IllegalArgumentException("Address Error - " + fieldName + " must exist. Invalid value = " + name);
		}
		String valid = name.trim();
		if(valid.isEmpty()) {
			throw new IllegalArgumentException("Address Error - " + fieldName + " must exist. Invalid value = " + name);
		}
		return valid;
	}

}
