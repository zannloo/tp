package seedu.address.model.profbook;

import seedu.address.model.taskmanager.TaskList;

import java.util.ArrayList;
import java.util.List;

public class Children<T> extends TaskListable {

    private final List<T> children;
    public Children(TaskList taskList, List<T> children) {
        super(taskList);
        this.children = children;
    }

    public void addChild(T child) {
        this.children.add(child);
    }

    public void deleteChild(Id id) {
        for (T child: this.children) {

        }
    }





}
