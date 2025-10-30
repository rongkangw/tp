package seedu.club.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.club.model.event.DateTime;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;
import seedu.club.model.name.Name;
import seedu.club.model.role.EventRole;
import seedu.club.model.util.SampleDataUtil;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "Orientation";
    public static final String DEFAULT_FROM = "151025 1200";
    public static final String DEFAULT_TO = "171025 1800";
    public static final String DEFAULT_DETAIL = "NIL";

    private Name name;
    private DateTime from;
    private DateTime to;
    private String detail;
    private Set<EventRole> roles;
    private Set<Member> roster;

    /**
     * Creates a {@code EventBuilder} with the default detail
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        from = new DateTime(DEFAULT_FROM);
        to = new DateTime(DEFAULT_TO);
        detail = DEFAULT_DETAIL;
        roles = new HashSet<>();
        roster = new HashSet<>();
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}
     * @param eventToCopy event to be copied
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        from = eventToCopy.getFrom();
        to = eventToCopy.getTo();
        detail = eventToCopy.getDetail();
        roles = new HashSet<>(eventToCopy.getRoles());
        roster = new HashSet<>(eventToCopy.getRoster());
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     *
     * @param name to be set
     * @return EventBuilder
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code roles} into a {@code Set<Role>} and set it to the {@code Event} that we are building.
     *
     * @param roles to be set
     * @return EventBuilder
     */
    public EventBuilder withRoles(String ... roles) {
        this.roles = SampleDataUtil.getEventRoleSet(this.name, roles);
        return this;
    }

    /**
     * Sets the {@code From} of the {@code Event} that we are building.
     *
     * @param from to be set
     * @return EventBuilder
     */
    public EventBuilder withFrom(String from) {
        this.from = new DateTime(from);
        return this;
    }

    /**
     * Sets the {@code To} of the {@code Event} that we are building.
     *
     * @param to to be set
     * @return EventBuilder
     */
    public EventBuilder withTo(String to) {
        this.to = new DateTime(to);
        return this;
    }

    /**
     * Sets the {@code Detail} of the {@code Event} that we are building.
     *
     * @param detail to be set
     * @return EventBuilder
     */
    public EventBuilder withDetail(String detail) {
        this.detail = detail;
        return this;
    }

    /**
     * Sets the {@code Roster} of the {@code Event} that we are building.
     *
     * @param members to be set
     * @return EventBuilder
     */
    public EventBuilder withRoster(Set<Member> members) {
        this.roster.addAll(members);
        return this;
    }

    public Event build() {
        return new Event(name, from, to, detail, roles, roster);
    }

}
