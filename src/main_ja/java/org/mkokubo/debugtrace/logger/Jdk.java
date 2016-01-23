/*
	JdkLogger.java
	(C) 2015 Masato Kokubo
*/

package org.mkokubo.debugtrace.logger;


/**
	Jdk を使用するロガーです。

	@since 1.0.0

	@author 小久保 雅人
*/
public class Jdk implements Logger {
	/**
		JdkLogger を構築します。
	*/
	public Jdk() {}

	/**
		{@inheritDoc}
	*/
	@Override
	public void setLevel(String levelStr) {}

	/**
		{@inheritDoc}
	*/
	@Override
	public boolean isEnabled() {return false;}

	/**
		{@inheritDoc}
	*/
	@Override
	public void log(String message) {}
}