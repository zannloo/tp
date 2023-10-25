package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Gets the feedback message to be displayed to the user.
     *
     * @return The feedback message as a String.
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Checks if help information should be shown to the user.
     *
     * @return {@code true} if help information should be shown, {@code false} otherwise.
     */
    public boolean isShowHelp() {
        return showHelp;
    }

    /**
     * Checks if the application should exit.
     *
     * @return {@code true} if the application should exit, {@code false} otherwise.
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Compares this CommandResult with another object for equality.
     *
     * @param other The object to compare with.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    /**
     * Computes the hash code for this CommandResult.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

    /**
     * Returns a string representation of this CommandResult.
     *
     * @return A string containing the feedback message, showHelp, and exit information.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .toString();
    }
}
