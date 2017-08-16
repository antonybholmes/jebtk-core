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

// TODO: Auto-generated Javadoc
/**
 * The Class FilterStream.
 *
 * @param <T> the generic type
 */
public class FilterStream<T> extends ContainerStream<T> {

	/** The m filter. */
	private Filter<T> mFilter;
	
	/** The m current. */
	private T mCurrent;
	
	/** The m next. */
	private T mNext;

	/**
	 * Instantiates a new filter stream.
	 *
	 * @param stream the stream
	 * @param filter the filter
	 */
	public FilterStream(Stream<T> stream, Filter<T> filter) {
		super(stream);

		mFilter = filter;

		// Set the lookahead to the first item.
		mNext = getNext();
	}

	/* (non-Javadoc)
	 * @see org.abh.common.stream.StreamIterator#peek()
	 */
	@Override
	public T peek() {
		if (mCurrent != null) {
			// We are sitting on a valid item
			return mCurrent;
		} else if (mNext != null) {
			// We are at the start of the iterator so only mNext is populated
			// so we use that as the current peek
			return mNext;
		} else {
			// There is nothing to peek at.
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return mNext != null;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public T next() {
		// We set the current to what we have already read
		mCurrent = mNext;

		// Move the pointer to the next item
		mNext = getNext();

		return mCurrent;
	}

	/**
	 * Returns the next item in the parent stream that matches the filter.
	 *
	 * @return the next
	 */
	private T getNext() {
		T next = null;

		while (mStream.hasNext()) {
			T item = mStream.next();

			if (item != null) {
				if (mFilter.keep(item)) {
					return item;
				}
			}
		}

		return next;
	}
}
