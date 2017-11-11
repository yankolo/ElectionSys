/**
 * 
 */
package election.business;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import election.business.interfaces.Ballot;
import election.business.interfaces.BallotItem;
import election.business.interfaces.Election;
import election.business.interfaces.Tally;
import election.business.interfaces.Voter;
import util.ListUtilities;

/**
 * 
 * This class represents an Election
 * 
 * @author Sammy Chaouki
 *
 */
public class DawsonElection implements Election {
	private static final long serialVersionUID = 42031768871L;
	private String name;
	private final LocalDate startDate;
	private final LocalDate endDate;
	private String postalCodeStartRange = null;
	private String postalCodeEndRange = null;
	private Tally tally;
	private BallotItem[] ballots;
	private ElectionType electionType;
	private List<Voter> gotBallot;
	private List<Voter> castBallot;
	private int invalidVote;

	/**
	 * The constructor to create an DawsonElection type object Purpose: The purpose
	 * of this constructor is to create an object of type DawsonElection that
	 * instantiate String name - corresponds to the name of the Election a LocalDate
	 * startDate - that takes as its parameters a int year, month , day - a
	 * LocalDate endDate - that takes as its parameters a int year, month , day a
	 * String postalCodeEndRange - if theres is a postal code range, it should not
	 * be null referenced - a String postalCodeEndRange - if theres is a postal code
	 * range, it should not be null referenced - a Tally object - which is used to
	 * track the cast ballot results a BallotItem [] ballots - which contains all
	 * the possible choices in a election a ElectionType (which is enum type)
	 * electionType - which contains the possible type of elections
	 * 
	 * @param name
	 * @param type
	 * @param startYear
	 * @param startMonth
	 * @param startDay
	 * @param endYear
	 * @param endMonth
	 * @param endDay
	 * @param startRange
	 * @param endRange
	 * @param tally
	 * @param items
	 * @throws IllegalArgumentException
	 *             - if any reference variables is null reference (except for
	 *             endRange and Start range). - if any String variable is an empty
	 *             string
	 * 
	 * 
	 */
	public DawsonElection(String name, String type, int startYear, int startMonth, int startDay, int endYear,
			int endMonth, int endDay, String startRange, String endRange, Tally tally, String... items) {
		if (isNull(name)) {
			throw new IllegalArgumentException("The name of the election cannot be null referenced");
		}
		if (name.trim().isEmpty()) {
			throw new IllegalArgumentException("The name of the election cannot be an empty string");
		} else {
			this.name = name;
		}
		if (isNull(type)) {
			throw new IllegalArgumentException("The type of election cannot be a null referenced string");
		}

		if (type.trim().isEmpty()) {
			throw new IllegalArgumentException("The type of electon cannot be an empty string");
		}

		if (!(isNull(startRange))) {
			if (startRange.trim().isEmpty())
				postalCodeStartRange = "A1A 1A1";
		}
		if (!(isNull(endRange))) {
			if (endRange.trim().isEmpty())
				postalCodeEndRange = "Z9Z 9Z9";
		}

		if (validDateRange(startYear, startMonth, startDay, endYear, endMonth, endDay)) {
			throw new DateTimeException(
					"The dates that you have entered to create an Election object are invalid please try again ");
		}
		if (validStartDateAndEndDate(startYear, startMonth, startDay, endYear, endMonth, endDay)) {
			startDate = LocalDate.of(startYear, startMonth, startMonth);
			endDate = LocalDate.of(endYear, endMonth, endDay);
		} else {
			throw new IllegalArgumentException(
					"The end day of the election cannot be before the start date of the election ");
		}
		if (validElectionType(type)) {
			this.electionType = ElectionType.valueOf(type.trim().toUpperCase());
		} else {
			throw new IllegalArgumentException("You entered a invalid type of Election.");
		}
		if (isNull(tally)) {
			throw new IllegalArgumentException("A tally object cannot be null referenced.");
		} else {
			this.tally = tally;
		}
		if (isNull(items)) {
			throw new IllegalArgumentException("The Ballot items cannot be null referenced.");
		}

		if (validBallotItems(items)) {
			ballots = new BallotItem[items.length];
			for (int i = 0; i < items.length; i++) {
				ballots[i] = DawsonElectionFactory.DAWSON_ELECTION.getBallotItem(items[i], this.electionType,
						items.length);
			}
		} else {
			throw new IllegalArgumentException("The minimum for the Ballot items must be of length 2 or more.");
		}
		this.postalCodeEndRange = endRange;
		this.postalCodeStartRange = startRange;
		gotBallot = new ArrayList<Voter>();
		castBallot = new ArrayList<Voter>();
		invalidVote = 0;
	}

	/**
	 * Overridden compareTo method, returns a integer value from the compareTo
	 * between this name and the parameters name
	 * 
	 * @param o
	 *            - only Election type objects can be passed to this method
	 * @throws IllegalArgumentException
	 *             - null referenced Election o
	 * @return integer value of the comparTo method of this name and the parameters
	 *         name
	 */
	@Override
	public int compareTo(Election o) throws IllegalArgumentException {
		if (isNull(o)) {
			throw new IllegalArgumentException(
					"In the compareTo method, the parameter passed cannot be null referenced");
		}
		return this.name.compareToIgnoreCase(o.getName());
	}

	/**
	 * This method is a simple getter that returns the Election type of
	 * DawsonElection object
	 * 
	 * @return electionType - returns ElectionType enum
	 */
	@Override
	public ElectionType getElectionType() {
		// TODO Auto-generated method stub
		return this.electionType;
	}

	/**
	 * Simple get method that returns a string array that contains all the possible
	 * election choices of DawsonElection object
	 * 
	 * @return electionChoices
	 */
	@Override
	public String[] getElectionChoices() {
		String[] electionChoices = new String[this.ballots.length];
		for (int i = 0; i < electionChoices.length; i++) {
			electionChoices[i] = this.ballots[i].getChoice();
		}
		return electionChoices;
	}

	/**
	 * Simple get method that returns the date when the Election will end
	 * 
	 * @return this.endDate
	 */
	@Override
	public LocalDate getEndDate() {

		return this.endDate;
	}

	/**
	 * Simple get method that returns the date when the Election will start
	 * 
	 * @return this.startDate
	 */
	@Override
	public LocalDate getStartDate() {
		return this.startDate;
	}

	/**
	 * Simple get method that returns the postal end range Any postal code ending
	 * with that string
	 * 
	 * @return this.postalCodeEndRange
	 */
	@Override
	public String getPostalRangeEnd() {
		// TODO Auto-generated method stub
		return this.postalCodeEndRange;
	}

	/**
	 * Simple get method that returns the postal start range Any postal code
	 * starting with that string
	 * 
	 * @return this.postalCodeStartRange
	 */
	@Override
	public String getPostalRangeStart() {

		return this.postalCodeStartRange;
	}

	/**
	 * This method checks if the both the start and end postal range are null
	 * referenced, if both are null referenced, there is no limit for the postal
	 * code range, vice verse.
	 * 
	 * @return true/false
	 */
	@Override
	public boolean isLimitedToPostalRange() {
		// TODO Auto-generated method stub
		if (isNull(getPostalRangeEnd()) || isNull(getPostalRangeStart())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Simple get method that returns the name instance field
	 * 
	 * @return this.name
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	/**
	 * Simple get method that returns the tally instance field
	 * 
	 * @return this.tally
	 */
	@Override
	public Tally getTally() {
		// TODO Auto-generated method stub
		return this.tally;
	}

	/**
	 * Set method for Tally. Will set the instance variable tally to the
	 * parameter,if and only if, they have the same election name. Otherwise throws
	 * IllegalArgumentException
	 * 
	 * @param tally
	 * @throws IllegalArgumentException
	 *             if the parameter is null referenced
	 * @throws IllegalArgumentException
	 *             if the election name of the tally parameter is not equal to the
	 *             election name of the instance variable tally.
	 */
	@Override
	public void setTally(Tally tally) {
		if (isNull(tally)) {
			throw new IllegalArgumentException(
					"You cannot set a null referenced Tally object to the tally instance field of the "
							+ " Election object");
		}
		if (this.getTally().getElectionName().equalsIgnoreCase(tally.getElectionName())) {
			this.tally = tally;
		} else {
			throw new IllegalArgumentException("The tally that you have passed through the setTally method "
					+ " has a different ElectionName compared to the tally instance variable of the Election"
					+ " class ");
		}

	}

	/**
	 * The getBallot method will check if the voter that is being passed is eligible
	 * for the current election. If it is eligible, it will check if the voter has
	 * requested a ballot previously. If not it will be added to a list of all
	 * voters who have requested a a ballot. If the voter has previously asked a
	 * ballot, it will check if the voter has already casted a ballot. It will then
	 * return a ballot object
	 * 
	 * @author Sammy Chaouki
	 * @param Voter
	 *            v
	 * @throws IllegalArgumentException
	 *             -- If the voter that is passed to this method is null referenced
	 * @throws InvalidVoterException
	 *             -- If the voter is not eligible for this election
	 * @throws InvalidVoterException
	 *             -- If the voter that is passed through has already cast a ballot
	 * @return bt
	 */

	@Override
	public Ballot getBallot(Voter v) throws InvalidVoterException, IllegalArgumentException {
		int placementGotBallot;
		int placementCastBallot;
		if (isNull(v)) {
			throw new IllegalArgumentException("The voter passed through the getBallot cannot be null referenced ");
		} else {

			if (v.isEligible(this)) {
				placementGotBallot = ListUtilities.binarySearch(gotBallot, v, 0, gotBallot.size() - 1);
				if (!gotBallot(placementGotBallot)) {
					placementGotBallot = (placementGotBallot * -1) - 1;
					gotBallot.add(placementGotBallot, v);
				} else {
					placementCastBallot = ListUtilities.binarySearch(castBallot, v, 0, castBallot.size() - 1);
					if (ballotCasted(placementCastBallot)) {
						throw new InvalidVoterException("The following voter " + v.toString()
								+ ": has already cast a ballot for this eleciton: " + this.getName());
					}
				}
			} else {
				throw new InvalidVoterException("The following voter " + v.toString()
						+ ": is not eligible for this eleciton: " + this.getName());
			}

			BallotItem[] bi = creatBallotArray(this.ballots);
			Ballot bt = DawsonElectionFactory.DAWSON_ELECTION.getBallot(bi, getElectionType(), this);
			return bt;
		}

	}

	/**
	 * The castBallot method will check if a voter and their corresponding ballot
	 * are valid for cast. For the voter to be able to cast his ballot, he must be
	 * eligible for this election and have requested a ballot before casting their
	 * vote. If the voter has already cast his ballot and tries to recast, it will
	 * result to a invalid vote attempt. This concept is applied each time a
	 * InvalidVoterException is thrown.
	 * 
	 * @param b
	 * @param v
	 * @throws InvalidVoterException
	 * @throws IllegalArgumentException
	 */

	@Override
	public void castBallot(Ballot b, Voter v) throws InvalidVoterException, IllegalArgumentException {
		int placementGotBallot;
		int placementCastBallot;
		if (isNull(v)) {
			throw new IllegalArgumentException("Voter v cannot be null referenced - castBallot");
		}
		if (v.isEligible(this)) {
			if (isNull(b)) {
				throw new IllegalArgumentException(
						"In the castBallot method a ballot passed through the parameters cannot be null referenced");
			}

			placementGotBallot = ListUtilities.binarySearch(gotBallot, v, 0, gotBallot.size() - 1);
			if (gotBallot(placementGotBallot)) {
				placementCastBallot = ListUtilities.binarySearch(castBallot, v, 0, castBallot.size() - 1);
				if (ballotCasted(placementCastBallot)) {
					invalidVote++;
					throw new InvalidVoterException("The following voter \n " + "\t" + v.toString() + " \n \t"
							+ "has already cast a ballot in the following election: " + this.getName());
				} else {
					placementCastBallot = (placementCastBallot * -1) - 1;
					castBallot.add(placementCastBallot, v);
					if (b.validateSelections()) {
						this.tally.update(b);
					} else {

						throw new IllegalArgumentException("The ballot of the following voter \n" + "\t" + v.toString()
								+ " \n \t"
								+ "is not filled correctly, making it a invalid ballot. The tally will not be updated");
					}
				}

			} else {
				invalidVote++;
				throw new InvalidVoterException("The following voter \n " + "\t" + v.toString()
						+ "\n \thas not requested a ballot for this eleciton: " + this.getName());
			}

		} else {
			invalidVote++;
			throw new InvalidVoterException("The following voter \n " + "\t" + v.toString()
					+ "\n \tis not eligible for this eleciton: " + this.getName());
		}
	}

	/**
	 * Returns the number of cast ballots for this election
	 * 
	 * @return castBallot.size()
	 */
	@Override
	public int getTotalVotesCast() {
		return castBallot.size();
	}

	/**
	 * Returns the number of invalid votes that occurred during an election
	 * 
	 * @return invalidVote -- which represents the number of invalid vote attempts
	 */
	@Override
	public int getInvalidVoteAttempts() {
		return invalidVote;
	}

	/**
	 * Overridden equals method for the DawsonElection class, Objects are considered
	 * equal if they are of the same class and have the same name
	 * 
	 * @param obj
	 *            - a object that may be equal to DawsonElection object
	 * @return true/false
	 */
	@Override
	public final boolean equals(Object obj) {
		if (isNull(obj)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Election)) {
			return false;
		}
		Election other = (Election) obj;

		if (other.getName().equalsIgnoreCase(this.getName())) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Overridden hashCode method for the DawsonElection Class
	 * 
	 * @return the hash code of both the class of the object as well as the name of
	 *         the election
	 */
	@Override
	public final int hashCode() {
		return this.name.toUpperCase().hashCode();
	}

	/**
	 * Overridden toString method for the DawsonElection class
	 * 
	 * @return output - it returns a formated string with the name of the election,
	 *         the start date the end date of the election, the postal range (if
	 *         there is any) the election type and all the ballot choices you can
	 *         have, as well as the number of ballots
	 */
	@Override
	public String toString() {
		String output = this.getName() + "*" + this.getStartDate().getYear() + "*" + this.getStartDate().getMonthValue()
				+ "*" + this.getStartDate().getDayOfMonth() + "*" + this.getEndDate().getYear() + "*"
				+ this.getEndDate().getMonthValue() + "*" + this.getEndDate().getDayOfMonth() + "*"
				+ ((isNull(this.getPostalRangeStart())) ? "" : this.getPostalRangeStart()) + "*"
				+ ((isNull(this.getPostalRangeEnd())) ? "" : this.getPostalRangeEnd()) + "*"
				+ this.getElectionType().toString() + "*" + this.ballots.length;
		String[] ballotItems = this.getElectionChoices();
		for (int i = 0; i < ballotItems.length; i++) {
			output += "\n" + ballotItems[i];
		}
		return output;

	}

	/**
	 * This method will check if the dates that the user entered are valid dates
	 * 
	 * @param startYear
	 *            - year when the election will start
	 * @param startMonth
	 *            - month when the election will start
	 * @param startDay
	 *            - day when the election will start
	 * @param endYear
	 *            - year when the election will end
	 * @param endMonth
	 *            - month when the election will end
	 * @param endDay
	 *            - day when the election will end
	 * @return true/false
	 */
	private boolean validDateRange(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay) {
		try {
			// trying to see if the start date is a valid date
			LocalDate.of(startYear, startMonth, startDay);
			LocalDate.of(endYear, endMonth, endDay);
			return false;
		} catch (DateTimeException dte) {
			return true;
		}

	}

	/**
	 * This method will check if the date when the election date is either equal to
	 * the date when the election starts or if both are equal. If any of these
	 * conditions are meet, the method would return false as it is an illegal
	 * argument. If not, both dates valid, and ready to be used.
	 * 
	 * @param startYear
	 *            - year when the election will start
	 * @param startMonth
	 *            - month when the election will start
	 * @param startDay
	 *            - day when the election will start
	 * @param endYear
	 *            - year when the election will end
	 * @param endMonth
	 *            - month when the election will end
	 * @param endDay
	 *            - day when the election will end
	 * @return true/false
	 */
	private boolean validStartDateAndEndDate(int startYear, int startMonth, int startDay, int endYear, int endMonth,
			int endDay) {
		LocalDate start = LocalDate.of(startYear, startMonth, startDay);
		LocalDate end = LocalDate.of(endYear, endMonth, endDay);
		if (start.isAfter(end)) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * This method will check if the type the user has entered is a valid election
	 * type, if not this method return false
	 * 
	 * @param type
	 *            is a string that refers to one of the both choice one can have
	 *            when it comes to possible election types
	 * @return true/false
	 */
	private boolean validElectionType(String type) {
		try {
			ElectionType.valueOf(type.trim().toUpperCase());
			return true;
		} catch (IllegalArgumentException iae) {
			return false;
		}

	}

	/**
	 * This methods checks if the items array is a valid array depending on its
	 * length if it has a length less than two, then it should be a invalid array
	 * size
	 * 
	 * @param items
	 *            is an array of strings that contains all the possible ballot items
	 * @return true/false depending on the length of the items array length
	 */
	private boolean validBallotItems(String... items) {
		if (items.length < 2) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * This is just a simple method to create and return an array of BallotItems
	 * that contains DawsonBallotItem objects
	 * 
	 * @param bi
	 * @return returns a deep copy of a ballot item array
	 */
	private BallotItem[] creatBallotArray(BallotItem[] bi) {
		BallotItem[] array = new BallotItem[bi.length];
		for (int i = 0; i < bi.length; i++) {
			array[i] = DawsonElectionFactory.DAWSON_ELECTION.getBallotItem(bi[i]);
		}
		return array;
	}

	/**
	 * Checks if the object that is passed through this method is null referenced
	 * Precondition:
	 * 
	 * @param obj
	 *            Any object
	 * @return true/false if the obj parameter is null referenced
	 */
	private boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Returns true if the user has yet to cast his ballot Returns false if he has
	 * cast his ballot
	 * 
	 * @param placementCastBallot
	 * @param v
	 * @return
	 */
	private boolean ballotCasted(int placementCastBallot) {
		if (placementCastBallot >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Return true if the voter has not requested a ballot Return false if the voter
	 * has already requested a ballot
	 * 
	 * @param placementGotBallot
	 * @param v
	 * @return
	 */
	private boolean gotBallot(int placementGotBallot) {
		if (placementGotBallot >= 0) {
			return true;
		} else {
			return false;
		}
	}

}
