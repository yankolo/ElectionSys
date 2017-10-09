package election.data;

import java.io.File;

public class SortMergeApp {

	public static void main(String[] args) {
		File sortedDirectory = new File("datafiles/sorted");
		sortedDirectory.mkdir();
		
		File databaseDirectory = new File("datafiles/database");
		databaseDirectory.mkdir();
	}
}
