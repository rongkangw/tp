package seedu.club.testutil;

import seedu.club.logic.commands.event.EditEventCommand;
import seedu.club.model.event.DateTime;
import seedu.club.model.event.Event;
import seedu.club.model.name.Name;

/**
 * A utility class to help with building EditMemberDescriptor objects.
 */
public class EditEventDescriptorBuilder {

    private final EditEventCommand.EditEventDescriptor descriptor;

    public EditEventDescriptorBuilder() {
        descriptor = new EditEventCommand.EditEventDescriptor();
    }

    public EditEventDescriptorBuilder(EditEventCommand.EditEventDescriptor descriptor) {
        this.descriptor = new EditEventCommand.EditEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMemberDescriptor} with fields containing {@code member}'s details
     */
    public EditEventDescriptorBuilder(Event event) {
        descriptor = new EditEventCommand.EditEventDescriptor();
        descriptor.setName(event.getName());
        descriptor.setFrom(event.getFrom());
        descriptor.setTo(event.getTo());
        descriptor.setDetails(event.getDetail());
    }

    /**
     * Sets the {@code Name} of the {@code EditMemberDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code from} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withFrom(String from) {
        descriptor.setFrom(new DateTime(from));
        return this;
    }

    /**
     * Sets the {@code to} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withTo(String to) {
        descriptor.setTo(new DateTime(to));
        return this;
    }

    /**
     * Parses the {@code roles} into a {@code Set<MemberRole>} and set it to the {@code EditMemberDescriptor}
     * that we are building.
     */
    public EditEventDescriptorBuilder withDetail(String details) {
        descriptor.setDetails(details);
        return this;
    }

    public EditEventCommand.EditEventDescriptor build() {
        return descriptor;
    }
}
