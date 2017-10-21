/**
 * 
 */
package lib;

/**
 * @author katsuragi
 *
 */
public class Person {
	private Name name;
	private Address address;

	public Person(String firstName, String lastName) {
		this.setName(firstName, lastName);
		this.address = new Address();
	}

	public Person(String firstName, String lastName, Address address) {
		this.setName(firstName, lastName);
		setAddress(address);
	}

	/**
	 * @return the name
	 */
	public Name getName() {
		Name newName = new Name(this.name.getFirstName(), this.name.getLastName());
		return newName;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		Address newAddress;
		if (this.address.getCivicNumber().isEmpty()) {
			newAddress = new Address();

		} else {
			newAddress = new Address(this.address.getCivicNumber(), this.address.getStreetName(),
					this.address.getCity());
			newAddress.setCity(this.address.getCity());
			newAddress.setProvince(this.address.getProvince());
		}
		return newAddress;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String firstName, String lastName) {
		this.name.setFirstName(firstName);
		this.name.setLastName(lastName);
	}

	/**
	 * @param address
	 *            the address to set
	 * @return 
	 */
	public void setAddress(Address address) {
		if(address != null) {
			if(address.getCivicNumber().isEmpty()) {
				this.address = new Address();
			}
			else {
				this.address = new Address(address.getCivicNumber(), address.getStreetName(), address.getCity());
				this.address.setProvince(address.getProvince());
				this.address.setCode(address.getCode());
			}
		}
		
	}

	/**
	 * 
	 */
	public String toString() {
		return name.toString() + "*" + (address == null ? "" : address.toString());

	}
}
