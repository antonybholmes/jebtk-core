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
package org.jebtk.core.geom;

/**
 * The Class IntPos provides an immutable x y position in contrast to the
 * java.awt.Point.
 */
public class IntPos2D implements Comparable<IntPos2D> {

  /**
   * The member x.
   */
  public final int mX;

  /**
   * The member y.
   */
  public final int mY;

  /**
   * Instantiates a new int position.
   *
   * @param x the x
   * @param y the y
   */
  public IntPos2D(int x, int y) {
    mX = x;
    mY = y;
  }

  /**
   * Gets the x.
   *
   * @return the x
   */
  public int getX() {
    return mX;
  }

  /**
   * Gets the y.
   *
   * @return the y
   */
  public int getY() {
    return mY;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "[" + mX + ", " + mY + "]";
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof IntPos2D) {
      return compareTo((IntPos2D) o) == 0;
    } else {
      return false;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(IntPos2D p) {
    if (mX > p.mX) {
      if (mY > p.mY) {
        return 1;
      } else {
        return -1;
      }
    } else if (mX < p.mX) {
      if (mY > p.mY) {
        return 1;
      } else {
        return -1;
      }
    } else {
      // Same x so just consider vertical position

      if (mY > p.mY) {
        return 1;
      } else if (mY < p.mY) {
        return -1;
      } else {
        return 0;
      }
    }
  }

  /**
   * Creates the.
   *
   * @param x the x
   * @param y the y
   * @return the int pos
   */
  public static IntPos2D create(int x, int y) {
    return new IntPos2D(x, y);
  }

  /**
   * Creates the.
   *
   * @param x the x
   * @param y the y
   * @return the int pos
   */
  public static IntPos2D create(long x, long y) {
    return new IntPos2D((int) x, (int) y);
  }

  /**
   * Creates the.
   *
   * @param x the x
   * @param y the y
   * @return the int pos
   */
  public static IntPos2D create(double x, double y) {
    return new IntPos2D((int) x, (int) y);
  }
}
