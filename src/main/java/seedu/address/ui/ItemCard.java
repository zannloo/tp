package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * ItemCard that can be displayed in ItemListPanel.
 */
public abstract class ItemCard extends UiPart<Region> {
    @FXML
    private Label index;

    /**
     * Construct an ItemCard with fxml and displayedIndex.
     */
    public ItemCard(String fxml, int displayedIndex) {
        super(fxml);
        index.setText(displayedIndex + ". ");
    }
}
