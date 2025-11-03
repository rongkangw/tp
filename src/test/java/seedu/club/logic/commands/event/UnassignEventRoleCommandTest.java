package seedu.club.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.club.logic.Messages.MESSAGE_EVENTROLE_NAME_NOT_EXIST;
import static seedu.club.logic.Messages.MESSAGE_EVENT_NAME_NOT_EXIST;
import static seedu.club.logic.Messages.MESSAGE_MEMBER_NAME_NOT_EXIST;
import static seedu.club.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.club.logic.commands.event.UnassignEventRoleCommand.MESSAGE_SUCCESS_EVENT_ROLE;
import static seedu.club.testutil.TypicalClubBook.getTypicalClubBook;
import static seedu.club.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.club.logic.commands.exceptions.CommandException;
import seedu.club.model.Model;
import seedu.club.model.ModelManager;
import seedu.club.model.UserPrefs;
import seedu.club.model.name.Name;
import seedu.club.model.role.EventRole;



public class UnassignEventRoleCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClubBook(), new UserPrefs());
    }

    @Test
    public void execute_eventDoesNotExist_throwsCommandException() {
        Name memberName = new Name("John");
        Name eventName = new Name("Dinner");
        UnassignEventRoleCommand unassignEventRoleCommand = new UnassignEventRoleCommand(eventName, memberName,
                Collections.emptySet());
        assertCommandFailure(unassignEventRoleCommand, model,
                String.format(MESSAGE_EVENT_NAME_NOT_EXIST, eventName));
    }

    @Test
    public void execute_memberDoesNotExist_throwsCommandException() {
        Name memberName = new Name("Johnnyn");
        Name eventName = new Name("Orientation");
        UnassignEventRoleCommand unassignEventRoleCommand = new UnassignEventRoleCommand(eventName, memberName,
                Collections.emptySet());
        assertCommandFailure(unassignEventRoleCommand, model,
                String.format(MESSAGE_MEMBER_NAME_NOT_EXIST, memberName));
    }

    @Test
    public void execute_eventRoleDoesNotExist_throwsCommandException() {
        Name memberName = new Name("Alice Pauline");
        Name eventName = new Name("Orientation");
        Set<EventRole> roles = Set.of(new EventRole("Publicity", eventName));
        UnassignEventRoleCommand unassignEventCommand = new UnassignEventRoleCommand(eventName, memberName, roles);
        assertCommandFailure(unassignEventCommand, model,
                String.format(MESSAGE_EVENTROLE_NAME_NOT_EXIST, eventName, roles));
    }

    @Test
    public void equals_sameNameEventAndRoles_success() {
        Name memberName = new Name("Alice Pauline");
        Name eventName = new Name("Orientation");
        Set<EventRole> eventRoles = Set.of(new EventRole("Facilitator", eventName),
                new EventRole("SafetyOfficer", eventName));
        UnassignEventRoleCommand unassignEventRoleCommandOne = new UnassignEventRoleCommand(eventName, memberName,
                eventRoles);
        UnassignEventRoleCommand unassignEventRoleCommandTwo = new UnassignEventRoleCommand(eventName, memberName,
                eventRoles);
        assert(unassignEventRoleCommandOne.equals(unassignEventRoleCommandTwo));
    }

    @Test
    public void execute_unassignEventWithEventRoles_success() throws CommandException {
        EventRole roleToDelete = new EventRole("facilitator", new Name("Orientation"));
        model.getFilteredMemberList()
                .get(INDEX_FIRST_MEMBER.getZeroBased())
                .addEventRoles(Set.of(roleToDelete));

        Name eventName = new Name("Orientation");
        Name memberName = new Name("Alice Pauline");

        UnassignEventRoleCommand unassignEventRoleCommand = new UnassignEventRoleCommand(eventName, memberName,
                Set.of(roleToDelete));
        ModelManager expectedModel = new ModelManager(model.getClubBook(), new UserPrefs());
        String result = unassignEventRoleCommand.execute(model).getFeedbackToUser();
        expectedModel.getClubBook().getMemberList()
                .get(INDEX_FIRST_MEMBER.getZeroBased())
                .removeEventRole(Set.of(roleToDelete));
        expectedModel.updateFilteredMemberList(m -> true);
        expectedModel.updateFilteredEventList(e -> true);
        model.updateFilteredMemberList(m -> true);
        model.updateFilteredEventList(e -> true);
        assertEquals(MESSAGE_SUCCESS_EVENT_ROLE, result);
        assertEquals(model, expectedModel);
    }
}
