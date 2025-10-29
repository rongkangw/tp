package seedu.club.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.club.logic.commands.event.EditEventCommand;
import seedu.club.logic.commands.member.EditMemberCommand.EditMemberDescriptor;
import seedu.club.model.event.Event;
import seedu.club.model.member.Email;
import seedu.club.model.member.Member;
import seedu.club.model.member.Phone;
import seedu.club.model.name.Name;
import seedu.club.model.role.MemberRole;

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
     * Sets the {@code Phone} of the {@code EditMemberDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withFrom(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditMemberDescriptor} that we are building.
     */
    public EditMemberDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
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
