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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

// TODO: Auto-generated Javadoc
/**
 * Creates zip files.
 *
 * @author Antony Holmes Holmes
 *
 */
public class Zip {

  /**
   * The buffer.
   */
  private static byte[] BUFFER = new byte[1024];

  /**
   * Instantiates a new zip.
   */
  private Zip() {
    // do nothing
  }

  /**
   * Create a zip file from a list of files.
   *
   * @param outFile the out file
   * @param files the files
   * @throws Exception {
   */
  public static void createZip(File outFile, List<File> files)
      throws Exception {
    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFile));

    for (File file : files) {
      FileInputStream in = new FileInputStream(file);

      out.putNextEntry(new ZipEntry(file.getAbsolutePath()));

      int len;

      while ((len = in.read(BUFFER)) > 0) {
        out.write(BUFFER, 0, len);
      }

      out.closeEntry();

      in.close();
    }

    out.close();
  }
}
