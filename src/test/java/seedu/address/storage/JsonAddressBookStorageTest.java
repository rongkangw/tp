package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMembers.ALICE;
import static seedu.address.testutil.TypicalMembers.HOON;
import static seedu.address.testutil.TypicalMembers.IDA;
import static seedu.address.testutil.TypicalMembers.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

public class JsonAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    Path memberFile = testFolder.resolve("TempMembers.json");
    Path eventFile = testFolder.resolve("TempEvents.json");
    JsonAddressBookStorage storage = new JsonAddressBookStorage(memberFile, eventFile);

    @Test
    public void readMembers_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMembers(null));
    }

    private java.util.Optional<ReadOnlyAddressBook> readMembers(String filePath) throws Exception {
        return new JsonAddressBookStorage(memberFile, eventFile).readMembers(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMembers("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readMembers("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readMembers_invalidMemberAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readMembers("invalidMemberAddressBook.json"));
    }

    @Test
    public void readMembers_invalidAndValidMemberAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readMembers("invalidAndValidMemberAddressBook.json"));
    }

    @Test
    public void readAndSaveMember_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        AddressBook original = getTypicalAddressBook();
        JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(filePath, eventFile);

        // Save in new file and read back
        jsonAddressBookStorage.saveMembers(original, filePath);
        ReadOnlyAddressBook readBack = jsonAddressBookStorage.readMembers(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addMember(HOON);
        original.removeMember(ALICE);
        jsonAddressBookStorage.saveMembers(original, filePath);
        readBack = jsonAddressBookStorage.readMembers(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Save and read without specifying file path
        original.addMember(IDA);
        jsonAddressBookStorage.saveMembers(original); // file path not specified
        readBack = jsonAddressBookStorage.readMembers().get(); // file path not specified
        assertEquals(original, new AddressBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyAddressBook addressBook, String filePath) {
        try {
            new JsonAddressBookStorage(Paths.get(filePath), eventFile)
                    .saveMembers(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new AddressBook(), null));
    }
}
