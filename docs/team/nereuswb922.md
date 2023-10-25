---
layout: default.md
title: Nereus Ng Wei Bin's Project Portfolio Page
---

# ProfBook

ProfBook is a desktop address book application used for easy management of tasks, group schedules, and notes for every
tutorial slot and group. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in
Java, and has about 10 kLoC.

## RepoSense Report

**Link to my RepoSense Report
**: [Code Contributions](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=nereuswb922&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos).

## Code Contributions

In this section, I will outline the code contributions I made to the project. These contributions include enhancements
and new features that I implemented.

### Class Enhancements

1. **Introduced `Path` Class :**
    - Provided a structured way to manage and manipulate paths in our application.
    - This addition assists other classes (e.g. `StateManager`, `Command`) in providing functionality such as navigating
      between directories and executing commands against specific paths.
    - The `AbsolutePath` class represents a full path within our application.
    - The `RelativePath` class represents a relative path in relation to the current path.

2. **Introduced `Id` class :**
    - Represent unique id of group and student.

2. **Introduced `Option` class :**
    - Similar to the `Prefix` class in AB3.
    - Enables the creation of command options with short-hand and long-hand names.

3. **Updated `ArgumentTokernizer` class :**
    - Updated `ArgumentTokenizer` class to adopt command format similar to the Unix command.
    - e.g. User can pass in name option using `--name` or  `-n`.

### New Features

1. **`cd` Command :**
    - The `cd` command allows users to navigate between different directories.
    - For example, using `cd ../grp-001` will navigate to the `grp-001` directory.

2. **`ls` Command :**
    - The `ls` command shows a list of directories under the current directory.
    - For example, `ls grp-001` will show the list of students under group with ID `grp-001`.

3. **`cat` Command :**
    - The `cat` command displays the task list under a specific path.
    - For example, `cat grp-001/stu-001` will show the task list of the student with ID `stu-001`.

### GUI Enhancements

1. **Visual Improvements**:  
   Added a new icon and expanded the color palette to enhance the project's visual appeal.

2. **Current Directory Display**:  
   Added a working directory display, similar to a terminal, simplifying navigation and enhancing users' understanding
   of the application's model.

## User Guide Contributions

coming soon

## Developer Guide Contributions

coming soon

## Team-Based Task Contributions

1. **Github Setup**:
    - Established our team's **organization** and **repo** on GitHub.
    - Setup **GitHub project** to manage issues related to user stories and tasks.
    - Setup project **website deployment**.

3. **Integration of GitHub Plugins**:
    - **Netifly :** Enables automatic deployments of our PR previews.
    - **Codecov :** Automate code coverage assessment, ensuring that our project maintains high code quality and
      identifying areas for improvement.

## Review and Mentoring Contributions

Here are the pull requests I reviewed and provided valuable feedback and suggestions:

- **[Pull Request #71](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/71)**
- **[Pull Request #91](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/91)**
- **[Pull Request #100](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/100)**
- **[Pull Request #102](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/102)**
- **[Pull Request #113](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/113)**
- **[Pull Request #115](https://github.com/AY2324S1-CS2103T-W15-2/tp/pull/115)**
