package Game;

import java.util.LinkedList;
import java.util.List;

import Collidable.Collidable;
import Collidable.CollisionInfo;
import Basics.Point;
import Basics.Line;
import biuoop.DrawSurface;

/**
 * The GameEnvironment class represents the collection of collidable objects in the game.
 * It manages adding collidables, detecting collisions, and drawing collidable objects.
 * @author :dabbahk1
 * Id : 214075343
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * Constructor for creating a GameEnvironment object.
     * Initializes the list of collidables.
     */
    public GameEnvironment(){
        this.collidables = new LinkedList<>();
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to be added.
     */
    public void addCollidable(Collidable c){
        this.collidables.add(c);
    }
    public void removeCollidable(Collidable c){
        this.collidables.remove(c);
    }

    /**
     * Draws all collidable objects on the given DrawSurface.
     *
     * @param d The DrawSurface to draw on.
     */
    public void drawCollidable(DrawSurface d){
        for(Collidable c :collidables){
            return;
        }
    }

    /**
     * Finds the closest collision point between a trajectory and any collidable in the environment.
     * If no collision is found, returns null. Otherwise, returns information about the closest collision.
     *
     * @param trajectory The trajectory line of the moving object.
     * @return Information about the closest collision, or null if no collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory){
        List<CollisionInfo> points = new LinkedList<CollisionInfo>();
        for (Collidable c : collidables){
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null){
                CollisionInfo collision = new CollisionInfo(p, c);
                points.add(collision);
            }
        }
        if (points.size() == 0){
            return null;
        }
        Point close = points.get(0).collisionPoint();
        CollisionInfo closest = points.get(0);
        double dis = close.distance(trajectory.start());
        for (CollisionInfo p2 : points){
            if (p2.collisionPoint().distance(trajectory.start()) < dis){
                closest = p2;
                dis = p2.collisionPoint().distance(trajectory.start());
            }
        }
        return closest;
    }

}
