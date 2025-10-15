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

    public static final Event CLASS = new EventBuilder().withName("Class")
            .withFrom("14/10/2025").withTo("14/10/2025")
            .withRoles("teacher").build();

    public static final Event MEETING = new EventBuilder().withName("Meeting")
            .withFrom("15/10/2025").withTo("15/10/2025")
            .withRoles("participant").build();

    public static final Event WORKSHOP = new EventBuilder().withName("Workshop")
            .withFrom("15/10/2025").withTo("15/10/2025")
            .withRoles("participant", "facilitator").build();



    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(CLASS, MEETING, WORKSHOP));
    }

    /**
     * Returns an {@code AddressBook} with all the typical events.
     */
    public static AddressBook getTypicalAddressBookWithEvents() {
        AddressBook ab = new AddressBook();
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }
}
