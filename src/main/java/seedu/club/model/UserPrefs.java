package seedu.club.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.club.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path clubBookFilePath = Paths.get("data" , "clubbook.json");
    private Path eventStorageFilePath = Paths.get("data", "events.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setClubBookFilePath(newUserPrefs.getClubBookFilePath());
        setEventStorageFilePath(newUserPrefs.getEventStorageFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getClubBookFilePath() {
        return clubBookFilePath;
    }

    public void setClubBookFilePath(Path clubBookFilePath) {
        requireNonNull(clubBookFilePath);
        this.clubBookFilePath = clubBookFilePath;
    }

    public Path getEventStorageFilePath() {
        return eventStorageFilePath;
    }

    public void setEventStorageFilePath(Path eventStorageFilePath) {
        requireNonNull(eventStorageFilePath);
        this.eventStorageFilePath = eventStorageFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserPrefs)) {
            return false;
        }

        UserPrefs otherUserPrefs = (UserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && clubBookFilePath.equals(otherUserPrefs.clubBookFilePath)
                && eventStorageFilePath.equals(otherUserPrefs.eventStorageFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, clubBookFilePath, eventStorageFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + clubBookFilePath);
        sb.append("\nLocal event data file location : " + eventStorageFilePath);
        return sb.toString();
    }

}
