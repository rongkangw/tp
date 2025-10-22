package seedu.club.model.role;

import java.util.Objects;

import seedu.club.model.event.Event;
import seedu.club.model.role.exceptions.DuplicateRoleAssignmentException;

/**
 * Represents the role of a member in a specific event for which that member is assigned to.
 */
public class EventRole extends Role {
    private Event assignedTo = null;

    /**
     * Constructs an {@code EventRole} with the specified role name and event assignment.
     *
     * @param roleName A valid role name.
     */
    public EventRole(String roleName) {
        super(roleName);
    }

    public void setAssignedTo(Event event) {
        // ensures assignedTo can only be set once
        if (assignedTo != null && assignedTo.equals(event)) {
            throw new DuplicateRoleAssignmentException();
        }

        assignedTo = event;
    }

    public Event getAssignedTo() {
        return assignedTo;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventRole)) {
            return false;
        }

        EventRole otherRole = (EventRole) other;
        return roleName.equals(otherRole.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }

    @Override
    public String toString() {
        return assignedTo == null
                ? '[' + "null" + ">" + roleName + ']'
                : '[' + assignedTo.getName().fullName + ">" + roleName + ']';
    }
}
