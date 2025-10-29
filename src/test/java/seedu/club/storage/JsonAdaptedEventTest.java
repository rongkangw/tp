package seedu.club.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.club.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.club.testutil.Assert.assertThrows;
import static seedu.club.testutil.TypicalEvents.ORIENTATION;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.club.commons.exceptions.IllegalValueException;
import seedu.club.model.event.DateTime;
import seedu.club.model.name.Name;
import seedu.club.testutil.TypicalMembers;

public class JsonAdaptedEventTest {
    private static final String INVALID_NAME = "@orientation";
    private static final String INVALID_DATETIME = "2025/5/13 0000";
    private static final String INVALID_EVENT_ROLE = "#treasurer";

    private static final String VALID_NAME = ORIENTATION.getName().toString();
    private static final String VALID_FROM = ORIENTATION.getFrom().value;
    private static final String VALID_TO = ORIENTATION.getTo().value;
    private static final String VALID_DETAILS = ORIENTATION.getDetail();
    private static final List<JsonAdaptedEventRole> VALID_EVENT_ROLES = ORIENTATION.getRoles().stream()
                            .map(JsonAdaptedEventRole::new)
                            .collect(Collectors.toList());
    private static final List<JsonAdaptedMember> VALID_EVENT_ROSTER = TypicalMembers.getTypicalMembers()
            .stream().map(JsonAdaptedMember::new).toList();


    @Test
    public void toModelType_validEvent_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(ORIENTATION);
        assertEquals(ORIENTATION, event.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(
                INVALID_NAME, VALID_FROM, VALID_TO, VALID_DETAILS, VALID_EVENT_ROLES, VALID_EVENT_ROSTER
        );
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(
                null, VALID_FROM, VALID_TO, VALID_DETAILS, VALID_EVENT_ROLES, VALID_EVENT_ROSTER
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(
                VALID_NAME, INVALID_DATETIME, VALID_TO, VALID_DETAILS, VALID_EVENT_ROLES, VALID_EVENT_ROSTER
        );
        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(
                VALID_NAME, null, VALID_TO, VALID_DETAILS, VALID_EVENT_ROLES, VALID_EVENT_ROSTER
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullDetails_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(
                VALID_NAME, VALID_FROM, VALID_TO, null, VALID_EVENT_ROLES, VALID_EVENT_ROSTER
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidEventRoles_throwsIllegalValueException() {
        List<JsonAdaptedEventRole> invalidEventRoles = new ArrayList<>(VALID_EVENT_ROLES);
        invalidEventRoles.add(new JsonAdaptedEventRole(INVALID_EVENT_ROLE, ""));
        JsonAdaptedEvent event = new JsonAdaptedEvent(
                VALID_NAME, VALID_FROM, VALID_TO, VALID_DETAILS, invalidEventRoles, VALID_EVENT_ROSTER
        );
        assertThrows(IllegalValueException.class, event::toModelType);
    }
}
