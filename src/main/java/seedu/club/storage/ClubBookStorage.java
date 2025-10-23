package seedu.club.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.club.commons.exceptions.DataLoadingException;
import seedu.club.model.ClubBook;
import seedu.club.model.ReadOnlyClubBook;

/**
 * Represents a storage for {@link ClubBook}.
 */
public interface ClubBookStorage {

    /**
     * Returns the file path of the member data file.
     */
    Path getMemberFilePath();

    /**
     * Returns the file path of the event data file.
     */
    Path getEventFilePath();

    /**
     * Returns ClubBook data as a {@link ReadOnlyClubBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyClubBook> readMembers() throws DataLoadingException;

    /**
     * @see #getMemberFilePath()
     */
    Optional<ReadOnlyClubBook> readMembers(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyClubBook} to the storage.
     * @param clubBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMembers(ReadOnlyClubBook clubBook) throws IOException;

    /**
     * @see #saveMembers(ReadOnlyClubBook)
     */
    void saveMembers(ReadOnlyClubBook clubBook, Path filePath) throws IOException;

    /**
     * Returns ClubBook data as a {@link ReadOnlyClubBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyClubBook> readEvents() throws DataLoadingException;

    Optional<ReadOnlyClubBook> readEvents(Path filepath) throws DataLoadingException;

    void saveEvents(ReadOnlyClubBook events) throws IOException;

    void saveEvents(ReadOnlyClubBook events, Path filePath) throws IOException;

    Optional<ReadOnlyClubBook> readClubBook() throws DataLoadingException;

    void saveClubBook(ReadOnlyClubBook clubBook) throws IOException;
}
