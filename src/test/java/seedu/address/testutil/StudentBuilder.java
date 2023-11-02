package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.id.StudentId;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.ReadOnlyTaskList;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskListManager;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {
    public static final String DEFAULT_ID = "0001Y";

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Task DEFAULT_TASK = new Deadline("Assignment 3", LocalDateTime.parse("2023-12-03T23:59"));

    private ReadOnlyTaskList taskList;
    private StudentId id;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        id = new StudentId(DEFAULT_ID);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        List<Task> defaultTaskList = new ArrayList<>();
        defaultTaskList.add(DEFAULT_TASK);
        taskList = new TaskListManager(defaultTaskList);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        id = studentToCopy.getId();
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        taskList = new TaskListManager(studentToCopy.getAllTasks());
    }

    /**
     * Sets the {@code Id} of the {@code Student} that we are building.
     */
    public StudentBuilder withId(String id) {
        this.id = new StudentId(id);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }


    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code TaskList} of the {@code Student} that we are building.
     */
    public StudentBuilder withTaskList(ReadOnlyTaskList tasklist) {
        this.taskList = tasklist;
        return this;
    }

    /**
     * Builds a `Student` object with the specified attributes.
     *
     * @return The `Student` object.
     */
    public Student build() {
        return new Student(taskList, name, email, phone, address, id);
    }
}
