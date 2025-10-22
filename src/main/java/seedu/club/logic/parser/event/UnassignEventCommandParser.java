package seedu.club.logic.parser.event;

import static seedu.club.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.club.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.Collections;
import java.util.Set;

import seedu.club.logic.commands.event.UnassignEventCommand;
import seedu.club.logic.parser.ArgumentMultimap;
import seedu.club.logic.parser.ArgumentTokenizer;
import seedu.club.logic.parser.Parser;
import seedu.club.logic.parser.ParserUtil;
import seedu.club.logic.parser.Prefix;
import seedu.club.logic.parser.exceptions.ParseException;
import seedu.club.model.name.Name;
import seedu.club.model.role.EventRole;






/**
 * Parses input arguments and creates a new UnassignEventCommand object
 */
public class UnassignEventCommandParser implements Parser<UnassignEventCommand> {
    public static final Prefix PREFIX_MEMBER = new Prefix("m/");
    public static final Prefix PREFIX_EVENT = new Prefix("e/");

    /**
     * Parses the given {@code String} of arguments in the context of the UnassignEventCommand
     * and returns a UnassignEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnassignEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEMBER, PREFIX_EVENT, PREFIX_ROLE);
        if (!argMultimap.arePrefixesPresent(PREFIX_MEMBER, PREFIX_EVENT)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnassignEventCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MEMBER, PREFIX_EVENT);
        try {
            Name member = ParserUtil.parseName(argMultimap.getValue(PREFIX_MEMBER).get());
            Name event = ParserUtil.parseName(argMultimap.getValue(PREFIX_EVENT).get());
            Set<EventRole> roles = !argMultimap.getAllValues(PREFIX_ROLE).isEmpty()
                    ? ParserUtil.parseEventRoles(argMultimap.getAllValues(PREFIX_ROLE))
                    : Collections.emptySet();

            return new UnassignEventCommand(member, event, roles);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignEventCommand.MESSAGE_USAGE), pe);
        }
    }
}
