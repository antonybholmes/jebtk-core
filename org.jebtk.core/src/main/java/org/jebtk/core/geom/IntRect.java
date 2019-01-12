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

import java.awt.Point;
import java.awt.Rectangle;

/**
 * Immutable integer rectangle.
 * 
 * @author Antony Holmes
 *
 */
public class IntRect extends IntPos2D {

  /** The Constant ZERO_RECT. */
  public static final IntRect ZERO_RECT = new IntRect(0, 0, 0, 0);

  /**
   * The member w.
   */
  public final int mW;

  /**
   * The member h.
   */
  public final int mH;

  /**
   * Instantiates a new int rect.
   *
   * @param x the x
   * @param y the y
   * @param w the w
   * @param h the h
   */
  public IntRect(int x, int y, int w, int h) {
    super(x, y);

    mW = w;
    mH = h;
  }

  /**
   * Gets the w.
   *
   * @return the w
   */
  public int getW() {
    return mW;
  }

  /**
   * Gets the h.
   *
   * @return the h
   */
  public int getH() {
    return mH;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.IntPosition#toString()
   */
  @Override
  public String toString() {
    return "[" + mX + " " + mY + " " + mW + " " + mH + "]";
  }

  /**
   * Contains.
   *
   * @param p the p
   * @return true, if successful
   */
  public boolean contains(Point p) {
    return contains(p, 0);
  }

  /**
   * Contains.
   *
   * @param p the p
   * @param padding the padding
   * @return true, if successful
   */
  public boolean contains(Point p, int padding) {
    return contains(p.x, p.y, padding);
  }

  /**
   * Contains.
   *
   * @param p the p
   * @return true, if successful
   */
  public boolean contains(IntPos2D p) {
    return contains(p, 0);
  }

  /**
   * Contains.
   *
   * @param p the p
   * @param padding the padding
   * @return true, if successful
   */
  public boolean contains(IntPos2D p, int padding) {
    return contains(p.getX(), p.getY(), padding);
  }

  /**
   * Returns true if the point is within the bounds of the rectangle.
   *
   * @param x the x
   * @param y the y
   * @return true, if successful
   */
  public boolean contains(int x, int y) {
    return contains(x, y, 0);
  }

  /**
   * Returns true if the point x y is contained within the rectangle plus a
   * padding allowance.
   *
   * @param x the x
   * @param y the y
   * @param padding the padding
   * @return true, if successful
   */
  public boolean contains(int x, int y, int padding) {
    return x >= mX - padding && x <= mX + mW + padding && y >= mY - padding
        && y <= mY + mH + padding;
  }

  /**
   * Creates the rect.
   *
   * @param r the r
   * @return the int rect
   */
  public static IntRect createRect(Rectangle r) {
    return new IntRect(r.x, r.y, r.width, r.height);
  }

  /**
   * Convert an int rect to a rectangle.
   * 
   * @param r
   * @return
   */
  public static Rectangle toRectangle(IntRect r) {
    return new Rectangle(r.getX(), r.getY(), r.getW(), r.getH());
  }
}
