package seedu.club.logic.parser.event;

import static seedu.club.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.club.commons.core.index.Index;
import seedu.club.logic.commands.event.DisplayEventCommand;
import seedu.club.logic.parser.ParserUtil;
import seedu.club.logic.parser.exceptions.ParseException;

public class DisplayEventCommandParser {
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
