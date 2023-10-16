package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.taskmanager.Deadline;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class DeadlineCard extends UiPart<Region> {
    private static final String FXML = "DeadlineCard.fxml";

    public final Deadline deadline;

    @FXML
    private HBox cardPane;
    @FXML
    private Label type;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Label index;
    @FXML
    private Label desc;
    @FXML
    private Label by;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DeadlineCard(Deadline deadline, int displayedIndex) {
        super(FXML);
        this.deadline = deadline;
        index.setText(displayedIndex + ". ");
        type.setText("Deadline");
        desc.setText(deadline.getDesc());
        by.setText(deadline.getDeadline());
        checkBox.setSelected(deadline.getStatus());
        checkBox.setDisable(true);
        if (displayedIndex % 2 == 0) {
            cardPane.setStyle("-fx-background-color: #534531;");
        } else {
            cardPane.setStyle("-fx-background-color: #866937;");
        }
    }
}
