package Game;
import Listeners.HitListener;
import Collidable.Block;
import Sprite.Ball;

/**
 * The ScoreTrackingListener class is a HitListener that updates the player's score when a block is hit.
 * @author : dabbahk1
 * ID : 214075343
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor for the ScoreTrackingListener class.
     *
     * @param scoreCounter The counter to track the player's score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Updates the player's score when a block is hit.
     *
     * @param beingHit The block that was hit.
     * @param hitter   The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5); // Increase the score by 5 when a block is hit
    }

    /**
     * Get the current score.
     *
     * @return The current score counter.
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }
}
