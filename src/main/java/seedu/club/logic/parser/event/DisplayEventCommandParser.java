package seedu.club.logic.parser.event;

import static seedu.club.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.club.commons.core.index.Index;
import seedu.club.logic.commands.event.DisplayEventCommand;
import seedu.club.logic.parser.ParserUtil;
import seedu.club.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DisplayEventCommand object
 */
public class DisplayEventCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DisplayEventCommand
     * and returns a DisplayEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DisplayEventCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DisplayEventCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            DisplayEventCommand.MESSAGE_USAGE), pe);
        }
    }
}
