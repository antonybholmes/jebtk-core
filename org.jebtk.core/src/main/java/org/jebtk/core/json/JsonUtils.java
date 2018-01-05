package org.jebtk.core.json;

/**
 * Helper class for dealing with JSON strings.
 * 
 * @author antony
 *
 */
public class JsonUtils {
  private JsonUtils() {
    // Do nothing
  }

  /**
   * Add JSON array characters around a JSON string. Useful to convert a list of
   * JSON values into a JSON array.
   * 
   * @param json
   * @return
   */
  public static String asArray(String json) {
    return "[" + json + "]";
  }
}
