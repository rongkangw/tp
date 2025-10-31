---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# EASync Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**
We would like to acknowledge the contributions of the following projects,
libraries, and communities that made EASync possible.

### Ideas, Code, Documentation
The **SE-EDU Initiative** and the developers of [AddressBook-Level 3 (AB3)](https://se-education.org/addressbook-level3/), whose codebase and documentation provided the
foundation for EASync. Both the architecture and the graphical user interface of EASync were adapted from
the original **AddressBook** project.

### Third-Party Libraries
1. [JavaFX](https://openjfx.io) - For the Graphical User Interface (GUI).
2. [JUnit5](https://junit.org) - For unit testing and integration testing.
3. [Jackson](https://github.com/FasterXML/jackson) - For JSON parsing and serialization.

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2526S1-CS2103T-T11-3/tp/tree/master/src/main/java/seedu/club/Main.java) and [`MainApp`](https://github.com/AY2526S1-CS2103T-T11-3/tp/tree/master/src/main/java/seedu/club/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2526S1-CS2103T-T11-3/tp/tree/master/src/main/java/seedu/club/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `MemberListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2526S1-CS2103T-T11-3/tp/tree/master/src/main/java/seedu/club/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2526S1-CS2103T-T11-3/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Member` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2526S1-CS2103T-T11-3/tp/tree/master/src/main/java/seedu/club/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("deleteMember 1")` API call as an example.

<puml src="diagrams/DeleteMemberSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteMemberCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `ClubBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteMemberCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteMemberCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a member).<br>
   Although this is shown as a single step in the diagram above (for simplicity), it can take several interactions (between the command object and the `Model`) to complete.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `ClubBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddMemberCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddMemberCommand`) which the `ClubBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddMemberCommandParser`, `DeleteMemberCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible, such as during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2526S1-CS2103T-T11-3/tp/tree/master/src/main/java/seedu/club/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the club book data i.e., all `Member` objects (which are contained in a `UniqueMemberList` object).
* stores the currently 'selected' `Member`/`Event` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Member>`/`ObservableList<Event>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)


### Storage component

**API** : [`Storage.java`](https://github.com/AY2526S1-CS2103T-T11-3/tp/tree/master/src/main/java/seedu/club/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both club book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `ClubBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.club.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Assigning a member to an event
The implementation of the event command follows the standard command parsing workflow, where ClubBookParser will parse the input string into an executable command.
When the user enters `assignEvent m/<MEMBERNAME> e/<EVENTNAME> [r/<EVENTROLE>]...`, ClubBookParser will first create an AssignEventCommandParser, which will then obtain the values corresponding to the prefixes `m/`, `e/`, and if applicable, `r/`  . If an error is encountered during parsing, a ParseException will be thrown.


Otherwise, AssignEventCommandParser will obtain the values as a Name object for Member name and Event name, and the roles as a Set of Event Role objects. It then creates a new instance of AssignEventCommand using these objects as parameters.

Upon execution, AssignEventCommand will check if the Event Role set is empty or not. If it is, it will only add the Member to the Event’s roster. If Event Roles are specified, it will add the Member to the Event’s roster and add a corresponding Event Role object to the Member’s event roles list.

The Event’s entry is then shown.

### Display event feature

The implementation of the display event command follows the standard command parsing workflow. When the user enters `event <INDEX>`,
`ClubBookParser` will parse the input string into an executable command. `ClubBookParser` will
first create `DisplayEventCommandParser` to parse the input string. If an error is encountered during parsing, `ClubBookParser`
will throw a `ParseException`.

Otherwise, `DisplayEventCommandParser` will obtain the index from
the user's input and create a new instance of `DisplayEventCommand`. `DisplayEventCommand` includes a `targetIndex`
which is the zero based index of the event to be displayed in the filtered event list.

Executing `DisplayEventCommand` will update the model's view state to be `SINGLE_EVENT`, filter the event list to only
include the selected event and filter the member list to only include the event's member roster.
The two lists are then shown.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile: Student Club Manager**

* has a need to manage a significant number of members' and participants' contacts and events
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:

EASync provides a student club manager with a centralised system to efficiently manage members’ and participants’ contact information and events, helping them stay organised. Optimised for users who prefer typing as input, it simplifies management of roles, relationships, and member turnover without relying on slow, cluttered spreadsheets or manual tracking.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                       | I want to …​                                                                       | So that I can…​                                                 |
|----------|-------------------------------|------------------------------------------------------------------------------------|-----------------------------------------------------------------|
| `*`      | new club manager              | easily purge sample data                                                           | start fresh with my own club info                               |
| `*`      | club manager                  | detect duplicate entries (based on name, email, missing fields)                    | keep my contact lists accurate                                  |
| `*`      | club manager                  | separate clubs by tabs                                                             | clubs do not get mixed up                                       |
| `*`      | club manager                  | view a summary of contact health (e.g. duplicates, missing fields)                 | clean up when needed                                            |
| `*`      | club manager                  | add additional notes to specific members (e.g. dietary restrictions/photo consent) | plan events smoother                                            |
| `*`      | club manager                  | simulate role reassignments before committing                                      | plan transitions without disrupting current setups              |
| `*`      | club manager                  | export an event's participant list with roles                                      | share it with my committee                                      |
| `*`      | club manager                  | export a member's timeline and role history                                        | prepare handover documents or performance reviews               |
| `*`      | experienced club manager      | chain commands (e.g. addMember && assignRole)                                      | execute multiple actions in one go                              |
| `*`      | experienced/lazy club manager | use keyboard shortcuts for repetitive tasks                                        | save time                                                       |
| `*`      | experienced/lazy manager      | look at my command history                                                         | quickly reuse the previous command without retyping             |
| `*`      | lazy club manager             | press on a member's email address to create a new email                            | send emails quickly without having to copy their email address  |
| `*`      | impatient club manager        | press tab to autocomplete commands                                                 | complete what i need to do faster                               |
| `*`      | club manager                  | undo the last command                                                              | recover from any mistake                                        |
| `* *`    | new club manager              | explore sample data                                                                | understand how EASync works before committing                   |
| `* *`    | club manager                  | edit members' memberal details                                                     | update them when information changes                            |
| `* *`    | club manager                  | restore archived members                                                           | re-engage them if they return                                   |
| `* *`    | club manager                  | validate contact fields (e.g., missing email, malformed tags)                      | catch errors before they affect workflows                       |
| `* *`    | club manager                  | search for members by their name, email, role, or by events                        | find them quickly                                               |
| `* *`    | club manager                  | check the attendance of the club’s members                                         | keep track of their overall club participation                  |
| `* *`    | club manager                  | bulk edit members details (eg. membership status)                                  | update records efficiently                                      |
| `* *`    | club manager                  | archive members who are no longer active                                           | keep my workspace focused on current contributors.              |
| `* *`    | club manager                  | view current role distribution across events                                       | identify over- or under-utilized members                        |
| `* *`    | club manager                  | attach notes to a member profile                                                   | remember preferences, strengths, or past issues                 |
| `* *`    | club manager                  | be informed of duplicate events or members when adding new ones                    | keep member/event lists clean                                   |
| `* *`    | busy/forgetful club manager   | be highlighted to important or upcoming events                                     | keep track of my schedule                                       |
| `* *`    | club manager                  | check upcoming events/list events chronologically                                  | remember what events are happening when                         |
| `* *`    | new club manager              | see a list of all possible commands                                                | know how to carry out what I want to do with the app            |
| `* * *`  | club manager                  | add a new member’s memberal details such as name, phone number, and email address  | keep track of member information                                |
| `* * *`  | club manager                  | view a list of all members                                                         | check who is part of the club                                   |
| `* * *`  | club manager                  | delete members                                                                     | remove members who have left the club                           |
| `* * *`  | club manager                  | tag members with appointed roles                                                   | identify and keep track of key appointment holders              |
| `* * *`  | club manager                  | create events with dates, participant lists, and details                           | easily manage club events                                       |
| `* * *`  | club manager                  | delete events                                                                      | remove events that are cancelled or have already passed         |
| `* * *`  | club manager                  | backup my club book                                                             | avoid losing data when I leave the app                          |

### Use cases

(For all use cases below, the **System** is `EASync` and the **Actor** is the `user`, unless specified otherwise)

**UC01: Adding a new Member**

**Preconditions**
* App is open

**Guarantees**
* Addition of new member will not affect other members in existing list of members

**MSS**
1. User requests to add member
2. System displays success message
3. System displays the new member in the member list

   Use case ends

**Extensions**

* 1a. Missing required parameters in member description (e.g. name, phone)

    * 1a1. System informs user of missing field(s)

      Use case ends

* 1b. Member with the same name, email address, and phone number as an existing member entry is entered in

    * 1b1. System informs user of duplicated member

      Use case ends

**UC02: Adding a new Event**

**Preconditions**
* App is open

**Guarantees**
* Addition of new event will not affect other events in existing list of events
* If a new event is added, it will be displayed at the top of the list of events

**MSS**
1. User requests to add an event
2. System displays a success message
3. System creates a new event
4. System displays the new event created in the list of events

   Use case ends

**Extensions**

* 1a. One or more of the parameters are missing

    * 1a1. System informs user of missing fields

      Use case ends

* 1b. One or more of the parameters are of invalid format

    * 1b1. System informs user of incorrect fields

      Use case ends

* 1c. Event with the same name, to and from datetimes are entered in

    * 1c1. System informs user of duplicated event

      Use case ends

**UC03: Editing a Member**

**Preconditions**
* The member to be edited must be in the list of members

**Guarantees**
* Member will be displayed with the updated information

**MSS**

1.  User requests to edit the specified member
2.  System displays success message
3.  System displays the updated member

    Use case ends

**Extensions**

* 1a. Incorrect parameters within command (e.g. event index out of range)
    * 1a1. System informs user of incorrect parameters

      Use case ends

* 1b. No edited fields are provided
    * 1b1. System informs user of missing parameters

      Use case ends


**UC04: Editing an Event**

**Preconditions**
* The event to be edited must be in the list of events

**Guarantees**
* Event will be displayed with the updated information

**MSS**

1.  User requests to edit the specified event
2.  System displays success message
3.  System displays the updated event

    Use case ends

**Extensions**

* 1a. Incorrect parameters within command (e.g. event index out of range)
    * 1a1. System informs user of incorrect parameters

      Use case ends

* 1b. No edited fields are provided
    * 1a1. System informs user of missing parameters

      Use case ends

**UC05: Removing a Member**

**Preconditions**
* Member to be removed must exist in the list of members
* User has to be on the member list.

**Guarantees**
* Member will be removed from the list of members
* Updated member list will be displayed

**MSS**
1. User requests to remove a member
2. System deletes the member
3. System displays success message and member list without the removed member

    Use case ends

**Extensions**

* 2a. The list is empty

  Use case ends

* 3a. System detects an error in the command (e.g., missing or incorrect fields)

    * 3a1. System shows an error message

      Use case resumes at step 2

**UC06: Removing an Event**

**Preconditions**

* The event to be removed to must exist in the list of events
* User has to be on the event list.

**Guarantees**

* The event is removed from the list of events

**MSS**

1. User requests to remove an event
2. System displays success message
3. System displays event list without the removed event

    Use case ends

**Extensions**

* 1a. Missing required parameters in command (e.g. event index)
    * 1a1. System informs user of missing fields

      Use case ends

* 1b. Incorrect parameters within command (e.g. event index out of range)
    * 1b1. System informs user of incorrect parameters

      Use case ends

**UC07: Displaying an event’s details**

**Preconditions**

* The event must exist
* User has to be on the event list.

**Guarantees**

* Displays a specific event’s member roster

**MSS**

1. User requests to display a specific event
2. System displays success message
3. System displays event’s details and member roster

    Use case ends

**Extensions**

* 1a. Missing required parameter in command (e.g. event index)

    * 1a1. System informs user of missing field

        Use case ends

* 1b. Incorrect parameter within command (e.g. event index out of range)

    * 1b1. System informs user of incorrect parameter

        Use case ends

**UC08: Assigning a member to an event**

**Preconditions**

* The member and event must exist
* The member must not have been already assigned to the event

**Guarantees**

* The member will be added to the event’s roster

**MSS**

1. User requests to assign a member to an event
2. System displays success message
3. System updates event’s roster with the assigned member
4. System displays event’s details(UC08)
        Use case ends

**Extensions**

* 1a. Missing required parameters in command (e.g. member name)

    * 1a1. System informs user of missing fields

        Use case ends

* 1b. Invalid parameters within command (e.g. blank or non-alphanumeric characters)

    * 1b1. System informs user of invalid parameters

        Use case ends

* 1c. Member is already assigned to the event

    * 1c1. System informs user that the member is already assigned to the event

        Use case ends

* 1d. Event role is specified in user request and event role exists under the event

    * 1d1. System displays success message
    * 1d2. System displays event’s roster with the assigned member, with the event role added to the member

        Use case ends

* 1e. Event role is specified in user request but event role does not exist under the event

    * 1e1. System informs user that the event role does not exist under the event

        Use case ends

**UC09: Unassigning a member from an event**

**Preconditions**

* The member and event must exist
* The member must have already been assigned to the event

**Guarantees**

* The member will be removed from the event’s roster
* Any event roles that the member may have under that event will also be removed

**MSS**

1. User requests to unassign a member from an event
2. System displays success message
3. System displays event’s roster with the unassigned member removed
        Use case ends

**Extensions**

* 1a. Missing required parameters in command (e.g. member name)

    * 1a1. System informs user of missing fields

        Use case ends

* 1b. Invalid parameters within command (e.g. blank or non-alphanumeric characters)

    * 1b1. System informs user of invalid parameters

        Use case ends

* 1c. Member or event does not exist

    * 1c1. System informs user that the member or event cannot be found

        Use case ends

**UC10: Unassigning an event role from a member**

**Preconditions**
* The member and event must exist
* The member must have already been assigned to the event
* The member must have an event role related to that event

**Guarantees**

* The event role will be removed from the member’s event roles list

**MSS**

1. User requests to unassign an event role from a member
2. System displays success message
3. System displays member list with the member’s event role removed
        Use case ends

**Extensions**

* 1a. Missing required parameters in command (e.g. event role)

    * 1a1. System informs user of missing fields

        Use case ends

* 1b. Invalid parameters within command (e.g. blank or non-alphanumeric characters)

    * 1b1. System informs user of invalid parameters

        Use case ends

* 1c. Member, event, or event role does not exist

    * 1c1. System informs user that the member, event, or event role cannot be found

        Use case ends


### Non-Functional Requirements

1. Scalability
   1. The system must support managing at least 100 members and 50 events without noticeable degradation in performance by the user.
2. Portability
   1. The system must be able to run on Windows, macOS, and Linux as long as Java 17 is installed.
   2. The system must run without requiring a separate installer. A single distributable JAR must be sufficient to run the program.
3. Reliability
   1.  The system shall remain operational without unexpected termination under all valid user operations and shall provide descriptive error messages for at least 90% invalid inputs tested.
4. Data Integrity
   1. The system must automatically save to local storage after each successful command by the user to prevent data loss to a maximum of 30 seconds of user activity in the event of an application crash or unexpected closure.
   2. Data must be stored locally in a human-readable and editable text file format, as JSON.
   3. The application must function without relying on any remote server to operate.
5. Performance
   1. All user commands (e.g., addEvent, deleteMember) should be processed within 1 second under normal usage.
   2. The system should complete data loading from local storage within 2 seconds on startup.
   3. The executable JAR should operate using less than 100MB of memory under normal usage (e.g., with 100 events and 50 members).
   4. Assets (e.g., images, libraries) must not be unnecessarily large or included unless strictly required
6. Usability
   1. Command parameters should only be defined format (e.g. n/, f/, dt/) for consistency.
   2. All commands must be able to be completed by the user using only typed commands.
      GUI interaction  must be secondary, rather than being the primary input mode.
7. Single-User Model
   1. The product must only support one user. Data files must not be shared or accessed concurrently by multiple users.
8. Screen Resolution Compatibility
   1. The GUI must be clearly visible and easily clickable at 1920×1080 resolution (100% and 125% scaling).
   2. The GUI must remain visible at 1280×720 resolution (150% scaling).
9. PDF-Friendly Documentation
   1. All project documentation (DG/UG) must be exportable and viewable as static PDFs without expandable panels, embedded media, or interactive elements.


### Glossary

* **Member**: A member who is part of the club and participates in club activities and events.
* **Event**: A scheduled activity organised by the club with a name, date/time, and optional details.
* **Role**: A responsibility or position a member can hold for the club or an event (e.g., “Logistics”, “Treasurer”, "MC").
* **Command**: A structured text instruction typed by the user following the app’s syntax (e.g. addEvent, deleteMember).
* **Command Line**: A space for typing text commands, serving as the main way for the user to interact with the application.
* **Index**: A unique number representing the position of a member or event in the displayed list. It is 1-based (starts from 1).
* **Parameter**: A short identifier placed before input in a command to indicate the information type (e.g., n/ for name,  f/ for from-date).
* **JAR (Java Archive)**: The file format used to package and deliver the application, containing everything needed for it to run.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Run `java -jar EASync.jar` in a terminal.<br>
      Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by running `java -jar EASync.jar` in a terminal. <br>
      Expected: The most recent window size and location is retained.

### Adding a member and an event
1. Adding a member to the member list

    1. Prerequisites: A member with the same name should not exist in the member list.

    2. Test case: `addMember n/John Doe p/98765432 e/johnd@example.com`<br>
       Expected: Member is added to the member list. Details of the added member are shown in the status message.
                 Member list is shown.

    3. Test case: `addMember n/Jane Doe p/98765432 e/janed@example.com r/President`<br>
       Expected: Similar to previous.

    4. Test case: `addMember n/James Doe p/78765432 e/jamesd@example.com`<br>
       Expected: Member is not added to the member list. Error details shown in the status message.

2. Adding a duplicate member to the member list

    1. Prerequisites: A member with the same name should exist in the member list.

    2. Test case: `addMember n/Jane Doe p/98766432 e/janed@example.com r/President`<br>
       Expected: Member is not added to the member list. Error details shown in the status message.

3. Adding an event to the event list

    1. Prerequisites: An event with the same name should not exist in the event list.

    2. Test case: `addEvent n/Team Bonding f/151025 1300 t/161025 1500`<br>
       Expected: Event is added to the event list. Details of the added event are shown in the status message. Event list is shown.

    3. Test case: `addEvent n/Orientation f/151025 1200 t/171025 1800 d/For freshmen r/gamemaster`<br>
       Expected: Similar to previous.

    4. Test case: `addEvent n/Movie Night f/151025 2:00pm t/161025 3:00pm`<br>
       Expected: Event is not added to the event list. Error details shown in the status message.

4. Adding a duplicate event to the event list

    1. Prerequisites: An event with the same name should exist in the event list.

    2. Test case: `addEvent n/Team Bonding f/151025 1300 t/161025 1500`<br>
       Expected: Event is not added to the event list. Error details shown in the status message.

### Listing members and events

1. Listing a non-empty member list

    1. Prerequisites: Member list must not be empty.

    2. Test case: `listMembers`<br>
       Expected: Member list is shown. Success message is shown in the status message.

    3. Test case: `list Members`<br>
       Expected: Member list is not shown. Error details shown in the status message.

2. Listing an empty member list

    1. Prerequisites: Member list must be empty.

    2. Test case: `listMembers`<br>
       Expected: No members are shown in member list. Status message indicates there are no members.

3. Listing a non-empty event list

    1. Prerequisites: Event list must not be empty.

    2. Test case: `listEvents`<br>
       Expected: Event list is shown. Success message is shown in the status message.

    3. Test case: `list Events`<br>
       Expected: No event list is shown. Error details are shown in the status message.

4. Listing an empty event list

    1. Prerequisites: Event list must be empty.

    2. Test case: `listEvents`<br>
       Expected: No events are shown in event list. Status message indicates there are no events.

### Displaying an event

1. Displaying an event with its member roster

    1. Prerequisites: List all events using the `listEvents` command. Multiple events in the list.

    2. Test case: `event 1` <br>
       Expected: First event and event's member roster is shown. Success message is shown in the status message.

    3. Test case: `event 0` <br>
       Expected: No event is displayed. Error details shown in the status message.

    4. Other incorrect event commands to try: `event`, `event x`, `...` (where x is negative or larger than the list size) <br>

### Deleting a member and an event

1. Deleting a member

   1. Prerequisites: List all members using the `listMembers` command. Multiple members in the member list.

   2. Test case: `deleteMember 1`<br>
      Expected: First member is deleted from the member list. Details of the deleted member shown in the status message.

   3. Test case: `deleteMember 0`<br>
      Expected: No member is deleted. Error details shown in the status message.

   4. Other incorrect delete commands to try: `deleteMember`, `deleteMember x`, `...` (where x is negative or larger than the list size)<br>
      Expected: Similar to previous.

2. Deleting a event

    1. Prerequisites: List all events using the `listEvents` command. Multiple events in the event list.

    2. Test case: `deleteEvent 1`<br>
       Expected: First event is deleted from the event list. Details of the deleted event shown in the status message.

    3. Test case: `deleteEvent 0`<br>
       Expected: No event is deleted. Error details shown in the status message.

    4. Other incorrect delete commands to try: `deleteEvent`, `deleteEvent x`, `...` (where x is negative or larger than the list size)<br>
       Expected: Similar to previous.

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_

--------------------------------------------------------------------------------------------------------------------
## **Appendix: Planned Enhancements**

Team Size: 5

##### 1. Allow users to use event command consecutively
Currently, after running the `event <INDEX>` command, users must return to the event list before executing the command
again. We plan to remove this restriction by storing the filtered event list,
enabling users to re-run the command directly from the single event display page, allowing for more efficient lookup for events.

#### 2. Adding an `assignEventRole` command
Currently, if users want to change a member's event role in an event after they have already been assigned, they must first
unassign the member and then run `assignEvent` again with the new role(s). We plan to add an `assignEventRole` command to allow
updating of the member's event role directly if they are already assigned to the event. This will reduce repetitive command usage
and streamline the event role changing process.
