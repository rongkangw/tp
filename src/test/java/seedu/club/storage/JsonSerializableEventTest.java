package seedu.club.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.club.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.club.commons.exceptions.IllegalValueException;
import seedu.club.commons.util.JsonUtil;
import seedu.club.model.ClubBook;
import seedu.club.testutil.TypicalEvents;

public class JsonSerializableEventTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableEventTest");
    private static final Path TYPICAL_EVENTS_FILE = TEST_DATA_FOLDER.resolve("typicalEvents.json");
    private static final Path INVALID_EVENTS_FILE = TEST_DATA_FOLDER.resolve("invalidEvent.json");
    private static final Path DUPLICATE_EVENT_FILE = TEST_DATA_FOLDER.resolve("duplicateEvents.json");

    @Test
    public void toModelType_typicalEventsFile_success() throws Exception {
        JsonSerializableEvent dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENTS_FILE,
                JsonSerializableEvent.class).get();
        ClubBook clubBookFromFile = dataFromFile.toModelType();
        ClubBook typicalEventsClubBook = TypicalEvents.getTypicalClubBookWithEvents();
        assertEquals(clubBookFromFile, typicalEventsClubBook);
    }

    @Test
    public void toModelType_invalidEventFile_throwsIllegalValueException() throws Exception {
        JsonSerializableEvent dataFromFile = JsonUtil.readJsonFile(INVALID_EVENTS_FILE,
                JsonSerializableEvent.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEvents_throwsIllegalValueException() throws Exception {
        JsonSerializableEvent dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EVENT_FILE,
                JsonSerializableEvent.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableEvent.MESSAGE_DUPLICATE_EVENT,
                dataFromFile::toModelType);
    }
}
