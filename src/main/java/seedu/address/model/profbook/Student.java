package seedu.address.model.profbook;

import seedu.address.model.taskmanager.TaskList;

/**
 * Encapsulates logic for a student's data
 */
public class Student extends ProfBookModel implements IChildElement {

    // Identity field
    // TODO: Add more
    /**
     * Name of the group
     */
    private final Name name;

    /**
     * Unique identifier of the group
     */
    private final Id id;

    /**
     * Constructs a new Student instance
     *
     * @param taskList - The task list associated with this student
     * @param name     - The group name
     * @param id       - Unique identifier of the group
     */

    public Student(TaskList taskList, Name name, Id id) {
        super((taskList));
        this.name = name;
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public Id getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", id=" + id + '}';
    }

    /**
     * Returns true if child element has same id as param id
     *
     * @param id - Id to compare to
     * @return true if it is equals using ID's equals method
     */
    @Override
    public boolean isSameAs(Id id) {
        return this.id.equals(id);
    }
}
