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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

import org.jebtk.core.text.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * Methods for dealing with temporary files.
 * 
 * @author Antony Holmes Holmes
 *
 */
public class Temp {

  /**
   * The constant TEMP_DIRECTORY.
   */
  public static final Path TEMP_DIRECTORY = PathUtils.getPath("tmp");

  /**
   * The next id.
   */
  private static AtomicInteger NEXT_ID = new AtomicInteger(1);

  /**
   * The constant LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(Temp.class);

  /**
   * Creates a temp directory if it does not exist.
   *
   * @return true, if successful
   */
  public static boolean createTempDirectory() {
    if (FileUtils.exists(TEMP_DIRECTORY)) {
      return false;
    }

    LOG.info("Creating tmp directory at {}...", TEMP_DIRECTORY);

    try {
      Files.createDirectory(TEMP_DIRECTORY);

      return true;
    } catch (IOException e) {
      e.printStackTrace();

      return false;
    }
  }

  /**
   * Generates a tmp file name with a unique id for this session. Ids are not
   * guaranteed to be unique if the program or VM are restarted.
   *
   * @return the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static Path generateTempFile() throws IOException {
    return generateTempFile("tmp");
  }

  /**
   * Generate a temp file with the given extension.
   *
   * @param ext the ext
   * @return the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static Path generateTempFile(String ext) throws IOException {
    createTempDirectory();

    return TEMP_DIRECTORY
        .resolve(TextUtils.paste("t", NEXT_ID.getAndIncrement(), ".", ext));
  }

  /**
   * Delete files from the temporary directory.
   */
  public static void deleteTempFiles() {
    deleteTempFiles(null);
  }

  /**
   * Delete temp files.
   *
   * @param name the name
   */
  public static void deleteTempFiles(String name) {
    if (!FileUtils.exists(TEMP_DIRECTORY)) {
      return;
    }

    LOG.info("Deleting temp files from {}...", TEMP_DIRECTORY);

    try {
      for (Path file : FileUtils.ls(TEMP_DIRECTORY)) {
        if (name != null && !name.equals("")
            && !PathUtils.getName(file).contains(name)) {
          continue;
        }

        Files.delete(file);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates the temp file.
   *
   * @param name the name
   * @return the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static Path createTempFile(String name) throws IOException {
    return createTempFile(TEMP_DIRECTORY, name);
  }

  /**
   * Creates a valid, random, disposable file name.
   *
   * @param tempDirectory the temp directory
   * @param name the name
   * @return the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static Path createTempFile(Path tempDirectory, String name)
      throws IOException {
    // ensure the temp directory exists
    FileUtils.mkdir(tempDirectory);

    return tempDirectory.resolve(name);
  }
}
