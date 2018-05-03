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

/**
 * Hashmap that automatically adds a default value if a key does not exist.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class DefaultIntObjMap<V> extends IntObjHashMap<V> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member default value.
   */
  private EntryCreator<V> mDefaultValue;

  /**
   * Instantiates a new auto hash map.
   *
   * @param defaultValue the default value
   */
  public DefaultIntObjMap(V defaultValue) {
    this(HashMapCreator.INITIAL_CAPACITY, defaultValue);
  }

  /**
   * Instantiates a new auto hash map.
   *
   * @param initialCapacity the initial capacity
   * @param defaultValue the default value
   */
  public DefaultIntObjMap(int initialCapacity, V defaultValue) {
    this(initialCapacity, new ValueCreator<V>(defaultValue));
  }

  /**
   * Instantiates a new default map.
   *
   * @param initialCapacity the initial capacity
   * @param defaultValue the default value
   */
  public DefaultIntObjMap(int initialCapacity, EntryCreator<V> defaultValue) {
    super(initialCapacity);

    mDefaultValue = defaultValue;
  }

  /**
   * Instantiates a new default hash map.
   *
   * @param defaultValue the default value
   */
  public DefaultIntObjMap(EntryCreator<V> defaultValue) {
    mDefaultValue = defaultValue;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.HashMap#get(java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  @Override
  public V get(Object key) {
    return getValue(key.hashCode());
  }

  /**
   * Gets the value.
   *
   * @param key the key
   * @return the value
   */
  @SuppressWarnings("unchecked")
  public V getValue(int key) {
    if (!containsKey(key)) {
      put(key, mDefaultValue.newEntry());
    }

    return (V) super.get(key);
  }

  //
  // Static methods
  //

  /**
   * Creates a new Default Hash Map.
   *
   * @param <K1> the generic type
   * @param <V1> the generic type
   * @param defaultValue the default value
   * @return the map
   */
  public static <V1> IterMap<Integer, V1> create(V1 defaultValue) {
    return create(HashMapCreator.INITIAL_CAPACITY, defaultValue);
  }

  /**
   * Creates the.
   *
   * @param <K1> the generic type
   * @param <VV> the generic type
   * @param defaultValue the default value
   * @return the map
   */
  public static <VV> IterMap<Integer, VV> create(
      EntryCreator<VV> defaultValue) {
    return new DefaultIntObjMap<VV>(HashMapCreator.INITIAL_CAPACITY,
        defaultValue);
  }

  /**
   * Creates the.
   *
   * @param <K1> the generic type
   * @param <VV> the generic type
   * @param initialCapacity the initial capacity
   * @param defaultValue the default value
   * @return the map
   */
  public static <VV> IterMap<Integer, VV> create(int initialCapacity,
      VV defaultValue) {
    return create(initialCapacity, new ValueCreator<VV>(defaultValue));
  }

  /**
   * Creates the.
   *
   * @param <K1> the generic type
   * @param <VV> the generic type
   * @param initialCapacity the initial capacity
   * @param defaultValue the default value
   * @return the map
   */
  public static <VV> IterMap<Integer, VV> create(int initialCapacity,
      EntryCreator<VV> defaultValue) {
    return new DefaultIntObjMap<VV>(initialCapacity, defaultValue);
  }
}
