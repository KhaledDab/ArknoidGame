package Basics;

import java.util.List;

/**
 * @author  : dabbahk1
 * ID : 214075343
 */

/**
 * The Line class represents a line segment in a two-dimensional space.
 * It provides methods for calculating the length, midpoint, and intersection with other lines.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructs a line segment between two given points.
     *
     * @param start The starting point of the line.
     * @param end   The ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Constructs a line segment using the coordinates of two points.
     *
     * @param x1 The x-coordinate of the starting point.
     * @param y1 The y-coordinate of the starting point.
     * @param x2 The x-coordinate of the ending point.
     * @param y2 The y-coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Calculates and returns the length of the line.
     *
     * @return The length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the midpoint of the line.
     *
     * @return The midpoint of the line.
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the starting point of the line.
     *
     * @return The starting point of the line.
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the ending point of the line.
     *
     * @return The ending point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * Checks if this line is intersecting with another line.
     *
     * @param other The other line to check for intersection.
     * @return True if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return isIntersecting(this, other);
    }

    /**
     * Checks if two lines are intersecting.
     *
     * @param line1 The first line.
     * @param line2 The second line.
     * @return True if the lines intersect, false otherwise.
     */
    public static boolean isIntersecting(Line line1, Line line2) {
        double x1 = line1.start.getX();
        double y1 = line1.start.getY();
        double x2 = line1.end.getX();
        double y2 = line1.end.getY();

        double x3 = line2.start.getX();
        double y3 = line2.start.getY();
        double x4 = line2.end.getX();
        double y4 = line2.end.getY();

        double denominator = ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));

        if (denominator == 0) {
            return false; // Lines are parallel
        }

        double intersectionX = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / denominator;
        double intersectionY = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / denominator;

        // Check if the intersection point lies on both line segments
        boolean onLine1 = isBetween(intersectionX, x1, x2) && isBetween(intersectionY, y1, y2);
        boolean onLine2 = isBetween(intersectionX, x3, x4) && isBetween(intersectionY, y3, y4);

        return onLine1 && onLine2;
    }

    private static boolean isBetween(double value, double start, double end) {
        return value >= Math.min(start, end) && value <= Math.max(start, end);
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other The other line to check for intersection.
     * @return The intersection point if the lines intersect, or null otherwise.
     */
    public Point intersectionWith(Line other) {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();

        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        double denominator = ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));

        if (denominator == 0) {
            return null; // Lines are parallel
        }

        double intersectionX = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / denominator;
        double intersectionY = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / denominator;

        // Check if the intersection point lies on both line segments
        if (isBetween(intersectionX, x1, x2) && isBetween(intersectionY, y1, y2) &&
                isBetween(intersectionX, x3, x4) && isBetween(intersectionY, y3, y4)) {
            return new Point(intersectionX, intersectionY);
        } else {
            return null;
        }
    }

    /**
     * Checks if this line is equal to another line.
     *
     * @param other The other line to check for equality.
     * @return True if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return start.equals(other.start) && end.equals(other.end);
    }

    /**
     * Finds the closest intersection point to the start of the line with a given rectangle.
     *
     * @param rect The rectangle to check for intersection.
     * @return The closest intersection point, or null if no intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }

        // Find the closest intersection point to the start of the line
        Point closestPoint = intersectionPoints.get(0);
        double closestDistance = closestPoint.distance(this.start);

        for (Point intersectionP : intersectionPoints) {
            if (intersectionP.distance(start) < closestDistance){
                closestPoint = intersectionP;
                closestDistance = intersectionP.distance(start);
            }
        }

        return closestPoint;
    }
}