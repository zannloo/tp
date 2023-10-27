---
layout: default.md
title: "User Guide"
pageNav: 3
---

# ProfBook User Guide

ProfBook is a **student management system** specially made for **CS2103T tutors**, built by CS2103T students. As current
students of CS2103T, we understand that CS2103T tutors have a high administrative workload on top of their existing
tutor duties. On top of that, any administrative mistakes or oversights can increase that workload exponentially and may
adversely impact student's learning. To address the aforementioned issues, Profbook was designed as a student management
system which can **dramatically decrease administrative mistakes and workload**.

It is optimized for tutors use via a **familiar Command Line Interface (CLI) that uses linux-styled commands** while
still having the benefits of a Graphical User Interface (GUI). If you can type fast, ProfBook can track and manage your
student's progress and tasks much faster than traditional GUI apps with a gentle learning curve.

---

## Features overview

### Consolidated Information

As a project based module, CS2103T tutors have to juggle multiple groups **within** multiple tutorial slots. ProfBook
aims to expedite this process by allowing tutors to keep track of all their tutorial groups and all the project groups
within that in a centralised location. Tutors are able to traverse between tutorial slots and groups quickly through
familiar linux commands.

### Student Information Management

ProfBook aids with the **management of student information**. Student's information can be **readily** added, edited,
deleted quickly. In addition, ProfBook supports the managment of additional information vital for a tutor. Tutors can
track their student progress through creating various tasks such as Todo and Deadline. Furthermore, Tutors can store
short description of a student or links to their GitHub Repository if desired.

### Tutorial and Groups Information Management

ProfBook aids with the **management of Tutorial and Groups information**. Profbook efficiently encapsulates information
of every tutorial group and every project group so that tutors can management their progress and relevant information *
*seamlessly**. Tutors can easily add, move, delete students from groups. Similiar to students, tutors are able to track
each group progress through tasks. In addition, Tutors can store short description of the groups or links to their
GitHub Repository if desired.

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

<!-- * Table of Contents -->
<page-nav-print>

  **Table of Contents**

</page-nav-print>

---


<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ProfBook.jar` from [here](https://github.com/AY2324S1-CS2103T-W15-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ProfBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ProfBook.jar` command
   to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   <div class="centered-container">

      ![Ui](images/Ui.png){width=400 height=200}

   </div>

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    - `list` : Lists all contacts.

    - `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe`
      to the Address Book.

    - `delete 3` : Deletes the 3rd contact shown in the current list.

    - `clear` : Deletes all contacts.

    - `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## Understanding ProfBook

<box type="definition">

  **ProfBook Software Structure** <br>

  ProfBook is organized hierarchically with the following structure:

  <tree>
  ~/
    grp-001/
      1001Z
      4123U
    grp-002/
      5815Y
    grp-003/
      ...
  </tree>

  **Root Directory (e.g., `~/`)**<br>
  The Root Directory serves as the main container for the ProfBook software. This is where all the data is organized.

  **Group Directories (e.g., `grp-001`, `grp-002`, ...)**<br>

  Within the Root Directory, there are Group Directories. Each Group Directory is dedicated to managing a specific group of students.

  **Student Directories (e.g., `1001Z`, `5815Y`, ...)**<br>

  Inside each Group Directory, you'll find Student Directories. These directories represent individual students within each group.

  This hierarchical structure enables a well-organized and efficient way to manage groups and students within ProfBook.
</box>

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>


<box type="info">

**Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
    - e.g. in `touch SPECIFIED_PATH -n NAME -e EMAIL - p PHONE_NUMBER -a ADDRESS`, `SPECIFIED_PATH` and the other words
      in `UPPER_CASE` can be substituted to form `touch stu-200 --name Bob --email bobby@example.com --phone 92929292
      --address blk 258 Toa Payoh`.

- Parameters can be in any order.
    - e.g. if the command specifies `-n NAME -e EMAIL`, `-e EMAIL -n NAME` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>

<box type="info" theme="warning">

**Acceptable values for each parameter:**

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
</box>

---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## General Commands

### View help : `help` (//TODO Update)

Shows a message explaning how to access the help page.

// TODO Update
![help message](images/helpMessage.png)

Format: `help`

### Change Directory: `cd`

Changes the current directory in the ProfBook.

**Format:** `cd SPECIFIED_PATH`

#### Example
- ```{heading="**~/grp-001**"}
    cd ../grp-002
  ```
  Changes directory from `grp-001` to `grp-002`

<box type="success" seamless>

#### Output if command succeeds:

Displays message indicating successful navigation to target path.

</box>

<box type="wrong" seamless>

#### Output if command fails:

Displays message indicating either:
  - Invalid command format
  - Invalid path

</box>


### List All Directories : `ls`

Shows the list of children in the current directory

Format: `ls SPECIFIED_PATH`

#### Example:

- When user is at the directory `~/grp-001`:
    - `ls` will return all the students in grp-001
- When user is at the root directory `~/`:
    - `ls` will return all the current groups

<box type="success" seamless>

#### Output if command succeeds:

- Displays message indicating successful display of children list.
- Children list of target path will be displayed on display panel.

</box>

<box type="wrong" seamless>

#### Output if command fails:

Displays message indicating either:
  - Invalid command format
  - Invalid path

</box>

<box type="info">
  
  If no `SPECIFIED_PATH` is given, the `ls` command will default to the current directory.

</box>

---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## Student / Group commands

### Add a student: `touch`

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

- Command creates a student within the group if command is used from within the group or specified path has indicated
  the group path.
- If a command is executed outside a specific group, specified path must be to the group and to the student.

#### Example:

- The following commands when executed at the different current path directory will create the same student at the same
  path directory`~/grp-001/stu-200`
    - When user is at the directory `~/grp-001` and keys in command:
        - `touch stu-200 --name Bob --email bobby@example.com --phone 92929292 --address blk 258 Toa Payoh `
    - When user is at the root directory `~/` and keys in command:
        - `touch ~/grp-001/stu-200 --name Bob --email bobby@example.com --phone 92929292 --address blk 258 Toa Payoh `

### Delete a Student or Group: `rm`

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

- When user is at the directory `~/grp-001` and keys in the following command, student with specified
  path `~/grp-001/0123Y` will be deleted:
    - `rm 0123Y`
- When user is at the root directory `~/` and keys in the following command, will delete group with specified
  path `~/grp-001` will be deleted:
    - `rm grp-001`

### Move Student or Group: `mv`

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

- This command will create a group with groupId which is a string called 1 and only consists the creator at that
  instance of creating the group

---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## Tasks command

### Create Todo Task : `todo`

Creates todo tasks for specific student(s) or group(s).

Format: `todo SPECIFIED_PATH --desc DESCRIPTION --all CATERGORY`

- `--desc` / `-d` : Description of the todo task
- `--all` / `-al` : (Optional) Either `allStu` or `allGrp` to add a deadline task to all students/groups in the
  specified path

#### Output if command fails:

- Pop up message indicating either:
    - Invalid command format
    - Invalid path

#### Output if command succeeds

- Pop up message indicating todo task created successfully.

#### Examples:

- When user is at the directory `~/grp-001` and keys in the following command, a todo task with
  description `Assignment 1`, will be allocated to student from path `grp-001/stu-001`.
    - `todo stu-001 --desc Assignment 1 `
- When user is at the root directory `~/` and keys in the following command, a todo task with
  description `Assignment 1`, will be allocated to student from path `grp-001/stu-001`.
    - `todo ~/grp-001/stu-001 --desc Assignment 1 `
- When user is at the directory `~` and keys in the following command, a todo task with description `Assignment 1`, will
  be allocated to all students in path `~/grp-001`.
    - `todo ~/grp-001 --desc Assignment 1 --all allStu`

### Create Deadline task : `deadline`

Creates task with a deadline for specific student(s) or group(s).

Format `deadline SPECIFIED_PATH --desc DESCRIPTION --datetime DATE_AND_TIME --all CATERGORY`

- `--desc` / `-d` : Description of the deadline task
- `--datetime` / `-dt` : The duedate of the task. In the following format: `YYYY-MM-DD HH:MM`
- `--all` / `-al` : (Optional) Either `allStu` or `allGrp` to add a deadline task to all students/groups in the
  specified path

#### Output if command fails:

- Pop up message indicating either:
    - Invalid command format
    - Invalid path

#### Output if command succeeds

- Pop up message indicating deadline task created successfully.

#### Examples:

- When user is at the directory `~/grp-001` and keys in the following command, a task with a deadline `2023-10-11 23:59`
  for the task with description `Assignment 1`, will be allocated to student from path `grp-001/stu-001`.
    - `deadline stu-001 --desc Assignment 1 --datetime 2023-10-11 23:59`
- When user is at the root directory `~/` and keys in the following command, a task with a deadline `2023-10-11 23:59`
  for the task with description `Assignment 1`, will be allocated to student from path `grp-001/stu-001`.
    - `deadline ~/grp-001/stu-001 --desc Assignment 1 --datetime 2023-10-11 23:59 `
- When user is at the root directory `~/` and keys in the following command, a task with a deadline `2023-10-11 23:59`
  for the task with description `Assignment 1`, will be allocated to all students in path `~/grp-001`.
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

### Save the data

ProfBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save
manually.

### Edit the data file

ProfBook data are saved automatically as a JSON file `[JAR file location]/data/ProfBook.json`. Advanced users are
welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, ProfBook will discard all data and start with an empty data
file at the next run. Hence, it is recommended to take a backup of the file before editing it.

</box>

_Details coming soon ..._

---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous ProfBook home folder.

---

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only
   the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the
   application before running the application again.

---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>


## Command summary

Action     | Format, Examples |
:-----     | :-------
**Help**| `help`
**Change Directory**| `cd SPECIFIED_PATH` <br> e.g., `cd ../grp-002`
**List Directories**| `ls SPECIFIED_PATH` <br> e.g. `ls grp-001`
**Add Student**     | `touch SPECIFIED_PATH -n NAME -id STUDENTID` <br> e.g., `touch grp-001/stu-001 -n Gary -id 1234Y`
**Move Student**    | `mv SPECIFIED_PATH SPECIFIED_PATH` <br> e.g., `mv grp-001/stu-001 grp-002`
**Create Group**    | `mkdir SPECIFIED_PATH` <br> e.g., `mkdir grp-001`
**Remove**          | `rm SPECIFIED_PATH` <br> e.g., `rm grp-001/stu-001`
**Create Todo**     | `todo SPECIFIED_PATH --desc DESCRIPTION --all CATERGORY` <br> e.g., `todo stu-001 --desc Assignment 1`
**Create Deadline** | `deadline SPECIFIED_PATH --desc DESCRIPTION --datetime DATE_AND_TIME --all CATERGORY`<br> e.g., `deadline stu-001 --desc Assignment 1 --datetime 2023-10-11 23:59`
**Mark**            | `mark -d [task] -level [student/group] -target [StudentID/groupId]`<br> e.g.,`mark -d Assignment 1 -level student -target 0123Y`
**Unmark**            | `unmark -d [task] -level [student/group] -target [StudentID/groupId]`<br> e.g.,`unmark -d Assignment 1 -level student -target 0123Y`
