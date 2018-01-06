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

// TODO: Auto-generated Javadoc
/**
 * The Class TreeMapCreator.
 *
 * @param <K>
 *          the key type
 * @param <V>
 *          the value type
 */
public class IntObjMapCreator<V> implements IterMapCreator<Integer, V> {

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.collections.EntryCreator#create()
   */
  @Override
  public IterMap<Integer, V> newEntry() {
    return new IntObjHashMap<V>();
  }

  public static <VV> IterMapCreator<Integer, VV> create() {
    return new IntObjMapCreator<VV>();
  }
}