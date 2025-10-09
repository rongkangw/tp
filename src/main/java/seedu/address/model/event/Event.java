package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.member.Name;
import seedu.address.model.tag.Tag;

/**
 * Represents an Event in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {

    // Identity fields
    private final Name name; // Imported from member.Name
    private final String from; // MVP: Store as string for now
    private final String to; // MVP: Store as string for now
    private final String details;

    // Data fields
    private final Set<Tag> roles = new HashSet<>(); // MVP: No current roles, event roles done in future iterations

    /**
     * Every field must be present and not null.
     */
    public Event(Name name, String from, String to, String details, Set<Tag> roles) {
        requireAllNonNull(name, from, to, details, roles);
        this.name = name;
        this.from = from;
        this.to = to;
        this.details = details;
        this.roles.addAll(roles);
    }

    public Name getName() {
        return name;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDetails() {
        return details;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    /**
     * Returns true if both events have the same name.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getName().equals(getName());
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
        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return name.equals(otherEvent.name)
                && from.equals(otherEvent.from)
                && to.equals(otherEvent.to)
                && details.equals(otherEvent.details)
                && roles.equals(otherEvent.roles);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, from, to, details, roles);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", from)
                .add("email", to)
                .add("address", details)
                .add("tags", roles)
                .toString();
    }

}
