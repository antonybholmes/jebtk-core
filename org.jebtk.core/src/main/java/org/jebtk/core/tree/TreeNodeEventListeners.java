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
package org.jebtk.core.tree;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.EventProducer;

// TODO: Auto-generated Javadoc
/**
 * The basis for model controls in a model view controller setup.
 * 
 * @author Antony Holmes Holmes
 *
 */
public class TreeNodeEventListeners extends EventProducer<TreeNodeEventListener>
    implements TreeNodeEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.tree.TreeNodeEventProducer#addTreeNodeListener(org.abh.lib.
   * tree. TreeNodeEventListener)
   */
  public void addTreeNodeListener(TreeNodeEventListener l) {
    mListeners.add(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.tree.TreeNodeEventProducer#removeTreeNodeListener(org.abh.lib.
   * tree.TreeNodeEventListener)
   */
  public void removeTreeNodeListener(TreeNodeEventListener l) {
    mListeners.remove(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.tree.TreeNodeEventProducer#fireTreeNodeChanged(org.abh.lib.
   * event. ChangeEvent)
   */
  public void fireTreeNodeChanged(ChangeEvent e) {
    for (TreeNodeEventListener l : mListeners) {
      l.nodeChanged(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.tree.TreeNodeEventProducer#fireTreeNodeUpdated(org.abh.lib.
   * event. ChangeEvent)
   */
  public void fireTreeNodeUpdated(ChangeEvent e) {
    for (TreeNodeEventListener l : mListeners) {
      l.nodeUpdated(e);
    }
  }
}