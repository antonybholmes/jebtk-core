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

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

// TODO: Auto-generated Javadoc
/**
 * The Class CommandLineArgs.
 */
public class CommandLineArgs implements Iterable<CommandLineArg> {

	/**
	 * The member args.
	 */
	private Map<String, CommandLineArg> mArgs = 
			new TreeMap<String, CommandLineArg>();
	
	/**
	 * Adds the arg.
	 *
	 * @param arg the arg
	 */
	public void add(CommandLineArg arg) {
		mArgs.put(arg.getLongName(), arg);
	}
	
	/**
	 * Gets the arg.
	 *
	 * @param name the name
	 * @return the arg
	 */
	public CommandLineArg get(String name) {
		return mArgs.get(name);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<CommandLineArg> iterator() {
		return mArgs.values().iterator();
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
				if (arg.startsWith("--" + option.getLongName())) {
					if (option.hasArg()) {
						// skip the equals and take the rest as an argument
						String value = arg.substring(arg.indexOf("=") + 1);
							
						commandLineOptions.add(new CommandLineArg(option, value));
					} else {
						commandLineOptions.add(new CommandLineArg(option));
					}
					
					break;
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
}
