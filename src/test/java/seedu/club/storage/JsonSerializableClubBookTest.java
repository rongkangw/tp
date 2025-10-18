package seedu.club.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.club.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.club.commons.exceptions.IllegalValueException;
import seedu.club.commons.util.JsonUtil;
import seedu.club.model.ClubBook;
import seedu.club.testutil.TypicalMembers;

public class JsonSerializableClubBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableClubBookTest");
    private static final Path TYPICAL_MEMBERS_FILE = TEST_DATA_FOLDER.resolve("typicalMembersClubBook.json");
    private static final Path INVALID_MEMBER_FILE = TEST_DATA_FOLDER.resolve("invalidMemberClubBook.json");
    private static final Path DUPLICATE_MEMBER_FILE = TEST_DATA_FOLDER.resolve("duplicateMemberClubBook.json");

    @Test
    public void toModelType_typicalMembersFile_success() throws Exception {
        JsonSerializableClubBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_MEMBERS_FILE,
                JsonSerializableClubBook.class).get();
        ClubBook clubBookFromFile = dataFromFile.toModelType();
        ClubBook typicalMembersClubBook = TypicalMembers.getTypicalClubBook();
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

}
