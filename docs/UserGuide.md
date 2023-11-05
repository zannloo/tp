---
layout: default.md
title: "User Guide"
pageNav: 3
---
# ProfBook User Guide :bulb:

Welcome to ProfBook, a specialised **student management system** exclusively tailored for **CS2103T tutors** by CS2103T 
students.  This guide is your gateway to unlocking the potential of ProfBook, where you will discover how ProfBook can 
help streamline your administrative tasks, alleviate workload and most importantly reduce any administrative errors.

## What can ProfBook do :raising_hand:
As current students of CS2103T, we understand that CS2103T tutors have a high administrative workload on top of their 
existing tutor duties. On top of that, any administrative mistakes or oversights can increase that workload 
exponentially and may adversely impact student's learning. We deeply appreciate your role, and it is precisely to 
address these aforementioned issues that we designed ProfBook. ProfBook aims to
**drastically decrease administrative mistakes and your workload**.

ProfBook is optimized for tutors' use via a **familiar Command Line Interface (CLI) that uses linux-styled commands** 
while retaining the benefits of a Graphical User Interface (GUI). If you are a fast typer, ProfBook will empower you 
to efficiently track and manage students' progress and tasks, surpassing the capabilities of traditional GUI apps 
with a gentle learning curve.
  
---  

## Features overview

### Consolidated Information

As a project-based module, CS2103T tutors have to juggle multiple groups. ProfBook
aims to expedite this process by allowing you to keep track of all your groups and their progress within a centralised 
location. You would be able to traverse between the different groups quickly through familiar Linux commands.

### Student and Group Management

ProfBook aids with the **management of student and group information**. ProfBook efficiently encapsulates information of 
every project group so that you can easily monitor their progress and relevant information.  You can 
effortlessly **add, delete and edit students or groups** and even **move students from one group to another**.

### Task Management

In addition to the efficient management of student and group information, ProfBook also aids with the 
**management of task information**.  You can quickly and seamlessly **allocate tasks to specific student or group** 
and track their progress. With ProfBook, you can easily monitor each stage of the task journey, 
from start to finish, ensuring optimal productivity.

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

1. Refer to the [General Commands](#general-commands), [Student or Group Commands](#student-or-group-commands), [Tasks Commands](#tasks-command) below for details of each command.

---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## Understanding ProfBook :books:

### ProfBook Software Structure

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
  
<box type="info">

  **Required format for `GroupId`:**
  - Must be in the format `grp-XXX` where `XXX` is replaceable with any 3 digit number
    - eg. `grp-001`
  
  </box>

**Student Directories (e.g., `1001Z`, `5815Y`, ...)**<br>
  Inside each Group Directory, you'll find Student Directories. These directories represent individual students 
within each group.

  This hierarchical structure enables a well-organized and efficient way to manage groups and students within ProfBook.

  <box type="info">
  
  **Required format for `StudentId`:**
  - Must be in the format `XXXXA` where `XXXX` is replaceable with any 4 digit number and `A` is replaceable with any
  capitalised alphabet.
    - - **Note:** StudentId here corresponds to the last 5 place of an NUS student's matriculation number and is not
        a StudentId created by you. This format helps you to identify students easily while maintaining 
    privacy and security.
      - eg. `8467U`
    </box>


### ProfBook Command Format

- Words in `UPPER_CASE` are the parameters to be supplied by you.
  * e.g. In `touch SPECIFIED_PATH -n NAME`, `SPECIFIED_PATH` and the other field in `UPPER_CASE` can be substituted 
  with the desired details to form `touch 2000Y --name Bob`.
* Words in `UPPER_CASE` that are surrounded by `[square brackets]` are optional parameters to be supplied by you.
  * e.g. In `cat [SPECIFIED_PATH]`, `[SPECIFIED_PATH]` can be substituted with details or left empty, 
      `cat 2000Y` and `cat` are acceptable commands.
- Parameters can be in any order.
  * e.g. If the command specifies `-n NAME -e EMAIL`, `-e EMAIL -n NAME` is also acceptable.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be  
  ignored. 
  * e.g. If the command specifies `help 123`, it will be interpreted as `help`.
- Duplicate can only be detected if all field is exactly the same.
* If you would like to pass in a field that starts with `\`, `-` or `--`, you would need to use `\` before the start of
that field. 
  * Else, fields that start with `-` or `--` will be treated as [flags](#flags) and fields that start with `\`
  will be treated without `\`(`\Orchard` when inputted will be treated as `Orchard`).
    * e.g. To pass in `-Clementi` in the `edit` command, the command would be `edit -a \-Clementi`.
- If you are using a PDF version of this document, please be careful when copying and pasting commands that span 
multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

### Flags :triangular_flag_on_post::triangular_flag_on_post::triangular_flag_on_post:
All flags begin with `--` or `-`.
Flags usually come before essential details required by the command and are compatible in both long and short forms:

- `--name` / `-n` followed by name of student or group specified by command.
- `--email` / `-e` followed by email of student. 
- `--phone` / `-p` followed by phone number of student.
- `--address` / `-a` followed by address of student.
- `--desc` followed by description of task specified by command.
- `--datetime` / `-dt` followed by due date of a deadline task in the `yyyy-MM-dd HH:mm` format.  
- `--all` / `-al` followed by either: 
  - `allStu` which adds **individual** tasks to all students under within the group directory.
    - can only be used at a group directory.  
  - `allGrp` which adds **group** tasks to all groups within the root directory.
    - can only be used at root directory.
- `--help` / `-h`
  - Use with any command (except `exit`, `help` and `clear`) for more information.
  - e.g. `touch --help` to learn more about how to add a student to ProfBook.

### Acceptable values for each parameter:

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
-Must be a **whole number**, which means it cannot contain decimals or fractions.
-Must fall **within the range of 1 and the size of the task list** of the specified student or group.

---
<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## How to use the User Guide :heart_eyes:
We understand that it may be daunting to delve into a completely new user guide. This section is designed 
to make this journey more smooth sailing for you and enhance your understanding of the commonly used icons and boxes. 
We hope you enjoy your reading experience! :blush: :book:

<box type="info">

**Light blue boxes** like this with an **"i" icon** are designated for information-related content.
</box>

<box type="tip">

Keep a look-out for **green boxes** like this with a **light bulb icon**, they provide valuable suggestions on
how you can better make use of the commands for greater efficiency and convenience once you have mastered 
the basics of the command. 
</box>

<box type="warning">

When you come across **yellow boxes** like this with a **"!" icon**, do take extra note and caution! <br>
It contains crucial cautionary information that is definitely a **must read**.
</box>

<box type="success" seamless>

This is utilized to indicate the output if command succeeds.:ok_woman:

</box>

<box type="wrong" seamless>

This is utilized to indicate the output if command fails.:no_good:

- There are [hyperlinks to some commonly made mistakes](#commonly-made-mistake) to help you recover from them!

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
- Must be a valid path to a group or root. 

<box type="success" seamless>

#### Output if command succeeds:
- Displays message indicating successful navigation to target path.

</box>

<box type="wrong" seamless>

#### Output if command fails:
- Displays message indicating either:
  - [Invalid command format.](#mistake-1-invalid-command-format)
  - [Invalid path.](#mistake-2-invalid-path)

</box>

#### Example(s):
- When you are at the directory `~/grp-001` and would like to change directory from `grp-001` to `grp-002` you could
use the command:
  -  `cd ../grp-002` ->  type `..` to step up one level before proceeding to the `grp-002` directory.

### Display Directories : `ls`

Shows the list of children in specified directory. 

**Format:** `ls [SPECIFIED_PATH]`

#### Acceptable values for each parameter:

`[SPECIFIED_PATH]`:
- Must be a valid path to a group or root.

<box type="info">
  
  If `[SPECIFIED_PATH]` is not provided, the `ls` command will show the list of children in the current directory.

</box>

<box type="success" seamless>

#### Output if command succeeds:

- Displays message indicating successful display of children list.
- Displays directories under target path on display panel.

</box>

<box type="wrong" seamless>

#### Output if command fails:

Displays message indicating either:
  - [Invalid command format.](#mistake-1-invalid-command-format)
  - [Invalid path.](#mistake-2-invalid-path)

</box>

#### Example(s):

- When you are at the root directory `~/` and would like to see all the groups you have,
    - `ls` will return all the current groups.

<box type="tip">

- When you are at the root directory `~/` and would like to see the students under `grp-001`,
  - `ls grp-001` will return all the students in `grp-001`.
</box>
  
### Display all tasks: `cat`

Displays all tasks depending on the directory you are at.

**Format:** `cat [SPECIFIED_PATH]`

#### Acceptable values for parameter:

`[SPECIFIED_PATH]`:
- Must be a valid path to a group or student.
- Must **not** be a path to root. 

<box type="info">

If `[SPECIFIED_PATH]` is not provided, the `ls` command will show the list of children in the current directory.

</box>

<box type="success" seamless>

#### Output if command succeeds:
- Displays list of tasks assigned under input path. 

</box>

<box type="wrong" seamless>

#### Output if command fails:
- If `cat` in root directory,
  - Displays message indicating that task list cannot be shown within root directory.

* If `cat` with invalid parameter, 
  - Displays message showing invalid path with user's input Path.

</box>

#### Example(s):
- When you are at the directory `~/grp-001` and would like to see all tasks allocated to `grp-001`,
  - `cat` will return all the tasks allocated to grp-001.

<box type="tip">

If you are at the directory `~/grp-001` and would like take a quick look of the tasks allocated to student `0010Y`,
- `cat 0010Y` will return all the tasks allocated to student of studentId, `0010Y`.

</box>

---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## Student or Group Commands :family:

### Add a student: `touch`

Adds a student into the specified directory.

**Format:** `touch SPECIFIED_PATH -n NAME [-e EMAIL] [-p PHONE_NUMBER] [-a ADDRESS]`

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
  - [Invalid command format.](#mistake-1-invalid-command-format)
  - [Invalid path.](#mistake-2-invalid-path)

</box>

#### Example(s):

- When you are at the directory `~/grp-001`, you could add a new student to this group through this command,
  - `touch 0200Y --name Bob`

<box type="tip">

When you are at the directory `~/grp-001` and would like to add a new student, Bob, to another directory 
`~/grp-002`, 
- You could use the command `touch ../grp-002/0200Y --name Bob` to save yourself the trouble for having to change 
  directory before executing the `touch` command!

</box>

#### To note:

- If the command is executed outside a specific group, the specified path must extend to encompass both the 
group and the student to ensure accurate execution.

### Edit a Student: `edit`
<box type="warning">

The `edit` command uses the same command word, `edit`, for both editing students and groups. 
For greater clarity, we have separated the sections for editing a student and group.<br> 
If you wish to edit a group instead, please refer to the dedicated section [here](#edit-a-group-edit). 
</box>

Edits a student's details including name, email, phone or address in the specified path. <br>
One or more fields can be edited in a single command.

**Format:** `edit SPECIFIED_PATH [-n NAME] [-e EMAIL] [-p PHONE_NUMBER] [-a ADDRESS]`

#### Acceptable values for each parameter:

`SPECIFIED_PATH`:  
- Must be a valid path to a student.

#### Output if command succeeds: 
- Displays message indicating successful edition of specified student. 

</box>

<box type="wrong" seamless> 

#### Output if command fails: 
- Displays message indicating either:
  - [Invalid command format.](#mistake-1-invalid-command-format)
  - [Invalid path.](#mistake-2-invalid-path)
  - At least one field (`NAME`, `EMAIL`, `PHONE_NUMBER` or `ADDRESS`)to edit must be provided.

</box>

#### Example(s):        
  
- If a student in `grp-001` of StudentId `0010Y` changes his phone number, you could execute the following command 
at the directory `~/grp-001`, to make the necessary changes. 
  - `edit 0010Y --phone 91919191`

<box type="tip">  

When you are at the root directory `~/` and would like to edit the same student's phone number,
- You could use the command `edit ~/grp-001/0010Y --phone 91919191` to save yourself the trouble for having to change 
directory before executing the `edit` command!
</box>

### Edit a Group: `edit`
<box type="warning">

The `edit` command uses the same command word, `edit`, for both editing students and groups.
For greater clarity, we have separated the sections for editing a student and group.<br>
If you wish to edit a student instead, please refer to the dedicated section [here](#edit-a-student-edit).
</box>

Edits a group's name in the specified path. <br>

**Format:** `edit [SPECIFIED_PATH] --name NAME`

#### Acceptable values for each parameter:

`[SPECIFIED_PATH]`:
- Must be a valid path to a group.

<box type="info">

If `[SPECIFIED_PATH]` is not provided, current directory must be a group directory.
The `edit` command will edit the group at current directory.  
</box>

#### Output if command succeeds:
- Displays message indicating successful edition of specified group.

</box>

<box type="wrong" seamless> 

#### Output if command fails:
- Displays message indicating either:
  - [Invalid command format.](#mistake-1-invalid-command-format)
  - [Invalid path.](#mistake-2-invalid-path)
  - At least one field (`NAME`) to edit must be provided.

</box>

#### Example(s):

- If `grp-001` changes their group name, you could execute the following command
  at the directory `~/grp-001`, to make the necessary changes.
  - `edit --name Amazing Group1`

<box type="tip">  

When you are at the root directory `~/` and would like to edit the same group's name,
- You could use the command `edit ~/grp-001 --name Amazing Group1` to save yourself the trouble for having to change
  directory before executing the `edit` command!
</box>

### Deletes a Student or Group: `rm`

Removes a student or group from the specified directory.

**Format:** `rm SPECIFIED_PATH`

#### Acceptable values for each parameter:

`SPECIFIED_PATH`:
- Must be a valid path to a student or group that you would like to delete.

<box type="success" seamless>

#### Output if command succeeds

- Displays message indicating successfully removal of student or group. 

</box>

<box type="wrong" seamless>

#### Output if command fails:

- Displays message indicating either:
    - [Invalid command format](#mistake-1-invalid-command-format)
    - [No such student or group to delete.](#mistake-3-no-such-student-or-group-to-delete)

</box>

#### Example(s):

- When you are at the root directory `~/` and would like to remove the group, `grp-001`,
    - `rm grp-001` will remove grp-001
  
  <box type="tip">
When you are at the root directory `~/` and would like to remove the student with the StudentId, `0123Y`,
in `~/grp-001`,
- You could use the command `rm ~/grp-001/0123Y` to save yourself the hassle for having to change directory!
</box>

### Move Student: `mv`

Moves student from one group to another group.

**Format:** `mv SPECIFIED_PATH_TO_STUDENT SPECIFIED_PATH_TO_GROUP`

#### Acceptable values for each parameter:

`SPECIFIED_PATH_TO_STUDENT`:

- Must be a valid path to a student that you would like to move.

`SPECIFIED_PATH_TO_GROUP`:

- Must be a valid path to a group that you would like to move the student to.

<box type="success" seamless>

#### Output if command succeeds

- Displays message to indicate the successful transferal of a student from one group to another.

</box>

<box type="wrong" seamless>

#### Output if command fails

- Displays message indicating either:
  - [Invalid command format.](#mistake-1-invalid-command-format)
  - [No such student to move.](#mistake-4-no-such-student-to-move)
  - Invalid destination path.

</box>

#### Example(s):
- In the beginning of the semester, student transfers amongst groups are common. When you are at the root directory 
`~/` you could move a student, `0123Y`, from `grp-001` to `grp-002` through this command easily,
  - `mv grp-001/0123Y grp-002`

<box type="tip">

When you are at the directory `~/grp-001` and would like to move a student to `grp-002` without changing to the 
root directory,
- You could use the command `mv 0123Y ../grp-002`!

</box>

### Create Group : `mkdir`

Creates a group that can contain students.

**Format:** `mkdir SPECIFIED_PATH_TO_GROUP --name NAME`

#### Acceptable values for each parameter:

`SPECIFIED_PATH_TO_GROUP`:
- Must be a valid path to a group.

<box type="success" seamless>

#### Output if command succeeds
- Displays message indicating successful creation and information of the new group.

</box>

<box type="wrong" seamless>

#### Output if command fails

- Displays message indicating either:
    - [Invalid command format.](#mistake-1-invalid-command-format)
    - Invalid path to a group.

</box>

#### Example(s):

- When you are at the root directory `~/` and would like to add a new group with GroupId `grp-001`, 
  - `mkdir grp-001 --name Group 001`

### View help : `help`

Shows a message of some commands you could use.

**Format:** `help`

### Clearing all entries : `clear`

Clears all entries from ProfBook.

**Format:** `clear`

<box type="warning">

The `clear` command, upon confirmation by pressing enter, will clear all entries from ProfBook. 
It is crucial to note that there is **no way to recover these entries** once the `clear` command has been executed. 
Therefore, we strongly advise exercising caution when using this command.
</box>

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

## Tasks command :clipboard:

### Create Todo Task : `todo`

Creates todo tasks for specific student(s) or group(s).

**Format:** `todo [SPECIFIED_PATH] --desc DESCRIPTION [--all CATEGORY]`

#### Acceptable values for each parameter: 

`[SPECIFIED_PATH]`:    
- Must be a valid path to a student or group. 

<box type="info">

If `[SPECIFIED_PATH]` is not provided, current directory must be a group directory. <br>
If you use `--all allStu`, the command will create todo task for all the students within this group directory.
Else, the command will create a todo task for the group at current directory.
</box>

<box type="success" seamless>

#### Output if command succeeds

- Displays message indicating the successful creation of the todo task.

</box>

<box type="wrong" seamless>

#### Output if command fails:

- Displays message indicating either:
  - [Invalid command format.](#mistake-1-invalid-command-format)
  - [Invalid path.](#mistake-2-invalid-path)

</box>

#### Example(s):

- When a specific student, `0010Y`, has to redo his tutorial, you could use the following command when you are in the directory 
`~/grp-001` to allocate the task to just this student,
    - `todo 0001Y --desc Redo tutorial`

<box type="tip">

When you have an assignment, `Assignment 1` to be allocated to **all your students** in `grp-001`, you could use the 
following command, 
- `todo ~/grp-001 --desc Assignment 1 --all allStu`

</box>


### Create Deadline task : `deadline`

Creates task with a deadline for specific student(s) or group(s).

**Format:** `deadline [SPECIFIED_PATH] --desc DESCRIPTION --datetime DATE_AND_TIME [--all CATEGORY]`

#### Acceptable values for each parameter:  
`[SPECIFIED_PATH]`:
- Must be a valid path to a student or group.

<box type="info">

If `[SPECIFIED_PATH]` is not provided, current directory must be a group directory. <br>
If you use `--all allStu`, the command will create deadline task for all the students within this group directory.
Else, the command will create a deadline task for the group at current directory.
</box> 

<box type="success" seamless>

#### Output if command succeeds

- Displays message indicating the successful creation of the deadline task.

</box>

<box type="wrong" seamless>

#### Output if command fails:

- Displays message indicating either:
  - [Invalid command format.](#mistake-1-invalid-command-format)
  - [Invalid path.](#mistake-2-invalid-path)

</box>

#### Example(s):

- When a specific student has to hand in his assignment again by a certain date, 
you could use the following command when you are in the directory `~/grp-001`,
  - `deadline 0001Y --desc resubmit Assignment 1 --datetime 2023-10-11 23:59`

<box type="tip">

When you have a task, `Project 1 submission` with a deadline to be allocated to **all your groups**, 
you could use the following command at the root directory, `~/`,
- `deadline --desc Project 1 submission --datetime 2023-10-11 23:59 --all allGrp`

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
  - [Invalid command format](#mistake-1-invalid-command-format)
  - [Invalid task index.](#mistake-5-invalid-task-index)
  - [The display panel is not showing task list.](#mistake-6-the-display-panel-is-not-showing-task-list)

</box>

<box type="warning">

When using this command, you will first need to [cat](#display-all-tasks-cat) at the path where the task list is at before executing `mark`.
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

- Displays message indicating unmark is done successfully as well as specific task that is unmarked.

</box>

<box type="wrong" seamless>

#### Output if command fails:

- Displays message indicating either:
  - [Invalid command format](#mistake-1-invalid-command-format)
  - [Invalid task index.](#mistake-5-invalid-task-index)
  - [The display panel is not showing task list.](#mistake-6-the-display-panel-is-not-showing-task-list)
</box>

<box type="warning">

When using this command, you will first need to [cat](#display-all-tasks-cat) at the path where the task list is at before executing `unmark`.
</box>

### Deletes task: `rmt`

Removes a task according to specified index number.

**Format:** `rmt TASK_INDEX`

<box type="success" seamless>

#### Output if command succeeds:

- Displays message indicating removal of task is done successfully as well as specific task that is removed.

</box>

<box type="wrong" seamless>

#### Output if command fails:

- Displays message indicating either:
  - [Invalid command format](#mistake-1-invalid-command-format)
  - [Invalid task index.](#mistake-5-invalid-task-index)
  - [The display panel is not showing task list.](#mistake-6-the-display-panel-is-not-showing-task-list)
    </box>

<box type="warning">

When using this command, you will first need to [cat](#display-all-tasks-cat) at the path where the task list 
is at before executing `rmt`.
</box>

#### Example(s):

- You are at `~/grp-001` would like to delete the first task allocated to student with StudentId, `0123Y`,
  - `cat 0123Y`
  - `delete 1`

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


---

<div class="page-break-before">
    <!-- Content that will start on a new printed page -->
</div>

## Commonly made mistake

<box type="warning">

As Albert Einstein wisely said, "A person who never made a mistake never tried anything new"
</box>

At ProfBook, we wholeheartedly believe that mistakes are part of the learning journey. We are here to support
you in your quest of mastering ProfBook, helping you recover from any hiccups that may happen along the way. 
As such, we have collated a list of some commonly made mistakes and solutions to them. :muscle:

##### Mistake 1: Invalid command format
**Solution:**
Ensure that the command you enter adheres to the format provided in the user guide.

##### Mistake 2: Invalid path
**Solution:**
Please check that the path you have entered is the path required by the command. Additionally, do ensure that it is
the intended path you wish you utilise, and it exists within ProBook.

##### Mistake 3: No such student or group to delete.
**Solution:**
Verify that the StudentId or GroupId belonging to the student or group you wish to delete is correct
and that the student or group exists in the specified path from which you wish to delete them.

##### Mistake 4: No such student to move.
**Solution:**
Verify that the StudentId belonging to the student you wish to move is correct
and that the student exists in the specified path from which you wish to move them.

##### Mistake 5: Invalid task index
**Solution:**
Task index starts from 1. Check that the index you input is a whole number and falls within the range 
of 1 to the total number of tasks in the task list.

##### Mistake 6: The display panel is not showing task list
**Solution:**
Please do not forget to execute the [`cat`](#display-all-tasks-cat) command prior to executing the required command.

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
| Action                      | Format, Example(s)                                                                                                                                                                   |
|:----------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**                    | `help`                                                                                                                                                                               |
| **Clear all entries**       | `clear`                                                                                                                                                                              |
| **Exit the program**        | `exit`                                                                                                                                                                               |
| **Change Directory**        | `cd SPECIFIED_PATH` <br> e.g., `cd ../grp-001`                                                                                                                                       |
| **Display Directories**     | `ls [SPECIFIED_PATH]` <br> e.g., `ls grp-001`                                                                                                                                        |
| **Display Task List**       | `cat [SPECIFIED_PATH]`<br> e.g., `cat 1234A, cat grp-001`                                                                                                                            |
| **Add Student**             | `touch SPECIFIED_PATH -n NAME [-e EMAIL] [-p PHONE_NUMBER] [-a ADDRESS]` <br> e.g., `touch 2000Y --name Bob --email bobby@example.com --phone 92929292 --address blk 258 Toa Payoh ` |
| **Edit Student**            | `edit SPECIFIED_PATH [-n NAME] [-e EMAIL] [-p PHONE_NUMBER] [-a ADDRESS]` <br> e.g., `edit 0010Y --phone 91919191`                                                                   |
| **Create Group**            | `mkdir SPECIFIED_PATH_TO_GROUP --name NAME` <br> e.g., `mkdir grp-001 --name Group 001`                                                                                              |
| **Delete Student or Group** | `rm SPECIFIED_PATH` <br> e.g., `rm 0123Y`, `rm grp-002`                                                                                                                              |
| **Move Student**                | `mv SPECIFIED_PATH_TO_STUDENT SPECIFIED_PATH_TO_GROUP`  <br> e.g., `mv grp-001/0123Y grp-002`                                                                                                                                                                                   |
| **Create Todo**             | `todo [SPECIFIED_PATH] --desc DESCRIPTION [--all CATEGORY]` <br> e.g., `todo 2000Y --desc Assignment 1`                                                                              |
| **Create Deadline**         | `deadline [SPECIFIED_PATH] --desc DESCRIPTION --datetime DATE_AND_TIME [--all CATEGORY]`<br> e.g., `deadline 2000Y --desc Assignment 1 --datetime 2023-10-11 23:59 `                 |
| **Mark**                    | `mark TASK_INDEX`<br> e.g.,`mark 1`                                                                                                                                                  |
| **Unmark**                  | `unmark TASK_INDEX`<br> e.g.,`unmark 2`                                                                                                                                              |
| **Delete Task**             | `rmt TASK_INDEX`<br> e.g.,`rmt 1`                                                                                                                                                    |
