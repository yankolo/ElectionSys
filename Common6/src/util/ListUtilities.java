package util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//skeleton provides the imports, plus methods saveListToTextFile and the Comparator sort overload

public class ListUtilities {
	private static final Charset CHARACTER_ENCODING = StandardCharsets.UTF_8;

	// TODO constructor here

	/**
	 * Takes in a list of objects and writes them to a given file. This method
	 * overwrites data in file and uses UTF8 character set.
	 * 
	 * @param objects
	 *            Array of items to be written to file.
	 * @param filename
	 *            filename and location of the file where list of items will be
	 *            written.
	 * @throws IOException
	 *             if an I/O error occurs writing to or creating the file
	 */
	public static void saveListToTextFile(Object[] objects, String filename) throws IOException {
		saveListToTextFile(objects, filename, false, CHARACTER_ENCODING);
	}

	/**
	 * Takes in a list of objects and writes them to a given file. This method uses
	 * UTF8 character set.
	 * 
	 * @param objects
	 *            Array of items to be written to file.
	 * @param filename
	 *            filename and location of the file where list of items will be
	 *            written.
	 * @param append
	 *            boolean indicating if the file is overwritten or if the items are
	 *            written to the end of the file.
	 * @throws IOException
	 *             if an I/O error occurs writing to or creating the file
	 */
	public static void saveListToTextFile(Object[] objects, String filename, boolean append) throws IOException {
		saveListToTextFile(objects, filename, append, CHARACTER_ENCODING);
	}

	/**
	 * Takes in a list of objects and writes them to a given file.
	 * 
	 * @param objects
	 *            Array of items to be written to file.
	 * @param filename
	 *            filename and location of the file where list of items will be
	 *            written.
	 * @param append
	 *            boolean indicating if the file is overwritten or if the items are
	 *            written to the end of the file.
	 * @param characterEncoding
	 *            the Charset to be used when encoding
	 * @throws IOException
	 *             if an I/O error occurs writing to or creating the file
	 */
	public static void saveListToTextFile(Object[] objects, String filename, boolean append, Charset characterEncoding)
			throws IOException {

		Path path = Paths.get(filename);
		OpenOption option;
		if (append)
			option = StandardOpenOption.APPEND;
		else
			option = StandardOpenOption.CREATE;

		// create list of strings
		List<String> toWrite = new ArrayList<String>();
		for (Object obj : objects)
			if (obj != null)
				toWrite.add(obj.toString());

		// write list to file
		Files.write(path, toWrite, characterEncoding, StandardOpenOption.WRITE, option);
	}

	/*
	 * @author Sammy Chaouki
	 * 
	 * Sorts a list of objects in ascending natural order using selection sort.
	 * 
	 * Precondition: Assumes that the list is not null and that the list's capacity
	 * is equal to the list's size.
	 * 
	 *
	 * @param list A list of objects. Assumes that the list's capacity is equal to
	 * the list's size.
	 * 
	 * @throws IllegalArgumentException if the parameter is not full to capacity.
	 *
	 * @throws NullPointerException if the list is null.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] list) throws IllegalArgumentException, NullPointerException {
		if (list == null) {
			throw new NullPointerException("The array that is passed through this method cannot be null referenced");
		}
		if (nullRefenreceElements(list)) {
			throw new IllegalArgumentException("The list array must be filled if you wish to sort it");
		}
		for (int i = 0; i < list.length - 1; i++) {
			int smallIndex = i;
			for (int j = i + 1; j < list.length; j++) {
				if (list[j].compareTo(list[smallIndex]) < 0) {
					smallIndex = j;
				}
			}
			Comparable temp = list[smallIndex];
			list[smallIndex] = list[i];
			list[i] = temp;
		}

	}
	@SuppressWarnings({ "rawtypes" })
	private static boolean nullRefenreceElements(Comparable[] list) {
		for (Comparable element : list) {
			if (element == null) {
				return true;
			}
		}
		return false;
	}
}
