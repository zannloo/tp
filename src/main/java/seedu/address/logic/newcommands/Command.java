package seedu.address.logic.newcommands;

import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.statemanager.State;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(State state) throws CommandException;
}
