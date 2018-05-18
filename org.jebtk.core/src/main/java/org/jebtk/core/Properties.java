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

import java.awt.Color;
import java.util.Iterator;

import org.jebtk.core.collections.IterMap;
import org.jebtk.core.collections.IterTreeMap;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.core.event.ChangeListeners;

/**
 * Generic properties object for sharing heterogenous properties.
 *
 * @author Antony Holmes Holmes
 */
public class Properties extends ChangeListeners implements Iterable<String>, ChangeListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  //protected IterMap<String, Property> mPropertyMap = null;
  
  /**
   * The member items.
   */
  protected IterMap<String, Object> mPropertyMap = null;

  public Properties() {
    mPropertyMap = new IterTreeMap<String, Object>();
  }

  public Properties(Properties parent) {
    if (parent != null) {
      mPropertyMap = new IterTreeMap<String, Object>(parent.mPropertyMap);
    } else {
      mPropertyMap = new IterTreeMap<String, Object>();
    }
  }
  
  public Properties inherits(Properties parent) {
    mPropertyMap.putAll(parent.mPropertyMap);
    
    return this;
  }
  
  /**
   * Update a property without triggering a change event.
   *
   * @param name the name
   * @param item the item
   * @return 
   */
  public Properties set(String name, Object value) {
    update(name, value);
    
    fireChanged();

    return this;
  }

  public Properties update(String name, Object value) {
    mPropertyMap.put(name, value);

    return this;
  }

  /**
   * Gets the property as int.
   *
   * @param name the name
   * @return the property as int
   */
  public int getInt(String name) {
    return (int) getValue(name);
  }

  /**
   * Gets the as bool.
   *
   * @param name the name
   * @return the as bool
   */
  public boolean getBool(String name) {
    if (contains(name)) {
      return (boolean) getValue(name);
    } else {
      return false;
    }
  }

  /**
   * Gets the as color.
   *
   * @param name the name
   * @return the as color
   */
  public Color getColor(String name) {
    return (Color) getValue(name);
  }

  /**
   * Gets the property as double.
   *
   * @param name the name
   * @return the property as double
   */
  public double getDouble(String name) {
    return (double) getValue(name);
  }
  
  public String toString(String name) {
    return getValue(name).toString();
  }

  public Object getValue(String name) {
    return mPropertyMap.get(name);
  }
  
  /**
   * Returns true if the property exists.
   *
   * @param name the name
   * @return true, if successful
   */
  public boolean contains(String name) {
    return mPropertyMap.containsKey(name);
  }

  /**
   * Clear.
   */
  public void clear() {
    mPropertyMap.clear();

    fireChanged();
  }

  /**
   * Size.
   *
   * @return the int
   */
  public int size() {
    return mPropertyMap.size();
  }

  /**
   * Returns a sorted iterator of the names in this model.
   *
   * @return the iterator
   */
  @Override
  public Iterator<String> iterator() {
    return mPropertyMap.iterator();
  }

  @Override
  public void changed(ChangeEvent e) {
    fireChanged();
  }

}