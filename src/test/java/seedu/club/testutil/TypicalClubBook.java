package seedu.club.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.club.model.ClubBook;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;

/**
 * A utility class containing a list of {@code Member} and {@code Event} objects to be used in tests.
 */
public class TypicalClubBook {
    public static final Member ALICE = new MemberBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withPhone("94351253").withMemberRoles("President")
            .withEventRoles("Orientation", "facilitator", "gamemaster").build();
    public static final Member BENSON = new MemberBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withEventRoles("Movie Night", "FoodIC", "OIC")
            .withPhone("98765432").withMemberRoles("VP", "Operations").build();
    public static final Member CARL = new MemberBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").build();
    public static final Member DANIEL = new MemberBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withMemberRoles("Secretary")
            .withEventRoles("Movie Night", "FoodIC", "OIC").build();
    public static final Member ELLE = new MemberBuilder().withName("Elle Meyer").withPhone("94822244")
            .withEmail("werner@example.com").withEventRoles("Movie Night", "FoodIC", "OIC").build();
    public static final Member FIONA = new MemberBuilder().withName("Fiona Kunz").withPhone("94824271")
            .withEmail("lydia@example.com").withEventRoles("Beach Day", "Gamemaster", "SafetyOfficer").build();
    public static final Member GEORGE = new MemberBuilder().withName("George Best").withPhone("94824425")
            .withEmail("anna@example.com").withEventRoles("Beach Day", "Gamemaster", "SafetyOfficer").build();

    public static final Event ORIENTATION = new EventBuilder().withName("Orientation")
            .withFrom("151025 1200").withTo("171025 1800").withDetail("For freshmen")
            .withEventRoles("facilitator", "gamemaster").withRoster(Set.of(ALICE)).build();
    public static final Event MOVIE_NIGHT = new EventBuilder().withName("Movie Night")
            .withFrom("201025 1800").withTo("201025 2000").withDetail("Showing The Shining")
            .withEventRoles("FoodIC", "OIC").withRoster(Set.of(BENSON, DANIEL, ELLE)).build();
    public static final Event BEACH_DAY = new EventBuilder().withName("Beach Day")
            .withFrom("251025 0900").withTo("251025 2000").withDetail("At Sentosa")
            .withEventRoles("Gamemaster", "SafetyOfficer").withRoster(Set.of(FIONA, GEORGE)).build();

    // Manually added events
    public static final Event MEETING = new EventBuilder().withName("Meeting")
            .withFrom("151025 1500").withTo("151025 1600")
            .withEventRoles("participant").build();
    public static final Event WORKSHOP = new EventBuilder().withName("Workshop")
            .withFrom("151025 1000").withTo("151025 1300")
            .withEventRoles("participant", "facilitator").build();

    public TypicalClubBook() {} //to prevent instantiation

    /**
     * Returns a list of typical members.
     */
    public static List<Member> getTypicalMembers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    /**
     * Returns a list of typical events.
     */
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
            cb.addEvent(new EventBuilder(event).build());
        }
        for (Member member : getTypicalMembers()) {
            cb.addMember(new MemberBuilder(member).build());
        }
        return cb;
    }
}
