package Game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import biuoop.DrawSurface;


import Sprite.Sprite;

import Basics.Rectangle;
import Collidable.Paddle;
import Sprite.SpriteCollection;
import Basics.Point;

import java.awt.Color;

import Collidable.Block;
import Sprite.Ball;
import Collidable.Collidable;

/**
 * The Game class represents the main game flow, including initialization, running the game loop,
 * and managing game objects such as sprites and collidables.
 * @author :dabbahk1
 * Id : 214075343
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter ballsCounter;
    private Counter score;
    private ScoreTrackingListener scoreListener;


    private GUI gui;
    private Sleeper sleeper;
    private BlockRemover blockRemover;

    /**
     * Constructor for creating a Game object.
     * Initializes the game components such as sprites, the game environment, GUI, and sleeper.
     */
    public Game(){
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.ballsCounter = new Counter();
        this.gui = new GUI("Game",800,600);
        sleeper = new Sleeper();
        this.score = new Counter(0);
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to be added.
     */
    public void addCollidable(Collidable c){
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game's sprite collection.
     *
     * @param s The sprite object to be added.
     */
    public void addSprite(Sprite s){
        sprites.addSprite(s);
    }

    /**
     * Initializes the game by setting up the initial game state.
     * Creates balls, blocks, and a paddle, and adds them to the game.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    public void initialize() {
        //create the balls in the radious and colors and speed we want
        Ball ball1 = new Ball(new Point(300, 300), 6, Color.WHITE, environment);
        Ball ball2 = new Ball(new Point(300, 250), 6, Color.WHITE, environment);
        Ball ball3 = new Ball(new Point(300, 200), 6, Color.WHITE, environment);

        ball1.setVelocity(3, 5);
        ball2.setVelocity(3, 5);
        ball1.addToGame(this);
        ball2.addToGame(this);
        ball3.setVelocity(5, 7);
        ball3.addToGame(this);
        ballsCounter.increase(3);
        Block endGame = new Block(new Rectangle(new Point(0, 620), 800, 20), Color.GRAY);
        BallRemover ballRemover = new BallRemover(this, ballsCounter);
        endGame.addToGame(this);
        endGame.addHitListener(ballRemover);
        Paddle p = createPaddle();
        p.addToGame(this);

        this.blockRemover = new BlockRemover(this, this.remainingBlocks);
        this.scoreListener = new ScoreTrackingListener(score);


        //we do create the blocks
        Block[] blocks = new Block[3];


        blocks[0] = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.darkGray);
        blocks[1] = new Block(new Rectangle(new Point(0, 0), 20, 700), Color.darkGray);
        blocks[2] = new Block(new Rectangle(new Point(780, 0), 20, 700), Color.darkGray);
        for (Block b : blocks) {
            b.addToGame(this);
        }



        Color[] rowColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.BLUE, Color.CYAN, Color.GRAY, Color.WHITE};

        // Create blocks and add them to the game
        int initialY = 80;
        int rowHeight = 20;
        int blockWidth = 50;
        int blocksInTopRow = 12;
        int maxRows = 6;

        // Calculate the starting X position to right-align the row
        for (int row = 0; row < maxRows; row++) {
            int blocksInCurrentRow = blocksInTopRow - row;  // Decrease blocks in each row
            Color currentRowColor = rowColors[row % rowColors.length];  // Get color for the current row

            // Calculate the starting X position to right-align the row
            int initialX = 780 - (blocksInCurrentRow * blockWidth);

            // Create blocks for the current row with the specified color
            for (int i = 0; i < blocksInCurrentRow; i++) {
                Block block = new Block(new Rectangle(new Point(initialX + i * blockWidth, initialY + row * rowHeight),
                        blockWidth, rowHeight), currentRowColor);
                block.addToGame(this);
                remainingBlocks.increase(1);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreListener);
            }
        }

// Add score indicator to the game
        ScoreIndicator indicator = new ScoreIndicator(scoreListener);
        indicator.addToGame(this);

    }


    private Paddle createPaddle(){
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        int speed = 6;
        Rectangle rect = new Rectangle(new Point(400,560),100,20);
        int barrier = 780;
        int startBarrier = 20;
        return new Paddle(rect,Color.ORANGE,keyboard,speed,barrier,startBarrier);
    }

    /**
     * Runs the game loop, updating the game state and rendering frames.
     */
    public void run() {
        //...
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.GREEN);
            d.fillRectangle(0,0,800,600);
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();
            gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;

            if (milliSecondLeftToSleep > 0) {

                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (remainingBlocks.getValue() == 0) {
                score.increase(100);
                DrawSurface d1 = gui.getDrawSurface();
                this.sprites.drawAllOn(d1);
                gui.show(d1);
                this.sprites.notifyAllTimePassed();

                sleeper.sleepFor(2500);
            }
            if (remainingBlocks.getValue() == 0 || ballsCounter.getValue() == 0) {
                gui.close();
            }
        }
    }


    public BlockRemover getBlockRemover() {
        return this.blockRemover;
    }
    /**
     * The main method creates a Game object, initializes it, and runs the game.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }

}
