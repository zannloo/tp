---
layout: default.md
title: Ching Ming Yuan's Project Portfolio Page
---

### Project: ProfBook

ProfBook is a desktop address book application used for easy management of tasks, group schedules, and notes for every
tutorial slot and group. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is tailor-made
to mimic the familiar terminal experience for our target users by introducing Linux-style commands, Dynamic commands and
a terminal like structure and interface. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

#### [Link to RepoSense Report :link:](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=mingyuanc&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos).

### Features Contribution

* **Designed the ProfBook folder structure**:
  * This folder structure allows our target users to organise their groups and students in an intuitive way
  * Justification:
    * The folder structure closely mimics the natural hierarchy that our target user are familiar to.
    * Such a structure would allow us to create more tailor-fit features such as Dynamic command and terminal-like
      structure.
  * Highlights:
    * Deep understanding of OOP principals was needed to craft classes generic yet robust enough to capture the 
      behaviour of each hierarchy. This proved to be difficult as in-depth analysis of design alternative were required
    * Implementation was challenging as it was hard to integrate with our dynamic commands.

### Classes Contribution

* **Introduced `TaskListManager` and `ChildManager` Class**:
    * Manages the required operations and reduces duplicated code.
    * Ensure that operations do not result in duplication
    * Enhances extensibility of code.
* **Introduced `TaskOperation` and `ChildOperation`  Class**
    * Provided an operation class to allow `Command` logic to manipulate the model without exposing internal details.
    * Justification:
        * Follows OOP principals more closely.
        * Allows for the future migration to an immutable model.
        * Adds another layer of safety by checking the validity and presence of the directory before performing modification.
    * Highlights:
        * Deep-understanding of what is required by `Command` class was required when designing this class.
* **Introduced `ChildrenAndTaskListManager` Class**
  * Wrapper class that manages the operations required for both TaskList and Children.
* **Introduced `Student`, `Group`, `Root` Class**
  * Encapsulates the required information to uniquely represent the respective objects.
* **Enhanced `ModelManager` Class**
  * Provides the logic to safely manipulate data stored in Student/Group/Root

### Project Management:

* Managed Releases v1.2 on GitHub
* Created and assigned issues for V1.3, V1.4
* Integrated **Codecov** to automate code coverage assessment of PR.

## Documentations:

### User Guide Contributions
* **Non-Feature Aspects**
  * Transferred initial command description from Google documents into Markdown format.
  * Provided skeleton format for each command so that teammates are able to more efficiently populate their features.
  * Wrote the overview of the whole project and three of our main features.
  * Wrote guides on how input flag works.
  * Updated the command summary page.
* **Feature Aspects:**
  * Wrote guide for `clear`, `cat`, `help`, `exit`

### Developer Guide Contributions
* **Non-Feature Aspects**
    * Migrated old documents to follow ProfBook new requirements and style.
    * Updated introduction paragraphs.
    * Proofread peer's contributions and performed changes for a more seamless reading experience.
    * Wrote proposed future features
* **Feature Aspects:**
    * Wrote and created UML for `Model` component including the `ProfBook` subpackages.
    * Wrote and created UML for the implementation of
      * Folder structure
      * Moving students
      * Adding a student/group

### Community:
* PRs reviewed (with non-trivial review comments):
<div class="pull-request-container">

**[PR #71](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/71)** |
**[PR #87](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/87)** |
**[PR #113](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/113)** |
**[PR #115](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/115)** |
**[PR #117](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/117)** |
**[PR #118](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/118)** |
**[PR #128](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/128)** |
**[PR #286](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/286)** |
**[PR #289](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/289)** |

</div>

* Contributions to forum:
<div class="pull-request-container">

**[Shared my GUI](https://github.com/nus-cs2103-AY2324S1/forum/issues/101#issuecomment-1706285315)** |
**[Help solved an issue](https://github.com/nus-cs2103-AY2324S1/forum/issues/110#issuecomment-1709913495)** |

</div>