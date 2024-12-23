package Collidable;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.*;
import Collidable.Collidable;
import Game.Game;
import Sprite.Sprite;
import Basics.Rectangle;
import Basics.Velocity;
import Basics.Line;
import Basics.Point;
import java.awt.Color;
import Sprite.Ball;
/**
 * The Paddle class represents the paddle in the game.
 * It implements the Collidable and Sprite interfaces to allow collision detection and drawing on the screen.
 * @author : dabbahk1
 * Id: 214075343
 */
public class Paddle implements Collidable,Sprite {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private Color color;

    private int barrier;
    private double speed;
    private int startBarrier;


    // Define the regions
    private static final int NUM_REGIONS = 5;
    /**
     * Constructor for the Paddle class.
     *
     * @param paddle       The rectangle representing the paddle.
     * @param color        The color of the paddle.
     * @param keyboard     The keyboard sensor for user input.
     * @param speed        The speed of the paddle movement.
     * @param barrier      The rightmost boundary for the paddle movement.
     * @param startBarrier The leftmost boundary for the paddle movement.
     */
    public Paddle(Rectangle paddle, Color color, KeyboardSensor keyboard,int speed,int barrier,int startBarrier) {
        this.paddle = paddle;
        this.color = color;
        this.keyboard = keyboard;
        this.speed = speed;
        this.barrier = barrier;
        this.startBarrier = startBarrier;
    }

    /**
     * Constructor for the Paddle class without a KeyboardSensor.
     *
     * @param paddle       The rectangle representing the paddle.
     * @param color        The color of the paddle.
     * @param barrier      The rightmost boundary for the paddle movement.
     * @param startBarrier The leftmost boundary for the paddle movement.
     */
    public Paddle(Rectangle paddle, Color color,int barrier,int startBarrier){
        this.paddle = paddle;
        this.color = color;
        this.barrier = barrier;
        this.startBarrier = startBarrier;
    }
    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        double newX = paddle.getUpperLeft().getX() - 5;
        if (newX < 0) {
            // If moving left reaches the left edge, wrap around to the right edge
            paddle = new Rectangle(new Point(800 - paddle.getWidth(), paddle.getUpperLeft().getY()),
                    paddle.getWidth(), paddle.getHeight());
        } else {
            paddle = new Rectangle(new Point(newX, paddle.getUpperLeft().getY()),
                    paddle.getWidth(), paddle.getHeight());
        }
    }
    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        double newX = paddle.getUpperLeft().getX() + 5;
        if (newX + paddle.getWidth() > 800) {
            // If moving right reaches the right edge, wrap around to the left edge
            paddle = new Rectangle(new Point(0, paddle.getUpperLeft().getY()),
                    paddle.getWidth(), paddle.getHeight());
        } else {
            paddle = new Rectangle(new Point(newX, paddle.getUpperLeft().getY()),
                    paddle.getWidth(), paddle.getHeight());
        }
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    @Override
    public Velocity hit(Ball hitter,Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double x = paddle.getUpperLeft().getX();
        double region = paddle.getWidth() / NUM_REGIONS;
        double hitX = collisionPoint.getX();
        double x1 = x + region;
        double x2 = x + 2*region;
        double x3 = x + 3*region;
        double x4 = x + 4*region;
        double x5 = x + 5*region;
        if (hitX >= x && hitX < x1){
            currentVelocity = Velocity.fromAngleAndSpeed(300,Velocity.vecSpeed(dx,dy));
        }else if (hitX >= x1 && hitX < x2){
            currentVelocity = Velocity.fromAngleAndSpeed(330,Velocity.vecSpeed(dx,dy));
        }else if (hitX >= x2 && hitX < x3){
            currentVelocity = new Velocity(dx,-dy);
        }else if (hitX >= x3 && hitX < x4){
            currentVelocity = Velocity.fromAngleAndSpeed(30,Velocity.vecSpeed(dx,dy));
        }else if (hitX >= x4 && hitX < x5){
            currentVelocity = Velocity.fromAngleAndSpeed(60,Velocity.vecSpeed(dx,dy));
        }

        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(color);
        drawSurface.fillRectangle((int) paddle.getUpperLeft().getX(), (int) paddle.getUpperLeft().getY(),
                (int) paddle.getWidth(), (int) paddle.getHeight());
    }

    @Override
    public void timePassed() {
        // Check for key presses and move accordingly
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        } else if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
    }

    /**
     * Adds the paddle to the given Game object.
     *
     * @param g The Game object to add the paddle to.
     */
    public void addToGame(Game g){
        g.addSprite(this);
        g.addCollidable(this);
    }
}