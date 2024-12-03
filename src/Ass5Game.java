import Game.Game;
/**
 * The Ass5Game class is the entry point for running the Arkanoid game.
 * It initializes the game and starts the game loop.
 * The main method creates a Game object, initializes it, and runs the game.
 * This class serves as the starting point for launching the Arkanoid game.
 *
 * @author dabbahk1
 * Id : 214075343
 */
public class Ass5Game {

    /**
     * The main method creates a Game object, initializes it, and runs the game.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a new instance of the Arkanoid Game
        Game game = new Game();

        // Initialize the game components such as sprites, environment, and GUI
        game.initialize();

        // Run the game loop
        game.run();
    }
}
