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
import org.jebtk.core.event.ChangeListeners;

/**
 * Generic properties object for sharing heterogenous properties.
 *
 * @author Antony Holmes Holmes
 */
public class Properties extends ChangeListeners implements Iterable<String> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member items.
   */
  protected IterMap<String, Object> mPropertyMap = 
      new IterTreeMap<String, Object>();

  /**
   * Sets the property and triggers a change event for any listener.
   *
   * @param name the name
   * @param item the item
   * @return 
   */
  public Properties setProperty(String name, Object item) {
    updateProperty(name, item);

    fireChanged();
    
    return this;
  }

  /**
   * Update a property without triggering a change event.
   *
   * @param name the name
   * @param item the item
   * @return 
   */
  public Properties updateProperty(String name, Object item) {
    mPropertyMap.put(name, item);
    
    return this;
  }

  /**
   * Gets the property.
   *
   * @param name the name
   * @return the property
   */
  public Object getProperty(String name) {
    return mPropertyMap.get(name);
  }

  /**
   * Gets the property as int.
   *
   * @param name the name
   * @return the property as int
   */
  public int getAsInt(String name) {
    return (int) getProperty(name);
  }

  /**
   * Gets the as bool.
   *
   * @param name the name
   * @return the as bool
   */
  public boolean getAsBool(String name) {
    if (contains(name)) {
      return (boolean) getProperty(name);
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
  public Color getAsColor(String name) {
    return (Color) getProperty(name);
  }

  /**
   * Gets the property as double.
   *
   * @param name the name
   * @return the property as double
   */
  public double getPropertyAsDouble(String name) {
    return (Double) getProperty(name);
  }

  /**
   * Contains.
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

}