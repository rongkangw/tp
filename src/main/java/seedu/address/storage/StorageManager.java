package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage membersStorage;
    private UserPrefsStorage userPrefsStorage;
    private EventStorage eventStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage membersStorage, UserPrefsStorage userPrefsStorage,
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
    public Optional<ReadOnlyAddressBook> readMembers() throws DataLoadingException {
        return readMembers(membersStorage.getMemberFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readMembers(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return membersStorage.readMembers(filePath);
    }

    @Override
    public void saveMembers(ReadOnlyAddressBook addressBook) throws IOException {
        saveMembers(addressBook, membersStorage.getMemberFilePath());
    }

    @Override
    public void saveMembers(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        membersStorage.saveMembers(addressBook, filePath);
    }

    // ================ Event methods ==============================

    @Override
    public Path getEventFilePath() {
        return eventStorage.getEventFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readEvents() throws DataLoadingException {
        return readEvents(eventStorage.getEventFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readEvents(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return eventStorage.readEvents(filePath);
    }

    @Override
    public void saveEvents(ReadOnlyAddressBook addressBook) throws IOException {
        saveEvents(addressBook, eventStorage.getEventFilePath());
    }

    @Override
    public void saveEvents(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        eventStorage.saveEvents(addressBook, filePath);
    }
}
