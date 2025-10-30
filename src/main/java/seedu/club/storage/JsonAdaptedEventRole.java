package seedu.club.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.club.commons.exceptions.IllegalValueException;
import seedu.club.model.name.Name;
import seedu.club.model.role.EventRole;

/**
 * Jackson-friendly version of {@link EventRole}.
 */
class JsonAdaptedEventRole {

    private final String roleName;
    private final String eventName;

    /**
     * Constructs a {@code JsonAdaptedEventRole} with the given {@code roleName}.
     */
    @JsonCreator
    public JsonAdaptedEventRole(@JsonProperty("roleName") String roleName,
                                @JsonProperty("eventName") String eventName) {
        this.roleName = roleName;
        this.eventName = eventName;
    }

    /**
     * Converts a given {@code EventRole} into this class for Jackson use.
     */
    public JsonAdaptedEventRole(EventRole source) {
        roleName = source.roleName;
        eventName = source.getAssignedTo().toString();
    }

    /**
     * Converts this Jackson-friendly adapted event role object into the model's {@code EventRole} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event role.
     */
    public EventRole toModelType() throws IllegalValueException {
        if (!EventRole.isValidRoleName(roleName)) {
            throw new IllegalValueException(EventRole.MESSAGE_CONSTRAINTS);
        }
        EventRole eventRole = new EventRole(roleName, new Name(eventName));
        return eventRole;
    }
}
