package seedu.club.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.club.commons.exceptions.DataLoadingException;
import seedu.club.model.ReadOnlyClubBook;

/**
 * Represents a storage for {@link seedu.club.model.event.Event}
 */
public interface EventStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getEventFilePath();

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
}
