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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * A specialized map which can be iterated over in the order keys were added.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class OrderedMap<K, V> extends MapContainer<K, V> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The m ordered keys. */
	private List<K> mOrderedKeys = new ArrayList<K>();
	
	/**
	 * Instantiates a new ordered map.
	 *
	 * @param map the map
	 */
	public OrderedMap(Map<K, V> map) {
		super(map);
	}
	
	/* (non-Javadoc)
	 * @see org.abh.common.collections.MapContainer#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public V put(K key, V value) {
		if (!mMap.containsKey(key)) {
			mOrderedKeys.add(key);
		}
		
		return super.put(key, value);
	}
	
	/* (non-Javadoc)
	 * @see org.abh.common.collections.MapContainer#remove(java.lang.Object)
	 */
	@Override
	public V remove(Object key) {
		mOrderedKeys.remove(key);
		
		return super.remove(key);
	}
	
	/* (non-Javadoc)
	 * @see org.abh.common.collections.MapContainer#iterator()
	 */
	@Override
	public Iterator<K> iterator() {
		return mOrderedKeys.iterator();
	}
	

	/**
	 * Creates the key ordered map.
	 *
	 * @param <KK> the generic type
	 * @param <VV> the generic type
	 * @return the ordered map
	 */
	public static <KK, VV> OrderedMap<KK, VV> newOrderedMap() {
		return newOrderedMap(new HashMap<KK, VV>());
	}
	
	/**
	 * Creates the key ordered map.
	 *
	 * @param <KK> the generic type
	 * @param <VV> the generic type
	 * @param map the map
	 * @return the ordered map
	 */
	public static <KK, VV> OrderedMap<KK, VV> newOrderedMap(Map<KK, VV> map) {
		return new OrderedMap<KK, VV>(map);
	}
}
