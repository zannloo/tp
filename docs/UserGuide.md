---
layout: default.md
title: "User Guide"
pageNav: 3
---
# ProfBook User Guide

Welcome to ProfBook, a specialised **student management system** exclusively tailored for **CS2103T tutors** by CS2103T 
students.  This guide is your gateway to unlocking the potential of ProfBook, where you will discover how ProfBook can 
help streamline your administrative tasks, alleviate workload and most importantly reduce any administrative errors.

## What can ProfBook do
As current students of CS2103T, we understand that CS2103T tutors have a high administrative workload on top of their 
existing tutor duties. On top of that, any administrative mistakes or oversights can increase that workload 
exponentially and may adversely impact student's learning. We deeply appreciate your role, and it is precisely to 
address these aforementioned issues that we designed ProfBook. ProfBook aims to
**drastically decrease administrative mistakes and your administrative workload**.

ProfBook is optimized for tutors' use via a **familiar Command Line Interface (CLI) that uses linux-styled commands** 
while retaining the benefits of a Graphical User Interface (GUI). If you are a fast typer, ProfBook will empower you 
to efficiently track and manage students' progress and tasks, surpassing the capabilities of traditional GUI apps 
with a gentle learning curve.
  
---  

## Features overview

### Consolidated Information

As a project-based module, CS2103T tutors have to juggle multiple groups **within** multiple tutorial slots. ProfBook
aims to expedite this process by allowing you keep track of all your groups and their progress within a centralised 
location. You would be able to traverse between the different groups quickly through familiar Linux commands.

### Student and Group Management

ProfBook aids with the **management of student and group information**. ProfBook efficiently encapsulates information of 
every project group so that you can easily monitor their progress and relevant information seamlessly.  You can 
effortlessly **add, delete and edit students or groups** and even **move students from one group to another**.

### Task Management

ProfBook aids with the **management of task information**.  You can quickly **allocate tasks to specific student 
or group** and track their progress. 

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

<!-- * Table of Contents -->
<div style="font-size: 23px;">

  <page-nav-print>

  **Table of Contents**

  </page-nav-print>

</div>

---


<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## Quick start

1. Ensure you have Java `11` or above installed in your computer.

1. ProfBook supports all operating systems.

1. Download the latest `ProfBook.jar` from [here](https://github.com/AY2324S1-CS2103T-W15-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ProfBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ProfBook.jar` command
   to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   <div class="centered-container">

      ![Ui](images/Ui.png){width=400 height=200}

   </div>

1. Type the command in the command box and press Enter to execute it.
   <br> Some example commands you can try:

    - `cat grp-001` : Lists all tasks belonging to grp-001.

    - `touch grp-001/0123Y --name Bob`
      : Adds a student named `Bob` to the grp-001.

    - `rm grp-001` : Deletes grp-001.

1. Refer to the [General Commands](#general-commands), [Student/ Group Commands](#student-or-group-commands), [Task Commands](#tasks-command) below for details of each command.

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

<box type="info" theme="warning">

**Acceptable values for each Id:**

`GroupId`:
- Must be in the format `grp-XXX` where `XXX` is replaceable with any 3 digit number
  - eg. `grp-001`

`StudentId`:
- Must be in the format `XXXX_` where `XXX` is replaceable with any 4 digit number and `_` is replaceable with any
  capitalised alphabet.
- This format accurately reflects the last 5 place of an NUS student's matriculation number while maintaining privacy and
  security.
  - eg. `8467U`


  </box>
</box>

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>


<box type="info">

**Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
  - e.g. In `touch SPECIFIED_PATH -n NAME`, `SPECIFIED_PATH` and the other fields in `UPPER_CASE` can be substituted 
    with the desired details to form `touch stu-200 --name Bob`.

- Words in `UPPER_CASE` and surrounded by `[square brackets]` are optional parameters to be supplied by the user.
  - e.g. In `cat [SPECIFIED_PATH]`, `[SPECIFIED_PATH]` can be substituted with details or left empty, 
    `cat stu-200` and `cat` are acceptable commands. 

- Parameters can be in any order.
  - e.g. If the command specifies `-n NAME -e EMAIL`, `-e EMAIL -n NAME` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be  
  ignored.
  - e.g. If the command specifies `help 123`, it will be interpreted as `help`.

- If you are using a PDF version of this document, please be careful when copying and pasting commands that span 
multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>

<box type="info" theme="warning">

**Acceptable values for each parameter:**

`SPECIFIED_PATH`:
- Must be a valid path.

`NAME`:
- Must be a non-empty string.

`EMAIL`:
- Must be a non-empty string.

`PHONE_NUMBER`:
- Must be a non-empty string.

`ADDRESS`:
- Must be a non-empty string

`TASK_INDEX`:
- Must be a valid index starts from 1.

</box>

---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## General Commands

### Change Directory: `cd`

Changes the current directory in the ProfBook.

**Format:** `cd SPECIFIED_PATH`

#### Acceptable values for each parameter:

`SPECIFIED_PATH`:
- Must be a valid path to a group, student or root. 

<box type="success" seamless>

#### Output if command succeeds:
- Displays message indicating successful navigation to target path.

</box>

<box type="wrong" seamless>

#### Output if command fails:
- Displays message indicating either:
  - Invalid command format
  - Invalid path

</box>

#### Example(s):
- When you are at the directory `~/grp-001` and would like to change directory from `grp-001` to `grp-002` you could
use the command:
  -  `cd ../grp-002`

### Display Directories : `ls`

Shows the list of children in specified directory. 

**Format:** `ls [SPECIFIED_PATH]`

#### Acceptable values for each parameter:

`[SPECIFIED_PATH]`:
- (Optional)
- Must be a valid path to a group or root.

<box type="info">
  
  If `[SPECIFIED_PATH]` is provided, the `ls` command show the list of children in the current directory.

</box>

<box type="success" seamless>

#### Output if command succeeds:

- Displays message indicating successful display of children list.
- Displays directories under target path on display panel.

</box>

<box type="wrong" seamless>

#### Output if command fails:

Displays message indicating either:
  - Invalid command format.
  - Invalid path.

</box>

#### Example(s):

- When you are at the directory `~/grp-001` and would like to see the students under `grp-001`,
    - `ls` will return all the students in `grp-001`.
- When you are at the root directory `~/` and would like to see all the groups you have,
    - `ls` will return all the current groups.

### Displays all tasks: `cat`

Displays all tasks depending on the directory you are at.

**Format:** `cat [SPECIFIED_PATH]`

#### Acceptable values for parameter:

`[SPECIFIED_PATH]`:
- (Optional)
- Must be a valid path to a group or student.
- Must **not** be a path to root. 

<box type="success" seamless>

#### Output if command succeeds:
- Displays list of tasks assigned under input path. 

</box>

<box type="wrong" seamless>

#### Output if command fails:
- If `cat` in root directory,
  - Displays message indicating that task list cannot be shown within root directory.

- If `cat` with invalid parameter, 
  - Displays message showing invalid path with user's input Path.

</box>

#### Example(s):
- When you are at the directory `~/grp-001` and would like to see all tasks allocated to `grp-001`,
  - `cat` will return all the tasks allocated to grp-001.

<box type="tip">

If you are at the directory `~/grp-001` and would like take a quick look of the tasks allocated to student `0010Y`,
- `cat 0010Y` will return all the tasks allocated to student of studentId, `0010Y`.

<box>

---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## Student or Group Commands

### Add a student: `touch`

Adds a student into the specified directory.

**Format:** `touch SPECIFIED_PATH -n NAME [-e EMAIL] [-p PHONE_NUMBER] [-a ADDRESS]`

- `--name` / `-n` : Name of Student
- `--email` / `-e` : Email of Student
- `--phone` / `-p`: Phone number of Student
- `--address` / `-a`: Address of Student

#### Acceptable values for each parameter:

`SPECIFIED_PATH`:
- Must be a valid path to a student.

<box type="success" seamless>

#### Output if command succeeds:

- Displays message indicating successful creation and information of the new student.

</box>

<box type="wrong" seamless>

#### Output if command fails:

- Displays message indicating either:
  - Invalid command format.
  - Invalid path.

</box>

#### Example(s):

- When you are at the directory `~/grp-001`, you could add a new student to this group through this command,
  - `touch stu-200 --name Bob`

<box type="tip">
##### ProTip:
When you are at the directory `~/grp-001` and would like to add a new student, Bob, to another directory 
`~/grp-002`, 
- You could use the command `touch ~/grp-002/0200Y --name Bob` to save yourself the trouble for having to change 
  directory before executing the `touch` command!

</box>

#### To note:

- When the command is executed from the group directory or if the specified path indicates the group, it creates a 
student within that group.
- However, if the command is executed outside a specific group, the specified path must extend to encompass both the 
group and the student to ensure accurate execution.

### Deletes a Student or Group: `rm`

Removes a student or group from the specified directory.

**Format:** `rm SPECIFIED_PATH`

#### Acceptable values for each parameter:

`SPECIFIED_PATH`:
- Must be a valid path to a student or group that user wants to delete.

<box type="success" seamless>

#### Output if command succeeds

- Displays message indicating successfully removal of student or group. 

</box>

<box type="wrong" seamless>

#### Output if command fails:

- Displays message indicating either:
    - Invalid command format.
    - No such student or group to delete.

</box>

#### Example(s):

- When you are at the root directory `~/` and would like to remove the group, `grp-001`,
    - `rm grp-001` will remove grp-001

<box type="tip">

- When you are at the directory `~/` and would like to remove the student with the StudentId, `0123Y`, in `~/grp-001`,
  - You could use the command `rm ~/grp-001/0123Y` to save yourself the hassle for having to change directory!

</box>


### Move Student or Group: `mv`

Moves student from one group to another group.

**Format:** `mv SPECIFIED_PATH_TO_STUDENT SPECIFIED_PATH_TO_GROUP`

#### Acceptable values for each parameter:

`SPECIFIED_PATH_TO_STUDENT`:

- Must be a valid path to a student.

`SPECIFIED_PATH_TO_GROUP`:

- Must be a valid path to a group.

<box type="success" seamless>

#### Output if command succeeds

- Displays message to indicate the successful transferal of a student from one group to another.

</box>

<box type="wrong" seamless>

#### Output if command fails

- Displays message indicating either:
  - Invalid command format.
  - No such student to move.
  - Invalid destination path.

</box>

#### Example(s):
- In the beginning of the semester, student transfers amongst groups are common. When you are at the root directory 
`~/` you could move a student, `0123Y` from `grp-001` to `grp-002` through this command,
  - `mv grp-001/0123Y grp-002`

<box type="tip">

- When you are at the directory `~/grp-001` and would like to move a student to `grp-002` without changing to the 
root directory,
  - You could use the command `mv 0123Y ../grp-002`!

</box>

### Create Group : `mkdir`

Creates a group that can contain students.

**Format:** `mkdir SPECIFIED_PATH_TO_GROUP`

#### Acceptable values for each parameter:

`SPECIFIED_PATH_TO_GROUP`:
- Must be a valid path to a group.

<box type="success" seamless>

#### Output if command succeeds
- Displays message indicating group with specific GroupId is created successfully.

</box>

<box type="wrong" seamless>

#### Output if command fails

- Displays message indicating either:
    - Invalid command format.
    - Invalid path to a group.

</box>

#### Example(s):

- When you are at the root directory `~/` and would like to add a new group with GroupId `grp-001`, 
  - `mkdir grp-001`

### View help : `help`

Shows a message explaining how to access the help page.

**Format:** `help`

### Clearing all entries : `clear`

Clears all entries from the address book.

**Format:** `clear`

<box type="tip">
This command is particularly valuable at the beginning of a new semester when you wish to clear all your previous 
students and groups, making way for the addition of new students. 
</box>

### Exiting the program : `exit`

Exits the program.

**Format:** `exit`

<box type="tip">


This command is a time-saver for those who would rather avoid using the mouse to click on the close button 
positioned in the top-left corner of the window.
</box>

---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## Tasks command

### Create Todo Task : `todo`

Creates todo tasks for specific student(s) or group(s).

**Format:** `todo SPECIFIED_PATH --desc DESCRIPTION [--all CATERGORY]`

- `--desc` / `-d` : Description of the todo task
- `--all` / `-al` : (Optional) Either `allStu` or `allGrp` to add a todo task to all students/groups in the
  specified path
  - `allStu` can only be used at a group directory.
  - `allGrp` can only be used at root directory.


<box type="success" seamless>

#### Output if command succeeds

- Displays message indicating the successful creation of the todo task.

</box>

<box type="wrong" seamless>

#### Output if command fails:

- Displays message indicating either:
  - Invalid command format.
  - Invalid path.

</box>

#### Example(s):

- When a specific student, `0010Y`, has to redo his tutorial, you could use the following command when you are in the directory 
`~/grp-001` to allocate the task to just this student,
    - `todo 0001Y --desc Redo tutorial`

<box type="tip">

- When you have an assignment, `Assignment 1` to be allocated to **all your students** in `grp-001`, you could use the 
following command, 
  - `todo ~/grp-001 --desc Assignment 1 --all allStu`

</box>


### Create Deadline task : `deadline`

Creates task with a deadline for specific student(s) or group(s).

**Format:** `deadline SPECIFIED_PATH --desc DESCRIPTION --datetime DATE_AND_TIME [--all CATERGORY]`

- `--desc` / `-d` : Description of the deadline task
- `--datetime` / `-dt` : Duedate of task in `yyyy-MM-dd HH:mm` format. 
- `--all` / `-al` : (Optional) Either `allStu` or `allGrp` to add a deadline task to all students/groups in the
  specified path
  - `allStu` can only be used at a group directory.
  - `allGrp` can only be used at root directory.

<box type="success" seamless>

#### Output if command succeeds

- Displays message indicating the successful creation of the deadline task.

</box>

<box type="wrong" seamless>

#### Output if command fails:

- Displays message indicating either:
  - Invalid command format.
  - Invalid path.

</box>

#### Example(s):

- When a specific student has to hand in his assignment again by a certain date, 
you could use the following command when you are in the directory `~/grp-001`,
  - `deadline 0001Y --desc resubmit Assignment 1 --datetime 2023-10-11 23:59`

<box type="tip">

- When you have a task, `Project 1 submission` with a deadline to be allocated to **all your groups**, 
you could use the following command at the root directory, `~/`,
  - `deadline ~/ --desc Project 1 submission --datetime 2023-10-11 23:59 --all allGrp`

</box>

### Mark tasks as completed: `mark`

Marks the specified task as done for the specified student or group. 

**Format:** `mark TASK_INDEX`


<box type="success" seamless>

#### Output if command succeeds

- Displays message indicating mark is done successfully as well as specific task that is marked.

</box>

<box type="wrong" seamless>

#### Output if command fails

- Displays message indicating either:
  - Invalid command format.
  - Invalid task index.
  - The display panel is not showing task list.

</box>

<box type="warning">

When using this command, you will first need to `cat` at the path where the task list is at before `mark`.
</box>

#### Example(s):

- When you are at `~/grp-001` and would like to mark the first task allocated to student with StudentId, `0123Y`,
  - `cat 0123Y`
  - `mark 1`

### Unmark completed task: `unmark`

Unmarks the specified task for the student or group. 

**Format:** `unmark TASK_INDEX`

<box type="success" seamless>

#### Output if command succeeds:

- Displays message indicating either:
  - Invalid command format.
  - Invalid task index.
  - The display panel is not showing task list.

</box>

<box type="wrong" seamless>

#### Output if command fails:

- Displays message indicating unmark is done successfully as well as specific task that is unmarked.

</box>

<box type="warning">

When using this command, you will first need to `cat` at the path where the task list is at before `unmark`.
</box>

#### Example(s):

- You are at `~/grp-001` would like to unmark the first task allocated to student with StudentId, `0123Y`,
  - `cat 0123Y`
  - `unmark 1`

### Save the data

ProfBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save
manually.

### Edit the data file

ProfBook data are saved automatically as a JSON file `[JAR file location]/data/profBook.json`. Advanced users are
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

**Q**: How do I transfer my data to another computer?<br>
**A**: Install ProfBook in the other computer and overwrite the empty data file it creates with the file that contains
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
| Action                  | Format, Example(s)                                                                                                                                                                      |
|:------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**                | `help`                                                                                                                                                                                  |
| **Clear all entries**   | `clear`                                                                                                                                                                                 |
| **Exits the program**   | `exit`                                                                                                                                                                                  |
| **Change Directory**    | `cd SPECIFIED_PATH` <br> e.g., `cd ../grp-001`                                                                                                                                          |
| **Display Directories** | `ls [SPECIFIED_PATH]` <br> e.g., `ls grp-001`                                                                                                                                           |
| **Display Task List**   | `cat [SPECIFIED_PATH]`<br> e.g., `cat 1234A, cat grp-001`                                                                                                                               |
| **Add Student**         | `touch SPECIFIED_PATH -n NAME [-e EMAIL] [- p PHONE_NUMBER] [-a ADDRESS]` <br> e.g., `touch stu-200 --name Bob --email bobby@example.com --phone 92929292 --address blk 258 Toa Payoh ` |
| **Create Group**        | `mkdir [groupId]` <br> e.g., `mkdir grp-1`                                                                                                                                              |
| **Delete**              | `rm SPECIFIED_PATH` <br> e.g., `rm 0123Y`, `rm grp-002`                                                                                                                                 |
| **Create Todo**         | `todo SPECIFIED_PATH --desc DESCRIPTION --all CATERGORY` <br> e.g., `todo stu-001 --desc Assignment 1                                                                                   |
| **Create Deadline**     | `deadline SPECIFIED_PATH --desc DESCRIPTION --datetime DATE_AND_TIME --all CATERGORY`<br> e.g., `deadline stu-001 --desc Assignment 1 --datetime 2023-10-11 23:59 `                     |
| **Mark**                | `mark TASK_INDEX`<br> e.g.,`mark 1`                                                                                                                                                     |
| **Unmark**              | `unmark TASK_INDEX`<br> e.g.,`unmark 2`                                                                                                                                                 |
