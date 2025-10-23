package seedu.club.logic.commands.event;

import org.junit.jupiter.api.Test;
import seedu.club.logic.Messages;
import seedu.club.logic.commands.exceptions.CommandException;
import seedu.club.testutil.Assert;
import seedu.club.model.Model;
import seedu.club.model.ModelManager;
import seedu.club.model.UserPrefs;
import seedu.club.model.name.Name;

import java.util.Collections;

import static seedu.club.logic.Messages.MESSAGE_EVENT_NAME_NOT_EXIST;
import static seedu.club.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.club.testutil.TypicalEvents.getTypicalClubBookWithEvents;

public class UnassignEventCommandTest {
    private final Model model = new ModelManager(getTypicalClubBookWithEvents(), new UserPrefs());

    /*@Test
    public void execute_EventDoesNotExist_throwsCommandException() {
        Name memberName = new Name("John");
        Name eventName = new Name("Dinner");
        UnassignEventCommand unassignEventCommand = new UnassignEventCommand(memberName, eventName,
                Collections.emptySet());
        assertCommandFailure(unassignEventCommand, model,
                String.format(MESSAGE_EVENT_NAME_NOT_EXIST, eventName));
    }*/

}
