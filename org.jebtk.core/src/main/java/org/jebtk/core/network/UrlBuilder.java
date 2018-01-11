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
package org.jebtk.core.network;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.text.TextUtils;

// TODO: Auto-generated Javadoc
/**
 * Represents a REST URL.
 * 
 * @author Antony Holmes Holmes
 *
 */
public class UrlBuilder implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant URL_SEPARATOR.
   */
  public static final String URL_SEPARATOR = "/";

  /**
   * The constant PORT_SEPARATOR.
   */
  public static final String PORT_SEPARATOR = ":";

  /**
   * The constant PARAM_SEPARATOR.
   */
  private static final String PARAM_SEPARATOR = "&";

  /**
   * The member parts.
   */
  private List<String> mParts = new ArrayList<String>();

  /**
   * The member params.
   */
  private List<String> mParams = new ArrayList<String>();

  /**
   * Instantiates a new url builder.
   *
   * @param server the server
   */
  public UrlBuilder(URL server) {
    this(server.toString());
  }

  /**
   * Instantiates a new url builder.
   *
   * @param server the server
   */
  public UrlBuilder(String server) {
    mParts.add(sanitize(server));
  }

  /**
   * Instantiates a new url builder.
   *
   * @param server the server
   * @param port the port
   */
  public UrlBuilder(String server, int port) {
    mParts.add(new StringBuilder(sanitize(server)).append(PORT_SEPARATOR)
        .append(port).toString());
  }

  /**
   * Instantiates a new url builder.
   *
   * @param urlBuilder the url builder
   * @param parts the parts
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public UrlBuilder(UrlBuilder urlBuilder, String... parts)
      throws UnsupportedEncodingException {
    mParts.addAll(urlBuilder.mParts);
    mParams.addAll(urlBuilder.mParams);

    for (String part : parts) {
      mParts.add(clean(part));
    }
  }

  /**
   * Adds the path.
   *
   * @param path the path
   * @return the url builder
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public UrlBuilder resolve(String path) throws UnsupportedEncodingException {
    return new UrlBuilder(this, path);
  }

  /**
   * Resolve.
   *
   * @param path the path
   * @return the url builder
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public UrlBuilder resolve(Object path) throws UnsupportedEncodingException {
    return resolve(path.toString());
  }

  /**
   * Adds the path.
   *
   * @param path the path
   * @return the url builder
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public UrlBuilder resolve(int path) throws UnsupportedEncodingException {
    return resolve(Integer.toString(path));
  }

  /**
   * Adds the path.
   *
   * @param path the path
   * @return the url builder
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public UrlBuilder resolve(double path) throws UnsupportedEncodingException {
    return resolve(Double.toString(path));
  }

  /**
   * Adds the param.
   *
   * @param name the name
   * @param value the value
   * @return the url builder
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public UrlBuilder param(String name, double value)
      throws UnsupportedEncodingException {
    return param(name, Double.toString(value));
  }

  /**
   * Adds the param.
   *
   * @param name the name
   * @param value the value
   * @return the url builder
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public UrlBuilder param(String name, int value)
      throws UnsupportedEncodingException {
    return param(name, Integer.toString(value));
  }

  /**
   * Param.
   *
   * @param name the name
   * @param value the value
   * @return the url builder
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public UrlBuilder param(String name, Object value)
      throws UnsupportedEncodingException {
    return param(name, value.toString());
  }

  /**
   * Adds the param.
   *
   * @param name the name
   * @param value the value
   * @return the url builder
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public UrlBuilder param(String name, String value)
      throws UnsupportedEncodingException {
    UrlBuilder url = new UrlBuilder(this);

    url.mParams.add(getParamString(name, value));

    return url;
  }

  /**
   * Add multiple parameters with the same name.
   *
   * @param name the name
   * @param values the values
   * @return the url builder
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public UrlBuilder params(String name, Object... values)
      throws UnsupportedEncodingException {
    UrlBuilder url = new UrlBuilder(this);

    for (Object value : values) {
      url.mParams.add(getParamString(name, value));
    }

    return this;
  }

  /**
   * Clean.
   *
   * @param text the text
   * @return the string
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  protected static String clean(String text)
      throws UnsupportedEncodingException {
    return URLEncoder.encode(sanitize(text), "UTF-8");
  }

  /**
   * Sanitize.
   *
   * @param text the text
   * @return the string
   */
  protected static String sanitize(String text) {
    if (text == null) {
      return TextUtils.EMPTY_STRING;
    }

    if (text.endsWith("/")) {
      return text.substring(0, text.length() - 1);
    } else {
      return text;
    }
  }

  /**
   * Gets the param string.
   *
   * @param name the name
   * @param value the value
   * @return the param string
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  protected static String getParamString(String name, int value)
      throws UnsupportedEncodingException {
    return getParamString(name, Integer.toString(value));
  }

  /**
   * Gets the param string.
   *
   * @param name the name
   * @param value the value
   * @return the param string
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  protected static String getParamString(String name, double value)
      throws UnsupportedEncodingException {
    return getParamString(name, Double.toString(value));
  }

  /**
   * Gets the param string.
   *
   * @param name the name
   * @param value the value
   * @return the param string
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  protected static String getParamString(String name, Object value)
      throws UnsupportedEncodingException {
    return getParamString(name, value.toString());
  }

  /**
   * Gets the param string.
   *
   * @param name the name
   * @param value the value
   * @return the param string
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  protected static String getParamString(String name, String value)
      throws UnsupportedEncodingException {
    return new StringBuilder(clean(name)).append("=").append(clean(value))
        .toString();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder(
        TextUtils.join(mParts, URL_SEPARATOR));

    if (mParams.size() > 0) {
      buffer.append("?").append(TextUtils.join(mParams, PARAM_SEPARATOR));
    }

    return buffer.toString();
  }

  /**
   * Converts the URL construct into a URL for compatibility.
   *
   * @return the url
   * @throws MalformedURLException the malformed url exception
   */
  public URL toUrl() throws MalformedURLException {
    return new URL(toString());
  }

  /**
   * The main method.
   *
   * @param args the arguments
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public static void main(String[] args) throws UnsupportedEncodingException {
    System.err.println(URLEncoder.encode("/file/path", "UTF-8"));
  }

}
