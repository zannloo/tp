---
layout: default.md
title: Ng Chun Man's Project Portfolio Page
---

### Project: ProfBook

ProfBook is a desktop address book application used for easy management of tasks, group schedules, and notes for every tutorial slot and group. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

### Code Contributions:

**Link to my RepoSense Report**: [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=NgChunMan&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos).

### Class Enhancements:

1. **Introduced `CreateGroupCommand` Class**:
   - Creates a group under the root directory.
   - Ensure the expected behaviour with regard to creating a group.
     - No duplicate groups with the same GroupId across the whole ProfBook.
     - Every group is identified by their Unique ID.
     - Command format is valid.


2. **Introduced `MoveStudentToGroupCommand` Class**:
   - Moves student from one group to another group.
   - Ensure the expected behaviour with regard to moving a student.
     - Paths to both source group and destination group are valid.
     - Source group consists of the student to be moved.
     - Destination group exists in ProfBook.
     - Command format is valid.


3. **Introduced `EditCommand` Class**:
   - Edits a student's details including name, email, phone, address or StudentId in the specified path.
   - Edits a group's name or GroupId in the specified path.


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

* **Non-Feature Aspects:**
   * Quick Start
   * Commonly made mistakes

* **Feature Aspects:**
   * `edit` command.
   * `mv` command.
   * `mkdir` command.

### Developer Guide Contributions

* Added the **Appendix: Instructions for manual testing** section.
* Created json files to illustrate the sample output of `profbook.json` after adding todo task to a student, removing a student and clear the saved data.
* Created a new folder called sample under docs folder to store the json files.

### Team-Based Task Contributions

- Wrapped up milestone v1.3.trial and released JAR file.
- Wrapped up milestone v1.4 and released JAR file.

### Review and Mentoring Contributions

Here are the pull requests I reviewed and provided valuable feedback and suggestions:
- **[Pull Request #113](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/113)**
- **[Pull Request #144](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/144)**
- **[Pull Request #161](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/161)**
- **[Pull Request #199](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/199)**
- **[Pull Request #268](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/268)**

