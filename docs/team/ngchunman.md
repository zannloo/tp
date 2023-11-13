---
layout: default.md
title: Ng Chun Man's Project Portfolio Page
---

## Project: ProfBook

ProfBook is a desktop address book application used for easy management of tasks, group schedules, and notes for every tutorial slot and group. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

### Code Contributions:

**Link to my RepoSense Report**: [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=NgChunMan&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos).

### Class Enhancements:

1. **Introduced `CreateGroupCommand` Class**:
   - Creates a group that can contain students.
   - For example, `mkdir grp-001 -n Group 1` will create a group named Group 1.


2. **Introduced `MoveStudentToGroupCommand` Class**:
   - Moves student from one group to another group.
   - For example, we are in `grp-001` directory, `mv 0123Y ../grp-002` will move the student 0123Y who is initially in grp-001 to grp-002.


3. **Introduced `EditCommand` Class**:
   - Edits a student's details including name, email, phone, address or StudentId in the specified path.
   - For example, `edit 0010Y -n Jacky -p 123456` will change the name and phone of a student with studentId 0010Y to Jacky and 123456 respectively.
   - Edits a group's name or GroupId in the specified path.
   - For example, `edit ~/grp-001 -n Amazing Group1` will change the name of group with GroupId grp-001 to Amazing Group1.


4. **Introduced `EditGroupDescriptor` Class**:
   - Represents the descriptor for editing the details of a group.
   - Helps to track which group's field that the user wanted to edit.


5. **Introduced `EditStudentDescriptor` Class**:
   - Represents the descriptor for editing the details of a student in a group.
   - Helps to track which student's field that the user wanted to edit.


### Enhancements to existing features:
1. `mkdir` command
   * Creates a group that can contain students.


2. `mv` command
   * Moves student from one group to another group.


2. `edit` command
   * Edits the details of a student or group.

### User Guide Contributions
    * to be added soon.

### Developer Guide Contributions
    * to be added soon.

### Team-Based Task Contributions

- Wrapped up milestone v1.3.trial and released JAR file.

### Review and Mentoring Contributions

Here are the pull requests I reviewed and provided valuable feedback and suggestions:
- **[Pull Request #113](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/113)**
- **[Pull Request #144](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/144)**
- **[Pull Request #161](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/161)**
- **[Pull Request #199](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/199)**
- **[Pull Request #268](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/268)**


