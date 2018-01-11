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
package org.jebtk.core.pool;

// TODO: Auto-generated Javadoc
/**
 * A pool can dynamically create objects in the pool.
 *
 * @author Antony Holmes Holmes
 * @param <T> the generic type
 */
public interface ObjectCreator<T> {

  /**
   * Should create a new instance of an object.
   *
   * @return the t
   */
  T create();
}
