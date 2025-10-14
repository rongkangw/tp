package seedu.address.testutil;

import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event CLASS = new EventBuilder().withName("Class")
            .withFrom("14/10/2025").withTo("14/10/2025")
            .withRoles("teacher").build();

}
