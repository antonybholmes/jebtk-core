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
package org.jebtk.core.settings;

import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.Mathematics;

// TODO: Auto-generated Javadoc
/**
 * The Class SettingsHistory.
 */
public class SettingsHistory {
	
	/** The m settings. */
	private List<Setting> mSettings = new ArrayList<Setting>(2);
	
	/** The m index. */
	private int mIndex = 0;
	
	/** The m size. */
	private int mSize = 0;
	
	/**
	 * Adds the.
	 *
	 * @param setting the setting
	 * @param updated the updated
	 */
	public void add(Setting setting, boolean updated) {
		if (!updated) {
			// If the setting is not updated, we assume it is a default,
			// internal setting, in which case, it should appear first in the
			// list.
			clear();
		}
		
		if (mSettings.size() == 2) {
			mSettings.set(1, setting);
		} else {
			mSettings.add(setting);
		}
		
		mSize = mSettings.size();
		
		mIndex = mSize - 1;
	}
	
	/**
	 * Clear.
	 */
	public void clear() {
		mSettings.clear();
		
		mIndex = -1;
	}

	/**
	 * Size.
	 *
	 * @return the int
	 */
	public int size() {
		return mSize;
	}

	/**
	 * First.
	 *
	 * @return the setting
	 */
	public Setting first() {
		return get(0);
	}
	
	/**
	 * Current.
	 *
	 * @return the setting
	 */
	public Setting current() {
		return get(mIndex);
	}
	
	/**
	 * Gets the.
	 *
	 * @param index the index
	 * @return the setting
	 */
	private Setting get(int index) {
		if (Mathematics.inBound(index, 0, mSize)) {
			return mSettings.get(index);
		} else {
			return null;
		}
	}

	/**
	 * Changes the setting to its default value.
	 */
	public void resetToDefault() {
		if (mSettings.size() == 2) {
			mSettings.set(1, first());
		}
	}
}
