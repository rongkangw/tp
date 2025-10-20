package seedu.club.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.club.commons.exceptions.IllegalValueException;
import seedu.club.model.role.EventRole;
import seedu.club.model.role.Role;

/**
 * Jackson-friendly version of {@link EventRole}.
 */
class JsonAdaptedEventRole {

    private final String roleName;

    /**
     * Constructs a {@code JsonAdaptedEventRole} with the given {@code roleName}.
     */
    @JsonCreator
    public JsonAdaptedEventRole(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Converts a given {@code Role} into this class for Jackson use.
     */
    public JsonAdaptedEventRole(EventRole source) {
        roleName = source.roleName;
    }

    @JsonValue
    public String getRoleName() {
        return roleName;
    }

    /**
     * Converts this Jackson-friendly adapted role object into the model's {@code Role} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted role.
     */
    public EventRole toModelType() throws IllegalValueException {
        if (!EventRole.isValidRoleName(roleName)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        }
        return new EventRole(roleName);
    }

}
