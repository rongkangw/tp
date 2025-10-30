package seedu.club.logic.parser.event;

import static seedu.club.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.club.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.club.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.club.testutil.TypicalIndexes.INDEX_FIRST_EVENT;

import org.junit.jupiter.api.Test;

import seedu.club.logic.commands.event.EditEventCommand;
import seedu.club.logic.commands.event.EditEventCommand.EditEventDescriptor;
import seedu.club.testutil.EditEventDescriptorBuilder;

public class EditEventCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE);

    private final EditEventCommandParser parser = new EditEventCommandParser();

    @Test
    public void parse_missingParameters_failure() {
        // no index
        assertParseFailure(parser, " n/Meeting", MESSAGE_INVALID_FORMAT);

        // no fields to edit
        assertParseFailure(parser, "1", EditEventCommand.MESSAGE_NOT_EDITED);

        // empty string
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        // index is negative
        assertParseFailure(parser, "-5 n/Meeting", MESSAGE_INVALID_FORMAT);

        // index is 0
        assertParseFailure(parser, "0 n/Meeting" , MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validMultipleInputs_success() {
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName("Movie Night")
                .withDetail("Bring Snacks")
                .withFrom("251225 1400")
                .withTo("251225 1600").build();
        EditEventCommand expectedCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        assertParseSuccess(parser, "1 n/Movie Night f/251225 1400 t/251225 1600 d/Bring Snacks",
                expectedCommand);
    }

}
