package seedu.club.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.club.testutil.Assert.assertThrows;
import static seedu.club.testutil.TypicalEvents.MEETING;
import static seedu.club.testutil.TypicalEvents.ORIENTATION;
import static seedu.club.testutil.TypicalEvents.WORKSHOP;
import static seedu.club.testutil.TypicalEvents.getTypicalClubBookWithEvents;
import static seedu.club.testutil.TypicalMembers.ALICE;
import static seedu.club.testutil.TypicalMembers.HOON;
import static seedu.club.testutil.TypicalMembers.IDA;
import static seedu.club.testutil.TypicalMembers.getTypicalClubBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.club.commons.exceptions.DataLoadingException;
import seedu.club.model.ClubBook;
import seedu.club.model.ReadOnlyClubBook;

public class JsonClubBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonClubBookStorageTest");

    @TempDir
    public Path testFolder;
    private Path clubBookFile;
    private JsonClubBookStorage storage;

    @BeforeEach
    public void setUp() {
        Path clubBookFile = testFolder.resolve("TempClubBook.json");
        storage = new JsonClubBookStorage(clubBookFile);
    }

    @Test
    public void readEvents_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readEvents(null));
    }

    private Optional<ReadOnlyClubBook> readEvents(String filePath) throws Exception {
        return new JsonClubBookStorage(Paths.get(filePath))
                .readEvents(addToTestDataPathIfNotNull(filePath));
    }

    @Test
    public void readEvents_missingFile_emptyResult() throws Exception {
        assertFalse(readEvents("NonExistentFile.json").isPresent());
    }

    @Test
    public void readEvents_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readEvents("notJsonFormatClubBook.json"));
    }

    @Test
    public void readEvents_invalidMemberClubBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readEvents("invalidEventClubBook.json"));
    }

    @Test
    public void readEvents_invalidAndValidMemberClubBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readEvents("invalidAndValidEventClubBook.json"));
    }


    @Test
    public void readAndSaveEvents_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempEvents.json");
        ClubBook original = getTypicalClubBookWithEvents();
        JsonClubBookStorage jsonClubBookStorage = new JsonClubBookStorage(filePath);

        // Save in new file and read back
        jsonClubBookStorage.saveEvents(original, filePath);
        ReadOnlyClubBook readBack = jsonClubBookStorage.readEvents(filePath).get();
        assertEquals(original, new ClubBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEvent(MEETING);
        original.removeEvent(ORIENTATION);
        jsonClubBookStorage.saveEvents(original, filePath);
        readBack = jsonClubBookStorage.readEvents(filePath).get();
        assertEquals(original, new ClubBook(readBack));

        // Save and read without specifying file path
        original.addEvent(WORKSHOP);
        jsonClubBookStorage.saveEvents(original); // file path not specified
        readBack = jsonClubBookStorage.readEvents().get(); // file path not specified
        assertEquals(original, new ClubBook(readBack));

    }

    /**
     * Saves {@code clubBook} at the specified {@code filePath}.
     */
    private void saveClubBookWithEvents(ReadOnlyClubBook clubBook, String filePath) {
        try {
            new JsonClubBookStorage(Paths.get(filePath))
                    .saveEvents(clubBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveClubBookWithEvents_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClubBookWithEvents(new ClubBook(), null));
    }

    @Test
    public void readMembers_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMembers(null));
    }

    private Optional<ReadOnlyClubBook> readMembers(String filePath) throws Exception {
        return new JsonClubBookStorage(Paths.get(filePath))
                .readMembers(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void readMembers_missingFile_emptyResult() throws Exception {
        assertFalse(readMembers("NonExistentFile.json").isPresent());
    }

    @Test
    public void readMembers_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readMembers("notJsonFormatClubBook.json"));
    }

    @Test
    public void readMembers_invalidMemberClubBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readMembers("invalidMemberClubBook.json"));
    }

    @Test
    public void readMembers_invalidAndValidMemberClubBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readMembers("invalidAndValidMemberClubBook.json"));
    }

    @Test
    public void readAndSaveMember_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempClubBook.json");
        ClubBook original = getTypicalClubBook();
        JsonClubBookStorage jsonClubBookStorage = new JsonClubBookStorage(filePath);

        // Save in new file and read back
        jsonClubBookStorage.saveMembers(original, filePath);
        ReadOnlyClubBook readBack = jsonClubBookStorage.readMembers(filePath).get();
        assertEquals(original, new ClubBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addMember(HOON);
        original.removeMember(ALICE);
        jsonClubBookStorage.saveMembers(original, filePath);
        readBack = jsonClubBookStorage.readMembers(filePath).get();
        assertEquals(original, new ClubBook(readBack));

        // Save and read without specifying file path
        original.addMember(IDA);
        jsonClubBookStorage.saveMembers(original); // file path not specified
        readBack = jsonClubBookStorage.readMembers().get(); // file path not specified
        assertEquals(original, new ClubBook(readBack));

    }

    @Test
    public void saveClubBook_nullClubBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClubBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code clubBook} at the specified {@code filePath}.
     */
    private void saveClubBook(ReadOnlyClubBook clubBook, String filePath) {
        try {
            new JsonClubBookStorage(Paths.get(filePath))
                    .saveMembers(clubBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveClubBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClubBook(new ClubBook(), null));
    }
}
