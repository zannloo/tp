package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.id.StudentId;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Student;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.TaskList;


public class JsonAdaptedGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";
    /**
     * Name of the group
     */
    private final String name;

    /**
     * Unique identifier of the group
     */
    private final String id;

    private final Set<JsonAdaptedStudent> students = new HashSet<>();
    private final Set<JsonAdaptedTasks> tasks = new HashSet<>();

    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("name") String name, @JsonProperty("id") String id,
                            @JsonProperty("students") List<JsonAdaptedStudent> students,
                            @JsonProperty("tasks") List<JsonAdaptedTasks> tasks) {
        this.name = name;
        this.id = id;
        if (students!= null) {
            this.students.addAll(students);
        }
        if (tasks!= null) {
            this.tasks.addAll(tasks);
        }

    }

    public Group toModelType() throws IllegalValueException {
        // Convert the list of JsonAdaptedStudent back into a map of students.
        final List<Task> taskList = new ArrayList<>();

        Map<Id, Student> studentMap = new HashMap<>();
        for (JsonAdaptedStudent studJson : students) {
            Student student = studJson.toModelType();
            studentMap.put(student.getId(), student);
        }
        for (JsonAdaptedTasks task : tasks) {

            taskList.add(task.toModelType());
        }
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    seedu.address.model.profbook.Name.class.getSimpleName()));
        }
        if (!seedu.address.model.profbook.Name.isValidName(name)) {
            throw new IllegalValueException(seedu.address.model.profbook.Name.MESSAGE_CONSTRAINTS);
        }
        final seedu.address.model.profbook.Name modelName = new Name(name);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    seedu.address.model.id.StudentId.class.getSimpleName()));
        }
        if (!seedu.address.model.id.StudentId.isValidStudentId(id)) {
            throw new IllegalValueException(seedu.address.model.id.StudentId.MESSAGE_CONSTRAINTS);
        }
        final seedu.address.model.id.Id GrpId = new GroupId(id);

        final seedu.address.model.taskmanager.TaskList modelTList = new TaskList(taskList);

        return new Group(modelTList,studentMap, modelName, GrpId);
    }
}