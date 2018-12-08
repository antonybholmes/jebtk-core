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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.jebtk.core.collections.ArrayListCreator;
import org.jebtk.core.collections.DefaultHashMap;
import org.jebtk.core.collections.IterMap;
import org.jebtk.core.text.TextUtils;

/**
 * The Class CommandLineArgs.
 */
public class ArgParser implements Iterable<Entry<String, List<String>>> {

  /**
   * The member args.
   */
  private IterMap<String, List<String>> mArgMap = DefaultHashMap
      .create(new ArrayListCreator<String>());

  private List<String> mOthers = new ArrayList<String>();

  private Args mOptions;

  //private static final Pattern SHORT_ARG_REGEX = Pattern.compile("^-([\\w\\-]+)");
  //private static final Pattern LONG_ARG_REGEX = Pattern.compile("^--([\\w\\-]+)");

  public ArgParser(Args options) {
    mOptions = options;
  }

  /**
   * Adds the arg.
   *
   * @param arg the arg
   */
  private void add(Arg arg) {
    mArgMap.get(arg.getLongName());
  }

  private void add(Arg arg, String value) {
    mArgMap.get(arg.getLongName()).add(value);
  }

  /**
   * Gets the arg.
   *
   * @param name the name
   * @return the arg
   */
  public String getArg(String name) {
    List<String> args = getArgs(name);

    if (args.size() > 0) {
      return args.get(0);
    } else {
      return TextUtils.EMPTY_STRING;
    }
  }

  /**
   * Returns a list of the parsed arguments with the given name
   * @param name
   * @return
   */
  public List<String> getArgs(String name) {
    return Collections.unmodifiableList(mArgMap.get(name));
  }

  /**
   * Returns the unnamed args in the order they were parsed.
   * 
   * @return
   */
  public List<String> getArgs() {
    return Collections.unmodifiableList(mOthers);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<Entry<String, List<String>>> iterator() {
    return mArgMap.iterator();
  }

  /**
   * Parses a set of arguments and extracts values. If an arg is not properly
   * specified, it will be ignored, for example '--arg=' with a missing value
   * will not be available as an argument. This is to negate needing to throw
   * exceptions, but requires diligence on the user.
   *
   * @param options the options
   * @param args the args
   * @return the command line args
   * @throws ArgException 
   */
  public ArgParser parse(String... args) {
    int index = 0;

    String arg;
    String value;
    boolean isLong;
    boolean isShort;
    
    while (index < args.length) {
      arg = args[index++];

      value = null;
      
      // Test the type of the argument
      isLong = arg.startsWith("--");
      isShort = !isLong && arg.length() == 2 && arg.startsWith("-");

      //System.err.println("is long " + isLong + " " + isShort + " " + arg + " " + Arrays.toString(args));

      //
      if (!isShort && !isLong) {
        mOthers.add(arg);
        continue;
      }

      if (isLong) {
        int delim = arg.indexOf("=");

        if (delim > 0) {
          value = arg.substring(delim + 1);
          arg = arg.substring(2, delim);
        } else {
          arg = arg.substring(2);
        }
      } else {
        arg = arg.substring(1, 2);
      }

      Arg option = mOptions.get(arg);

      if (option != null) {
        if (isLong) {
          if (option.hasValue()) {
            if (!TextUtils.isNullOrEmpty(value)) {
              add(option, value);
            }
          } else {
            add(option);
          }
        } else {
          if (option.hasValue()) {
            if (index < args.length) {
              add(option, args[index++]);
            }
          } else {
            add(option);
          }
        }
      }
    }

    return this;
  }
  
  public static Entry<String, String> parsePosixArg(String arg) {

    arg = arg.replaceFirst("--", TextUtils.EMPTY_STRING);

    int index = arg.indexOf("=");
    
    if (index > 0) {
      return new org.jebtk.core.collections.Entry<String, String>(arg.substring(0, index), arg.substring(index + 1));
    } else {
      return new org.jebtk.core.collections.Entry<String, String>(arg, TextUtils.EMPTY_STRING);
    }
  }

  public static String longArg(String name, String value) {
    return new StringBuilder().append("--").append(name).append("=").append(value).toString();
  }

  public static String longArg(String name) {
    return new StringBuilder().append("--").append(name).toString();
  }
}
