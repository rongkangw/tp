package seedu.club.testutil;

import static seedu.club.logic.commands.CommandTestUtil.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.club.model.ClubBook;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;

public class TypicalClubBook {
    public static final Event ORIENTATION = new EventBuilder().withName("Orientation")
            .withFrom("151025 1200").withTo("171025 1800").withDetail("For freshmen")
            .withEventRoles("facilitator", "gamemaster").build();
    public static final Event MOVIE_NIGHT = new EventBuilder().withName("Movie Night")
            .withFrom("201025 1800").withTo("201025 2000").withDetail("Showing The Shining")
            .withEventRoles("FoodIC", "OIC").build();
    public static final Event BEACH_DAY = new EventBuilder().withName("Beach Day")
            .withFrom("251025 0900").withTo("251025 2000").withDetail("At Sentosa")
            .withEventRoles("Gamemaster", "SafetyOfficer").build();

    // Manually added
    public static final Event MEETING = new EventBuilder().withName("Meeting")
            .withFrom("151025 1500").withTo("151025 1600")
            .withEventRoles("participant").build();
    public static final Event WORKSHOP = new EventBuilder().withName("Workshop")
            .withFrom("151025 1000").withTo("151025 1300")
            .withEventRoles("participant", "facilitator").build();

    public static final Member ALICE = new MemberBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withPhone("94351253")
            .withMemberRoles("President").withEventRoles(ORIENTATION.getRoles()).build();
    public static final Member BENSON = new MemberBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withEventRoles(MOVIE_NIGHT.getRoles()).withPhone("98765432")
            .withMemberRoles("VP", "Operations").build();
    public static final Member CARL = new MemberBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").build();
    public static final Member DANIEL = new MemberBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withMemberRoles("Secretary").withEventRoles(MOVIE_NIGHT.getRoles()).build();
    public static final Member ELLE = new MemberBuilder().withName("Elle Meyer").withPhone("94822244")
            .withEmail("werner@example.com").withEventRoles(MOVIE_NIGHT.getRoles()).build();
    public static final Member FIONA = new MemberBuilder().withName("Fiona Kunz").withPhone("94824271")
            .withEmail("lydia@example.com").withEventRoles(BEACH_DAY.getRoles()).build();
    public static final Member GEORGE = new MemberBuilder().withName("George Best").withPhone("94824425")
            .withEmail("anna@example.com").withEventRoles(BEACH_DAY.getRoles()).build();

    // Manually added
    public static final Member HOON = new MemberBuilder().withName("Hoon Meier").withPhone("84824241")
            .withEmail("stefan@example.com").build();
    public static final Member IDA = new MemberBuilder().withName("Ida Mueller").withPhone("84821311")
            .withEmail("hans@example.com").build();

    // Manually added - Member's details found in {@code CommandTestUtil}
    public static final Member AMY = new MemberBuilder().withName(VALID_MEMBER_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withMemberRoles(VALID_MEMBER_ROLE_TREASURER).build();
    public static final Member BOB = new MemberBuilder().withName(VALID_MEMBER_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withMemberRoles(VALID_MEMBER_ROLE_PRESIDENT, VALID_MEMBER_ROLE_TREASURER)
            .build();

    public TypicalClubBook() {} //to prevent instantiation

    public static List<Member> getTypicalMembers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(ORIENTATION, MOVIE_NIGHT, BEACH_DAY));
    }

    /**
     * Returns an {@code ClubBook} with all the typical events only.
     */
    public static ClubBook getTypicalEventOnlyClubBook() {
        ClubBook cb = new ClubBook();
        for (Event event : getTypicalEvents()) {
            cb.addEvent(event);
        }
        return cb;
    }

    /**
     * @return an {@code ClubBook} with all the typical events and members.
     */
    public static ClubBook getTypicalClubBook() {
        ClubBook cb = new ClubBook();
        for (Event event : getTypicalEvents()) {
            cb.addEvent(event);
        }
        for (Member member : getTypicalMembers()) {
            cb.addMember(member);
        }
        return cb;
    }
}
