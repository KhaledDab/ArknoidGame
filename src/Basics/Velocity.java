package Basics;

/**
 * A velocity class.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author : dabbahk1
 * Id : 214075343
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     * given the dx and dy.
     *
     * @param dx : change in position on the x axis.
     * @param dy : change in position on the y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructor.
     * specify the velocity in terms and angle and a speed.
     *
     * @param angle : degree.
     * @param speed : perform steps.
     * @return the velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //if (angle == 90 || angle == 180 || angle == 270 || angle == 360) { //fixed
            //angle = 0;
        //}
        double dx = speed * Math.cos(Math.toRadians(angle-90));
        double dy = speed * Math.sin(Math.toRadians(angle-90));
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p : point that will be changed.
     * @return (x + dx, y + dy) while p=(x,y).
     */

    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * @return change in position on the x axis.
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return change in position on the y axis.
     */
    public double getDy() {
        return dy;
    }

    /**
     * return speed and get dx,dy .
     * @param dx = the x speed.
     * @param dy = the y speed.
     * @return the velocity.
     */
    public static double vecSpeed(double dx, double dy ){
        double m = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
        return m;
    }
}

