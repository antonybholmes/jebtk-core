package org.jebtk.core.io;

import java.util.List;

public interface LineFunction {
	/**
	 * Give a list of tokens to be processed.
	 * 
	 * @param tokens
	 */
	public void parse(final String line);
}
