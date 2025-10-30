package seedu.club.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.club.model.member.Email;
import seedu.club.model.member.Member;
import seedu.club.model.member.Phone;
import seedu.club.model.name.Name;
import seedu.club.model.role.EventRole;
import seedu.club.model.role.MemberRole;
import seedu.club.model.util.SampleDataUtil;

/**
 * A utility class to help with building Member objects.
 */
public class MemberBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";

    private Name name;
    private Phone phone;
    private Email email;
    private Set<MemberRole> memberRoles;
    private Set<EventRole> eventRoles;

    /**
     * Creates a {@code MemberBuilder} with the default details.
     */
    public MemberBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        memberRoles = new HashSet<>();
        eventRoles = new HashSet<>();
    }

    /**
     * Initializes the MemberBuilder with the data of {@code memberToCopy}.
     */
    public MemberBuilder(Member memberToCopy) {
        name = memberToCopy.getName();
        phone = memberToCopy.getPhone();
        email = memberToCopy.getEmail();
        memberRoles = new HashSet<>(memberToCopy.getMemberRoles());
        eventRoles = new HashSet<>(memberToCopy.getEventRoles());
    }

    /**
     * Sets the {@code Name} of the {@code Member} that we are building.
     */
    public MemberBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code roles} into a {@code Set<MemberRole>} and set it to the {@code Member} that we are building.
     */
    public MemberBuilder withMemberRoles(String ... roles) {
        this.memberRoles = SampleDataUtil.getMemberRoleSet(roles);
        return this;
    }

    /**
     * Sets the {@code eventRoles} of the {@code Member} that we are building.
     */
    public MemberBuilder withEventRoles(Set<EventRole> eventRoles) {
        this.eventRoles.addAll(eventRoles);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Member} that we are building.
     */
    public MemberBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }



    /**
     * Sets the {@code Email} of the {@code Member} that we are building.
     */
    public MemberBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Member build() {
        return new Member(name, phone, email, memberRoles, eventRoles);
    }

}
