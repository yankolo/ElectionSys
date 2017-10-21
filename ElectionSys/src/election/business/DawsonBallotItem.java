/**
 * 
 */
package election.business;

import java.io.Serializable;

import election.business.interfaces.BallotItem;
import lib.Email;

/**
 * @author Mohamed Hamza
 *
 */
public class DawsonBallotItem implements BallotItem, Serializable, Comparable<BallotItem> {

	private static final long serialVersionUID = 42031768871L;
	private String choice;
	private int value = 0;
	private final int maxValue;

	/**
	 * The constructor set the choice and the value.
	 * 
	 * @param choice
	 * @param maxValue
	 */
	public DawsonBallotItem(String choice, int maxValue) {
		this.choice = validateChoice(choice);
		this.maxValue = validateMaxValue(maxValue);
	}

	/**
	 * The copy constructor takes a BallotItem and sets the fields according to the
	 * parameter's value.
	 * 
	 * @param item
	 */
	public DawsonBallotItem(BallotItem item) {
		this(item.getChoice(), item.getMaxValue());
	}

	/**
	 * The validateChoice checks if choice is null or empty. Else it returns a
	 * trimmed String.
	 * 
	 * @param choice
	 * @return a trimmed choice.
	 */
	private String validateChoice(String choice) {
		if (choice == null) {
			throw new IllegalArgumentException(
					"DawsonBallotItem Error - Choice must exist. Inavlaid value = " + choice);
		}

		String trimmedChoice = choice.trim();
		if (trimmedChoice.isEmpty()) {
			throw new IllegalArgumentException(
					"DawsonBallotItem Error - Choice must exist. Invalid value = " + trimmedChoice);
		}

		return trimmedChoice;
	}

	/**
	 * The validateMaxValue checks if the maxValue is greater or equal to 0.
	 * 
	 * @param maxValue
	 * @return the maxValue.
	 */
	private int validateMaxValue(int maxValue) {
		if (!(maxValue >= 1)) {
			throw new IllegalArgumentException(
					"DawsonBallotItem Error - maxValue must be geater or equal to 1. Invalid value = " + maxValue);
		}
		return maxValue;
	}

	/**
	 * Compare to method compares the choices of 2 BallotItems Ignoring cases.
	 * @param o
	 * @return an int
	 */
	@Override
	public int compareTo(BallotItem o) {
		if (o == null) {
			throw new IllegalArgumentException("DawsonBallotItem Error - BallotItem must exist. Invalid value = " + o);
		}
		return this.getChoice().compareToIgnoreCase(o.getChoice());

	}

	@Override
	public String getChoice() {
		return this.choice;
	}

	@Override
	public int getMaxValue() {
		return this.maxValue;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	/**
	 * The setValue method sets the value if it's bigger of equal to 0 and smaller
	 * or equal to maxValue
	 * @param value
	 * @return void
	 */
	@Override
	public void setValue(int value) {
		if (!(value >= 0 && value <= this.maxValue)) {
			throw new IllegalArgumentException(
					"DawsonBallotItem Error - value must be >= 0 and <= to maxValue." + " Invalid value = " + value);
		}
		this.value = value;

	}

	/**
	 * The equals method validates first if the object passed is an instance of DawsonBallotItem or a null object.
	 * @param obj
	 * @return boolean by comparing 2 DawsonBallotItems
	 */
	@Override
	public final boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof DawsonBallotItem)) {
			return false;
		}

		DawsonBallotItem itemObj = (DawsonBallotItem) obj;
		if (this.choice.equalsIgnoreCase(itemObj.choice)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public final int hashCode() {
		return choice.toUpperCase().hashCode();
	}

	@Override
	public String toString() {
		return this.choice + "*" + this.value;
	}

}
