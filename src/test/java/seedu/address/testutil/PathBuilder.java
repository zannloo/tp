package seedu.address.testutil;

import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Student;

/**
 * A utility class to help with building AbsolutePath objects.
 */
public class PathBuilder {
    private GroupId group;
    private StudentId student;

    /**
     * Creates a {@code PathBuilder} with group and student as null.
     */
    public PathBuilder() {
        this.group = null;
        this.student = null;
    }

    /**
     * Sets the group of the {@code AbsolutePath} that we are building.
     */
    public PathBuilder withGroup(GroupId groupId) {
        this.group = groupId;
        return this;
    }

    /**
     * Sets the group of the {@code AbsolutePath} that we are building.
     */
    public PathBuilder withGroup(Group group) {
        this.group = group.getId();
        return this;
    }

    /**
     * Sets the student of the {@code StudentPath} that we are building.
     */
    public PathBuilder withStudent(Student student) {
        this.student = student.getId();
        return this;
    }

    /**
     * Returns the complete {@code AbsolutePath}.
     */
    public AbsolutePath build() {
        AbsolutePath completePath = AbsolutePath.ROOT_PATH;
        if (group != null) {
            try {
                RelativePath groupPath = new RelativePath(group.toString());
                completePath = completePath.resolve(groupPath);
            } catch (InvalidPathException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        if (student != null) {
            try {
                RelativePath studentPath = new RelativePath(student.toString());
                completePath = completePath.resolve(studentPath);
            } catch (InvalidPathException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        return completePath;
    }
}
