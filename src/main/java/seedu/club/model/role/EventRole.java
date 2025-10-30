package seedu.club.model.role;

import java.util.Objects;

import seedu.club.model.name.Name;

/**
 * Represents the role of a member in a specific event for which that member is assigned to.
 */
public class EventRole extends Role {
    private Name assignedTo;
    private boolean isUnassigned = false;

    /**
     * Constructs an {@code EventRole} with the specified role name and event assignment.
     *
     * @param roleName A valid role name.
     */
    public EventRole(String roleName, Name assignedTo) {
        super(roleName);
        this.assignedTo = assignedTo;
    }

    /**
     * Factory method for empty {@code EventRole}
     *
     * @param eventName The name of the event to be assigned to
     */
    public static EventRole unassigned(Name eventName) {
        EventRole role = new EventRole("Unassigned", eventName);
        role.isUnassigned = true;
        return role;
    }

    public Name getAssignedTo() {
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
        return roleName.equals(otherRole.roleName)
                && assignedTo.equals(otherRole.assignedTo)
                && isUnassigned == otherRole.isUnassigned;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, assignedTo, isUnassigned);
    }

    @Override
    public String toString() {
        if (isUnassigned) {
            return assignedTo.toString(); // Just the event name since no role name
        }
        return assignedTo + ">" + roleName;
    }
}
