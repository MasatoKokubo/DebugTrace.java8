// LogBuffer.java
// (C) 2015 Masato Kokubo

package org.debugtrace;

import java.util.ArrayList;
import java.util.List;

import org.debugtrace.helper.ListUtils;
import org.debugtrace.helper.Tuple;

/**
 * Buffers logs.
 *
 * @since 3.0.0
 * @author Masato Kokubo
 */
class LogBuffer {
    private int nestLevel = 0;
    private int appendNestLevel = 0;

    // tuples of data indentation level and log string
    private final List<Tuple._2<Integer, String>> lines = new ArrayList<>();

    // buffer for a line of logs
    private StringBuilder builder = new StringBuilder();

    /**
     * Returns tuples of data indentation level and log string.
     *
     * @return tuples of data indentation level and log string
     */
    public List<Tuple._2<Integer, String>> lines() {
        return ListUtils.copyOf(lines);
    }

   /**
     * Breaks the current line.
     */
    public void lineFeed() {
        lines.add(Tuple.of(nestLevel + appendNestLevel, builder.toString()));
        appendNestLevel = 0;
        builder.setLength(0);
    }

   /**
     * Ups the nest level.
     */
    public void upNest() {
    	++nestLevel;
    }

   /**
     * Downs the nest level.
     */
    public void downNest() {
    	--nestLevel;
    }

   /**
     * Appends a string representation of the value.
     *
     * @param value the value to append
     * @param nestLevel >the nest level of the value
     * @param noBreak if true, does not break even if the maximum width is exceeded
     * @return this object
     */
    public LogBuffer append(Object value, int nestLevel, boolean noBreak) {
        String str = value.toString();
        if (!noBreak && length() > 0 && length() + str.length() > DebugTrace.maximumDataOutputWidth)
            lineFeed();
        appendNestLevel = nestLevel;
        builder.append(str);
        return this;
    }

   /**
     * Appends a string representation of the value.
     *
     * @param value the value to append
     * @param nestLevel >the nest level of the value
     * @return this object
     */
    public LogBuffer append(Object value, int nestLevel) {
    	return append(value, nestLevel, false);
    }
 
   /**
     * Appends a string representation of the value.
     *
     * @param value the value to append
     * @return this object
     */
    public LogBuffer append(Object value) {
    	return append(value, 0, false);
    }
 
   /**
     * Appends a string representation of the value.
     * Does not break even if the maximum width is exceeded.
     *
     * @param value the value to append
     * @return this object
     */
    public LogBuffer noBreakAppend(Object value) {
    	return append(value, 0, true);
    }
 
   /**
     * Appends lines of another LogBuffer.
     *
     * @param buff another LogBuffer
     * @return this object
     */
    public LogBuffer append(LogBuffer buff) {
        buff.lineFeed();
        int index = 0;
        for (Tuple._2<Integer, String> line : buff.lines) {
            if (index > 0)
                lineFeed();
            append(line.value2(), line.value1());
            ++index;
        }
        return this;
    }

   /**
     * Returns log length of the current line.
     *
     * @return log length of the current line
     */
    public int length() {
    	return builder.length();
    }

   /**
     * Returns true if multiple line.
     *
     * @return true if multiple line, false otherwise
     */
    public boolean isMultiLines() {
    	return lines.size() > 1 || lines.size() == 1 && length() > 0;
    }
}