package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import java.util.Set;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.commands.EditCommand.EditMemberDescriptor;
import seedu.address.model.event.Event;
import seedu.address.model.member.Member;
import seedu.address.model.tag.Tag;

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
        sb.append(PREFIX_FROM + event.getFrom() + " ");
        sb.append(PREFIX_TO + event.getTo() + " ");
        sb.append(PREFIX_DETAIL + event.getDetail() + " ");
        event.getRoles().stream().forEach(
            s -> sb.append(PREFIX_ROLE + s.tagName + " ")
        );
        return sb.toString();
    }
}
