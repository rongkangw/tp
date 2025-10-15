package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event ORIENTATION = new EventBuilder().withName("Orientation")
            .withFrom("15/10/2025").withTo("17/10/2025").withDetails("For freshmen")
            .withRoles("facilitator", "gamemaster").build();
    public static final Event MOVIE_NIGHT = new EventBuilder().withName("Movie Night")
            .withFrom("20/10/2025 1800").withTo("20/10/2025 2000").withDetails("Showing The Shining")
            .withRoles("FoodIC", "OIC").build();
    public static final Event BEACH_DAY = new EventBuilder().withName("Beach Day")
            .withFrom("25/10/2025").withTo("25/10/2025").withDetails("At Sentosa")
            .withRoles("Gamemaster", "SafetyOfficer").build();

    public static AddressBook getTypicalEventAddressBook() {
        AddressBook ab = new AddressBook();
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(ORIENTATION, MOVIE_NIGHT, BEACH_DAY));
    }
}
