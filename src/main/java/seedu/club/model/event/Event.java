package seedu.club.model.event;

import static seedu.club.commons.util.AppUtil.checkArgument;
import static seedu.club.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.club.commons.util.ToStringBuilder;
import seedu.club.model.member.Member;
import seedu.club.model.name.Name;
import seedu.club.model.name.NamedEntity;
import seedu.club.model.role.EventRole;

/**
 * Represents an Event in the club book.
 * Guarantees: detail is present and not null, field values are validated, immutable.
 */
public class Event extends NamedEntity {

    // DateTime constraints
    public static final String MESSAGE_CONSTRAINTS =
            "Starting date/time should be before ending date/time";

    // Identity fields
    private final DateTime from;
    private final DateTime to;
    private final String detail;

    // Data fields
    private final Set<EventRole> roles = new HashSet<>();
    private final Set<Member> roster = new HashSet<>();

    /**
     * Creates an Event containing no participating members.
     * Every field must be present and not null.
     */
    public Event(Name name, DateTime from, DateTime to,
                 String detail, Set<EventRole> roles) {
        super(name);

        requireAllNonNull(from, to, detail, roles);
        // Starting date/time must be before ending date/time
        checkArgument(from.isBefore(to), MESSAGE_CONSTRAINTS);

        this.from = from;
        this.to = to;
        this.detail = detail;
        this.roles.addAll(roles);
    }

    /**
     * Creates an Event with the given roster.
     * Every field must be present and not null.
     */
    public Event(Name name, DateTime from, DateTime to,
                 String detail, Set<EventRole> roles, Set<Member> roster) {
        super(name);

        requireAllNonNull(from, to, detail, roles, roster);
        // Starting date/time must be before ending date/time
        checkArgument(from.isBefore(to), MESSAGE_CONSTRAINTS);

        this.from = from;
        this.to = to;
        this.detail = detail;
        this.roles.addAll(roles);
        this.roster.addAll(roster);
    }

    public DateTime getFrom() {
        return from;
    }

    public DateTime getTo() {
        return to;
    }

    public String getDetail() {
        return detail;
    }

    /**
     * Returns an immutable role set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<EventRole> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    /**
     * Returns an immutable member set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Member> getRoster() {
        return Collections.unmodifiableSet(roster);
    }

    /**
     * Removes specified member from the event's roster
     */
    public void removeMemberFromRoster(Member member) {
        roster.removeIf(m -> m.isSameMember(member));
    }

    /**
     * Returns true if a member with the same identity as {@code member} exists in the event roster.
     */
    public boolean hasMember(Member member) {
        return getRoster().contains(member);
    }

    /**
     * Adds the given member to the event roster.
     */
    public void addMember(Member member) {
        roster.add(member);
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
     * Updates a member reference in all event rosters when it is deleted or edited
     * in EASync's {@code UniqueMemberList}.
     * @param originalMember The member to be replaced or removed.
     * @param replacementMember The new member to replace with. If {@code null}, the original member is removed.
     */
    public static void updateMemberInAllEvents(ObservableList<Event> eventList,
                                               Member originalMember, Member replacementMember) {
        for (Event event : eventList) {
            event.updateMemberInEvent(originalMember, replacementMember);
        }
    }

    /**
     * Updates a member reference in a single event roster.
     *
     * @param originalMember    The member to be replaced or removed
     * @param replacementMember the new member to replace with; if {@code null}, the original member is removed
     */
    private void updateMemberInEvent(Member originalMember, Member replacementMember) {
        if (!hasMember(originalMember)) {
            return;
        }

        removeMemberFromRoster(originalMember);

        if (replacementMember != null) {
            addMember(replacementMember);
        }
    }

    /**
     * Updates the assigned event name in event roles under roles list to new name
     *
     * @param updatedName   New name reference for event roles
     */
    public Set<EventRole> updateEventRolesAssignedTo(Name updatedName) {
        Set<EventRole> updatedRoles = new HashSet<>();

        for (EventRole eventRole: roles) {
            updatedRoles.add(new EventRole(eventRole.roleName, updatedName));
        }
        return updatedRoles;
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two events.
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
        // does not include roster as it is mutable
        return name.equals(otherEvent.name)
                && from.equals(otherEvent.from)
                && to.equals(otherEvent.to)
                && detail.equals(otherEvent.detail)
                && roles.equals(otherEvent.roles);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        // does not include roster as it is mutable
        return Objects.hash(name, from, to, detail, roles);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("from", from)
                .add("to", to)
                .add("detail", detail)
                .add("roles", roles)
                .add("roster", roster)
                .toString();
    }
}
