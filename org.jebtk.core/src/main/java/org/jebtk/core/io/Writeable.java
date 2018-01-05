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
import java.nio.file.Path;

// TODO: Auto-generated Javadoc
/**
 * The interface Writeable.
 */
public interface Writeable {

  /**
   * Writes the contents for file to the string builder so multiple exporters can
   * be amalgamated.
   *
   * @param buffer
   *          the buffer
   */
  void append(StringBuilder buffer);

  /**
   * Writes a file to disk without prompting.
   *
   * @param file
   *          the file
   * @throws IOException
   *           Signals that an I/O exception has occurred.
   */
  void write(Path file) throws IOException;
}
