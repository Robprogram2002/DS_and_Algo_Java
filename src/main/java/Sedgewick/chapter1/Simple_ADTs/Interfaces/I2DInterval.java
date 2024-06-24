package Sedgewick.chapter1.Simple_ADTs.Interfaces;

/**
 * Abstract Data Type that represent a 2-Dimensional interval in the Euclidean space
 */
public interface I2DInterval {

    /**
     * @return The x-axis interval
     */
    I1DInterval x_interval();

    /**
     * @return The y-axis interval
     */
    I1DInterval y_interval();

    /**
     * @return area of the interval
     */
    double area();

    /**
     * @return perimeter of the interval
     */
    double perimeter();

    /**
     * @param p 2-dimensional point to check if belongs to the interval
     * @return whether p is inside the interval
     */
    boolean contains(IPoint2D p);

    /**
     *
     * @param other 2-Dimensional Interval to check if it intersects with the
     *              current interval
     * @return whether both intervals intersect each other
     */
    boolean intersects(I2DInterval other);

    /**
     * draw the interval on StdDraw
     */
    void draw();

}
