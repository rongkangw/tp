package seedu.club.model.role;

import static seedu.club.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.club.model.name.Name;

public class EventRoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventRole(null, null));
    }

    @Test
    public void constructor_invalidEventRoleName_throwsIllegalArgumentException() {
        String invalidRoleName = "";
        String validEventName = "Meeting";
        assertThrows(IllegalArgumentException.class, () -> new EventRole(invalidRoleName, new Name(validEventName)));
    }

    @Test
    public void isValidEventRoleName() {
        // null role name
        assertThrows(NullPointerException.class, () -> EventRole.isValidRoleName(null));
    }

}
