package seedu.club.testutil;

import static seedu.club.logic.parser.CliSyntax.PREFIX_DETAIL;
import static seedu.club.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.club.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.club.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.club.logic.parser.CliSyntax.PREFIX_TO;

import seedu.club.logic.commands.event.AddEventCommand;
import seedu.club.model.event.Event;

/**
 * A utility class for Event.
 */
public class EventUtil {

    /**
     * Returns an add event command string for adding the {@code event}.
     */
    public static String getAddEventCommand(Event event) {
        return AddEventCommand.COMMAND_WORD + " " + getEventDetails(event);
    }

    /**
     * Returns the part of command string for the given {@code event}'s details.
     */
    public static String getEventDetails(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + event.getName().fullName + " ");
        sb.append(PREFIX_FROM + event.getFrom().value + " ");
        sb.append(PREFIX_TO + event.getTo().value + " ");
        sb.append(PREFIX_DETAIL + event.getDetail() + " ");
        event.getRoles().stream().forEach(
            s -> sb.append(PREFIX_ROLE + s.roleName + " ")
        );
        return sb.toString();
    }
}
