package Collidable;

import biuoop.DrawSurface;
import Sprite.Ball;
import Basics.Rectangle;
import Basics.Velocity;
import Basics.Point;
/**
 * The Collidable interface represents objects that can participate in collisions in a game.
 * It defines methods for retrieving the collision shape, handling collisions, and drawing the object.
 *
 * @author :dabbahk1
 * Id : 214075343
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object, which is usually a rectangle.
     *
     * @return The collision rectangle of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the given collision point with a specified velocity.
     *
     * @param collisionPoint    The point where the collision occurred.
     * @param currentVelocity   The current velocity of the colliding object.
     * @return The new velocity expected after the hit, based on the force inflicted by the object.
     */
    Velocity hit(Ball hitter,Point collisionPoint, Velocity currentVelocity);

    /**
     * Draws the collidable object on the specified DrawSurface.
     *
     * @param drawSurface The DrawSurface on which the object will be drawn.
     */
    void drawOn(DrawSurface drawSurface);
}
