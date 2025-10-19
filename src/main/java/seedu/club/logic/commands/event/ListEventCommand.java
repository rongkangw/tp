package seedu.club.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.club.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import seedu.club.logic.commands.Command;
import seedu.club.logic.commands.CommandResult;
import seedu.club.model.Model;

/**
 * Lists all upcoming events in the club to the user.
 */
public class ListEventCommand extends Command {

    public static final String COMMAND_WORD = "listEvents";

    public static final String MESSAGE_SUCCESS = "Listed all events";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
