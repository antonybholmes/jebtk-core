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
 * The Class doublePosition provides an immutable x y position in contrast
 * to the java.awt.Podouble.
 */
public class DoublePos {
	
	/**
	 * The member x.
	 */
	double mX;
	
	/**
	 * The member y.
	 */
	double mY;

	/**
	 * Instantiates a new double position.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public DoublePos(double x, double y) {
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
		return "[" + mX + ", " + mY +"]";
	}
}
