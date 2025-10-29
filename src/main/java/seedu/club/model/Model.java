package seedu.club.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.club.commons.core.GuiSettings;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;
import seedu.club.model.name.Name;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Member> PREDICATE_SHOW_ALL_MEMBERS = unused -> true;
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' club book file path.
     */
    Path getClubBookStorageFilePath();

    /**
     * Sets the user prefs' club book file path.
     */
    void setClubBookStorageFilePath(Path clubBookFilePath);

    /**
     * Replaces club book data with the data in {@code clubBook}.
     */
    void setClubBook(ReadOnlyClubBook clubBook);

    /**
     * Returns the ClubBook
     */
    ReadOnlyClubBook getClubBook();

    /**
     * Returns true if a member with the same identity as {@code member} exists in the club book.
     */
    boolean hasMember(Member member);

    /**
     * Deletes the given member.
     * The member must exist in the club book.
     */
    void deleteMember(Member target);

    /**
     * Adds the given member.
     * {@code member} must not already exist in the club book.
     */
    void addMember(Member member);

    /**
     * Replaces the given member {@code target} with {@code editedMember}.
     * {@code target} must exist in the club book.
     * The member identity of {@code editedMember} must not be the same as another existing member in the club book.
     */
    void setMember(Member target, Member editedMember);
    /**
     * Returns an unmodifiable view of the unfiltered member list
     */
    ObservableList<Member> getFullMemberList();

    /**
     * Returns true if an event with the same identity as {@code event} exists in the club book.
     */
    boolean hasEvent(Event event);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the club book.
     */
    void addEvent(Event event);

    /**
     * Deletes the given event.
     * The event must exist in the club book.
     */
    void deleteEvent(Event target);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the club book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the club book.
     */
    void setEvent(Event target, Event editedEvent);

    /**
     * Returns an unmodifiable view of the filtered member list
     */
    ObservableList<Member> getFilteredMemberList();

    /**
     * Updates the filter of the filtered member list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMemberList(Predicate<Member> predicate);

    /**
     * Returns an unmodifiable view of the filtered event list
     */
    ObservableList<Event> getFilteredEventList();

    /**
     * Returns an unmodifiable view of the unfiltered event list
     */
    ObservableList<Event> getFullEventList();

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

    /**
     * Returns index if there exists a member with the same name as {@code name} and -1 otherwise
     */
    int memberNameIndex(Name name);

    /**
     * Returns index if there exists an event with the same name as {@code name} and -1 otherwise
     */
    int eventNameIndex(Name name);

    /**
     * Returns the current state of the ClubBook
     */
    ViewState getViewState();

    /**
     * Changes the current state of the ClubBook.
     * The state must be one of the values defined in {@link ViewState}
     */
    void setViewState(ViewState state);
}
