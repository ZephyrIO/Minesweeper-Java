
# Minesweeper Alpha v2022

![Unrelated image of mine."](mine.jpg)

## How to Play

First run
```
./compile-script.sh
```
or
```
javac -d bin src/cs1302/game/MinesweeperGame.java
javac -d bin -cp bin src/cs1302/game/MinesweeperDriver.java
```
to compile the program, then run
```
java -cp bin cs1302.game.MinesweeperDriver [seed.txt]
```
to play the game.

## Project Description

This is a **non-recursive**, **non-GUI** (GUI = Graphical User
Interface) version of the game called **Minesweeper**. The code for this game is
organized in such a way that the recursive elements of Minesweeper can
be added at a later point in time, if desired. It is also organized so that
a GUI can be added to it later as well.

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
**This game does not support this behavior**. If the user reveals
one square, then, at most, one square is revealed in the minefield.

### Minesweeper Overview

In Minesweeper, the player is initially presented with a grid of
undifferentiated squares. Some of those squares contain hidden mines. The
size of the grid, the number of mines, and the individual mine locations
are set in advance by a seed file (more on that later) that the user
specifies as a command-line argument to your program. The ratio of the
number of mines to the grid size is often used as a
measure of an individual game's difficulty. The grid size can also be
represented in terms of the number of rows and columns in the grid.
In this description, we may refer to the _grid_ or to the
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

#### Running the Test Cases

When a regular user plays the game, they specify the seed file as a command-line
argument, e.g.,

```
$ java -cp bin cs1302.game.MinesweeperDriver some/path/to/seed.txt
```

In this scenario, the user enters their commands into standard input
and the game prints its output to standard output.

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

Note: This README was modified from the original project specifications, copyright information below.

<small>
Finished Project Copyright &copy; Aditya Mukila.
<br>
Original Project Starter Copyright &copy; Michael E. Cotterell and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>