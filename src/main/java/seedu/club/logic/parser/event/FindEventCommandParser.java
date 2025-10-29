package seedu.club.logic.parser.event;

import static seedu.club.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.club.logic.commands.event.FindEventCommand;
import seedu.club.logic.parser.Parser;
import seedu.club.logic.parser.exceptions.ParseException;
import seedu.club.model.name.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindEventCommand object
 */
public class FindEventCommandParser implements Parser<FindEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindEventCommand
     * and returns a FindEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEventCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindEventCommand(new NameContainsKeywordsPredicate<>(Arrays.asList(nameKeywords)));
    }
}
