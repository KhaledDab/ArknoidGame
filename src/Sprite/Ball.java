package Sprite;

import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;
import Game.Game;
import Basics.Point;
import Basics.Velocity;
import Basics.Line;
import Game.GameEnvironment;
import Collidable.CollisionInfo;


/**
 * The Ball class represents a ball in a 2D space.
 * @author :dabbahk1
 * Id :214075343
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Velocity velocity;
    private GameEnvironment environment;

    private Color color;


    /**
     * Constructor for creating a ball with specified parameters.
     *
     * @param center      The center point of the ball.
     * @param radius      The radius of the ball.
     * @param velocity    The velocity of the ball.
     * @param environment The game environment in which the ball operates.
     */
    public Ball(Point center, int radius, Velocity velocity, GameEnvironment environment) {
        this.center = center;
        this.radius = radius;
        this.velocity = velocity;
        this.environment = environment;
    }
    /**
     * Constructor for creating a ball with specified parameters.
     *
     * @param center      The center point of the ball.
     * @param radius      The radius of the ball.
     * @param color       The color of the ball.
     * @param environment The game environment in which the ball operates.
     */
    public Ball(Point center,int radius, Color color, GameEnvironment environment){
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.environment = environment;
    }

    /**
     * Get the x-coordinate of the ball's center point.
     *
     * @return The x-coordinate of the center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get the y-coordinate of the ball's center point.
     *
     * @return The y-coordinate of the center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }


    /**
     * Get the size (radius) of the ball.
     *
     * @return The radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }
    public void setColor(Color color){
        this.color = color;
    }
    public void setSize(int size){
        this.radius = size;
    }
    public static Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat(), g = rand.nextFloat(), b = rand.nextFloat();
        return new Color(r, g, b);
    }

    /**
     * Get the color of the ball.
     *
     * @return The color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Move the ball one step,
     * considering collisions with the environment and screen borders.
     */

    public void moveOneStep() {

        Point target = this.velocity.applyToPoint(this.center);
        Line trajectory = new Line(this.center, target);
        CollisionInfo c = environment.getClosestCollision(trajectory);
        if (c == null) {
            this.center = target;
        } else {
            double y = this.center.getY(), x = this.center.getX();
            Point cPoint = c.collisionPoint();
            if (this.center.getX() > c.collisionPoint().getX()) {
                x = cPoint.getX() + this.radius;
            } else {
                x = cPoint.getX() - this.radius;
            }
            if (this.center.getY() > c.collisionPoint().getY()) {
                y = cPoint.getY() + this.radius;
            } else if (this.center.getY() < c.collisionPoint().getY()) {
                y = cPoint.getY() - this.radius;
            }

            Point newP = new Point(x, y);
            this.velocity = c.collisionObject().hit(this, cPoint, this.velocity);
//            Basics.Line Tline = new Basics.Line(center, newP);
//            this.center = Tline.middle();
            this.center = this.velocity.applyToPoint(center);
//            if (game.getClosestCollision(Tline) == null) {
//                this.center = newP;
//            }

        }
    }

    /**
     * Check if the ball hits the screen borders and update the velocity accordingly.
     */
    private void checkScreenBorders() {
        // Check if the ball hits the left or right border
        if (center.getX() - radius <= 0 || center.getX() + radius >= 800) {
            velocity = new Velocity(-velocity.getDx(), velocity.getDy());
        }

        // Check if the ball hits the top or bottom border
        if (center.getY() - radius <= 0 || center.getY() + radius >= 600) {
            velocity = new Velocity(velocity.getDx(), -velocity.getDy());
        }
    }
    /**
     * Set the velocity of the ball given changes in position on the x and y axes.
     *
     * @param dx Change in position on the x axis.
     * @param dy Change in position on the y axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }


    /**
     * Get the velocity of the ball.
     *
     * @return The velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color); // Set the color of the ball (you can customize this)
        surface.fillCircle((int) center.getX(), (int) center.getY(), radius); // Draw a filled circle for the ball
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Add the ball to the specified game's sprite collection.
     *
     * @param g The game to which the ball will be added.
     */
    public void addToGame(Game g){
        g.addSprite(this);
    }
    /**
     * Remove the ball from the specified game's sprite collection.
     *
     * @param game The game from which the ball will be removed.
     */
    public void removeFromGame(Game game){
        game.removeSprite(this);
    }

}