package seedu.club.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.club.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.club.testutil.Assert.assertThrows;
import static seedu.club.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.club.logic.parser.exceptions.ParseException;
import seedu.club.model.event.DateTime;
import seedu.club.model.member.Email;
import seedu.club.model.member.Phone;
import seedu.club.model.name.Name;
import seedu.club.model.role.EventRole;
import seedu.club.model.role.MemberRole;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_MEMBER_ROLE = "#friend";
    private static final String INVALID_EVENT_ROLE = "#treasurer";
    private static final String INVALID_DATETIME = "010325 210s";
    private static final String INVALID_DETAIL = "a".repeat(501); // 501 characters

    private static final String VALID_MEMBER_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "62123456";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_EVENT_NAME = "Coding Workshop";
    private static final String VALID_MEMBER_ROLE_1 = "friend";
    private static final String VALID_MEMBER_ROLE_2 = "vice president";
    private static final String VALID_MEMBER_ROLE_2_WITH_SPACES = "vice    president";
    private static final String VALID_EVENT_ROLE_1 = "productions";
    private static final String VALID_EVENT_ROLE_2 = "vice treasurer";
    private static final String VALID_EVENT_ROLE_2_WITH_SPACES = "vice    treasurer";
    private static final String VALID_DATETIME = "010325 2100";
    private static final String VALID_DATETIME_AS_STRING = "1 Mar 2025 9:00pm";
    private static final String VALID_DETAIL_1 = "a".repeat(500);
    private static final String VALID_DETAIL_2 = "a".repeat(250)
            + " " + "a".repeat(249); // 500 characters total
    private static final String VALID_DETAIL_2_WITH_SPACES = "a".repeat(250)
            + "    " + "a".repeat(249); // 500 characters total after whitespace normalization

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_MEMBER, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_MEMBER, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_MEMBER_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_MEMBER_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_MEMBER_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_MEMBER_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseMemberRole_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMemberRole(null));
    }

    @Test
    public void parseEventRole_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                ParserUtil.parseEventRole(null, new Name(VALID_EVENT_NAME)));
    }

    @Test
    public void parseMemberRole_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMemberRole(INVALID_MEMBER_ROLE));
    }

    @Test
    public void parseEventRole_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseEventRole(INVALID_EVENT_ROLE, new Name(VALID_EVENT_NAME)));
    }

    @Test
    public void parseMemberRole_validValueWithoutWhitespace_returnsMemberRole() throws Exception {
        MemberRole expectedRole = new MemberRole(VALID_MEMBER_ROLE_1);
        assertEquals(expectedRole, ParserUtil.parseMemberRole(VALID_MEMBER_ROLE_1));
    }

    @Test
    public void parseEventRole_validValueWithoutWhitespace_returnsEventRole() throws Exception {
        EventRole expectedRole = new EventRole(VALID_EVENT_ROLE_1, new Name(VALID_EVENT_NAME));
        assertEquals(expectedRole, ParserUtil.parseEventRole(VALID_EVENT_ROLE_1, new Name(VALID_EVENT_NAME)));
    }

    @Test
    public void parseMemberRole_validValueWithWhitespace_returnsTrimmedMemberRole() throws Exception {
        String roleWithWhitespace = WHITESPACE + VALID_MEMBER_ROLE_1 + WHITESPACE;
        MemberRole expectedRole = new MemberRole(VALID_MEMBER_ROLE_1);
        assertEquals(expectedRole, ParserUtil.parseMemberRole(roleWithWhitespace));
    }

    @Test
    public void parseEventRole_validValueWithWhitespace_returnsTrimmedEventRole() throws Exception {
        String roleWithWhitespace = WHITESPACE + VALID_EVENT_ROLE_1 + WHITESPACE;
        EventRole expectedRole = new EventRole(VALID_EVENT_ROLE_1, new Name(VALID_EVENT_NAME));
        assertEquals(expectedRole, ParserUtil.parseEventRole(roleWithWhitespace, new Name(VALID_EVENT_NAME)));
    }

    @Test
    public void parseMemberRole_validValueWithInternalWhitespace_returnsMemberRole() throws Exception {
        MemberRole expectedRole = new MemberRole(VALID_MEMBER_ROLE_2);
        assertEquals(expectedRole, ParserUtil.parseMemberRole(VALID_MEMBER_ROLE_2));
    }

    @Test
    public void parseEventRole_validValueWithInternalWhitespace_returnsEventRole() throws Exception {
        MemberRole expectedRole = new MemberRole(VALID_EVENT_ROLE_2);
        assertEquals(expectedRole, ParserUtil.parseMemberRole(VALID_EVENT_ROLE_2));
    }


    @Test
    public void parseMemberRole_validValueWithMultipleInternalWhitespace_returnsTrimmedMemberRole() throws Exception {
        MemberRole expectedRole = new MemberRole(VALID_MEMBER_ROLE_2);
        assertEquals(expectedRole, ParserUtil.parseMemberRole(VALID_MEMBER_ROLE_2_WITH_SPACES));
    }

    @Test
    public void parseEventRole_validValueWithMultipleInternalWhitespace_returnsTrimmedEventRole() throws Exception {
        MemberRole expectedRole = new MemberRole(VALID_EVENT_ROLE_2);
        assertEquals(expectedRole, ParserUtil.parseMemberRole(VALID_EVENT_ROLE_2_WITH_SPACES));
    }

    @Test
    public void parseMemberRoles_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMemberRoles(null));
    }

    @Test
    public void parseEventRoles_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventRoles(null, null));
    }

    @Test
    public void parseMemberRoles_collectionWithInvalidRoles_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMemberRoles(
                Arrays.asList(VALID_MEMBER_ROLE_1, INVALID_MEMBER_ROLE))
        );
    }

    @Test
    public void parseEventRoles_collectionWithInvalidRoles_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventRoles(
                Arrays.asList(VALID_EVENT_ROLE_1, INVALID_EVENT_ROLE), new Name(VALID_EVENT_NAME))
        );
    }

    @Test
    public void parseMemberRoles_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseMemberRoles(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseEventRoles_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseEventRoles(Collections.emptyList(), new Name(VALID_EVENT_NAME)).isEmpty());
    }

    @Test
    public void parseMemberRoles_collectionWithValidMemberRoles_returnsMemberRoleSet() throws Exception {
        Set<MemberRole> actualRoleSet = ParserUtil.parseMemberRoles(
                Arrays.asList(VALID_MEMBER_ROLE_1, VALID_MEMBER_ROLE_2)
        );
        Set<MemberRole> expectedRoleSet = new HashSet<MemberRole>(
                Arrays.asList(new MemberRole(VALID_MEMBER_ROLE_1), new MemberRole(VALID_MEMBER_ROLE_2))
        );

        assertEquals(expectedRoleSet, actualRoleSet);
    }

    @Test
    public void parseEventRoles_collectionWithValidEventRoles_returnsEventRoleSet() throws Exception {
        Set<EventRole> actualRoleSet = ParserUtil.parseEventRoles(
                Arrays.asList(VALID_EVENT_ROLE_1, VALID_EVENT_ROLE_2), new Name(VALID_EVENT_NAME)
        );
        Set<EventRole> expectedRoleSet = new HashSet<EventRole>(
                Arrays.asList(new EventRole(VALID_EVENT_ROLE_1, new Name(VALID_EVENT_NAME)),
                        new EventRole(VALID_EVENT_ROLE_2, new Name(VALID_EVENT_NAME)))
        );

        assertEquals(expectedRoleSet, actualRoleSet);
    }

    @Test
    public void parseDateTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDateTime((String) null));
    }

    @Test
    public void parseDateTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_DATETIME));
    }

    @Test
    public void parseDateTime_validValueWithoutWhitespace_returnsDateTime() throws Exception {
        DateTime expectedDateTime = new DateTime(VALID_DATETIME);
        assertEquals(expectedDateTime, ParserUtil.parseDateTime(VALID_DATETIME));
    }

    @Test
    public void parseDateTime_validValueWithWhitespace_returnsTrimmedDateTime() throws Exception {
        String dateTimeWithWhitespace = WHITESPACE + VALID_DATETIME + WHITESPACE;
        DateTime expectedDateTime = new DateTime(VALID_DATETIME);
        assertEquals(expectedDateTime, ParserUtil.parseDateTime(dateTimeWithWhitespace));
    }

    @Test
    public void formatDateTime_null_returnsEmptyString() {
        assertEquals("", ParserUtil.formatDateTime(null));
    }

    @Test
    public void parseDetail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDetail(null));
    }

    @Test
    public void parseDetail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDetail(INVALID_DETAIL));
    }

    @Test
    public void parseDetail_validValueWithoutWhitespace_returnsDetail() throws Exception {
        assertEquals(VALID_DETAIL_1, ParserUtil.parseDetail(VALID_DETAIL_1));
    }

    @Test
    public void parseDetail_validValueWithWhitespace_returnsTrimmedDetail() throws Exception {
        String detailWithWhitespace = WHITESPACE + VALID_DETAIL_1 + WHITESPACE;
        assertEquals(VALID_DETAIL_1, ParserUtil.parseDetail(detailWithWhitespace));
    }

    @Test
    public void parseDetail_validValueWithMultipleInternalWhitespace_returnsTrimmedDetail() throws Exception {
        System.out.println(VALID_DETAIL_2_WITH_SPACES.length());
        assertEquals(VALID_DETAIL_2, ParserUtil.parseDetail(VALID_DETAIL_2_WITH_SPACES));
    }
}
