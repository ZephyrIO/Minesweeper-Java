package cs1302.game;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/**
 * The class that contains all of the code for my implementation of Minesweeper.
 */
public class MinesweeperGame {
    private int rows; //the number of rows on the board
    private int cols; //the number of columns on the board
    private int rounds; //the number of rounds that have passed during the game
    private int numMines; //the number of mines that exist on the map
    private boolean noFog; //controls whether the player can see where the mines are
    private String[][] board; //the board on which the game is played
    private boolean[][] mineBoard; //the board that holds whether there is a mine on a certain tile
    private final Scanner stdIn; //the Scanner that is used to get input from the player

    /**
     * The constructor for the {@code MinesweeperGame} class.
     *
     * @param stdIn a {@code Scanner} containing the standard input for the program.
     * @param seedPath a {@code String} that represent the path to a file containing the
     * seed that generates the map.
     *
     * @throws NullPointerException If the given {@code seedPath} is {@code null}.
     * @throws FileNotFoundException If the given file at {@code seedPath} cannot be found.
     * @throws IllegalArgumentException If the given seed file is malformed in some way.
     */
    public MinesweeperGame(Scanner stdIn, String seedPath) throws FileNotFoundException {
        this.stdIn = stdIn;
        File seedFile = new File(seedPath);
        Scanner seedReader = new Scanner(seedFile);
        try {
            rows = seedReader.nextInt();
            cols = seedReader.nextInt();

            if (rows < 5 || rows > 10) {
                throw new IllegalArgumentException("rows must be between 5 and 10");
            } //if

            if (cols < 5 || cols > 10) {
                throw new IllegalArgumentException("cols must be between 5 and 10");
            } //if

            rounds = 0;
            numMines = seedReader.nextInt();

            if (numMines < 1 || numMines > ((rows * cols) - 1)) {
                throw new IllegalArgumentException("numMines must be between 1 and "
                    + "(rows * cols) - 1");
            } //if

            noFog = false;

            board = new String[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    board[i][j] = "   ";
                } //for
            } //for

            mineBoard = new boolean[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    mineBoard[i][j] = false;
                } //for
            } //for

            while (seedReader.hasNextInt()) {
                try {
                    int row = seedReader.nextInt();
                    int col = seedReader.nextInt();
                    mineBoard[row][col] = true;
                } catch (ArrayIndexOutOfBoundsException outofBounds) {
                    throw new IllegalArgumentException("The mine must be within the bounds of the "
                        + "board");
                } //try
            } //while
        } catch (InputMismatchException inputMismatch) {
            throw new IllegalArgumentException("A token not of the expected type was provided");
        } catch (NoSuchElementException noElement) {
            throw new IllegalArgumentException("A token was expected, but not found");
        } //try
        seedReader.close();
    } //constructor

    /**
     * This method prints a welcome message to standard output when called.
     */
    public void printWelcome() {
        System.out.println("        _\n"
            + "  /\\/\\ (F)_ __   ___  _____      _____  ___ _ __   ___ _ __\n"
            + " /    \\| | '_ \\ / _ \\/ __\\ \\ /\\ / / _ \\/ _ \\ '_ \\ / _ \\ '__|\n"
            + "/ /\\/\\ \\ | | | |  __/\\__ \\\\ V  V /  __/  __/ |_) |  __/ |\n"
            + "\\/    \\/_|_| |_|\\___||___/ \\_/\\_/ \\___|\\___| .__/ \\___|_|\n"
            + "                             ALPHA EDITION |_| v2022.sp");
    } //printWelcome

    /**
     * This method prints out a visual interpretation of the minefield to standard output
     * when called.
     */
    public void printMineField() {
        for (int i = 0; i < rows; i++) {
            System.out.print(" " + i + " |");

            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + "|");
            } //for

            System.out.println();
        } //for
        System.out.print("    ");
        for (int i = 0; i < cols; i++) {
            System.out.print( " " + i + "  ");
        } //for
        System.out.println();

        if (noFog) {
            reFog();
        } //if
    } //printMineField

    /**
     * This method both prints out the game prompt to standard output and
     * also interprets the user input from standard input.
     *
     * @throws InputMismatchException if the input provided is not the same data
     * type that the method expects
     * @throws NoSuchElementException if the user does not provide enough
     * information as part of thier command
     */
    public void promptUser() {
        int row;
        int col;
        Scanner commandParser = this.promptHelper();
        String command = commandParser.next();

        switch (command) {
        case "reveal", "r" :
        case "mark", "m" :
        case "guess", "g" :
            row = commandParser.nextInt();
            col = commandParser.nextInt();
            if (commandParser.hasNextInt()) {
                System.err.println("Invalid Command: Too many parameters given");
            } else {
                try {
                    switch (command) {
                    case "reveal", "r" :
                        reveal(row, col);
                        break;
                    case "mark", "m" :
                        mark(row, col);
                        break;
                    case "guess", "g" :
                        guess(row, col);
                        break;
                    } //switch
                } catch (ArrayIndexOutOfBoundsException outOfBounds) {
                    System.err.println("Invalid Command: " + outOfBounds.getMessage());
                } //try
            } //if
            break;
        case "help", "h" :
            help();
            break;
        case "nofog" :
            noFog();
            break;
        case "quit", "q" :
            quit();
            break;
        default :
            System.err.println("\nInvalid Command: Command not recognized!");
            break;
        } //switch

        commandParser.close();
    } //promptUser

    /**
     * A helper method that is used to reduce the length of the {@code promptUser()} method.
     *
     * @return Scanner the commandParser that can read through the commands that the user provides.
     */
    private Scanner promptHelper() {
        System.out.println("\n Rounds Completed: " + rounds + "\n");
        this.printMineField();
        System.out.println();
        System.out.print("minesweeper-alpha: ");
        String input = stdIn.nextLine();
        return new Scanner(input);
    } //promptHelper

    /**
     * Reveals a square and either ends the game if the user "steps" on a mine or replaces the
     * square with the number of mines that are on adjacent tiles. Checks if the user has
     * won after revealing a tile and ends the game if they do. Adds one to the round
     * counter if they have not.
     *
     * @param row the {@code row} of the tile that the user wants to {@code reveal}
     * @param col the {@code column} of the tile that the user wants to {@code reveal}
     */
    private void reveal(int row, int col) {
        if (mineBoard[row][col]) {
            printLoss();
            System.exit(0);
        } else {
            int mines = 0;
            if (row < rows - 1 && mineBoard[row + 1][col]) {
                mines++;
            } //if

            if (row > 0 && mineBoard[row - 1][col]) {
                mines++;
            } //if

            if (col < cols - 1 && mineBoard[row][col + 1]) {
                mines++;
            } //if

            if (col > 0 && mineBoard[row][col - 1]) {
                mines++;
            } //if

            if (row < rows - 1 && col < cols - 1 && mineBoard[row + 1][col + 1]) {
                mines++;
            } //if

            if (row < rows - 1 && col > 0 && mineBoard[row + 1][col - 1]) {
                mines++;
            } //if

            if (row > 0 && col > 0 && mineBoard[row - 1][col - 1]) {
                mines++;
            } //if

            if (row > 0 && col < cols - 1 && mineBoard[row - 1][col + 1]) {
                mines++;
            } //if

            board[row][col] = " " + mines + " ";
        } //if
        rounds++;

        if (this.isWon()) {
            this.printWin();
            System.exit(0);
        } //if
    } //reveal

    /**
     * Marks a square with a {@code F} to flag that the user believes that a
     * mine is here. Adds one to the round counter.
     *
     * @param row the {@code row} of the tile that the user wants to {@code mark}
     * @param col the {@code column} of the tile that the user wants to {@code mark}
     */
    private void mark(int row, int col) {
        if (noFog && mineBoard[row][col]) {
            board[row][col] = "<F>";
        } else {
            board[row][col] = " F ";
        } //if
        rounds++;

        if (this.isWon()) {
            this.printWin();
            System.exit(0);
        } //if
    } //mark

    /**
     * Marks a square with a {@code ?} to flag that the user thinks that a
     * mine is here. Adds one to the round counter.
     *
     * @param row the {@code row} of the tile that the user wants to {@code guess}
     * @param col the {@code column} of the tile that the user wants to {@code guess}
     */
    private void guess(int row, int col) {
        if (noFog && mineBoard[row][col]) {
            board[row][col] = "<?>";
        } else {
            board[row][col] = " ? ";
        } //if
        rounds++;
    } //guess

    /**
     * Prints out the list of commands that the user can use in the game of minesweeper.
     */
    private void help() {
        System.out.println("\nCommands Available...\n - Reveal: r/reveal row col\n"
            + " -   Mark: m/mark   row col\n -  Guess: g/guess  row col\n"
            + " -   Help: h/help\n -   Quit: q/quit");
        rounds++;
    } //help

    /**
     * A cheat that the user can use in order to know exactly where all of the mines on the field
     * are.
     */
    private void noFog() {
        noFog = true;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mineBoard[i][j]) {
                    if (board[i][j] == "   ") {
                        board[i][j] = "< >";
                    } else if (board[i][j] == " F ") {
                        board[i][j] = "<F>";
                    } else if (board[i][j] == " ? ") {
                        board[i][j] = "<?>";
                    } //if
                } //if
            } //for
        } //for
        rounds++;
    } //noFog

    /**
     * Resets the fog over the map.
     */
    private void reFog() {
        noFog = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mineBoard[i][j]) {
                    if (board[i][j] == "< >") {
                        board[i][j] = "   ";
                    } else if (board[i][j] == "<F>") {
                        board[i][j] = " F ";
                    } else if (board[i][j] == "<?>") {
                        board[i][j] = " ? ";
                    } //if
                } //if
            } //for
        } //for
    } //noFog

    /**
     * Allows the user to quit the game gracefully.
     */
    private void quit() {
        System.out.println("\nQuitting the game...\nBye!");
        System.exit(0);
        rounds++;
    } //quit

    /**
     * Determines if the player has won the game based on the following conditions:
     * <br>
     * All squares that contain a mine are flagged as definitely containing a mine.
     * <br>
     * All the squares that do not contain a mine have been revealed.
     *
     * @return true if the player has won the game as described above.
     */
    public boolean isWon() {
        boolean isWon = false;
        int minesFound = 0;
        int squaresRevealed = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (board[i][j] == " F " || board[i][j] == "<F>") {
                    if (mineBoard[i][j]) {
                        minesFound++;
                    } //if
                } //if

                String temp = board[i][j].trim();
                if (temp.equals("0") || temp.equals("1") || temp.equals("2") ||
                    temp.equals("3") || temp.equals("4") || temp.equals("5") ||
                    temp.equals("6") || temp.equals("7") || temp.equals("8")) {
                    squaresRevealed++;
                } //if
            } //for
        } //for

        if (minesFound == numMines && squaresRevealed == ((rows * cols) - numMines)) {
            isWon = true;
        } //if

        return isWon;
    } //isWon

    /**
     * Prints the victory message to standard output when called.
     */
    public void printWin() {
        double score = 100.0 * rows * cols / rounds;

        System.out.println("\n ░░░░░░░░░▄░░░░░░░░░░░░░░▄░░░░ \"So Doge\"\n"
            + " ░░░░░░░░▌▒█░░░░░░░░░░░▄▀▒▌░░░\n"
            + " ░░░░░░░░▌▒▒█░░░░░░░░▄▀▒▒▒▐░░░ \"Such Score\"\n"
            + " ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐░░░\n"
            + " ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐░░░ \"Much Minesweeping\"\n"
            + " ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌░░░\n"
            + " ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒▌░░ \"Wow\"\n"
            + " ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐░░\n"
            + " ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄▌░\n"
            + " ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▌░\n"
            + " ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒▐░\n"
            + "▐▒▒▐▀▐▀▒░▄▄▒▄▒▒▒▒▒▒░▒░▒░▒▒▒▒▌\n"
            + "▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒▒▒░▒░▒░▒▒▐░\n"
            + "░▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒░▒░▒░▒░▒▒▒▌░\n"
            + "░▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▄▒▒▐░░\n"
            + "░░▀▄▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▄▒▒▒▒▌░░\n"
            + "░░░░▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀░░░ CONGRATULATIONS!\n"
            + "░░░░░░▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀░░░░░ YOU HAVE WON!");
        System.out.printf(" ░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▀▀░░░░░░░░ SCORE: %1.2f%n", score);
    } //printWin

    /**
     * Prints the game over message to standard output when called.
     */
    public void printLoss() {
        System.out.println("\n Oh no... You revealed a mine!\n"
            + "  __ _  __ _ _ __ ___   ___    _____   _____ _ __\n"
            + " / _` |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '__|\n"
            + "| (_| | (_| | | | | | |  __/ | (_) \\ V /  __/ |\n"
            + " \\__, |\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|\n"
            + " |___/\n");
    } //printLoss

    /**
     * This method controls the gameplay loop by calling the rest of the methods in this
     * class.
     */
    public void play() {
        boolean hasWon = false;

        this.printWelcome();

        while (!hasWon) {
            try {
                this.promptUser();
            } catch (NoSuchElementException noElement) {
                System.err.println("\nInvalid Command: " + noElement.getMessage());
            } //try
        } //while
    } //play
}
