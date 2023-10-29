package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.ReadOnlyTaskList;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Group objects.
 */
public class GroupBuilder {
    public static final Task DEFAULT_TASK = new Deadline("Assignment 1", LocalDateTime.parse("2023-12-03T23:59"));
    public static final String DEFAULT_NAME = "Group ProfBook";
    public static final String DEFAULT_ID = "grp-001";

    private ReadOnlyTaskList taskList;
    private Map<Id, Student> students;
    private GroupId id;
    private Name name;

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        List<Task> defaultTaskList = new ArrayList<>();
        defaultTaskList.add(DEFAULT_TASK);
        taskList = new ReadOnlyTaskList(defaultTaskList);
        students = new HashMap<>();
        Student stu1 = new StudentBuilder().build();
        Student stu2 = new StudentBuilder()
                .withName("Bob")
                .withEmail("johnd@example.com")
                .withPhone("98765432")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withId("0010Y").build();
        students.put(stu1.getId(), stu1);
        students.put(stu2.getId(), stu2);
        name = new Name(DEFAULT_NAME);
        id = new GroupId(DEFAULT_ID);
    }

    public Group build() {
        return new Group(taskList, students, name, id);
    }

    /**
     * Builds a Group object with an empty task list using the current details of this {@code GroupBuilder}.
     *
     * @return a new Group object
     */
    public Group buildEmptyTask() {
        List<Task> emptyList = new ArrayList<>();
        ReadOnlyTaskList emptyTaskList = new ReadOnlyTaskList(emptyList);
        return new Group(emptyTaskList, students, name, id);
    }

    /**
     * Builds a Group object with an empty student list using the current details of this {@code GroupBuilder}.
     *
     * @return a new Group object
     */
    public Group buildWithEmptyStudent() {
        Map<Id, Student> emptyStudent = new HashMap<>();
        return new Group(taskList, emptyStudent, name, id);
    }
}

