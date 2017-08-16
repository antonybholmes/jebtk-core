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

import java.util.List;

import org.jebtk.core.text.TextUtils;

// TODO: Auto-generated Javadoc
/**
 * Captures an actual argument on the command line.
 * 
 * @author Antony Holmes Holmes
 *
 */
public class CommandLineArg {

	/**
	 * The member value.
	 */
	private String mValue = null;

	/**
	 * The member name.
	 */
	private String mLongName = null;

	/** The m short name. */
	private char mShortName;

	/**
	 * Instantiates a new command line arg.
	 *
	 * @param option the option
	 */
	public CommandLineArg(CommandLineOption option) {
		this(option, null);
	}
	
	/**
	 * Instantiates a new command line arg.
	 *
	 * @param option the option
	 * @param value the value
	 */
	public CommandLineArg(CommandLineOption option, String value) {
		this(option.getShortName(), option.getLongName(), value);
	}
	
	/**
	 * Instantiates a new command line arg.
	 *
	 * @param shortName the short name
	 * @param longName the long name
	 */
	public CommandLineArg(char shortName, String longName) {
		this(shortName, longName, null);
	}

	/**
	 * Instantiates a new command line arg.
	 *
	 * @param shortName the short name
	 * @param longName the long name
	 * @param value the value
	 */
	public CommandLineArg(char shortName, String longName, String value) {
		mShortName = shortName;
		mLongName = longName;
		mValue = value;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getLongName() {
		return mLongName;
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
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return mValue;
	}

	/**
	 * Parses the posix arg.
	 *
	 * @param arg the arg
	 * @return the command line arg
	 */
	public static CommandLineArg parsePosixArg(String arg) {

		arg = arg.replaceFirst("--", TextUtils.EMPTY_STRING);

		CommandLineArg ret;
		
		if (arg.contains("=")) {
			List<String> tokens = TextUtils.fastSplit(arg, TextUtils.EQUALS_DELIMITER);

			ret = new CommandLineArg((char)0, tokens.get(0), tokens.get(1));
		} else {
			ret = new CommandLineArg((char)0, arg);
		}

		return ret;
	}
}
