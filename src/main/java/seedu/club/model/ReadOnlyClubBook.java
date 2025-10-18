package seedu.club.model;

import javafx.collections.ObservableList;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;

/**
 * Unmodifiable view of a club book
 */
public interface ReadOnlyClubBook {

    /**
     * Returns an unmodifiable view of the members list.
     * This list will not contain any duplicate members.
     */
    ObservableList<Member> getMemberList();

    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();

}
