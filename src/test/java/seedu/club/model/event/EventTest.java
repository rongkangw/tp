package seedu.club.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.club.logic.commands.CommandTestUtil.VALID_EVENT_NAME_BEACHDAY;
import static seedu.club.logic.commands.CommandTestUtil.VALID_EVENT_NAME_ORIENTATION;
import static seedu.club.logic.commands.CommandTestUtil.VALID_EVENT_ROLE_FOODIC;
import static seedu.club.logic.commands.CommandTestUtil.VALID_FROM_BEACHDAY;
import static seedu.club.logic.commands.CommandTestUtil.VALID_FROM_ORIENTATION;
import static seedu.club.logic.commands.CommandTestUtil.VALID_TO_BEACHDAY;
import static seedu.club.testutil.Assert.assertThrows;
import static seedu.club.testutil.TypicalEvents.BEACH_DAY;
import static seedu.club.testutil.TypicalEvents.ORIENTATION;

import org.junit.jupiter.api.Test;

import seedu.club.testutil.EventBuilder;

public class EventTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Event event = new EventBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> event.getRoles().remove(0));
    }

    @Test
    public void isSameEvent() {
        // same object -> returns true
        assertTrue(ORIENTATION.isSameEvent(ORIENTATION));

        // null -> returns false
        assertFalse(ORIENTATION.isSameEvent(null));

        // same name, all other attributes different -> returns true
        Event sameOrientationName = new EventBuilder(ORIENTATION).withFrom(VALID_FROM_BEACHDAY)
                .withTo(VALID_TO_BEACHDAY).withDetail("aa").withRoles(VALID_EVENT_ROLE_FOODIC).build();
        assertTrue(ORIENTATION.isSameEvent(sameOrientationName));

        // different name, all other attributes same -> returns false
        Event diffOrientationName = new EventBuilder(ORIENTATION).withName(VALID_EVENT_NAME_BEACHDAY).build();
        assertFalse(ORIENTATION.isSameEvent(diffOrientationName));

        // name differs in case, all other attributes same -> returns false
        Event diffNameCaseOrientation = new EventBuilder(ORIENTATION)
                .withName(VALID_EVENT_NAME_ORIENTATION.toLowerCase()).build();
        assertFalse(ORIENTATION.isSameEvent(diffNameCaseOrientation));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_EVENT_NAME_ORIENTATION + " ";
        Event trailingNameOrientation = new EventBuilder(ORIENTATION).withName(nameWithTrailingSpaces).build();
        assertFalse(ORIENTATION.isSameEvent(trailingNameOrientation));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event orientationCopy = new EventBuilder(ORIENTATION).build();
        assertTrue(ORIENTATION.equals(orientationCopy));

        // same object -> returns true
        assertTrue(ORIENTATION.equals(ORIENTATION));

        // null -> returns false
        assertFalse(ORIENTATION.equals(null));

        // different type -> returns false
        assertFalse(ORIENTATION.equals(5));

        // different event -> returns false
        assertFalse(ORIENTATION.equals(BEACH_DAY));

        // different name -> returns false
        Event editedOrientation = new EventBuilder(ORIENTATION).withName(VALID_EVENT_NAME_BEACHDAY).build();
        assertFalse(ORIENTATION.equals(editedOrientation));

        // different from -> returns false
        editedOrientation = new EventBuilder(BEACH_DAY).withFrom(VALID_FROM_ORIENTATION).build();
        assertFalse(ORIENTATION.equals(editedOrientation));

        // different to -> returns false
        editedOrientation = new EventBuilder(ORIENTATION).withTo(VALID_TO_BEACHDAY).build();
        assertFalse(ORIENTATION.equals(editedOrientation));

        // different roles -> returns false
        editedOrientation = new EventBuilder(ORIENTATION).withRoles(VALID_EVENT_ROLE_FOODIC).build();
        assertFalse(ORIENTATION.equals(editedOrientation));
    }

    @Test
    public void toStringMethod() {
        String expected = Event.class.getCanonicalName()
                + "{name=" + ORIENTATION.getName()
                + ", from=" + ORIENTATION.getFrom()
                + ", to=" + ORIENTATION.getTo()
                + ", detail=" + ORIENTATION.getDetail()
                + ", roles=" + ORIENTATION.getRoles()
                + ", roster=" + ORIENTATION.getRoster()
                + "}";
        assertEquals(expected, ORIENTATION.toString());
    }
}
