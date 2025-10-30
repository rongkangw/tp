package seedu.club.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.club.model.ClubBook;
import seedu.club.model.ReadOnlyClubBook;
import seedu.club.model.event.DateTime;
import seedu.club.model.event.Event;
import seedu.club.model.member.Email;
import seedu.club.model.member.Member;
import seedu.club.model.member.Phone;
import seedu.club.model.name.Name;
import seedu.club.model.role.EventRole;
import seedu.club.model.role.MemberRole;

/**
 * Contains utility methods for populating {@code ClubBook} with sample data.
 */
public class SampleDataUtil {
    private static Member[] getSampleMembers() {
        return new Member[] {
            new Member(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getMemberRoleSet("President")),
            new Member(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                getMemberRoleSet("Vice President", "Operations")),
            new Member(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                getMemberRoleSet("Secretary")),
            new Member(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                getMemberRoleSet("Logistics")),
            new Member(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                getMemberRoleSet("Publicity")),
            new Member(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                getMemberRoleSet())
        };
    }

    private static Event[] getSampleEvents() {
        return new Event[] {
            new Event(new Name("Orientation"), new DateTime("151025 1200"),
                    new DateTime("171025 1500"), "For freshmen",
                    getEventRoleSet(new Name("Orientation"), "facilitator", "game master")),
            new Event(new Name("Movie Night"), new DateTime("201025 1800"),
                    new DateTime("201025 2000"), "Showing The Shining",
                    getEventRoleSet(new Name("Movie Night"), "FoodIC", "OIC")),
            new Event(new Name("Beach Day"), new DateTime("251025 0900"),
                    new DateTime("251025 1300"), "At Sentosa",
                    getEventRoleSet(new Name("Beach Day"), "Game Master", "Safety Officer"))
        };
    }

    public static ReadOnlyClubBook getSampleClubBook() {
        ClubBook sampleClubBook = new ClubBook();
        for (Member sampleMember : getSampleMembers()) {
            sampleClubBook.addMember(sampleMember);
        }
        for (Event sampleEvent : getSampleEvents()) {
            sampleClubBook.addEvent(sampleEvent);
        }
        return sampleClubBook;
    }

    /**
     * Returns a member role set containing the list of strings given.
     */
    public static Set<MemberRole> getMemberRoleSet(String... strings) {
        return Arrays.stream(strings)
                .map(MemberRole::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an event role set containing the list of strings given.
     */
    public static Set<EventRole> getEventRoleSet(Name eventName, String... strings) {
        return Arrays.stream(strings)
                .map(s -> new EventRole(s, eventName))
                .collect(Collectors.toSet());
    }
}
