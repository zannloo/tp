package seedu.address.logic.newcommands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.newcommands.exceptions.CommandException;
import seedu.address.model.statemanager.State;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(State state) throws CommandException {
        return null;
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
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toMarkTask", this.index)
                .toString();
    }
}
