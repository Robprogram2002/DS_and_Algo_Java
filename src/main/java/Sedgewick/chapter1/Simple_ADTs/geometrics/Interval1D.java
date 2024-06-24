package Sedgewick.chapter1.Simple_ADTs.geometrics;

import Sedgewick.chapter1.Simple_ADTs.Interfaces.I1DInterval;
import Sedgewick.libraries.StdDraw;
import Sedgewick.libraries.StdOut;
import org.jetbrains.annotations.NotNull;

/**
 * Simple implementation of the I1DInterval ADT
 */
public class Interval1D implements I1DInterval {
    /**
     * Left end of the interval
     */
    final private double low;

    /**
     * Right end of the interval
     */
    final private double high;

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

        this.low = lo;
        this.high = hi;
    }

    public double length() {
        return Math.abs(high - low);
    }

    public boolean contains(double x) {
        return low <= x && x <= high;
    }

    public double lower() {
        return this.low;
    }

    public double upper() {
        return this.high;
    }

    public boolean intersects(I1DInterval that) {
        double that_high = that.upper();
        if (this.high > that_high) {
            return this.low < that_high;
        }
        return this.high > that.lower();

        // if (that.contains(low) || that.contains(high)) return true;
        // else return this.contains(that.lower()) || this.contains(that.upper());
    }

    public void draw() {
        StdDraw.setXscale(low / 2.0, high * 2.0);
        StdDraw.setYscale(0, 10);
        StdDraw.line(low, 1, high, 1);
    }

    public double middle() {
        return (this.high - this.low) / 2.0;
    }

    @Override
    public String toString() {
        return "[" + low + "," + high + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Interval1D interval = (Interval1D) o;
        return (this.high == interval.high) && (this.low == interval.low);
    }

    public static void main(String[] args) {
        Interval1D i1 = new Interval1D(1.3, 6.2);
        Interval1D i2 = new Interval1D(0.2, 1.4);
        StdOut.println(i1.upper());
        StdOut.println(i1.lower());
        StdOut.println(i1.length());
        StdOut.println(i1.middle());
        StdOut.println(i1.contains(7.0));
        StdOut.println(i1.equals(new Interval1D(1.3, 3.2)));
        StdOut.println(i1.intersects(i2));
        StdOut.println(i1 + " | " + i2);

        i1.draw();
    }

}
