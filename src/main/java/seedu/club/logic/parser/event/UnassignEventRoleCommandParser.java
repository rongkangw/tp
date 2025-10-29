package seedu.club.logic.parser.event;

import static seedu.club.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.club.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.club.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.club.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.Set;

import seedu.club.logic.commands.event.UnassignEventRoleCommand;
import seedu.club.logic.parser.ArgumentMultimap;
import seedu.club.logic.parser.ArgumentTokenizer;
import seedu.club.logic.parser.Parser;
import seedu.club.logic.parser.ParserUtil;
import seedu.club.logic.parser.exceptions.ParseException;
import seedu.club.model.name.Name;
import seedu.club.model.role.EventRole;

/**
 * Parses input arguments and creates a new UnassignEventRoleCommand object
 */
public class UnassignEventRoleCommandParser implements Parser<UnassignEventRoleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnassignEventRoleCommand
     * and returns a UnassignEventRoleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnassignEventRoleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT, PREFIX_MEMBER, PREFIX_ROLE);
        if (!argMultimap.arePrefixesPresent(PREFIX_EVENT, PREFIX_MEMBER, PREFIX_ROLE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnassignEventRoleCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EVENT, PREFIX_MEMBER);
        try {
            Name event = ParserUtil.parseName(argMultimap.getValue(PREFIX_EVENT).get());
            Name member = ParserUtil.parseName(argMultimap.getValue(PREFIX_MEMBER).get());
            Set<EventRole> roles = ParserUtil.parseEventRoles(argMultimap.getAllValues(PREFIX_ROLE));

            return new UnassignEventRoleCommand(event, member, roles);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignEventRoleCommand.MESSAGE_USAGE), pe);
        }
    }

}
