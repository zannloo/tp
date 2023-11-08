package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.task.Deadline;
import seedu.address.model.task.ReadOnlyTaskList;
import seedu.address.model.task.TaskListManager;
import seedu.address.model.task.ToDo;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Deadline DEADLINE_1 = new Deadline(
            "Assignment 1", LocalDateTime.parse("2023-11-15T10:00:00"));
    public static final Deadline DEADLINE_2 = new Deadline(
            "Assignment 2", LocalDateTime.parse("2023-11-20T15:30:00"));
    public static final Deadline DEADLINE_3 = new Deadline(
            "Project Presentation", LocalDateTime.parse("2023-12-05T14:00:00"));
    public static final Deadline DEADLINE_4 = new Deadline(
            "Essay Submission", LocalDateTime.parse("2023-12-10T12:00:00"));
    public static final Deadline DEADLINE_5 = new Deadline(
            "Final Exam", LocalDateTime.parse("2023-12-15T09:00:00"));
    public static final Deadline DEADLINE_6 = new Deadline(
            "Lab Report", LocalDateTime.parse("2023-12-20T16:30:00"));
    public static final ToDo TODO_1 = new ToDo("Quiz 1");
    public static final ToDo TODO_2 = new ToDo("Homework 2");
    public static final ToDo TODO_3 = new ToDo("Research Paper");
    public static final ToDo TODO_4 = new ToDo("Presentation Practice");
    public static final ToDo TODO_5 = new ToDo("Read Chapter 7");
    public static final ToDo TODO_6 = new ToDo("Group Meeting Preparation");

    public static final ReadOnlyTaskList TASK_LIST_1 = new TaskListManager(
            new ArrayList<>(Arrays.asList(DEADLINE_1, DEADLINE_2, DEADLINE_3, TODO_1, TODO_2, TODO_3)));
    public static final ReadOnlyTaskList TASK_LIST_2 = new TaskListManager(
            new ArrayList<>(Arrays.asList(DEADLINE_4, DEADLINE_5, DEADLINE_6, TODO_4, TODO_5, TODO_6)));

    private TypicalTasks() {} // prevents instantiation
}
