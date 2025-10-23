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
    private ClubBookStorage clubBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ClubBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ClubBookStorage clubBookStorage, UserPrefsStorage userPrefsStorage,) {
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


    // ================ Members methods ==============================

    @Override
    public Path getMemberFilePath() {
        return clubBookStorage.getMemberFilePath();
    }

    @Override
    public Optional<ReadOnlyClubBook> readMembers() throws DataLoadingException {
        return readMembers(clubBookStorage.getMemberFilePath());
    }

    @Override
    public Optional<ReadOnlyClubBook> readMembers(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return clubBookStorage.readMembers(filePath);
    }

    @Override
    public void saveMembers(ReadOnlyClubBook clubBook) throws IOException {
        saveMembers(clubBook, clubBookStorage.getMemberFilePath());
    }

    @Override
    public void saveMembers(ReadOnlyClubBook clubBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        clubBookStorage.saveMembers(clubBook, filePath);
    }

    // ================ Event methods ==============================

    @Override
    public Path getEventFilePath() {
        return clubBookStorage.getEventFilePath();
    }

    @Override
    public Optional<ReadOnlyClubBook> readEvents() throws DataLoadingException {
        return readEvents(clubBookStorage.getEventFilePath());
    }

    @Override
    public Optional<ReadOnlyClubBook> readEvents(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return clubBookStorage.readEvents(filePath);
    }

    @Override
    public void saveEvents(ReadOnlyClubBook clubBook) throws IOException {
        saveEvents(clubBook, clubBookStorage.getEventFilePath());
    }

    @Override
    public void saveEvents(ReadOnlyClubBook clubBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        clubBookStorage.saveEvents(clubBook, filePath);
    }

    // ================ ClubBook methods ==============================

}
