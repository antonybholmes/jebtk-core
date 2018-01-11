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
package org.jebtk.core.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import org.jebtk.core.collections.CollectionUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ByteStreams.
 */
public class ByteStreams {

  /** The default buffer size when copying arrays. */
  private static final int DEFAULT_BUFFER_SIZE = 0x1000; // 4096

  /**
   * Instantiates a new byte streams.
   */
  private ByteStreams() {
    // Do nothing
  }

  /**
   * Copies all bytes from the input stream to the output stream. Does not close
   * or flush either stream.
   *
   * @param from The input stream to read from.
   * @param to The output stream to write to.
   * @return The number of bytes copied.
   * @throws IOException if an I/O error occurs.
   */
  public static long copy(InputStream from, OutputStream to)
      throws IOException {
    return copy(from, to, DEFAULT_BUFFER_SIZE);
  }

  /**
   * Copies all bytes from the input stream to the output stream. Does not close
   * or flush either stream.
   *
   * @param from The input stream to read from.
   * @param to The output stream to write to.
   * @param bufSize The size in bytes of the buffer used for copying.
   * @return The number of bytes copied.
   * @throws IOException if an I/O error occurs
   */
  public static long copy(InputStream from, OutputStream to, int bufSize)
      throws IOException {
    byte[] buf = new byte[bufSize];

    long total = 0;

    int r;

    while ((r = from.read(buf)) != -1) {
      to.write(buf, 0, r);
      total += r;
    }

    return total;
  }

  /**
   * Copies all bytes from the readable channel to the writable channel. Does
   * not close or flush either channel.
   *
   * @param from the readable channel to read from
   * @param to the writable channel to write to
   * @return the number of bytes copied
   * @throws IOException if an I/O error occurs
   */
  public static long copy(ReadableByteChannel from, WritableByteChannel to)
      throws IOException {
    ByteBuffer buf = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);

    long total = 0;

    while (from.read(buf) != -1) {
      buf.flip();

      while (buf.hasRemaining()) {
        total += to.write(buf);
      }

      buf.clear();
    }

    return total;
  }

  /**
   * Reads all bytes from an input stream into a byte array. Does not close the
   * stream.
   *
   * @param in the input stream to read from
   * @return a byte array containing all the bytes from the stream
   * @throws IOException if an I/O error occurs
   */
  public static byte[] toByteArray(InputStream in) throws IOException {
    return toByteArray(in, DEFAULT_BUFFER_SIZE);
  }

  /**
   * To byte array.
   *
   * @param in the in
   * @param bufSize the buf size
   * @return the byte[]
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static byte[] toByteArray(InputStream in, int bufSize)
      throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream(bufSize);

    byte[] ret = CollectionUtils.EMPTY_BYTE_ARRAY;

    try {
      copy(in, out);

      ret = out.toByteArray();
    } finally {
      out.close();
    }

    return ret;
  }

  /**
   * To byte array.
   *
   * @param in the in
   * @return the byte[]
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static byte[] toByteArray(ReadableByteChannel in) throws IOException {
    return toByteArray(in, DEFAULT_BUFFER_SIZE);
  }

  /**
   * To byte array.
   *
   * @param in the in
   * @param bufSize the buf size
   * @return the byte[]
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static byte[] toByteArray(ReadableByteChannel in, int bufSize)
      throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream(bufSize);

    byte[] ret = CollectionUtils.EMPTY_BYTE_ARRAY;

    try {
      copy(in, Channels.newChannel(out));

      ret = out.toByteArray();
    } finally {
      out.close();
    }

    return ret;
  }
}
