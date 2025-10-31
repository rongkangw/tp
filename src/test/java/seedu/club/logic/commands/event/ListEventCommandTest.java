package seedu.club.logic.commands.event;

import static seedu.club.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.club.testutil.TypicalClubBook.getTypicalEventOnlyClubBook;
import static seedu.club.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.club.logic.commands.CommandTestUtil;
import seedu.club.model.Model;
import seedu.club.model.ModelManager;
import seedu.club.model.UserPrefs;
import seedu.club.testutil.ClubBookBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code ListMemberCommand}.
 */
public class ListEventCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalEventOnlyClubBook(), new UserPrefs());
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

    @Test
    public void execute_emptyClubBook_showsEmptyListMessage() {
        model = new ModelManager(new ClubBookBuilder().build(), new UserPrefs());
        expectedModel = new ModelManager(model.getClubBook(), new UserPrefs());
        CommandTestUtil.assertCommandSuccess(
                new ListEventCommand(), model, ListEventCommand.MESSAGE_EMPTY_LIST, expectedModel);
    }
}
