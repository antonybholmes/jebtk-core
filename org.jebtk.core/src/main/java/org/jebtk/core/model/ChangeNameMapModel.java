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
package org.jebtk.core.model;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeEventProducer;

/**
 * Generic model for sharing named items.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class ChangeNameMapModel<T extends ChangeEventProducer> extends NameMapModel<T>
    implements org.jebtk.core.event.ChangeListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.model.NameMapModel#add(java.lang.String, java.lang.Object)
   */
  @Override
  public void add(String name, T item) {
    item.addChangeListener(this);

    super.add(name, item);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.event.ChangeListener#changed(org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void changed(ChangeEvent e) {
    fireChanged();
  }

}