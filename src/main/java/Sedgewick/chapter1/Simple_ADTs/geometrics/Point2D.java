package Sedgewick.chapter1.Simple_ADTs.geometrics;


import Sedgewick.libraries.StdDraw;


/**
 * Class that represent an abstraction of a point in the 2-dimensional euclidean space
 */
public class Point2D {
    private double x;
    private double y;

    /**
     * Create a Point given its coordinates
     *
     * @param x real number representing coordinate on the x-axes
     * @param y real number representing coordinate on the y-axes
     * @throws IllegalArgumentException if either {@code x} or {@code y}
     *                                  is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY} or
     *                                  {@code Double.NEGATIVE_INFINITY}
     */
    public Point2D(double x, double y) {
        if (Double.isInfinite(x) || Double.isInfinite(y))
            throw new IllegalArgumentException("Coordinates must be finite");
        if (Double.isNaN(x) || Double.isNaN(y))
            throw new IllegalArgumentException("Coordinates cannot be NaN");
        this.x = x;
        this.y = y;
    }

    /**
     * @return coordinate on the x-axis
     */
    public double x() {
        return this.x;
    }

    /**
     * @return coordinate on the y-axis
     */
    public double y() {
        return this.y;
    }

    /**
     * @return radius of the point in polar coordinates
     */
    public double radius() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * @return the angle (in radians) of this point in polar coordinates (between –&pi; and &pi;)
     */
    public double theta() {
        return Math.atan2(y, x);
    }

    /**
     * @param that The other point
     * @return Euclidean distance from this point to that
     */
    public double distTo(Point2D that) {
        double dist_x = x - that.x();
        double dist_y = y - that.y();
        return Math.sqrt(Math.pow(dist_x, 2) + Math.pow(dist_y, 2));
    }

    /**
     * Draw the point represented by the object in the plane
     */
    public void draw() {
        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 10);
        StdDraw.point(x, y);
        StdDraw.circle(x, y, 2);
    }

    public static void main(String[] args) {
        Point2D p1 = new Point2D(2.3, 3.2);
        p1.draw();
    }

}
