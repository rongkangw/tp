package seedu.club.logic.commands.event;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.club.commons.core.index.Index;
import seedu.club.commons.util.ToStringBuilder;
import seedu.club.logic.Messages;
import seedu.club.logic.commands.Command;
import seedu.club.logic.commands.CommandResult;
import seedu.club.logic.commands.exceptions.CommandException;
import seedu.club.model.Model;
import seedu.club.model.ViewState;
import seedu.club.model.event.Event;

/**
 * Displays an event with its participating members,
 * identified by the index number used with its participating members.\n
 */
public class DisplayEventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays an event with the participating members.\n"
            + "Parameters : INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DISPLAY_EVENT_SUCCESS = "Event: %1$s displayed successfully";

    private final Index targetIndex;

    public DisplayEventCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> fullEventList = model.getFullEventList();

        if (targetIndex.getZeroBased() >= fullEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToBeDisplayed = fullEventList.get(targetIndex.getZeroBased());
        model.setViewState(ViewState.SINGLE_EVENT);
        model.updateFilteredEventList(e -> e.equals(eventToBeDisplayed));
        model.updateFilteredMemberList(m -> eventToBeDisplayed.getRoster().contains(m));
        return new CommandResult(String.format(MESSAGE_DISPLAY_EVENT_SUCCESS,
                eventToBeDisplayed.getName()), false, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DisplayEventCommand)) {
            return false;
        }

        DisplayEventCommand otherDisplayEventCommand = (DisplayEventCommand) other;
        return targetIndex.equals(otherDisplayEventCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

}
