package seedu.club.logic.parser.event;

import static seedu.club.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.club.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.club.logic.parser.CliSyntax.PREFIX_MEMBER;

import seedu.club.logic.commands.event.UnassignEventCommand;
import seedu.club.logic.parser.ArgumentMultimap;
import seedu.club.logic.parser.ArgumentTokenizer;
import seedu.club.logic.parser.Parser;
import seedu.club.logic.parser.ParserUtil;
import seedu.club.logic.parser.exceptions.ParseException;
import seedu.club.model.name.Name;

/**
 * Parses input arguments and creates a new UnassignEventCommand object
 */
public class UnassignEventCommandParser implements Parser<UnassignEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnassignEventCommand
     * and returns a UnassignEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnassignEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT, PREFIX_MEMBER);
        if (!argMultimap.arePrefixesPresent(PREFIX_EVENT, PREFIX_MEMBER)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnassignEventCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EVENT, PREFIX_MEMBER);
        try {
            Name event = ParserUtil.parseName(argMultimap.getValue(PREFIX_EVENT).get());
            Name member = ParserUtil.parseName(argMultimap.getValue(PREFIX_MEMBER).get());

            return new UnassignEventCommand(event, member);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignEventCommand.MESSAGE_USAGE), pe);
        }
    }
}
