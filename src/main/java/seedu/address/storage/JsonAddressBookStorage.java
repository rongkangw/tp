package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage, EventStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path memberFilePath;
    private Path eventFilePath;

    /**
     * Constructs a {@code JsonAddressBookStorage} with the given member and event file paths
     *
     * @param memberFilePath
     * @param eventFilePath
     */
    public JsonAddressBookStorage(Path memberFilePath, Path eventFilePath) {
        this.memberFilePath = memberFilePath;
        this.eventFilePath = eventFilePath;
    }

    // ================ Members methods ==============================

    public Path getMemberFilePath() {
        return memberFilePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readMembers() throws DataLoadingException {
        return readMembers(memberFilePath);
    }

    /**
     * Similar to {@link #readMembers()}.
     *
     * @param memberFilePath location of the member data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyAddressBook> readMembers(Path memberFilePath) throws DataLoadingException {
        requireNonNull(memberFilePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                memberFilePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + memberFilePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveMembers(ReadOnlyAddressBook addressBook) throws IOException {
        saveMembers(addressBook, memberFilePath);
    }

    /**
     * Similar to {@link #saveMembers(ReadOnlyAddressBook)}.
     *
     * @param memberFilePath location of the data. Cannot be null.
     */
    public void saveMembers(ReadOnlyAddressBook addressBook, Path memberFilePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(memberFilePath);

        FileUtil.createIfMissing(memberFilePath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), memberFilePath);
    }

    // ================ Event methods ==============================

    public Path getEventFilePath() {
        return eventFilePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readEvents() throws DataLoadingException {
        return readEvents(eventFilePath);
    }

    /**
     * Similar to link {@link #readEvents()}
     *
     * @param eventFilePath location of event data. Cannot be null
     * @return {@code Optional<ReadOnlyAddressBook>} containing event data
     * @throws DataLoadingException if loading from storage failed.
     */
    public Optional<ReadOnlyAddressBook> readEvents(Path eventFilePath) throws DataLoadingException {
        requireNonNull(eventFilePath);

        Optional<JsonSerializableEvent> jsonEvents = JsonUtil.readJsonFile(
                eventFilePath, JsonSerializableEvent.class);
        if (!jsonEvents.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonEvents.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + eventFilePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveEvents(ReadOnlyAddressBook addressBook) throws IOException {
        saveEvents(addressBook, eventFilePath);
    }

    /**
     * Similar to {@link #saveEvents(ReadOnlyAddressBook)}.
     *
     * @param eventFilePath location where event data is to be stored. Cannot be null.
     */
    public void saveEvents(ReadOnlyAddressBook addressBook, Path eventFilePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(eventFilePath);

        FileUtil.createIfMissing(eventFilePath);
        JsonUtil.saveJsonFile(new JsonSerializableEvent(addressBook), eventFilePath);
    }

    // ================ Address Book methods ==============================

    /**
     * Reads data from both member and event files and store them in a new AddressBook
     *
     * @param memberFilePath location where member data is stored
     * @param eventFilePath location where event data is stored
     * @return {@code Optional<ReadOnlyAddressBook>} containing both member and event data
     * @throws DataLoadingException if loading from storage fails
     */
    public Optional<ReadOnlyAddressBook> readFullAddressBook(Path memberFilePath,
                                                             Path eventFilePath) throws DataLoadingException {
        requireNonNull(memberFilePath);
        requireNonNull(eventFilePath);

        Optional<ReadOnlyAddressBook> members = readMembers();
        Optional<ReadOnlyAddressBook> events = readEvents();

        AddressBook addressBook = new AddressBook();
        members.ifPresent(m -> addressBook.resetData(m));
        events.ifPresent(e -> addressBook.setEvents(e.getEventList()));
        return Optional.of(addressBook);
    }

    /**
     * Saves both member and event data from AddressBook to respective files
     *
     * @param addressBook addressBook containing members and events
     * @throws IOException
     */
    public void saveFullAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveMembers(addressBook);
        saveEvents(addressBook);
    }
}
