package seedu.club.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.club.logic.commands.CommandTestUtil.VALID_ROLE_HUSBAND;
import static seedu.club.testutil.Assert.assertThrows;
import static seedu.club.testutil.TypicalEvents.ORIENTATION;
import static seedu.club.testutil.TypicalMembers.ALICE;
import static seedu.club.testutil.TypicalMembers.getTypicalClubBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;
import seedu.club.model.member.exceptions.DuplicateMemberException;
import seedu.club.testutil.EventBuilder;
import seedu.club.testutil.MemberBuilder;

public class ClubBookTest {

    private final ClubBook clubBook = new ClubBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), clubBook.getMemberList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clubBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyClubBook_replacesData() {
        ClubBook newData = getTypicalClubBook();
        clubBook.resetData(newData);
        assertEquals(newData, clubBook);
    }

    @Test
    public void resetData_withDuplicateMembers_throwsDuplicateMemberException() {
        // Two members with the same identity fields
        Member editedAlice = new MemberBuilder(ALICE).withRoles(VALID_ROLE_HUSBAND)
                .build();
        Event editedClass = new EventBuilder(ORIENTATION).build();
        List<Member> newMembers = Arrays.asList(ALICE, editedAlice);
        List<Event> newEvents = Arrays.asList(ORIENTATION, editedClass);
        ClubBookStub newData = new ClubBookStub(newMembers, newEvents);

        assertThrows(DuplicateMemberException.class, () -> clubBook.resetData(newData));
    }

    @Test
    public void hasMember_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clubBook.hasMember(null));
    }

    @Test
    public void hasMember_memberNotInClubBook_returnsFalse() {
        assertFalse(clubBook.hasMember(ALICE));
    }

    @Test
    public void hasMember_memberInClubBook_returnsTrue() {
        clubBook.addMember(ALICE);
        assertTrue(clubBook.hasMember(ALICE));
    }

    @Test
    public void hasMember_memberWithSameIdentityFieldsInClubBook_returnsTrue() {
        clubBook.addMember(ALICE);
        Member editedAlice = new MemberBuilder(ALICE).withRoles(VALID_ROLE_HUSBAND)
                .build();
        assertTrue(clubBook.hasMember(editedAlice));
    }

    @Test
    public void getMemberList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> clubBook.getMemberList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = ClubBook.class.getCanonicalName() + "{members=" + clubBook.getMemberList()
                + ", events=" + clubBook.getEventList() + "}";
        assertEquals(expected, clubBook.toString());
    }

    /**
     * A stub ReadOnlyClubBook whose members list can violate interface constraints.
     */
    private static class ClubBookStub implements ReadOnlyClubBook {
        private final ObservableList<Member> members = FXCollections.observableArrayList();
        private final ObservableList<Event> events = FXCollections.observableArrayList();

        ClubBookStub(Collection<Member> members, Collection<Event> events) {
            this.members.setAll(members);
            this.events.setAll(events);
        }

        @Override
        public ObservableList<Member> getMemberList() {
            return members;
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }
    }

}
