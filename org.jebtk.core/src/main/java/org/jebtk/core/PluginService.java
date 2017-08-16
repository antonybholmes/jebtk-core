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
package org.jebtk.core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.jebtk.core.collections.UniqueArrayList;
import org.jebtk.core.io.DirectoryFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// TODO: Auto-generated Javadoc
/**
 * Loads plugin class definitions from file so they can be instantiated.
 * Plugins must be in there own directory, for example annotations, within
 * the main plugin directory. Within each individual plugin folder, the
 * classes must be in the same package hierarchy as they were designed.
 * The plugin loader will scan the directories and build the packages
 * from this.
 *
 * @author Antony Holmes Holmes
 *
 */
public class PluginService implements Iterable<Plugin> {
	
	/** The Constant LOG. */
	private static final Logger LOG = 
			LoggerFactory.getLogger(PluginService.class);

	/**
	 * Default directory where to find plugins.
	 */
	public static final File DEFAULT_PLUGIN_DIRECTORY = 
			new File("res" + File.separator + "plugins");

	/**
	 * The constant INSTANCE.
	 */
	private static final PluginService INSTANCE = new PluginService();

	/**
	 * The member plugins.
	 */
	private List<Plugin> mPlugins = new UniqueArrayList<Plugin>();

	/**
	 * Gets the single instance of PluginService.
	 *
	 * @return single instance of PluginService
	 */
	public static final PluginService getInstance() {
		return INSTANCE;
	}

	/**
	 * Instantiates a new plugin service.
	 */
	private PluginService() {
		// do nothing
	}

	/**
	 * Adds the plugin.
	 *
	 * @param c the c
	 */
	public void addPlugin(Class<?> c) {
		addPlugin(new Plugin(c));
	}

	/**
	 * Adds the plugin.
	 *
	 * @param plugin the plugin
	 */
	public void addPlugin(Plugin plugin) {
		mPlugins.add(plugin);
	}


	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Plugin> iterator() {
		return mPlugins.iterator();
	}


	/**
	 * Scans the default plugin directory for plugins.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws MalformedURLException the malformed URL exception
	 */
	public final void scan() throws ClassNotFoundException, MalformedURLException {
		scan(DEFAULT_PLUGIN_DIRECTORY);
	}

	/**
	 * Scans a directory within the main plugin directory for
	 * class files.
	 *
	 * @param filename the filename
	 * @throws ClassNotFoundException the class not found exception
	 * @throws MalformedURLException the malformed URL exception
	 */
	public final void scanDirectory(String filename) throws ClassNotFoundException, MalformedURLException {
		scan(new File(filename));
	}

	/**
	 * Scans a specific directory for plugins.
	 *
	 * @param pluginDir the plugin dir
	 * @throws ClassNotFoundException the class not found exception
	 * @throws MalformedURLException the malformed URL exception
	 */
	public final void scan(File pluginDir) throws ClassNotFoundException, MalformedURLException {
		DirectoryFileFilter directoryFilter = new DirectoryFileFilter();

		URLClassLoader ucl;

		File[] dirs = pluginDir.listFiles(directoryFilter);

		Class<?> pluginClass;

		Stack<File> scanDirs = new Stack<File>();
		Stack<String> packages = new Stack<String>();

		//StringBuilder packageName = new StringBuilder();

		for (File dir : dirs) {
			// each plugin dir represents a type of plugin

			scanDirs.clear();
			packages.clear();

			scanDirs.push(dir);
			packages.push("");

			//packageName = new StringBuilder();

			URL classUrl = dir.toURI().toURL();
			URL[] classUrls = {classUrl};
			ucl = new URLClassLoader(classUrls);

			Set<String> visited = new HashSet<String>();

			while (!scanDirs.empty()) {
				File currentDirectory = scanDirs.pop();

				File[] files = currentDirectory.listFiles();

				String currentPackage = packages.pop();

				LOG.info("Scanning {} [{}] for plugins.", 
						currentDirectory.getAbsolutePath(), 
						currentPackage);

				for (File file : files) {
					if (!file.isDirectory()) {
						continue;
					}

					if (visited.contains(file.getAbsolutePath())) {
						continue;
					}

					scanDirs.push(file);

					packages.push(currentPackage + file.getName() + ".");
				}

				for (File file : files) {
					if (file.isDirectory()) {
						continue;
					}

					if (!file.getName().endsWith(".class")) {
						continue;
					}

					String plugin = currentPackage + file.getName().replaceFirst("\\.class$", "");

					LOG.info("Loading plugin {}.", plugin);

					pluginClass = ucl.loadClass(plugin);

					//System.err.println(pluginClass.getSimpleName() + " " +pluginClass.getCanonicalName() + " " + pluginClass.getName());

					addPlugin(new Plugin(pluginClass));
				}

				visited.add(currentDirectory.getAbsolutePath());
			}
		}
	}

	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount() {
		return mPlugins.size();
	}


}