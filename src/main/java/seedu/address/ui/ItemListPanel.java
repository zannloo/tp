package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.IChildElement;
import seedu.address.model.profbook.Student;

/**
 * Panel containing the list of children items.
 */
public class ItemListPanel<T extends IChildElement> extends UiPart<Region> {
    private static final String FXML = "ItemListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItemListPanel.class);

    @FXML
    private ListView<T> itemListView;

    /**
     * Creates a {@code ListPanel} with the given {@code ObservableList} and FXML file name.
     */
    public ItemListPanel(ObservableList<T> itemList) {
        super(FXML);
        logger.info(itemList.toString());
        itemListView.setItems(itemList);
        itemListView.setCellFactory(listView -> new ItemListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an item using its Displayable methods.
     */
    class ItemListViewCell extends ListCell<T> {
        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(createItemCard(item).getRoot());
            }
        }

        // Create a specific cell factory for each item type
        private UiPart<Region> createItemCard(T item) {
            // Implement the creation of ItemCard based on the item type.
            // You may need to pass an additional parameter for differentiation, or
            // have different implementations for different item types.
            // Example:
            if (item instanceof Group) {
                return new GroupCard((Group) item);
            } else if (item instanceof Student) {
                return new StudentCard((Student) item);
            }

            throw new IllegalArgumentException("No card class implemented for " + item.getClass());
        }
    }
}
