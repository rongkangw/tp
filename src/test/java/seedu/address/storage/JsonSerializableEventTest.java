package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalEvents;

public class JsonSerializableEventTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableEventTest");
    private static final Path TYPICAL_EVENTS_FILE = TEST_DATA_FOLDER.resolve("typicalEvents.json");
    private static final Path INVALID_EVENTS_FILE = TEST_DATA_FOLDER.resolve("invalidEvent.json");
    private static final Path DUPLICATE_EVENT_FILE = TEST_DATA_FOLDER.resolve("duplicateEvents.json");

    @Test
    public void toModelType_typicalEventsFile_success() throws Exception {
        JsonSerializableEvent dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENTS_FILE,
                JsonSerializableEvent.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalEventsAddressBook = TypicalEvents.getTypicalAddressBookWithEvents();
        assertEquals(addressBookFromFile, typicalEventsAddressBook);
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
