package seedu.club.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.club.logic.parser.Prefix;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;
import seedu.club.model.role.EventRole;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX = "The member index provided is invalid";
    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX = "The event index provided is invalid";
    public static final String MESSAGE_MEMBERS_LISTED_OVERVIEW = "%1$d members listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code member} for display to the user.
     */
    public static String format(Member member) {
        final StringBuilder builder = new StringBuilder();
        builder.append(member.getName())
                .append("; Phone: ")
                .append(member.getPhone())
                .append("; Email: ")
                .append(member.getEmail())
                .append("; Roles: ");
        member.getRoles().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code event} for display to the user.
     */
    public static String format(Event event) {
        final StringBuilder builder = new StringBuilder();
        builder.append(event.getName())
                .append("; From: ")
                .append(event.getFrom());

        String to = event.getTo();
        if (to != null && !to.isEmpty()) {
            builder.append("; To: ").append(to);
        }

        String detail = event.getDetail();
        if (detail != null && !detail.isEmpty()) {
            builder.append("; Detail: ").append(detail);
        }

        Set<EventRole> roles = event.getRoles();
        if (roles != null && !roles.isEmpty()) {
            builder.append("; Roles: ");
            roles.forEach(builder::append);
        }

        return builder.toString();
    }
}
