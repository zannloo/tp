package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;


/**
 * TaskCard that can be displayed on ItemListPanel.
 */
public class TaskCard extends ItemCard {

    @FXML
    protected Label type;
    @FXML
    protected CheckBox checkBox;
    @FXML
    protected Label desc;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TaskCard(String descStr, boolean status, String fxml, int displayedIndex) {
        super(fxml, displayedIndex);
        desc.setText(descStr);
        checkBox.setSelected(status);
        checkBox.setDisable(true);
        if (displayedIndex % 2 == 0) {
            cardPane.setStyle("-fx-background-color: #534531;");
        } else {
            cardPane.setStyle("-fx-background-color: #866937;");
        }
        desc.setWrapText(true);
    }
}
