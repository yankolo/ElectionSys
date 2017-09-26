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
		this.firstName = validate(firstName);
		this.lastName = validate(lastName);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Name))
			return false;
		Name other = (Name) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	private String validate(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Address Error - Invalid value = " + name);
		}
		
		String valid = name.trim();
		
		if(!valid.matches("^[a-zA-Z](?:[a-zA-Z]|(?:([ '-])(?![ '-])))*[a-zA-Z]$")) 
			throw new IllegalArgumentException("Address Error - Invalid value = " + name);
		
		return valid;
	}
}
