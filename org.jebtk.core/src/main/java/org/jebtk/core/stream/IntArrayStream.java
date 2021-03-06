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
package org.jebtk.core.stream;

/**
 * Holds a reference to an existing stream.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class IntArrayStream extends Stream<Integer> {

  private final int[] mArray;
  private int mPc = 0;

  /**
   * Instantiates a new container stream.
   *
   * @param stream the stream
   */
  public IntArrayStream(int... array) {
    mArray = array;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Iterator#hasNext()
   */
  @Override
  public boolean hasNext() {
    return mPc < mArray.length - 1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Iterator#next()
   */
  @Override
  public Integer next() {
    return mArray[mPc++];
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.stream.Stream#size()
   */
  @Override
  public int size() {
    return mArray.length;
  }
}
