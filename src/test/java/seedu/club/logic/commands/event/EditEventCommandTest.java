package seedu.club.logic.commands.event;

import org.junit.jupiter.api.Test;
import seedu.club.logic.Messages;
import seedu.club.logic.commands.CommandTestUtil;
import seedu.club.logic.commands.member.EditMemberCommand;
import seedu.club.model.ClubBook;
import seedu.club.model.Model;
import seedu.club.model.ModelManager;
import seedu.club.model.UserPrefs;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;
import seedu.club.testutil.EditMemberDescriptorBuilder;
import seedu.club.testutil.EventBuilder;
import seedu.club.testutil.MemberBuilder;

import static seedu.club.testutil.TypicalEvents.getTypicalClubBookWithEvents;
import static seedu.club.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;

public class EditEventCommandTest {
    private final Model model = new ModelManager(getTypicalClubBookWithEvents(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Event editedEvent = new EventBuilder().build();
        EditMemberCommand.EditMemberDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EditMemberCommand editMemberCommand = new EditMemberCommand(INDEX_FIRST_MEMBER, descriptor);

        String expectedMessage = String.format(
                EditMemberCommand.MESSAGE_EDIT_MEMBER_SUCCESS, Messages.format(editedMember));

        Model expectedModel = new ModelManager(new ClubBook(model.getClubBook()), new UserPrefs());
        expectedModel.setMember(model.getFilteredMemberList().get(0), editedMember);

        CommandTestUtil.assertCommandSuccess(editMemberCommand, model, expectedMessage, expectedModel);
    }

}
