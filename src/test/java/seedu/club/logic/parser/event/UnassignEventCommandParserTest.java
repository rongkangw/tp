package seedu.club.logic.parser.event;

import static seedu.club.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.club.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.club.logic.commands.event.UnassignEventCommand;




public class UnassignEventCommandParserTest {
    private final UnassignEventCommandParser parser = new UnassignEventCommandParser();


    @Test
    public void parse_emptyString_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignEventCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_missingMember_throwsParseException() {
        assertParseFailure(parser, "e/Meeting",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignEventCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_missingEvent_throwsParseException() {
        assertParseFailure(parser, "m/John",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignEventCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_duplicatePrefixes_throwsParseException() {
        assertParseFailure(parser, "m/John e/Meeting e/Workshop r/Logistics",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignEventCommand.MESSAGE_USAGE)
        );
    }




}
