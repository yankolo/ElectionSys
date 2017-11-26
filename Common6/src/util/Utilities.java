/**
 * 
 */
package util;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Yanik, Mohamed
 *
 */
public class Utilities {
	/**
	 * Serializes an object to the specified file
	 * 
	 * @param object The object to serialize
	 * @param fileSpecification The path of the file to which serialize the object
	 * @throws IOException Throws exception if there is a problem writing to the file
	 */
	public static void serializeObject(Object object, String fileSpecification) throws IOException {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileSpecification));

			out.writeObject(object);

		} catch (IOException e) {
			// normally the exception would be logged to file then thrown
			throw new IOException("Error serializing object to \n" + fileSpecification + " " + e);
		} finally {
			if (out != null)
				out.close();
		}
	}

	/**
	 * Deserializes an object from a file
	 * 
	 * @param fileSpecification The path of the file to deserialize
	 * @return The deserialized object
	 * @throws IOException Throws exception if there is a problem reading the file
	 * @throws ClassNotFoundException Throws exception if there is no class that represents the object
	 */
	public static Object deserializeObject(String fileSpecification) throws IOException, ClassNotFoundException {

		ObjectInputStream in = null;
		try {
			Object obj = null;
			in = new ObjectInputStream(new FileInputStream(fileSpecification));
			if (in != null)
				obj = in.readObject();

			return obj;
		} catch (ClassNotFoundException | IOException e) {
			// normally the exception would be logged to file then thrown
			throw new IOException("Error deserializing object from " + fileSpecification + "\n" + e);
		} finally {
			if (in != null)
				in.close();
		}
	}

}
