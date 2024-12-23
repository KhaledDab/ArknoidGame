package Game;

import java.util.ArrayList;
import java.util.List;
import Collidable.Block;
import Listeners.HitListener;
import Sprite.Ball;
/**
 * The BlockRemover class is responsible for removing blocks from the game,
 * as well as removing itself as a listener from those blocks.
 * @author : dabbahk1
 * ID : 214075343
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;
    private List<HitListener> listenersToRemove;

    /**
     * Constructor for creating a BlockRemover with the specified game and counter of remaining blocks.
     *
     * @param game           The game to which the BlockRemover is associated.
     * @param remainingBlocks The counter of remaining blocks.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
        this.listenersToRemove = new ArrayList<>();
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * It removes the block from the game, removes this listener from the block,
     * and decreases the remaining blocks counter by 1.
     *
     * @param beingHit The block that was hit.
     * @param hitter   The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}
