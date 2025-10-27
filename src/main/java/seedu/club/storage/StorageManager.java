package seedu.club.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.club.commons.core.LogsCenter;
import seedu.club.commons.exceptions.DataLoadingException;
import seedu.club.model.ReadOnlyClubBook;
import seedu.club.model.ReadOnlyUserPrefs;
import seedu.club.model.UserPrefs;

/**
 * Manages storage of ClubBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final ClubBookStorage clubBookStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ClubBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ClubBookStorage clubBookStorage, UserPrefsStorage userPrefsStorage) {
        this.clubBookStorage = clubBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ ClubBook methods ==============================
    @Override
    public Path getClubBookFilePath() {
        return clubBookStorage.getClubBookFilePath();
    }

    @Override
    public Optional<ReadOnlyClubBook> readClubBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return clubBookStorage.readClubBook(filePath);
    }

    @Override
    public Optional<ReadOnlyClubBook> readClubBook() throws DataLoadingException {
        return readClubBook(clubBookStorage.getClubBookFilePath());
    }

    @Override
    public void saveClubBook(ReadOnlyClubBook clubBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        clubBookStorage.saveClubBook(clubBook, filePath);
    }

    @Override
    public void saveClubBook(ReadOnlyClubBook clubBook) throws IOException {
        saveClubBook(clubBook, clubBookStorage.getClubBookFilePath());
    }
}
