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
package org.jebtk.core.settings;

// TODO: Auto-generated Javadoc
/**
 * The class StyleService.
 */
public class StyleService extends KeyService {
	
	/**
	 * The constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The constant INSTANCE.
	 */
	private static final StyleService INSTANCE = new StyleService();
	
	/**
	 * Gets the single instance of StyleService.
	 *
	 * @return single instance of StyleService
	 */
	public static final StyleService getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Instantiates a new style service.
	 */
	private StyleService() {
		super("styles");
	}
}
