package Collidable;
import Basics.Point;

/**
 * The CollisionInfo class represents information about a collision event between two objects.
 * It holds the collision point and the collidable object involved in the collision.
 * @author :dabbahk1
 * Id : 214075343
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor for creating a CollisionInfo object with specified parameters.
     *
     * @param collisionPoint  The point at which the collision occurs.
     * @param collisionObject The collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject){
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }
    /**
     * Get the point at which the collision occurs.
     *
     * @return The collision point.
     */
    public Point collisionPoint(){
        return this.collisionPoint;
    }

    /**
     * Get the collidable object involved in the collision.
     *
     * @return The collidable object.
     */
    public Collidable collisionObject(){
        return this.collisionObject;
    }


}
