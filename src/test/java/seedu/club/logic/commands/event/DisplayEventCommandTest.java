package seedu.club.logic.commands.event;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.club.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.club.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.club.testutil.TypicalEvents.getTypicalClubBookWithEvents;
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
import seedu.club.model.event.Event;

public class DisplayEventCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        this.model = new ModelManager(getTypicalClubBookWithEvents(), new UserPrefs());
        this.expectedModel = new ModelManager(model.getClubBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Event eventToBeDisplayed = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        model.setViewState(ViewState.EVENT);
        expectedModel.setViewState(ViewState.SINGLE_EVENT);
        expectedModel.updateFilteredEventList(e -> e.equals(eventToBeDisplayed));
        expectedModel.updateFilteredMemberList(m -> eventToBeDisplayed.getRoster().contains(m));

        String expectedMessage = String.format(DisplayEventCommand.MESSAGE_DISPLAY_EVENT_SUCCESS,
                eventToBeDisplayed.getName());

        CommandTestUtil.assertCommandSuccess(
                new DisplayEventCommand(INDEX_FIRST_EVENT),
                model,
                expectedMessage,
                expectedModel
        );
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        DisplayEventCommand displayEventCommand = new DisplayEventCommand(outOfBoundIndex);

        assertCommandFailure(displayEventCommand, model, Messages.MESSAGE_NOT_EVENT_STATE);

        model.setViewState(ViewState.EVENT);
        assertCommandFailure(displayEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventToDisplay = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        model.setViewState(ViewState.EVENT);
        expectedModel.setViewState(ViewState.SINGLE_EVENT);
        expectedModel.updateFilteredEventList(e -> e.equals(eventToDisplay));
        expectedModel.updateFilteredMemberList(m -> eventToDisplay.getRoster().contains(m));

        String expectedMessage = String.format(DisplayEventCommand.MESSAGE_DISPLAY_EVENT_SUCCESS,
                eventToDisplay.getName());

        CommandTestUtil.assertCommandSuccess(
                new DisplayEventCommand(INDEX_FIRST_EVENT),
                model,
                expectedMessage,
                expectedModel
        );
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        //ensures that outOfBoundIndex is still in bounds of club book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getClubBook().getEventList().size());

        DisplayEventCommand displayEventCommand = new DisplayEventCommand(outOfBoundIndex);

        model.setViewState(ViewState.EVENT);
        assertCommandFailure(displayEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DisplayEventCommand displayEventFirstCommand = new DisplayEventCommand(INDEX_FIRST_EVENT);
        DisplayEventCommand displayEventSecondCommand = new DisplayEventCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(displayEventFirstCommand.equals(displayEventFirstCommand));

        // same values -> returns true
        DisplayEventCommand displayEventFirstCommandCopy = new DisplayEventCommand(INDEX_FIRST_EVENT);
        assertTrue(displayEventFirstCommand.equals(displayEventFirstCommandCopy));

        // different types -> returns false
        assertFalse(displayEventFirstCommand.equals(1));

        // null -> returns false
        assertFalse(displayEventFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(displayEventFirstCommand.equals(displayEventSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DisplayEventCommand displayEventCommand = new DisplayEventCommand(targetIndex);
        String expected = DisplayEventCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, displayEventCommand.toString());
    }
}
