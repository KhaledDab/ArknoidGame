package Sprite;

import biuoop.DrawSurface;

import java.util.LinkedList;
import java.util.List;
/**
 * The SpriteCollection class represents a collection of sprites in a game.
 * It provides methods for adding sprites, notifying all sprites about time passage, and drawing all sprites on a DrawSurface.
 *
 * @author dabbahk1
 * Id : 214075343
 */
public class SpriteCollection {
    private List<Sprite> sprites;
    /**
     * Constructs a SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection(){
        this.sprites = new LinkedList<>();
    }
    /**
     * Adds a sprite to the collection.
     *
     * @param s The sprite to be added.
     */
    public void addSprite(Sprite s){
        sprites.add(s);
    }
    public void removeSprite(Sprite s){
        sprites.remove(s);
    }
    /**
     * Notifies all sprites in the collection about the passage of time.
     */

    public void notifyAllTimePassed() {
        LinkedList<Sprite> copy = new LinkedList<>(sprites);
        for (Sprite s : copy) {
            s.timePassed();
        }
    }
    /**
     * Draws all sprites in the collection on the specified DrawSurface.
     *
     * @param d The DrawSurface on which the sprites will be drawn.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

}
