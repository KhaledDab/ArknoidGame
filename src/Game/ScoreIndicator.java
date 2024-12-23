package Game;

import biuoop.DrawSurface;

import java.awt.Color;
import Sprite.Sprite;
import Basics.Rectangle;
import Basics.Point;
/**
 * The ScoreIndicator class represents the score indicator in the game.
 * It implements the Sprite interface to allow drawing the score on the screen.
 * @author : dabbahk1
 * ID : 214075343
 */
public class ScoreIndicator implements Sprite {

    private Rectangle rect;             // The rectangle for the score indicator
    private Color color;                // The color of the score indicator
    private ScoreTrackingListener score; // The score tracking listener

    /**
     * Constructor for the ScoreIndicator class.
     *
     * @param score The ScoreTrackingListener object for tracking the score.
     */
    public ScoreIndicator(ScoreTrackingListener score) {
        this.rect = new Rectangle(new Point(0, 0), 800, 20);
        this.color = Color.LIGHT_GRAY;
        this.score = score;
    }

    /**
     * Draws the score indicator on the given DrawSurface.
     *
     * @param d The DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.rect.getUpperLeft().getX();
        int y = (int) this.rect.getUpperLeft().getY();
        int width = (int) this.rect.getWidth();
        int height = (int) this.rect.getHeight();

        // Draw the background rectangle
        d.setColor(color);
        d.fillRectangle(x, y, width, height);

        // Draw the border
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);

        // Draw the score text
        d.drawText(320, 15, "Score: " + score.getCurrentScore().getValue(), 15);
    }

    /**
     * Empty method for the time passed - no updates needed.
     */
    @Override
    public void timePassed() {
        // No updates needed
    }

    /**
     * Adds the score indicator to the given Game object.
     *
     * @param game The Game object to add the score indicator to.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
