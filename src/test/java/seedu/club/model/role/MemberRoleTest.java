package seedu.club.model.role;

import static seedu.club.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MemberRoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MemberRole(null));
    }

    @Test
    public void constructor_invalidMemberRoleName_throwsIllegalArgumentException() {
        String invalidRoleName = "";
        assertThrows(IllegalArgumentException.class, () -> new MemberRole(invalidRoleName));
    }

    @Test
    public void isValidMemberRoleName() {
        // null role name
        assertThrows(NullPointerException.class, () -> MemberRole.isValidRoleName(null));
    }

}
