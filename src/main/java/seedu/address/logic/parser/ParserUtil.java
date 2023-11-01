package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.Category;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.id.GroupId;
import seedu.address.model.id.StudentId;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.path.RelativePath;
import seedu.address.model.path.exceptions.InvalidPathException;
import seedu.address.model.profbook.Address;
import seedu.address.model.profbook.Email;
import seedu.address.model.profbook.Name;
import seedu.address.model.profbook.Phone;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final DateTimeFormatter DATE_INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_OPTION = "Invalid option: %1$s";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String path} into an {@code RelativePath}.
     *
     * @throws ParseException if the given {@code path} is invalid.
     */
    public static RelativePath parseRelativePath(String path) throws ParseException {
        requireNonNull(path);
        String trimmedPath = path.trim();
        RelativePath relativePath = null;
        try {
            relativePath = new RelativePath(trimmedPath);
        } catch (InvalidPathException e) {
            throw new ParseException(e.getMessage());
        }
        return relativePath;
    }

    /**
     * Resolve the {@code target} against given {@code path}
     * and returns the fullPath.
     *
     * @throws ParseException if there is error when resolving.
     */
    public static AbsolutePath resolvePath(AbsolutePath path, RelativePath target) throws ParseException {
        requireAllNonNull(path, target);
        AbsolutePath fullPath = null;
        try {
            fullPath = path.resolve(target);
        } catch (InvalidPathException e) {
            throw new ParseException(e.getMessage());
        }
        return fullPath;
    }

    /**
     * Parses a {@code RelativePath} to extract a {@code StudentId}.
     *
     * @param path The {@code RelativePath} to parse.
     * @return The extracted {@code StudentId}.
     * @throws ParseException If the path is invalid or doesn't contain a valid {@code StudentId}.
     */
    public static StudentId parseStudentId(AbsolutePath path) throws ParseException {
        requireNonNull(path);
        Optional<StudentId> studentIdOptional = path.getStudentId();

        if (!studentIdOptional.isPresent()) {
            throw new ParseException("No student id found in the path.");
        }

        return studentIdOptional.get();
    }

    /**
     * Parses a {@code String student id} into a {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static StudentId parseStudentId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();

        if (!StudentId.isValidStudentId(trimmedId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }

        return new StudentId(trimmedId);
    }

    /**
     * Parses a {@code RelativePath} to extract a {@code GroupId}.
     *
     * @param path The {@code RelativePath} to parse.
     * @return The extracted {@code GroupId}.
     * @throws ParseException If the path is invalid or doesn't contain a valid {@code GroupId}.
     */
    public static GroupId parseGroupId(AbsolutePath path) throws ParseException {
        requireNonNull(path);
        Optional<GroupId> groupIdOptional = path.getGroupId();

        if (!groupIdOptional.isPresent()) {
            throw new ParseException("No group id found in the path.");
        }

        return groupIdOptional.get();
    }

    /**
     * Parses a {@code String group id} into a {@code GroupId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static GroupId parseGroupId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();

        if (!GroupId.isValidGroupId(trimmedId)) {
            throw new ParseException(GroupId.MESSAGE_CONSTRAINTS);
        }

        return new GroupId(trimmedId);
    }

    /**
     * Parses a {@code String dateTime} into a {@code LocalDateTime}.
     *
     * @param dateTimeStr The dateTime string.
     * @throws ParseException if the given {@code path} is invalid.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) throws ParseException {
        requireNonNull(dateTimeStr);
        String trimmedDateTimeStr = dateTimeStr.trim();

        LocalDateTime dateTime = null;
        try {
            dateTime = LocalDateTime.parse(trimmedDateTimeStr, DATE_INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException(e.getMessage());
        }

        return dateTime;
    }

    /**
     * Parses a {@code String cat} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code cat} is invalid.
     */
    public static Category parseCategory(String cat) throws ParseException {
        requireNonNull(cat);
        String trimmedCat = cat.trim();

        if (trimmedCat.equals("allStu")) {
            return Category.ALLSTU;
        }

        if (trimmedCat.equals("allGrp")) {
            return Category.ALLGRP;
        }

        throw new ParseException("Format is invalid. Should be allStu or allGrp");
    }

    /**
     * Checks if all options in {@code argString} are valid.
     */
    public static void verifyAllOptionsValid(String args, Option... options) throws ParseException {
        Set<String> allOptions = ArgumentTokenizer.extractAllOptionNames(args);
        for (String optionName : allOptions) {
            Stream<Option> optionStream = Arrays.stream(options);
            Predicate<Option> pred = option
                -> optionName.equals(option.getLongName()) || optionName.equals(option.getShortName());
            if (optionStream.noneMatch(pred)) {
                throw new ParseException(String.format(MESSAGE_INVALID_OPTION, optionName));
            }
        }
    }

    /**
     * Returns true if none of the options contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean areOptionsPresent(ArgumentMultimap argumentMultimap, Option... options) {
        return Stream.of(options).allMatch(option -> isOptionPresent(argumentMultimap, option));
    }

    public static boolean isOptionPresent(ArgumentMultimap argumentMultimap, Option option) {
        return argumentMultimap.getValue(option).isPresent();
    }

}
