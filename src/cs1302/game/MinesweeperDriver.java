package cs1302.game;

import cs1302.game.MinesweeperGame;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * The driver class that allows a user to run the {@link cs1302.game.MinesweeperGame} class.
 */
public class MinesweeperDriver {
    /**
     * This is the main method that starts the program.
     *
     * @param args where the user should supply the path to the seed file
     */
    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        try {
            String seedPath = args[0];
            MinesweeperGame game = new MinesweeperGame(user, seedPath);
            game.play();
        } catch (FileNotFoundException fnf) {
            System.err.println();
            System.err.println("Seed File Not Found Error: " + fnf.getMessage());
            System.exit(2);
        } catch (NullPointerException np) {
            System.err.println();
            System.err.println("Usage: MinesweeperDriver SEED_FILE_PATH");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException arrayIndex) {
            System.err.println();
            System.err.println("Usage: MinesweeperDriver SEED_FILE_PATH");
            System.exit(1);
        } catch (IllegalArgumentException illegalArgument) {
            System.err.println();
            System.err.println("Seed File Malformed Error: " + illegalArgument.getMessage());
            System.exit(3);
        } //try
    } //main
}
