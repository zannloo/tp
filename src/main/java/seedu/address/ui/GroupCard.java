package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.profbook.Group;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class GroupCard extends UiPart<Region> {
    private static final String FXML = "GroupCard.fxml";

    public final Group group;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    //todo: TaskList

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public GroupCard(Group group) {
        super(FXML);
        this.group = group;
        id.setText(group.getId().toString());
        name.setText(group.getName().fullName);
    }
}
