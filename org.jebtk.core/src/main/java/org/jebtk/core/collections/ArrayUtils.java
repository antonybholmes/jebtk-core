package org.jebtk.core.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ArrayUtils {
  private ArrayUtils() {
    // Do nothing
  }

  public static int[] array(int start, int end) {
    return array(start, end, 1);
  }

  private static int[] array(int start, int end, int inc) {
    int n = end - start + 1;

    n += n % inc;

    int [] ret = new int[n];

    for (int i = 0; i < n; ++i) {
      ret[i] = start++;
    }

    return ret;
  }

  public static <T> List<T> toList(T[] items) {
    return toList(items, items.length);
  }

  /**
   * Convert an array to a list.
   * 
   * @param items   An array.
   * @param n       How many items to copy from the array.
   * @return
   */
  public static <T> List<T> toList(T[] items, int n) {
    if (isNullOrEmpty(items) || n < 1) {
      return Collections.emptyList();
    }

    List<T> ret = new ArrayList<T>(n);

    for (int i = 0; i < n; ++i) {
      ret.add(items[i]);
    }

    return ret;
  }

  public static <T> List<T> toUniqueList(T[] items) {
    return toUniqueList(items, items.length);
  }

  /**
   * Convert array to list and remove duplicates.
   * 
   * @param items
   * @param n
   * @return
   */
  public static <T> List<T> toUniqueList(T[] items, int n) {
    if (isNullOrEmpty(items) || n < 1) {
      return Collections.emptyList();
    }

    List<T> ret = new ArrayList<T>(n);
    Set<T> used = new HashSet<T>(n);

    for (int i = 0; i < n; ++i) {
      if (!used.contains(items[i])) {
        ret.add(items[i]);
        used.add(items[i]);
      }
    }

    return ret;
  }

  /**
   * Returns true if the array is null or empty.
   * 
   * @param items
   * @return
   */
  public static boolean isNullOrEmpty(Object[] items) {
    return items == null || items.length == 0;
  }
}
