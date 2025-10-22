package seedu.club.logic.commands.event;

import seedu.club.logic.commands.Command;
import seedu.club.logic.commands.CommandResult;
import seedu.club.model.Model;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;
import seedu.club.model.name.Name;
import seedu.club.model.role.Role;

import java.util.Set;

import static java.util.Objects.requireNonNull;

public class UnassignEventCommand extends Command {
    public static final String COMMAND_WORD = "unassignEvent";
    public static final String MESSAGE_SUCCESS = "The event has been unassigned from the member successfully.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the event identified by the index number used in the displayed events list.\n"
            + "Parameters : INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_NAME_DOES_NOT_EXIST = "The event or member does not exist.\n";


    private final Name eventName;
    private final Name memberName;

    private Event event;
    private Member member;
    private final Set<Role> roles;

    private boolean hasRoles = true;

    public UnassignEventCommand(Name event, Name member, Set<Role> roles) {
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
        if (model.eventNameIndex(eventName) == -1 || model.memberNameIndex(memberName) == -1) {
            return new CommandResult(String.format(MESSAGE_NAME_DOES_NOT_EXIST),
                    false, false, false);
        }
        member = model.getFilteredMemberList().get(memberIndex);
        event = model.getFilteredEventList().get(eventIndex);

        //to be implemented
        if (hasRoles) {
            return new CommandResult(String.format(MESSAGE_SUCCESS),
                    false, false, true);
        }
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);

    }

    private CommandResult executeNoMemberRole(Member member, Event event) {

    }


}
