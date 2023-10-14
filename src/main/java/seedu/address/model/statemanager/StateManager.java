package seedu.address.model.statemanager;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.exceptions.UnsupportedPathOperationException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.profbook.exceptions.NoSuchChildException;

/**
 * Encapsulates logic manipulating data in ProfBook
 */
public class StateManager {


    /**
     * Creates a ChildOperation class performs operation on the specified root
     *
     * @param root        - The current root instance
     * @return A groupOperation instance specific to current Group
     * @throws UnsupportedPathOperationException If the operation is not supported
     *                                           based on the directory's state.
     * @throws NoSuchChildException              If the path does not lead to a
     *                                           valid childManager
     */
    public static ChildOperation<Group> rootChildOperation(Root root)
            throws UnsupportedPathOperationException, NoSuchChildException {
        return new ChildOperation<>(root);
    }

    /**
     * Creates a ChildOperation class performs operation on the specified group
     *
     * @param root        - The current root instance
     * @param path - The path to the childManager
     * @return A groupOperation instance specific to current Group
     * @throws UnsupportedPathOperationException If the operation is not supported
     *                                           based on the directory's state.
     * @throws NoSuchChildException              If the path does not lead to a
     *                                           valid childManager
     */
    public static ChildOperation<Student> groupChildOperation(Root root, AbsolutePath path)
            throws UnsupportedPathOperationException, NoSuchChildException {
        requireAllNonNull(root, path);
        if (!path.isGroupDirectory()) {
            throw new UnsupportedPathOperationException("Not a group directory");
        }
        return new ChildOperation<>(StateManager.getGroupFromPath(root, path));
    }

    /**
     * Creates a TaskOperation class performs task operation on the specified directory
     *
     * @param root          - The current root instance
     * @param path - The path to the specified student
     * @return A TaskOperation instance specific to current path
     * @throws UnsupportedPathOperationException If the operation is not supported
     *                                           based on the directory's state.
     * @throws NoSuchChildException              If the path does not lead to a
     *                                           valid directory
     */
    public static TaskOperation taskOperation(Root root, AbsolutePath path)
            throws UnsupportedPathOperationException, NoSuchChildException {
        requireAllNonNull(root, path);

        if (path.isRootDirectory()) {
            return new TaskOperation(root);
        } else if (path.isGroupDirectory()) {
            return new TaskOperation(getGroupFromPath(root, path));
        } else if (path.isStudentDirectory()) {
            return new TaskOperation(getStudentFromPath(root, path));
        } else {
            throw new RuntimeException("Not supposed to reach here");
        }
    }

    static Group getGroupFromPath(Root root, AbsolutePath p)
            throws UnsupportedPathOperationException, NoSuchChildException {
        Optional<GroupId> groupIdOptional = p.getGroupId();
        if (groupIdOptional.isPresent()) {
            return root.getChild(groupIdOptional.get());
        }
        throw new UnsupportedPathOperationException("No such group at " + p);
    }

    static Student getStudentFromPath(Root root, AbsolutePath p)
            throws UnsupportedPathOperationException, NoSuchChildException {
        Optional<GroupId> groupIdOptional = p.getGroupId();
        Optional<StudentId> studentIdOptional = p.getStudentId();
        if (studentIdOptional.isPresent() && groupIdOptional.isPresent()) {
            return root.getChild(groupIdOptional.get()).getChild(studentIdOptional.get());
        }
        throw new UnsupportedPathOperationException("No such student at " + p);
    }

}
