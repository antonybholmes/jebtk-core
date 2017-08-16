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
package org.jebtk.core;

// TODO: Auto-generated Javadoc
/**
 * A simple mapping between a key and a value which can be used in place of
 * two separate variables.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class KeyValuePair<K extends Comparable<? super K>, V> implements Comparable<KeyValuePair<K, V>> {

	/**
	 * The member key.
	 */
	private K mKey;
	
	/**
	 * The member value.
	 */
	private V mValue;

	/**
	 * Instantiates a new key value pair.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public KeyValuePair(K key, V value) {
		mKey = key;
		
		setValue(value);
	}
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public K getKey() {
		return mKey;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public V getValue() {
		return mValue;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(V value) {
		mValue = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(KeyValuePair<K, V> k) {
		return mKey.compareTo(k.mKey);
	}

}
