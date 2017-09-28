/**
 * 
 */
package election.business;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.PrimitiveIterator.OfDouble;

import election.business.interfaces.Ballot;
import election.business.interfaces.BallotItem;
import election.business.interfaces.Election;
import election.business.interfaces.Tally;
import election.business.interfaces.Voter;

/**
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

	// used to create Ballot Items;
	// do i need to create ballot item;
	public DawsonElection(String name, String type, int startYear, int startMonth, int startDay, int endYear,
			int endMonth, int endDay, String startRange, String endRange, Tally tally, String... items) {
		if (isNull(name)) {
			throw new IllegalArgumentException("The name of the election cannot be null referenced");
		}
		if (name.trim().isEmpty()) {
			throw new IllegalArgumentException("The name of the election cannot be an empty string");
		}
		if (isNull(type)) {
			throw new IllegalArgumentException("The type of election cannot be a null referenced string");
		}

		if (type.trim().isEmpty()) {
			throw new IllegalArgumentException("The type of electon cannot be an empty string");
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
				ballots[i] = new DawsonBallotItem(items[i], items.length);
			}
		} else {
			throw new IllegalArgumentException("The minimum for the Ballot items must be of length 2 or more.");
		}
		this.name = name;

	}

	@Override
	public int compareTo(Election o) {
		return this.name.compareToIgnoreCase(o.getName());
	}

	@Override
	public ElectionType getElectionType() {
		// TODO Auto-generated method stub
		return electionType;
	}

	@Override
	public String[] getElectionChoices() {
		String[] electionChoices = new String[this.ballots.length];
		for (int i = 0; i < electionChoices.length; i++) {
			electionChoices[i] = this.ballots[i].getChoice();
		}
		return electionChoices;
	}

	@Override
	public LocalDate getEndDate() {

		return this.endDate;
	}

	@Override
	public LocalDate getStartDate() {
		return this.startDate;
	}

	@Override
	public String getPostalRangeEnd() {
		// TODO Auto-generated method stub
		return this.postalCodeEndRange;
	}

	@Override
	public String getPostalRangeStart() {

		return this.postalCodeStartRange;
	}

	@Override
	public boolean isLimitedToPostalRange() {
		// TODO Auto-generated method stub
		if (isNull(getPostalRangeEnd()) && isNull(getPostalRangeStart())) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public Tally getTally() {
		// TODO Auto-generated method stub
		return this.tally;
	}

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

	@Override
	public Ballot getBallot(Voter v) {
		// TODO Auto-generated method stub
		BallotItem[] bi = deepCopy(this.ballots);
		StubBallot sb = new StubBallot(bi, this);
		return sb;
	}

	@Override
	public void castBallot(Ballot b, Voter v) {
		// TODO Auto-generated method stub
		if (b.validateSelections()) {
			this.tally.update(b);
		}
	}

	@Override
	public int getTotalVotesCast() {
		throw new UnsupportedOperationException(
				"This method ( -- getInvalidVoteAttempts() -- ) have yet to be implemented and used during this phase, should throw exception"
						+ " of type UnsupportedOperationException");
	}

	@Override
	public int getInvalidVoteAttempts() {
		throw new UnsupportedOperationException(
				"This method ( -- getInvalidVoteAttempts() -- ) have yet to be implemented and used during this phase, should throw exception"
						+ " of type UnsupportedOperationException");
	}

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

		if (other.getClass() == this.getClass() && other.getName().equalsIgnoreCase(this.getName())) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public final int hashCode() {
		return this.getClass().hashCode() + this.name.toUpperCase().hashCode();
	}

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

	private boolean validStartDateAndEndDate(int startYear, int startMonth, int startDay, int endYear, int endMonth,
			int endDay) {
		LocalDate start = LocalDate.of(startYear, startMonth, startDay);
		LocalDate end = LocalDate.of(endYear, endMonth, endDay);
		if (end.isAfter(start) || start.isEqual(end)) {
			return false;
		} else {
			return true;
		}

	}

	private boolean validElectionType(String type) {
		try {
			ElectionType.valueOf(type.trim().toUpperCase());
			return true;
		} catch (IllegalArgumentException iae) {
			return false;
		}

	}

	private boolean validBallotItems(String... items) {
		if (items.length < 2) {
			return false;
		} else {
			return true;
		}
	}

	private BallotItem[] deepCopy(BallotItem[] bi) {
		BallotItem[] deepCopy = new BallotItem[bi.length];
		for (int i = 0; i < bi.length; i++) {
			deepCopy[i] = new DawsonBallotItem(bi[i]);
		}
		return deepCopy;
	}

	private boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		} else {
			return false;
		}

	}

}
