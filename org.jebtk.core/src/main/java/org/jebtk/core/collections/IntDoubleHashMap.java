package org.jebtk.core.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.jebtk.core.collections.IntHashMap.IntEntry;

/**
 * IntIntMap2 without states array. We introduce one extra pairs of fields - for
 * key=0, which is used as 'used' flag
 */
public class IntDoubleHashMap extends IntHashMap<Double> {
  
  private double[] mValues;

  public IntDoubleHashMap(final int size, final double fillFactor) {
    super(size, fillFactor);
  }

  @Override
  protected void resize(int newCapacity) {
    mValues = new double[newCapacity];
  }

  @Override
  protected Double getValue(int index) {
    return mValues[index];
  }

  @Override
  protected void setValue(int key, Double value) {
    mValues[key] = value;
  }
  
  @Override
  protected void rehash(final int newCapacity, final int oldCapacity) {
    final int[] oldKeys = keys();
    
    final double[] oldValues = mValues;

    rehashKeys(newCapacity);
    
    mValues = new double[newCapacity];
    
    mSize = mHasFreeKey ? 1 : 0;

    for (int i = 0; i < oldCapacity; ++i) {
      final int oldKey = oldKeys[i];
      
      if (oldKey != FREE_KEY) {
        put(oldKey, oldValues[i]);
      }
    }
  }
  
  @Override
  public boolean containsValue(Object o) {
    double value = (double)o;
    
    if (mHasFreeKey && mFreeValue == value) {
      return true;
    }
    
    for (double v : mValues) {
      if (v == value) {
        return true;
      }
    }
    
    return false;
  }
  
  @Override
  public Collection<Double> values() {
    List<Double> ret = new ArrayList<Double>(mSize);
    
    for (int i = 0; i < mKeys.length; ++i) {
      if (mKeys[i] == FREE_KEY) {
        if (mHasFreeKey) {
          ret.add(mFreeValue);
        }
      } else {
        ret.add(mFreeValue);
      }
    }
    
    return ret;
  }
  
  @Override
  public Set<Entry<Integer, Double>> entrySet() {
    Set<Entry<Integer, Double>> ret = new HashSet<Entry<Integer, Double>>(mSize);

    for (int i = 0; i < mKeys.length; ++i) {
      final int key = mKeys[i];
      
      if (key == FREE_KEY) {
        if (mHasFreeKey) {
          ret.add(new IntEntry(key, mFreeValue));
        }
      } else {
        ret.add(new IntEntry(key, mValues[i]));
      }
    }

    return ret;
  }
}
