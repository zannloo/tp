---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# ProfBook User Guide

ProfBook is a **student management system** specially made for **CS2103T tutors**, built by CS2103T students. As current students of CS2103T, we understand that CS2103T tutors have a high administrative workload on top of their existing tutor duties. On top of that, any administrative mistakes or oversights can increase that workload exponentially and may adversely impact student's learning. To address the aforementioned issues, Profbook was designed as a student management system which can **dramatically decrease administrative mistakes and workload**.

It is optimized for tutors use via a **familiar Command Line Interface (CLI) that uses linux-styled commands** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ProfBook can track and manage your student's progress and tasks much faster than traditional GUI apps with a gentle learning curve.

## Features overview

### Consolidated Information

As a project based module, CS2103T tutors have to juggle multiple groups **within** multiple tutorial slots. ProfBook aims to expedite this process by allowing tutors to keep track of all their tutorial groups and all the project groups within that in a centralised location. Tutors are able to traverse between tutorial slots and groups quickly through familiar linux commands.

#### Student Information Management

ProfBook aids with the **management of student information**. Student's information can be **readily** added, edited, deleted quickly. In addition, ProfBook supports the managment of additional information vital for a tutor. Tutors can track their student progress through creating various tasks such as Todo and Deadline. Furthermore, Tutors can store short description of a student or links to their GitHub Repository if desired.

#### Tutorial and Groups Information Management

ProfBook aids with the **management of Tutorial and Groups information**. Profbook efficiently encapsulates information of every tutorial group and every project group so that tutors can management their progress and relevant information **seamlessly**. Tutors can easily add, move, delete students from groups. Similiar to students, tutors are able to track each group progress through tasks. In addition, Tutors can store short description of the groups or links to their GitHub Repository if desired.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start 
(//TODO Change)

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ProfBook.jar` from [here](https://github.com/AY2324S1-CS2103T-W15-2/tp/releases).

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

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
  - e.g. in `touch SPECIFIED_PATH -n NAME -e EMAIL - p PHONE_NUMBER -a ADDRESS`, `SPECIFIED_PATH` and the other words 
  in `UPPER_CASE` can be substituted to form `touch stu-200 --name Bob --email bobby@example.com --phone 92929292 
  --address blk 258 Toa Payoh`.

- Parameters can be in any order.
  - e.g. if the command specifies `-n NAME -e EMAIL`, `-e EMAIL -n NAME` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

Acceptable values for each parameter:

`SPECIFIED_PATH`:
- must be valid path

`NAME`:
- must be a non-empty string

`EMAIL`:
- must be a non-empty string

`PHONE_NUMBER`:
- must be a non-empty string

`ADDRESS`:
- must be a non-empty string


--------------------------------------------------------------------------------------------------------------------

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

--------------------------------------------------------------------------------------------------------------------

# Student/Group commands

### Adding a student: `touch`

Adds a student into the specified directory.

Format: `touch SPECIFIED_PATH -n NAME -e EMAIL - p PHONE_NUMBER -a ADDRESS`
- `--name` / `-n` : Name of Student
- `--email` / `-e` : Email of Student
- `--phone` / `-p`: Phone number of Student
- `--address` / `-a`: Address of Student

#### Acceptable values for each parameter:

Specified path:
- must be a valid path to a student

#### Output if command fails: 

- Pop up message indicating either:
    - Invalid command format
    - Invalid path

#### Output if command succeeds: 

- Pop up message indicating successful creation together with information of created student

#### To note:
- Command creates a student within the group if command is used from within the  group or specified path has indicated the group path.
- If a command is executed outside a specific group, specified path must be to the group and to the student.

#### Example:
- The following commands when executed at the different current path directory will create the same student at the same path directory`~/grp-001/stu-200`
  - When user is at the directory `~/grp-001` and keys in command:
    - `touch stu-200 --name Bob --email bobby@example.com --phone 92929292 --address blk 258 Toa Payoh ` 
  - When user is at the root directory `~/` and keys in command:
    - `touch ~/grp-001/stu-200 --name Bob --email bobby@example.com --phone 92929292 --address blk 258 Toa Payoh `


### Deleting a Student/ Group: `rm`

Deletes a student/group from the specified directory.

Format: `rm SPECIFIED_PATH`

#### Acceptable values for each parameter:

Specified path:
- must be a valid path to a student/group that user wants to delete

#### Output if command fails:

- Pop up message indicating either:
  - Invalid command format
  - No such target to delete

#### Output if command succeeds

- Pop up message indicating target successfully removed

#### Examples:
- When user is at the directory `~/grp-001` and keys in the following command, student with specified path `~/grp-001/0123Y` will be deleted:
  - `rm 0123Y`
- When user is at the root directory `~/` and keys in the following command, will delete group with specified path `~/grp-001` will be deleted:
  - `rm grp-001`

### Move students into/out of the group: `mv`

Moves student from a group to another group

Format `mv [StudentID] -source [source group]  -dest [destination group]`

Acceptable values for each parameter:
StudentID:

- must be a 4 digits number follow with any letter

source group:

- must be a valid non-empty string starting with grp-

destination group:

- must be a valid non-empty string starting with grp-

Output if command fails

- pop up message indicate error when moving student with id 0123Y from grp-1 to grp-2

Output if command succeeds

- pop up message indicates successfully moving student with id 0123Y from grp-1 to grp-2

Examples:
`mv 0123Y -source grp-1 -dest grp-2`

- This command will move a student with value id 0123Y within the groups or from Ungroup to Group.

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

--------------------------------------------------------------------------------------------------------------------

# Tasks command

### Create Todo Task : `todo`

Creates todo tasks for specific student(s) or group(s).

Format: `todo SPECIFIED_PATH --desc DESCRIPTION --all CATERGORY`
- `--desc` / `-d` : Description of the todo task
- `--all` / `-al` : (Optional) Either `allStu` or `allGrp` to add a deadline task to all students/groups in the specified path

#### Output if command fails:

- Pop up message indicating either:
    - Invalid command format
    - Invalid path

#### Output if command succeeds

- Pop up message indicating todo task created successfully.

#### Examples:
- When user is at the directory `~/grp-001` and keys in the following command, a todo task with description `Assignment 1`, will be allocated to student from path `grp-001/stu-001`.
    - `todo stu-001 --desc Assignment 1 `
- When user is at the root directory `~/` and keys in the following command, a todo task with description `Assignment 1`, will be allocated to student from path `grp-001/stu-001`.
    - `todo ~/grp-001/stu-001 --desc Assignment 1 `
- When user is at the directory `~` and keys in the following command, a todo task with description `Assignment 1`, will be allocated to all students in path `~/grp-001`.
    - `todo ~/grp-001 --desc Assignment 1 --all allStu`

### Create Deadline task : `deadline`

Creates task with a deadline for specific student(s) or group(s).

Format `deadline SPECIFIED_PATH --desc DESCRIPTION --datetime DATE_AND_TIME --all CATERGORY`
- `--desc` / `-d` : Description of the deadline task
- `--datetime` / `-dt` : The duedate of the task. In the following format: `YYYY-MM-DD HH:MM`
- `--all` / `-al` : (Optional) Either `allStu` or `allGrp` to add a deadline task to all students/groups in the specified path

#### Output if command fails:

- Pop up message indicating either:
    - Invalid command format
    - Invalid path 

#### Output if command succeeds

- Pop up message indicating deadline task created successfully.

#### Examples:
- When user is at the directory `~/grp-001` and keys in the following command, a task with a deadline `2023-10-11 23:59` for the task with description `Assignment 1`, will be allocated to student from path `grp-001/stu-001`.
  - `deadline stu-001 --desc Assignment 1 --datetime 2023-10-11 23:59 `
- When user is at the root directory `~/` and keys in the following command, a task with a deadline `2023-10-11 23:59` for the task with description `Assignment 1`, will be allocated to student from path `grp-001/stu-001`.
  - `deadline ~/grp-001/stu-001 --desc Assignment 1 --datetime 2023-10-11 23:59 `
- When user is at the root directory `~/` and keys in the following command, a task with a deadline `2023-10-11 23:59` for the task with description `Assignment 1`, will be allocated to all students in path `~/grp-001`.
    - `deadline ~/grp-001 --desc Assignment 1 --datetime 2023-10-11 23:59 --all allStu`


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
`mark -d Assignment 1 -level student -target 0123Y`

- This command will mark 0123Y's Assignment 1 as done

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

<box type="warning" seamless>
**Caution:**
If your changes to the data file makes its format invalid, ProfBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</box>

(//TODO Update)

### Archive data files `[coming in v1.3]`

### Locate persons by name: `[coming in v1.3]`

### Delete a person : `[coming in v1.3]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

(//TODO Update)

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ProfBook home folder.

--------------------------------------------------------------------------------------------------------------------

(//TODO Update)

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

(//TODO Update)

## Command summary

| Action              | Format, Examples                                                                                                                                                                                 |
| ------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Help**            | `help`                                                                                                                                                                                           |
| **List**            | `ls`                                                                                                                                                                                             |
| **Clear**           | `clear`                                                                                                                                                                                          |
| **Exit**            | `exit`                                                                                                                                                                                           |
| **Add**             | `touch student -n [name] -id [StudentId]` <br> e.g., `touch student -n Gary -id 1234Y`                                                                                                        |
| **Create Group**    | `mkdir [groupId]` <br> e.g., `mkdir grp-1`                                                                                                                                                       |
| **Delete**          | `rm [StudentId]` <br> e.g., `touch student -n Gary -id 1234Y`                                                                                                                                 |
| **Create Todo**     | `todo -desc [task] -level [student/group] -target [StudentID/group Id/tutorialId]` <br> e.g., `todo -desc ps1 -level student -target 0123Y`                                                    |
| **Create Deadline** | `deadline -desc [task] -level [student/group] -target [StudentID/groupId/tutorialId] -byDate[dd/MM/yyyy]`<br> e.g., `deadline -d grade proposal 1 -level group -target tut-1 -byDate 20/10/2023` |
| **Mark**            | `mark -d [task] -level [student/group] -target [StudentID/groupId]`<br> e.g.,`mark -d Assignment 1 -level student -target 0123Y`                                                               |
| **Mark**            | `unmark -d [task] -level [student/group] -target [StudentID/groupId]`<br> e.g.,`unmark -d Assignment 1 -level student -target 0123Y`                                                           |
| **Find**            | `find [task]`<br> e.g., `find grade proposal`                                                                                                                                                    |
