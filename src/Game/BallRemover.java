package Game;
import Sprite.Ball;
import Collidable.Block;
import Listeners.HitListener;
/**
 * The BallRemover class is responsible for removing balls from the game,
 * as well as decreasing the remaining balls counter.
 * @author : dabbahk1
 * ID : 214075343
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructor for creating a BallRemover with the specified game and counter of remaining balls.
     *
     * @param game           The game to which the BallRemover is associated.
     * @param remainingBalls The counter of remaining balls.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * It removes the ball from the game and decreases the remaining balls counter by 1.
     *
     * @param beingHit The block that was hit.
     * @param hitter   The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
