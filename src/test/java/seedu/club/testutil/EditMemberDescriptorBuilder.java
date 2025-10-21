package seedu.club.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.club.logic.commands.member.EditCommand.EditMemberDescriptor;
import seedu.club.model.member.Email;
import seedu.club.model.member.Member;
import seedu.club.model.member.Phone;
import seedu.club.model.name.Name;
import seedu.club.model.role.MemberRole;

/**
 * A utility class to help with building EditMemberDescriptor objects.
 */
public class EditMemberDescriptorBuilder {

    private final EditMemberDescriptor descriptor;

    public EditMemberDescriptorBuilder() {
        descriptor = new EditMemberDescriptor();
    }

    public EditMemberDescriptorBuilder(EditMemberDescriptor descriptor) {
        this.descriptor = new EditMemberDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMemberDescriptor} with fields containing {@code member}'s details
     */
    public EditMemberDescriptorBuilder(Member member) {
        descriptor = new EditMemberDescriptor();
        descriptor.setName(member.getName());
        descriptor.setPhone(member.getPhone());
        descriptor.setEmail(member.getEmail());
        descriptor.setRoles(member.getRoles());
    }

    /**
     * Sets the {@code Name} of the {@code EditMemberDescriptor} that we are building.
     */
    public EditMemberDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditMemberDescriptor} that we are building.
     */
    public EditMemberDescriptorBuilder withPhone(String phone) {
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
    public EditMemberDescriptorBuilder withRoles(String... roles) {
        Set<MemberRole> memberRoleSet = Stream.of(roles).map(MemberRole::new).collect(Collectors.toSet());
        descriptor.setRoles(memberRoleSet);
        return this;
    }

    public EditMemberDescriptor build() {
        return descriptor;
    }
}
