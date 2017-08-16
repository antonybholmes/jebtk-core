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

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * ArrayList that auto fills with default values so that it is initialized
 * to a given size.
 *
 * @param <V> the value type
 */
public class DefaultArrayList<V> extends ArrayList<V> {
	
	/**
	 * The constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The Constant DEFAULT_SIZE. */
	private static final int DEFAULT_SIZE = 32;

	/**
	 * The member default value.
	 */
	private EntryCreator<V> mDefaultValue;

	
	/**
	 * Instantiates a new default array list.
	 *
	 * @param defaultValue the default value
	 */
	public DefaultArrayList(V defaultValue) {
		this(DEFAULT_SIZE, new ValueCreator<V>(defaultValue));
	}
	
	/**
	 * Instantiates a new auto hash map.
	 *
	 * @param size the size
	 * @param defaultValue the default value
	 */
	public DefaultArrayList(int size, V defaultValue) {
		this(size, new ValueCreator<V>(defaultValue));
	}
	
	/**
	 * Instantiates a new default list.
	 *
	 * @param size the size
	 * @param defaultValue the default value
	 */
	public DefaultArrayList(int size, EntryCreator<V> defaultValue) {
		super(size);
		
		mDefaultValue = defaultValue;
		
		autoCreate(size);
	}
	
	/* (non-Javadoc)
	 * @see java.util.ArrayList#get(int)
	 */
	@Override
	public V get(int index) {
		autoCreate(index + 1);

		return super.get(index);
	}
	
	/**
	 * Auto create.
	 *
	 * @param size the size
	 */
	public void autoCreate(int size) {
		while (size() < size) {
			add(mDefaultValue.newEntry());	
		}
	}
	
	/**
	 * Creates the.
	 *
	 * @param <V1> the generic type
	 * @param defaultValue the default value
	 * @return the list
	 */
	public static <V1> List<V1> create(V1 defaultValue) {
		return create(DEFAULT_SIZE, new ValueCreator<V1>(defaultValue));
	}
	
	/**
	 * Creates the.
	 *
	 * @param <V1> the generic type
	 * @param size the size
	 * @param defaultValue the default value
	 * @return the list
	 */
	public static <V1> List<V1> create(int size, V1 defaultValue) {
		return create(size, new ValueCreator<V1>(defaultValue));
	}

	/**
	 * Creates a new {@code DefaultArrayList}.
	 *
	 * @param <V1> the generic type
	 * @param defaultValue the default value
	 * @return the list
	 */
	public static <V1> List<V1> create(EntryCreator<V1> defaultValue) {
		return create(DEFAULT_SIZE, defaultValue);
	}
	
	/**
	 * Creates a new {@code DefaultArrayList}.
	 *
	 * @param <V1> the generic type
	 * @param size the size
	 * @param defaultValue the default value
	 * @return the list
	 */
	public static <V1> List<V1> create(int size, 
			EntryCreator<V1> defaultValue) {
		return new DefaultArrayList<V1>(size, defaultValue);
	}
}
