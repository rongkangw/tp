package seedu.club.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.club.model.ClubBook;
import seedu.club.model.Model;

/**
 * Clears the club book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Club book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setClubBook(new ClubBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
