---
layout: page
title: Ching Ming Yuan's Project Portfolio Page
---

### Project: ProfBook

ProfBook is a desktop address book application used for easy management of tasks, group schedules, and notes for every
tutorial slot and group. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in
Java, and has about 10 kLoC.

Given below are my contributions to the project.

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

* **Code contributed**:
  [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=w15&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=mingyuanc&tabRepo=AY2324S1-CS2103T-W15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

* **Project management**:
    * to be added soon.

* **Enhancements to existing features**:
    * to be added soon.

* **Documentation**:
    * User Guide:
        * to be added soon.
    * Developer Guide:
        * to be added soon.

* **Community**:
    * PRs reviewed (with non-trivial review comments): to be added soon.
    * Contributed to forum discussions (examples: to be added soon)
    * Reported bugs and suggestions for other teams in the class (examples: to be added soon)
    * Some parts of the history feature I added was adopted by several other class mates (to be added soon)

* **Tools**:
    * to be added soon

