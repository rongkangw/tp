package seedu.club.model.name;

import static java.util.Objects.requireNonNull;
import static seedu.club.commons.util.AppUtil.checkArgument;

/**
 * Represents the name of a {@code NamedEntity}.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should not be blank and can only contain alphanumeric characters, spaces, " +
                    "and these special characters: \n" +
                    "comma(,), parentheses(()), at sign(@), hyphen(-), period(.), apostrophe(') and backslash(\\).\n" +
                    "For '/', please use '\\'.\n"
                    + "The first and last characters should be alphanumeric.\n";
    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}][\\p{Alnum} ,()@.'\\\\-]*[\\p{Alnum}]$";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return fullName.equalsIgnoreCase(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.toLowerCase().hashCode();
    }

}
