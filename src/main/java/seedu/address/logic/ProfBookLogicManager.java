package seedu.address.logic;

import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.newcommands.Command;
import seedu.address.logic.newcommands.CommandResult;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.newcommandparser.ProfBookParser;
import seedu.address.model.statemanager.State;
//import seedu.address.storage.Storage;
import seedu.address.ui.Displayable;

/**
 * The main LogicManager of the app.
 */
public class ProfBookLogicManager {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final State state;
    //private final Storage storage = null;
    private final ProfBookParser profBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public ProfBookLogicManager(State state) {
        //todo : storage;
        this.state = state;
        profBookParser = new ProfBookParser();
    }

    /**
     * Execute command and return the result if success
     * or throw exception if error occurred.
     */
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = profBookParser.parseCommand(commandText);
        commandResult = command.execute(state);

        // try {
        //     storage.saveAddressBook(model.getAddressBook());
        // } catch (AccessDeniedException e) {
        //     throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        // } catch (IOException ioe) {
        //     throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        // }

        return commandResult;
    }

    public ObservableList<Displayable> getFilteredList() {
        return state.getFilteredList();
    }

    public Path getAddressBookFilePath() {
        return state.getAddressBookFilePath();
    }

    public GuiSettings getGuiSettings() {
        return state.getGuiSettings();
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        state.setGuiSettings(guiSettings);
    }
}
