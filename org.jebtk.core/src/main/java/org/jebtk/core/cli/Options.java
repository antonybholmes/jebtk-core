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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jebtk.core.text.TextUtils;

/**
 * The Class Options.
 */
public class Options implements Iterable<CommandLineOption> {

  /**
   * The member options.
   */
  private List<CommandLineOption> mOptions = new ArrayList<CommandLineOption>();

  /**
   * Instantiates a new options.
   */
  public Options() {
    add('h', "help");
  }

  /**
   * Adds the.
   *
   * @param shortName the short name
   * @param longName the long name
   * @return 
   */
  public Options add(char shortName, String longName) {
    return add(shortName, longName, false);
  }

  /**
   * Adds the.
   *
   * @param shortName the short name
   * @param longName the long name
   * @param hasArg the has arg
   * @return 
   */
  public Options add(char shortName, String longName, boolean hasArg) {
    return add(shortName, longName, hasArg, TextUtils.EMPTY_STRING);
  }

  /**
   * Adds the option.
   *
   * @param shortName the short name
   * @param longName the long name
   * @param hasArg the has arg
   * @param description the description
   * @return 
   */
  public Options add(char shortName,
      String longName,
      boolean hasArg,
      String description) {
    return addOption(new CommandLineOption(shortName, longName, hasArg, description));
  }

  /**
   * Adds the option.
   *
   * @param option the option
   * @return 
   */
  public Options addOption(CommandLineOption option) {
    mOptions.add(option);
    
    return this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<CommandLineOption> iterator() {
    return mOptions.iterator();
  }

  /**
   * Prints the help.
   *
   * @param options the options
   */
  public static void printHelp(Options options) {
    System.out.println("OPTIONS");
    for (CommandLineOption option : options) {
      System.out.print("\t" + option.getShortName());

      if (option.hasArg()) {
        System.out.print(" VALUE");
      }

      System.out.print(", --" + option.getLongName());

      if (option.hasArg()) {
        System.out.print("=VALUE");
      }

      System.out.println();

      if (!TextUtils.isNullOrEmpty(option.getDescription())) {
        System.out.println("\t\t" + option.getDescription());
      }

      System.out.println();
    }
  }
}
