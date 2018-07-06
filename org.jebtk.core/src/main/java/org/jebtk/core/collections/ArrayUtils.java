package org.jebtk.core.collections;

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
}
