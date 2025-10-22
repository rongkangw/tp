package seedu.club.model.member;

import static seedu.club.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.club.commons.util.ToStringBuilder;
import seedu.club.model.name.Name;
import seedu.club.model.name.NamedEntity;
import seedu.club.model.role.EventRole;
import seedu.club.model.role.MemberRole;

/**
 * Represents a Member in the club book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Member extends NamedEntity {

    // Identity fields
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Set<MemberRole> roles = new HashSet<>();
    private final Set<EventRole> eventRoles = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Member(Name name, Phone phone, Email email, Set<MemberRole> roles) {
        super(name);
        requireAllNonNull(phone, email, roles);
        this.phone = phone;
        this.email = email;
        this.roles.addAll(roles);
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    /**
     * Returns an immutable role set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<MemberRole> getRoles() {
        return Collections.unmodifiableSet(roles);
    }


    public void addEventRoles(Set<EventRole> roles) {
        eventRoles.addAll(roles);
    }

    /**
     * Returns true if both members have the same name.
     * This defines a weaker notion of equality between two members.
     */
    public boolean isSameMember(Member otherMember) {
        if (otherMember == this) {
            return true;
        }

        return otherMember != null
                && otherMember.getName().equals(getName());
    }

    /**
     * Returns true if both members have the same identity and data fields.
     * This defines a stronger notion of equality between two members.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Member)) {
            return false;
        }

        Member otherMember = (Member) other;
        return name.equals(otherMember.name)
                && phone.equals(otherMember.phone)
                && email.equals(otherMember.email)
                && roles.equals(otherMember.roles);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, roles);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("roles", roles)
                .toString();
    }

}
