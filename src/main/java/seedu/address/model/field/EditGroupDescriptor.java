package seedu.address.model.field;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.Id;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.ReadOnlyTaskList;
import seedu.address.model.task.TaskListManager;

/**
 * Represents the descriptor for editing the details of a group in ProfBook. The descriptor contains fields for
 * updating the group's name and id. It helps to track which fields have been edited by the user.
 * An instance of this class is used within the {@code EditCommand} to specify the details to be edited.
 */
public class EditGroupDescriptor implements EditDescriptor<Group> {

    private Name name;

    private GroupId id;

    /**
     *  Default constructor for creating an empty EditGroupDescriptor.
     */
    public EditGroupDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditGroupDescriptor(EditGroupDescriptor toCopy) {
        setName(toCopy.name);
        setId(toCopy.id);
    }

    /**
     * Checks if any field in the descriptor has been edited.
     *
     * @return True if any field is edited, false otherwise.
     */
    @Override
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, id);
    }

    /**
     * Sets the name field in the descriptor.
     *
     * @param name The new name to set.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Gets the name field from the descriptor.
     *
     * @return An Optional containing the name if it's set, empty otherwise.
     */
    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    /**
     * Sets the id field in the descriptor.
     *
     * @param id The new id to set.
     */
    public void setId(GroupId id) {
        this.id = id;
    }

    /**
     * Gets the id field from the descriptor.
     *
     * @return An Optional containing the id if it's set, empty otherwise.
     */
    public Optional<GroupId> getId() {
        return Optional.ofNullable(id);
    }

    /**
     * Applies the edits specified in the descriptor to the old group.
     *
     * @param oldGroup The old group to be edited.
     * @return A new group with the applied edits.
     */
    @Override
    public Group applyEditsToOld(Group oldGroup) {
        assert oldGroup != null;

        Name updatedName = getName().orElse(oldGroup.getName());
        GroupId updatedId = getId().orElse(oldGroup.getId());
        ReadOnlyTaskList taskList = new TaskListManager(oldGroup.getAllTasks());
        Map<Id, Student> students = oldGroup.getChildren();

        return new Group(taskList, students, updatedName, updatedId);
    }

    /**
     * Checks if this EditStudentDescriptor is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditGroupDescriptor)) {
            return false;
        }

        EditGroupDescriptor otherEditGroupDescriptor = (EditGroupDescriptor) other;

        return Objects.equals(this.name, otherEditGroupDescriptor.name)
                && Objects.equals(this.id, otherEditGroupDescriptor.id);
    }

    /**
     * Returns the string representation of this EditStudentDescriptor.
     *
     * @return The string representation of this object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("id", id)
                .toString();
    }
}
