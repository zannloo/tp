package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.id.StudentId;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;
import seedu.address.model.task.ToDo;

/**
 * Jackson-friendly version of {@link Student}.
 */
public class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String email;
    private final String phone;

    // Data fields
    private final String address;
    private final String id;

    private final Set<JsonAdaptedTasks> tasks = new HashSet<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("id") String id, @JsonProperty("tasks") List<JsonAdaptedTasks> tasks) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.id = id;
        if (tasks != null) {
            this.tasks.addAll(tasks);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     *
     * @param source The source Student object.
     */
    public JsonAdaptedStudent(Student source) {
        this.name = source.getName().toString();
        this.phone = source.getPhone().toString();
        this.email = source.getEmail().toString();
        this.address = source.getAddress().toString();
        this.id = source.getId().toString();
        tasks.addAll(source.getAllTask().stream()
                .map(task -> (task instanceof ToDo)
                        ? new JsonAdaptedToDo((ToDo) task)
                        : new JsonAdaptedDeadline((Deadline) task))
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's Student object.
     *
     * @return The Student object that corresponds to this JsonAdaptedStudent.
     * @throws IllegalValueException If there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Task> taskList = new ArrayList<>();

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

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    seedu.address.model.profbook.Phone.class.getSimpleName()));
        }
        if (!seedu.address.model.profbook.Phone.isValidPhone(phone)) {
            throw new IllegalValueException(seedu.address.model.profbook.Phone.MESSAGE_CONSTRAINTS);
        }
        final seedu.address.model.profbook.Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    seedu.address.model.profbook.Email.class.getSimpleName()));
        }
        if (!seedu.address.model.profbook.Email.isValidEmail(email)) {
            throw new IllegalValueException(seedu.address.model.profbook.Email.MESSAGE_CONSTRAINTS);
        }
        final seedu.address.model.profbook.Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    seedu.address.model.profbook.Address.class.getSimpleName()));
        }
        if (!seedu.address.model.profbook.Address.isValidAddress(address)) {
            throw new IllegalValueException(seedu.address.model.profbook.Address.MESSAGE_CONSTRAINTS);
        }
        final seedu.address.model.profbook.Address modelAddress = new Address(address);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    seedu.address.model.id.StudentId.class.getSimpleName()));
        }
        if (!seedu.address.model.id.StudentId.isValidStudentId(id)) {
            throw new IllegalValueException(seedu.address.model.id.StudentId.MESSAGE_CONSTRAINTS);
        }
        final seedu.address.model.id.StudentId studId = new StudentId(id);

        final seedu.address.model.task.TaskList modelTList = new TaskList(taskList);

        return new Student(modelTList, modelName, modelEmail, modelPhone, modelAddress, studId);
    }

}
