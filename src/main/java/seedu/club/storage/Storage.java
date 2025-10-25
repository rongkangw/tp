package seedu.club.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.club.commons.exceptions.DataLoadingException;
import seedu.club.model.ReadOnlyClubBook;
import seedu.club.model.ReadOnlyUserPrefs;
import seedu.club.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ClubBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getClubBookFilePath();

    @Override
    Optional<ReadOnlyClubBook> readMembers() throws DataLoadingException;

    @Override
    void saveMembers(ReadOnlyClubBook clubBook) throws IOException;

    @Override
    Optional<ReadOnlyClubBook> readEvents() throws DataLoadingException;

    @Override
    void saveEvents(ReadOnlyClubBook clubBook) throws IOException;

    @Override
    Optional<ReadOnlyClubBook> readClubBook() throws DataLoadingException;

    @Override
    void saveClubBook(ReadOnlyClubBook clubBook) throws IOException;
}
