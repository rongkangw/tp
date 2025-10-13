package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMemberFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyAddressBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyAddressBook> readMembers() throws DataLoadingException;

    /**
     * @see #getMemberFilePath()
     */
    Optional<ReadOnlyAddressBook> readMembers(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMembers(ReadOnlyAddressBook addressBook) throws IOException;

    /**
     * @see #saveMembers(ReadOnlyAddressBook)
     */
    void saveMembers(ReadOnlyAddressBook addressBook, Path filePath) throws IOException;

}
