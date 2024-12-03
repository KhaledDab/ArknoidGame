package Listeners;
import Collidable.Block;
import Sprite.Ball;

/**
 * The HitListener interface represents objects that listen to hit events.
 * It defines a method to be called when a "hit" event occurs.
 * @author : dabbahk1
 * ID : 214075343
 */
public interface HitListener {
    /**
     * This method is called whenever the `beingHit` object is hit by the `hitter` Ball.
     *
     * @param beingHit The Block that is being hit.
     * @param hitter   The Ball that is doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
