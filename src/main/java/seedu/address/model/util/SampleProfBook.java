package seedu.address.model.util;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;
import seedu.address.model.profbook.Root;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.Task;
import seedu.address.model.task.ToDo;

/**
 * Sample ProfBook data.
 */
public class SampleProfBook {
    public static Student[] getSampleStudents1() {
        return new Student[] {
            new Student(new Name("Tejas"), new Email("tejas@gmail.com"), new Phone("67352798"),
                new Address("170 Ghim Moh Road Ncss Centre"), new StudentId("0001Y")),
            new Student(new Name("Jackie"), new Email("jackie@gmail.com"), new Phone("65341488"),
                new Address("1 Park Road 03-K78 People's Park Complex"), new StudentId("0002Y")),
            new Student(new Name("Raman"), new Email("raman@gmail.com"), new Phone("64755838"),
                new Address("3Rd Floor, 80 Raffles Place, Uob Plaza 2"), new StudentId("0003Y")),
            new Student(new Name("Yarn Meng"), new Email("yarnmeng@gmail.com"), new Phone("65467742"),
                new Address("480 Lorong 6 Toa Payoh Atrium 4th Storey HDB Hub"), new StudentId("0004Y")),
            new Student(new Name("Kiat Win"), new Email("kiatwin@gmail.com"), new Phone("62102572"),
                new Address("4 Loyang Walk Loyang Industrial Estate"), new StudentId("0005Y")),
        };
    }

    public static Student[] getSampleStudents2() {
        return new Student[] {
            new Student(new Name("Ming Yuan"), new Email("mingyuan@gmail.com"), new Phone("64380137"),
                new Address("Woodlands, Malaysia"), new StudentId("0006Y")),
            new Student(new Name("Zann"), new Email("zann@gmail.com"), new Phone("62924352"),
                new Address("Somewhere in bedok"), new StudentId("0007Y")),
            new Student(new Name("Nereus"), new Email("nereus@gmail.com"), new Phone("63365480"),
                new Address("Muar, Johor, Malaysia"), new StudentId("0008Y")),
            new Student(new Name("Gary"), new Email("gary@gmail.com"), new Phone("67792128"),
                new Address("Somewhere in Malaysia"), new StudentId("0009Y")),
            new Student(new Name("Nidhish"), new Email("nidhish@gmail.com"), new Phone("63480398"),
                new Address("Somewhere in Sinagpore"), new StudentId("0010Y")),
        };
    }

    public static List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            tasks.add(new ToDo("Task " + i));
        }

        for (int i = 0; i < 5; i++) {
            ToDo todo = new ToDo("Task " + (i + 5));
            todo.mark();
            tasks.add(todo);
        }

        return tasks;
    }

    public static Group getGroup1() {
        Group grp = new Group(new Name("Group One"), new GroupId("grp-001"));
        Student[] students = getSampleStudents1();
        for (Student s : students) {
            grp.addChild(s.getId(), s);
        }
        return grp;
    }

    public static Group getGroup2() {
        Group grp = new Group(new Name("Best Group in CS2103T"), new GroupId("grp-002"));
        Student[] students = getSampleStudents2();
        for (Student s : students) {
            grp.addChild(s.getId(), s);
        }
        List<Task> tasks = getTasks();
        for (Task task : tasks) {
            grp.addTask(task);
        }
        return grp;
    }

    public static Root getRoot() {
        Root root = new Root();
        Group grp1 = getGroup1();
        Group grp2 = getGroup2();
        root.addChild(grp1.getId(), grp1);
        root.addChild(grp2.getId(), grp2);
        return root;
    }
}
