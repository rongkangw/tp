package seedu.club.model.role;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RoleTest {
    @Test
    public void isValidRoleName() {
        // invalid role name
        assertFalse(Role.isValidRoleName("")); // empty string
        assertFalse(Role.isValidRoleName(" ")); // spaces only
        assertFalse(Role.isValidRoleName("^")); // only non-alphanumeric characters
        assertFalse(Role.isValidRoleName("director*")); // contains non-alphanumeric characters
        // longer than 30 characters
        assertFalse(Role.isValidRoleName("Vice President of Interdisciplinary Collaborations"));

        // valid role name
        assertTrue(Role.isValidRoleName("director")); // alphabets only
        assertTrue(Role.isValidRoleName("12345")); // numbers only
        assertTrue(Role.isValidRoleName("2nd Director")); // alphanumeric characters
        assertTrue(Role.isValidRoleName("Secretary")); // with capital letters
        assertTrue(Role.isValidRoleName("Director of Community Outreach")); // long names within 30 characters
    }
}
