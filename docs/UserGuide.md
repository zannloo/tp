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
address these aforementioned issues that we designed Profbook as a student management system which can 
**dramatically decrease administrative mistakes and workload**.

ProfBook is optimized for tutors' use via a **familiar Command Line Interface (CLI) that uses linux-styled commands** 
while retaining the benefits of a Graphical User Interface (GUI). If you are a fast typer, ProfBook will empower you 
to efficiently track and manage students' progress and tasks, surpassing the capabilities of traditional GUI apps 
with a gentle learning curve.
  
---  

## Features overview

### Consolidated Information

As a project-based module, CS2103T tutors have to juggle multiple groups **within** multiple tutorial slots. ProfBook  
aims to expedite this process by allowing you keep track of all your groups and their progress within a centralised 
location. You would be able to traverse between the different groups quickly through familiar linux commands.

### Student and Group Management

ProfBook aids with the **management of student and group information**.Profbook efficiently encapsulates information of 
every project group so that you can easily monitor their progress and relevant information **seamlessly**.  You can 
effortlessly **add, delete and edit students or group** and even **move students from one group to another**.

### Task Management

ProfBook aids with the **management of task information**.  You can easily **allocate tasks to specific student 
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

    - `cat grp-001` : Lists all tasks belonging to grp-001.

    - `touch grp-001/stu-200 --name Bob --email bobby@example.com --phone 92929292 --address blk 258 Toa Payoh`
      : Adds a student named `Bob` to the grp-001.

    - `rm grp-001` : Deletes grp-001.

1. Refer to the [General Commands](#general-commands), [Student/Group Commands](#student-or-group-commands), [Task Commands](#tasks-command) below for details of each command.

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

`TASK_INDEX`:
- must be a valid index starts from 1
</box>

---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## General Commands

### View help : `help`

Shows a message explaning how to access the help page.

**Format:** `help`

### Change Directory: `cd`

Changes the current directory in the ProfBook.

**Format:** `cd SPECIFIED_PATH`

#### Acceptable values for each parameter:

`SPECIFIED_PATH`:

- must be a valid path to a group or root

<box type="success" seamless>

#### Output if command succeeds:

- displays message indicating successful navigation to target path.

</box>

<box type="wrong" seamless>

#### Output if command fails:

- displays message indicating either:
  - Invalid command format
  - Invalid path

</box>

#### Examples:
- When user is at the directory `~/grp-001`
  - User can change directory from `grp-001` to `grp-002` using `cd ../grp-002`

### Display Directories : `ls`

Shows the list of children in the current directory

**Format:** `ls SPECIFIED_PATH`

#### Acceptable values for each parameter:

`SPECIFIED_PATH`:

- must be a valid path to a group or root

<box type="info">
  
  If no `SPECIFIED_PATH` is given, the `ls` command will default to the current directory.

</box>

<box type="success" seamless>

#### Output if command succeeds:

- displays message indicating successful display of children list.
- displays directories under target path on display panel.

</box>

<box type="wrong" seamless>

#### Output if command fails:

Displays message indicating either:
  - Invalid command format
  - Invalid path

</box>

#### Examples:

- When user is at the directory `~/grp-001`:
    - `ls` will return all the students in grp-001
- When user is at the root directory `~/`:
    - `ls` will return all the current groups

### Display all tasks: `cat`

Searches for tasks depending on the environment.

**Format:** `cat SPECIFIED_PATH`

#### Acceptable values for parameter:

`SPECIFIED_PATH`:
- must be a valid path

<box type="success" seamless>

#### Output if command succeeds:

- display updates with tasks assigned under input Path

</box>

<box type="wrong" seamless>

#### Output if command fails:

If `cat` in root directory

- display updates indicating that tasks cannot be shown within root directory.

If `cat` with invalid parameter

- display updates showing invalid path with user entered Path

</box>

#### Examples:
`cat grp-001`, `cat 1234A`

- The first command will display all tasks assigned to group 001
- The second command will display all tasks that are assigned to student with Id 1234A

---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## Student or Group Commands

### Add a student: `touch`

Adds a student into the specified directory.

**Format:** `touch SPECIFIED_PATH -n NAME -e EMAIL - p PHONE_NUMBER -a ADDRESS`

- `--name` / `-n` : Name of Student
- `--email` / `-e` : Email of Student
- `--phone` / `-p`: Phone number of Student
- `--address` / `-a`: Address of Student

#### Acceptable values for each parameter:

`SPECIFIED_PATH`:

- must be a valid path to a student

<box type="success" seamless>

#### Output if command succeeds:

- Displays message indicating successful creation together with information of created student

</box>

<box type="wrong" seamless>

#### Output if command fails:

- Displays message indicating either:
    - Invalid command format
    - Invalid path

</box>

#### Examples:

- The following commands when executed at the different current path directory will create the same student at the same
  path directory`~/grp-001/stu-200`
    - When user is at the directory `~/grp-001` and keys in command:
        - `touch stu-200 --name Bob --email bobby@example.com --phone 92929292 --address blk 258 Toa Payoh `
    - When user is at the root directory `~/` and keys in command:
        - `touch ~/grp-001/stu-200 --name Bob --email bobby@example.com --phone 92929292 --address blk 258 Toa Payoh `


#### To note:

- Command creates a student within the group if command is used from within the group or specified path has indicated
  the group path.
- If a command is executed outside a specific group, specified path must be to the group and to the student.

### Delete a Student or Group: `rm`

Deletes a student/group from the specified directory.

**Format:** `rm SPECIFIED_PATH`

#### Acceptable values for each parameter:

`SPECIFIED_PATH`:

- must be a valid path to a student/group that user wants to delete

<box type="success" seamless>

#### Output if command succeeds

- Displays message indicating target successfully removed

</box>

<box type="wrong" seamless>

#### Output if command fails:

- Displays message indicating either:
    - Invalid command format
    - No such target to delete

</box>

#### Examples:

- When user is at the directory `~/grp-001` and keys in the following command, student with specified
  path `~/grp-001/0123Y` will be deleted:
    - `rm 0123Y`
- When user is at the root directory `~/` and keys in the following command, will delete group with specified
  path `~/grp-001` will be deleted:
    - `rm grp-001`


### Move Student or Group: `mv`

Moves student from one group to another group

**Format:** `mv SPECIFIED_PATH SPECIFIED_PATH`

#### Acceptable values for each parameter:

`SPECIFIED_PATH`:

- must be a valid path to a student

`SPECIFIED_PATH`:

- must be a valid path to a group

<box type="success" seamless>

#### Output if command succeeds

- Displays message to indicate the successful transfer of a student from one group to another group

</box>

<box type="wrong" seamless>

#### Output if command fails

- Displays message indicating either:
  - Invalid command format
  - No such student to move
  - Invalid destination path

</box>

#### Examples:
- When user is at the root directory `~` and keys in the following command, student with specified path `~/grp-001/0123Y` will be moved from grp-001 to grp-002:
  - `mv grp-001/0123Y grp-002`

- When user is at the group directory `~/grp-001` and keys in the following command, student with specified path `~/grp-001/0123Y` will be moved from grp-001 to grp-002:
  - `mv 0123Y ../grp-002`


### Create Group : `mkdir`

Creates a group consists of students

**Format:** `mkdir PATH_TO_THE_GROUP`

#### Acceptable values for each parameter:

`PATH_TO_THE_GROUP`:

- must be a valid path to a group

<box type="success" seamless>

#### Output if command succeeds

- pop up message indicates group with specific groupId was created successfully.

</box>

<box type="wrong" seamless>

#### Output if command fails

- Displays message indicating either:
    - Invalid command format
    - Invalid path to a group

</box>

#### Examples:

- When user is at the root directory `~` and keys in the following command, a group with groupId called grp-001 will be created
  - `mkdir grp-001`


---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## Tasks command

### Create Todo Task : `todo`

Creates todo tasks for specific student(s) or group(s).

**Format:** `todo SPECIFIED_PATH --desc DESCRIPTION --all CATERGORY`

- `--desc` / `-d` : Description of the todo task
- `--all` / `-al` : (Optional) Either `allStu` or `allGrp` to add a deadline task to all students/groups in the
  specified path

<box type="success" seamless>

#### Output if command succeeds

- Displays message indicating todo task created successfully.

</box>

<box type="wrong" seamless>

#### Output if command fails:

- Displays message indicating either:
  - Invalid command format
  - Invalid path

</box>

#### Examples:

- When user is at the directory `~/grp-001` and keys in the following command, a todo task with
  description `Assignment 1`, will be allocated to student from path `grp-001/stu-001`.
    - `todo stu-001 --desc Assignment 1 `
- When user is at the root directory `~/` and keys in the following command, a todo task with
  description `Assignment 1`, will be allocated to student from path `grp-001/stu-001`.
    - `todo ~/grp-001/stu-001 --desc Assignment 1 `
- When user is at the root directory `~` and keys in the following command, a todo task with description `Assignment 1`, will
  be allocated to all students in path `~/grp-001`.
    - `todo ~/grp-001 --desc Assignment 1 --all allStu`


### Create Deadline task : `deadline`

Creates task with a deadline for specific student(s) or group(s).

**Format:** `deadline SPECIFIED_PATH --desc DESCRIPTION --datetime DATE_AND_TIME --all CATERGORY`

- `--desc` / `-d` : Description of the deadline task
- `--datetime` / `-dt` : The duedate of the task. In the following format: `yyyy-MM-dd HH:mm`
- `--all` / `-al` : (Optional) Either `allStu` or `allGrp` to add a deadline task to all students/groups in the
  specified path

<box type="success" seamless>

#### Output if command succeeds

- Displays message indicating deadline task created successfully.

</box>

<box type="wrong" seamless>

#### Output if command fails:

- Displays message indicating either:
  - Invalid command format
  - Invalid path

</box>

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


### Mark tasks as completed: `mark`

Marks the specified task as done for the student

**Format:** `mark TASK_INDEX`

#### Acceptable parameter should be:

`TASK_INDEX`:

- must be a valid index starts from 1

<box type="success" seamless>

#### Output if command succeeds

- displays message indicating mark is done successfully as well as specific task that is marked

</box>

<box type="wrong" seamless>

#### Output if command fails

- displays message indicating either:
  - Invalid command format
  - Invalid task index
  - The display panel is not showing task list

</box>

#### Examples:

- When user is at the group directory `~/grp-001` and keys in the following command, the task list of a student with specified path `~/grp-001/0123Y` will be shown after entering the command `cat 0123Y`. Then, user can mark task by specifying the task index:
  - `cat 0123Y`
  - `mark 1`

### Unmark completed task: `unmark`

Unmarks the specified task for the student

**Format:** `unmark TASK_INDEX`

#### Acceptable parameter should be:

`TASK_INDEX`:

- must be a valid index starts from 1

<box type="success" seamless>

#### Output if command succeeds:

- displays message indicating either:
  - Invalid command format
  - Invalid task index
  - The display panel is not showing task list

</box>

<box type="wrong" seamless>

#### Output if command fails:

- displays message indicating unmark is done successfully as well as specific task that is unmarked

</box>

#### Examples:

- When user is at the group directory `~/grp-001` and keys in the following command, the task list of a student with specified path `~/grp-001/0123Y` will be shown after entering the command `cat 0123Y`. Then, user can unmark task by specifying the task index:
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
| Action              | Format, Examples|
|:------              |:------|
| **Help**            | `help` |
| **Change Directory**| `cd SPECIFIED_PATH` <br> e.g., `cd ../grp-001`|
| **Display Directories**| `ls SPECIFIED_PATH` <br> e.g., `ls grp-001`|
| **Display Task List**  | `cat SPECIFIED_PATH`<br> e.g., `cat 1234A, cat grp-001`|
| **Add Student**     | `touch SPECIFIED_PATH -n NAME -e EMAIL - p PHONE_NUMBER -a ADDRESS` <br> e.g., `touch stu-200 --name Bob --email bobby@example.com --phone 92929292 --address blk 258 Toa Payoh ` |
| **Create Group**    | `mkdir [groupId]` <br> e.g., `mkdir grp-1`|
| **Delete**          | `rm SPECIFIED_PATH` <br> e.g., `rm 0123Y`, `rm grp-002`|
| **Create Todo**     | `todo SPECIFIED_PATH --desc DESCRIPTION --all CATERGORY` <br> e.g., `todo stu-001 --desc Assignment 1|
| **Create Deadline** | `deadline SPECIFIED_PATH --desc DESCRIPTION --datetime DATE_AND_TIME --all CATERGORY`<br> e.g., `deadline stu-001 --desc Assignment 1 --datetime 2023-10-11 23:59 `|
| **Mark**            | `mark TASK_INDEX`<br> e.g.,`mark 1`|
| **Unmark**          | `unmark TASK_INDEX`<br> e.g.,`unmark 2`|
