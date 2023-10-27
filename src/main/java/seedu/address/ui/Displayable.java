package seedu.address.ui;

/**
 * Item that can be displayed on display panel
 */
public interface Displayable {
    /**
     * Get card that can display on panel
     */
    public ItemCard getDisplayCard(int displayedIndex);
}
