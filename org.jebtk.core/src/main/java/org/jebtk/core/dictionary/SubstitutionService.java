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
package org.jebtk.core.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jebtk.core.io.Io;
import org.jebtk.core.text.TextUtils;

// TODO: Auto-generated Javadoc
/**
 * Provides a substitution service so strings/words can be mapped to an
 * alternative. This is designed for cases where user data has common
 * misspellings etc.
 * 
 * @author Antony Holmes Holmes
 *
 */
public class SubstitutionService {

  /**
   * The substitutions.
   */
  private Map<String, String> substitutions = new HashMap<String, String>();

  /**
   * The constant DEFAULT_FILE.
   */
  public static final File DEFAULT_FILE = new File("res/substitutions.txt");

  /**
   * The constant instance.
   */
  private static final SubstitutionService instance = new SubstitutionService();

  /**
   * Gets the single instance of SubstitutionService.
   *
   * @return single instance of SubstitutionService
   */
  public static final SubstitutionService getInstance() {
    return instance;
  }

  /**
   * Load xml.
   *
   * @param file the file
   */
  public void loadXml(File file) {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();

      SubstitutionXmlHandler handler = new SubstitutionXmlHandler(this);

      saxParser.parse(file, handler);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Load tsv file.
   *
   * @param file the file
   */
  public void loadTSVFile(File file) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));

      String line;

      try {
        while ((line = reader.readLine()) != null) {
          if (Io.isEmptyLine(line)) {
            continue;
          }

          List<String> tokens = TextUtils.fastSplit(line,
              TextUtils.TAB_DELIMITER);

          addSubstitution(tokens.get(0), tokens.get(1));
        }
      } finally {
        reader.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Adds the substitution.
   *
   * @param word the word
   * @param subsitute the subsitute
   */
  public void addSubstitution(String word, String subsitute) {
    substitutions.put(word, subsitute);
  }

  /**
   * Gets the substitute.
   *
   * @param word the word
   * @return the substitute
   */
  public String getSubstitute(String word) {
    if (word == null) {
      return null;
    }

    if (!substitutions.containsKey(word)) {
      return word;
    }

    return substitutions.get(word);
  }

  /**
   * Returns the substitute of a given word. If the word has no substitute, the
   * word itself will be returned.
   * 
   * @param word
   * @return
   */
  /*
   * public String replace(String text) { if (word == null) { return null; }
   * 
   * String ret = word;
   * 
   * for (String w : substitutions.keySet()) { ret = ret.replaceAll(w,
   * substitutions.get(w)); }
   * 
   * return ret; }
   */
}
