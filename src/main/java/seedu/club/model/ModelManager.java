package seedu.club.model;

import static java.util.Objects.requireNonNull;
import static seedu.club.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.club.commons.core.GuiSettings;
import seedu.club.commons.core.LogsCenter;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;
import seedu.club.model.name.Name;

/**
 * Represents the in-memory model of the club book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ClubBook clubBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Member> filteredMembers;
    private final FilteredList<Event> filteredEvents;

    /**
     * Initializes a ModelManager with the given clubBook and userPrefs.
     */
    public ModelManager(ReadOnlyClubBook clubBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(clubBook, userPrefs);

        logger.fine("Initializing with club book: " + clubBook + " and user prefs " + userPrefs);

        this.clubBook = new ClubBook(clubBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredMembers = new FilteredList<>(this.clubBook.getMemberList());
        filteredEvents = new FilteredList<>(this.clubBook.getEventList());
    }

    public ModelManager() {
        this(new ClubBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getClubBookFilePath() {
        return userPrefs.getClubBookFilePath();
    }

    @Override
    public void setClubBookFilePath(Path clubBookFilePath) {
        requireNonNull(clubBookFilePath);
        userPrefs.setClubBookFilePath(clubBookFilePath);
    }

    //=========== ClubBook ================================================================================

    @Override
    public void setClubBook(ReadOnlyClubBook clubBook) {
        this.clubBook.resetData(clubBook);
    }

    @Override
    public ReadOnlyClubBook getClubBook() {
        return clubBook;
    }

    //=========== Member =============================================================
    @Override
    public boolean hasMember(Member member) {
        requireNonNull(member);
        return clubBook.hasMember(member);
    }

    @Override
    public void deleteMember(Member target) {
        clubBook.removeMember(target);
    }

    @Override
    public void addMember(Member member) {
        clubBook.addMember(member);
        updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
    }

    @Override
    public void setMember(Member target, Member editedMember) {
        requireAllNonNull(target, editedMember);

        clubBook.setMember(target, editedMember);
    }

    //=========== Filtered Member List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Member} backed by the internal list of
     * {@code versionedClubBook}
     */
    @Override
    public ObservableList<Member> getFilteredMemberList() {
        return filteredMembers;
    }

    @Override
    public void updateFilteredMemberList(Predicate<Member> predicate) {
        requireNonNull(predicate);
        filteredMembers.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return clubBook.equals(otherModelManager.clubBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredMembers.equals(otherModelManager.filteredMembers)
                && filteredEvents.equals(otherModelManager.filteredEvents);
    }

    //=========== Event =============================================================

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return clubBook.hasEvent(event);
    }

    @Override
    public void addEvent(Event event) {
        clubBook.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void deleteEvent(Event target) {
        clubBook.removeEvent(target);
    }

    /* Edit commands are to be implemented in a future iteration
    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        clubBook.setEvent(target, editedEvent);
    }
     */

    //=========== Filtered Event List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Event} backed by the internal list of
     * {@code versionedClubBook}
     */
    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    @Override
    public boolean containsMemberName(Name name) {
        for (Member member : clubBook.getMemberList()) {
            if (member.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsEventName(Name name) {
        for (Event event : clubBook.getEventList()) {
            if (event.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
