package seedu.club.model;

import java.nio.file.Path;

import seedu.club.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getMemberStorageFilePath();

    Path getEventStorageFilePath();

}
