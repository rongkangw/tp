package seedu.club.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.club.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.club.testutil.TypicalClubBook.getTypicalEventOnlyClubBook;
import static seedu.club.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.club.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.club.commons.core.index.Index;
import seedu.club.logic.Messages;
import seedu.club.logic.commands.CommandTestUtil;
import seedu.club.model.Model;
import seedu.club.model.ModelManager;
import seedu.club.model.UserPrefs;
import seedu.club.model.ViewState;
import seedu.club.model.event.DateTime;
import seedu.club.model.event.Event;
import seedu.club.model.name.Name;
import seedu.club.testutil.EditEventDescriptorBuilder;
import seedu.club.testutil.EventBuilder;




public class EditEventCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalEventOnlyClubBook(), new UserPrefs());
        model.setViewState(ViewState.EVENT);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Event editedEvent = new EventBuilder().withName("Orientation Day")
                .withFrom("251025 1600")
                .withTo("251025 1800")
                .withDetail("Bring extra clothes")
                .withEventRoles("facilitator", "gamemaster")
                .build();
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        descriptor.setName(new Name("Orientation Day"));
        descriptor.setFrom(new DateTime("251025 1600"));
        descriptor.setTo(new DateTime("251025 1800"));
        descriptor.setDetails("Bring extra clothes");

        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        String expectedMessage = String.format(
                EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, Messages.format(editedEvent));

        Model expectedModel = new ModelManager(getTypicalEventOnlyClubBook(), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);
        expectedModel.setViewState(ViewState.EVENT);

        CommandTestUtil.assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_wrongViewState_failure() {
        model.setViewState(ViewState.MEMBER);
        Event editedEvent = new EventBuilder().withName("Orientation Day")
                .withFrom("251025 1600")
                .withTo("251025 1800")
                .withDetail("Bring extra clothes")
                .withEventRoles("facilitator", "gamemaster")
                .build();
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        descriptor.setName(new Name("Orientation Day"));
        descriptor.setFrom(new DateTime("251025 1600"));
        descriptor.setTo(new DateTime("251025 1800"));
        descriptor.setDetails("Bring extra clothes");

        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        Model expectedModel = new ModelManager(getTypicalEventOnlyClubBook(), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);
        expectedModel.setViewState(ViewState.EVENT);

        CommandTestUtil.assertCommandFailure(editEventCommand, model, Messages.MESSAGE_NOT_EVENT_STATE);
    }


    @Test
    public void execute_duplicateEventUnfilteredList_failure() {
        Event firstEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder(firstEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_SECOND_EVENT, descriptor);

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditEventCommand.EditEventDescriptor descriptor = new EditEventCommand.EditEventDescriptor();
        descriptor.setName(new Name("Orientation"));
        descriptor.setFrom(new DateTime("251025 1600"));
        descriptor.setTo(new DateTime("251025 1800"));
        descriptor.setDetails("Bring extra clothes");

        EditEventCommand editEventCommand = new EditEventCommand(index, descriptor);

        String expected = EditEventCommand.class.getCanonicalName()
                + "{index=" + index
                + ", editEventDescriptor=" + descriptor + "}";

        assertEquals(expected, editEventCommand.toString());

    }

    @Test
    public void equals() {
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withName("First Event").build();
        EditEventCommand editFirstCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        // same values -> returns true
        EditEventCommand.EditEventDescriptor copyDescriptor = new EditEventCommand.EditEventDescriptor(descriptor);
        EditEventCommand commandWithSameValues = new EditEventCommand(INDEX_FIRST_EVENT, copyDescriptor);
        assertTrue(editFirstCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(editFirstCommand.equals(editFirstCommand));

        // null -> returns false
        assertFalse(editFirstCommand.equals((Object) null));

        // different types -> returns false
        assertFalse(editFirstCommand.equals(1));

        // different index -> returns false
        EditEventCommand editSecondCommand = new EditEventCommand(INDEX_SECOND_EVENT, descriptor);
        assertFalse(editFirstCommand.equals(editSecondCommand));

        // different descriptor -> returns false
        EditEventCommand.EditEventDescriptor differentDescriptor = new EditEventDescriptorBuilder()
                .withName("Second Event").build();
        EditEventCommand commandWithDifferentDescriptor = new EditEventCommand(INDEX_FIRST_EVENT, differentDescriptor);
        assertFalse(editFirstCommand.equals(commandWithDifferentDescriptor));
    }

}
