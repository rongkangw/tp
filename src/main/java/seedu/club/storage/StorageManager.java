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
    private ClubBookStorage membersStorage;
    private UserPrefsStorage userPrefsStorage;
    private EventStorage eventStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ClubBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ClubBookStorage membersStorage, UserPrefsStorage userPrefsStorage,
                          EventStorage eventStorage) {
        this.membersStorage = membersStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.eventStorage = eventStorage;
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
        return membersStorage.getMemberFilePath();
    }

    @Override
    public Optional<ReadOnlyClubBook> readMembers() throws DataLoadingException {
        return readMembers(membersStorage.getMemberFilePath());
    }

    @Override
    public Optional<ReadOnlyClubBook> readMembers(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return membersStorage.readMembers(filePath);
    }

    @Override
    public void saveMembers(ReadOnlyClubBook clubBook) throws IOException {
        saveMembers(clubBook, membersStorage.getMemberFilePath());
    }

    @Override
    public void saveMembers(ReadOnlyClubBook clubBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        membersStorage.saveMembers(clubBook, filePath);
    }

    // ================ Event methods ==============================

    @Override
    public Path getEventFilePath() {
        return eventStorage.getEventFilePath();
    }

    @Override
    public Optional<ReadOnlyClubBook> readEvents() throws DataLoadingException {
        return readEvents(eventStorage.getEventFilePath());
    }

    @Override
    public Optional<ReadOnlyClubBook> readEvents(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return eventStorage.readEvents(filePath);
    }

    @Override
    public void saveEvents(ReadOnlyClubBook clubBook) throws IOException {
        saveEvents(clubBook, eventStorage.getEventFilePath());
    }

    @Override
    public void saveEvents(ReadOnlyClubBook clubBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        eventStorage.saveEvents(clubBook, filePath);
    }
}
