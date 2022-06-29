package Sedgewick.chapter1.Simple_ADTs.geometrics;

import Sedgewick.libraries.StdDraw;

/**
 * Class that represent an abstraction of an 2-dimensional interval in the euclidean plane
 */
public class Interval2D {
    private Interval1D ix;
    private Interval1D iy;

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

    /**
     * @return area of the interval
     */
    public double area() {
        return this.ix.length() * this.iy.length();
    }

    /**
     * @param p 2 dimensional point to check if belongs to the interval
     * @return whether p is inside the interval
     */
    public boolean contains(Point2D p) {
        return this.ix.contains(p.x()) && this.iy.contains(p.y());
    }

    public boolean intersects(Interval2D that) {
        return this.ix.intersects(that.ix) || this.iy.intersects(that.iy);
    }

    public void draw() {
        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 10);

        StdDraw.filledRectangle(this.ix.middle(), this.iy.middle(),
                this.ix.length() / 2.0, this.iy.length() / 2.0);
    }

    public static void main(String[] args) {
        Interval1D i1 = new Interval1D(2, 3.2);
        Interval1D i2 = new Interval1D(3, 4);
        Interval2D d2 = new Interval2D(i1, i2);

        d2.draw();
    }
}


