package seedu.club.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.club.commons.core.GuiSettings;
import seedu.club.commons.core.LogsCenter;
import seedu.club.logic.commands.Command;
import seedu.club.logic.commands.CommandResult;
import seedu.club.logic.commands.exceptions.CommandException;
import seedu.club.logic.parser.ClubBookParser;
import seedu.club.logic.parser.exceptions.ParseException;
import seedu.club.model.Model;
import seedu.club.model.ReadOnlyClubBook;
import seedu.club.model.event.Event;
import seedu.club.model.member.Member;
import seedu.club.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ClubBookParser clubBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        clubBookParser = new ClubBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = clubBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveMembers(model.getClubBook());
            storage.saveEvents(model.getClubBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyClubBook getClubBook() {
        return model.getClubBook();
    }

    @Override
    public ObservableList<Member> getFilteredMemberList() {
        return model.getFilteredMemberList();
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return model.getFilteredEventList();
    }

    @Override
    public Path getMemberStorageFilePath() {
        return model.getMemberStorageFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
