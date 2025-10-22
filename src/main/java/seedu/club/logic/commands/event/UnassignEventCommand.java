package seedu.club.logic.commands.event;

import static seedu.club.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.club.logic.parser.event.UnassignEventCommandParser.PREFIX_EVENT;
import static seedu.club.logic.parser.event.UnassignEventCommandParser.PREFIX_MEMBER;

import java.util.Set;

import seedu.club.logic.commands.Command;
import seedu.club.logic.commands.CommandResult;
import seedu.club.model.Model;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;
import seedu.club.model.name.Name;
import seedu.club.model.role.EventRole;





/**
 * Removes assignation between a member and an event or event role.
 */
public class UnassignEventCommand extends Command {
    public static final String COMMAND_WORD = "unassignEvent";
    public static final String MESSAGE_SUCCESS_EVENT = "The event has been unassigned from the member successfully.";
    public static final String MESSAGE_SUCCESS_EVENT_ROLE =
            "The event role has been unassigned from the member successfully.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the event roles from the specified member\n"
            + "If no roles are specified, the member will be removed from the event\n"
            + "Parameters: "
            + PREFIX_MEMBER + "MEMBER "
            + PREFIX_EVENT + "EVENT "
            + "[" + PREFIX_ROLE + "EVENT ROLE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT + "Meeting "
            + PREFIX_MEMBER + "John "
            + PREFIX_ROLE + "Facilitator";
    public static final String MESSAGE_NAME_DOES_NOT_EXIST = "The event or member does not exist.\n";

    private final Name eventName;
    private final Name memberName;

    private final Set<EventRole> roles;

    private boolean hasRoles = true;

    /**
     * Creates an UnassignEventCommand for the specified event, member, and roles.
     */
    public UnassignEventCommand(Name event, Name member, Set<EventRole> roles) {
        this.eventName = event;
        this.memberName = member;
        this.roles = roles;
        if (this.roles.isEmpty()) {
            hasRoles = false;
        }
    }


    @Override
    public CommandResult execute(Model model) {
        int eventIndex = model.eventNameIndex(eventName);
        int memberIndex = model.memberNameIndex(memberName);
        if (eventIndex == -1 || memberIndex == -1) {
            return new CommandResult(String.format(MESSAGE_NAME_DOES_NOT_EXIST),
                    false, false, false);
        }
        Member member = model.getFilteredMemberList().get(memberIndex);
        Event event = model.getFilteredEventList().get(eventIndex);

        if (hasRoles) {
            return executeWithMemberRole(member, roles);
        }
        return executeNoMemberRole(member, event);

    }

    private CommandResult executeWithMemberRole(Member member, Set<EventRole> eventRoles) {
        member.removeEventRole(eventRoles);
        return new CommandResult(String.format(MESSAGE_SUCCESS_EVENT_ROLE),
                false, false, true);
    }

    private CommandResult executeNoMemberRole(Member member, Event event) {
        event.removeMemberFromRoster(member);
        member.removeEvent(event);
        return new CommandResult(String.format(MESSAGE_SUCCESS_EVENT),
                false, false, true);

    }


}
