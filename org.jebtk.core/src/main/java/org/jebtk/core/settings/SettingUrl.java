/**
 * Copyright 2017 Antony Holmes
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
package org.jebtk.core.settings;

import org.jebtk.core.io.PathUtils;
import org.jebtk.core.network.UrlBuilder;
import org.jebtk.core.path.Path;
import org.jebtk.core.text.TextUtils;

/**
 * The Class SettingUrl.
 */
public class SettingUrl extends Setting {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The m value. */
  private UrlBuilder mValue;

  /**
   * Instantiates a new setting url.
   *
   * @param path the path
   * @param value the value
   * @param description the description
   * @param locked the locked
   */
  public SettingUrl(Path path, UrlBuilder value, String description,
      boolean locked) {
    super(path, description, locked);

    mValue = value;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.settings.Setting#getAsString()
   */
  @Override
  public String getAsString() {
    return mValue.toString();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.settings.Setting#getAsFile()
   */
  @Override
  public java.nio.file.Path getAsFile() {
    return PathUtils.getPath(getAsString());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.settings.Setting#getAsUrlBuilder()
   */
  @Override
  public UrlBuilder getAsUrlBuilder() {
    return mValue;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.settings.Setting#toString()
   */
  @Override
  public String toString() {
    return new StringBuilder("url_setting:").append(mPath.toString())
        .append(TextUtils.EQUALS_DELIMITER).append(mValue).toString();
  }
}
