package seedu.club.logic.commands.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.club.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.club.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.club.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.club.logic.commands.CommandTestUtil.VALID_MEMBER_NAME_BOB;
import static seedu.club.logic.commands.CommandTestUtil.VALID_MEMBER_ROLE_PRESIDENT;
import static seedu.club.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.club.logic.commands.member.EditMemberCommand.EditMemberDescriptor;
import seedu.club.testutil.EditMemberDescriptorBuilder;

/**
 * Contains unit tests for {@code EditMemberDescriptor} class defined in {@code EditCommand}.
 */
public class EditMemberDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditMemberDescriptor descriptorWithSameValues = new EditMemberDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditMemberDescriptor editedAmy = new EditMemberDescriptorBuilder(DESC_AMY)
                .withName(VALID_MEMBER_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditMemberDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditMemberDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different roles -> returns false
        editedAmy = new EditMemberDescriptorBuilder(DESC_AMY).withRoles(VALID_MEMBER_ROLE_PRESIDENT).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditMemberDescriptor editMemberDescriptor = new EditMemberDescriptor();
        String expected = EditMemberDescriptor.class.getCanonicalName() + "{name="
                + editMemberDescriptor.getName().orElse(null) + ", phone="
                + editMemberDescriptor.getPhone().orElse(null) + ", email="
                + editMemberDescriptor.getEmail().orElse(null) + ", roles="
                + editMemberDescriptor.getRoles().orElse(null) + "}";
        assertEquals(expected, editMemberDescriptor.toString());
    }
}
