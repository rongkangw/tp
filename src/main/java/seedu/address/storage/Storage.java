package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage, EventStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getMemberFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readMembers() throws DataLoadingException;

    @Override
    void saveMembers(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Path getEventFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readEvents() throws DataLoadingException;

    @Override
    void saveEvents(ReadOnlyAddressBook addressBook) throws IOException;
}
