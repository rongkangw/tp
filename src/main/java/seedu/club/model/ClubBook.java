package seedu.club.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.club.commons.util.ToStringBuilder;
import seedu.club.model.event.Event;
import seedu.club.model.event.UniqueEventList;
import seedu.club.model.member.Member;
import seedu.club.model.member.UniqueMemberList;

/**
 * Wraps all data at the club-book level
 * Duplicates are not allowed (by .isSameMember and .isSameEvent comparisons)
 */
public class ClubBook implements ReadOnlyClubBook {

    private final UniqueMemberList members;
    private final UniqueEventList events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        members = new UniqueMemberList();
        events = new UniqueEventList();
    }

    public ClubBook() {}

    /**
     * Creates a ClubBook using the Members in the {@code toBeCopied}
     */
    public ClubBook(ReadOnlyClubBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the member list with {@code members}.
     * {@code members} must not contain duplicate members.
     */
    public void setMembers(List<Member> members) {
        this.members.setMembers(members);
    }

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Resets the existing data of this {@code ClubBook} with {@code newData}.
     */
    public void resetData(ReadOnlyClubBook newData) {
        requireNonNull(newData);

        setMembers(newData.getMemberList());
        setEvents(newData.getEventList());
    }

    //// member-level operations

    /**
     * Returns true if a member with the same identity as {@code member} exists in the club book.
     */
    public boolean hasMember(Member member) {
        requireNonNull(member);
        return members.contains(member);
    }

    /**
     * Adds a member to the club book.
     * The member must not already exist in the club book.
     */
    public void addMember(Member p) {
        members.add(p);
    }

    /**
     * Replaces the given member {@code target} in the list with {@code editedMember}.
     * {@code target} must exist in the club book.
     * The member identity of {@code editedMember} must not be the same as another existing member in the club book.
     */
    public void setMember(Member target, Member editedMember) {
        requireNonNull(editedMember);

        members.setMember(target, editedMember);
    }

    /**
     * Removes {@code key} (member) from this {@code ClubBook}.
     * {@code key} must exist in the club book.
     */
    public void removeMember(Member key) {
        members.remove(key);
    }

    //// event-level operations

    /**
     * Returns true if an event with the same identity as {@code event} exists in the club book.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds an event to the club book.
     * The event must not already exist in the club book.
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Replaces the given {@code target} (event) in the list with {@code editedEvent}.
     * {@code target} must exist in the club book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the club book.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);

        events.setEvent(target, editedEvent);
    }

    /**
     * Removes {@code key} from this {@code ClubBook}.
     * {@code key} must exist in the club book.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("members", members)
                .add("events", events)
                .toString();
    }

    @Override
    public ObservableList<Member> getMemberList() {
        return members.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClubBook)) {
            return false;
        }

        ClubBook otherClubBook = (ClubBook) other;
        return members.equals(otherClubBook.members) && events.equals(otherClubBook.events);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(members, events);
    }
}
