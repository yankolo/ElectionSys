/**
 * 
 */
package lib;

import java.io.Serializable;

/**
 * @author Mohamed Hamza
 *
 */
public class Email implements Serializable, Comparable<Email> {
	private static final long serialVersionUID = 4203172017L;
	private final String address;

	/**
	 * The constructor calls the validateEmail method that validates an Email as
	 * described in the comment of validateEmail.
	 * 
	 * @param address
	 */
	public Email(String address) {
		this.address = validateEmail(address);
	}

	/**
	 * The getAddress method returns a String representing the address.
	 * 
	 * @return emailObj.toString()
	 */
	public String getAddress() {
		Email emailObj = new Email(this.address);
		return emailObj.toString();
	}

	/**
	 * The getUserId method returns a String representing the userId which starts
	 * from the beginning (inclusive) of the email till the at symbol '@'
	 * (exclusive).
	 * 
	 * @return userId
	 */
	public String getUserId() {
		String userId = address.substring(0, address.indexOf('@'));
		return userId;
	}

	/**
	 * The getHost method returns a String representing the host name which starts
	 * from the at symbol '@' (exclusive) till the end of the email (inclusive).
	 * 
	 * @return host
	 */
	public String getHost() {
		String host = address.substring(address.indexOf('@') + 1, address.length());
		return host;
	}

	@Override
	public final int hashCode() {
		return address.toUpperCase().hashCode();
	}

	/**
	 * The equals method validates first if the object passed is null or an instance of 
	 * Email. It then compares both Email ignoring cases.
	 * @param obj
	 * @return a boolean of the 2 compared emails
	 */
	@Override
	public final boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Email)) {
			return false;
		}

		Email emailObj = (Email) obj;
		return this.address.equalsIgnoreCase(emailObj.address);
	}

	@Override
	public String toString() {
		return this.address;
	}

	/**
	 * The validateEmail method takes an email an valid it using an Extremely
	 * Powerful Regex that validates first the userId by checking if it's of length
	 * 1-32 composed of upper and lower case letters, digits, hyphen, underscore,
	 * dot. Note: the dot can't be the first or last character of a userId, and
	 * there canno't be two consecutive dots. For the host name, it must have at
	 * least 1 domain segment. If there are more than 1 segment, they must be dot
	 * separated. Each segment must be of length 1-32 composed of upper and lower
	 * case letters, digits, hyphen. Note: the host name segment can't start or end
	 * with a hyphen. A valid email must have only one at symbol '@' and must
	 * consist only from valid characters (as described above).
	 * 
	 * @param email
	 * @return a trimmed email
	 */
	private String validateEmail(String email) {
		if (email == null) {
			throw new IllegalArgumentException("Email Error - email must exist. Invalid value = " + email);
		}

		if (!(email.replaceAll("^\\s+|\\s+$", "").matches(
				"^[a-zA-Z0-9_-](?:(?:[a-zA-Z0-9_-]|(?:(\\.)(?!\\1))){0,30}[a-zA-Z0-9_-])?@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,30}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,30}[a-zA-Z0-9])?)*$"))) {
			throw new IllegalArgumentException("Email Error - Inavlid email address = " + email);
		} else {
			return email.trim();
		}
	}

	/**
	 * Overridden the compareTo method checks the hosts, if the hosts are the same it will check the userIds.
	 * 
	 * @param Email o - an Email Object
	 * @return int - returns a int value of the comparTo method from the String class
	 */
	@Override
	public int compareTo(Email o) {
		if (o == null) {
			throw new IllegalArgumentException("Email Error - email must exist.");
		}

		if (this.getHost().compareToIgnoreCase(o.getHost()) == 0) {
			if (this.getUserId().compareToIgnoreCase(o.getUserId()) < 0) {
				return -1;
			} else if (this.getUserId().compareToIgnoreCase(o.getUserId()) > 0) {
				return 1;
			} else {
				return 0;
			}
		}
		return this.getHost().compareToIgnoreCase(o.getHost());
	}

}
