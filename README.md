
# CSCI 1302 - Minesweeper Alpha v2022.sp

![Approved for: Spring 2022](https://img.shields.io/badge/Approved%20for-Spring%202022-purple) 

![Unrelated image of mine."](mine.jpg)

This repository contains the skeleton code for the Minesweeper Alpha project
assigned to the students in the Spring 2022 CSCI 1302 classes
at the University of Georgia.

**Please read the entirety of this file before beginning your project.**

There are different deadline options for this project. Students who
perform their final submission via the `submit` command before the date/times listed
below automatically receive the associated **Submission-Based (SB) extra credit**.
The late penalty does not start applying until after the final date listed.

* **WED 2022-02-09 (Feb 9th) @ 11:55 PM EST (`+20` SB Extra Credit)**
* **FRI 2022-02-11 (Feb 11th) @ 11:55 PM EST (`+10` SB Extra Credit)**
* **SUN 2022-02-13 (Feb 13th) @ 11:55 PM EST (`+0` SB Extra Credit)**

**Seriously. Read this entire file *before* starting.**

## Table of Contents

* [Academic Honesty](#academic-honesty)
* [Course-Specific Learning Outcomes](#course-specific-learning-outcomes)
* [Updates](#updates)
* [Suggested Checklist](#suggested-checklist)
* [Project Description](#project-description)
  * [Note Concerning No Recursion](#note-concerning-no-recursion)
  * [Minesweeper Overview](#minesweeper-overview)
  * [The Grid and Interface](#the-grid-and-interface)
    * [The Grid](#the-grid)
    * [The User Interface](#the-user-interface)
    * [Command Syntax Format](#command-syntax-format)
    * [Revealing a Square](#revealing-a-square)
    * [Mark Command](#mark-command)
    * [Guess Command](#guess-command)
    * [No Fog Command](#no-fog-command)
    * [Help Command](#help-command)
    * [Quit Command](#quit-command)
    * [Player Wins](#player-wins)
  * [Seed Files](#seed-files)
  * [Displaying Errors](#displaying-errors)
* [Minesweeper Oracle](#minesweeper-oracle)
* [Project Requirements & Grading](#project-requirements--grading)
  * [Functional Requirements](#functional-requirements)
  * [Non-Functional Requirements](#non-functional-requirements)
* [Suggestions](#suggestions)
* [How to Download the Project](#how-to-download-the-project)
* [Submission Instructions](#submission-instructions)
* [Appendix](#appendix)
  * [Minefield Output Examples](#minefield-output-examples)
  * [Test Case Examples](#test-case-examples)

## Academic Honesty

**You agree to the Academic Honesty policy as outlined in the course syllabus.**
In accordance with this notice, I must caution you **not** to
fork this repository on GitHub if you have an account. Doing so will more than
likely make your copy of the project publicly visible. Please follow the
instructions contained in the [How to Download the Project](#how-to-download-the-project)
section below in order to do your development on Odin. Furthermore, you must adhere
to the copyright notice and licensing information at the bottom of this document.

## Course-Specific Learning Outcomes

* **LO1.a:** Navigate and modify files, directories, and permissions in a
  multi-user Unix-like environment.
* **LO1.b:** (Partial) Execute, redirect, pipe, and manage programs/processes
  in a multi-user Unix-like environment.
* **LO1.c:** Create and modify text files and source code using a powerful
  terminal-based text editor such as Emacs or Vi.
* **LO1.d:** (Partial)	Use shell commands to compile new and existing software
  solutions that are organized into multi-level packages and have external
  dependencies.
* **LO2.b:** (Partial) Define, throw, and propagate exceptions appropriately in
  a software solution.
* **LO3.a:** Create and update source code that adheres to established style
  guidelines.
* **LO3.b:** (Partial) Create class, interface, method, and inline documentation
  that satisfies a set of requirements.
* **LO7.c:** (Partial) Use common abstract data types and structures, including
  lists, queues, arrays, and stacks in solving typical problems.

## Updates

**Updates will be posted in this section.**
If there is an update and you have already cloned the project to Odin,
then you can update your copy of the project using the `$ git pull`
command while inside of your project directory. In many cases, this just
updates your copy of the `README.md` file; however, it is possible for
an update to affect other files as well. If other files are affected, then
they will be mentioned in the update summary.

## Suggested Checklist

To help you with planning out this project, here are some suggested steps you can take that
your instructors believe will help you complete the project more easily. Some of the
items in this checklist may not make sense until you have read the entire project description,
including the appendices. These steps are suggesions and, therefore, do not constitute an
exhaustive list of steps that you may need to take to complete the project.

**Preparation:** (Finish before Monday, Jan. 31st)

- [x] Start reading the project description.
- [ ] Read through the entire project description, including the appendices,
      **and write down questions as you go.**
- [ ] Read it again! This time, you may be able to answer some of your own
      questions.

**Planning:** (Finish before Tuesday, Feb. 1st)

- [ ] Play the Minesweeper game using the provided oracle. This will help you see
      how to run the program and allow you to see expected input / output for a
	  variety of scenarios.
- [ ] Plan how you will represent the Minesweeper board. The internal representation
      should not contain all of the characters that the user sees when
	  the board is printed (e.g., you don't need to include vertical bars or index
	  numbers in your array since they can be printed as you loop over your array).
      One idea of how to approach this is given under
	  [suggestions](#suggestions).
- [ ] Unsure of your chosen board representation? Feel free to come by office hours to discuss!
- [ ] Write out test cases. Make sure to consider edge cases.

**Documenting:** (Finish before Friday, Feb. 4th)

- [ ] Type out the method signatures (not the full methods - just the signatures) for each method listed in the
      [functional requirements](#functional-requirements)
- [ ] Add proper Javadoc comments to all methods and both classes. Don't implement the methods yet.
      This will give you an opportunity to think about each method a little bit
	  more before you start writing the code. Also, these comments serve as
      a convenient reference while writing the code. Remember, if you
      can't describe, in detail, what each method does, you can't implement it.

**Implementing:** (Finish before Monday, Feb. 7th)

- [ ] Start by declaring the instance variables of the `MinesweeperGame` class based
      on your chosen board representation.
- [ ] For testing and debugging purposes, it's a good idea to implement the
      `printMineField` method early in the implementation phase.
- [ ] Create seed files with various board dimensions. Test that your
      `printMineField` method works with each.
- [ ] Implement `printWelcome`, `printWin`, `printLoss` using the banners given
      later in this document.
- [ ] Implement the main game loop and the commands one at a time, testing each as you go.
- [ ] Double check all output formatting and error conditions.
- [ ] Implement the `isWon` method with the proper win conditions.
- [ ] Implement any remaining methods

**Testing** (Finish before Tuesday, Feb. 8th)

- [ ] Create seed files for testing. Be sure to vary the dimensions of the board,
      the number of mines and the mine locations. Also be sure to include invalid
	  configurations.
- [ ] Create input and output files for each seed file. Run your code as described
      later in this document. In the testing phase, try to avoid entering commands
	  manually while playing the game. The commands should come
      from the input files at this point.

**Review** (Finish before Wednesday, Feb. 9th)

- [ ] Do one final pass through the project document making sure you didn't
      miss anything.
- [ ] Run your code through your test cases one last time.
- [ ] Submit your code to Odin.

## Project Description

This first project is meant to ensure that you are able to apply and extend
your prerequisite knowledge as well as introduce you to developing and testing
a Java application in a Linux environment (i.e., the Odin development
server). You should be familiar with the majority of aspects of this project through
your introductory programming course and the work so far from 1302. However, you
may also be asked to do things that you have never been given explicit directions for
before. This is just a part of software development. Sometimes you need to research
how to solve a problem in order to implement a solution. That being said,
the material included in this document should hopefully answer the majority
of your questions.

Your goal is to develop a **non-recursive**, **non-GUI** (GUI = Graphical User
Interface) version of the game called **Minesweeper**. The code for this game will
be organized in such a way that the recursive elements of Minesweeper can
be added at a later point in time, if desired. It will also be organized so that
you can add a GUI to it later as well. Interestingly, the organization of some of the
classes in this project will also introduce you to some elementary aspects of
game programming.

If you are not familiar with Minesweeper as a game, we recommend playing a free, online
version of the game. Just make sure to ignore the recursive elements of the game that uncovers
more than one cell in a single click (more below). Our version will only do one cell at a time. After
you've familiarized yourself with the game, play the oracle a few times to see the expected
1302 implementation. You should also consult the
[Wikipedia entry](https://en.wikipedia.org/wiki/Minesweeper_(video_game))
for the game, ignoring any mentions of recursion. Once you're familiar
with the basic gameplay, then continue reading this project description.

### Note Concerning "No Recursion"

In a traditional game of Minesweeper, when the player "reveals" a square
that does not contain a mine, **two** things happen:

1. A number representing the number of mines in the (up to) eight
   adjacent squares is placed in the revealed square; and

1. If the number of adjacent mines is zero, then game goes ahead
   and "reveals" all of the (up to) eight adjacent squares.

The second part mentioned above can cause one reveal made by the user
to result in multiple reveals in the minefield. This behavior is what
the literature is referring to when it talks about recursion in Mineweeper.
**Your game should not support this behavior**. If the user reveals
one square, then, at most, one square is revealed in the minefield.

### Minesweeper Overview

In your Minesweeper, the player is initially presented with a grid of
undifferentiated squares. Some of those squares contain hidden mines. The
size of the grid, the number of mines, and the individual mine locations
are set in advance by a seed file (more on that later) that the user
specifies as a command-line argument to your program. The ratio of the
number of mines to the grid size is often used as a
measure of an individual game's difficulty. The grid size can also be
represented in terms of the number of rows and columns in the grid.
In this project description, we may refer to the _grid_ or to the
_minefield_. Both of these terms mean the same thing. Furthermore,
we will use the term _square_ to denote a location in the minefield, even
in situations where a location may be visually rectangular instead
of perfectly square.

The game is played in rounds. During each round, the player is presented with
the grid, the number of rounds completed so far, as well as a prompt. The player
has the option to do 6 different things, each of which is briefly listed
below and explained in great detail in later sections:

 1. Reveal a square on the grid.
 2. Mark a square as potentially containing a mine.
 3. Mark a square as definitely containing a mine.
 4. Lift the fog of war (cheat code).
 5. Display help information.
 6. Quit the game.

When the player reveals a square of the grid, different things can happen:

* If the revealed square contains a mine, then the player loses the game.

* If the revealed square does not contain a mine, then a digit is instead displayed
  in the square, indicating how many adjacent squares contain mines. Typically,
  there are 8 squares adjacent to any given square, unless the square lies on an
  edge or corner of the grid. The player uses this information to deduce the contents
  of other squares, and may perform any of the first three options in the list presented above.

* When the player marks a square as potentially containing a mine, a `?` is displayed
  in the square. This provides the user with a way to note those places that they
  believe may contain a mine but are not sure enough to mark as definitely containing
  a mine.

* When the player marks a square as definitely containing a mine, a flag, denoted
  by the character `F`, is displayed in the square.

To simplify the game mechanics, **the player may mark, guess, or reveal any square in the grid,
even squares that have already been marked or revealed.** In other words, the player may issue a
command to mark, guess, or reveal a square, regardless of its current state. The logic for
determining what happens to the square is always the same. For example, if a square has been
revealed and the user marks it as definitely containing a mine then a round is consumed and the
square should be marked. The user would then have to reveal this square again later. This may
not be consistent with how you've played Minesweeper in the past but it will make it easier
to code. We will leave it up to the user to be smart about how they play!

<a id="win-conditions">

The game is won only when **both** of the following conditions are met:

* All squares containing a mine are marked as _definitely_ containing a mine; and
* All squares not containing a mine are revealed.

At the end of the game, the player is presented with a score. Let `rows`, `cols`,
and  `rounds` denote the number of rows in the grid, columns in the grid, and
number of rounds completed, respectively. A **round** is defined as one successful
iteration of the main game loop. Therefore, only valid commands result in a round
being consumed. To be clear, _rounds_ is not quite the same as the number of commands
entered (some may be invalid); however, it should be less than or equal to that number.

The player's score is calculated as follows:

```java
score = 100.0 * rows * cols / rounds;
```

A score of `100` would denote a perfect game. In this version of Mineweeper, it should
not be possible for the player to win the game in less than `(rows * cols)`-many rounds
(take a second to convince yourself of this fact).
Therefore, any game in which the player exceeds that many rounds would result in a score
that is less than `100`. When displaying the score, the number should always be printed
with two digits following the decimal point.

### The Grid and Interface

When the game begins, the following **welcome banner** should be displayed to the player
once and only once:

```
        _
  /\/\ (F)_ __   ___  _____      _____  ___ _ __   ___ _ __
 /    \| | '_ \ / _ \/ __\ \ /\ / / _ \/ _ \ '_ \ / _ \ '__|
/ /\/\ \ | | | |  __/\__ \\ V  V /  __/  __/ |_) |  __/ |
\/    \/_|_| |_|\___||___/ \_/\_/ \___|\___| .__/ \___|_|
                             ALPHA EDITION |_| v2022.sp
```

Take care when printing this message out to the screen. Depending on how you implement this
part, you may need to escape some of the characters in order for them to show up correctly.
A copy of this welcome banner is contained in this [`README.md`](README.md) file and in
[`resources/welcome.txt`](resources/welcome.txt).

In this Minesweeper game, the initial game configuration is loaded from a
[seed file](#seed-files); the player provides the path to a *seed file* when as
a command-line argument to the program. Two pieces of of information that are read
from the seed file are the number of rows and the number of columns which together
specify the grid size (i.e., the size of the minefield).

The number of rows and the number of columns need not be the same. Rows and columns
are indexed starting at `0`. Therefore, in a `10`-by-`10` (rows-by-columns),
the first row is indexed as `0` and the last row is indexed as `9` (similarly for columns).
In a `5`-by-`8` game, the row indices are from `0` to `4`, while the column indices
are from `0` to `7`, respectively.

#### The Grid

Let's assume we are playing a `5`-by-`5` game of Minesweeper. When the game
starts, the interface should look like this:

```

 Rounds Completed: 0

 0 |   |   |   |   |   |
 1 |   |   |   |   |   |
 2 |   |   |   |   |   |
 3 |   |   |   |   |   |
 4 |   |   |   |   |   |
     0   1   2   3   4

minesweeper-alpha:
```

Let's assume we are playing a `10`-by-`10` game of Minesweeper. When the game
starts, the interface should look like this:

```

 Rounds Completed: 0

 0 |   |   |   |   |   |   |   |   |   |   |
 1 |   |   |   |   |   |   |   |   |   |   |
 2 |   |   |   |   |   |   |   |   |   |   |
 3 |   |   |   |   |   |   |   |   |   |   |
 4 |   |   |   |   |   |   |   |   |   |   |
 5 |   |   |   |   |   |   |   |   |   |   |
 6 |   |   |   |   |   |   |   |   |   |   |
 7 |   |   |   |   |   |   |   |   |   |   |
 8 |   |   |   |   |   |   |   |   |   |   |
 9 |   |   |   |   |   |   |   |   |   |   |
     0   1   2   3   4   5   6   7   8   9

minesweeper-alpha:
```

Please note that the in either example, the first, third, and second-to-last lines are blank
(the lines before and after "Rounds Completed" and the line before the prompt).
All other lines, except the last line containing the prompt, start with one blank space.
The line containing the prompt contains an extra space after the `:`
so that **when the user types in a command, the text does not touch the
`:`.** Multiple output examples are provided in the [Appendix](#minefield-output-examples)
of this project description for your convenience.

#### The User Interface

The possible commands that can be entered into the game's prompt as well as their
syntax are listed in the subsections below. Commands with leading or trailing
whitespace are to be interpreted as if there were no leading or trailing
whitespace. For example, the following two examples should be interpreted the
same:

```
minesweeper-alpha: help
minesweeper-alpha:         help
```

Although it's hard to see in the example above, trailing whitespace should
also be ignored. That is, if the user types ` ` one or more times before
pressing the `RET` (return) key, then those extra whitespaces should be
ignored.

The different parts of a command are known as tokens. The `help`
command, for example, only has one token. Other commands, such as the
`mark` (seen below) have more than one token because other
pieces of information are needed in order to interpret the command. As a quick
example (which will be explored in more depth below), the player can
mark the square at coordinate (0,0) using `mark` as follows:

```
minesweeper-alpha: mark 0 0
```

In the above example, you can see that the `mark` command has three
tokens. A command with more than one token is still considered syntactically
correct if there is more than one white space between tokens. For example, the
following four examples should be interpreted the same:

```
minesweeper-alpha: mark 0 0
minesweeper-alpha: mark     0  0
minesweeper-alpha:     mark 0 0
minesweeper-alpha:   mark     0  0
```

As a reminder, trailing whitespace is ignored.

#### Advice on Reading Commands

All valid game commands are entered on a single line. Implementers should always
use the `nextLine()` method of their one and only *standard input* `Scanner`
object to retrieve an entire line of input for a command as a `String`. Once
an entire line is retrieved, it can be parsed using various methods; however,
implementers may find it useful to construct a new `Scanner` object using
the line as its source so that they can scan over the individual tokens.
To put this into perspective, taking the "make a `Scanner` from the line"
approach would make it so you can handle all four examples at the end
of the last sub-section with one set of code.

Here's some example code:

```java
// Assume you have have a Scanner object that reads from System.in called stdIn
String fullCommand = stdIn.nextLine();  // reads the full command from the user (Ex: command may contain "reveal 1 3")

// Create a new Scanner to parse the tokens from the given command
Scanner commandScan = new Scanner(fullCommand); // Neat! A new use of Scanner. :)

// Now, we can call our regular Scanner methods to get each part of the assigned command
String command = commandScan.next(); // command would contain "reveal" from if given the command above.

// Continue to call additional Scanner methods (nextInt(), etc.) to parse out the other tokens from the full command.
```

#### Command Syntax Format

In the sections below, we describe the syntax format that each command must
adhere to in order to be considered correct. Unknown commands and commands
that are known but syntactically incorrect are considered invalid.
Information about displaying errors related to invalid commands is
contained in [a later section](#displaying-errors) in this document.

**Please do not confuse this syntax with regular expressions, a topic that
will not be covered in this course.** You are NOT meant to put this weird
looking syntax into any code. It is purely meant to convey to you, the reader,
what is and what is not valid input for a given command.

In a syntax format string, one or more non-new-line white space is represented
as a `-`. Command tokens are enclosed in `[]` braces. If the
contents of a token are surrounded by `""` marks, then that token can
only take on that literal value. If more than one literal value is accepted for
a token, then the quoted literals are separated by `/`. If the
contents of a token are surrounded by `()` marks, then that token can
only take on a value of the type expressed in parentheses. Note: the literal
values are case-sensitive. So, "ReVeal" is not the same as "reveal".

#### Revealing a Square

In order to reveal a square, the `reveal` or `r` command
is used. The syntax format for this command is as follows: `-["reveal"/"r"]-[(int)]-[(int)]-`.
The second and third tokens indicate the row and column indices, respectively,
of the square to be revealed.

Let's go back to our `10`-by-`10` example. Suppose that we secretly know that there is
a mine in squares (1,1) and (1,3). Now suppose that the player wants to reveal
square (1, 2). Here is an example of what that might look like.

```

 Rounds Completed: 0

 0 |   |   |   |   |   |   |   |   |   |   |
 1 |   |   |   |   |   |   |   |   |   |   |
 2 |   |   |   |   |   |   |   |   |   |   |
 3 |   |   |   |   |   |   |   |   |   |   |
 4 |   |   |   |   |   |   |   |   |   |   |
 5 |   |   |   |   |   |   |   |   |   |   |
 6 |   |   |   |   |   |   |   |   |   |   |
 7 |   |   |   |   |   |   |   |   |   |   |
 8 |   |   |   |   |   |   |   |   |   |   |
 9 |   |   |   |   |   |   |   |   |   |   |
     0   1   2   3   4   5   6   7   8   9

minesweeper-alpha: r 1 2

 Rounds Completed: 1

 0 |   |   |   |   |   |   |   |   |   |   |
 1 |   |   | 2 |   |   |   |   |   |   |   |
 2 |   |   |   |   |   |   |   |   |   |   |
 3 |   |   |   |   |   |   |   |   |   |   |
 4 |   |   |   |   |   |   |   |   |   |   |
 5 |   |   |   |   |   |   |   |   |   |   |
 6 |   |   |   |   |   |   |   |   |   |   |
 7 |   |   |   |   |   |   |   |   |   |   |
 8 |   |   |   |   |   |   |   |   |   |   |
 9 |   |   |   |   |   |   |   |   |   |   |
     0   1   2   3   4   5   6   7   8   9

minesweeper-alpha:
```

After the player correctly entered the command `r 1 2`, the state of
the game updates (e.g., number of rounds completed, the grid, etc.), and the
next round happens. Since there was no mine in square (1,2), the player does not
lose the game. Also, since the total number of mines in the 8 cells directly
adjacent to square (1,2) is 2, the number 2 is now placed in that cell.

If the player reveals a square containing a mine, then the following message
should be displayed and the program should exit *gracefully* (as defined near
the end of this section):

```

 Oh no... You revealed a mine!
  __ _  __ _ _ __ ___   ___    _____   _____ _ __
 / _` |/ _` | '_ ` _ \ / _ \  / _ \ \ / / _ \ '__|
| (_| | (_| | | | | | |  __/ | (_) \ V /  __/ |
 \__, |\__,_|_| |_| |_|\___|  \___/ \_/ \___|_|
 |___/

```

Yeah, that's old school ASCII art. Please note that the first and last lines are
blank. Also note that the second line (containing "oh no...") begins with a single
white space. A copy of this game over text, excluding the first and last blank
lines, is contained in [`resources/gameover.txt`](resources/gameover.txt).

<a id="graceful-exit">

**Graceful Exit:** When we say that a program should exit *gracefully*, we mean that
the *exit status* code used in the call to
[`System.exit`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/System.html#exit(int))
is `0` (i.e., zero).

* If a *graceful* exit is expected and your program exits for any reason
  with an exit status other than `0` (e.g., if your game crashes), then some
  points will be deducted from your grade.

* Immediately after any program terminates and returns to the terminal shell,
  a user can inspect what exit code was used by executing the following command:
  ```
  $ echo $?
  ```
  Note that using `echo $?` a second time would show the exit status of the
  first `echo` command; you would need to rerun your program and cause it to
  exit in order to check the exit status again.

#### Mark Command

In order to mark a square as definitely containing a mine, the
`mark` or `m` command is used. The syntax format for this
command is as follows: `-["mark"/"m"]-[(int)]-[(int)]-`.
The second and third tokens indicate the row and column indices, respectively,
of the square to be revealed.

Let's go back to our `10`-by-`10` example. Suppose that the player wants to mark
square (1, 2). Here is an example of what that might look like.

```

 Rounds Completed: 0

 0 |   |   |   |   |   |   |   |   |   |   |
 1 |   |   |   |   |   |   |   |   |   |   |
 2 |   |   |   |   |   |   |   |   |   |   |
 3 |   |   |   |   |   |   |   |   |   |   |
 4 |   |   |   |   |   |   |   |   |   |   |
 5 |   |   |   |   |   |   |   |   |   |   |
 6 |   |   |   |   |   |   |   |   |   |   |
 7 |   |   |   |   |   |   |   |   |   |   |
 8 |   |   |   |   |   |   |   |   |   |   |
 9 |   |   |   |   |   |   |   |   |   |   |
     0   1   2   3   4   5   6   7   8   9

minesweeper-alpha: m 1 2

 Rounds Completed: 1

 0 |   |   |   |   |   |   |   |   |   |   |
 1 |   |   | F |   |   |   |   |   |   |   |
 2 |   |   |   |   |   |   |   |   |   |   |
 3 |   |   |   |   |   |   |   |   |   |   |
 4 |   |   |   |   |   |   |   |   |   |   |
 5 |   |   |   |   |   |   |   |   |   |   |
 6 |   |   |   |   |   |   |   |   |   |   |
 7 |   |   |   |   |   |   |   |   |   |   |
 8 |   |   |   |   |   |   |   |   |   |   |
 9 |   |   |   |   |   |   |   |   |   |   |
     0   1   2   3   4   5   6   7   8   9

minesweeper-alpha:
```

After the player correctly entered the command `m 1 2`, the state of
the game updates (e.g., number of rounds completed, the grid, etc.), and the
next round happens.

#### Guess Command

In order to mark a square as potentially containing a mine, the
`guess` or `g` command is used. The syntax format for this
command is as follows: `-["guess"/"g"]-[(int)]-[(int)]-`.
The second and third tokens indicate the row and column indices, respectively,
of the square to be revealed.

Let's go back to our `10`-by-`10` example. Suppose that the player wants to guess
square (1, 2). Here is an example of what that might look like.

```

 Rounds Completed: 0

 0 |   |   |   |   |   |   |   |   |   |   |
 1 |   |   |   |   |   |   |   |   |   |   |
 2 |   |   |   |   |   |   |   |   |   |   |
 3 |   |   |   |   |   |   |   |   |   |   |
 4 |   |   |   |   |   |   |   |   |   |   |
 5 |   |   |   |   |   |   |   |   |   |   |
 6 |   |   |   |   |   |   |   |   |   |   |
 7 |   |   |   |   |   |   |   |   |   |   |
 8 |   |   |   |   |   |   |   |   |   |   |
 9 |   |   |   |   |   |   |   |   |   |   |
     0   1   2   3   4   5   6   7   8   9

minesweeper-alpha: g 1 2

 Rounds Completed: 1

 0 |   |   |   |   |   |   |   |   |   |   |
 1 |   |   | ? |   |   |   |   |   |   |   |
 2 |   |   |   |   |   |   |   |   |   |   |
 3 |   |   |   |   |   |   |   |   |   |   |
 4 |   |   |   |   |   |   |   |   |   |   |
 5 |   |   |   |   |   |   |   |   |   |   |
 6 |   |   |   |   |   |   |   |   |   |   |
 7 |   |   |   |   |   |   |   |   |   |   |
 8 |   |   |   |   |   |   |   |   |   |   |
 9 |   |   |   |   |   |   |   |   |   |   |
     0   1   2   3   4   5   6   7   8   9

minesweeper-alpha:
```

After the player correctly entered the command `g 1 2`, the state of
the game updates (e.g., number of rounds completed, the grid, etc.), and the
next round happens.

#### No Fog Command

This command removes, for the next round only, what is often
referred to as the, "fog of war." All squares containing mines, whether unrevealed,
marked, or guessed, will be displayed with less-than and greater-than symbols on
either side of the square's center (as opposed to white space). Using the
`nofog` command **does** use up a round.
In order to issue this command, the `nofog` command is used.
The syntax format for this command is as follows: `-["nofog"]-`.

Let's go back to our `10`-by-`10` example. Suppose that in this example, there
are only two mines in the entire board which are located in squares (1,1) and (1,3).
If the player marked square (1,1) during the
first round and then used the `nofog` command during the second
round, then here is an example of what that scenario might look like:

```

 Rounds Completed: 2

 0 |   |   |   |   |   |   |   |   |   |   |
 1 |   |<F>|   |< >|   |   |   |   |   |   |
 2 |   |   |   |   |   |   |   |   |   |   |
 3 |   |   |   |   |   |   |   |   |   |   |
 4 |   |   |   |   |   |   |   |   |   |   |
 5 |   |   |   |   |   |   |   |   |   |   |
 6 |   |   |   |   |   |   |   |   |   |   |
 7 |   |   |   |   |   |   |   |   |   |   |
 8 |   |   |   |   |   |   |   |   |   |   |
 9 |   |   |   |   |   |   |   |   |   |   |
     0   1   2   3   4   5   6   7   8   9

minesweeper-alpha:
```

**NOTE:** This command should **not** be listed when the `help` command
is used. Think of it as a cheat code! It should also be useful for debugging.

#### Help Command

In order to show the help menu, the `help` or `h` command
is used. The syntax format for this command is as follows: `-["help"/"h"]-`.

Let's go back to our `10`-by-`10` example. Suppose that the player wants to display
the help menu. Here is an example of what that might look like.

```

 Rounds Completed: 0

 0 |   |   |   |   |   |   |   |   |   |   |
 1 |   |   |   |   |   |   |   |   |   |   |
 2 |   |   |   |   |   |   |   |   |   |   |
 3 |   |   |   |   |   |   |   |   |   |   |
 4 |   |   |   |   |   |   |   |   |   |   |
 5 |   |   |   |   |   |   |   |   |   |   |
 6 |   |   |   |   |   |   |   |   |   |   |
 7 |   |   |   |   |   |   |   |   |   |   |
 8 |   |   |   |   |   |   |   |   |   |   |
 9 |   |   |   |   |   |   |   |   |   |   |
     0   1   2   3   4   5   6   7   8   9

minesweeper-alpha: h

Commands Available...
 - Reveal: r/reveal row col
 -   Mark: m/mark   row col
 -  Guess: g/guess  row col
 -   Help: h/help
 -   Quit: q/quit

 Rounds Completed: 1

 0 |   |   |   |   |   |   |   |   |   |   |
 1 |   |   |   |   |   |   |   |   |   |   |
 2 |   |   |   |   |   |   |   |   |   |   |
 3 |   |   |   |   |   |   |   |   |   |   |
 4 |   |   |   |   |   |   |   |   |   |   |
 5 |   |   |   |   |   |   |   |   |   |   |
 6 |   |   |   |   |   |   |   |   |   |   |
 7 |   |   |   |   |   |   |   |   |   |   |
 8 |   |   |   |   |   |   |   |   |   |   |
 9 |   |   |   |   |   |   |   |   |   |   |
     0   1   2   3   4   5   6   7   8   9

minesweeper-alpha:
```

After the player correctly entered the command `h`, the state of
the game updates (e.g., number of rounds completed, the grid, etc.), the
help menu is displayed, and the next round happens.

**Note:** the `help` command does use up a round.

#### Quit Command

In order to quit the game, the `quit` or `q` command
is used. The syntax format for this command is as follows: `-["quit"/"q"]-`.

Let's go back to our `10`-by-`10` example. Suppose that the player wants to quit the
game. Here is an example of what that might look like.

```

 Rounds Completed: 0

 0 |   |   |   |   |   |   |   |   |   |   |
 1 |   |   |   |   |   |   |   |   |   |   |
 2 |   |   |   |   |   |   |   |   |   |   |
 3 |   |   |   |   |   |   |   |   |   |   |
 4 |   |   |   |   |   |   |   |   |   |   |
 5 |   |   |   |   |   |   |   |   |   |   |
 6 |   |   |   |   |   |   |   |   |   |   |
 7 |   |   |   |   |   |   |   |   |   |   |
 8 |   |   |   |   |   |   |   |   |   |   |
 9 |   |   |   |   |   |   |   |   |   |   |
     0   1   2   3   4   5   6   7   8   9

minesweeper-alpha: q

Quitting the game...
Bye!

```

After the player correctly entered the command `q`, the game
displayed the goodbye message and the program exited *gracefully*
(as defined elsewhere in this document).

#### Player Wins

When the player wins the game, the following message should be displayed
to the player and the game should exit *gracefully* (as defined
elsewhere in this document):

```

 ░░░░░░░░░▄░░░░░░░░░░░░░░▄░░░░ "So Doge"
 ░░░░░░░░▌▒█░░░░░░░░░░░▄▀▒▌░░░
 ░░░░░░░░▌▒▒█░░░░░░░░▄▀▒▒▒▐░░░ "Such Score"
 ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐░░░
 ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐░░░ "Much Minesweeping"
 ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌░░░
 ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒▌░░ "Wow"
 ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐░░
 ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄▌░
 ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▌░
 ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒▐░
 ▐▒▒▐▀▐▀▒░▄▄▒▄▒▒▒▒▒▒░▒░▒░▒▒▒▒▌
 ▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒▒▒░▒░▒░▒▒▐░
 ░▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒░▒░▒░▒░▒▒▒▌░
 ░▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▄▒▒▐░░
 ░░▀▄▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▄▒▒▒▒▌░░
 ░░░░▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀░░░ CONGRATULATIONS!
 ░░░░░░▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀░░░░░ YOU HAVE WON!
 ░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▀▀░░░░░░░░ SCORE: 82.30


```

Note that the first and last lines are blank and that the beginning of the
other lines contain a single white space. You should replace the score in the
output with the actual calculated score (mentioned above). A copy of this game won
text, excluding the first and last blank lines as well as the score value, is
contained in [`resources/gamewon.txt`](resources/gamewon.txt).

The conditions for winning are outlined earlier in this document,
[here](#win-conditions).

### Seed Files

Each game is setup using seed files. Seed files have the following
format:

 * The first two tokens are two integers (separated by white-space) indicating the
   number of `rows` and `cols`, respectively, for the size
   of the mine board.

 * The third token is an integer indicating `numMines`, i.e., the number of
   mines to be placed on the mine board.

 * Subsequent pairs of tokens are integers (separated by white space)
   indicating the location of each mine.

**NOTE:** Please refer to the "**Seed File Malformed Error**" description in the
["Displaying Errors" section](#displaying-errors) later in this document
for constraints related to the tokens in a seed file.

**NOTE:** In Java, the term _white-space_ refers to one or more characters in a sequence that
each satisfy the conditions outlined in [`Character.isWhitespace`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Character.html#isWhitespace(char)).
You do not need to check these conditions specifically nor use this method
if you use the built-in tokenizing provided by the `Scanner` class.

**NOTE:** It is acceptable for white-space to occur both at the beginning and
end of a seed file.

The following seed files are valid and contain the same information:

```
10 10 2 0 0 1 1
```

```
10 10
2
0 0
1 1
```

```
    10    10 2
0       0 1      1
```

An example seed file is present in the project materials. In order to run
your program with the seed file, you should be able to use the following
command (actual seed filename may differ):

```
$ java -cp bin cs1302.game.MinesweeperDriver tests/seed1.txt
```

**Note:** The command you use to run your file from your main project directory
(the directory containing `src` and `bin`) should **exactly match** the command above
if you are passing in a seed file called `seed1.txt` containined in a `tests` directory
located directly within your main project directory.

To read the file, let us assume that we have access to the seed file's path
via a `String` variable called `seedPath` and that we will use the following classes:

* [`File`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/File.html)
* [`Scanner`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html)

Most of you have used the `Scanner` class to read keyboard input from standard
input. Here, we will use it to read from a text file. This is accomplished using
something similar to the following code snippet:

```java
try {
  File configFile = new File(seedPath);
  Scanner configScanner = new Scanner(configFile);
  // use Scanner here as usual
} catch (FileNotFoundException e) {
  // handle the exception here
  // and perhaps do the following for testing/debugging only:
  // System.err.println(e);
  // e.printStackTrace();
  // and don't forget to:
  // print any error messages described earlier and exit appropriately
} // try
```

You may need to import
[`FileNotFoundException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/FileNotFoundException.html)
(or use its fully qualified name) if adapting the code snippet above.

### Displaying Errors

All error messages should be printed to standard error (`System.err`)
with one blank line preceeding the line with the error message.
In some cases, an error message should cause the program
to terminate. In such cases, an integer will be specificied that
you are required to use the specific exit status number in your call to
[`System.exit`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/System.html#exit(int)).
If you let `errorMessage` denote an error message and `exitStatus` denote
an exit status, then here is some code that exactly illustrates what needs
to happen for error messages that exit the program:

```java
System.err.println();
System.err.println(errorMessage);
System.exit(exitStatus);
```

If an error does not exit the program, then the last line in the code above
should be omitted.

Here is a list of the different errors that can occur in the program:

* **Invalid Usage Error:** If your program encounters any number of
  command-line arguments other than one (i.e., if `args.length != 1`),
  then the error message and exit status in the table near the end of
  this section should be used.

* **Seed File Not Found Error:** If your program is not able to read the seed
  file that is specified by the user via a command-line argument due to a
  `FileNotFoundException`, then the error message and exit status
  in the table near the end of this section should be used.

  Note that `<message>` (including the angle brackets) in the error message
  text should be replaced with the `String` returned by the exception
  object's `getMessage()` method.

* **Seed File Malformed Error:** <a id="badseed"/> If your program encounters a problem in the
  format of a seed file, then the error message and exit status in the table
  near the end of this section should be used. Note that a seed file is
  considered *malformed* or not formatted correctly if any of the following
  conditions are met:

  * a token is expected but is not found;
  * a token is not of the expected type (e.g., it's expected to be an `int` but it's not);
  * the token for `rows` is less than `5` or greater than `10`;
  * the token for `cols` is less than `5` or greater than `10`;
  * the token for `numMines` is less than `1` or greater than `(rows * cols) - 1`; or
  * the location of a mine is not in bounds.


  Note that `<message>` (including the angle brackets) in the error message text
  should be replaced with some descriptive `String`. If the error arises due
  to some exception, then you may use the `String` returned by the exception
  object's `getMessage()` method; otherwise, you may use some short, single line
  `String` of your choosing that describes the problem.

* **Invalid Command Error:** While the game is running, if a command entered by
  the player is invalid or not recognized, then the error message in the table
  near the end of this section should be used. For this kind of error, the
  program **should NOT exit**, and **a round should NOT be consumed** (i.e.,
  your counter for the number of rounds should not increase). After the error
  message is displayed, the round is essentially restarted and the number of rounds,
  the grid, and the prompt should be displayed again.

  Note that `<message>` (including the angle brackets) in the error message text
  should be replaced with some descriptive `String`. If the error arrises due
  to some exception, then you may use the `String` returned by the exception
  object's `getMessage()` method; otherwise, you may use some short, single line
  `String` of your choosing that describes the problem.

Here is the table that summarizes the different error messages and exit status codes:

| Error                         | Error Message                             | Exit Status |
|-------------------------------|-------------------------------------------|-------------|
| **Invalid Usage Error**       | `Usage: MinesweeperDriver SEED_FILE_PATH` | `1`         |
| **Seed File Not Found Error** | `Seed File Not Found Error: <message>`    | `2`         |
| **Seed File Malformed Error** | `Seed File Malformed Error: <message>`    | `3`         |
| **Invalid Command Error:**    | `Invalid Command: <message>`              | NA          |

## Minesweeper Oracle

If at any time while you are writing your Minesweeper program you find yourself wondering
"How should my program respond if \_\_\_\_\_ happens", you can consult the provided
Minesweeper oracle. The oracle is an executable-only (no code provided) version of the
instructors' solution to the Minesweeper project and is available on Odin. To run the oracle, you
need to provide the same command-line arguments that you would to your version of Minesweeper.
You only need to change the start of the command. Here is the exact syntax:

```
$ minesweeper-oracle cs1302.game.MinesweeperDriver some/path/to/seed.txt < optional/path/to/input.txt
```

where `some/path/to/seed.txt` and `optional/path/to/input.txt` are replaced with paths to real seed
and input files, respectively.

<!--The Minesweeper oracle also includes the `--gen` option to
generate seed files. Feel free to use this to generate seed files for testing.-->

If you find a bug in the oracle or believe there to be an inconsistency between what the oracle
says and what this document says, please let your instructors know in a Piazza post. We have only
given students access to the oracle once before so we may still have to iron out a few kinks.

## Project Requirements & Grading

This assignment is worth 100 points. The lowest possible grade is 0, and the
highest possible grade without extra credit is 100.

### Functional Requirements

A functional requirement is *added* to your point total if satisfied.
There will be no partial credit for any of the requirements that simply
require the presence of a method related a particular functionality.
The actual functionality is tested using test cases.

#### (40 points) Required Classes

<!-- ![UML Class Diagram](uml_diagram.png) //-->

* **`cs1302.game.MinesweeperGame` Class**: <a id="constructor"/> Instances of
  this class represent a game of Minesweeper Alpha. You need to implement
  all of the methods listed below. Unless stated otherwise, each
  method is assumed to have public visibility. Instance variables
  may be added, as needed, to keep track of object state.

  * **`MinesweeperGame(Scanner stdIn, String seedPath)`**: In this constructor,
    you should intialize some of your instance variables and setup the game
    using the information in the seed file referred to by `seedPath`.

    * Keep in mind that this constructor will be called from your `Driver`
      class, so that's where the iniitial values of `stdIn` and `seedPath`
      come from. You should refer to the instructions for `Driver` for
      more information before you make any assumptions.
    * You will probably want to create a separate method for reading the seed file.
    * Take care that you **assign `stdIn` to an instance variable** and that
      you use that instance variable whenever you need to read from standard input.

      **WARNING:** Other than the initial assignment to your `stdIn`-related
      instance variable in the constructor, you should not reassign that instance
      variable elsewhere in your `MinesweeperGame` class; if you do, then you risk
      getting a zero on your project per [one of the requirements](#stdin).

      **SUGGESTION:** We said that you should assign `stdIn` to an instance
      variable, but **it's also perfectly okay for you to assign it to an instance
      constant**. This will prevent you from reassigning it on accident. In Java,
      you can leave an instance constant uninitialized on the line where it's
      declared so long as you initialize it exactly once inside your constructor.
      Here is the declaration (notice we didn't initialize it here):

      ```java
      private final Scanner stdIn; // declare instance constant
      ```

      Here is what a line in the constructor might look like:

      ```java
      this.stdIn = stdIn; // initialize instance constant
      ```

      If you follow this suggestion, then you can call `stdIn.nextLine()` or
      `this.stdIn.nextLine()` in your class's instance methods whenever
      you want to get a line of user input (e.g., for a command).

  * **`void printWelcome()`:** This method should print
    the welcome banner to standard output, as described earlier in
    this document.

  * **`void printMineField()`:** This method should print
    the current contents of the mine field to standard output, as
    described earlier in this document.

  * **`void promptUser()`:** This method should print
    the game prompt to standard output and interpret user input
    from standard input, as described earlier in this document.
    Based on the command received, this method should delegate (i.e., call other
    methods) to handle the work.

  * **`boolean isWon()`:** This method should return `true` if, and only if,
    all the conditions are met to win the game as defined earlier in
    this document.

  * **`void printWin()` and ``void printLoss()``:** These
    methods should print the win and game over emssages to standard
    output, respectively, as described earler in this document.

  * **`void play()`:** This method should provide the
    main game loop by invoking other instance methods, as needed.

  **NOTE:** Please see the [Suggestions](#suggestions) section of this
  document before writing the code to implement these methods.

  **NOTE:** You are encouraged to implement other methods, as needed,
  to help with readability, code reuse, etc. In some cases, you may
  need to add other methods to meet the style requirement for method length.

* **`cs1302.game.MinesweeperDriver` Class**: This class
  should only contain the `main` method:

  * `void main(String[] args)`: This public, static method should
    do the following :

    1. **Create your one and only standard input `Scanner` object.** While your
       program may have as many `Scanner` objects as is needed, it may only have
       exactly one `Scanner` object for standard input per program execution.
       Your `main` method should include the following code:
       ```java
       Scanner stdIn = new Scanner(System.in);
       ```
       You must not include `new Scanner(System.in)` anywhere else in your project
       or you risk getting a grade of zero for the project.

    1. **Handle command-line arguments.** Exactly one command-line argument is
       expected, and it represents the path to a seed file. You should assign
       that argument to a local `String` variable called `seedPath` so that it
       can be used in the call to your `MinesweeperGame` constructor in step 3.

    1. **Instatiate the `MinesweeperGame` object call its `play()` method.**
       Use the `stdIn` and `seedPath` variables from the first two steps when
       you create the object. Once the object is created, call its `play()`
       method.

* **(60 points) Test Cases**: <a id="test-cases"/> The bulk of this project will be graded
  based on multiple test cases. A single test case can be described by three things:

  Some example test cases are provided with the project description; they
  are described in the [Test Case Examples](#test-case-examples) section of the
  Appendix. Since the actual test cases that will be used to grade your project
  may be a superset of the ones provided, you should take care to read and
  understand how to test your program using the example test cases. If your
  program does not execute with a command-line argument and input/output
  redirection as described in the appendix (and perhaps elsewhere), then it will
  automatically be assigned a grade of zero, regardless of any code
  contained in the submission. No exceptions will be made for this.

  To be absolutely clear, you must make sure your program runs as described
  in the [Test Case Examples](#test-case-examples) section of the Appendix;
  **the graders will not adjust the commands when running your program to accomodate
  a different set of command-line arguments.** If there is a package-related issue,
  however, then the graders may make some minor adjustments for a relatively small
  grade penalty.

### Non-Functional Requirements

A non-functional requirement is *subtracted* from your point total if
not satisfied. In order to emphasize the importance of these requirements,
non-compliance results in the full point amount being subtracted from your
point total. That is, they are all or nothing (no partial credit).

* **(10 or 100 points) Project Directory Structure:** The location of the default
  package for the source code should be a direct subdirectory of
  `cs1302-minesweeper-alpha` called `src`. When the project is compiled,
  the `-d` option should be used with `javac` to make the default package
  for compiled code a direct subdirectory of `cs1302-minesweeper-alpha`
  called `bin`.

  If you follow this structure, then you would type the following to compile
  your code, assuming you are in the top-level project
  directory `cs1302-minesweeper-alpha`:

  ```
  $ javac -cp bin -d bin src/cs1302/game/MinesweeperGame.java
  $ javac -cp bin -d bin src/cs1302/game/MinesweeperDriver.java
  ```

  The class path may be omitted in the first command if there are
  no other dependencies. Remember, when you compile `.java` files individually,
  there might be dependencies between the files. In such cases, the order in which
  you compile the code and whether or not you specify the class path matters.

  **NOTE:** If your grader needs to modify your directory structure or
  any of your filenames to compile your code, then the 10 point version
  of this penalty will apply. If, however, your grader is unable to compile
  your code, then the 100 point version of this penalty applies.
  Graders are instructed not to modify source code in an attempt to to make
  a submission compile.

  **Any additional classes that you create should be located in or under the `cs1302.game` package.**
  If other `.java` files are present, then they will be compiled _individually_
  by the graders and added to the class path, as needed, when compiling other files.

* **(100 points) Development Environment:** This project must be implemented
  in Java 17, and it *must compile and run* correctly on Odin using the specific
  version of Java 17 that is setup according to the instructions provided
  by your instructor. Graders are instructed not to modify source code in
  an attempt to to make a submission compile.

* **(100 points) One Scanner for Standard Input:** <a id="stdin"/>Only one `Scanner`
  object for `System.in` (i.e., for standard input) should be created.
  **You may create `Scanner` objects for other input sources as needed.**
  Please note that if you create a new  `Scanner` object at
  the beginning of a method or loop, then more than one object will
  be created if the method is called more than once or if the loop
  iterates more than once.

* **(0 points) [RECOMMENDED] No Static Variables:** Use of static variables
  is not appropriate for this assignment. However, static constants are
  perfectly fine.

* **(20 points) Code Style Guidelines:** You should be consistent with the style
  aspect of your code in order to promote readability. Every `.java` file that
  you include as part of your submission for this project must be in valid style
  as defined in the [CS1302 Code Style Guide](https://github.com/cs1302uga/cs1302-styleguide).
  All of the individual code style guidelines listed in that document are part
  of this single non-functional requirement. Like the other non-functional
  requirements, **this requirement is all or nothing**.

  **NOTE:** The [CS1302 Code Style Guide](https://github.com/cs1302uga/cs1302-styleguide)
  includes instructions on how to use the `check1302` program to check
  your code for compliance on Odin.

* **In-line Documentation (10 points):** Code blocks should be adequately documented
  using in-line comments. With in-line comments, you should explain tricky, large, complicated,
  or confusing blocks of code. This is especially necessary whenever a block of code
  is not immediately understood by a reader (e.g., yourself or the grader). You might also
  include information that someone reading your code would need to know but not someone using it
  (that is more appropriate for a Javadoc comment). A good heuristic for this: if you can imagine that,
  after six months, you might not be able to tell in under a few seconds what a code block is doing,
  then then you probably need to write some in-line comments.

## Suggestions

This project will be a lot easier if you structure your code properly. There is
not a single correct way to do this, but here are some ideas for support methods
that I think will make things easier. These are just suggestions. If you choose
to use these, then you will need to implement them yourself.

```java
/**
 * Returns the number of mines adjacent to the specified
 * square in the grid.
 *
 * @param row the row index of the square
 * @param col the column index of the square
 * @return the number of adjacent mines
 */
private int getNumAdjMines(int row, int col) { }
```

The method above (as well as some other methods) can be implemented a lot more
easily if you have an easy way to determine if a square is in bounds. Here is
a suggestion for a method that does just that.

```java
/**
 * Indicates whether or not the square is in the game grid.
 *
 * @param row the row index of the square
 * @param col the column index of the square
 * @return true if the square is in the game grid; false otherwise
 */
private boolean isInBounds(int row, int col) { }
```

Also, it might be easier to use two different arrays (of the same size) in order
to keep track of the game grid. One of the arrays could be a two-dimensional
boolean array that indicates mine locations. The other array could be a
two-dimensional char or String array that holds the blanks, numbers, and other
characters for each square.

## How to Download the Project

On Odin, execute the following terminal command in order to download the project
files into sub-directory within your present working directory:

```
$ git clone --depth 1 https://github.com/cs1302uga/cs1302-minesweeper-alpha.git
```

This should create a directory called `cs1302-minesweeper-alpha` in
your present working directory that contains the project files.

If any updates to the project files are announced by your instructor, you can
merge those changes into your copy by changing into your project's directory
on Odin and issuing the following terminal command:

```
$ git pull
```

If you have any problems with any of these procedures, then please contact
your instructor.

## Submission Instructions

You will submit your project via Odin. Before you submit, make sure that your
project files are located in a directory called `cs1302-minesweeper-alpha` -- if
you followed the instructions provided earlier in this document to download
the project, then your directory name should good. To submit, change into the
parent of your project directory and execute the following command:

```
$ check1302 cs1302-minesweeper-alpha
```

If there are style guide violations, then fix them and retest your code! Once
you have no style guide violations and your code compiles, you can submit
using the following command:

```
$ submit cs1302-minesweeper-alpha csci-1302
```

<!--

It is also a good idea to email a copy to yourself. To do this, simply execute
the following command, replacing the email address with your email address:

```
$ tar zcvf cs1302-minesweeper-alpha.tar.gz cs1302-minesweeper-alpha
$ mutt -s "[cs1302] cs1302-minesweeper-alpha" -a cs1302-minesweeper-alpha.tar.gz -- your@email.com < /dev/null
```
-->

If you have any problems submitting your project then please email your
instructor as soon as possible. However, emailing him about something like this
the day or night the project is due is probably not the best idea.

## Appendix

### Minefield Output Examples

The [`examples`](examples/) directory contains an example of
a blank grid for every possible combination of `rows` and `cols`
supported by this game.

### Test Case Examples

**What is described in this section is exactly the method that the graders
will use to test your program**, with the exception that the graders have
access to more test cases. Therefore, **you should make every effor to test
your program following these instruction.**

The [`tests`](tests/) directory contains some example test cases.
Each test case has a number (e.g., `01`) and can be described by five things:

  1. a path to a **seed file**;
  2. a path to a file with **user input** (StdIn);
  3. a path to a file with expected **standard output** (StdOut) given 1 and 2;
  4. a path to a file with expected **standard error** (StdErr) given 1 and 2; and
  5. a path to a file with expected **combined output** (Combined, i.e., standard
     output and standard error) given 1 and 2.

| # | Seed File | StdIn | StdOut | StdErr | Combined |
|---|-----------|-------|--------|--------|----------|
| `tc01` | [`.seed.txt`](tests/tc01.seed.txt) | [`.in.txt`](tests/tc01.in.txt) | [`.out.txt`](tests/tc01.out.txt) | [`.err.txt`](tests/tc01.err.txt) | [`.combined.txt`](tests/tc01.combined.txt) |
| `tc02` | [`.seed.txt`](tests/tc02.seed.txt) | [`.in.txt`](tests/tc02.in.txt) | [`.out.txt`](tests/tc02.out.txt) | [`.err.txt`](tests/tc02.err.txt) | [`.combined.txt`](tests/tc02.combined.txt) |
| `tc03` | [`.seed.txt`](tests/tc03.seed.txt) | [`.in.txt`](tests/tc03.in.txt) | [`.out.txt`](tests/tc03.out.txt) | [`.err.txt`](tests/tc03.err.txt) | [`.combined.txt`](tests/tc03.combined.txt) |
| `tc04` | [`.seed.txt`](tests/tc04.seed.txt) | [`.in.txt`](tests/tc04.in.txt) | [`.out.txt`](tests/tc04.out.txt) | [`.err.txt`](tests/tc04.err.txt) | [`.combined.txt`](tests/tc04.combined.txt) |
| `tc05` | [`.seed.txt`](tests/tc05.seed.txt) | [`.in.txt`](tests/tc05.in.txt) | [`.out.txt`](tests/tc05.out.txt) | [`.err.txt`](tests/tc05.err.txt) | [`.combined.txt`](tests/tc05.combined.txt) |
| `tc06` | [`.seed.txt`](tests/tc06.seed.txt) | [`.in.txt`](tests/tc06.in.txt) | [`.out.txt`](tests/tc06.out.txt) | [`.err.txt`](tests/tc06.err.txt) | [`.combined.txt`](tests/tc06.combined.txt) |
| `tc07` | [`.seed.txt`](tests/tc07.seed.txt) | [`.in.txt`](tests/tc07.in.txt) | [`.out.txt`](tests/tc07.out.txt) | [`.err.txt`](tests/tc07.err.txt) | [`.combined.txt`](tests/tc07.combined.txt) |

**You are encouraged to add your own test cases;** however, we will not use
your test cases when grading your project.

#### Running the Test Cases

When a regular user plays the game, they specify the seed file as a command-line
argument, e.g.,

```
$ java -cp bin cs1302.game.MinesweeperDriver some/path/to/seed.txt
```

In this scenario, the user enters their commands into standard input
and the game prints its output to standard output.

When the grader wants to check your game, **they will not manually
type in commands into standard input.** Instead, they will use the shell
to redirect standard input to a file that contains user input. From the
program's perspective, it stil thinks it's reading from standard input.
It's just that standard input now refers to an actual file on disk
instead of keyboard input. This is accomplished using the shell
input redirection operator `<` or pipe `|`. For example, the grader
might type the following to run the first test case provided
in the [`tests`](tests/):

```
$ java -cp bin cs1302.game.MinesweeperDriver tests/tc01.seed.txt < tests/tc01.in.txt
```

Here is what each part of that command means:

```
  run with test/tc01.seed.txt
 +--------------------------------------------------------------+
$ java -cp bin cs1302.game.MinesweeperDriver tests/tc01.seed.txt < tests/tc01.in.txt
                                                                +-------------------+
                                        redirect standard input to tests/tc01.in.txt
```

In this example, the shell forces the program to interpret standard input
as the file `tests/tc01.in.txt`. Instead of halting for user input, any method
calls to your program's `Scanner` object for `System.in` return immediately
with a token from the file. Once the program has stopped producing output,
the grader then compares that output to `tests/tc01.out.txt`, `tests/tc01.err.txt`, and
`tests/tc01.combined.txt` to see ensure that everything appears as it should for
that test case.

You can run the same tests against [the oracle implementation](#minesweeper-oracle).
Here is an example:

```
$ minesweeper-oracle cs1302.game.MinesweeperDriver tests/tc01.seed.txt < tests/tc01.in.txt
```

#### Saving Output

If you want to save the standard output, standard error, and combined output of your
program, then you can utilize output redirection as follows (replace `filename` with
some appropriate name, as needed):

| Type            | Example                    |
|-----------------|----------------------------|
| Standard Output | `> filename.out.txt`       |
| Standard Error  | `2> filename.err.txt`      |
| Combined Output | `&> filename.combined.txt` |

You can combine input and output redirection, but **take care not to overwrite the
output files in the `test` directory.**

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
