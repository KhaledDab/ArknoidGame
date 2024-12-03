package Sprite;

import biuoop.DrawSurface;
/**
 * The Sprite interface represents objects that can be drawn on the screen and are updated over time in a game.
 * It defines methods for drawing the sprite and handling the passage of time.
 *
 * @author : dabbahk1
 * Id : 214075343
 */
public interface Sprite {
    /**
     * Draws the sprite on the specified DrawSurface.
     *
     * @param surface The DrawSurface on which the sprite will be drawn.
     */
    void drawOn(DrawSurface surface);

    /**
     * Notifies the sprite that a unit of time has passed, allowing it to update its state.
     */
    void timePassed();
}