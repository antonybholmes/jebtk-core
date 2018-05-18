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

/**
 * Generic properties object for sharing heterogenous properties.
 *
 * @author Antony Holmes Holmes
 */
public class Property extends KeyValuePair<String, Object> implements NameProperty {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  public Property(String name, Object item) {
    super(name, item);
  }
  
  @Override
  public String getName() {
    return getKey();
  }

  /**
   * Gets the property as int.
   *
   * @param name the name
   * @return the property as int
   */
  public int getInt() {
    return getInt(this);
  }

  /**
   * Gets the as bool.
   *
   * @param name the name
   * @return the as bool
   */
  public boolean getBool() {
    return getBool(this);
  }

  /**
   * Gets the as color.
   *
   * @param name the name
   * @return the as color
   */
  public Color getColor() {
    return getColor(this);
  }

  /**
   * Gets the property as double.
   *
   * @param name the name
   * @return the property as double
   */
  public double getDouble() {
    return getDouble(this);
  }

  public static double getDouble(Property p) {
    return (Double) p.getValue();
  }
  
  public static int getInt(Property p) {
    return (Integer) p.getValue();
  }
  
  public static boolean getBool(Property p) {
    return (Boolean) p.getValue();
  }
  
  public static Color getColor(Property p) {
    return (Color) p.getValue();
  }
}