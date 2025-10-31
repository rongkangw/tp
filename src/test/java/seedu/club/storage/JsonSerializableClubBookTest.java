package seedu.club.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.club.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.club.commons.exceptions.IllegalValueException;
import seedu.club.commons.util.JsonUtil;
import seedu.club.model.ClubBook;
import seedu.club.testutil.TypicalClubBook;
import seedu.club.testutil.TypicalMembers;

public class JsonSerializableClubBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableClubBookTest");
    private static final Path TYPICAL_MEMBERS_FILE = TEST_DATA_FOLDER.resolve("typicalMemberClubBook.json");
    private static final Path INVALID_MEMBER_FILE = TEST_DATA_FOLDER.resolve("invalidMemberOnlyClubBook.json");
    private static final Path DUPLICATE_MEMBER_FILE = TEST_DATA_FOLDER.resolve("duplicateMemberOnlyClubBook.json");
    private static final Path TYPICAL_EVENTS_FILE = TEST_DATA_FOLDER.resolve("typicalEventOnlyClubBook.json");
    private static final Path INVALID_EVENTS_FILE = TEST_DATA_FOLDER.resolve("invalidEventOnlyClubBook.json");
    private static final Path DUPLICATE_EVENT_FILE = TEST_DATA_FOLDER.resolve("duplicateEventOnlyClubBook.json");
    private static final Path TYPICAL_CLUBBOOK_FILE = TEST_DATA_FOLDER.resolve("typicalClubBook.json");

    @Test
    public void toModelType_typicalEventsFile_success() throws Exception {
        JsonSerializableClubBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENTS_FILE,
                JsonSerializableClubBook.class).get();
        ClubBook clubBookFromFile = dataFromFile.toModelType();
        ClubBook typicalEventsClubBook = TypicalClubBook.getTypicalEventOnlyClubBook();
        assertEquals(clubBookFromFile, typicalEventsClubBook);
    }

    @Test
    public void toModelType_invalidEventFile_throwsIllegalValueException() throws Exception {
        JsonSerializableClubBook dataFromFile = JsonUtil.readJsonFile(INVALID_EVENTS_FILE,
                JsonSerializableClubBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEvents_throwsIllegalValueException() throws Exception {
        JsonSerializableClubBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EVENT_FILE,
                JsonSerializableClubBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableClubBook.MESSAGE_DUPLICATE_EVENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalMembersFile_success() throws Exception {
        JsonSerializableClubBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_MEMBERS_FILE,
                JsonSerializableClubBook.class).get();
        ClubBook clubBookFromFile = dataFromFile.toModelType();
        ClubBook typicalMembersClubBook = TypicalMembers.getTypicalMemberOnlyClubBook();
        assertEquals(clubBookFromFile, typicalMembersClubBook);
    }

    @Test
    public void toModelType_invalidMemberFile_throwsIllegalValueException() throws Exception {
        JsonSerializableClubBook dataFromFile = JsonUtil.readJsonFile(INVALID_MEMBER_FILE,
                JsonSerializableClubBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateMembers_throwsIllegalValueException() throws Exception {
        JsonSerializableClubBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MEMBER_FILE,
                JsonSerializableClubBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableClubBook.MESSAGE_DUPLICATE_MEMBER,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalClubBookFile_success() throws Exception {
        JsonSerializableClubBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_CLUBBOOK_FILE,
                JsonSerializableClubBook.class).get();
        ClubBook clubBookFromFile = dataFromFile.toModelType();
        ClubBook typicalClubBook = TypicalClubBook.getTypicalClubBook();
        assertEquals(clubBookFromFile, typicalClubBook);
    }

}
