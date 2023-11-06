package seedu.address.testutil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.ReadOnlyTaskList;
import seedu.address.model.task.TaskListManager;

/**
 * A utility class to help with building Group objects.
 */
public class GroupBuilder {
    public static final String DEFAULT_NAME = "Group ProfBook";
    public static final String DEFAULT_ID = "grp-001";

    private GroupId id;
    private Name name;
    private Map<Id, Student> students;
    private TaskListManager tasks;


    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        id = new GroupId(DEFAULT_ID);
        name = new Name(DEFAULT_NAME);
        students = new HashMap<>();
        tasks = new TaskListManager();
    }

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder(Group toBeCopied) {
        id = toBeCopied.getId();
        name = toBeCopied.getName();
        students = toBeCopied.getChildren();
        tasks = toBeCopied.getTaskListManager();
    }

    /**
     * Sets the {@code Id} of the {@code Group} that we are building.
     */
    public GroupBuilder withId(String id) {
        this.id = new GroupId(id);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Group} that we are building.
     */
    public GroupBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Adds a new {@code Student} to the group we are building.
     */
    public GroupBuilder withStudent(Student student) {
        students.put(student.getId(), student);
        return this;
    }

    /**
     * Adds a list of new {@code Student} to the group we are building.
     */
    public GroupBuilder withStudents(List<Student> students) {
        for (Student student : students) {
            this.students.put(student.getId(), student);
        }
        return this;
    }

    /**
     * Clear the student map.
     */
    public GroupBuilder withNoStudent() {
        this.students = new HashMap<>();
        return this;
    }

    /**
     * Sets the {@code TaskList} of the {@code Group} that we are building.
     */
    public GroupBuilder withTaskList(ReadOnlyTaskList tasks) {
        this.tasks = new TaskListManager(tasks);
        return this;
    }

    public Group build() {
        return new Group(tasks, students, name, id);
    }
}

