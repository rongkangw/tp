package seedu.club.model.role;

import static seedu.club.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventRoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventRole(null));
    }

    @Test
    public void constructor_invalidEventRoleName_throwsIllegalArgumentException() {
        String invalidRoleName = "";
        assertThrows(IllegalArgumentException.class, () -> new EventRole(invalidRoleName));
    }

    @Test
    public void isValidEventRoleName() {
        // null role name
        assertThrows(NullPointerException.class, () -> EventRole.isValidRoleName(null));
    }

}
