package seedu.club.model.event;

import java.util.List;
import java.util.function.Predicate;

import seedu.club.commons.util.StringUtil;
import seedu.club.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Member}'s {@code Name} matches any of the keywords given.
 */
public class EventContainsKeywordsPredicate implements Predicate<Event> {
    private final List<String> keywords;

    public EventContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventContainsKeywordsPredicate)) {
            return false;
        }

        EventContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (EventContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
