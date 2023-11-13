---
layout: default.md
title: Nidhish Krishnan's Project Portfolio Page
---

## Project: ProfBook

ProfBook is a desktop application used for easy management of a variety of tasks, students and groups. 
The user interacts with it using a CLI, and has a GUI created with JavaFX.

ProfBook is a desktop application used for easy management of a variety of tasks, students and groups.
The user interacts with it using a CLI, and has a GUI created with JavaFX. It is tailor-made to mimic the familiar terminal experience for our target users by introducing Linux-style commands, Dynamic commands and a terminal like structure and interface. It is written in Java, and has about 10 kLoC.

## RepoSense Report

**Link to my RepoSense Report**: 
[Code Contributions](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=Nid21cs&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos).

## Code Contributions

In this section, I will outline the code contributions I made to the project. These contributions include enhancements
and new features that I implemented.

### Class Enhancements

Given below are my contributions to the project.

1. **Introduced `Storage` Class :**
   
    - Provides methods to convert ProfBook application to Json format and read it back into our project upon start up.
    - Each part of ProfBook storage is managed by seperate classes within storage such as
      - `Root` managed by `JsonAdaptedRoot`.
      - `Group` managed by `JsonAdaptedGroup`.
      - `Student` managed by `JsonAdaptedStudent`.
      - `Task` managed by `JsonAdaptedTasks`.
      - `Deadline` managed by `JsonAdaptedDeadline`.
      - `ToDo` managed by `JsonAdaptedToDo`.
      - Overall storage managed by `ProfBookStorageManager`.

2. **Introduced `task` package :**
   
   - Contains classes which control tasks for Groups and students, e.g `Todo`, `Deadline`.
   - Controls behaviour of the tasks itself
   - Contains methods to manage and change each task as well as the taskList

## User Guide Contributions
**Non-Feature Aspects**

  - How to use User Guide  
  - Exciting future developments

**Feature Aspects**

- `todo` command 
- `deadline` command 
- `mark` command
## Developer Guide Contributions

1. **Storage component**:
   - Changed UML diagram for storage to better match our ProfBook implementation of storage.
   - Explained the storage component briefly.
   
2. **Task component**:
   - Added UML diagrams for Task package to describe the structure of out task package.
   - Described briefly the task component with explanation to how it links to the product.
   
3. **Use cases**:
   - Added use cases to show possible workflow.

4. **Non-Functional Requirements**:
   - Added non-functional requirements to the applications to describe our system capabilities and constraints.

5. **User stories**:
   - Added User stories to the DG to articulate how our features will be valuable to a customer.
   - Added it in the form of a table with a priority level, and the level of experience of the user.

6. **Glossary**:
   - Added glossary of the terms used in our project for users to better understand our product.

## Team-Based Task Contributions

* Transfer user stories with description from Google Docs to GitHub.
* Handled v1.3 release.
* After Pe-D handled bug management and kept track of issues.
* Added in glossary, User stories, NFRs to the developer  guide.

## Review and Mentoring Contributions

Here are the pull requests I reviewed and provided valuable feedback and suggestions:

**[Pull Request #269](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/269)**|
**[Pull Request #262](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/262)**|
**[Pull Request #91](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/91)**|
