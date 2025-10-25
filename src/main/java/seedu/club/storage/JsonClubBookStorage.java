package seedu.club.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.club.commons.core.LogsCenter;
import seedu.club.commons.exceptions.DataLoadingException;
import seedu.club.commons.exceptions.IllegalValueException;
import seedu.club.commons.util.FileUtil;
import seedu.club.commons.util.JsonUtil;
import seedu.club.model.ClubBook;
import seedu.club.model.ReadOnlyClubBook;

/**
 * A class to access ClubBook data stored as a json file on the hard disk.
 */
public class JsonClubBookStorage implements ClubBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonClubBookStorage.class);

    private Path memberFilePath;
    private Path eventFilePath;

    /**
     * Constructs a {@code JsonClubBookStorage} with the given member and event file paths
     *
     * @param memberFilePath
     * @param eventFilePath
     */
    public JsonClubBookStorage(Path memberFilePath, Path eventFilePath) {
        this.memberFilePath = memberFilePath;
        this.eventFilePath = eventFilePath;
    }

    // ================ Members methods ==============================

    public Path getMemberFilePath() {
        return memberFilePath;
    }

    @Override
    public Optional<ReadOnlyClubBook> readMembers() throws DataLoadingException {
        return readMembers(memberFilePath);
    }

    /**
     * Similar to {@link #readMembers()}.
     *
     * @param memberFilePath location of the member data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyClubBook> readMembers(Path memberFilePath) throws DataLoadingException {
        requireNonNull(memberFilePath);

        Optional<JsonSerializableClubBook> jsonClubBook = JsonUtil.readJsonFile(
                memberFilePath, JsonSerializableClubBook.class);
        if (!jsonClubBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonClubBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + memberFilePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveMembers(ReadOnlyClubBook clubBook) throws IOException {
        saveMembers(clubBook, memberFilePath);
    }

    /**
     * Similar to {@link #saveMembers(ReadOnlyClubBook)}.
     *
     * @param memberFilePath location of the data. Cannot be null.
     */
    public void saveMembers(ReadOnlyClubBook clubBook, Path memberFilePath) throws IOException {
        requireNonNull(clubBook);
        requireNonNull(memberFilePath);

        FileUtil.createIfMissing(memberFilePath);
        JsonUtil.saveJsonFile(new JsonSerializableClubBook(clubBook), memberFilePath);
    }

    // ================ Event methods ==============================

    public Path getEventFilePath() {
        return eventFilePath;
    }

    @Override
    public Optional<ReadOnlyClubBook> readEvents() throws DataLoadingException {
        return readEvents(eventFilePath);
    }

    /**
     * Similar to link {@link #readEvents()}
     *
     * @param eventFilePath location of event data. Cannot be null
     * @return {@code Optional<ReadOnlyClubBook>} containing event data
     * @throws DataLoadingException if loading from storage failed.
     */
    public Optional<ReadOnlyClubBook> readEvents(Path eventFilePath) throws DataLoadingException {
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
    public void saveEvents(ReadOnlyClubBook clubBook) throws IOException {
        saveEvents(clubBook, eventFilePath);
    }

    /**
     * Similar to {@link #saveEvents(ReadOnlyClubBook)}.
     *
     * @param eventFilePath location where event data is to be stored. Cannot be null.
     */
    public void saveEvents(ReadOnlyClubBook clubBook, Path eventFilePath) throws IOException {
        requireNonNull(clubBook);
        requireNonNull(eventFilePath);

        FileUtil.createIfMissing(eventFilePath);
        JsonUtil.saveJsonFile(new JsonSerializableEvent(clubBook), eventFilePath);
    }

    // ================ Club Book methods ==============================

    /**
     * Reads data from both member and event files and store them in a new ClubBook
     *
     * @return {@code Optional<ReadOnlyClubBook>} containing both member and event data
     * @throws DataLoadingException if loading from storage fails
     */
    public Optional<ReadOnlyClubBook> readClubBook() throws DataLoadingException {
        requireNonNull(memberFilePath);
        requireNonNull(eventFilePath);

        Optional<ReadOnlyClubBook> members = readMembers();
        Optional<ReadOnlyClubBook> events = readEvents();

        if (!members.isPresent() || !events.isPresent()) {
            return Optional.empty();
        }
        ClubBook clubBook = new ClubBook();
        members.ifPresent(m -> clubBook.resetData(m));
        events.ifPresent(e -> clubBook.setEvents(e.getEventList()));
        return Optional.of(clubBook);
    }

    /**
     * Saves both member and event data from ClubBook to respective files
     *
     * @param clubBook clubBook containing members and events
     * @throws IOException
     */
    public void saveClubBook(ReadOnlyClubBook clubBook) throws IOException {
        saveMembers(clubBook);
        saveEvents(clubBook);
    }
}
