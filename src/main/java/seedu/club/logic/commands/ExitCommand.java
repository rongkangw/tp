package seedu.club.logic.commands;

import seedu.club.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Club Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        // This makes the app go to memberList instead of eventList, need to change this someday
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false);
    }

}
