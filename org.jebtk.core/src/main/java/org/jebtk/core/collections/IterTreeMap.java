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

import java.util.Iterator;
import java.util.TreeMap;

// TODO: Auto-generated Javadoc
/**
 * A Tree map where the keys can be iterated over.
 *
 * @author Antony Holmes Holmes
 * @param <K> the key type
 * @param <V> the value type
 */
public class IterTreeMap<K, V> extends TreeMap<K, V> implements IterMap<K, V> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<K> iterator() {
		return keySet().iterator();
	}

	/**
	 * Create a new IterTreeMap.
	 * 
	 * @return		A new IterTreeMap.
	 */
	//public static <KK, VV> IterTreeMap<KK, VV> create() {
	//	return new IterTreeMap<KK, VV>();
	//}
}
