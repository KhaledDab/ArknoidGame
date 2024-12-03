package Collidable;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import Game.Game;
import Sprite.Sprite;
import Basics.Rectangle;
import Basics.Point;
import Basics.Velocity;
import Listeners.HitNotifier;
import Listeners.HitListener;
import Sprite.Ball;

/**
 * The Block class represents a rectangular block in a 2D space.
 * @author : dabbahk1
 * Id :214075343
 */
public class Block implements Collidable , Sprite,HitNotifier{
    List<HitListener> hitListeners;
    private Rectangle rectangle;
    private Color color;
    private Game game;
    /**
     * Constructor for creating a block with specified parameters.
     *
     * @param rectangle The rectangle representing the block's shape.
     * @param c     The color of the block.
     */
    public Block (Rectangle rectangle, Color c){
        this.rectangle = rectangle;
        this.color = c;
        this.hitListeners = new ArrayList<>();
    }
    public Block(Rectangle rectangle,Color c,Game game){
        this.rectangle = rectangle;
        this.color = c;
        this.game = game;
        this.hitListeners = new ArrayList<>();
    }
    /**
     * Get the color of the block.
     *
     * @return The color of the block.
     */
    public Color getColor() {
        return this.color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter,Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // Calculate the sides of the rectangle
        double leftX = this.rectangle.getUpperLeft().getX();
        double rightX = leftX + this.rectangle.getWidth();
        double upperY = this.rectangle.getUpperLeft().getY();
        double lowerY = upperY + this.rectangle.getHeight();

        // If the collision is on the left or right side, flip the X velocity
        if ((collisionPoint.getX() == leftX ) || (collisionPoint.getX() == rightX )) {
            dx = -dx;
        }

        // If the collision is on the top or bottom side, flip the Y velocity
        if ((collisionPoint.getY() == upperY ) || (collisionPoint.getY() == lowerY )) {
            dy = -dy;

        }
        if (!ballColorMatch(hitter) && !this.getColor().equals(Color.darkGray)){
            hitter.setColor(this.getColor());
            this.notifyHit(hitter);
            this.removeFromGame(game);
        }


        return new Velocity(dx,dy);
    }

    /**
     * Add the block to the specified game's sprite collection and collidables.
     *
     * @param game The game to which the block will be added.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
        if (this.getColor() != Color.darkGray) {
            this.addHitListener(game.getBlockRemover());
        }
    }



    @Override
    public void drawOn(DrawSurface drawSurface) {
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        int width = (int)this.rectangle.getWidth();
        int height = (int)this.rectangle.getHeight();
        drawSurface.setColor(color);
        drawSurface.fillRectangle(x,y,width,height);
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawRectangle(x,y,width,height);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.add(hl);
        }
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    public void removeFromGame(Game game){
        if(game != null) {
            game.removeCollidable(this);
            game.removeSprite(this);
        }
    }
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    private boolean ballColorMatch(Ball ball){
        return this.getColor().equals(ball.getColor());
    }

}
