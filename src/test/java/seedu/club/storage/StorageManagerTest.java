package seedu.club.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.club.testutil.TypicalMembers.getTypicalClubBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.club.commons.core.GuiSettings;
import seedu.club.model.ClubBook;
import seedu.club.model.ReadOnlyClubBook;
import seedu.club.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonClubBookStorage clubBookStorage = new JsonClubBookStorage(getTempFilePath("ab"),
                getTempFilePath("bc"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(clubBookStorage, userPrefsStorage, clubBookStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void clubBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonClubBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonClubBookStorageTest} class.
         */
        ClubBook original = getTypicalClubBook();
        storageManager.saveMembers(original);
        ReadOnlyClubBook retrieved = storageManager.readMembers().get();
        assertEquals(original, new ClubBook(retrieved));
    }

    @Test
    public void getClubBookFilePath() {
        assertNotNull(storageManager.getMemberFilePath());
    }

}
