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
package org.jebtk.core.text;

import java.util.Collection;
import java.util.List;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.stream.Stream;

// TODO: Auto-generated Javadoc
/**
 * The Class Join.
 */
public class Join {
	
	/** The m delimiter. */
	private String mDelimiter;
	
	/** The m ignore empty strings. */
	private boolean mIgnoreEmptyStrings;
	
	/** The m ignore nulls. */
	private boolean mIgnoreNulls;
	
	/** The m builder. */
	StringBuilder mBuilder = new StringBuilder();

	/**
	 * Instantiates a new join.
	 *
	 * @param delimiter the delimiter
	 */
	public Join(String delimiter) {
		this(delimiter, false, false);
	}
	
	/**
	 * Instantiates a new join.
	 *
	 * @param delimiter the delimiter
	 * @param ignoreEmptyStrings the ignore empty strings
	 * @param ignoreNulls the ignore nulls
	 */
	public Join(String delimiter, 
			boolean ignoreEmptyStrings,
			boolean ignoreNulls) {
		mDelimiter = delimiter;
		mIgnoreEmptyStrings = ignoreEmptyStrings;
		mIgnoreNulls = ignoreNulls;
	}
	
	/**
	 * Instantiates a new join.
	 *
	 * @param join the join
	 */
	public Join(final Join join) {
		mDelimiter = join.mDelimiter;
		mIgnoreEmptyStrings = join.mIgnoreEmptyStrings;
		mIgnoreNulls = join.mIgnoreNulls;
		mBuilder.append(join.mBuilder);
	}
	
	/**
	 * Values.
	 *
	 * @param values the values
	 * @return the join
	 */
	public Join values(final Collection<?> values) {
		Join join = new Join(this);
		
		if (CollectionUtils.isNullOrEmpty(values)) {
			return join;
		}
		
		boolean first = true;
		boolean append = join.mBuilder.length() > 0;

		for (Object v : values) {
			if (v == null && mIgnoreNulls) {
				continue;
			}
			
			String s = v.toString();
			
			if (TextUtils.isNullOrEmpty(s) && mIgnoreEmptyStrings) {
				continue;
			}
			
			if (first && !append) {
				join.mBuilder.append(s);
			} else {
				join.mBuilder.append(mDelimiter).append(s);
			}
			
			first = false;
		}

		return join;
	}
	
	/**
	 * Values.
	 *
	 * @param values the values
	 * @return the join
	 */
	public Join values(Object... values) {
		Join join = new Join(this);
		
		if (CollectionUtils.isNullOrEmpty(values)) {
			return join;
		}
		
		boolean first = true;
		boolean append = mBuilder.length() > 0;

		for (Object v : values) {
			if (mIgnoreNulls && v == null) {
				continue;
			}
			
			String s = v.toString();
			
			if (mIgnoreEmptyStrings && TextUtils.isNullOrEmpty(s)) {
				continue;
			}
			
			if (first && !append) {
				join.mBuilder.append(s);
			} else {
				join.mBuilder.append(mDelimiter).append(s);
			}
			
			first = false;
		}

		return join;
	}
	
	/**
	 * Create a join from the values in an iterable object.
	 *
	 * @param values the values
	 * @return the join
	 */
	public Join values(Iterable<?> values) {
		Join join = new Join(this);
		
		boolean first = true;
		boolean append = mBuilder.length() > 0;

		for (Object v : values) {
			if (mIgnoreNulls && v == null) {
				continue;
			}
			
			String s = v.toString();
			
			if (mIgnoreEmptyStrings && TextUtils.isNullOrEmpty(s)) {
				continue;
			}
			
			if (first && !append) {
				join.mBuilder.append(s);
			} else {
				join.mBuilder.append(mDelimiter).append(s);
			}
			
			first = false;
		}

		return join;
	}
	
	/**
	 * Repeat an item n times adding any necessary delimiters.
	 *
	 * @param o the o
	 * @param n the n
	 * @return the join
	 */
	public Join repeat(Object o, int n) {
		Join join = new Join(this);
		
		// If there is something already cached, add
		// a delimiter before beginning.
		
		if (mBuilder.length() > 0) {
			join.mBuilder.append(mDelimiter);
		}
		
		join.mBuilder.append(TextUtils.repeat(o, mDelimiter, n));
		
		return join;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return mBuilder.toString();
	}
	
	/**
	 * Ignore empty strings.
	 *
	 * @return the join
	 */
	public Join ignoreEmptyStrings() {
		return new Join(mDelimiter, true, mIgnoreNulls);
	}
	
	/**
	 * Ignore nulls.
	 *
	 * @return the join
	 */
	public Join ignoreNulls() {
		return new Join(mDelimiter, mIgnoreEmptyStrings, true);
	}
	
	/**
	 * Join values on a given character.
	 *
	 * @param delimiter the delimiter
	 * @return the join
	 */
	public static Join on(char delimiter) {
		return on(Character.toString(delimiter));
	}
	
	/**
	 * Join values using a given string.
	 *
	 * @param delimiter the delimiter
	 * @return the join
	 */
	public static Join on(String delimiter) {
		return new Join(delimiter, false, false);
	}
	
	/**
	 * Join values using the tab character.
	 *
	 * @return the join
	 */
	public static Join onTab() {
		return on(TextUtils.TAB_DELIMITER);
	}
	
	/**
	 * On comma.
	 *
	 * @return the join
	 */
	public static Join onComma() {
		return on(TextUtils.COMMA_DELIMITER);
	}
	
	/**
	 * On semi colon.
	 *
	 * @return the join
	 */
	public static Join onSemiColon() {
		return on(TextUtils.SEMI_COLON_DELIMITER);
	}

	/**
	 * On tab.
	 *
	 * @param values the values
	 * @return the join
	 */
	public static Join onTab(Object... values) {
		return onTab().values(values);
	}
	
	/**
	 * On tab.
	 *
	 * @param <T> the generic type
	 * @param values the values
	 * @return the string
	 */
	public static <T extends Comparable<T>> String onTab(List<T> values) {
		return Stream.stream(values)
				.mapToString()
				.join(TextUtils.TAB_DELIMITER);
	}

	/**
	 * On space.
	 *
	 * @return the join
	 */
	public static Join onSpace() {
		return on(TextUtils.SPACE_DELIMITER);
	}
}
