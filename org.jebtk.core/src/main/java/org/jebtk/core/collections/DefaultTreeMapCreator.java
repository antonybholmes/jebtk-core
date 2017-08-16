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

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class DefaultTreeMapCreator.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class DefaultTreeMapCreator<K, V> implements MapCreator<K, V> {

	/** The m default value. */
	private EntryCreator<V> mDefaultValue;

	/**
	 * Instantiates a new default tree map creator.
	 *
	 * @param defaultValue the default value
	 */
	public DefaultTreeMapCreator(V defaultValue) {
		this(new ValueCreator<V>(defaultValue));
	}
	
	/**
	 * Instantiates a new default map.
	 *
	 * @param defaultValue the default value
	 */
	public DefaultTreeMapCreator(EntryCreator<V> defaultValue) {
		mDefaultValue = defaultValue;
	}

	/* (non-Javadoc)
	 * @see org.abh.common.collections.EntryCreator#newEntry()
	 */
	@Override
	public Map<K, V> newEntry() {
		return new DefaultTreeMap<K, V>(mDefaultValue);
	}
}
