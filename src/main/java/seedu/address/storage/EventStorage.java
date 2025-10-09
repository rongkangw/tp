package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;

/**
 * Represents a storage for {@link seedu.address.model.event.Event}
 */
public interface EventStorage {


    /**
     * Returns the file path of the data file.
     * @return
     */
    Path getEventFilePath();

    Optional<Event> readEvents() throws DataLoadingException;

    Optional<ReadOnlyAddressBook> readEvents(Path filepath) throws DataLoadingException;

    void saveEvents(ReadOnlyAddressBook events) throws IOException;

    void saveEvents(ReadOnlyAddressBook events, Path filePath) throws IOException;
}
