package lib;

public class Name {
	private String firstName;
	private String lastName;

	public Name(String firstName, String lastName) {
		 setFirstName(firstName);
		 setLastName(lastName);
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
	public void setFirstName(String firstName) {
		this.firstName = validNameString("first name", firstName);
	}
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = validNameString("last Name", lastName);
	}
	/**
	 * 
	 */
	public String toString() {
		return (firstName + "*" + lastName);
	}
	public int compareTo(Name name) {
		if(this.lastName.compareToIgnoreCase(name.lastName) == 0) {
			if(this.firstName.compareToIgnoreCase(name.firstName) == 0) {
				return 0;
			}
			else if(this.firstName.compareToIgnoreCase(name.firstName) < 0) {
				return -1;
			}
			else {
				return 1;
			}
		}else {
			if(this.firstName.compareToIgnoreCase(name.firstName) < 0) {
				return -1;
			}
			else {
				return 1;
			}
		}
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
