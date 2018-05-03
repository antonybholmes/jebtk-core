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
package org.jebtk.core.sys;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jebtk.core.text.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class ExternalProcess.
 */
public class ExternalProcess {

  /**
   * The member args.
   */
  private List<String> mArgs = new ArrayList<String>();

  /**
   * The member working directory.
   */
  private File mWorkingDirectory;

  /**
   * The constant LOG.
   */
  private static final Logger LOG = LoggerFactory
      .getLogger(ExternalProcess.class);

  /**
   * Instantiates a new external process.
   *
   * @param workingDirectory the working directory
   */
  public ExternalProcess(File workingDirectory) {
    mWorkingDirectory = workingDirectory;
  }

  /**
   * Adds the arg.
   *
   * @param arg the arg
   */
  public final void addArg(String arg) {
    mArgs.add(arg);
  }

  /**
   * Sets the args.
   *
   * @param args the new args
   */
  public final void setArgs(List<String> args) {
    mArgs = new ArrayList<String>(args);
  }

  /**
   * Sets the args.
   *
   * @param args the new args
   */
  public final void setArgs(String[] args) {
    mArgs = Arrays.asList(args);
  }

  /**
   * Run.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws InterruptedException the interrupted exception
   */
  public final void run() throws IOException, InterruptedException {
    ProcessBuilder builder = new ProcessBuilder(mArgs);

    builder.directory(mWorkingDirectory);

    builder.redirectErrorStream(true);

    LOG.info(TextUtils.join(mArgs, TextUtils.SPACE_DELIMITER));

    Process process = builder.start();

    // Runtime runtime = Runtime.getRuntime();
    // Process process = runtime.exec(args);

    BufferedReader br = new BufferedReader(
        new InputStreamReader(process.getInputStream()));
    String line;

    while ((line = br.readLine()) != null) {
      LOG.info(line);
    }

    process.waitFor();
  }

  /**
   * Run.
   *
   * @param command the command
   * @param workingDirectory the working directory
   * @throws InterruptedException the interrupted exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static final void run(String command, File workingDirectory)
      throws InterruptedException, IOException {
    ExternalProcess process = new ExternalProcess(workingDirectory);

    process.addArg(command);

    process.run();
  }

  /**
   * Run.
   *
   * @param commands the commands
   * @param workingDirectory the working directory
   * @throws InterruptedException the interrupted exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static final void run(List<String> commands, File workingDirectory)
      throws InterruptedException, IOException {
    ExternalProcess process = new ExternalProcess(workingDirectory);

    process.setArgs(commands);

    process.run();
  }

  /**
   * Run.
   *
   * @param commands the commands
   * @param workingDirectory the working directory
   * @throws InterruptedException the interrupted exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static final void run(String[] commands, Path pwd)
      throws InterruptedException, IOException {
    ExternalProcess process = new ExternalProcess(pwd.toFile());

    process.setArgs(commands);

    process.run();
  }
}
