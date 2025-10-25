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
     * Returns the file path of the ClubBook data file.
     */
    Path getClubBookFilePath();

    /**
     * Returns ClubBook members data as a {@link ReadOnlyClubBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyClubBook> readMembers() throws DataLoadingException;

    /**
     * @see #readMembers()
     */
    Optional<ReadOnlyClubBook> readMembers(Path filePath) throws DataLoadingException;

    /**
     * Saves members from the given {@link ReadOnlyClubBook} to the storage.
     * @param clubBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMembers(ReadOnlyClubBook clubBook) throws IOException;

    /**
     * @see #saveMembers(ReadOnlyClubBook)
     */
    void saveMembers(ReadOnlyClubBook clubBook, Path filePath) throws IOException;

    /**
     * Returns ClubBook events data as a {@link ReadOnlyClubBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyClubBook> readEvents() throws DataLoadingException;

    /**
     * @see #readEvents()
     */
    Optional<ReadOnlyClubBook> readEvents(Path filepath) throws DataLoadingException;

    /**
     * Saves events from the given {@link ReadOnlyClubBook} to the storage.
     * @param clubBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveEvents(ReadOnlyClubBook clubBook) throws IOException;

    /**
     * @see #saveEvents(ReadOnlyClubBook)
     */
    void saveEvents(ReadOnlyClubBook events, Path filePath) throws IOException;

    /**
     * Returns ClubBook data as a {@link ReadOnlyClubBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyClubBook> readClubBook() throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyClubBook} to the storage.
     * @param clubBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveClubBook(ReadOnlyClubBook clubBook) throws IOException;
}
