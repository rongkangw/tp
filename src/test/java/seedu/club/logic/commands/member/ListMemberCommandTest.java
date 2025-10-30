package seedu.club.logic.commands.member;

import static seedu.club.logic.commands.CommandTestUtil.showMemberAtIndex;
import static seedu.club.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.club.testutil.TypicalClubBook.getTypicalClubBook;

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
        CommandTestUtil.assertCommandSuccess(
                new ListMemberCommand(), model, ListMemberCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showMemberAtIndex(model, INDEX_FIRST_MEMBER);
        CommandTestUtil.assertCommandSuccess(
                new ListMemberCommand(), model, ListMemberCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyClubBook_showEmptyListMessage() {
        model = new ModelManager(new ClubBookBuilder().build(), new UserPrefs());
        expectedModel = new ModelManager(model.getClubBook(), new UserPrefs());
        CommandTestUtil.assertCommandSuccess(
                new ListMemberCommand(), model, ListMemberCommand.MESSAGE_EMPTY_LIST, expectedModel);
    }
}
