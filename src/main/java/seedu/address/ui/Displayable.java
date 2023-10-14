package seedu.address.ui;

import javafx.scene.layout.Region;

/**
 * Item that can be displayed on display panel
 */
public interface Displayable {
    /**
     * Get card that can display on panel
     */
    public UiPart<Region> getDisplayCard(int displayedIndex);
}
