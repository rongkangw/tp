package seedu.club.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.club.storage.JsonAdaptedMember.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.club.testutil.Assert.assertThrows;
import static seedu.club.testutil.TypicalMembers.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.club.commons.exceptions.IllegalValueException;
import seedu.club.model.member.Email;
import seedu.club.model.member.Phone;
import seedu.club.model.name.Name;

public class JsonAdaptedMemberTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_MEMBER_ROLE = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final List<JsonAdaptedMemberRole> VALID_MEMBER_ROLES = BENSON.getMemberRoles().stream()
            .map(JsonAdaptedMemberRole::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedEventRole> VALID_EVENT_ROLES = BENSON.getEventRoles().stream()
            .map(JsonAdaptedEventRole::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validMemberDetails_returnsMember() throws Exception {
        JsonAdaptedMember member = new JsonAdaptedMember(BENSON);
        assertEquals(BENSON, member.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MEMBER_ROLES, VALID_EVENT_ROLES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedMember member = new JsonAdaptedMember(null, VALID_PHONE, VALID_EMAIL,
                VALID_MEMBER_ROLES, VALID_EVENT_ROLES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_MEMBER_ROLES, VALID_EVENT_ROLES);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, null, VALID_EMAIL, VALID_MEMBER_ROLES, VALID_EVENT_ROLES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_MEMBER_ROLES, VALID_EVENT_ROLES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedMember member = new JsonAdaptedMember(VALID_NAME, VALID_PHONE, null,
                VALID_MEMBER_ROLES, VALID_EVENT_ROLES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidMemberRoles_throwsIllegalValueException() {
        List<JsonAdaptedMemberRole> invalidMemberRoles = new ArrayList<>(VALID_MEMBER_ROLES);
        invalidMemberRoles.add(new JsonAdaptedMemberRole(INVALID_MEMBER_ROLE));
        JsonAdaptedMember member = new JsonAdaptedMember(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                invalidMemberRoles, VALID_EVENT_ROLES);
        assertThrows(IllegalValueException.class, member::toModelType);
    }

}
