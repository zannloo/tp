package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.model.path.AbsolutePath.ROOT_PATH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.GROUP_ONE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPaths.PATH_TO_ALICE;
import static seedu.address.testutil.TypicalPaths.PATH_TO_GROUP_ONE;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalTasks.DEADLINE_1;
import static seedu.address.testutil.TypicalTasks.TODO_1;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Category;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseRelativePath_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRelativePath(null));
    }

    @Test
    public void parseRelativePath_invalidPath_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRelativePath("invalidPath"));
    }

    @Test
    public void parseRelativePath_validPath_returnsRelativePath() throws InvalidPathException, ParseException {
        RelativePath expectedPath = new RelativePath(PATH_TO_GROUP_ONE.toString());
        assertEquals(expectedPath, ParserUtil.parseRelativePath(PATH_TO_GROUP_ONE.toString()));
    }

    @Test
    public void resolvePath_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.resolvePath(ROOT_PATH, null));
        assertThrows(NullPointerException.class, () -> ParserUtil.resolvePath(null,
                CommandTestUtil.getValidGroupRelativePath().toString()));
        assertThrows(NullPointerException.class, () -> ParserUtil.resolvePath(null, null));
    }

    @Test
    public void resolvePath_invalidPath_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.resolvePath(ROOT_PATH, RelativePath.PARENT.toString()));
    }

    @Test
    public void resolvePath_validPath_returnsResolvedPath() throws InvalidPathException, ParseException {
        RelativePath target = new RelativePath(GROUP_ONE.getId().toString());
        assertEquals(PATH_TO_GROUP_ONE, ParserUtil.resolvePath(ROOT_PATH, target.toString()));
    }

    @Test
    public void parseStudentId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentId((AbsolutePath) null));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentId((String) null));
    }

    @Test
    public void parseStudentId_invalidPath_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentId(ROOT_PATH));
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentId(PATH_TO_GROUP_ONE));
    }

    @Test
    public void parseStudentId_validPath_returnsStudentId() throws ParseException {
        assertEquals(ALICE.getId(), ParserUtil.parseStudentId(PATH_TO_ALICE));
    }

    @Test
    public void parseStudentId_invalidIdString_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentId("invalidId"));
    }

    @Test
    public void parseStudentId_validIdString_returnsStudentId() throws ParseException {
        assertEquals(ALICE.getId(), ParserUtil.parseStudentId(ALICE.getId().toString()));
    }

    @Test
    public void parseGroupId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroupId((AbsolutePath) null));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroupId((String) null));
    }

    @Test
    public void parseGroupId_invalidPath_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroupId(ROOT_PATH));
    }

    @Test
    public void parseGroupId_validPath_returnsGroupId() throws ParseException {
        assertEquals(GROUP_ONE.getId(), ParserUtil.parseGroupId(PATH_TO_ALICE));
        assertEquals(GROUP_ONE.getId(), ParserUtil.parseGroupId(PATH_TO_GROUP_ONE));
    }

    @Test
    public void parseGroupId_invalidIdString_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroupId("invalidId"));
    }

    @Test
    public void parseGroupId_validIdString_returnsGroupId() throws ParseException {
        assertEquals(GROUP_ONE.getId(), ParserUtil.parseGroupId(GROUP_ONE.getId().toString()));
    }

    @Test
    public void parseDateTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDateTime(null));
    }

    @Test
    public void parseDateTime_invalidFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime("2023-09-22"));
    }

    @Test
    public void parseDateTime_invalidDateTime_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime("2023-02-31 11:59"));
    }

    @Test
    public void parseDateTime_validDateTime_returnsDateTime() throws ParseException {
        assertTrue(ParserUtil.parseDateTime("2023-11-06 15:42") instanceof LocalDateTime);
    }

    @Test
    public void parseCategory_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCategory(null));
    }

    @Test
    public void parseCategory_invalidCategory_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCategory("Invalid Category"));
    }

    @Test
    public void parseCategory_validCategory_returnsCategory() throws ParseException {
        assertEquals(Category.ALLGRP, ParserUtil.parseCategory("allGrp"));
        assertEquals(Category.ALLSTU, ParserUtil.parseCategory("allStu"));
    }

    @Test
    public void parseToDo_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseToDo(null));
    }

    @Test
    public void parseToDo_validArg_returnsToDo() throws ParseException {
        assertEquals(TODO_1, ParserUtil.parseToDo(TODO_1.getDesc()));
    }

    @Test
    public void parseDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadline(null, null));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadline(DEADLINE_1.getDesc(), null));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadline(null, DEADLINE_1.formatDueBy()));
    }

    @Test
    public void parseDeadline_validArg_returnsDeadline() throws ParseException {
        assertEquals(DEADLINE_1, ParserUtil.parseDeadline(DEADLINE_1.getDesc(), DEADLINE_1.formatDueBy()));
    }
}
