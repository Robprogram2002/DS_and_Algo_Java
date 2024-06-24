package Sedgewick.chapter1.Simple_ADTs.geometrics;


import Sedgewick.chapter1.Simple_ADTs.Interfaces.IPoint2D;
import Sedgewick.libraries.StdDraw;
import Sedgewick.libraries.StdOut;
import org.jetbrains.annotations.NotNull;


/**
 * Simple Implementation of a point in the 2-Dimensional Euclidean Space
 */
public class Point2D implements IPoint2D {
    final private double x;
    final private double y;

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

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public double r() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double theta() {
        return Math.atan2(y, x);
    }

    public double distanceTo(IPoint2D point) {
        double dist_x = x - point.x();
        double dist_y = y - point.y();
        return Math.sqrt(Math.pow(dist_x, 2) + Math.pow(dist_y, 2));
    }

    public void draw() {
        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 10);
        StdDraw.point(x, y);
        StdDraw.circle(x, y, 2);
    }

    /**
     * Compare the distance from the origin of two points
     *
     * @param o the object to be compared.
     * @return 0 if both have the same distance to the origin, a number greater than 0 if
     * this object has a greater distance and a number lower than 0 if the other object has
     * a greater distance
     */
    @Override
    public int compareTo(@NotNull IPoint2D o) {
        return Double.compare(this.r() - o.r(), 0.0);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point = (Point2D) o;
        if (Double.compare(point.x, x) != 0) return false;
        return Double.compare(point.y, y) == 0;
    }

    public static void main(String[] args) {
        Point2D p1 = new Point2D(2.3, 3.2);
        Point2D p2 = new Point2D(1.0, 1.0);
        StdOut.println(p1.r());
        StdOut.println(p1.theta());
        StdOut.println(p1.distanceTo(p2));
        StdOut.println(p1.equals(p2));
        StdOut.println(p1.compareTo(p2));
        StdOut.println(p1 + " | " + p2);

        p1.draw();

    }


}
