package seedu.club.model.role;

import java.util.Objects;

import seedu.club.model.name.Name;

/**
 * Represents the role of a member in a specific event for which that member is assigned to.
 */
public class EventRole extends Role {
    private Name assignedTo;

    /**
     * Constructs an {@code EventRole} with the specified role name and event assignment.
     *
     * @param roleName A valid role name.
     */
    public EventRole(String roleName, Name assignedTo) {
        super(roleName);
        this.assignedTo = assignedTo;
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
                && assignedTo.equals(otherRole.assignedTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, assignedTo);
    }

    @Override
    public String toString() {
        return assignedTo + ">" + roleName;
    }
}
