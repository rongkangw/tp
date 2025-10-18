package seedu.club.logic.commands;

import static seedu.club.logic.commands.CommandTestUtil.showMemberAtIndex;
import static seedu.club.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.club.testutil.TypicalMembers.getTypicalClubBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.club.model.Model;
import seedu.club.model.ModelManager;
import seedu.club.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListMemberCommand.
 */
public class ListMemberCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClubBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getClubBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandTestUtil.assertMemberCommandSuccess(
                new ListMemberCommand(), model, ListMemberCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showMemberAtIndex(model, INDEX_FIRST_MEMBER);
        CommandTestUtil.assertMemberCommandSuccess(
                new ListMemberCommand(), model, ListMemberCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
