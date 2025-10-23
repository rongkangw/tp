package seedu.club.logic.parser.event;

import static seedu.club.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.club.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.club.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.club.testutil.TypicalEventsWithEventRoles.getTypicalClubBookWithEventRoles;
import static seedu.club.testutil.TypicalIndexes.INDEX_FIRST_EVENT;

import org.junit.jupiter.api.Test;

import seedu.club.logic.commands.event.DeleteEventCommand;
import seedu.club.logic.commands.event.UnassignEventCommand;
import seedu.club.model.Model;
import seedu.club.model.ModelManager;
import seedu.club.model.UserPrefs;
import seedu.club.model.name.Name;
import seedu.club.model.role.EventRole;

import java.util.Collections;
import java.util.Set;


public class UnassignEventCommandParserTest {
    private final UnassignEventCommandParser parser = new UnassignEventCommandParser();


    @Test
    public void parse_validArgsWithNoEventRoles_returnsUnassignEventCommand() {
        assertParseSuccess(parser,
                " e/Meeting m/John",
                new UnassignEventCommand(new Name("Meeting"), new Name("John"), Collections.emptySet()));
    }

    @Test
    public void parse_validArgsWithEventRoles_returnsUnassignEventCommand() {
        assertParseSuccess(parser,
                " e/Meeting m/John r/Facilitator r/SafetyOfficer",
                new UnassignEventCommand(new Name("Meeting"), new Name("John"),
                        Set.of(new EventRole("Facilitator"),
                                new EventRole("SafetyOfficer"))));
    }


    @Test
    public void parse_emptyString_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignEventCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_emptyMember_throwsParseException() {
        assertParseFailure(parser, "e/ m/John",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignEventCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_emptyEvent_throwsParseException() {
        assertParseFailure(parser, "e/Meeting m/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignEventCommand.MESSAGE_USAGE)
        );
    }



    @Test
    public void parse_invalidMember_throwsParseException() {
        assertParseFailure(parser, "e/Meeting m/@@@",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignEventCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_invalidEvent_throwsParseException() {
        assertParseFailure(parser, "e/!!! m/John",
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
