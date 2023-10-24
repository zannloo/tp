---
layout: default.md
title: Ng Chun Man's Project Portfolio Page
---

### Project: ProfBook

ProfBook is a desktop address book application used for easy management of tasks, group schedules, and notes for every tutorial slot and group. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: To be added soon.
  * What it does: to be added soon.
  * Justification: to be added soon.
  * Highlights: to be added soon.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Feature**: to be added soon.

* **Project management**:
  * to be added soon.

### **Code contributed**:
**Link to my RepoSense Report**: [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=NgChunMan&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

### Classes contribution:
1. **Introduced CreateGroupCommand Class**:
   - Creates a group in ProfBook with the specified relative path.
   - For example, `mkdir grp-001 -n Group 1` will create a group named Group 1.


2. **Introduced MoveStudentToGroupCommand Class**:
   - Moves a student from one group to another group.
   - For example, we are in `grp-001` directory, `mv stu-001 ../grp-002` will move the student stu-001 who are initially in grp-001 to grp-002.


3. **Introduced EditCommand Class**:
   - Edits the details of a student or a group in ProfBook.
   - Student particular (name, phone, email, address and id) can be edited through this command.
   - Group particular (name and id) can be edited through this command.
   - For example, `edit stu-001 -n Jacky -p 123456` will change the name and phone of a student with studentId stu-001 to Jack and 123456 respectively.

4. **Introduced HelpCommand Class**:
   - Displays program usage instructions to the user.
   - For example, `help` will display the program usage instructions to the user.


5. **Introduced EditGroupDescriptor Class**:
   - Represents the descriptor for editing the details of a group in ProfBook
   - Helps to track which group's field that the user wanted to edit


6. **Introduced EditStudentDescriptor Class**:
   - Represents the descriptor for editing the details of a student in a group
   - Helps to track which student's field that the user wanted to edit


* **Enhancements to existing features**:
  * to be added soon.

* **Documentation**:
  * User Guide:
    * to be added soon.
  * Developer Guide:
    * to be added soon.

* **Community**:
  * PRs reviewed (with non-trivial review comments):
    - **[Pull Request #112](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/112)**
    - **[Pull Request #113](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/113)**
    - **[Pull Request #114](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/114)**
    - **[Pull Request #115](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/115)**
    - **[Pull Request #135](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/135)**

