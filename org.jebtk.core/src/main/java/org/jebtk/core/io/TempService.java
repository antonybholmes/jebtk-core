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
import java.security.SecureRandom;

import org.jebtk.core.text.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Methods for dealing with temporary files.
 * 
 * @author Antony Holmes Holmes
 *
 */
public class TempService {

  private static class TempServiceLoader {

    /** The Constant INSTANCE. */
    private static final TempService INSTANCE = new TempService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static TempService getInstance() {
    return TempServiceLoader.INSTANCE;
  }
  
  private static final SecureRandom RANDOM = new SecureRandom();
  
  private static final String RND_TMP_LETTERS = 
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  
  
  /**
   * The constant TEMP_DIRECTORY.
   */
  public static final Path TMP_DIR = PathUtils.getPath("tmp");

  /**
   * The constant LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(TempService.class);

  private Path mTmpDir;

  private TempService() {
    mTmpDir = TMP_DIR;
  }
  

  public Path getTmpDir() {
    return mTmpDir;
  }
  
  /**
   * Creates a temp directory if it does not exist.
   *
   * @return true, if successful
   */
  public boolean createTmpDir() {
    if (FileUtils.exists(mTmpDir)) {
      return false;
    }

    LOG.info("Creating tmp directory at {}...", mTmpDir);

    try {
      Files.createDirectory(mTmpDir);

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
  public Path generateTmpFile() throws IOException {
    return generateTmpFile("tmp");
  }

  /**
   * Generate a temp file with the given extension.
   *
   * @param ext the ext
   * @return the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public Path generateTmpFile(String ext) throws IOException {
    createTmpDir();

    return mTmpDir
        .resolve(TextUtils.paste(generateTmpString(), ".", ext));
  }
  
  public Path generateTmpFile(String prefix, String ext) throws IOException {
    createTmpDir();

    return mTmpDir
        .resolve(TextUtils.paste(prefix, ".", generateTmpString(), ".", ext));
  }

  /**
   * Delete files from the temporary directory.
   */
  public void deleteTempFiles() {
    deleteTempFiles(null);
  }

  /**
   * Delete temp files.
   *
   * @param name the name
   */
  public void deleteTempFiles(String name) {
    if (!FileUtils.exists(mTmpDir)) {
      return;
    }

    LOG.info("Deleting temp files from {}...", mTmpDir);

    try {
      for (Path file : FileUtils.ls(mTmpDir)) {
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
  
  private static String generateTmpString() {
    return generateTmpString(6);
  }
  
  private static String generateTmpString(int l) {
      StringBuilder buffer = new StringBuilder();
      
      for (int i = 0; i < l; ++i) {
        buffer.append(RND_TMP_LETTERS.charAt(RANDOM.nextInt(RND_TMP_LETTERS.length())));
      }
      
      return buffer.toString();
  }

}
