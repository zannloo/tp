package seedu.address.model.profbook;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.id.Id;
import seedu.address.model.tag.Tag;
import seedu.address.model.taskmanager.TaskList;
import seedu.address.model.taskmanager.exceptions.NoSuchTaskException;

/**
 * Encapsulates logic for a student's data
 */
public class Student extends TaskListManager implements IChildElement {

    // Identity field
    /**
     * Name of the group
     */
    private final Name name;
    private final Email email;
    private final Phone phone;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Unique identifier of the group
     */
    private final Id id;

    /**
     * Constructs a new Student instance
     *
     * @param taskList - The task list associated with this student
     * @param name     - The group name
     * @param email    - The email of the student
     * @param phone    - Student's Phone number
     * @param address  - Students address
     * @param id       - Unique identifier of the group
     */

    public Student(TaskList taskList, Name name, Email email, Phone phone, Address address, Id id) {
        super((taskList));
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public Id getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public Phone getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public Set<Tag> getTags() {
        return tags;
    }


    /**
     * Creates a clone of the current element, this is to achieve immutability
     *
     * @return The clone of the IChildElement
     */
    @Override
    public Student clone() {
        try {
            return new Student(new TaskList(this.getAllTask()), new Name(this.name.fullName),
                    new Email(this.email.value), new Phone(this.phone.value), new Address(this.address.value), this.id);
        } catch (NoSuchTaskException e) {
            return new Student(new TaskList(new ArrayList<>()), new Name(this.name.fullName),
                    new Email(this.email.value), new Phone(this.phone.value), new Address(this.address.value), this.id);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Student Id", id)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .toString();
    }
}
