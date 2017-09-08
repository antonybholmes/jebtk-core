/**
 * Copyright 2016 Antony Holmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jebtk.core.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipOutputStream;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.text.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class FileUtils.
 */
public class FileUtils {
	
	/** The default charset. */
	public static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
	
	/** The Constant LOG. */
	protected final static Logger LOG = 
			LoggerFactory.getLogger(FileUtils.class);
	
	/** The Constant COMMON_FILE_HEADERS. */
	public static final Collection<String> COMMON_FILE_HEADERS = 
			new ArrayList<String>();
	
	static {
		COMMON_FILE_HEADERS.add("#");
		COMMON_FILE_HEADERS.add("%");
		COMMON_FILE_HEADERS.add("@");
	}
	
	/**
	 * Instantiates a new file utils.
	 */
	private FileUtils() {
		// Do nothing
	}
	
	/**
	 * Return the user's home directory.
	 *
	 * @return the path
	 */
	public static Path home() {
		return PathUtils.getPath(System.getProperty("user.home"));
	}
	
	/**
	 * Ls.
	 *
	 * @param dir the dir
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<Path> ls(Path dir) throws IOException {
		return ls(dir, true);
	}
	
	/**
	 * Ls.
	 *
	 * @param root the root
	 * @param includeDirs the include dirs
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<Path> ls(Path root, 
			boolean includeDirs) throws IOException {
		return ls(root, includeDirs, false);
	}
	
	/**
	 * List all files in a directory, optionally recursively going through
	 * all sub directories.
	 *
	 * @param root 		The starting directory.
	 * @param includeDirs Whether to include directories in search results.
	 * @param recursive 	Whether to list files in sub directories.
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<Path> ls(Path root, 
			boolean includeDirs,
			boolean recursive) throws IOException {
		if (isFile(root)) {
			return Collections.emptyList();
		}
		
		List<Path> ret = new ArrayList<Path>();
		
		Deque<Path> dirStack = new ArrayDeque<Path>();
		
		dirStack.push(root);
		
		while (!dirStack.isEmpty()) {
			Path dir = dirStack.pop();
			
			for (Path file : Files.newDirectoryStream(dir)) {
				if (FileUtils.isDirectory(file)) {
					if (recursive) {
						dirStack.push(file);
					}
					
					if (includeDirs) {
						ret.add(file);
					}
				} else {
					ret.add(file);
				}
			}
		}
		
		return CollectionUtils.sort(ret);
	}
	
	/**
	 * List just the directories in a directory.
	 *
	 * @param root the root
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<Path> lsdir(Path root) throws IOException {
		return lsdir(root, false);
	}
	
	/**
	 * List just the directories in a directory.
	 *
	 * @param root the root
	 * @param recursive the recursive
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<Path> lsdir(Path root,
			boolean recursive) throws IOException {
		List<Path> ret = new ArrayList<Path>();
		
		Deque<Path> dirStack = new ArrayDeque<Path>();
		
		dirStack.push(root);
		
		while (!dirStack.isEmpty()) {
			Path dir = dirStack.pop();
			
			for (Path file : Files.newDirectoryStream(dir)) {
				if (FileUtils.isDirectory(file)) {
					if (recursive) {
						dirStack.push(file);
					}
					
					ret.add(file);
				}
			}
		}
		
		return CollectionUtils.sort(ret);
	}
	
	/**
	 * Ls.
	 *
	 * @param dir the dir
	 * @param filter the filter
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<Path> ls(Path dir, FileFilter filter) throws IOException {
		List<Path> ret = new ArrayList<Path>();
		
		for (Path path : Files.newDirectoryStream(dir)) {
			if (filter.accept(path.toFile())) {
				ret.add(path);
			}
		}

		return ret;
	}
	
	/**
	 * Finds the first file matching a pattern in a directory and returns
	 * it, or null otherwise. This method is non-recursive.
	 *
	 * @param dir the dir
	 * @param pattern the pattern
	 * @return the path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Path find(Path dir, String pattern) throws IOException {
		for (Path path : ls(dir)) {
			if (path.toString().contains(pattern)) {
				return path;
			}
		}

		return null;
	}
	
	/**
	 * Find the first file whose name ends with a given suffix.
	 * 
	 * @param dir
	 * @param pattern
	 * @return
	 * @throws IOException
	 */
	public static Path endsWith(Path dir, String pattern) throws IOException {
		return endsWith(dir, false, pattern);
	}
	
	/**
	 * Find the first file whose name ends with a given suffix.
	 * 
	 * @param dir			The starting directory.
	 * @param recursive		Whether to search recursively.
	 * @param pattern		The pattern to look for.
	 * 
	 * @return				The first file found or null if search is empty.
	 * @throws IOException
	 */
	public static Path endsWith(Path dir, 
			boolean recursive, 
			String pattern) throws IOException {
		return endsWith(dir, false, false, pattern);
	}
	
	/**
	 * Find the first file whose name ends with a given suffix.
	 * 
	 * @param dir			The starting directory.
	 * @param includeDirs	Whether to include directory names in search.
	 * @param recursive		Whether to search recursively.
	 * @param pattern		The pattern to look for.
	 * 
	 * @return				The first file found or null if search is empty.
	 * @throws IOException
	 */
	public static Path endsWith(Path dir,
			boolean includeDirs,
			boolean recursive,
			String pattern) throws IOException {
		for (Path path : ls(dir, includeDirs, recursive)) {
			if (path.toString().endsWith(pattern)) {
				return path;
			}
		}

		return null;
	}
	
	/**
	 * Find all.
	 *
	 * @param dir the dir
	 * @param patterns the patterns
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<Path> findAll(Path dir, String... patterns) throws IOException {
		return findAll(dir, false, patterns);
	}
	
	/**
	 * Find all.
	 *
	 * @param dir the dir
	 * @param recursive the recursive
	 * @param patterns the patterns
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<Path> findAll(Path dir,
			boolean recursive,
			String... patterns) throws IOException {
		return findAll(dir, false, recursive, patterns);
	}
	
	/**
	 * Find all.
	 *
	 * @param dir the dir
	 * @param includeDirs the include dirs
	 * @param recursive the recursive
	 * @param patterns the patterns
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<Path> findAll(Path dir,
			boolean includeDirs,
			boolean recursive,
			String... patterns) throws IOException {
		List<Path> ret = new ArrayList<Path>();
		
		for (Path path : ls(dir, includeDirs, recursive)) {
			for (String pattern : patterns) {
				if (path.toString().contains(pattern)) {
					ret.add(path);
					break;
				}
			}
		}

		return ret;
	}
	
	/**
	 * Find file matches that match all the patterns.
	 *
	 * @param dir the dir
	 * @param patterns the patterns
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<Path> findMatch(Path dir, String... patterns) throws IOException {
		List<Path> ret = new ArrayList<Path>();
		
		for (Path path : Files.newDirectoryStream(dir)) {
			boolean found = true;
			
			for (String pattern : patterns) {
				if (!path.toString().contains(pattern)) {
					found = false;
					break;
				}
			}
			
			if (found) {
				ret.add(path);
			}
		}

		return ret;
	}

	/**
	 * New buffered writer.
	 *
	 * @param file the file
	 * @return the buffered writer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static BufferedWriter newBufferedWriter(Path file) throws IOException {
		return Files.newBufferedWriter(file, DEFAULT_CHARSET);
	}
	
	/**
	 * New buffered table writer.
	 *
	 * @param file the file
	 * @return the buffered table writer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static BufferedTableWriter newBufferedTableWriter(Path file) throws IOException {
		return new BufferedTableWriter(newFileWriter(file));
	}
	
	/**
	 * New file writer.
	 *
	 * @param file the file
	 * @return the file writer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static FileWriter newFileWriter(Path file) throws IOException {
		return new FileWriter(file.toFile());
	}

	/**
	 * Create a buffered reader from a file. This method will cope with
	 * gzipped files (by name) so can be used for compressed or
	 * uncompressed files.
	 *
	 * @param file 		a file, optionally gzipped.
	 * @return the buffered reader
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static BufferedReader newBufferedReader(Path file) throws IOException {
		if (PathUtils.getName(file).toLowerCase().endsWith("gz")) {
			// Cope with gzipped files
			return newBufferedReader(newBufferedInputStream(file));
		} else {
			return Files.newBufferedReader(file, DEFAULT_CHARSET);
		}
	}
	
	/**
	 * New buffered reader.
	 *
	 * @param stream the stream
	 * @return the buffered reader
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static BufferedReader newBufferedReader(InputStream stream) throws IOException {
		return new BufferedReader(newInputReader(stream));
	}
	
	/**
	 * New input reader.
	 *
	 * @param stream the stream
	 * @return the reader
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Reader newInputReader(InputStream stream) throws IOException {
		return new InputStreamReader(stream, DEFAULT_CHARSET);
	}

	/**
	 * Creates a new buffered input stream.
	 *
	 * @param file the file
	 * @return the input stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static InputStream newBufferedInputStream(Path file) throws IOException {
		return newBufferedInputStream(newInputStream(file));
	}
	
	/**
	 * Returns a buffered input stream.
	 *
	 * @param stream the stream
	 * @return the input stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static InputStream newBufferedInputStream(InputStream stream) throws IOException {
		return new BufferedInputStream(stream);
	}
	
	/**
	 * Creates a new input stream. If the file name ends with the gz ext,
	 * The stream will be automatically wrapped into a GZInputStream.
	 *
	 * @param file the file
	 * @return the input stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static InputStream newInputStream(Path file) throws IOException {
		InputStream inputStream = Files.newInputStream(file);
		
		if (file.getFileName().toString().toLowerCase().endsWith("gz")) {
			// Cope with gzipped files
			inputStream = new GZIPInputStream(inputStream);
		}
		
		return inputStream;
	}
	
	/**
	 * New output stream.
	 *
	 * @param file the file
	 * @return the output stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static OutputStream newOutputStream(Path file) throws IOException {
		return new BufferedOutputStream(Files.newOutputStream(file));
	}

	/**
	 * Checks if is directory.
	 *
	 * @param file the file
	 * @return true, if is directory
	 */
	public static boolean isDirectory(Path file) {
		return exists(file) && Files.isDirectory(file);
	}
	
	/**
	 * Checks if is file.
	 *
	 * @param file the file
	 * @return true, if is file
	 */
	public static boolean isFile(Path file) {
		return !isDirectory(file);
	}

	/**
	 * Returns true if the file exists.
	 *
	 * @param file the file
	 * @return true, if successful
	 */
	public static boolean exists(Path file) {
		if (file != null) {
			return Files.exists(file);
		} else {
			return false;
		}
	}

	/**
	 * Copy.
	 *
	 * @param source the source
	 * @param dest the dest
	 * @return 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static boolean copy(Path source, Path dest) throws IOException {
		Files.copy(source, dest);
		
		return true;
	}
	
	/**
	 * Mv.
	 *
	 * @param source the source
	 * @param dest the dest
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void mv(Path source, Path dest) throws IOException {
		Files.move(source, dest, StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * Makes a new directory if it does not exist. The default behavior is
	 * to create all non-existant parent directories if they do not exist.
	 *
	 * @param dir the dir
	 * @return 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static boolean mkdir(Path dir) throws IOException {
		if (!exists(dir)) {
			LOG.info("Creating directory {}...", dir);
			
			Files.createDirectories(dir);
			
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Rm.
	 *
	 * @param dir the dir
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void rm(Path dir) throws IOException {
		if (!exists(dir) || !isDirectory(dir)) {
			return;
		}
		
		Deque<Path> stack = new ArrayDeque<Path>();

		stack.push(dir);
		
		rm(stack);
	}
	
	/**
	 * Recursively empty a directory, but doesn't delete the directory
	 * itself.
	 *
	 * @param dir the dir
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void empty(Path dir) throws IOException {
		if (!exists(dir) || !isDirectory(dir)) {
			return;
		}
		
		Deque<Path> stack = new ArrayDeque<Path>();

		List<Path> files = ls(dir);
		
		for (Path file : files) {
			stack.push(file);
		}
		
		rm(stack);
	}
	
	/**
	 * Rm.
	 *
	 * @param stack the stack
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void rm(Deque<Path> stack) throws IOException {
		Path path;
		
		while (!stack.isEmpty()) {
			path = stack.pop();
			
			if (isDirectory(path)) {
				List<Path> files = ls(path);
				
				if (files.size() > 0) {
					stack.push(path);
					
					for (Path file : files) {
						stack.push(file);
					}
				} else {
					Files.delete(path);
				}
			} else {
				Files.delete(path);
			}
		}
	}
	
	/**
	 * New gzip input stream.
	 *
	 * @param file the file
	 * @return the input stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static InputStream newGzipInputStream(Path file) throws IOException {
		return new GZIPInputStream(Files.newInputStream(file));
	}

	/**
	 * Write.
	 *
	 * @param path the path
	 * @param bytes the bytes
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void write(Path path, byte[] bytes) throws IOException {
		FileOutputStream stream = new FileOutputStream(path.toFile());
		
		try {
		    stream.write(bytes);
		} finally {
		    stream.close();
		}
	}

	/**
	 * Returns a buffered data input stream on the file.
	 *
	 * @param file the file
	 * @return the data input stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static DataInputStream newDataInputStream(Path file) throws IOException {
		return new DataInputStream(newBufferedInputStream(file));
	}

	/**
	 * New data output stream.
	 *
	 * @param file the file
	 * @return the data output stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static DataOutputStream newDataOutputStream(Path file) throws IOException {
		return new DataOutputStream(newOutputStream(file));
	}

	/**
	 * Returns a new random access file for reading.
	 *
	 * @param file the file
	 * @return the random access file
	 * @throws FileNotFoundException the file not found exception
	 */
	public static RandomAccessFile newRandomAccess(Path file) throws FileNotFoundException {
		return new RandomAccessFile(file.toFile(), "r");
	}
	
	
	/**
	 * Count header lines.
	 *
	 * @param file the file
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static int countHeaderLines(Path file) throws IOException {
		return countHeaderLines(file, COMMON_FILE_HEADERS);
	}
	
	/**
	 * Count the number of header lines in a file where a header line begins
	 * with a given string. Common examples are # or %.
	 *
	 * @param file the file
	 * @param matches the matches
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static int countHeaderLines(Path file, Collection<String> matches) throws IOException {
		int ret = 0;

		BufferedReader reader = FileUtils.newBufferedReader(file);
		
		try {
			String line;
			
			while ((line = reader.readLine()) != null) {
				// Assuming heading lines at beginning of file
				if (!TextUtils.startsWith(line, matches)) {
					break;
				}

				++ret;
			}
		} finally {
			reader.close();
		}
		
		return ret;
	}

	/**
	 * Wrap an output stream into a zip stream.
	 * 
	 * @param output
	 * @return
	 */
	public static ZipOutputStream zip(OutputStream output) {
		return new ZipOutputStream(output);
	}
}
