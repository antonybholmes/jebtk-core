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

import java.util.Set;

/**
 * Creates a simple map of sets where the primary and secondary maps have the
 * same key type (e.g. for mimicking a two dimensional structure).
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public abstract class SetMultiMap<K, V> extends DefaultMultiMap<K, V, Set<V>> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new sets the multi map.
   *
   * @param initialCapacity the initial capacity
   * @param defaultValue the default value
   */
  public SetMultiMap(int initialCapacity,
      CollectionCreator<V, Set<V>> defaultValue) {
    super(initialCapacity, defaultValue);
  }

}
