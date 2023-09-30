package seedu.address.model.statemanager;

import java.util.Set;

import seedu.address.model.id.Id;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Student;
import seedu.address.model.tag.Tag;

/**
 * Encapsulates the logic required to perform supported operation on students
 */
public class StudentOperation extends StateManager {

    private static final String LOGGING_PREFIX = "In Student Operations, ";
    private final Student baseDir;

    /**
     * Constructs a new student operation method
     * @param baseDir - The student object to perform operations on
     */
    StudentOperation(Student baseDir) {
        super(baseDir);
        this.baseDir = baseDir;
        this.stateLogger(StudentOperation.LOGGING_PREFIX);
    }

    public Name getStudentName() {
        return this.baseDir.getName();
    }

    public Id getStudentId() {
        return this.baseDir.getId();
    }

    public Email getStudentEmail() {
        return this.baseDir.getEmail();
    }

    public Phone getStudentPhone() {
        return this.baseDir.getPhone();
    }

    public Address getStudentAddress() {
        return this.baseDir.getAddress();
    }

    public Set<Tag> getStudentTags() {
        return this.baseDir.getTags();
    }

}
