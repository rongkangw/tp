package seedu.club.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.club.testutil.EventBuilder;

public class EventContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EventContainsKeywordsPredicate firstPredicate = new EventContainsKeywordsPredicate(firstPredicateKeywordList);
        EventContainsKeywordsPredicate secondPredicate = new EventContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EventContainsKeywordsPredicate firstPredicateCopy =
                new EventContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different member -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        EventContainsKeywordsPredicate predicate = new EventContainsKeywordsPredicate(Collections
                .singletonList("Orientation"));
        assertTrue(predicate.test(new EventBuilder().withName("Orientation Camp").build()));

        // Multiple keywords
        predicate = new EventContainsKeywordsPredicate(Arrays.asList("Orientation", "Camp"));
        assertTrue(predicate.test(new EventBuilder().withName("Orientation Camp").build()));

        // Only one matching keyword
        predicate = new EventContainsKeywordsPredicate(Arrays.asList("Orientation", "Meeting"));
        assertTrue(predicate.test(new EventBuilder().withName("Exco Meeting").build()));

        // Mixed-case keywords
        predicate = new EventContainsKeywordsPredicate(Arrays.asList("orIENtaTion", "caMP"));
        assertTrue(predicate.test(new EventBuilder().withName("Orientation Camp").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EventContainsKeywordsPredicate predicate = new EventContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EventBuilder().withName("Meeting").build()));

        // Non-matching keyword
        predicate = new EventContainsKeywordsPredicate(Arrays.asList("Workshop"));
        assertFalse(predicate.test(new EventBuilder().withName("Orientation Camp").build()));

        // Keywords match phone, and email, but does not match name
        predicate = new EventContainsKeywordsPredicate(Arrays.asList("15/10/2025",
                "16/10/2025", "Facilitator"));
        assertFalse(predicate.test(new EventBuilder().withName("Alice").withFrom("15/10/2025")
                .withTo("16/10/2025").withRoles("Facilitator").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        EventContainsKeywordsPredicate predicate = new EventContainsKeywordsPredicate(keywords);

        String expected = EventContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
