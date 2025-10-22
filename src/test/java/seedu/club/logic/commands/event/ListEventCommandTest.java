package seedu.club.logic.commands.event;

import static seedu.club.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.club.testutil.TypicalEvents.getTypicalClubBookWithEvents;
import static seedu.club.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.club.logic.commands.CommandTestUtil;
import seedu.club.model.Model;
import seedu.club.model.ModelManager;
import seedu.club.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code ListMemberCommand}.
 */
public class ListEventCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClubBookWithEvents(), new UserPrefs());
        expectedModel = new ModelManager(model.getClubBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandTestUtil.assertCommandSuccess(
                new ListEventCommand(), model, ListEventCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showEventAtIndex(model, INDEX_FIRST_MEMBER);
        CommandTestUtil.assertCommandSuccess(
                new ListEventCommand(), model, ListEventCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
