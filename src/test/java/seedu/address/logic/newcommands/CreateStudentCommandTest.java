//package seedu.address.logic.newcommands;
//
//import static java.util.Objects.requireNonNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalStudents.ALICE;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.model.id.Id;
//import seedu.address.model.path.RelativePath;
//import seedu.address.model.path.exceptions.InvalidPathException;
//import seedu.address.model.profbook.Group;
//import seedu.address.model.profbook.Student;
//import seedu.address.model.profbook.exceptions.DuplicateChildException;
//import seedu.address.model.profbook.exceptions.NoSuchChildException;
//import seedu.address.model.statemanager.IChildOperation;
//import seedu.address.testutil.StudentBuilder;
//
//class CreateStudentCommandTest {
//    @Test
//    public void constructor_nullPersonNullPath_throwsNullPointerException() { //might need to split to person and path
//        assertThrows(NullPointerException.class, () -> new CreateStudentCommand(null, null));
//    }
//
//        @Test
//        void execute_studentAcceptedByGroup_addSuccessful() throws Exception {
//            Student validStudent = new StudentBuilder().build();
//
//            TaskList taskList = new TaskList(null);
//            Map<Id, Student> students = new HashMap<>();
//            Map<Id, Group> groups =  new HashMap<>();
//            Root root = new Root(taskList, groups);
//            Group group = new Group(taskList, students, new Name("Group001"),new Id("grp-001"));
//            AbsolutePath currPath = new AbsolutePath("~/grp-001");
//            RelativePath path = new RelativePath("~/grp-001");
//
//            GroupOperationStubAlwaysAcceptingStudent groupOperationStub =
//                    new GroupOperationStubAlwaysAcceptingStudent(group, validStudent);
//
//            AbsolutePath absolutePath = currPath.resolve(path);
//            StudentId studentId = absolutePath.getStudentId();
//            groupOperationStub.addChild(studentId, validStudent);
//
//            CreateStudentCommand createStudentCommand = new CreateStudentCommand(path, validStudent);
//            CommandResult commandResult = createStudentCommand.execute(currPath, root);
//
//            assertEquals(String.format(CreateStudentCommand.MESSAGE_SUCCESS, Messages.format(validStudent)),
//                    commandResult.getFeedbackToUser());
//
//            assertTrue(groupOperationStub.duplicate(validStudent));
//        }
//
//    @Test
//        public void execute_duplicateStudent_throwsCommandException()
//          throws InvalidPathException, UnsupportedPathOperationException, CommandException, InvalidIdException {
//        Student validStudent = new StudentBuilder().build();
//        TaskList taskList = new TaskList(null);
//        Map<Id, Student> students = new HashMap<>();
//        Map<Id, Group> groups =  new HashMap<>();
//        Root root = new Root(taskList, groups);
//        Group group = new Group(taskList, students, new Name("Group001"),new Id("grp-001"));
//        AbsolutePath currPath = new AbsolutePath("~/grp-001");
//        RelativePath path = new RelativePath("~/grp-001");
//        CreateStudentCommand createStudentCommand = new CreateStudentCommand(path, validStudent);
//        GroupOperationStub groupOperationStub =
//                new GroupOperationStubWithStudent(group, validStudent);
//        //assertTrue(groupOperationStub.duplicate(validStudent));
//        assertThrows(CommandException.class,
//                CreateStudentCommand.MESSAGE_DUPLICATE_STUDENT,
//                () -> {
//                    createStudentCommand.execute(currPath, root);
//                });
//
//        assertThrows(seedu.address.logic.commands.exceptions.CommandException.class,
//                AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
//        }
//
//    @Test
//    void testEquals() throws InvalidPathException {
//        RelativePath path = new RelativePath("~/grp-001");
//        Student alice = new StudentBuilder()
//                .withName("Alice")
//                .withEmail("alice@example.com")
//                .withPhone("94351253")
//                .withAddress("123, Jurong West Ave 6, #08-111")
//                .withId("stu-001").build();
//        Student bob = new StudentBuilder()
//                .withName("Bob")
//                .withEmail("johnd@example.com")
//                .withPhone("98765432")
//                .withAddress("311, Clementi Ave 2, #02-25")
//                .withTags("owesMoney", "friends")
//                .withId("stu-002").build();
//        CreateStudentCommand createAliceCommand = new CreateStudentCommand(path, alice);
//        CreateStudentCommand createBobCommand = new CreateStudentCommand(path, bob);
//
//        // same object -> returns true
//        assertTrue(createAliceCommand.equals(createAliceCommand));
//
//        // same values -> returns true
//        CreateStudentCommand createAliceCommandCopy = new CreateStudentCommand(path, alice);
//        assertTrue(createAliceCommand.equals(createAliceCommandCopy));
//
//        // different types -> returns false
//        assertFalse(createAliceCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(createAliceCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(createAliceCommand.equals(createBobCommand));
//    }
//
//    @Test
//    void testToString() throws InvalidPathException {
//        RelativePath path = new RelativePath("~/grp-001");
//        CreateStudentCommand createStudentCommand = new CreateStudentCommand(path, ALICE);
//        String expected = CreateStudentCommand.class.getCanonicalName() + "{toCreateStudent=" + ALICE + "}";
//        assertEquals(expected, createStudentCommand.toString());
//    }
//
//    /**
//     * A default groupOperation stub that have all of the methods failing.
//     */
//    private class GroupOperationStub implements IChildOperation<Student> {
//
//        @Override
//        public void addChild(Id id, Student child) throws DuplicateChildException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Student deleteChild(Id id) throws NoSuchChildException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Student getChild(Id id) throws NoSuchChildException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateChild(Id id, Student child) throws NoSuchChildException {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Student[] getAllChildren() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public int numOfChildren() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean duplicate(Student student) {
//            throw new AssertionError("This method should not be called.");
//        }
//    }
//
//    /**
//     * A groupOperation stub that contains a single student.
//     */
//    private class GroupOperationStubWithStudent extends GroupOperationStub {
//        private final Group baseDir;
//        private Student child;
//        public GroupOperationStubWithStudent(Group baseDir, Student child) {
//            this.baseDir = baseDir;
//            this.child = child;
//        }
//
//        @Override
//        public boolean duplicate(Student student) {
//            requireNonNull(student);
//            return true;
//        }
//    }
//
//    /**
//     * A GroupOperation stub that always accept the student being added.
//     */
//    private class GroupOperationStubAlwaysAcceptingStudent extends GroupOperationStub {
//        private final Group baseDir;
//        private Student child;
//        GroupOperationStubAlwaysAcceptingStudent(Group baseDir, Student child) {
//            this.baseDir = baseDir;
//            this.child = child;
//        }
//        @Override
//        public void addChild(Id id, Student child) throws DuplicateChildException {
//            this.baseDir.addChild(id, child);
//        }
//        @Override
//        public boolean duplicate(Student student) {
//            requireNonNull(student);
//            return this.child.equals(student);
//        }
//    }
//}
