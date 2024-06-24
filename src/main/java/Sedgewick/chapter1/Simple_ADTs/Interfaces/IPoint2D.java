package Sedgewick.chapter1.Simple_ADTs.Interfaces;

/**
 * Abstract Data Type that represent a point in the 2-dimensional Euclidean space
 */
public interface IPoint2D extends Comparable<IPoint2D> {
    /**
     * Get the point x-coordinate
     * @return x-coordinate
     */
    double x();

    /**
     * Get the point y-coordinate
     * @return y-coordinate
     */
    double y();

    /**
     * Get the point radius in polar coordinates
     * @return point radius
     */
    double r();

    /**
     * Get the point angle in polar coordinates
     * @return angle (in radians between –&pi; and &pi;)
     */
    double theta();

    /**
     * Get the Euclidean distance from this point to the given point
     * @param point The other Point2D object
     * @return The distance between the two Point2D objects
     */
    double distanceTo(IPoint2D point);

    /**
     * Draw the point using StdDraw
     */
    void draw();
}
