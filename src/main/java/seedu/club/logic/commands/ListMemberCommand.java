package seedu.club.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.club.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import seedu.club.model.Model;

/**
 * Lists all members in the club to the user.
 */
public class ListMemberCommand extends Command {

    public static final String COMMAND_WORD = "listMembers";

    public static final String MESSAGE_SUCCESS = "Listed all members";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
