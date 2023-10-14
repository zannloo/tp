package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.profbook.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {
    private static final String FXML = "StudentCard.fxml";

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    //todo: TaskList

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public StudentCard(Student student) {
        super(FXML);
        this.student = student;
        id.setText(student.getId().toString());
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().value);
        address.setText(student.getAddress().value);
        email.setText(student.getEmail().value);
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
