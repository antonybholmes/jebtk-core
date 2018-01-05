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

import java.io.UnsupportedEncodingException;

// TODO: Auto-generated Javadoc
/**
 * Defaults to building a HTTP url.
 * 
 * @author Antony Holmes Holmes
 *
 */
public class HttpUrlBuilder extends UrlBuilder {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new http url builder.
   *
   * @throws UnsupportedEncodingException
   *           the unsupported encoding exception
   */
  public HttpUrlBuilder() throws UnsupportedEncodingException {
    super("http://");
  }
}
