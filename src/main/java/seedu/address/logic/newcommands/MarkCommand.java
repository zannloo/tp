package seedu.address.logic.newcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.statemanager.State;
import seedu.address.model.statemanager.TaskOperation;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_SUCCESS = "This task has been successfully marked: %1$s";

    public static final String MESSAGE_INCORRECT_STATE = "The current state is not showing task list.";

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(State state) throws CommandException {
        requireNonNull(state);
        if (state.isShowTaskList()) {
            AbsolutePath displayPath = state.getDisplayPath();
            TaskOperation taskOperation = state.taskOperation(displayPath);
            taskOperation.markTask(this.index);
            state.updateList();

            return new CommandResult(String.format(MESSAGE_SUCCESS, this.index));
        } else {
            throw new CommandException(MESSAGE_INCORRECT_STATE);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        MarkCommand otherMarkCommand = (MarkCommand) other;
        return this.index == otherMarkCommand.index;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toMarkTask", this.index)
                .toString();
    }
}
