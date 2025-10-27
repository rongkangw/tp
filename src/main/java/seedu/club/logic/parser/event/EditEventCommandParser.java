package seedu.club.logic.parser.event;

import seedu.club.commons.core.index.Index;
import seedu.club.logic.commands.event.EditEventCommand;
import seedu.club.logic.parser.ArgumentMultimap;
import seedu.club.logic.parser.ArgumentTokenizer;
import seedu.club.logic.parser.ParserUtil;
import seedu.club.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.club.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.club.logic.parser.CliSyntax.*;

public class EditEventCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the EditEventCommand
     * and returns an EditEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_FROM, PREFIX_TO, PREFIX_DETAIL);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_FROM, PREFIX_TO, PREFIX_DETAIL);

        EditEventCommand.EditEventDescriptor editEventDescriptor = new EditEventCommand.EditEventDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEventDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_FROM).isPresent()) {
            editEventDescriptor.setFrom(ParserUtil.parseDate(argMultimap.getValue(PREFIX_FROM).get()));
        }
        if (argMultimap.getValue(PREFIX_TO).isPresent()) {
            editEventDescriptor.setTo(ParserUtil.parseDate(argMultimap.getValue(PREFIX_TO).get()));
        }
        if (argMultimap.getValue(PREFIX_DETAIL).isPresent()) {
            editEventDescriptor.setDetails(ParserUtil.parseDetail(argMultimap.getValue(PREFIX_DETAIL).get()));
        }
        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditEventCommand.MESSAGE_NOT_EDITED);
        }

        return new EditEventCommand(index, editEventDescriptor);
    }
}
