---
layout: page
title: User Guide
---

# ProfBook

## Overview

ProfBook is a **student management system** specially made for **CS2103T tutors**, built by CS2103T students. As current students of CS2103T, we understand that CS2103T tutors have a high administrative workload on top of their existing tutor duties. On top of that, any administrative mistakes or oversights can increase that workload exponentially and may adversely impact student's learning. To address the aforementioned issues, Profbook was designed as a student management system which can **dramatically decrease administrative mistakes and workload**.

It is optimized for tutors use via a **familiar Command Line Interface (CLI) that uses linux-styled commands** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ProfBook can track and manage your student's progress and tasks much faster than traditional GUI apps with a gentle learning curve.

## Features overview

### Consolidated Information

As a project based module, CS2103T tutors have to juggle multiple groups **within** multiple tutorial slots. ProfBook aims to expedite this process by allowing tutors to keep track of all their tutorial groups and all the project groups within that in a centralised location. Tutors are able to traverse between tutorial slots and groups quickly through familiar linux commands.

#### Student Information Management

ProfBook aids with the **management of student information**. Student's information can be **readily** added, edited, deleted quickly. In addition, ProfBook supports the managment of additional information vital for a tutor. Tutors can track their student progress through creating various tasks such as Todo and Deadline. Furthermore, Tutors can store short description of a student or links to their GitHub Repository if desired.

#### Tutorial and Groups Information Management

ProfBook aids with the **management of Tutorial and Groups information**. Profbook efficiently encapsulates information of every tutorial group and every project group so that tutors can management their progress and relevant information **seamlessly**. Tutors can easily add, move, delete students from groups. Similiar to students, tutors are able to track each group progress through tasks. In addition, Tutors can store short description of the groups or links to their GitHub Repository if desired.

- Table of Contents
  {:toc}

---

## Quick start (//TODO Change)

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ProfBook.jar` from [here](https://github.com/se-edu/ProfBook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ProfBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ProfBook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - `list` : Lists all contacts.

   - `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   - `delete 3` : Deletes the 3rd contact shown in the current list.

   - `clear` : Deletes all contacts.

   - `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

(//TODO Change)

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

---

# General Commands

### Viewing help : `help` (//TODO Update)

Shows a message explaning how to access the help page.

// TODO Update
![help message](images/helpMessage.png)

Format: `help`

### Listing all persons : `ls`

Shows a list of all persons in the address book.

Format: `ls`

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

---

# Student/Group commands

### Adding a student: `touch`

Adds a student to the address book.

Format: `touch student -n [name] -id [StudentId]`

Acceptable values for each parameter:
name:

- must be a non-empty string

StudentId:

- must be a non-empty string starting with stu-

Output if command fails

- pop up message indicating non valid param/ invalid site of student creation

Output if command succeeds

- pop up message indicating successful creation together with created student with student id

(//TODO Update)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
`touch student -n Gary -id stu-1234`

- Command creates a student within the tutorial group if command is used from within the tutorial group.
- If a command is executed outside of a specific group, students are added into an ungrouped folder and can be moved into a group later.

### Deleting a Student : `rm`

Delete task, student or group.

Format: `rm -type [targetType] -target [targetId]`

Acceptable values for each parameter:
type:

- "student", "task" or "group"

target:

- A valid non-empty String

Output if command fails

- pop up message indicate error when remove target

Output if command succeeds

- pop up message indicate target successfully removed

Examples:
`rm -type student -target stu-123`

- This command will delete the student profile with id stu-123

### Move students into/out of the group: `mv`

Moves student from a group to another group

Format `mv [StudentID] -source [source group]  -dest [destination group]`

Acceptable values for each parameter:
StudentID:

- must be a non-empty string starting with stu-

source group:

- must be a valid non-empty string starting with grp-

destination group:

- must be a valid non-empty string starting with grp-

Output if command fails

- pop up message indicate error when moving student with id stu-123 from grp-1 to grp-2

Output if command succeeds

- pop up message indicates successfully moving student with id stu-123 from grp-1 to grp-2

Examples:
`mv stu-123 -source grp-1 -dest grp-2`

- This command will move a student with value id stu-123 within the groups or from Ungroup to Group.

### Create Group : `mkdir`

Creates a group consists of a maximum of five students

Format: `mkdir [groupId]`

Acceptable values for each parameter:
groupId:

- groupId must be a non-empty string starting with grp-

Output if command fails

- pop up message indicates error when creating new group

Output if command succeeds

- pop up message indicates group with specific groupId was created successfully.

Examples:
`mkdir grp-1`

- This command will create a group with groupId which is a string called 1 and only consists the creator at that instance of creating the group

---

# Tasks command

### Create Todo Task : `todo`

Creates todo tasks for specific students or groups.

Format: `todo -desc [task] -level [student/group] -target [StudentID/group Id/tutorialId]`
-desc / -d : Description of the todo task
-level / -l : The level of the task (student or group)
-target / -t : The target id (StudentID/groupId/tutorialId)

Acceptable values for each parameter:
Task:

- Non empty string

Level:

- the level only can be lower than current level
  -- E.g., if my current level is group (i.e I am in a specific group), I only can create a task for the group or a student
- If a -level flag is not present, the app will by default create task for current level
  -- E.g., command “todo -d ps1” will create todo for current level (specific group or student)

Target:

- Need to specify a valid id for indicated level
  -- E.g., Need to specify a valid student id if want to create a student level task
- If -target flag is not present, app will by default create task for every items under current level
  -- E.g, command “todo -d ps1 -level student” will create todo “ps1” for every students under current group
- Target flag is allowed only if level flag is provided

Output if command fails

- pop up message indicate error when creating new todo.

Output if command succeeds

- pop up message indicate todo created successfully

Examples:
`todo -desc ps1 -level student -target stu-123`

- This command will create a todo task called “ps 1” for the student with id stu-123

### Create Deadline task : `Deadline`

Creates task with a deadline for specific student or group or tutorial slot

Format `deadline -desc [task] -level [student/group] -target [StudentID/groupId/tutorialId] -byDate[dd/MM/yyyy]`
-desc / -d : Description of the deadline task
-level / -l : The level of the task (student or group)
-target / -t : The target id (StudentID/groupId/tutorialId)
-byDate/ -b : the deadline for the task

Acceptable values for each parameter:
Task:

- non empty string

Level:

- The level only can be lower than current level
  -- E.g., if my current level is group (i.e I am in a specific group), I can create a deadline task for the group or a student
- If a -level flag is not present, app will by default create deadline task for current level
  -- E.g., command “deadline -d grade proposal 1-byDate 20/10/2023” will create deadline task for current level (specific group or student)

Target:

- Need to specify a valid id for indicated level
  -- E.g., Need to specify a valid student id if want to create a student level task
- If a -target flag is not present, app will by default create task for every items under current level
  -- E.g, command “deadline -d grade proposal 1 -level student” will create deadline task “grade proposal 1” for every student under current group
- Target flag is allowed only if level flag is provided

byDate:

- In the format of dd/MM/yyyy.

Output if command fails

- pop up message indicate error when creating new deadline task.

Output if command succeeds

- pop up message indicate deadline task created successfully.

Examples:
`deadline -d grade proposal 1 -level group -target tut-1 -byDate 20/10/2023`

- This command will create a task with a deadline on 20/10/2023 for the task called grade proposal 1, allocated to students from tut-1.

### Mark/Unmark tasks as completed: `mark`

Marks specific tasks as done for each student/tutorial group

Format: `mark -d [task] -level [student/group] -target [StudentID/groupId] `

Acceptable parameter should be:
task:

- must be a valid non empty String

student:

- String should be "student" or "group"

StudentID:

- must be a valid non empty String starting with

Output if command fails

- pop up message saying mark is not done as well as specific task that is unchanged

Output if command succeeds

- pop up message saying mark is done successfully as well as specific task that is marked

Examples:
`mark -d Assignment 1 -level student -target stu-123`

- This command will mark stu-123's Assignment 1 as done

### Search for Tasks: `find`

Searches for tasks depending on the environment.

Format `find [task]`

Acceptable values for each parameter:
task:

- Non empty string

Output if command fails

- pop up message indicates error when finding for the task.

Output if command succeeds

- pop up message indicating the list of tasks matching the user’s input or that there is no task matching user input.

Examples:
`find grade proposal`

- This command will search for the task, grade proposal, depending on the environment the user is in, it will search for task(s) allocated to a tutorial group or student.

### Save the data (//TODO update)

ProfBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Edit the data file (//TODO update)

ProfBook data are saved automatically as a JSON file `[JAR file location]/data/ProfBook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ProfBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

(//TODO Update)

### Archive data files `[coming in v1.3]`

### Locate persons by name: `[coming in v1.3]`

### Delete a person : `[coming in v1.3]`

_Details coming soon ..._

---

(//TODO Update)

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ProfBook home folder.

---

(//TODO Update)

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

---

(//TODO Update)

## Command summary

| Action              | Format, Examples                                                                                                                                                                                 |
| ------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Help**            | `help`                                                                                                                                                                                           |
| **List**            | `ls`                                                                                                                                                                                             |
| **Clear**           | `clear`                                                                                                                                                                                          |
| **Exit**            | `exit`                                                                                                                                                                                           |
| **Add**             | `touch student -n [name] -id [StudentId]` <br> e.g., `touch student -n Gary -id stu-1234`                                                                                                        |
| **Create Group**    | `mkdir [groupId]` <br> e.g., `mkdir grp-1`                                                                                                                                                       |
| **Delete**          | `rm [StudentId]` <br> e.g., `touch student -n Gary -id stu-1234`                                                                                                                                 |
| **Create Todo**     | `todo -desc [task] -level [student/group] -target [StudentID/group Id/tutorialId]` <br> e.g., `todo -desc ps1 -level student -target stu-123`                                                    |
| **Create Deadline** | `deadline -desc [task] -level [student/group] -target [StudentID/groupId/tutorialId] -byDate[dd/MM/yyyy]`<br> e.g., `deadline -d grade proposal 1 -level group -target tut-1 -byDate 20/10/2023` |
| **Mark**            | `mark -d [task] -level [student/group] -target [StudentID/groupId]`<br> e.g.,`mark -d Assignment 1 -level student -target stu-123`                                                               |
| **Mark**            | `unmark -d [task] -level [student/group] -target [StudentID/groupId]`<br> e.g.,`unmark -d Assignment 1 -level student -target stu-123`                                                           |
| **Find**            | `find [task]`<br> e.g., `find grade proposal`                                                                                                                                                    |
