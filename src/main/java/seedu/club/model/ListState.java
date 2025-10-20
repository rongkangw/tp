package seedu.club.model;

import seedu.club.logic.commands.general.ClearCommand;
import seedu.club.logic.commands.general.ExitCommand;
import seedu.club.logic.commands.general.HelpCommand;

/**
 * Represents the current list state of the ClubBook.
 * This state determines which list (members or events) is displayed in the UI.
 *
 * <p>The state changes when commands targeting different entities are executed:
 * <ul>
 *   <li>{@code MEMBER} – when the user executes member-related commands.</li>
 *   <li>{@code EVENT} – when the user executes event-related commands.</li>
 * </ul>
 *
 * For neutral commands like {@link ClearCommand}, {@link ExitCommand} and {@link HelpCommand}, the existing
 * state is preserved.
 */
public enum ListState {
    MEMBER,
    EVENT
}
