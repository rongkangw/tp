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
    public static final String MESSAGE_SUCCESS = "The following";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the event identified by the index number used in the displayed events list.\n"
            + "Parameters : INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";


    private final Name eventName;
    private final Name memberName;

    //private final Event event;
    //private final Member member;
    private final Set<Role> roles;

    public UnassignEventCommand(Name event, Name member, Set<Role> roles) {
        this.eventName = event;
        this.memberName = member;
        this.roles = roles;
    }



    @Override
    public CommandResult execute(Model model) {



        //to be implemented
        if (this.roles.isEmpty()) {
            return new CommandResult(MESSAGE_USAGE);
        }



        return new CommandResult(MESSAGE_SUCCESS, false, false, true);

    }


}
