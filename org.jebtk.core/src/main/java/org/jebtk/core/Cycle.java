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

import java.util.ArrayList;
import java.util.List;

/**
 * List that allows continuous cycling through items.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class Cycle<T> {

  /**
   * The member items.
   */
  private List<T> mItems = new ArrayList<T>();

  /**
   * The member p.
   */
  private int mP = -1;

  /**
   * Adds the.
   *
   * @param item the item
   */
  public void add(T item) {
    mItems.add(item);
  }

  /**
   * Next.
   *
   * @return the t
   */
  public T next() {
    mP = (mP + 1) % mItems.size();

    return mItems.get(mP);
  }

  /**
   * Reset.
   */
  public void reset() {
    mP = -1;
  }
}
