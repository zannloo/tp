---
layout: default.md
title: Ching Ming Yuan's Project Portfolio Page
---

### Project: ProfBook

ProfBook is a desktop address book application used for easy management of tasks, group schedules, and notes for every
tutorial slot and group. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in
Java, and has about 10 kLoC.

Given below are my contributions to the project.

### Features Contribution

* **Dynamic Commands**:
    * What it does: Allow user's command to have different effect based on their current location in the file structure
    * Justification:
        * Cuts down on the number of command that the user have to remember
        * Commands become more intuitive and determinant as it's effect depends on their environment
        * Safer command execution as we can cross-check the current environment with the command
    * Highlights:
        * Implementation of this required an in-depth analysis of design alternative.
        * There were many ways of achieving the same result, but I believe the current implementation is most in
          accordance with OOP's SOLID principal.
        * Implementation were also made generic to allow for future extensions if needed.
        * Implementation was challenging as it required modifying the whole flow and structure of the application

* **Streamline state modifications**:
    * What it does: Provide a safe way for the command to modify the state
    * Justification:
        * Modification of the state should be done through a state manager instead of directly being modified by an
          external class in accordance with OOP principals
        * Allow for the future migration to immutable state
    * Highlights:
        * Implementation of this required an in-depth analysis of design alternative as there was a need for a generic
          way to modify all the current different classes alongside future classes.

### Classes Contribution

* **Introduced TaskListManager Class**:

    * Manages the required operations of TaskList
    * Ensure the expected behaviour with regard to managing the tasks
        * No Duplicate tasks
        * Able to accept subclasses of Tasks
        * Other classes not being able to directly modify tasks
* **Introduced ChildManager Class**

    * Manages the operation required to modify the children
    * Ensure the expected behaviour with regard to managing the children
        * No Duplicate children across the whole profbook
        * Children are identified by their Unique ID
        * Other classes not being able to directly modify children

* **Introduced Student Class**

    * Encapsulates the required information to represent student

* **Introduced Group Class**

    * Encapsulates the required information to represent a group within tutorial group

* **Introduced Root Class**

    * Encapsulates the required information to represent the whole application

* **Enhanced StateManager Class**
    * Provides the logic to safely manipulate data stored in Student/Group/Root
    * Ensure separation of concern by implementing other two other classes, TaskOperation and ChildOperation,
      each handling their own operation.
        * Ensure the expected behaviour with regard to manipulating the state
            * Path given matches the expected outcome
            * Commands are unable to directly modify the state

### **Code contributed**:

[RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=w15&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=mingyuanc&tabRepo=AY2324S1-CS2103T-W15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

### Project Management:

* Managed Releases v1.2 on GitHub

### Documentations:

* User Guide:
    * Non-Feature specific
        * Transferred initial command description from Google documents into Markdown format
        * Wrote the overview of the whole project
        * Wrote the overview for all three of our main features
        * Updated the command summary page
    * Developer Guide:
        * to be added soon.


* **Community**:
    * PRs reviewed (with non-trivial review comments):
        * **[Pull Request #71](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/71)**
        * **[Pull Request #87](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/87)**
        * **[Pull Request #113](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/113)**
        * **[Pull Request #115](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/115)**
        * **[Pull Request #117](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/117)**
        * **[Pull Request #118](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/118)**
