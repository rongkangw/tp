package seedu.club.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.club.commons.exceptions.IllegalValueException;
import seedu.club.model.role.MemberRole;

/**
 * Jackson-friendly version of {@link MemberRole}.
 */
class JsonAdaptedMemberRole {

    private final String roleName;

    /**
     * Constructs a {@code JsonAdaptedMemberRole} with the given {@code roleName}.
     */
    @JsonCreator
    public JsonAdaptedMemberRole(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Converts a given {@code MemberRole} into this class for Jackson use.
     */
    public JsonAdaptedMemberRole(MemberRole source) {
        roleName = source.roleName;
    }

    @JsonValue
    public String getRoleName() {
        return roleName;
    }

    /**
     * Converts this Jackson-friendly adapted member role object into the model's {@code MemberRole} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted member role.
     */
    public MemberRole toModelType() throws IllegalValueException {
        if (!MemberRole.isValidRoleName(roleName)) {
            throw new IllegalValueException(MemberRole.MESSAGE_CONSTRAINTS);
        }
        return new MemberRole(roleName);
    }

}
