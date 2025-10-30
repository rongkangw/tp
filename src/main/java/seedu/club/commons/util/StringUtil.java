package seedu.club.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.club.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     * Ignores case, but a full word match is required.
     * <br>examples:<pre>
     *     containsWordIgnoreCase("ABc def", "abc") == true
     *     containsWordIgnoreCase("ABc def", "DEF") == true
     *     containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *     </pre>
     *
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns true if {@code s} has length less than or equal {@code length}.
     *    Leading and trailing whitespaces are removed before comparing length.
     *    <br>examples:<pre>
     *       hasLessThanOrEqualLength("hello world", 5) == false
     *       hasLessThanOrEqualLength("hi", 5) == true
     *       hasLessThanOrEqualLength("hello", 5) == true
     *       hasLessThanOrEqualLength("  hello  ", 5) == true
     *       </pre>
     * @param s cannot be null
     * @param length cannot be null
     */
    public static boolean hasLessThanOrEqualLength(String s, int length) {
        requireNonNull(s);

        return s.trim().length() <= length;
    }

    /**
     * Returns a copy of {@code s} with all consecutive whitespace characters replaced by a single space.
     * Leading and trailing whitespaces are removed.
     * <br>examples:<pre>
     *     normalizeAndTrimWhitespace("hello    world") == "hello world"
     *     normalizeAndTrimWhitespace("   hello   world   ") == "hello world"
     *     normalizeAndTrimWhitespace("   ") == ""
     *     normalizeAndTrimWhitespace("   x   ") == "x"
     * </pre>
     * @param s cannot be null
     * @return A normalized string with single spaces between words and no leading or trailing spaces
     */
    public static String normalizeAndTrimWhitespace(String s) {
        requireNonNull(s);

        return s.replaceAll(" +", " ").trim();
    }
}
