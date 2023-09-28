package seedu.address.model.profbook;

import seedu.address.model.taskmanager.NoSuchTaskException;
import seedu.address.model.taskmanager.Task;
import seedu.address.model.taskmanager.TaskList;

import java.util.List;

public class TaskListable {

     private final TaskList taskList;

     public TaskListable() {
          // TODO: change when there is a proper class
          this.taskList = new Tmp();
     }

     public  TaskListable(TaskList taskList) {
          this.taskList = taskList;
     }
     public void addTask(Task t) {
          this.taskList.add(t);
     }

     public Task deleteTask(int index) throws NoSuchTaskException {
          return this.taskList.delete(index);
     }
     public Task markTask(int index) throws NoSuchTaskException {
          return this.taskList.mark(index);
     }
     public Task unmarkTask(int index) throws NoSuchTaskException {
          return this.taskList.mark(index);
     }
     public List<Task> findTask(String query) {
          return this.taskList.find(query);
     }
     public Task getTask(int index) throws NoSuchTaskException {
          return this.taskList.get(index);
     }
     public List<Task> getAllTask() {
          return this.taskList.getAllTask();
     }

}
