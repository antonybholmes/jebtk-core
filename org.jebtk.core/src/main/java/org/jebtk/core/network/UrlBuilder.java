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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jebtk.core.text.TextUtils;

/**
 * Easily build urls from string components.
 * 
 * @author Antony Holmes
 *
 */
public class UrlBuilder implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * A regex for the url minus any parameters.
   */
  private static final Pattern HOST_REGEX =
      Pattern.compile("((https?:\\/\\/)?(localhost|([\\w-]+(\\.[\\w-]+)+))(:\\d+)?)");

  private static final Pattern PATH_REGEX =
      Pattern.compile("[^\\/:]\\/([\\/\\w\\.-]+[^\\/])");

  //private static final Pattern PORT_REGEX = Pattern.compile(":(\\d+)");

  private static final Pattern PART_REGEX =
      Pattern.compile("([\\w-]+)");

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
  private List<Param> mParams = new ArrayList<Param>();

  private int mPort = -1;

  private String mServer;

  private String mPath;

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
    mServer = getHost(server);
    mPath = getPath(server);
    
    mParts.add(mServer);
    mParts.add(mPath);
  }

  /*
  public UrlBuilder(String server, int port) {
    StringBuilder buffer = new StringBuilder(getHost(server));

    Matcher matcher = PORT_REGEX.matcher(buffer);

    // If port already specified, replace it
    if (matcher.find()) {
      buffer.replace(matcher.start(1), matcher.end(1), Integer.toString(port));
    } else {
      buffer.append(PORT_SEPARATOR).append(port).toString();
    }

    mServer = buffer.toString();
    mPort = port;
  }
   */

  /**
   * Instantiates a new url builder.
   *
   * @param urlBuilder the url builder
   * @param parts the parts
   */
  public UrlBuilder(UrlBuilder urlBuilder) {
    mServer = urlBuilder.mServer;
    mPath = urlBuilder.mPath;
    mPort = urlBuilder.mPort;
    mParts.addAll(urlBuilder.mParts);
    mParams.addAll(urlBuilder.mParams);
  }

  public String getServer() {
    return mServer;
  }

  public String getPath() {
    return mPath;
  }

  public int getPort() {
    return mPort;
  }

  /**
   * Adds the path.
   *
   * @param path the path
   * @return the url builder
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public UrlBuilder resolve(String path) {
    UrlBuilder ret = new UrlBuilder(this);

    ret.mParts.add(clean(path));

    return ret;
  }

  /**
   * Resolve.
   *
   * @param path the path
   * @return the url builder
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public UrlBuilder resolve(Object path) {
    return resolve(path.toString());
  }

  /**
   * Adds the path.
   *
   * @param path the path
   * @return the url builder
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public UrlBuilder resolve(int path) {
    return resolve(Integer.toString(path));
  }

  /**
   * Adds the path.
   *
   * @param path the path
   * @return the url builder
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public UrlBuilder resolve(double path) {
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
  public UrlBuilder param(String name, double value) {
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
  public UrlBuilder param(String name, int value) {
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
  public UrlBuilder param(String name, Object value) {
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
  public UrlBuilder param(String name, String value) {
    return param(new StaticParam(name, value));
  }

  public UrlBuilder param(Param param) {
    UrlBuilder url = new UrlBuilder(this);

    // Don't add if param values are null
    if (!TextUtils.isNullOrEmpty(param.getName()) &&
        !TextUtils.isNullOrEmpty(param.getValue())) {
      url.mParams.add(param);
    }
    
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
  public UrlBuilder params(String name, Object... values) {
    UrlBuilder url = new UrlBuilder(this);

    for (Object value : values) {
      url.mParams.add(new StaticParam(name, value));
    }

    return this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder();

    //if (mPath.length() > 0) {
    //  buffer.append(mPath);
    //}

    //buffer.append(URL_SEPARATOR);

    TextUtils.join(mParts, URL_SEPARATOR, buffer);

    if (mParams.size() > 0) {
      buffer.append("?");

      TextUtils.join(mParams, PARAM_SEPARATOR, buffer);
    }

    return buffer.toString();
  }

  /**
   * Converts the URL construct into a URL for compatibility.
   *
   * @return the url
   * @throws MalformedURLException the malformed url exception
   */
  public URL toURL() throws MalformedURLException {
    return new URL(toString());
  }

  /**
   * Clean.
   *
   * @param text the text
   * @return the string
   * @throws UnsupportedEncodingException the unsupported encoding exception
   */
  public static String clean(String text) {
    String ret = TextUtils.EMPTY_STRING;

    Matcher matcher = PART_REGEX.matcher(text);

    if (matcher.find()) {
      ret = encode(text);
    }

    return ret;
  }

  public static String encode(String text) {
    String ret = TextUtils.EMPTY_STRING;

    try {
      ret = URLEncoder.encode(text, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    return ret;
  }

  /**
   * Sanitize.
   *
   * @param text the text
   * @return the string
   */
  private static String getHost(String server) {
    Matcher matcher = HOST_REGEX.matcher(server);

    if (matcher.find()) {
      return matcher.group(1); //sanitize(server);
    } else {
      return TextUtils.EMPTY_STRING;
    }
  }

  private static String getPath(String server) {
    Matcher matcher = PATH_REGEX.matcher(server);

    if (matcher.find()) {
      return matcher.group(1); //sanitize(server);
    } else {
      return TextUtils.EMPTY_STRING;
    }
  }
}
