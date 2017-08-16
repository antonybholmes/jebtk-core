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
package org.jebtk.core.collections;

// TODO: Auto-generated Javadoc
/**
 * Returns a fixed object/value so that a structure will be auto-populated
 * with the same value. This is best used with immutable objects such as 
 * strings and numbers.
 *
 * @param <T> the generic type
 */
public class ValueCreator<T> implements EntryCreator<T> {
	
	/**
	 * The member value.
	 */
	private T mValue;

	/**
	 * Instantiates a new value creator.
	 *
	 * @param value the value
	 */
	public ValueCreator(T value) {
		mValue = value;
	}
	
	/* (non-Javadoc)
	 * @see org.abh.lib.collections.EntryCreator#create()
	 */
	@Override
	public T newEntry() {
		return mValue;
	}
}
