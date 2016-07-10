/*
	SystemOut.java

	(c) 2015 Masato Kokubo
*/
package org.debugtrace.logger;

import org.debugtrace.DebugTrace;

/**
	A logger using System.out.

	@since 1.6.0
	@deprecated as version 2.1.0, use {@link Std.Out}

	@author Masato Kokubo
*/
@Deprecated
public class SystemOut implements Logger {
	/**
		{@inheritDoc}
	*/
	@Override
	public void log(String message) {
		System.out.println(DebugTrace.appendTimestamp(message));
	}
}