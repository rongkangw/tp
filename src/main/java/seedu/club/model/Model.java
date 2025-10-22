package seedu.club.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.club.commons.core.GuiSettings;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;

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
    Path getMemberStorageFilePath();

    /**
     * Sets the user prefs' club book file path.
     */
    void setMemberStorageFilePath(Path clubBookFilePath);

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
     * */
    ObservableList<Event> getFilteredEventList();

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

}
