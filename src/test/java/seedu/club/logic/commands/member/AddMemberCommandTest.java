package seedu.club.logic.commands.member;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.club.testutil.Assert.assertThrows;
import static seedu.club.testutil.TypicalMembers.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.club.commons.core.GuiSettings;
import seedu.club.logic.Messages;
import seedu.club.logic.commands.CommandResult;
import seedu.club.logic.commands.exceptions.CommandException;
import seedu.club.model.ClubBook;
import seedu.club.model.Model;
import seedu.club.model.ReadOnlyClubBook;
import seedu.club.model.ReadOnlyUserPrefs;
import seedu.club.model.ViewState;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;
import seedu.club.testutil.MemberBuilder;

/**
 * Contains unit tests for {@code AddMemberCommand}.
 */
public class AddMemberCommandTest {

    @Test
    public void constructor_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMemberCommand(null));
    }

    @Test
    public void execute_memberAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingMemberAdded modelStub = new ModelStubAcceptingMemberAdded();
        Member validMember = new MemberBuilder().build();

        CommandResult commandResult = new AddMemberCommand(validMember).execute(modelStub);

        assertEquals(String.format(AddMemberCommand.MESSAGE_SUCCESS, Messages.format(validMember)),
                commandResult.getFeedbackToUser());

        assertEquals(Arrays.asList(validMember), modelStub.membersAdded);
    }

    @Test
    public void execute_duplicateMember_throwsCommandException() {
        Member validMember = new MemberBuilder().build();
        AddMemberCommand addMemberCommand = new AddMemberCommand(validMember);
        ModelStub modelStub = new ModelStubWithMember(validMember);

        assertThrows(CommandException.class, AddMemberCommand.MESSAGE_DUPLICATE_MEMBER, () ->
                addMemberCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Member alice = new MemberBuilder().withName("Alice").build();
        Member bob = new MemberBuilder().withName("Bob").build();
        AddMemberCommand addAliceCommand = new AddMemberCommand(alice);
        AddMemberCommand addBobCommand = new AddMemberCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddMemberCommand addAliceCommandCopy = new AddMemberCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different member -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddMemberCommand addMemberCommand = new AddMemberCommand(ALICE);
        String expected = AddMemberCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addMemberCommand.toString());
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private static class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getMemberStorageFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMemberStorageFilePath(Path memberStorageFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMember(Member member) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClubBook(ReadOnlyClubBook clubBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyClubBook getClubBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMember(Member member) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMember(Member target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMember(Member target, Member editedMember) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Member> getFilteredMemberList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMemberList(Predicate<Member> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ViewState getViewState() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setViewState(ViewState viewState) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single member.
     */
    private static class ModelStubWithMember extends ModelStub {
        private final Member member;

        ModelStubWithMember(Member member) {
            requireNonNull(member);
            this.member = member;
        }

        @Override
        public boolean hasMember(Member member) {
            requireNonNull(member);
            return this.member.isSameMember(member);
        }
    }

    /**
     * A Model stub that always accept the member being added.
     */
    private static class ModelStubAcceptingMemberAdded extends ModelStub {
        final ArrayList<Member> membersAdded = new ArrayList<>();

        @Override
        public boolean hasMember(Member member) {
            requireNonNull(member);
            return membersAdded.stream().anyMatch(member::isSameMember);
        }

        @Override
        public void addMember(Member member) {
            requireNonNull(member);
            membersAdded.add(member);
        }

        @Override
        public ReadOnlyClubBook getClubBook() {
            return new ClubBook();
        }

        /**
         * Method is overridden but not implemented since {@code ModelStubAcceptingMemberAdded} only cares about
         * adding members and not setting list states.
         */
        @Override
        public void setViewState(ViewState state) {
        }
    }

}
