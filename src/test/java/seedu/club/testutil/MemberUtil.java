package seedu.club.testutil;

import static seedu.club.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.club.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.club.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.club.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.Set;

import seedu.club.logic.commands.member.AddMemberCommand;
import seedu.club.logic.commands.member.EditCommand.EditMemberDescriptor;
import seedu.club.model.member.Member;
import seedu.club.model.role.MemberRole;

/**
 * A utility class for Member.
 */
public class MemberUtil {

    /**
     * Returns an add member command string for adding the {@code member}.
     */
    public static String getAddMemberCommand(Member member) {
        return AddMemberCommand.COMMAND_WORD + " " + getMemberDetails(member);
    }

    /**
     * Returns the part of command string for the given {@code member}'s details.
     */
    public static String getMemberDetails(Member member) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + member.getName().fullName + " ");
        sb.append(PREFIX_PHONE + member.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + member.getEmail().value + " ");
        member.getRoles().stream().forEach(
            s -> sb.append(PREFIX_ROLE + s.roleName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditMemberDescriptor}'s details.
     */
    public static String getEditMemberDescriptorDetails(EditMemberDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        if (descriptor.getRoles().isPresent()) {
            Set<MemberRole> roles = descriptor.getRoles().get();
            if (roles.isEmpty()) {
                sb.append(PREFIX_ROLE);
            } else {
                roles.forEach(s -> sb.append(PREFIX_ROLE).append(s.roleName).append(" "));
            }
        }
        return sb.toString();
    }
}
