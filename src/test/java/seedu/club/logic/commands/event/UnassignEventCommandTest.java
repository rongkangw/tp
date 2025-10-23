package seedu.club.logic.commands.event;

import static seedu.club.logic.Messages.MESSAGE_EVENT_NAME_NOT_EXIST;
import static seedu.club.logic.Messages.MESSAGE_MEMBER_NAME_NOT_EXIST;
import static seedu.club.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.club.testutil.TypicalEventsWithEventRoles.getTypicalClubBookWithEventRoles;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.club.model.Model;
import seedu.club.model.ModelManager;
import seedu.club.model.UserPrefs;
import seedu.club.model.name.Name;
import seedu.club.model.role.EventRole;


public class UnassignEventCommandTest {
    private final Model model = new ModelManager(getTypicalClubBookWithEventRoles(), new UserPrefs());

    @Test
    public void execute_eventDoesNotExist_throwsCommandException() {
        Name memberName = new Name("John");
        Name eventName = new Name("Dinner");
        UnassignEventCommand unassignEventCommand = new UnassignEventCommand(eventName, memberName,
                Collections.emptySet());
        assertCommandFailure(unassignEventCommand, model,
                String.format(MESSAGE_EVENT_NAME_NOT_EXIST, eventName));
    }

    @Test
    public void execute_memberDoesNotExist_throwsCommandException() {
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
    public void equals_sameNameEventAndRoles_success() {
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


    /* @Test
    public void execute_UnassignEventWithEventRoles_success() {
        EventRole roleToDelete = model
                .getFilteredMemberList()
                .get(INDEX_FIRST_MEMBER.getZeroBased())
                .getEventRoles()
                .iterator().next();
        Name eventName = new Name("Orientation");
        Name memberName = new Name("John");
        UnassignEventCommand unassignEventCommand = new UnassignEventCommand(eventName, memberName,
                Set.of(roleToDelete));
        String expectedMessage = String.format(MESSAGE_SUCCESS_EVENT);
        ModelManager expectedModel = new ModelManager(model.getClubBook(), new UserPrefs());
        expectedModel.getFilteredMemberList()
        .get(INDEX_FIRST_MEMBER.getZeroBased()).removeEventRole(Set.of(roleToDelete));
        CommandTestUtil.assertCommandSuccess(unassignEventCommand, model, expectedMessage, expectedModel);
    }*/
    //requires assignEvent in memberBuilder



}
