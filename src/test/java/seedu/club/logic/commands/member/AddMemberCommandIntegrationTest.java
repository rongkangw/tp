package seedu.club.logic.commands.member;

import static seedu.club.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.club.testutil.TypicalMembers.getTypicalClubBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.club.logic.Messages;
import seedu.club.logic.commands.CommandTestUtil;
import seedu.club.model.Model;
import seedu.club.model.ModelManager;
import seedu.club.model.UserPrefs;
import seedu.club.model.member.Member;
import seedu.club.testutil.MemberBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddMemberCommand}.
 */
public class AddMemberCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClubBook(), new UserPrefs());
    }

    @Test
    public void execute_newMember_success() {
        Member validMember = new MemberBuilder().build();

        Model expectedModel = new ModelManager(model.getClubBook(), new UserPrefs());
        expectedModel.addMember(validMember);

        CommandTestUtil.assertMemberCommandSuccess(new AddMemberCommand(validMember), model,
                String.format(AddMemberCommand.MESSAGE_SUCCESS, Messages.format(validMember)),
                expectedModel);
    }

    @Test
    public void execute_duplicateMember_throwsCommandException() {
        Member memberInList = model.getClubBook().getMemberList().get(0);
        assertCommandFailure(new AddMemberCommand(memberInList), model,
                AddMemberCommand.MESSAGE_DUPLICATE_MEMBER);
    }

}
