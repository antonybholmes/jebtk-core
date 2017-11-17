package org.jebtk.core.io;

import java.util.List;

public interface TokenFunction {
	/**
	 * Give a list of tokens to be processed.
	 * 
	 * @param tokens
	 */
	public void parse(final List<String> tokens);
}
