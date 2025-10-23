package seedu.club.logic.parser.event;

import static seedu.club.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.club.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.club.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.club.logic.parser.CliSyntax.PREFIX_MEMBER;

import java.util.Collections;
import java.util.Set;

import seedu.club.logic.commands.event.AssignEventCommand;
import seedu.club.logic.parser.ArgumentMultimap;
import seedu.club.logic.parser.ArgumentTokenizer;
import seedu.club.logic.parser.Parser;
import seedu.club.logic.parser.ParserUtil;
import seedu.club.logic.parser.exceptions.ParseException;
import seedu.club.model.name.Name;
import seedu.club.model.role.EventRole;

/**
 * Parses input arguments and creates a new {@code AssignEventCommand} object
 */
public class AssignEventCommandParser implements Parser<AssignEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AssignEventCommand
     * and returns an {@code AssignEventCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT, PREFIX_MEMBER, PREFIX_ROLE);

        if (!argMultimap.arePrefixesPresent(PREFIX_EVENT, PREFIX_MEMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignEventCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EVENT, PREFIX_MEMBER);
        Name eventName = ParserUtil.parseName(argMultimap.getValue(PREFIX_EVENT).get());
        Name memberName = ParserUtil.parseName(argMultimap.getValue(PREFIX_MEMBER).get());
        Set<EventRole> eventRoleList = !argMultimap.getAllValues(PREFIX_ROLE).isEmpty()
                ? ParserUtil.parseEventRoles(argMultimap.getAllValues(PREFIX_ROLE))
                : Collections.emptySet();

        return new AssignEventCommand(eventName, memberName, eventRoleList);
    }
}
