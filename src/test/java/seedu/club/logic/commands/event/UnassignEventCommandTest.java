package seedu.club.logic.commands.event;

import static seedu.club.logic.Messages.MESSAGE_EVENT_NAME_NOT_EXIST;
import static seedu.club.logic.Messages.MESSAGE_MEMBER_NAME_NOT_EXIST;
import static seedu.club.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.club.testutil.TypicalEvents.getTypicalClubBookWithEvents;
import static seedu.club.testutil.TypicalEventsWithEventRoles.getTypicalClubBookWithEventRoles;

import org.junit.jupiter.api.Test;
import seedu.club.model.Model;
import seedu.club.model.ModelManager;
import seedu.club.model.UserPrefs;
import seedu.club.model.name.Name;
import seedu.club.model.role.EventRole;

import java.util.Collections;
import java.util.Set;


public class UnassignEventCommandTest {
    private final Model model = new ModelManager(getTypicalClubBookWithEventRoles(), new UserPrefs());

    @Test
    public void execute_EventDoesNotExist_throwsCommandException() {
        Name memberName = new Name("John");
        Name eventName = new Name("Dinner");
        UnassignEventCommand unassignEventCommand = new UnassignEventCommand(eventName, memberName,
                Collections.emptySet());
        assertCommandFailure(unassignEventCommand, model,
                String.format(MESSAGE_EVENT_NAME_NOT_EXIST, eventName));
    }

    @Test
    public void execute_MemberDoesNotExist_throwsCommandException() {
        Name memberName = new Name("Jane");
        Name eventName = new Name("Orientation");
        UnassignEventCommand unassignEventCommand = new UnassignEventCommand(eventName, memberName,
                Collections.emptySet());
        assertCommandFailure(unassignEventCommand, model,
                String.format(MESSAGE_MEMBER_NAME_NOT_EXIST, memberName));
    }

    @Test
    public void equals_thisObject_success() {
        Name memberName = new Name("Jane");
        Name eventName = new Name("Orientation");
        UnassignEventCommand unassignEventCommand = new UnassignEventCommand(eventName, memberName,
                Collections.emptySet());
        assert(unassignEventCommand.equals(unassignEventCommand));
    }

    @Test
    public void equals_differentType_false() {
        Name memberName = new Name("Jane");
        Name eventName = new Name("Orientation");
        UnassignEventCommand unassignEventCommand = new UnassignEventCommand(eventName, memberName,
                Collections.emptySet());
        assert(!unassignEventCommand.equals("Event"));
    }

    @Test
    public void equals_sameNameEventAndRoles_Success() {
        Name memberName = new Name("Jane");
        Name eventName = new Name("Orientation");
        Set<EventRole> eventRoles = Set.of(new EventRole("Facilitator"),
                new EventRole("SafetyOfficer"));
        UnassignEventCommand unassignEventCommandOne = new UnassignEventCommand(eventName, memberName,
                eventRoles);
        UnassignEventCommand unassignEventCommandTwo = new UnassignEventCommand(eventName, memberName,
                eventRoles);
        assert(unassignEventCommandOne.equals(unassignEventCommandTwo));
    }


    @Test
    public void execute_UnassignEventWithEventRoles_success() {

    }



}
