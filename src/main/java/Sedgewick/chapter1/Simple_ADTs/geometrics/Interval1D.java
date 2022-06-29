package Sedgewick.chapter1.Simple_ADTs.geometrics;

import Sedgewick.libraries.StdDraw;

/**
 * Class that represent an abstraction of an interval in real line
 */
public class Interval1D {
    private double lo;
    private double hi;

    /**
     * create an interval given lower and upper limits
     *
     * @param lo real value representing lower limit
     * @param hi real value representing upper limit
     * @throws IllegalArgumentException if either {@code x} or {@code y}
     *                                  is {@code Double.NaN}
     */
    public Interval1D(double lo, double hi) {
        if (Double.isNaN(lo) || Double.isNaN(hi)) throw new IllegalArgumentException("interval limits cannot be NaN");
        if (lo == hi) {
            throw new IllegalArgumentException("Limits cannot be equal");
        }

        this.lo = lo;
        this.hi = hi;
    }

    /**
     * @return length of the interval
     */
    public double length() {
        return Math.abs(hi - lo);
    }

    /**
     * @param x real value
     * @return boolean representing whether this value is in the interval
     */
    public boolean contains(double x) {
        return lo <= x && x <= hi;
    }

    /**
     * @return lower limit
     */
    public double lower() {
        return this.lo;
    }

    /**
     * @return upper limit
     */
    public double upper() {
        return this.hi;
    }

    /**
     * @param that Interval1D instance to check if there exist an intersection
     * @return boolean representing whether the two interval intersect
     */
    public boolean intersects(Interval1D that) {
        if (that.contains(lo) || that.contains(hi)) return true;
        else return this.contains(that.lower()) || this.contains(that.upper());
    }

    public void draw() {
        StdDraw.setXscale(lo / 2.0, hi * 2.0);
        StdDraw.setYscale(0, 10);
        StdDraw.line(lo, 1, hi, 1);
    }

    /**
     * @return the middle point of the interval
     */
    public double middle() {
        return (this.hi - this.lo) / 2.0;
    }

    public static void main(String[] args) {
        Interval1D i1 = new Interval1D(1.3, 6.2);
        i1.draw();
    }

}
