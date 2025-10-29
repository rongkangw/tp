package seedu.club.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.club.testutil.TypicalEvents.getTypicalClubBookWithEvents;
import static seedu.club.testutil.TypicalIndexes.INDEX_FIRST_EVENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void execute_validIndexFullList_success() {
        Event eventToDisplay = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        model.setViewState(ViewState.EVENT);
        expectedModel.setViewState(ViewState.SINGLE_EVENT);
        String expectedMessage = String.format(
                DisplayEventCommand.MESSAGE_DISPLAY_EVENT_SUCCESS, eventToDisplay.getName());
        assertTrue(model.getFilteredEventList().contains(eventToDisplay));
        assertFalse(model.getFilteredEventList().isEmpty());
    }

}
