package seedu.club.testutil;

import seedu.club.model.ClubBook;
import seedu.club.model.member.Member;

/**
 * A utility class to help with building Clubbook objects.
 * Example usage: <br>
 *     {@code ClubBook ab = new ClubBookBuilder().withMember("John", "Doe").build();}
 */
public class ClubBookBuilder {

    private ClubBook clubBook;

    public ClubBookBuilder() {
        clubBook = new ClubBook();
    }

    public ClubBookBuilder(ClubBook clubBook) {
        this.clubBook = clubBook;
    }

    /**
     * Adds a new {@code Member} to the {@code ClubBook} that we are building.
     */
    public ClubBookBuilder withMember(Member member) {
        clubBook.addMember(member);
        return this;
    }

    public ClubBook build() {
        return clubBook;
    }
}
