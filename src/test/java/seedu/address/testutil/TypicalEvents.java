package seedu.address.testutil;

import seedu.address.model.event.Event;

public class TypicalEvents {

    public static final Event CLASS = new EventBuilder().withName("Class")
            .withFrom("14/10/2025").withTo("14/10/2025")
            .withRoles("teacher").build();

}
