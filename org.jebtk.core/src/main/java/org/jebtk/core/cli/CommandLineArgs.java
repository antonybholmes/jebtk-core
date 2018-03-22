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
import java.util.Map;

import org.jebtk.core.collections.ArrayListCreator;
import org.jebtk.core.collections.DefaultHashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class CommandLineArgs.
 */
public class CommandLineArgs implements Iterable<CommandLineArg> {

  /**
   * The member args.
   */
  private Map<String, List<CommandLineArg>> mArgMap = DefaultHashMap
      .create(new ArrayListCreator<CommandLineArg>());

  private List<CommandLineArg> mArgs = new ArrayList<CommandLineArg>();

  /**
   * Adds the arg.
   *
   * @param arg the arg
   */
  public void add(CommandLineArg arg) {
    mArgs.add(arg);

    mArgMap.get(arg.getLongName()).add(arg);
  }

  /**
   * Gets the arg.
   *
   * @param name the name
   * @return the arg
   */
  public Iterable<CommandLineArg> get(String name) {
    return mArgMap.get(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<CommandLineArg> iterator() {
    return mArgs.iterator();
  }

  /**
   * Parses the.
   *
   * @param options the options
   * @param args the args
   * @return the command line args
   */
  public static CommandLineArgs parse(Options options, String... args) {
    CommandLineArgs commandLineOptions = new CommandLineArgs();

    int argIndex = 0;

    while (argIndex < args.length) {
      String arg = args[argIndex];

      for (CommandLineOption option : options) {
        if (arg.startsWith("--")) {
          if (option.hasArg()) {
            if (arg.contains(option.getLongName() + "=")) {
              // skip the equals and take the rest as an argument
              String value = arg.substring(arg.indexOf("=") + 1);

              commandLineOptions.add(new CommandLineArg(option, value));
              break;
            }
          } else {
            if (arg.equals("--" + option.getLongName())) {
              commandLineOptions.add(new CommandLineArg(option));
              break;
            }
          }
        } else if (arg.equals("-" + option.getShortName())) {
          if (option.hasArg()) {
            String value = args[argIndex + 1];

            commandLineOptions.add(new CommandLineArg(option, value));

            // skip the second argument since there is no point
            // parsing it is the argument to the first argument.
            ++argIndex;
          } else {
            commandLineOptions.add(new CommandLineArg(option));
          }

          break;
        } else {
          // do nothing
        }
      }

      ++argIndex;
    }

    return commandLineOptions;
  }

  public static String longArg(String name, String value) {
    return new StringBuilder().append("--").append(name).append("=").append(value).toString();
  }
  
  public static String longArg(String name) {
    return new StringBuilder().append("--").append(name).toString();
  }
}
