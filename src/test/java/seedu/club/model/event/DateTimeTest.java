package seedu.club.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.club.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        // Different format
        String yyyymmdd = "2025/5/13 0000";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(yyyymmdd));
    }

    @Test
    public void isValidDateTime() {
        // null date time
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));

        // invalid datetime
        assertFalse(DateTime.isValidDateTime("")); // empty string
        assertFalse(DateTime.isValidDateTime("5/13 1200")); // no year provided
        // Tests for no month and no date are skipped since they are kind of in the same EP as no year

        // Invalid month: <=0 and >=13
        assertFalse(DateTime.isValidDateTime("13/13/2025 2359")); // month >= 13
        assertFalse(DateTime.isValidDateTime("13/0/2025 2359")); // month <= 0
        // Invalid date: 29th February for non leap years, 31st for months with only 30 days, etc
        assertFalse(DateTime.isValidDateTime("29/2/2025 1300")); // February 29
        assertFalse(DateTime.isValidDateTime("31/6/2025 1300")); // Months with only 30 days
        assertFalse(DateTime.isValidDateTime("0/1/2025 1300")); // zeroth date
        assertFalse(DateTime.isValidDateTime("24/2/2025 2400")); // hour >= 24
        assertFalse(DateTime.isValidDateTime("24/2/2025 1260")); // time >= 60

        // valid datetime
        assertTrue(DateTime.isValidDateTime("3/2/2025 0000")); // Correct format: dd/mm/yyyy HHmm
        assertTrue(DateTime.isValidDateTime("13/12/2025 2359"));
        assertTrue(DateTime.isValidDateTime("31/12/2025 1200"));
    }

    @Test
    public void isBefore() {
        DateTime test = new DateTime("10/10/2025 1200");

        DateTime laterEndDate = new DateTime("11/10/2025 1200");
        DateTime laterEndTime = new DateTime("10/10/2025 1201");
        DateTime earlierEndDate = new DateTime("9/10/2025 1200");
        DateTime earlierEndTime = new DateTime("10/10/2025 1159");

        DateTime emptyDate = new DateTime("");

        assertFalse(test.isBefore(test)); // Same datetime
        assertTrue(test.isBefore(laterEndDate));
        assertTrue(test.isBefore(laterEndTime));
        assertFalse(test.isBefore(earlierEndDate));
        assertFalse(test.isBefore(earlierEndTime));

        assertTrue(test.isBefore(null));
        assertTrue(test.isBefore(emptyDate));
    }

    @Test
    public void toStringMethod() {
        DateTime test = new DateTime("15/10/2025 1200");
        String expected = "15 Oct 2025 12:00pm";

        assertEquals(expected, test.toString());
    }

    @Test
    public void equals() {
        DateTime test = new DateTime("15/10/2025 1200");

        // same values -> returns true
        assertTrue(test.equals(new DateTime("15/10/2025 1200")));

        // same object -> returns true
        assertTrue(test.equals(test));

        // null -> returns false
        assertFalse(test.equals(null));

        // different types -> returns false
        assertFalse(test.equals(5));

        // different values -> returns false
        assertFalse(test.equals(new DateTime("1/1/2025 0000")));
    }
}
