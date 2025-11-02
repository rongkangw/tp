package seedu.club.logic.parser.event;

import static seedu.club.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.club.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.club.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.club.testutil.TypicalIndexes.INDEX_FIRST_EVENT;

import org.junit.jupiter.api.Test;

import seedu.club.logic.commands.event.DisplayEventCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the DisplayEventCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DisplayEventCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DisplayEventCommandParserTest {

    private final DisplayEventCommandParser parser = new DisplayEventCommandParser();

    @Test
    public void parse_validArgs_returnsDisplayEventCommand() {
        assertParseSuccess(parser, "1", new DisplayEventCommand(INDEX_FIRST_EVENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(
                parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DisplayEventCommand.MESSAGE_USAGE)
        );
    }
}
