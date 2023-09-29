package seedu.address.model.statemanager;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.id.exceptions.InvalidIdException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.profbook.TaskListManager;
import seedu.address.model.profbook.exceptions.NoSuchChildException;
import seedu.address.model.taskmanager.NoSuchTaskException;
import seedu.address.model.taskmanager.Task;

/**
 * Encapsulates logic manipulating data in ProfBook
 */
public class StateManager implements ITaskOperations {

    private final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private final TaskListManager baseDir;

    /**
     * Constructs a new stateManager instance
     *
     * @param baseDir - The current base directory of the state Manager
     */
    StateManager(TaskListManager baseDir) {
        this.baseDir = baseDir;
    }

    /**
     * Creates a rootOperation class performs operation on the root
     *
     * @param root - The current root instance
     * @return A rootOperation instance specific to current root
     */
    public static RootOperation rootOperation(Root root) {
        return new RootOperation(root);
    }

    /**
     * Creates a GroupOperation class performs operation on the specified group
     *
     * @param root        - The current root instance
     * @param pathToGroup - The path to the specified group
     * @return A groupOperation instance specific to current Group
     * @throws UnsupportedPathOperationException If the operation is not supported
     *                                           based on the directory's state.
     * @throws InvalidIdException                If the retrieved ID is invalid.
     * @throws NoSuchChildException              If the path does not lead to a
     *                                           valid group
     */
    public static GroupOperation groupOperation(Root root, AbsolutePath pathToGroup)
            throws UnsupportedPathOperationException, InvalidIdException, NoSuchChildException {
        Group currDir = StateManager.getGroupFromPath(root, pathToGroup);
        return new GroupOperation(currDir);
    }

    /**
     * Creates a StudentOperation class performs operation on the specified Student
     *
     * @param root          - The current root instance
     * @param pathToStudent - The path to the specified student
     * @return A StudentOperation instance specific to current Stident
     * @throws UnsupportedPathOperationException If the operation is not supported
     *                                           based on the directory's state.
     * @throws InvalidIdException                If the retrieved ID is invalid.
     * @throws NoSuchChildException              If the path does not lead to a
     *                                           valid student
     */
    public static StudentOperation studentOperation(Root root, AbsolutePath pathToStudent)
            throws UnsupportedPathOperationException, InvalidIdException, NoSuchChildException {
        Student currDir = StateManager.getStudentFromPath(root, pathToStudent);
        return new StudentOperation(currDir);
    }

    private static Group getGroupFromPath(Root root, AbsolutePath p) throws UnsupportedPathOperationException,
            InvalidIdException, NoSuchChildException {
        if (p.isGroupDirectory()) {
            return root.getChild(p.getGroupId());
        }
        throw new UnsupportedPathOperationException("No such group at " + p);
    }

    private static Student getStudentFromPath(Root root, AbsolutePath p)
            throws UnsupportedPathOperationException, InvalidIdException, NoSuchChildException {
        if (p.isStudentDirectory()) {
            return root.getChild(p.getGroupId()).getChild(p.getStudentId());
        }
        throw new UnsupportedPathOperationException("No such Student at " + p);
    }

    void stateLogger(String log) {
        this.logger.info(log);
    }

    void stateErrorLogger(String errMsg) {
        this.logger.severe(errMsg);
    }

    /**
     * Adds a new tasks to the task list
     *
     * @param t
     */
    @Override
    public void addTask(Task t) {
        this.baseDir.addTask(t);
    }

    /**
     * Deletes the task at the specified index
     *
     * @param index - The index of the targeted class
     * @return The deleted class
     * @throws NoSuchTaskException if no task can be found by the index
     */
    @Override
    public Task deleteTask(int index) throws NoSuchTaskException {
        return this.baseDir.deleteTask(index);
    }

    /**
     * Marks the task at the specified index as completed
     *
     * @param index - The index of the targeted class
     * @return The marked task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    @Override
    public Task markTask(int index) throws NoSuchTaskException {
        return this.baseDir.markTask(index);
    }

    /**
     * Marks the task at the specified index as not completed
     *
     * @param index - The index of the targeted class
     * @return The un-marked task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    @Override
    public Task unmarkTask(int index) throws NoSuchTaskException {
        return this.baseDir.unmarkTask(index);
    }

    /**
     * Finds all matching task, compares by the task's description
     *
     * @param query - The String to match
     * @return A list of all matching Tasks
     */
    @Override
    public List<Task> findTask(String query) {
        return this.baseDir.findTask(query);
    }

    /**
     * Returns the task at the specified index
     *
     * @param index - The index of the targeted class
     * @return The specified task
     * @throws NoSuchTaskException if no task can be found by the index
     */
    @Override
    public Task getTask(int index) throws NoSuchTaskException {
        return this.baseDir.getTask(index);
    }

    /**
     * Returns all current task
     *
     * @return A list of all Tasks
     */
    @Override
    public List<Task> getAllTask() {
        return this.baseDir.getAllTask();
    }
}
