package seedu.club.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.club.model.ClubBook;
import seedu.club.model.member.Member;

/**
 * A utility class containing a list of {@code Member} objects to be used in tests.
 */
public class TypicalMembers {

    public static final Member ALICE = new MemberBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withPhone("94351253")
            .withMemberRoles("President").build();
    public static final Member BENSON = new MemberBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withMemberRoles("VP", "Operations").build();
    public static final Member CARL = new MemberBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").build();
    public static final Member DANIEL = new MemberBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withMemberRoles("Secretary").build();
    public static final Member ELLE = new MemberBuilder().withName("Elle Meyer").withPhone("94822244")
            .withEmail("werner@example.com").build();
    public static final Member FIONA = new MemberBuilder().withName("Fiona Kunz").withPhone("94824271")
            .withEmail("lydia@example.com").build();
    public static final Member GEORGE = new MemberBuilder().withName("George Best").withPhone("94824425")
            .withEmail("anna@example.com").build();

    private TypicalMembers() {} // prevents instantiation

    /**
     * Returns an {@code ClubBook} with all the typical members.
     */
    public static ClubBook getTypicalMemberOnlyClubBook() {
        ClubBook ab = new ClubBook();
        for (Member member : getTypicalMembers()) {
            ab.addMember(member);
        }
        return ab;
    }

    public static List<Member> getTypicalMembers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
