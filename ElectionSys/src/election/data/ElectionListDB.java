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

	public ElectionListDB(ListPersistenceObject listPersistenceObject) {
		this.database = listPersistenceObject.getElectionDatabase();
		this.listPersistenceObject = listPersistenceObject;
		this.factory = DawsonElectionFactory.DAWSON_ELECTION;
	}

	public ElectionListDB(ListPersistenceObject listPersistenceObject, ElectionFactory factory) {
		this.database = listPersistenceObject.getElectionDatabase();
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
	}

	@Override
	public void add(Election election) throws DuplicateElectionException {
	}

	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Election getElection(String name) throws InexistentElectionException {
		if (database.size() == 0) {
			throw new InexistentElectionException(
					"The Election with the name " + name + " does not exist in the databse");
		}
		Election firstElection = database.get(0);
		String dummyName = name;
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

		Election dummyElection = factory.getElectionInstance(dummyName, type, startYear, startMonth, startDay, endYear,
				endMonth, endDay, startRange, endRange, choices);

		int indexOfElection = ListUtilities.binarySearch(database, dummyElection, 0, database.size() - 1);

		if (indexOfElection < 0) {
			throw new InexistentElectionException(
					"The Election with the name " + name + " does not exist in the databse");
		}
		return database.get(indexOfElection);
	}
	
	@Override
	public String toString() {
		String electionListDB = "Number of elections in the database: " + database.size();
		for(Election election : database) {
			electionListDB += "\n" + election;
		}
		return electionListDB;
	}
}
