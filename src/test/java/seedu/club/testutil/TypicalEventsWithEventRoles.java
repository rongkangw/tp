package seedu.club.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.club.model.ClubBook;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEventsWithEventRoles {

    public static final Event ORIENTATION = new EventBuilder().withName("Orientation")
            .withFrom("151025 1200").withTo("171025 1800").withDetail("For freshmen")
            .withRoles("facilitator", "gamemaster").build();
    public static final Event MOVIE_NIGHT = new EventBuilder().withName("Movie Night")
            .withFrom("201025 1800").withTo("201025 2000").withDetail("Showing The Shining")
            .withRoles("FoodIC", "OIC").build();
    public static final Event BEACH_DAY = new EventBuilder().withName("Beach Day")
            .withFrom("251025 0900").withTo("251025 2000").withDetail("At Sentosa")
            .withRoles("Gamemaster", "SafetyOfficer").build();
    public static final Event MEETING = new EventBuilder().withName("Meeting")
            .withFrom("151025 1500").withTo("151025 1600")
            .withRoles("participant").build();
    public static final Event WORKSHOP = new EventBuilder().withName("Workshop")
            .withFrom("151025 1000").withTo("151025 1300")
            .withRoles("participant", "facilitator").build();
    public static final Member JOHN = new MemberBuilder()
            .withName("John")
            .withPhone("94824491")
            .withEmail("John@example.com")
            .withEventRoles(ORIENTATION.getRoles())
            .build();
    public static final Member JANE = new MemberBuilder()
            .withName("Jane")
            .withPhone("94834491")
            .withEmail("Jane@example.com")
            .withEventRoles(MOVIE_NIGHT.getRoles())
            .build();


    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(ORIENTATION, MOVIE_NIGHT, BEACH_DAY));
    }

    /**
     * Returns an {@code ClubBook} with all the typical events.
     */
    public static ClubBook getTypicalClubBookWithEventRoles() {
        ClubBook cb = new ClubBook();
        for (Event event : getTypicalEvents()) {
            cb.addEvent(event);
        }
        cb.addMember(JOHN);
        cb.addMember(JANE);
        return cb;
    }

}
