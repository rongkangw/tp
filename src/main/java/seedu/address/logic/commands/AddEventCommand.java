package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds an event to the club event list.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "addEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a member to the member list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_FROM + "FROM "
            + PREFIX_TO + "TO "
            + PREFIX_DETAILS + "DETAILS "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Coding Workshop "
            + PREFIX_FROM + "2025-09-30T14:30 "
            + PREFIX_TO + "2025-09-30T16:30 "
            + PREFIX_DETAILS + "Bring gloves and trash bags ";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Hello from addEvent");
    }
}
