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
package org.jebtk.core.sys;

import java.io.PrintStream;
import java.util.Collection;
import java.util.List;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.text.Join;

// TODO: Auto-generated Javadoc
/**
 * Utility class for functions related to the system such as enhanced
 * println.
 * 
 */
public class SysUtils {
	
	/**
	 * The Class Out.
	 */
	public static class Out {

		/** The m S. */
		private PrintStream mS;

		/**
		 * Instantiates a new out.
		 *
		 * @param s the s
		 */
		public Out(PrintStream s) {
			mS = s;
		}
		
		/**
		 * Columns.
		 *
		 * @param lists the lists
		 */
		public void columns(List<?>... lists) {
			int n = CollectionUtils.minSize(lists);
			
			for (int i = 0; i < n; ++i) {
				mS.println(Join.onTab().values(CollectionUtils.asList(i, lists)));
			}
		}
		
		/**
		 * Prints the.
		 *
		 * @param o the o
		 */
		public void print(Object o) {
			mS.print(o);
		}
		
		/**
		 * Println.
		 *
		 * @param o the o
		 */
		public void println(Object o) {
			mS.println(o);
		}
		
		/**
		 * Println.
		 *
		 * @param values the values
		 */
		public void println(Object... values) {
			mS.println(Join.onSpace().values(values).toString());
		}

		/**
		 * Prints the collection.
		 *
		 * @param values the values
		 */
		public void printCollection(Collection<?> values) {
			for (Object v : values) {
				println(v);
			}
		}
	}
	
	/**
	 * Out.
	 *
	 * @return the out
	 */
	public static Out out() {
		return new Out(System.out);
	}
	
	/**
	 * Err.
	 *
	 * @return the out
	 */
	public static Out err() {
		return new Out(System.err);
	}
}
