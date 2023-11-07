package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.profbook.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends ItemCard {
    public static final String DISPLAY_NAME = "%1$s";
    public static final String DISPLAY_ID = "ID: %1$s";
    public static final String DISPLAY_PHONE = "Phone: %1$s";
    public static final String DISPLAY_ADDRESS = "Address: %1$s";
    public static final String DISPLAY_EMAIL = "Email: %1$s";
    private static final String FXML = "StudentCard.fxml";

    public final Student student;

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

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML, displayedIndex);
        this.student = student;
        id.setText(String.format(DISPLAY_ID, student.getId().toString().toUpperCase()));
        name.setText(String.format(DISPLAY_NAME, student.getName().fullName));
        phone.setText(String.format(DISPLAY_PHONE, student.getPhone().value));
        address.setText(String.format(DISPLAY_ADDRESS, student.getAddress().value));
        email.setText(String.format(DISPLAY_EMAIL, student.getEmail().value));
        name.setWrapText(true);
        address.setWrapText(true);
        email.setWrapText(true);
        phone.setWrapText(true);
    }
}
