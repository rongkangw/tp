package seedu.club.model.role.exceptions;

/**
 * Signals that the operation will result in an EventRole being assigned to more than one event.
 * (EventRole assignments are considered duplicated if {@code assignedTo} is already not null)
 */
public class DuplicateRoleAssignmentException extends RuntimeException {
    public DuplicateRoleAssignmentException() {
        super("Operation would result in duplicate role assignment");
    }
}
