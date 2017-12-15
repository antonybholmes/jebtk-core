package org.jebtk.core.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Stores objects in a hashmap that uses primitive ints for keys. Attempting
 * to add an object as a key, will result in its hashcode being used for
 * the key.
 * 
 * @author antony
 *
 * @param <V>
 */
public class IntObjHashMap<V> implements IterMap<Integer, V> {

	private static final long serialVersionUID = 1L;

	/**
	 * The hash table data.
	 */
	private Entry mTable[];

	/**
	 * The total number of entries in the hash table.
	 */
	private int mCount;

	/**
	 * The table is rehashed when its size exceeds this threshold.  (The
	 * value of this field is (int)(capacity * loadFactor).)
	 *
	 * @serial
	 */
	private int mThreshold;

	/**
	 * The load factor for the hashtable.
	 *
	 * @serial
	 */
	private double mLoadFactor;

	/**
	 * Innerclass that acts as a datastructure to create a new entry in the
	 * table.
	 */
	private static class Entry implements java.util.Map.Entry<Integer, Object> {
		public final int mKey;
		public Object mValue;
		public Entry mNext;

		/**
		 * Create a new entry with the given values.
		 *
		 * @param hash The code used to hash the object with
		 * @param key The key used to enter this in the table
		 * @param value The value for this key
		 * @param next A reference to the next entry in the table
		 */
		public Entry(int key, Object value, Entry next) {
			mKey = key;
			mValue = value;
			mNext = next;
		}

		@Override
		public Integer getKey() {
			return mKey;
		}

		@Override
		public Object getValue() {
			return mValue;
		}

		@Override
		public Object setValue(Object value) {
			return null;
		}
	}

	private class IntObjIter implements Iterator<Integer> {

		private Entry mNext = null;        // next entry to return
		private int index = 0;              // current slot

		private IntObjIter(Entry[] table) {
			mTable = table;

			// advance to first entry
			if (mCount > 0) {
				Entry[] t = mTable;
				
				while (index < t.length && (mNext = t[index++]) == null);
			}
		}

		public final boolean hasNext() {
			return mNext != null;
		}

		public Integer next() {
			return nextEntry().mKey;
		}

		public final Entry nextEntry() {
			Entry e = mNext;

			if (e == null) {
				throw new NoSuchElementException();
			}

			if ((mNext = e.mNext) == null) {
				Entry[] t = mTable;
				
				while (index < t.length && (mNext = t[index++]) == null);
			}

			return e;
		}

		@Override
		public void remove() {
			// Do nothing
		}

	}

	/**
	 * Constructs a new, empty hashtable with a default capacity and load
	 * factor, which is <code>20</code> and <code>0.75</code> respectively.
	 */
	public IntObjHashMap() {
		this(20, 0.75);
	}

	/**
	 * Constructs a new, empty hashtable with the specified initial capacity
	 * and default load factor, which is <code>0.75</code>.
	 *
	 * @param  initialCapacity the initial capacity of the hashtable.
	 * @throws IllegalArgumentException if the initial capacity is less
	 *   than zero.
	 */
	public IntObjHashMap(int initialCapacity) {
		this(initialCapacity, 0.75);
	}

	/**
	 * Constructs a new, empty hashtable with the specified initial
	 * capacity and the specified load factor.
	 *
	 * @param initialCapacity the initial capacity of the hashtable.
	 * @param loadFactor the load factor of the hashtable.
	 * @throws IllegalArgumentException  if the initial capacity is less
	 *             than zero, or if the load factor is nonpositive.
	 */
	public IntObjHashMap(int initialCapacity, double loadFactor) {
		super();

		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		}

		if (loadFactor <= 0) {
			throw new IllegalArgumentException("Illegal Load: " + loadFactor);
		}

		if (initialCapacity == 0) {
			initialCapacity = 1;
		}

		mLoadFactor = loadFactor;
		mTable = new Entry[initialCapacity];
		mThreshold = (int)(initialCapacity * loadFactor);
	}

	/**
	 * Returns the number of keys in this hashtable.
	 *
	 * @return  the number of keys in this hashtable.
	 */
	public int size() {
		return mCount;
	}

	/**
	 * Tests if this hashtable maps no keys to values.
	 *
	 * @return  <code>true</code> if this hashtable maps no keys to values;
	 *          <code>false</code> otherwise.
	 */
	public boolean isEmpty() {
		return mCount == 0;
	}

	/**
	 * Tests if some key maps into the specified value in this hashtable.
	 * This operation is more expensive than the <code>containsKey</code>
	 * method.
	 *
	 * Note that this method is identical in functionality to containsValue,
	 * (which is part of the Map interface in the collections framework).
	 *
	 * @param      value   a value to search for.
	 * @return     <code>true</code> if and only if some key maps to the
	 *             <code>value</code> argument in this hashtable as
	 *             determined by the <tt>equals</tt> method;
	 *             <code>false</code> otherwise.
	 * @throws  NullPointerException  if the value is <code>null</code>.
	 * @see        #containsKey(int)
	 * @see        #containsValue(Object)
	 * @see        java.util.Map
	 */
	public boolean contains(Object value) {
		if (value == null) {
			throw new NullPointerException();
		}

		Entry tab[] = mTable;

		for (int i = tab.length - 1; i >= 0; --i) {
			for (Entry e = tab[i]; e != null; e = e.mNext) {
				if (e.mValue.equals(value)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Returns <code>true</code> if this HashMap maps one or more keys
	 * to this value.
	 *
	 * Note that this method is identical in functionality to contains
	 * (which predates the Map interface).
	 *
	 * @param value value whose presence in this HashMap is to be tested.
	 * @return boolean <code>true</code> if the value is contained
	 * @see    java.util.Map
	 * @since JDK1.2
	 */
	public boolean containsValue(Object value) {
		return contains(value);
	}

	/**
	 * Tests if the specified object is a key in this hashtable.
	 *
	 * @param  key  possible key.
	 * @return <code>true</code> if and only if the specified object is a
	 *    key in this hashtable, as determined by the <tt>equals</tt>
	 *    method; <code>false</code> otherwise.
	 * @see #contains(Object)
	 */
	public boolean containsKey(int key) {
		Entry tab[] = mTable;

		int hash = key;

		int index = indexHash(hash);

		for (Entry e = tab[index]; e != null; e = e.mNext) {
			if (e.mKey == hash) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns the value to which the specified key is mapped in this map.
	 *
	 * @param   key   a key in the hashtable.
	 * @return  the value to which the key is mapped in this hashtable;
	 *          <code>null</code> if the key is not mapped to any value in
	 *          this hashtable.
	 * @see     #put(int, Object)
	 */
	public Object get(int key) {
		Entry tab[] = mTable;
		int hash = key;
		int index = indexHash(hash);

		for (Entry e = tab[index]; e != null; e = e.mNext) {
			if (e.mKey == hash) {
				return e.mValue;
			}
		}

		return null;
	}

	protected void rehash() {
		int oldCapacity = mTable.length;
		Entry oldMap[] = mTable;

		int newCapacity = oldCapacity * 2 + 1;
		Entry newMap[] = new Entry[newCapacity];

		mThreshold = (int) (newCapacity * mLoadFactor);
		mTable = newMap;

		for (int i = oldCapacity; i-- > 0;) {
			for (Entry old = oldMap[i]; old != null;) {
				Entry e = old;
				old = old.mNext;

				int index = indexHash(e.mKey); //(e.mHash & 0x7FFFFFFF) % newCapacity;
				e.mNext = newMap[index];
				newMap[index] = e;
			}
		}
	}

	/**
	 * Maps the specified <code>key</code> to the specified
	 * <code>value</code> in this hashtable. The key cannot be
	 * <code>null</code>. 
	 *
	 * The value can be retrieved by calling the <code>get</code> method
	 * with a key that is equal to the original key.
	 *
	 * @param key     the hashtable key.
	 * @param value   the value.
	 * @return the previous value of the specified key in this hashtable,
	 *         or <code>null</code> if it did not have one.
	 * @throws  NullPointerException  if the key is <code>null</code>.
	 * @see     #get(int)
	 */
	public Object put(int key, Object value) {
		// Makes sure the key is not already in the hashtable.
		Entry tab[] = mTable;
		int hash = key;
		int index = indexHash(hash);

		for (Entry e = tab[index]; e != null; e = e.mNext) {
			if (e.mKey == hash) {
				Object old = e.mValue;
				e.mValue = value;

				return old;
			}
		}

		if (mCount >= mThreshold) {
			// Rehash the table if the threshold is exceeded
			rehash();

			tab = mTable;
			index = indexHash(hash);
		}

		// Creates the new entry.
		Entry e = new Entry(hash, value, tab[index]);
		tab[index] = e;

		++mCount;

		return null;
	}

	/**
	 * Removes the key (and its corresponding value) from this
	 * hashtable.
	 *
	 * This method does nothing if the key is not present in the
	 * hashtable.
	 *
	 * @param   key   the key that needs to be removed.
	 * @return  the value to which the key had been mapped in this hashtable,
	 *          or <code>null</code> if the key did not have a mapping.
	 */
	public Object remove(int key) {
		Entry tab[] = mTable;
		int hash = key;

		int index = indexHash(hash);

		for (Entry e = tab[index], prev = null; e != null; prev = e, e = e.mNext) {
			if (e.mKey == hash) {
				if (prev != null) {
					prev.mNext = e.mNext;
				} else {
					tab[index] = e.mNext;
				}

				mCount--;

				Object oldValue = e.mValue;
				e.mValue = null;
				return oldValue;
			}
		}

		return null;
	}

	public synchronized void clear() {
		Entry tab[] = mTable;

		for (int index = tab.length; --index >= 0;) {
			tab[index] = null;
		}

		mCount = 0;
	}

	/**
	 * Return the index of a key.
	 * 
	 * @param hash
	 * @return
	 */
	private int indexHash(int hash) {
		return (hash & 0x7FFFFFFF) % mTable.length;
	}

	@Override
	public boolean containsKey(Object key) {
		return containsKey(key.hashCode());
	}

	@Override
	public Set<java.util.Map.Entry<Integer, V>> entrySet() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(Object key) {
		return (V)get(key.hashCode());
	}

	@Override
	public Set<Integer> keySet() {
		Set<Integer> ret = new HashSet<Integer>(mTable.length);

		for (int i = 0; i < mTable.length; ++i) {
			if (mTable[i] != null) {
				ret.add(i);
			}
		}

		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V put(Integer key, V value) {
		return (V)put(key.hashCode(), value);
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends V> m) {
		for (int key : m.keySet()) {
			put(key, m.get(key));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public V remove(Object key) {
		return (V)remove(key.hashCode());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<V> values() {
		List<V> ret = new ArrayList<V>(mTable.length);

		for (Entry e : mTable) {
			if (e != null) {
				//ret.add((V)e.mValue);

				for (Entry e2 = e; e2 != null; e2 = e2.mNext) {
					if (e2 != null) {
						ret.add((V)e2.mValue);
					}
				}
			}
		}

		return ret;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new IntObjIter(mTable);
	}
	
	@Override
	public Integer first() {
		return iterator().next();
	}
}