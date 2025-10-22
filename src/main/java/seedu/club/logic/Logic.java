package seedu.club.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.club.commons.core.GuiSettings;
import seedu.club.logic.commands.CommandResult;
import seedu.club.logic.commands.exceptions.CommandException;
import seedu.club.logic.parser.exceptions.ParseException;
import seedu.club.model.ReadOnlyClubBook;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the ClubBook.
     *
     * @see seedu.club.model.Model#getClubBook()
     */
    ReadOnlyClubBook getClubBook();

    /** Returns an unmodifiable view of the filtered list of members */
    ObservableList<Member> getFilteredMemberList();

    /** Returns an unmodifiable view of the filtered list of events */
    ObservableList<Event> getFilteredEventList();

    /**
     * Returns the user prefs' club book file path.
     */
    Path getMemberStorageFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
