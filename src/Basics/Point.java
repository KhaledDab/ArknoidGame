package Basics;

/**
 * The Point class represents a point in a two-dimensional space.
 * It has methods for calculating the distance between points, checking equality, and retrieving x and y values.
 *
 * @author dabbahk1
 * Id : 214075343
 *
 */
public class Point {
    private double x;
    private double y ;
    /**
     * Constructs a Point with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Sets the x-coordinate of the point.
     *
     * @param x The new x-coordinate.
     */
    public void setX(double x){
        this.x = x;
    }
    /**
     * Sets the y-coordinate of the point.
     *
     * @param y The new y-coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other The other point to calculate the distance to.
     * @return The distance between this point and the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x-other.getX())*(this.x-other.getX()))
                +((this.y-other.getY())*(this.y-other.getY())));

    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other The other point to compare with.
     * @return True if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return The x-coordinate of this point.
     */
    public double getX() {

        return this.x;
    }
    /**
     * Returns the y-coordinate of this point.
     *
     * @return The y-coordinate of this point.
     */
    public double getY() {

        return this.y;
    }
}
