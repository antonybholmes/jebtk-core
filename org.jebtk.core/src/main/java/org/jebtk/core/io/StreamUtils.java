package org.jebtk.core.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

public class StreamUtils {
  private static final int BUFFER_SIZE = 8192;
  
  private StreamUtils() {
    // Do nothing
  }
  
  /**
   * Create a new buffer for IO operations.
   * 
   * @return    A new buffer.
   */
  public static byte[] createBuffer() {
    return new byte[BUFFER_SIZE];
  }
  
  /**
   * Copy the bytes from one stream to another.
   * 
   * @param input     An input stream.
   * @param output    An output stream.
   * @return          The number of bytes copied.
   * 
   * @throws IOException
   */
  public static int copy(InputStream input, OutputStream output) throws IOException {
    byte[] buffer = createBuffer();

    int c;
    int ret = 0;

    while ((c = input.read(buffer)) > 0) {
       output.write(buffer, 0, c);
       ret += c;
    }
    
    return ret;
  }
  
  
  public static BufferedWriter newBufferedWriter(OutputStream stream) {
    return new BufferedWriter(new OutputStreamWriter(stream, StandardCharsets.UTF_8));
  }
  
  /**
   * New input reader.
   *
   * @param stream the stream
   * @return the reader
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static Reader newInputReader(InputStream stream) {
    return new InputStreamReader(stream, StandardCharsets.UTF_8);
  }
  
  /**
   * New buffered reader.
   *
   * @param stream the stream
   * @return the buffered reader
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static BufferedReader newBufferedReader(InputStream stream) {
    return new BufferedReader(newInputReader(stream));
  }

  public static BufferedReader newBufferedReader(Reader reader) {
    return new BufferedReader(reader);
  }
  
  
  /**
   * Returns a buffered input stream.
   *
   * @param stream the stream
   * @return the input stream
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static InputStream newBufferedInputStream(InputStream stream) {
    return new BufferedInputStream(stream);
  }
  
  /**
   * Returns a buffered input stream.
   *
   * @param stream the stream
   * @return the input stream
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static OutputStream newBufferedOutputStream(OutputStream stream) {
    return new BufferedOutputStream(stream);
  }
  
  public static GZIPOutputStream gz(OutputStream output) throws IOException {
    return new GZIPOutputStream(newBufferedOutputStream(output));
  }
  

  public static ZipOutputStream zip(OutputStream output) {
    return new ZipOutputStream(newBufferedOutputStream(output));
  }
}
