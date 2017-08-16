/**
 * Copyright 2017 Antony Holmes
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

import java.util.HashMap;
import java.util.Iterator;

// TODO: Auto-generated Javadoc
/**
 * A hashmap where the keys can be iterated over.
 *
 * @author Antony Holmes Holmes
 * @param <K> the key type
 * @param <V> the value type
 */
public class IterHashMap<K, V> extends HashMap<K, V> implements IterMap<K, V> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new iter hash map.
	 */
	public IterHashMap() {
		this(100);
	}
	
	/**
	 * Instantiates a new iter hash map.
	 *
	 * @param initialCapacity the initial capacity
	 */
	public IterHashMap(int initialCapacity) {
		super(initialCapacity);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<K> iterator() {
		return keySet().iterator();
	}
	
	/**
	 * Create a new IterHashMap.
	 * 
	 * @return		A new IterHashMap.
	 */
	//public static <KK, VV> IterHashMap<KK, VV> create() {
	//	return new IterHashMap<KK, VV>();
	//}
}
