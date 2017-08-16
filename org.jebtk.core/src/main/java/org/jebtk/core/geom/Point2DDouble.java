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

// TODO: Auto-generated Javadoc
/**
 * An immutable integer point.
 * 
 * @author Antony Holmes Holmes
 *
 */
public class Point2DDouble implements Comparable<Point2DDouble> {
	
	/**
	 * The member x.
	 */
	private double mX;
	
	/**
	 * The member y.
	 */
	private double mY;

	/**
	 * Instantiates a new point2 d double.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Point2DDouble(double x, double y) {
		mX = x;
		mY = y;
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public double getX() {
		return mX;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public double getY() {
		return mY;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return mX + " " + mY;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Point2DDouble o) {
		if (mX > o.mX || mY > o.mY) {
			return 1;
		} else if (mX < o.mX || mY < o.mY) {
			return -1;
		} else {
			return 0;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Point2DDouble)) {
			return false;
		}
		
		return compareTo((Point2DDouble)o) == 0;
	}
}
