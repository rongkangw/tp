package seedu.club.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.club.logic.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.club.logic.Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX;
import static seedu.club.logic.Messages.MESSAGE_NOT_EVENT_STATE;
import static seedu.club.logic.Messages.MESSAGE_NOT_MEMBER_STATE;
import static seedu.club.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.club.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.club.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.club.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.club.testutil.Assert.assertThrows;
import static seedu.club.testutil.TypicalClubBook.AMY;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.club.logic.commands.CommandResult;
import seedu.club.logic.commands.event.ListEventCommand;
import seedu.club.logic.commands.exceptions.CommandException;
import seedu.club.logic.commands.member.AddMemberCommand;
import seedu.club.logic.commands.member.ListMemberCommand;
import seedu.club.logic.parser.exceptions.ParseException;
import seedu.club.model.Model;
import seedu.club.model.ModelManager;
import seedu.club.model.ReadOnlyClubBook;
import seedu.club.model.UserPrefs;
import seedu.club.model.ViewState;
import seedu.club.model.member.Member;
import seedu.club.storage.JsonClubBookStorage;
import seedu.club.storage.JsonUserPrefsStorage;
import seedu.club.storage.StorageManager;
import seedu.club.testutil.MemberBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private final Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonClubBookStorage clubBookStorage =
                new JsonClubBookStorage(temporaryFolder.resolve("clubbook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(clubBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteMemberCommand = "deleteMember 9";
        String deleteEventCommand = "deleteEvent 9";
        assertCommandException(deleteMemberCommand, MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        assertCommandException(deleteEventCommand, MESSAGE_NOT_EVENT_STATE);

        logic.setViewState(ViewState.EVENT);
        assertCommandException(deleteMemberCommand, MESSAGE_NOT_MEMBER_STATE);
        assertCommandException(deleteEventCommand, MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        logic.setViewState(ViewState.MEMBER);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listMemberCommand = ListMemberCommand.COMMAND_WORD;
        String listEventCommand = ListEventCommand.COMMAND_WORD;
        assertCommandSuccess(listMemberCommand, ListMemberCommand.MESSAGE_EMPTY_LIST, model);
        assertCommandSuccess(listEventCommand, ListEventCommand.MESSAGE_EMPTY_LIST, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_IO_EXCEPTION, String.format(
                LogicManager.FILE_OPS_ERROR_FORMAT, DUMMY_IO_EXCEPTION.getMessage()));
    }

    @Test
    public void execute_storageThrowsAdException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_AD_EXCEPTION, String.format(
                LogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT, DUMMY_AD_EXCEPTION.getMessage()));
    }

    @Test
    public void getFilteredMemberList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredMemberList().remove(0));
    }

    @Test
    public void getFilteredEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredEventList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getClubBook(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * Tests the Logic component's handling of an {@code IOException} thrown by the Storage component.
     *
     * @param e the exception to be thrown by the Storage component
     * @param expectedMessage the message expected inside exception thrown by the Logic component
     */
    private void assertCommandFailureForExceptionFromStorage(IOException e, String expectedMessage) {
        Path prefPath = temporaryFolder.resolve("ExceptionUserPrefs.json");

        // Inject LogicManager with an ClubBookStorage that throws the IOException e when saving
        JsonClubBookStorage clubBookStorage = new JsonClubBookStorage(prefPath) {
            @Override
            public void saveClubBook(ReadOnlyClubBook clubBook, Path filePath)
                    throws IOException {
                throw e;
            }
        };

        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(clubBookStorage, userPrefsStorage);

        logic = new LogicManager(model, storage);

        // Triggers the saveClubBook method by executing an add command
        String addCommand = AddMemberCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY;
        Member expectedMember = new MemberBuilder(AMY).withMemberRoles().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addMember(expectedMember);
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }
}
