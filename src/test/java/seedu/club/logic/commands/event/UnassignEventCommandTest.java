package seedu.club.logic.commands.event;

import static seedu.club.testutil.TypicalEvents.getTypicalClubBookWithEvents;

import seedu.club.model.Model;
import seedu.club.model.ModelManager;
import seedu.club.model.UserPrefs;



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
