package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.profbook.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {
    // Group 1
    public static final Student ALICE = new StudentBuilder()
            .withId("0001Y").withName("Alice Pauline").withPhone("94351253")
            .withEmail("alice@example.com").withAddress("123, Jurong West Ave 6, #08-111").build();
    public static final Student BENSON = new StudentBuilder()
            .withId("0002Y").withName("Benson Meier").withPhone("98765432")
            .withEmail("johnd@example.com").withAddress("311, Clementi Ave 2, #02-25").build();
    public static final Student CARL = new StudentBuilder()
            .withId("0003Y").withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Student DANIEL = new StudentBuilder()
            .withId("0004Y").withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").build();
    public static final Student ELLE = new StudentBuilder()
            .withId("0005Y").withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    // Group 2
    public static final Student FIONA = new StudentBuilder()
            .withId("0006Y").withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Student GEORGE = new StudentBuilder()
            .withId("0007Y").withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();
    public static final Student HOON = new StudentBuilder()
            .withId("0008Y").withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Student IDA = new StudentBuilder()
            .withId("0009Y").withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();
    public static final Student JACK = new StudentBuilder()
            .withId("0010Y").withName("Jack").withPhone("98765432")
            .withEmail("jack@example.com").withAddress("johor").build();
    // Extra
    public static final Student KAREN = new StudentBuilder()
            .withId("0011Y").withName("Karen Adams").withPhone("98765433")
            .withEmail("karen@example.com").withAddress("willow lane").build();
    public static final Student LEO = new StudentBuilder()
            .withId("0012Y").withName("Leo Brown").withPhone("98765434")
            .withEmail("leo@example.com").withAddress("oak street").build();
    public static final Student MAYA = new StudentBuilder()
            .withId("0013Y").withName("Maya Carter").withPhone("98765435")
            .withEmail("maya@example.com").withAddress("elm road").build();
    public static final Student NATHAN = new StudentBuilder()
            .withId("0014Y").withName("Nathan Davis").withPhone("98765436")
            .withEmail("nathan@example.com").withAddress("maple avenue").build();
    public static final Student OLIVIA = new StudentBuilder()
            .withId("0015Y").withName("Olivia Edwards").withPhone("98765437")
            .withEmail("olivia@example.com").withAddress("cedar street").build();

    public static final List<Student> STUDENT_LIST_1 =
            new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE));

    public static final List<Student> STUDENT_LIST_2 =
            new ArrayList<>(Arrays.asList(FIONA, GEORGE, HOON, IDA, JACK));

    private TypicalStudents() {} // prevents instantiation
}
