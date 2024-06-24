package Sedgewick.chapter1.Simple_ADTs.geometrics;

import Sedgewick.chapter1.Simple_ADTs.Interfaces.I1DInterval;
import Sedgewick.chapter1.Simple_ADTs.Interfaces.I2DInterval;
import Sedgewick.chapter1.Simple_ADTs.Interfaces.IPoint2D;
import Sedgewick.libraries.StdDraw;
import Sedgewick.libraries.StdOut;

/**
 * Simple implementation of the I2DInterval ADT
 */
public class Interval2D implements I2DInterval {
    /**
     * x-axis 1-dimensional interval
     */
    final private Interval1D ix;
    /**
     * y-axis 1-dimensional interval
     */
    final private Interval1D iy;

    /**
     * create a 2D interval given a 1D interval for each axes
     *
     * @param ix x-axes 1D interval
     * @param iy y-axes 1D interval
     */
    public Interval2D(Interval1D ix, Interval1D iy) {
        this.ix = ix;
        this.iy = iy;
    }

    public I1DInterval x_interval() {
        return ix;
    }

    public I1DInterval y_interval() {
        return iy;
    }

    public double area() {
        return this.ix.length() * this.iy.length();
    }

    public double perimeter() {
        return this.ix.length() * 2 + this.iy.length() * 2;
    }

    public boolean contains(IPoint2D p) {
        return this.ix.contains(p.x())
                && this.iy.contains(p.y());
    }

    public boolean intersects(I2DInterval that) {
        return this.ix.intersects(that.x_interval())
                || this.iy.intersects(that.y_interval());
    }

    public void draw() {
        // StdDraw.setXscale(this.ix.lower() / 2.0, this.ix.upper() * 2.0);
        // StdDraw.setYscale(this.iy.lower() / 2.0, this.iy.upper() * 2.0);

        StdDraw.setYscale(0, 10);
        StdDraw.setXscale(0, 10);

        StdDraw.filledRectangle(this.ix.middle(), this.iy.middle(),
                this.ix.length() / 2.0, this.iy.length() / 2.0);
    }

    @Override
    public String toString() {
        return "Interval2D {ix=" + ix + ", iy=" + iy + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Interval2D interval = (Interval2D) o;
        return (this.ix == interval.x_interval()) && (this.iy == interval.y_interval());
    }

    public static void main(String[] args) {
        Interval1D i1 = new Interval1D(6, 8.2);
        Interval1D i2 = new Interval1D(6.0, 7.2);
        Interval2D d2 = new Interval2D(i1, i2);

        StdOut.println(d2.area());
        StdOut.println(d2.perimeter());
        StdOut.println(d2.x_interval());
        StdOut.println(d2.y_interval());
        StdOut.println(d2.contains(new Point2D(2.5, 5.0)));
        StdOut.println(d2);

        d2.draw();
    }
}


