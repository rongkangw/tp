package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.event.Event;
import seedu.address.model.member.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "Orientation";
    public static final String DEFAULT_FROM = "Monday";
    public static final String DEFAULT_TO = "Tuesday";
    public static final String DEFAULT_DETAILS = "NIL";

    private Name name;
    private String from;
    private String to;
    private String details;
    private Set<Tag> roles;

    /**
     * Creates a {@code EventBuilder} with the default details
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        from = DEFAULT_FROM;
        to = DEFAULT_TO;
        details = DEFAULT_DETAILS;
        roles = new HashSet<>();
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}
     * @param eventToCopy event to be copied
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        from = eventToCopy.getFrom();
        to = eventToCopy.getTo();
        details = DEFAULT_DETAILS;
        roles = new HashSet<>(eventToCopy.getRoles());
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
     * Parses the {@code roles} into a {@code Set<Tag>} and set it to the {@code Event} that we are building.
     *
     * @param roles to be set
     * @return EventBuilder
     */
    public EventBuilder withRoles(String ... roles) {
        this.roles = SampleDataUtil.getTagSet(roles);
        return this;
    }

    /**
     * Sets the {@code From} of the {@code Event} that we are building.
     *
     * @param from to be set
     * @return EventBuilder
     */
    public EventBuilder withFrom(String from) {
        this.from = from;
        return this;
    }

    /**
     * Sets the {@code To} of the {@code Event} that we are building.
     *
     * @param to to be set
     * @return EventBuilder
     */
    public EventBuilder withTo(String to) {
        this.to = to;
        return this;
    }

    /**
     * Sets the {@code Details} of the {@code Event} that we are building.
     *
     * @param details to be set
     * @return EventBuilder
     */
    public EventBuilder withDetails(String details) {
        this.details = details;
        return this;
    }

    public Event build() {
        return new Event(name, from, to, details, roles);
    }

}
