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
import seedu.club.model.ReadOnlyClubBook;

/**
 * A class to access ClubBook data stored as a json file on the hard disk.
 */
public class JsonClubBookStorage implements ClubBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonClubBookStorage.class);

    private Path clubBookFilePath;

    /**
     * Constructs a {@code JsonClubBookStorage} with the given member and event file paths
     *
     * @param clubBookFilePath
     */
    public JsonClubBookStorage(Path clubBookFilePath) {
        this.clubBookFilePath = clubBookFilePath;
    }

    /**
     * Returns the file path of the ClubBook data file.
     */
    public Path getClubBookFilePath() {
        return clubBookFilePath;
    }

    @Override
    public Optional<ReadOnlyClubBook> readClubBook() throws DataLoadingException {
        return readClubBook(clubBookFilePath);
    }

    /**
     * Similar to {@link #readClubBook(Path)} ()}.
     *
     * @param clubBookFilePath location of the clubBook data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyClubBook> readClubBook(Path clubBookFilePath) throws DataLoadingException {
        requireNonNull(clubBookFilePath);

        Optional<JsonSerializableClubBook> jsonClubBook = JsonUtil.readJsonFile(
                clubBookFilePath, JsonSerializableClubBook.class);
        if (!jsonClubBook.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonClubBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + clubBookFilePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveClubBook(ReadOnlyClubBook clubBook) throws IOException {
        saveClubBook(clubBook, clubBookFilePath);
    }

    /**
     * Similar to {@link #saveClubBook(ReadOnlyClubBook)}
     *
     * @param clubBookFilePath location where data is to be stored. Cannot be null.
     */
    public void saveClubBook(ReadOnlyClubBook clubBook, Path clubBookFilePath) throws IOException {
        requireNonNull(clubBook);
        requireNonNull(clubBookFilePath);

        FileUtil.createIfMissing(clubBookFilePath);
        JsonUtil.saveJsonFile(new JsonSerializableClubBook(clubBook), clubBookFilePath);
    }

}
