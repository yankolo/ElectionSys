package election.data;

import java.io.IOException;
import java.util.List;

import election.business.DawsonElectionFactory;
import election.business.interfaces.Election;
import election.business.interfaces.ElectionFactory;
import election.data.interfaces.ElectionDAO;
import election.data.interfaces.ListPersistenceObject;
import util.ListUtilities;

/**
 * 
 * @author Mohamed Hamza
 *
 */
public class ElectionListDB implements ElectionDAO {

	private List<Election> database;
	private final ListPersistenceObject listPersistenceObject;
	private final ElectionFactory factory;

	/**
	 * Initializes the database using a ListPersistenceObject
	 * 
	 * @param listPersistenceObject
	 */
	public ElectionListDB(ListPersistenceObject listPersistenceObject) {
		this.database = listPersistenceObject.getElectionDatabase();
		this.listPersistenceObject = listPersistenceObject;
		this.factory = DawsonElectionFactory.DAWSON_ELECTION;
	}

	/**
	 * Initializes the database using a ListPersistenceObject and sets a different
	 * ElectionFactory (factory class used to initialize objects)
	 * 
	 * @param listPersistenceObject
	 *            The object that holds the file paths (for the databases)
	 * @param factory
	 *            Factory class used to initialize objects
	 */
	public ElectionListDB(ListPersistenceObject listPersistenceObject, ElectionFactory factory) {
		this.database = listPersistenceObject.getElectionDatabase();
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
	}

	/**
	 * Adds an election to the database. The election is added in natural sort order
	 * to keep the database sorted. Only one election can be inserted for a given
	 * name string.
	 * 
	 * @param election
	 *            The election to add to the database.
	 * @throws DuplicateElectionException;
	 *             The election's name already exists.
	 */
	@Override
	public void add(Election election) throws DuplicateElectionException {
		int indexOfElection = ListUtilities.binarySearch(database, election, 0, database.size() - 1);
		if (indexOfElection >= 0) {
			throw new DuplicateElectionException();
		}

		indexOfElection = (indexOfElection * -1) - 1;
		database.add(indexOfElection, factory.getElectionInstance(election));
	}

	/**
	 * Saves the list of elections and disconnects from the database.
	 * 
	 * @throws IOException
	 *             Problems saving or disconnecting from database.
	 */
	@Override
	public void disconnect() throws IOException {
		listPersistenceObject.saveElectionDatabase(database);

	}

	/**
	 * Returns the election with the specified name.
	 * 
	 * @param name
	 *            The name of the requested election.
	 *
	 * @return The election with the specified name.
	 *
	 * @throws InexistentElectionException
	 *             If there is no election with the specified name.
	 */
	@Override
	public Election getElection(String name) throws InexistentElectionException {
		if (database.size() == 0) {
			throw new InexistentElectionException(
					"The Election with the name " + name + " does not exist in the databse");
		}
		Election firstElection = database.get(0);
		String type = firstElection.getElectionType().toString();
		int startYear = firstElection.getStartDate().getYear();
		int startMonth = firstElection.getStartDate().getMonthValue();
		int startDay = firstElection.getStartDate().getDayOfMonth();
		int endYear = firstElection.getEndDate().getYear();
		int endMonth = firstElection.getEndDate().getMonthValue();
		int endDay = firstElection.getEndDate().getDayOfMonth();
		String startRange = firstElection.getPostalRangeStart();
		String endRange = firstElection.getPostalRangeEnd();
		String[] choices = firstElection.getElectionChoices();

		Election dummyElection = factory.getElectionInstance(name, type, startYear, startMonth, startDay, endYear,
				endMonth, endDay, startRange, endRange, choices);

		int indexOfElection = ListUtilities.binarySearch(database, dummyElection, 0, database.size() - 1);

		if (indexOfElection < 0) {
			throw new InexistentElectionException(
					"The Election with the name " + name + " does not exist in the databse");
		}
		return database.get(indexOfElection);
	}

	/**
	 * @return It returns a String with the number of ELections in the database and
	 *         the Elections.
	 */
	@Override
	public String toString() {
		String electionListDB = "Number of elections in the database: " + database.size();
		for (Election election : database) {
			electionListDB += "\n" + election;
		}
		return electionListDB;
	}
}
