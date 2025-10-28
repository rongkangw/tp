package seedu.club.logic.parser.member;

import static seedu.club.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.club.logic.commands.member.FindMemberCommand;
import seedu.club.logic.parser.Parser;
import seedu.club.logic.parser.exceptions.ParseException;
import seedu.club.model.name.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindMemberCommand object
 */
public class FindMemberCommandParser implements Parser<FindMemberCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindMemberCommand
     * and returns a FindMemberCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindMemberCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMemberCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindMemberCommand(new NameContainsKeywordsPredicate<>(Arrays.asList(nameKeywords)));
    }

}
