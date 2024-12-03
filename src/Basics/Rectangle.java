package Basics;

import java.util.ArrayList;
import java.util.List;
import Basics.Line;
import Basics.Point;

/**
 * The Rectangle class represents a rectangle in a two-dimensional space.
 * It has methods for calculating intersection points with a line, retrieving width, height, and upper-left point.
 *
 * @author : dabbahk1
 * Id : 214075343
 */

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;


    /**
     * Constructs a Rectangle with the specified upper-left point, width, and height.
     *
     * @param upperLeft The upper-left point of the rectangle.
     * @param width     The width of the rectangle.
     * @param height    The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Calculates and returns a list of intersection points with the specified line.
     *
     * @param line The line to check for intersections.
     * @return A list of intersection points with the line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();

        // Calculate the rectangle's vertices
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);

        // Create the rectangle's edges
        Line topEdge = new Line(upperLeft, upperRight);
        Line leftEdge = new Line(upperLeft, lowerLeft);
        Line bottomEdge = new Line(lowerLeft, lowerRight);
        Line rightEdge = new Line(upperRight, lowerRight);

        // Check for intersections with each edge
        if (line.isIntersecting(topEdge)) {
            intersectionPoints.add(line.intersectionWith(topEdge));
        }
        if (line.isIntersecting(leftEdge)) {
            intersectionPoints.add(line.intersectionWith(leftEdge));
        }
        if (line.isIntersecting(bottomEdge)) {
            intersectionPoints.add(line.intersectionWith(bottomEdge));
        }
        if (line.isIntersecting(rightEdge)) {
            intersectionPoints.add(line.intersectionWith(rightEdge));
        }

        return intersectionPoints;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return The upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
}
