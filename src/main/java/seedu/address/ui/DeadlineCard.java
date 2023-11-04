package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.task.Deadline;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class DeadlineCard extends TaskCard {
    private static final String FXML = "DeadlineCard.fxml";

    public final Deadline deadline;

    @FXML
    private Label by;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DeadlineCard(Deadline deadline, int displayedIndex) {
        super(deadline.getDesc(), deadline.getStatus(), FXML, displayedIndex);
        this.deadline = deadline;
        type.setText("Deadline");
        by.setText(deadline.getDeadline());
    }
}
