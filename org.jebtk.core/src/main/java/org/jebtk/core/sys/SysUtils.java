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
import org.jebtk.core.text.TextUtils;

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
		private String mDelimiter = TextUtils.SPACE_DELIMITER;

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
			mS.println(Join.on(mDelimiter ).values(values).toString());
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
		
		public Out setSeparator(String delimiter) {
			mDelimiter = delimiter;
			
			return this;
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

	/**
	 * Copy one array to another. Is is assumed the dest array can contain the
	 * src array.
	 * 
	 * @param src
	 * @param dest
	 */
	public static void arraycopy(double[] src, 
			double[] dest) {
		arraycopy(src, dest, src.length);
	}
	
	public static void arraycopy(double[] src, 
			double[] dest,
			int l) {
		arraycopy(src, 0, dest, 0, l);
	}
	
	public static void arraycopy(double[] src, 
			int srcoffset, 
			double[] dest,
			int l) {
		arraycopy(src, srcoffset, dest, 0, l);
	}
	
	public static void arraycopy(double[] src, 
			int srcoffset, 
			double[] dest, 
			int destoffset,
			int l) {
		System.arraycopy(src, srcoffset, dest, destoffset, l);
	}
	
	public static void arraycopy(double[] src, 
			int srcoffset, 
			int srcskip, 
			double[] dest,
			int l) {
		arraycopy(src, srcoffset, srcskip, dest, 0, l);
	}
	
	public static void arraycopy(double[] src, 
			int srcoffset, 
			int srcskip, 
			double[] dest, 
			int destoffset,
			int l) {
		arraycopy(src, srcoffset, srcskip, dest, destoffset, 1, l);
	}
	
	public static void arraycopy(double[] src, 
			double[] dest, 
			int destoffset, 
			int destskip) {
		arraycopy(src, dest, destoffset, destskip, src.length);
	}
	
	public static void arraycopy(double[] src, 
			double[] dest, 
			int destoffset, 
			int destskip, 
			int l) {
		arraycopy(src, 0, 1, dest, destoffset, destskip, l);
	}
	
	public static void arraycopy(double[] src, 
			int srcoffset, 
			int srcskip, 
			double[] dest, 
			int destoffset, 
			int destskip,
			int l) {
		for (int i = 0; i < l; ++i) {
			dest[destoffset] = src[srcoffset];
			
			srcoffset += srcskip;
			destoffset += destskip;
		}
	}
	
	public static void arraycopy(int[] src, 
			int[] dest) {
		arraycopy(src, dest, src.length);
	}
	
	public static void arraycopy(int[] src, 
			int[] dest,
			int l) {
		arraycopy(src, 0, dest, 0, l);
	}
	
	public static void arraycopy(int[] src,
			int srcoffset, 
			int[] dest,
			int l) {
		arraycopy(src, srcoffset, dest, 0, l);
	}
	
	public static void arraycopy(int[] src, 
			int srcoffset, 
			int[] dest, 
			int destoffset,
			int l) {
		
		System.arraycopy(src, srcoffset, dest, destoffset, l);
		//arraycopy(src, srcoffset, 1, dest, destoffset, 1, l);
	}
	
	public static void arraycopy(int[] src, 
			int srcoffset, 
			int srcskip, 
			int[] dest, 
			int l) {
		arraycopy(src, srcoffset, srcskip, dest, 0, l);
	}
	
	public static void arraycopy(int[] src, 
			int srcoffset, 
			int srcskip, 
			int[] dest, 
			int destoffset,
			int l) {
		arraycopy(src, srcoffset, srcskip, dest, destoffset, 1, l);
	}
	
	public static void arraycopy(int[] src, 
			int srcoffset, 
			int srcskip, 
			int[] dest, 
			int destoffset, 
			int destskip,
			int l) {
		for (int i = 0; i < l; ++i) {
			dest[destoffset] = src[srcoffset];
			
			srcoffset += srcskip;
			destoffset += destskip;
		}
	}
	
	//
	// Long
	//
	
	public static void arraycopy(long[] src, 
			long[] dest) {
		arraycopy(src, dest, src.length);
	}
	
	public static void arraycopy(long[] src, 
			long[] dest,
			int l) {
		arraycopy(src, 0, dest, 0, l);
	}
	
	public static void arraycopy(long[] src,
			int srcoffset, 
			long[] dest,
			int l) {
		arraycopy(src, srcoffset, dest, 0, l);
	}
	
	public static void arraycopy(long[] src, 
			int srcoffset, 
			long[] dest, 
			int destoffset,
			int l) {
		System.arraycopy(src, srcoffset, dest, destoffset, l);
	}
	
	public static void arraycopy(long[] src, 
			int srcoffset, 
			int srcskip, 
			long[] dest) {
		arraycopy(src, srcoffset, srcskip, dest, src.length);
	}
	
	/**
	 * Copy src array to dest array.
	 * 
	 * @param src			The source array.
	 * @param srcoffset		Source offset to begin copying.
	 * @param srcskip		How many elements to skip at a time.
	 * @param dest			Destination array.
	 * @param l				How many elements to copy.
	 */
	public static void arraycopy(long[] src, 
			int srcoffset, 
			int srcskip, 
			long[] dest,
			int l) {
		arraycopy(src, srcoffset, srcskip, dest, 0, l);
	}
	
	public static void arraycopy(long[] src, 
			int srcoffset, 
			int srcskip, 
			long[] dest, 
			int destoffset,
			int l) {
		arraycopy(src, srcoffset, srcskip, dest, destoffset, 1, l);
	}
	
	public static void arraycopy(long[] src, 
			int srcoffset, 
			int srcskip, 
			long[] dest, 
			int destoffset, 
			int destskip,
			int l) {
		for (int i = 0; i < l; ++i) {
			dest[destoffset] = src[srcoffset];
			
			srcoffset += srcskip;
			destoffset += destskip;
		}
	}
	
	//
	// Object
	//
	
	public static void arraycopy(Object[] src, 
			Object[] dest) {
		arraycopy(src, dest, src.length);
	}
	
	public static void arraycopy(Object[] src, 
			Object[] dest,
			int l) {
		arraycopy(src, 0, dest, 0, l);
	}
	
	public static void arraycopy(Object[] src,
			int srcoffset, 
			Object[] dest,
			int l) {
		arraycopy(src, srcoffset, dest, 0, l);
	}
	
	public static void arraycopy(Object[] src, 
			int srcoffset, 
			Object[] dest, 
			int destoffset,
			int l) {
		System.arraycopy(src, srcoffset, dest, destoffset, l);
		//arraycopy(src, srcoffset, 1, dest, destoffset, 1, l);
	}
	
	public static void arraycopy(Object[] src, 
			int srcoffset, 
			int srcskip, 
			Object[] dest, 
			int destoffset,
			int l) {
		arraycopy(src, srcoffset, srcskip, dest, destoffset, 1, l);
	}
	
	public static void arraycopy(Object[] src, 
			int srcoffset, 
			int srcskip, 
			Object[] dest, 
			int destoffset, 
			int destskip,
			int l) {
		for (int i = 0; i < l; ++i) {
			dest[destoffset] = src[srcoffset];
			
			srcoffset += srcskip;
			destoffset += destskip;
		}
	}

	
}
