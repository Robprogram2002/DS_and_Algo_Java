package Sedgewick.chapter1.Simple_ADTs.Interfaces;

/**
 * Abstract Data Type that represent a 1-Dimensional interval in the Real line
 */
public interface I1DInterval {

    /**
     * @return lower limit
     */
    double lower();

    /**
     * @return upper limit
     */
    double upper();

    /**
     * @return length of the interval
     */
    double length();

    /**
     * Check whether the  given real value is inside the interval
     *
     * @param x A real number
     * @return boolean value determining if the interval contain the value of {@code x}
     */
    boolean contains(double x);

    /**
     * Check whether the two intervals intersect
     *
     * @param that The other 1-Dimensional interval object
     * @return Boolean value specifying if intervals intersect between each other
     */
    boolean intersects(I1DInterval that);

    /**
     * @return the middle point of the interval
     */
    double middle();

    /**
     * draw the interval on StdDraw
     */
    void draw();
}
