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
package org.jebtk.core.cli;

// TODO: Auto-generated Javadoc
/**
 * Specifies an expected argument on the command line.
 * 
 * @author Antony Holmes Holmes
 *
 */
public class CommandLineOption {

  /**
   * The member short name.
   */
  private char mShortName;

  /**
   * The member long name.
   */
  private String mLongName;

  /**
   * The member has arg.
   */
  private boolean mHasArg;

  /**
   * The member description.
   */
  private String mDescription;

  /**
   * Instantiates a new command line option.
   *
   * @param shortName
   *          the short name
   * @param hasArg
   *          the has arg
   * @param description
   *          the description
   */
  public CommandLineOption(char shortName, boolean hasArg, String description) {
    this(shortName, null, hasArg, description);
  }

  /**
   * Instantiates a new command line option.
   *
   * @param shortName
   *          the short name
   * @param longName
   *          the long name
   * @param hasArg
   *          the has arg
   * @param description
   *          the description
   */
  public CommandLineOption(char shortName, String longName, boolean hasArg, String description) {
    mShortName = shortName;
    mLongName = longName;
    mHasArg = hasArg;
    mDescription = description;
  }

  /**
   * Gets the short name.
   *
   * @return the short name
   */
  public char getShortName() {
    return mShortName;
  }

  /**
   * Gets the long name.
   *
   * @return the long name
   */
  public String getLongName() {
    return mLongName;
  }

  /**
   * Checks for arg.
   *
   * @return true, if successful
   */
  public boolean hasArg() {
    return mHasArg;
  }

  /**
   * Gets the description.
   *
   * @return the description
   */
  public String getDescription() {
    return mDescription;
  }
}
